package com.zhongli.dbupdateservice.app;

import java.util.ArrayList;
import java.util.List;

import com.zhongli.dbupdateservice.model.EarthSqure;
import com.zhongli.dbupdateservice.model.LocArea;

public class TestMain1 {

	public static void main(String[] args) {
		TestMain1 tm = new TestMain1();
		LocArea ottawa = new LocArea(45.616358, -76.453943, 44.999200,
				-75.322352);
		System.out.println(tm.drawRect("ottawa", "#000000", "#FF0000",
				ottawa.getNorth(), ottawa.getSouth(), ottawa.getWest(),
				ottawa.getEast()));
		List<EarthSqure> ottawas = tm.getStreamSqures(ottawa);
		for (int i = 0; i < ottawas.size(); i++) {
			System.out.println(ottawas.get(i).getSouth() + ","
					+ ottawas.get(i).getWest() + ","
					+ ottawas.get(i).getNorth() + ","
					+ ottawas.get(i).getEast());
		}

	}

	public List<EarthSqure> getStreamSqures(LocArea area) {
		ArrayList<EarthSqure> result = new ArrayList<EarthSqure>();
		// System.out.println();
		// System.out.println(reg.getRegName());
		// 循环得到Stream区块
		// 每个区块四个顶点分别计算
		// NW
		EarthSqure e1 = new EarthSqure(area.getNorth(), area.getWest());
		System.out.println("//西北角");
		addToArray(result, e1);
		// NE
		EarthSqure e2 = new EarthSqure(area.getNorth(), area.getEast());
		System.out.println("//东北角");
		addToArray(result, e2);
		// SW
		EarthSqure e3 = new EarthSqure(area.getSouth(), area.getWest());
		System.out.println("//西南角");
		addToArray(result, e3);
		// SE
		EarthSqure e4 = new EarthSqure(area.getSouth(), area.getEast());
		System.out.println("//东南角");
		addToArray(result, e4);
		// System.out.println("fill:");
		// 如果四个顶点所在的区域之间存在空隙则增加空隙区域的区块
		// 首先补全上下两边界的区域
		for (int j = e1.getCol(); j < e2.getCol(); j++) {
			EarthSqure e = new EarthSqure(e1.getRow(), j);
			addToArray(result, e);
		}
		for (int j = e3.getCol(); j < e4.getCol(); j++) {
			EarthSqure e = new EarthSqure(e3.getRow(), j);
			addToArray(result, e);
		}

		// 开始补全中间的行
		for (double j = e3.getNorth(); j < e1.getSouth(); j += 0.15) {
			// 这一行从西到东补全
			EarthSqure ew = new EarthSqure(j, area.getWest());
			EarthSqure ee = new EarthSqure(j, area.getEast());
			addToArray(result, ew);
			addToArray(result, ee);
			for (int k = ew.getCol(); k < ee.getCol(); k++) {
				EarthSqure e = new EarthSqure(ew.getRow(), k);
				addToArray(result, e);
			}
		}

		return result;
	}

	/**
	 * 保证没有重复的区域
	 * 
	 * @param list
	 * @param e
	 * @return
	 */
	private boolean addToArray(ArrayList<EarthSqure> list, EarthSqure e) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRow() == e.getRow()
					&& list.get(i).getCol() == e.getCol()) {
				return false;
			}
		}
		// System.out.println(e);
		System.out.println();

		System.out.println(drawRect("" + list.size(), "#FF0000", "#FFFFF",
				e.getNorth(), e.getSouth(), e.getWest(), e.getEast()));
		list.add(e);
		return true;

	}

	/**
	 * 在地图上划出方形区域
	 * @param name
	 * @param strokeColor
	 * @param fillColor
	 * @param North
	 * @param South
	 * @param West
	 * @param East
	 * @return
	 */
	private String drawRect(String name, String strokeColor, String fillColor,
			double North, double South, double West, double East) {
		String js = "var rectangle"
				+ name
				+ " = new google.maps.Rectangle({\nstrokeColor: '"
				+ strokeColor
				+ "',\nstrokeOpacity: 0.8,\nstrokeWeight: 2,\nfillColor: '"
				+ fillColor
				+ "',\nfillOpacity: 0.35,\nmap: map,\nbounds: new google.maps.LatLngBounds(\nnew google.maps.LatLng("
				+ South + " , " + West + "),\nnew google.maps.LatLng(" + North
				+ "," + East + "))});";
		return js;
	}

}
