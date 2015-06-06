package com.zhongli.dbupdateservice.servicethreads.twitter4j;

import java.util.ArrayList;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.zhongli.dbupdateservice.dao.impl.JdbcTwetDAO;
import com.zhongli.dbupdateservice.model.EarthSqure;
import com.zhongli.dbupdateservice.servicethreads.ServiceThread;

/**
 * 将size个区块加入一个监视线程进行监视，并且允许修改这几个区域或是直接停止
 * 
 * @author zhonglili
 *
 */
public class TwitterStreamThread extends ServiceThread {
	private ArrayList<EarthSqure> watchList;
	private JdbcTwetDAO db;
	private TwitterStream twitterStream;
	LocatedTwitterListener listener;

	public TwitterStreamThread(ArrayList<EarthSqure> watchList, TwitterTools tt) {
		this.watchList = watchList;
		this.db = new JdbcTwetDAO();
		ConfigurationBuilder cb = tt.getConfigurationBuilder();
		this.twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		this.listener = new LocatedTwitterListener(watchList, db);
		this.twitterStream.addListener(listener);
	}

	// @Override
	// public void run() {
	// super.run();
	// }
	public void startListening() {
		ArrayList<double[]> locs = new ArrayList<double[]>();
		// 添加Squre
		for (int i = 0; i < watchList.size(); i++) {
			addLocationArea(locs, watchList.get(i).getSouth(), watchList.get(i)
					.getWest(), watchList.get(i).getNorth(), watchList.get(i)
					.getEast());
		}
		double[][] lo = locs.toArray(new double[locs.size()][2]);

		FilterQuery q = new FilterQuery();
		q.locations(lo);
		twitterStream.filter(q);
	}

	// 按照W,S,E,N的顺序添加
	private void addLocationArea(ArrayList<double[]> locs, double south,
			double west, double north, double east) {
		locs.add(new double[] { west, south });
		locs.add(new double[] { east, north });
	}

	// 返回监视列表的大小
	public int getWatchSize() {
		return watchList.size();
	}

	// 将线程名称存入数据库并且修改区块状态
	public void saveThreadname() {
		for (int i = 0; i < watchList.size(); i++) {
			watchList.get(i).setStreamState(1);
			watchList.get(i).setThreadName(this.gettName());
			db.changeSqureState(watchList.get(i).getSqureID(), 1,
					this.gettName());
		}

	}

	// 根据区块ID停止监听
	public void stopStreamSqure(int squreID) {
		for (int i = 0; i < watchList.size(); i++) {
			if (watchList.get(i).getSqureID() == squreID) {
				// 改变数据库这个区域的状态
				db.changeSqureState(squreID, 0, "none");
				watchList.remove(i);
				reStart();
				return;
			}
		}
	}

	// 当监视列表发生变化是重新启动监视
	private void reStart() {
		// 停止当前流
		twitterStream.cleanUp();
		twitterStream.shutdown();
		init();
		// 改变监听器
		listener.setWatchLocs(watchList);
		// 重新运行
		startListening();
	}

	private void init() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("4moUpaUZE1wwmV1ASjm4DCo5s")
				.setOAuthConsumerSecret(
						"CZhI6yAUkCVpLq8zZ4tzV6tuSwG3c8BH1wfVIXJMHUynoQgbp7")
				.setOAuthAccessToken(
						"1663910887-tR8kQSAsXhCJVeQdAseENtntkDVKAbEO9ecX7ee")
				.setOAuthAccessTokenSecret(
						"6NaZeNI28L9kGYTXhbDGeXod26tnBcGA6jPRCAPvXABnf");
		this.twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		this.listener = new LocatedTwitterListener(watchList, db);
		this.twitterStream.addListener(listener);
	}

	@Override
	public void stopMe() {
		// 停止当前流
		twitterStream.clearListeners();
		twitterStream.cleanUp();
		twitterStream.shutdown();
	}

}
