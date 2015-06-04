package com.zhongli.model;

import java.util.Arrays;

public class TwetMsg {
	private int id;
	private String userName;
	private String msg;
	private String week;
	private String month;
	private int day;
	private String timeZone;
	private int year;
	private String time;
	private double[] location;
	private String language;
	private String emotion = "";

	public TwetMsg(String userName, String msg, String week, String month,
			int day, String time, String timeZone, int year,
			double location_lat, double location_lon, String language,
			String emontion) {
		this.userName = userName;
		this.msg = msg;
		this.week = week;
		this.month = month;
		this.day = day;
		this.time = time;
		this.timeZone = timeZone;
		this.year = year;
		this.location = new double[] { location_lat, location_lon };
		this.language = language;
		this.emotion = emontion;
	}

	/**
	 * 分析原始信息得到对象
	 * 
	 * @param rawTwet
	 */
	public TwetMsg(String rawTwet) {
		String[] info = rawTwet.split("\t");
		this.userName = info[0];
		this.msg = info[1];
		String times[] = info[2].split(" ");
		this.week = times[0];
		this.month = times[1];
		this.day = Integer.parseInt(times[2]);
		this.time = times[3];
		this.timeZone = times[4];
		this.year = Integer.parseInt(times[5]);
		String[] loc = info[3].split(",");
		this.location = new double[] { Double.parseDouble(loc[0]),
				Double.parseDouble(loc[1]) };
		this.language = info[4];
	}

	public TwetMsg(String username, String msg, String datetime,
			double latitude, double longitude, String lang) {
		this.userName = username;
		this.msg = msg;
		String times[] = datetime.split(" ");
		this.week = times[0];
		this.month = times[1];
		this.day = Integer.parseInt(times[2]);
		this.time = times[3];
		this.timeZone = times[4];
		this.year = Integer.parseInt(times[5]);
		this.location = new double[] { latitude, longitude };
		this.language = lang;
	}

	public String getUserName() {
		return userName;
	}

	public String getMsg() {
		return msg;
	}

	public String getTime() {
		return time;
	}

	public double[] getLocation() {
		return location;
	}

	public String getLanguage() {
		return language;
	}

	public String getWeek() {
		return week;
	}

	public String getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public int getYear() {
		return year;
	}

	public String getEmotion() {
		return emotion;
	}

	@Override
	public String toString() {
		return "TwetMsg [id=" + id + ", userName=" + userName + ", msg=" + msg
				+ ", week=" + week + ", month=" + month + ", day=" + day
				+ ", timeZone=" + timeZone + ", year=" + year + ", time="
				+ time + ", location=" + Arrays.toString(location)
				+ ", language=" + language + ", emotion=" + emotion + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
