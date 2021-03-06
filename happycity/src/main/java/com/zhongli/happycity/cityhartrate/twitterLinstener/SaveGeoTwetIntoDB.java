package com.zhongli.happycity.cityhartrate.twitterLinstener;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhongli.dao.TwetDAO;
import com.zhongli.model.TwetMsg;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 将带有地理位置的信息存入数据库
 * 
 * @author John
 *
 */
public class SaveGeoTwetIntoDB extends Thread {

	// public static void main(String[] args) {
	// SaveGeoTwetIntoDB s2d = new SaveGeoTwetIntoDB();
	// s2d.runServer();
	// }

	@Override
	public void run() {
		runServer();
	};

	/**
	 * 开始数据获取
	 */
	private void runServer() {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("4moUpaUZE1wwmV1ASjm4DCo5s")
				.setOAuthConsumerSecret(
						"CZhI6yAUkCVpLq8zZ4tzV6tuSwG3c8BH1wfVIXJMHUynoQgbp7")
				.setOAuthAccessToken(
						"1663910887-tR8kQSAsXhCJVeQdAseENtntkDVKAbEO9ecX7ee")
				.setOAuthAccessTokenSecret(
						"6NaZeNI28L9kGYTXhbDGeXod26tnBcGA6jPRCAPvXABnf");
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
				.getInstance();

		StatusListener listener;
		listener = new StatusListener() {
			// String outPath = "datas/out.txt";
			// FileOutputStream fos = new FileOutputStream(outPath);
			// BufferedOutputStream bos = new BufferedOutputStream(fos);
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"springmvc.xml");
			TwetDAO twitterDAO = (TwetDAO) context.getBean("twetDAO");

			@Override
			public void onStatus(Status status) {
				String res = "";
				if (status.getGeoLocation() != null) {
					res += "@" + status.getUser().getScreenName() + "\t"
							+ status.getText().replace("\n", " ") + "\t"
							+ status.getCreatedAt() + "\t"
							+ status.getGeoLocation().getLatitude() + ","
							+ status.getGeoLocation().getLongitude() + "\t"
							+ status.getLang() + "\n";
					System.out.print(res);
					// 写入文件or存入数据库
					TwetMsg tMsg = new TwetMsg(
							status.getUser().getScreenName(), status.getText(),
							status.getCreatedAt().toString(), status
									.getGeoLocation().getLatitude(), status
									.getGeoLocation().getLongitude(),
							status.getLang());
					try {
						twitterDAO.insert(tMsg);
						System.out.println(tMsg);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// try {
					// bos.write(res.getBytes());
					// bos.flush();
					// } catch (IOException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					res = "";

				}

			}

			@Override
			public void onDeletionNotice(
					StatusDeletionNotice statusDeletionNotice) {
				// System.out.println("Got a status deletion notice id:" +
				// statusDeletionNotice.getStatusId());
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// System.out.println("Got track limitation notice:" +
				// numberOfLimitedStatuses);
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// System.out.println("Got scrub_geo event userId:" + userId
				// +
				// " upToStatusId:" + upToStatusId);
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				// System.out.println("Got stall warning:" + warning);
			}

			@Override
			public void onException(Exception ex) {
				ex.printStackTrace();
			}

		};
		// Listener
		twitterStream.addListener(listener);

		twitterStream.sample();

		// Filter
		// FilterQuery filtre = new FilterQuery(new long[] {1663910887});
		// twitterStream.filter(filtre);

	}
}
