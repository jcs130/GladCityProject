package com.zhongli.dataclear.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.zhongli.dataclear.dao.MsgDAO;
import com.zhongli.dataclear.model.MarkRecord;

public class MsgDAOimpl implements MsgDAO {
	private MySQLHelper_Mark markDB;
	private SimpleDateFormat sdf;

	public MsgDAOimpl() {
		markDB = new MySQLHelper_Mark();
		sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC-4"));
	}

	@Override
	public ArrayList<Long> getMsgIDByTimes(int mark_time) {
		// and media_type !='[]'
		String sqlString = "SELECT msg_id FROM mark_messages where mark_times>"
				+ mark_time + " and msg_id>16659;";
		// System.out.println(sqlString);
		Connection conn = null;

		try {
			conn = markDB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ArrayList<Long> res = new ArrayList<Long>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("msg_id");
				res.add(id);
				System.out.println("msg_id:"+id);
			}
			return res;
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
	public ArrayList<MarkRecord> getMarkRecordByID(Long msg_id) {
		// and media_type !='[]'
		String sqlString = "SELECT * FROM mark_records where msg_id=" + msg_id
				+ ";";
		Connection conn = null;
		try {
			conn = markDB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ArrayList<MarkRecord> res = new ArrayList<MarkRecord>();
			MarkRecord message;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				message = new MarkRecord();
				message.setMsg_id(rs.getLong("msg_id"));
				message.setRecord_id(rs.getLong("record_id"));
				message.setText(rs.getString("text"));
				message.setEmotion_text(rs.getString("emotion_text"));
				message.setMedia_types(getListFromString(rs
						.getString("media_types")));
				message.setMedia_urls(getListFromString(rs
						.getString("media_urls")));
				message.setEmotion_medias(getListFromString(rs
						.getString("emotion_medias")));
				message.setUser_id(rs.getLong("user_id"));
				try {
					message.setMark_at(sdf.parse(rs.getString("mark_at")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(message);
				res.add(message);
			}
			// System.out.println("Get new Datas");
			return res;
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
	 * 将ArrayList的toString()之后生成的字符串转化为ArrayList
	 * 
	 * @param string
	 * @return
	 */
	private List<String> getListFromString(String listString) {
		// 去掉首尾的中括号
		String[] temp = listString.substring(1, listString.length() - 1).split(
				", ");
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < temp.length; i++) {
			res.add(temp[i]);
		}

		return res;
	}

	@Override
	public void delOneRecord(Long record_id) {
		// and media_type !='[]'
		String sqlString = "DELETE FROM mark_records WHERE record_id="
				+ record_id + ";";
		// System.out.println(sqlString);
		Connection conn = null;

		try {
			conn = markDB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.executeUpdate();
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
	 * SELECT user_id FROM user_details;
	 * 
	 * @return
	 */
	public ArrayList<Long> getActiveUserIDs() {
		// and media_type !='[]'
		String sqlString = "SELECT user_id FROM user_details";
		// System.out.println(sqlString);
		Connection conn = null;

		try {
			conn = markDB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ArrayList<Long> res = new ArrayList<Long>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("user_id");
				res.add(id);
				System.out.println(id);
			}
			return res;
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

	public int getUserCounts(Long user_id) {
		// and media_type !='[]'
		String sqlString = "SELECT count(*) FROM happycityweb.mark_records where user_id="
				+ user_id + ";";
		// System.out.println(sqlString);
		Connection conn = null;

		try {
			conn = markDB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			int res = 0;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
			return res;
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

	public void updateUserCount(Long user_id, int count) {
		// and media_type !='[]'
		String sqlString = "UPDATE user_details SET count=" + count
				+ " WHERE user_id=" + user_id + ";";
		// System.out.println(sqlString);
		Connection conn = null;

		try {
			conn = markDB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.executeUpdate();
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
	 * UPDATE mark_messages SET mark_times= WHERE msg_id=;
	 * 
	 * @param msg_id
	 * @param mark_times
	 */
	public void updateMarkTime(Long msg_id, int mark_times) {
		// and media_type !='[]'
		String sqlString = "UPDATE mark_messages SET mark_times=" + mark_times
				+ " WHERE msg_id=" + msg_id + ";";
		// System.out.println(sqlString);
		Connection conn = null;

		try {
			conn = markDB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ps.executeUpdate();
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
