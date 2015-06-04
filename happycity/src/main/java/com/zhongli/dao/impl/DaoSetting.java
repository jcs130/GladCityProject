package com.zhongli.dao.impl;

import java.util.ArrayList;

import com.zhongli.model.LocArea;

/**
 * 静态类，用于存储数据库一些参数
 * 
 * @author John
 *
 */
public class DaoSetting {

	private static ArrayList<LocArea> dbAreas = new ArrayList<LocArea>();

	public static ArrayList<LocArea> getDbAreas() {
		return dbAreas;
	}

	private DaoSetting() {};
	
	public void init() {
		if (dbAreas.size() == 0) {
			LocArea dbArea1 = new LocArea(71.52490903732816, -10.546875,
					36.5978891330702, 52.03125, "twets1");
			LocArea dbArea2 = new LocArea(34.30714385628804, -19.6875,
					-35.46066995149529, 63.984375, "twets2");
			LocArea dbArea3 = new LocArea(77.91566898632584, 47.109375,
					48.922499263758255, -168.75, "twets3");
			LocArea dbArea4 = new LocArea(47.040182144806664, 63.28125,
					1.4061088354351594, 149.0625, "twets4");
			LocArea dbArea5 = new LocArea(-1.0546279422758869, 85.078125,
					-47.279229002570816, -177.890625, "twets5");
			LocArea dbArea6 = new LocArea(78.76779175784321, -171.2109375,
					49.15296965617039, -11.953125, "twets6");
			LocArea dbArea7 = new LocArea(48.45835188280866, -128.3203125,
					12.89748918375589, -51.328125, "twets7");
			LocArea dbArea8 = new LocArea(12.211180191503997, -90.3515625,
					-55.97379820507658, -34.1015625, "twets8");
			dbAreas.add(dbArea1);
			dbAreas.add(dbArea2);
			dbAreas.add(dbArea3);
			dbAreas.add(dbArea4);
			dbAreas.add(dbArea5);
			dbAreas.add(dbArea6);
			dbAreas.add(dbArea7);
			dbAreas.add(dbArea8);
		}
	}

	/**
	 * 根据传入的经纬度确定坐标所在的数据库
	 * 
	 * @param lat
	 * @param lan
	 * @return
	 */
	public static String DBChooese(double lat, double lan) {
		for (int i = 0; i < dbAreas.size(); i++) {
			if (lat <= dbAreas.get(i).getNorth()
					&& lat >= dbAreas.get(i).getSouth()
					&& lan >= dbAreas.get(i).getWest()
					&& lan <= dbAreas.get(i).getEast()) {
				return dbAreas.get(i).getLocName();
			}
		}
		return "twets9";
	}
}
