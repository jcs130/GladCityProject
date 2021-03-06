package com.zhongli.happycity.extradata.dao.impl;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.zhongli.happycity.extradata.dao.MessageDAO;
import com.zhongli.happycity.extradata.model.MarkMessageObj;
import com.zhongli.happycity.extradata.model.MarkMsg2Web;
import com.zhongli.happycity.extradata.model.MarkRecordObj;
import com.zhongli.happycity.extradata.model.MediaObject;

public class MessageDAOimpl implements MessageDAO {
	private MySQLHelper_Mark markDB;
	private SimpleDateFormat sdf;
	// 缓存区
	private static ArrayList<MarkMessageObj> cacheMessage = new ArrayList<MarkMessageObj>();
	private static int index = 0;
	private int cacheSize;
	private int markMaxTime;

	public MessageDAOimpl(int cacheSize, int markMaxTime) {
		markDB = new MySQLHelper_Mark();
		this.cacheSize = cacheSize;
		this.markMaxTime = markMaxTime;

		sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	@Override
	public ArrayList<MarkMessageObj> getNewMarkingMsg(int limit, String queryOption) {
		// and media_type !='[]'
		String sqlString = "SELECT * FROM mark_messages where " + queryOption + " ORDER BY rand() LIMIT " + limit + ";";
		System.out.println(sqlString);
		Connection conn = null;

		try {
			conn = markDB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ArrayList<MarkMessageObj> res = new ArrayList<MarkMessageObj>();
			MarkMessageObj message;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				message = new MarkMessageObj();
				message.setMsg_id(rs.getLong("msg_id"));
				message.setFull_msg_id(rs.getLong("full_msg_id"));
				message.setText(rs.getString("text"));
				message.setMedia_types(getListFromString(rs.getString("media_types")));
				message.setMedia_urls(getListFromString(rs.getString("media_urls_local")));
				message.setLang(rs.getString("lang"));
				System.out.println(message);
				res.add(message);
			}
			System.out.println("Get new Datas");
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
	 * 检测消息是否还存在
	 * 
	 * @param testURL
	 * @return
	 */
	private boolean isURLAvailable(URL testURL) {
		InputStream in;
		try {
			in = testURL.openStream();
			System.out.println(testURL + " 可以打开");
			in.close();
		} catch (Exception e1) {
			System.out.println(testURL + " 连接打不开!");
			return false;
		}

		return true;
	}

	/**
	 * 将ArrayList的toString()之后生成的字符串转化为ArrayList
	 * 
	 * @param string
	 * @return
	 */
	private List<String> getListFromString(String listString) {
		// 去掉首尾的中括号
		String[] temp = listString.substring(1, listString.length() - 1).split(", ");
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < temp.length; i++) {
			res.add(temp[i]);
		}

		return res;
	}

	@Override
	public MarkMsg2Web getOneNewMsg() {
		String queryOption = "mark_times <" + markMaxTime;
		if (cacheMessage.size() == 0) {
			// 若缓存中无数据，则获取新的数据
			cacheMessage.addAll(getNewMarkingMsg(cacheSize, queryOption));
		} else if (index == cacheMessage.size()) {
			// 若缓存已满，则获取新的数据
			cacheMessage.clear();
			index = 0;
			cacheMessage.addAll(getNewMarkingMsg(cacheSize, queryOption));
		}
		MarkMsg2Web res = new MarkMsg2Web(cacheMessage.get(index));
		index++;
		return res;
	}

	@Override
	public void recordForMessage(long user_id, long message_id, String text_emotion, ArrayList<String> media_emotion) {
		MarkMessageObj message = new MarkMessageObj();
		message = getMessageInfo(message_id);
		// insert into mark_records
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = markDB.getConnection();

			String sqlString = "INSERT INTO mark_records "
					+ "(msg_id, user_id, mark_at, text, emotion_text, media_types, media_urls, media_urls_local, emotion_medias) VALUES"
					+ "(?, ?, NOW(), ?, ?, ?, ?, ?, ?);";
			ps = conn.prepareStatement(sqlString);
			ps.setLong(1, message_id);
			ps.setLong(2, user_id);
			ps.setString(3, message.getText());
			ps.setString(4, text_emotion);

			ps.setString(5, message.getMedia_types().toString());
			ps.setString(6, message.getMedia_urls().toString());
			ps.setString(7, message.getMedia_urls_local().toString());
			ps.setString(8, media_emotion.toString());

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		// update mark_messages
		updateMarkMessage(message_id);
		// update user_details, message_id, updatetime
		updateUserDetail(message_id, user_id);
	}

	@Override
	public void updateMarkMessage(long message_id) {
		PreparedStatement ps = null;
		String sqlString = "UPDATE mark_messages SET mark_times = mark_times + 1 WHERE msg_id = ?;";
		Connection conn = null;
		try {
			conn = markDB.getConnection();
			ps = conn.prepareStatement(sqlString);
			ps.setLong(1, message_id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void createUserDetail(long user_id) {
		PreparedStatement ps = null;
		String sqlString = "INSERT user_details (user_id, updated_on) VALUES (?, NOW());";
		Connection conn = null;
		try {
			conn = markDB.getConnection();
			ps = conn.prepareStatement(sqlString);
			ps.setLong(1, user_id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void updateUserDetail(long message_id, long user_id) {
		PreparedStatement ps = null;
		String sqlString = "UPDATE user_details SET last_recorded_message_id = ?,count=count+1 , updated_on = NOW()"
				+ " WHERE user_id = ?;";
		Connection conn = null;
		try {
			conn = markDB.getConnection();
			ps = conn.prepareStatement(sqlString);
			ps.setLong(1, message_id);
			ps.setLong(2, user_id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public MarkMessageObj getMessageInfo(long message_id) {
		MarkMessageObj message = new MarkMessageObj();
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = markDB.getConnection();

			String sqlString = "SELECT * FROM mark_messages WHERE msg_id = ?;";
			ps = conn.prepareStatement(sqlString);

			ps.setLong(1, message_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ArrayList<String> types = new ArrayList<String>(Arrays.asList(rs.getString("media_types")));
				ArrayList<String> urls = new ArrayList<String>(Arrays.asList(rs.getString("media_urls")));
				ArrayList<MediaObject> medias = new ArrayList<MediaObject>();
				for (int i = 0; i < types.size(); i++) {
					medias.add(new MediaObject(types.get(i), urls.get(i)));
				}
				message.setMsg_id(rs.getLong("msg_id"));
				message.setText(rs.getString("text"));
				message.setMedia_urls_local(getListFromString(rs.getString("media_urls_local")));
				message.setMedia_types(getListFromString(rs.getString("media_types")));
				message.setMedia_urls(getListFromString(rs.getString("media_urls")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return message;
	}

	@Override
	public ArrayList<MarkRecordObj> getRecentRecords(int count, long user_id) {
		MarkRecordObj record;
		ArrayList<MarkRecordObj> records = new ArrayList<MarkRecordObj>();
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = markDB.getConnection();
			String sqlString = "SELECT * FROM mark_records WHERE user_id = ? ORDER BY mark_at DESC";
			if (count == 0) {
				sqlString += ";";
				ps = conn.prepareStatement(sqlString);
				ps.setLong(1, user_id);
			} else {
				sqlString += " LIMIT ?;";
				ps = conn.prepareStatement(sqlString);
				ps.setLong(1, user_id);
				ps.setInt(2, count);
			}
			ResultSet rs = ps.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
				record = new MarkRecordObj();
				record.setEmotion_medias(getListFromString(rs.getString("emotion_medias")));
				record.setEmotion_text(rs.getString("emotion_text"));
				record.setMark_at(sdf.parse(rs.getString("mark_at")));
				record.setMedia_types(getListFromString(rs.getString("media_types")));
				record.setMedia_urls(getListFromString(rs.getString("media_urls_local")));
				record.setMsg_id(rs.getLong("msg_id"));
				record.setRecord_id(rs.getInt("record_id"));
				record.setText(rs.getString("text"));
				record.setUser_id(rs.getLong("user_id"));
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return records;
	}

	private void updateRecordCount(long user_id, int count) {
		PreparedStatement ps = null;
		String sqlString = "UPDATE user_details SET count=" + count + " WHERE user_id = ?;";
		Connection conn = null;
		try {
			conn = markDB.getConnection();
			ps = conn.prepareStatement(sqlString);
			ps.setLong(1, user_id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public int getRecordCount(long user_id) {
		PreparedStatement ps = null;
		int count = 0;
		Connection conn = null;
		try {
			conn = markDB.getConnection();
			String sqlString = "SELECT count(*) FROM mark_records WHERE user_id = ?";
			// String sqlString = "SELECT count FROM user_details WHERE user_id
			// = ?";
			ps = conn.prepareStatement(sqlString);
			ps.setLong(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		updateRecordCount(user_id, count);
		return count;
	}
}
