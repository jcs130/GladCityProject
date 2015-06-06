package com.zhongli.dbupdateservice.servicethreads.twitter4j;

import java.io.IOException;
import java.util.ArrayList;

import com.zhongli.dbupdateservice.dao.impl.JdbcTwetDAO;
import com.zhongli.dbupdateservice.model.EarthSqure;
import com.zhongli.dbupdateservice.model.TwetMsg;

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
				String res = "";
				res += "@" + status.getUser().getScreenName() + "\t"
						+ status.getText().replace("\n", " ") + "\t"
						+ status.getCreatedAt() + "\t"
						+ status.getGeoLocation().getLatitude() + ","
						+ status.getGeoLocation().getLongitude() + "\t"
						+ status.getLang() + "\n";
				System.out.print(res);
				// 存入数据库
				TwetMsg tMsg = new TwetMsg(status.getUser().getScreenName(),
						status.getText(), status.getCreatedAt().toString(),
						status.getGeoLocation().getLatitude(), status
								.getGeoLocation().getLongitude(),
						status.getLang());
				try {
					db.insert(tMsg);
					System.out.println(tMsg);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
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
