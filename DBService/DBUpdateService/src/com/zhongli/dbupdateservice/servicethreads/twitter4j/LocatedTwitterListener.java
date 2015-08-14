package com.zhongli.dbupdateservice.servicethreads.twitter4j;

import java.util.ArrayList;

import com.zhongli.dbupdateservice.dao.impl.JdbcTwetDAO;
import com.zhongli.dbupdateservice.model.EarthSqure;
import com.zhongli.dbupdateservice.model.TwetMsg;

import twitter4j.GeoLocation;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * 消息监听器的实现类
 * 
 * @author zhonglili
 *
 */
public class LocatedTwitterListener implements StatusListener {

	private ArrayList<EarthSqure> watchLocs;
	private JdbcTwetDAO db;
	private TwetMsg tMsg;

	public LocatedTwitterListener(ArrayList<EarthSqure> watchLocs,
			JdbcTwetDAO db) {
		this.watchLocs = watchLocs;
		this.db = db;
	}

	public void setWatchLocs(ArrayList<EarthSqure> watchLocs) {
		this.watchLocs = watchLocs;
	}

	@Override
	public void onException(Exception arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatus(Status status) {
		if (status.getGeoLocation() != null) {
			double lat = status.getGeoLocation().getLatitude();
			double lon = status.getGeoLocation().getLongitude();
			if (inTheAreas(lat, lon)) {
				// System.out.println(watchLocs.get(0).getThreadName());
				// System.out.println(lat + "\t" + lon);
				// System.out.println("@" + status.getUser().getScreenName()
				// + " - " + status.getText() + "["
				// + status.getGeoLocation().getLatitude() + ","
				// + status.getGeoLocation().getLongitude() + "]");
				// String res = "";
				// res += "@" + status.getUser().getScreenName() + "\t"
				// + status.getText().replace("\n", " ") + "\t"
				// + status.getCreatedAt() + "\t"
				// + status.getGeoLocation().getLatitude() + ","
				// + status.getGeoLocation().getLongitude() + "\t"
				// + status.getLang() + "\n";
				// System.out.print(res);

				tMsg = new TwetMsg(status.getUser().getScreenName(),
						status.getText(), status.getCreatedAt().toString(),
						status.getGeoLocation().getLatitude(), status
								.getGeoLocation().getLongitude(),
						status.getLang());

			}
		} else {
			double center_lat = 0, center_lon = 0;
			// 根据城市边界确定城市中心
			// String placeType = status.getPlace().getPlaceType();
			// if (placeType.equals("city")) {
			if (status.getPlace() != null) {
				GeoLocation[][] box = status.getPlace()
						.getBoundingBoxCoordinates();
				double sum_lat = 0;
				double sum_lon = 0;
				for (int i = 0; i < box.length; i++) {
					// double sum_lat=0;
					// double sum_lon=0;
					for (int j = 0; j < box[0].length; j++) {
						double lat = box[i][j].getLatitude();
						double lon = box[i][j].getLongitude();
						sum_lat += lat;
						sum_lon += lon;
					}
				}
				center_lat = sum_lat / (double) (box.length * box[0].length);
				center_lon = sum_lon / (double) (box.length * box[0].length);
				tMsg = new TwetMsg(status.getUser().getScreenName(),
						status.getText(), status.getCreatedAt().toString(),
						center_lat, center_lon, status.getLang());
				System.out.println("city:" + status.getPlace().getName()
						+ ",center:[" + center_lat + "," + center_lon + "]");
			} else {
				System.out.println("特殊情况：\n" + status);
			}

		}
		// 存入数据库
		try {
			db.insert(tMsg);
			System.out.println(tMsg);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
		// TODO Auto-generated method stub

	}

	private boolean inTheAreas(double lat, double lon) {
		for (int i = 0; i < watchLocs.size(); i++) {
			if (watchLocs.get(i).containThis(lat, lon)) {
				return true;
			}
		}
		return false;
	}

}
