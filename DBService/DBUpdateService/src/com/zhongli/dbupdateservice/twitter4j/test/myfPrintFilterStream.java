/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhongli.dbupdateservice.twitter4j.test;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import twitter4j.*;

/**
 * <p>
 * This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class myfPrintFilterStream {
	/**
	 * Main entry of this application.
	 *
	 * @param args
	 *            arguments doesn't take effect with this example
	 */
	public static void main(String[] args) throws TwitterException {
		double south = 40.41916167664671;
		double north = 40.95808383233533;
		double west = -74.11764705882354;
		double east = -73.41176470588235;
		south = 40.74626865671642;
		north = 41.19402985074627;
		west = -74.60526315789474;
		east = -74.01315789473684;
		String filePath = "out.txt";

		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		StatusListener listener;
		// try {
		listener = new statusLintener(west, south, east, north);
		twitterStream.addListener(listener);
		ArrayList<double[]> locs = new ArrayList<double[]>();
		// ottawa
		// double north = -76.453943;
		// double east = 44.999200;
		// double south = -75.322352;
		// double west = 45.616358;
		// NewYork
		addLocationArea(locs, south, west, north, east);
		// locs.add(new double[] { -76.453943, 44.999200 });
		// locs.add(new double[] { -75.322352, 45.616358 });
		double[][] lo = locs.toArray(new double[locs.size()][2]);
		System.out.println(lo.toString());
		// twitterStream.sample();
		// FilterQuery q= new FilterQuery(0, new long[] {1234}, new String[]
		// {"java"},lo);
		FilterQuery q = new FilterQuery();
		q.locations(lo);
		System.out.println(q.toString());
		// q.setGeoCode(new GeoLocation(39.9210645, 116.4073950), 10,
		// Query.MILES);
		twitterStream.filter(q);
		// } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	private static void addLocationArea(ArrayList<double[]> locs, double south,
			double west, double north, double east) {
		locs.add(new double[] { west, south });
		locs.add(new double[] { east, north });
	}

}

class statusLintener implements StatusListener {

	double north = 0;
	double east = 0;
	double south = 0;
	double west = 0;

	public statusLintener(double west, double south, double east, double north) {
		this.west = west;
		this.south = south;
		this.east = east;
		this.north = north;
	}

	String context = "";

	// FileOutputStream fos = new FileOutputStream(filePath);
	// BufferedOutputStream bos = new BufferedOutputStream(fos);

	@Override
	public void onStatus(Status status) {
		if (status.getGeoLocation() != null) {
			double lat = status.getGeoLocation().getLatitude();
			double lon = status.getGeoLocation().getLongitude();
			if (lat > south && lat < north && lon > west && lon < east) {
				System.out.println(lat + "\t" + lon);
				// System.out.println("@" + status.getUser().getScreenName()
				// + " - " + status.getText() + "["
				// + status.getGeoLocation().getLatitude() + ","
				// + status.getGeoLocation().getLongitude() + "]");
			}
		}

	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
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
		System.out.println("Got scrub_geo event userId:" + userId
				+ " upToStatusId:" + upToStatusId);

	}

	@Override
	public void onStallWarning(StallWarning warning) {
		// System.out.println("Got stall warning:" + warning);
	}

	@Override
	public void onException(Exception ex) {
		ex.printStackTrace();
	}

	public double getNorth() {
		return north;
	}

	public void setNorth(double north) {
		this.north = north;
	}

	public double getEast() {
		return east;
	}

	public void setEast(double east) {
		this.east = east;
	}

	public double getSouth() {
		return south;
	}

	public void setSouth(double south) {
		this.south = south;
	}

	public double getWest() {
		return west;
	}

	public void setWest(double west) {
		this.west = west;
	}

}
