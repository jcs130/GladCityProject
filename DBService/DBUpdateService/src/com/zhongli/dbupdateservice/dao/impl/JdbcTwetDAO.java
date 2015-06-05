package com.zhongli.dbupdateservice.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.zhongli.dbupdateservice.dao.*;
import com.zhongli.dbupdateservice.model.*;

public class JdbcTwetDAO implements TwetDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTwetDAO() {
		dataSource = new DBHelper();
	}

	/*
	 * @Override public void insert(TwetMsg tweet) { String sqlString =
	 * "INSERT INTO alltwets (username, msg, week, month, day, time, timezone, year, location_lat, location_lan, language) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
	 * ; Connection conn = null; try { conn = dataSource.getConnection();
	 * PreparedStatement ps = conn.prepareStatement(sqlString); ps.setString(1,
	 * tweet.getUserName()); ps.setString(2, tweet.getMsg()); ps.setString(3,
	 * tweet.getWeek()); ps.setString(4, tweet.getMonth()); ps.setInt(5,
	 * tweet.getDay()); ps.setString(6, tweet.getTime()); ps.setString(7,
	 * tweet.getTimeZone()); ps.setInt(8, tweet.getYear()); ps.setDouble(9,
	 * tweet.getLocation()[0]); ps.setDouble(10, tweet.getLocation()[1]);
	 * ps.setString(11, tweet.getLanguage()); ps.executeUpdate(); ps.close();
	 * 
	 * } catch (SQLException e) { throw new RuntimeException(e);
	 * 
	 * } finally { if (conn != null) { try { conn.close(); } catch (SQLException
	 * e) { } } } }
	 */

	@Override
	public void insert(TwetMsg tweet) {
		String sqlString = "INSERT INTO ";
		// 根据地理位置信息写入数据道不同的数据库
		sqlString += DaoSetting.DBChooese(tweet.getLocation()[0],
				tweet.getLocation()[1]);
		sqlString += "(username, msg, date, time, location_lat, location_lan, language) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, tweet.getUserName());
			ps.setString(2, tweet.getMsg());
			ps.setString(3,
					makeDate(tweet.getYear(), tweet.getMonth(), tweet.getDay()));
			ps.setString(4, tweet.getTime());
			ps.setDouble(5, tweet.getLocation()[0]);
			ps.setDouble(6, tweet.getLocation()[1]);
			ps.setString(7, tweet.getLanguage());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	/**
	 * 生成符合格式的日期字符串
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private String makeDate(int year, String month, int day) {
		// 将月份转化为数字
		int mo = getIntMonthFromString(month);
		Date date = Date.valueOf("" + year + "-" + mo + "-" + day);
		return date.toString();
	}

	/**
	 * 将英文月份转化为数字
	 * 
	 * @param month
	 * @return
	 */
	private int getIntMonthFromString(String month) {
		if (month.toLowerCase().equals("january")
				|| month.toLowerCase().equals("jan")
				|| month.toLowerCase().equals("jan.")) {
			return 1;
		} else if (month.toLowerCase().equals("february")
				|| month.toLowerCase().equals("feb")
				|| month.toLowerCase().equals("feb.")) {
			return 2;
		} else if (month.toLowerCase().equals("march")
				|| month.toLowerCase().equals("mar")
				|| month.toLowerCase().equals("mar.")) {
			return 3;
		} else if (month.toLowerCase().equals("april")
				|| month.toLowerCase().equals("apr")
				|| month.toLowerCase().equals("apr.")) {
			return 4;
		} else if (month.toLowerCase().equals("may")) {
			return 5;
		} else if (month.toLowerCase().equals("june")
				|| month.toLowerCase().equals("jun")
				|| month.toLowerCase().equals("jun.")) {
			return 6;
		} else if (month.toLowerCase().equals("july")
				|| month.toLowerCase().equals("jul")
				|| month.toLowerCase().equals("jul.")) {
			return 7;
		} else if (month.toLowerCase().equals("august")
				|| month.toLowerCase().equals("aug")
				|| month.toLowerCase().equals("aug.")) {
			return 8;
		} else if (month.toLowerCase().equals("september")
				|| month.toLowerCase().equals("sep")
				|| month.toLowerCase().equals("sep.")) {
			return 9;
		} else if (month.toLowerCase().equals("october")
				|| month.toLowerCase().equals("oct")
				|| month.toLowerCase().equals("oct.")) {
			return 10;
		} else if (month.toLowerCase().equals("november")
				|| month.toLowerCase().equals("nov")
				|| month.toLowerCase().equals("nov.")) {
			return 11;
		} else if (month.toLowerCase().equals("december")
				|| month.toLowerCase().equals("dec")
				|| month.toLowerCase().equals("dec.")) {
			return 12;
		} else {
			return 0;
		}
	}

	@Override
	public TwetMsg findByID(int id) {
		String sqlString = "SELECT * FROM twitterproject.alltwets where numID=?;";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setInt(1, id);
			TwetMsg twetMsg = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				twetMsg = new TwetMsg(rs.getString("userName"),
						rs.getString("msg"), rs.getString("week"),
						rs.getString("month"), rs.getInt("day"),
						rs.getString("time"), rs.getString("timezone"),
						rs.getInt("year"), rs.getDouble("location_lat"),
						rs.getDouble("location_lan"), rs.getString("language"),
						rs.getString("emotion"));
			}
			return twetMsg;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public TwetMsg findByMaxID() {
		String sqlString = "SELECT * FROM alltwets where numID = (SELECT max(numID) FROM alltwets)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			TwetMsg twetMsg = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				twetMsg = new TwetMsg(rs.getString("userName"),
						rs.getString("msg"), rs.getString("week"),
						rs.getString("month"), rs.getInt("day"),
						rs.getString("time"), rs.getString("timezone"),
						rs.getInt("year"), rs.getDouble("location_lat"),
						rs.getDouble("location_lan"), rs.getString("language"),
						rs.getString("emotion"));
				twetMsg.setId(rs.getInt("numid"));
			}
			return twetMsg;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void updateEmotion(int id, String motionType, double confidence) {
		String sqlString = "UPDATE alltwets SET emotion=?, confidence=? WHERE numID=?";
		System.out.println("id:" + id + "motion:" + motionType);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.setString(1, motionType);
			ps.setDouble(2, confidence);
			ps.setInt(3, id);
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	// @Override
	// public List<TwetMsg> findByParameters(double north, double west,
	// double south, double east, int year_start, int year_end,
	// int month_start, int month_end, int day_start, int day_end,
	// Time time_start, Time time_end, String language, String emotion) {
	// ArrayList<TwetMsg> result = new ArrayList<TwetMsg>();
	// // 如果有经纬度信息，则根据经纬度选择数据库，若没有则查询全部八个数据库
	// if (north != 0 && south != 0 && west != 0 && east != 0) {
	// // 1.首先根据经纬度选择表,判断要从哪些数据库表中获取数据，一般只有1个，最多4个
	// HashSet<String> dataBases = new HashSet<String>();
	// dataBases.add(DaoSetting.DBChooese(north, west));
	// dataBases.add(DaoSetting.DBChooese(north, east));
	// dataBases.add(DaoSetting.DBChooese(south, west));
	// dataBases.add(DaoSetting.DBChooese(south, east));
	// System.out.println("the number of databases is : "
	// + dataBases.size());
	// Iterator<String> iterator = dataBases.iterator();
	// while (iterator.hasNext()) {
	// String sqlString = makeSqlString(iterator.next(), north, west,
	// south, east, year_start, year_end, month_start,
	// month_end, day_start, day_end, time_start, time_end,
	// language, emotion);
	// // 查询数据库，获取结果
	// Connection conn = null;
	// try {
	// conn = dataSource.getConnection();
	// PreparedStatement ps = conn.prepareStatement(sqlString);
	// TwetMsg twetMsg = null;
	// ResultSet rs = ps.executeQuery();
	// if (rs.next()) {
	// twetMsg = new TwetMsg(rs.getString("userName"),
	// rs.getString("msg"), rs.getString("week"),
	// rs.getString("month"), rs.getInt("day"),
	// rs.getString("time"), rs.getString("timezone"),
	// rs.getInt("year"),
	// rs.getDouble("location_lat"),
	// rs.getDouble("location_lan"),
	// rs.getString("language"),
	// rs.getString("emotion"));
	// twetMsg.setId(rs.getInt("numid"));
	// result.add(twetMsg);
	// }
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	//
	// } finally {
	// if (conn != null) {
	// try {
	// conn.close();
	// } catch (SQLException e) {
	// }
	// }
	// }
	//
	// }
	// } else {
	// for (int i = 0; i < DaoSetting.getDbAreas().size(); i++) {
	// String sqlString = makeSqlString(DaoSetting.getDbAreas().get(i)
	// .getLocName(), north, west, south, east, year_start,
	// year_end, month_start, month_end, day_start, day_end,
	// time_start, time_end, language, emotion);
	// // 查询数据库，获取结果
	// Connection conn = null;
	// try {
	// conn = dataSource.getConnection();
	// PreparedStatement ps = conn.prepareStatement(sqlString);
	// TwetMsg twetMsg = null;
	// ResultSet rs = ps.executeQuery();
	// if (rs.next()) {
	// twetMsg = new TwetMsg(rs.getString("userName"),
	// rs.getString("msg"), rs.getString("week"),
	// rs.getString("month"), rs.getInt("day"),
	// rs.getString("time"), rs.getString("timezone"),
	// rs.getInt("year"),
	// rs.getDouble("location_lat"),
	// rs.getDouble("location_lan"),
	// rs.getString("language"),
	// rs.getString("emotion"));
	// twetMsg.setId(rs.getInt("numid"));
	// result.add(twetMsg);
	// }
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	//
	// } finally {
	// if (conn != null) {
	// try {
	// conn.close();
	// } catch (SQLException e) {
	// }
	// }
	// }
	//
	// }
	// // 查询剩下的数据库
	// String sqlString = makeSqlString("twets9", north, west, south,
	// east, year_start, year_end, month_start, month_end,
	// day_start, day_end, time_start, time_end, language, emotion);
	// // 查询数据库，获取结果
	// Connection conn = null;
	// try {
	// conn = dataSource.getConnection();
	// PreparedStatement ps = conn.prepareStatement(sqlString);
	// TwetMsg twetMsg = null;
	// ResultSet rs = ps.executeQuery();
	// if (rs.next()) {
	// twetMsg = new TwetMsg(rs.getString("userName"),
	// rs.getString("msg"), rs.getString("week"),
	// rs.getString("month"), rs.getInt("day"),
	// rs.getString("time"), rs.getString("timezone"),
	// rs.getInt("year"), rs.getDouble("location_lat"),
	// rs.getDouble("location_lan"),
	// rs.getString("language"), rs.getString("emotion"));
	// twetMsg.setId(rs.getInt("numid"));
	// result.add(twetMsg);
	// }
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	//
	// } finally {
	// if (conn != null) {
	// try {
	// conn.close();
	// } catch (SQLException e) {
	// }
	// }
	// }
	//
	// }
	//
	// return result;
	// }

	@Override
	public List<TwetMsg> findByParmeters(double north, double west,
			double south, double east, Date date_start, Date date_end,
			Time time_start, Time time_end, String language, String emotion) {
		ArrayList<TwetMsg> result = new ArrayList<TwetMsg>();
		// 如果有经纬度信息，则根据经纬度选择数据库，若没有则查询全部八个数据库
		if (north != 0 && south != 0 && west != 0 && east != 0) {
			// 1.首先根据经纬度选择表,判断要从哪些数据库表中获取数据，一般只有1个，最多4个
			HashSet<String> dataBases = new HashSet<String>();
			dataBases.add(DaoSetting.DBChooese(north, west));
			dataBases.add(DaoSetting.DBChooese(north, east));
			dataBases.add(DaoSetting.DBChooese(south, west));
			dataBases.add(DaoSetting.DBChooese(south, east));
			System.out.println("the number of databases is : "
					+ dataBases.size());
			Iterator<String> iterator = dataBases.iterator();
			while (iterator.hasNext()) {
				String sqlString = makeSqlString(iterator.next(), north, west,
						south, east, date_start, date_end, time_start,
						time_end, language, emotion);
				// 查询数据库，获取结果
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					PreparedStatement ps = conn.prepareStatement(sqlString);
					TwetMsg twetMsg = null;
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						twetMsg = new TwetMsg(rs.getString("username"),
								rs.getString("msg"), rs.getString("week"),
								rs.getString("month"), rs.getInt("day"),
								rs.getString("time"), rs.getString("timezone"),
								rs.getInt("year"),
								rs.getDouble("location_lat"),
								rs.getDouble("location_lan"),
								rs.getString("language"),
								rs.getString("emotion"));
						twetMsg.setId(rs.getInt("numid"));
						result.add(twetMsg);
					}
				} catch (SQLException e) {
					throw new RuntimeException(e);

				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}

			}
		} else {
			for (int i = 0; i < DaoSetting.getDbAreas().size(); i++) {
				String sqlString = makeSqlString(DaoSetting.getDbAreas().get(i)
						.getLocName(), north, west, south, east, date_start,
						date_end, time_start, time_end, language, emotion);
				// 查询数据库，获取结果
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					PreparedStatement ps = conn.prepareStatement(sqlString);
					TwetMsg twetMsg = null;
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						twetMsg = new TwetMsg(rs.getString("username"),
								rs.getString("msg"), rs.getString("week"),
								rs.getString("month"), rs.getInt("day"),
								rs.getString("time"), rs.getString("timezone"),
								rs.getInt("year"),
								rs.getDouble("location_lat"),
								rs.getDouble("location_lan"),
								rs.getString("language"),
								rs.getString("emotion"));
						twetMsg.setId(rs.getInt("numid"));
						result.add(twetMsg);
					}
				} catch (SQLException e) {
					throw new RuntimeException(e);

				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
						}
					}
				}

			}
			// 查询剩下的数据库
			String sqlString = makeSqlString("twets9", north, west, south,
					east, date_start, date_end, time_start, time_end, language,
					emotion);
			// 查询数据库，获取结果
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlString);
				TwetMsg twetMsg = null;
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					twetMsg = new TwetMsg(rs.getString("username"),
							rs.getString("msg"), rs.getString("week"),
							rs.getString("month"), rs.getInt("day"),
							rs.getString("time"), rs.getString("timezone"),
							rs.getInt("year"), rs.getDouble("location_lat"),
							rs.getDouble("location_lan"),
							rs.getString("language"), rs.getString("emotion"));
					twetMsg.setId(rs.getInt("numid"));
					result.add(twetMsg);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);

			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}

		}

		return result;
	}

	// private String makeSqlString(String databaseName, double north,
	// double west, double south, double east, int year_start,
	// int year_end, int month_start, int month_end, int day_start,
	// int day_end, Time time_start, Time time_end, String language,
	// String emotion) {
	//
	// // 根据传入的参数组成SQL查询语句
	// String sqlString = "SELECT * FROM " + databaseName + " where ";
	// if (north != 0 && south != 0 && west != 0 && east != 0) {
	// sqlString += "location_lat<=" + north + "&&location_lan>=" + west
	// + "&&location_lat>=" + south + "&&location_lan<=" + east;
	// }
	// // 由于数据库格式问题不能很好地实现按照日期查询
	// if (year_start != 0 && year_end != 0) {
	// sqlString += "&&year>=" + year_start + "&&year<=" + year_end;
	// }
	// if (month_start != 0 && month_end != 0) {
	// sqlString += "&&month>=" + month_start + "&&month<=" + month_end;
	// }
	// if (month_start != 0 && month_end != 0) {
	// sqlString += "&&month>=" + month_start + "&&month<=" + month_end;
	// }
	// if (day_start != 0 && day_end != 0) {
	// sqlString += "&&day>=" + day_start + "&&day<=" + day_end;
	// }
	// if (time_start != null && time_end != null) {
	// sqlString += "&&time between " + time_start + " and " + time_end;
	// }
	// if (language != "") {
	// sqlString += "&&language=='" + language + "'";
	// }
	// if (emotion != "") {
	// sqlString += "&&emotion=='" + emotion + "'";
	// }
	// sqlString += ";";
	// System.out.println(sqlString);
	//
	// return sqlString;
	// }

	private String makeSqlString(String databaseName, double north,
			double west, double south, double east, Date date_start,
			Date date_end, Time time_start, Time time_end, String language,
			String emotion) {

		// 根据传入的参数组成SQL查询语句
		String sqlString = "SELECT * FROM " + databaseName + " where ";
		if (north != 0 && south != 0 && west != 0 && east != 0) {
			sqlString += "location_lat<=" + north + "&&location_lan>=" + west
					+ "&&location_lat>=" + south + "&&location_lan<=" + east;
		}
		// 由于数据库格式问题不能很好地实现按照日期查询
		if (date_start != null && date_end != null) {
			sqlString += "&&date>=" + date_start + "&&date<=" + date_end;
		}
		if (time_start != null && time_end != null) {
			sqlString += "&&time >= " + time_start + " &&time <= " + time_end;
		}
		if (language != "") {
			sqlString += "&&language=='" + language + "'";
		}
		if (emotion != "") {
			sqlString += "&&emotion=='" + emotion + "'";
		}
		sqlString += ";";
		System.out.println(sqlString);

		return sqlString;
	}

	@Override
	public List<EarthSqure> getSqureInfo(int type) {
		// 根据type获取大区域的信息
		String sqlString = "SELECT * FROM earthsqure where streamstate ="
				+ type + ";";
		ArrayList<EarthSqure> result = new ArrayList<EarthSqure>();
		// 查询数据库，获取结果
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			EarthSqure squre = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				squre = new EarthSqure(rs.getDouble("south"),
						rs.getDouble("north"), rs.getDouble("west"),
						rs.getDouble("east"), rs.getInt("row"),
						rs.getInt("col"), rs.getDouble("degreepersqure"));
				squre.setSqureID(rs.getInt("squreid"));
				squre.setStreamState(rs.getInt("streamstate"));
				squre.setUseTimes(rs.getInt("usetimes"));
				result.add(squre);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	@Override
	public List<RegInfo> getRegInfo(int type) {
		// 根据type获取大区域的信息
		String sqlString = "SELECT * FROM regnames where streamstate =" + type
				+ "&&private=0;";
		ArrayList<RegInfo> result = new ArrayList<RegInfo>();
		// 查询数据库，获取结果
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			RegInfo reg = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reg = new RegInfo(rs.getString("regname"));
				reg.setRegID(rs.getInt("regid"));
				getAreasByReg(reg);
				System.out.println(reg);
				result.add(reg);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		System.out.println(result.size());
		return result;
	}

	// 读取大小区域关系表添加大区域下的小区域
	private void getAreasByReg(RegInfo reg) {
		// 如果列表不为空则清空列表
		if (reg.getAreas().size() != 0) {
			reg.getAreas().clear();
		} else {

			// 先查询regandarea表得到大区域下面的小区与编号
			ArrayList<Integer> areaIDs = new ArrayList<Integer>();
			String sqlString = "SELECT * FROM regandarea where regid="
					+ reg.getRegID() + ";";
			// 查询数据库，获取结果
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlString);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					areaIDs.add(rs.getInt("areaid"));
				}
				// 根据编号添加具体的对象
				for (int i = 0; i < areaIDs.size(); i++) {
					sqlString = "SELECT * FROM interestareas where areaid="
							+ areaIDs.get(i) + ";";
					ps = conn.prepareStatement(sqlString);
					rs = ps.executeQuery();
					while (rs.next()) {
						LocArea loc = new LocArea(areaIDs.get(i),
								rs.getDouble("north"), rs.getDouble("west"),
								rs.getDouble("south"), rs.getDouble("east"));
						loc.setCenterAndRange(
								new LocPoint(rs.getDouble("center_lat"), rs
										.getDouble("center_lon")), rs
										.getInt("range"));
						reg.getAreas().add(loc);
					}
				}

			} catch (SQLException e) {
				throw new RuntimeException(e);

			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}

		}
	}

	@Override
	public void saveEarthSqure(double south, double north, double west,
			double east, int row, int col, double degreepersqure) {
		// 先判断数据库中存不存在相同的区块，如果不存在新建一个数据
		if (!haveSqure(row, col)) {

			Connection conn = null;
			String sqlString = "INSERT INTO earthsqure (south, north, west, east, row, col,degreepersqure) VALUES ("
					+ south
					+ ", "
					+ north
					+ ", "
					+ west
					+ ", "
					+ east
					+ ", "
					+ row + ", " + col + ", " + degreepersqure + ");";
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqlString);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}

		} else {
			// 如果存在了这个区块则什么也不做
		}
	}

	/**
	 * 查询数据库中是否有指定的区块
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean haveSqure(int row, int col) {
		EarthSqure es = getSqureInfo(row, col);
		if (es == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void saveEarthSqure(EarthSqure es) {
		saveEarthSqure(es.getSouth(), es.getNorth(), es.getWest(),
				es.getEast(), es.getRow(), es.getCol(),
				es.getDegreePerSqure_lon());

	}

	@Override
	public EarthSqure getSqureInfo(int row, int col) {
		// 根据type获取大区域的信息
		String sqlString = "SELECT * FROM earthsqure where row =" + row
				+ "&& col =" + col + ";";
		// 查询数据库，获取结果
		Connection conn = null;
		EarthSqure squre = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				squre = new EarthSqure(rs.getDouble("south"),
						rs.getDouble("north"), rs.getDouble("west"),
						rs.getDouble("east"), rs.getInt("row"),
						rs.getInt("col"), rs.getDouble("degreepersqure"));
				squre.setSqureID(rs.getInt("squreid"));
				squre.setStreamState(rs.getInt("streamstate"));
				squre.setUseTimes(rs.getInt("usetimes"));
			}
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return squre;
	}

	@Override
	public List<LocArea> getAreaByLoc(double north, double south, double west,
			double east) {
		String sqlString = "SELECT * FROM interestareas where center_lat<="
				+ north + "&&center_lat>=" + south + "&&center_lon>=" + west
				+ "&&center_lon<=" + east + ";";
		ArrayList<LocArea> result = new ArrayList<LocArea>();
		// 查询数据库，获取结果
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LocArea loc = new LocArea(rs.getInt("areaid"),
						rs.getDouble("north"), rs.getDouble("west"),
						rs.getDouble("south"), rs.getDouble("east"));
				loc.setCenterAndRange(new LocPoint(rs.getDouble("center_lat"),
						rs.getDouble("center_lon")), rs.getInt("range"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	@Override
	public List<EarthSqure> getSquresByLoc(double north, double south,
			double west, double east) {
		// 获取四个顶点的所在的区块，并把之间的区块都添加
		return null;
	}

	@Override
	public List<EarthSqure> getStreamSqures(RegInfo reg) {
		ArrayList<LocArea> areas = reg.getAreas();
		ArrayList<EarthSqure> result = new ArrayList<EarthSqure>();
		System.out.println();
		System.out.println(reg.getRegName());
		// 循环得到Stream区块
		for (int i = 0; i < areas.size(); i++) {
			System.out.println("area:" + (i + 1));
			// 每个区块四个顶点分别计算
			// NW
			EarthSqure e1 = new EarthSqure(areas.get(i).getNorth(), areas
					.get(i).getWest());
			addToArray(result, e1);
			// NE
			EarthSqure e2 = new EarthSqure(areas.get(i).getNorth(), areas
					.get(i).getEast());
			addToArray(result, e2);
			// SW
			EarthSqure e3 = new EarthSqure(areas.get(i).getSouth(), areas
					.get(i).getWest());
			addToArray(result, e3);
			// SE
			EarthSqure e4 = new EarthSqure(areas.get(i).getSouth(), areas
					.get(i).getEast());
			addToArray(result, e4);
			System.out.println("fill:");
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
				EarthSqure ew = new EarthSqure(j, areas.get(i).getWest());
				EarthSqure ee = new EarthSqure(j, areas.get(i).getEast());
				addToArray(result, ew);
				addToArray(result, ee);
				for (int k = ew.getCol(); k < ee.getCol(); k++) {
					EarthSqure e = new EarthSqure(ew.getRow(), k);
					addToArray(result, e);
				}
			}

		}
		return result;
	}

	private boolean addToArray(ArrayList<EarthSqure> list, EarthSqure e) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRow() == e.getRow()
					&& list.get(i).getCol() == e.getCol()) {
				return false;
			}
		}
		System.out.println(e);
		list.add(e);
		return true;

	}

}
