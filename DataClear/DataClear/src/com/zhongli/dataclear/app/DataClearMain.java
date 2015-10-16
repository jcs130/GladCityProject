package com.zhongli.dataclear.app;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zhongli.dataclear.dao.impl.MsgDAOimpl;
import com.zhongli.dataclear.model.MarkMessage;
import com.zhongli.dataclear.model.MarkRecord;

public class DataClearMain {

	public static void main(String[] args) {
		DataClearMain dc = new DataClearMain();
		// dc.doClean();
		// dc.reCount();
		// 统计还需要标注多少条数据
		dc.countUnDone();
	}

	private void countUnDone() {
		FileOutputStream fos;
		MsgDAOimpl dao = new MsgDAOimpl();
		ArrayList<Long> allSame_text = new ArrayList<Long>();
		ArrayList<Long> oneDifferent_text = new ArrayList<Long>();
		ArrayList<Long> allDifferent_text = new ArrayList<Long>();
		ArrayList<Long> allSame_image = new ArrayList<Long>();
		ArrayList<Long> oneDifferent_image = new ArrayList<Long>();
		ArrayList<Long> allDifferent_image = new ArrayList<Long>();
		int mark_times_left = 0;
		// 统计每个数据的统计次数
		ArrayList<MarkMessage> allMarkMessages = dao.getAllMarkMessages();
		// 0次的+3，1次+2，2次的+1
		for (int i = 0; i < allMarkMessages.size(); i++) {
			MarkMessage temp = allMarkMessages.get(i);
			// 如果大于等于3次则统计是否满足条件
			if (temp.getMark_times() >= 3) {
				// 3次的比较文字的情绪以及图片的情绪，记录下来不同的编号
				int[] result = compareMarkRecord(dao, temp.getMsg_id());
				if (result[0] == 0) {
					// Allsame_text
					allSame_text.add(temp.getMsg_id());
				} else if (result[0] == 1) {
					oneDifferent_text.add(temp.getMsg_id());
				} else if (result[0] == 2) {
					allDifferent_text.add(temp.getMsg_id());
				}
				if (result[1] == 0) {
					allSame_image.add(temp.getMsg_id());
				} else if (result[1] == 1) {
					oneDifferent_image.add(temp.getMsg_id());
				} else if (result[1] == 2) {
					allDifferent_image.add(temp.getMsg_id());
				}
			} else {
				mark_times_left += (3 - temp.getMark_times());
			}
		}
		// 输出统计数据
		System.out.println("文字标记全部一致的数量：" + allSame_text.size());
		writeResult2File("allSame_text.txt", allSame_text);
		System.out.println("文字标记都不一致的数量：" + allDifferent_text.size());
		writeResult2File("allDifferent_text.txt", allDifferent_text);
		System.out.println("文字标记不完全一致的数量：" + oneDifferent_text.size());
		writeResult2File("oneDifferent_text.txt", oneDifferent_text);
		System.out.println("图片标记全部一致的数量：" + allSame_image.size());
		writeResult2File("allSame_image.txt", allSame_image);
		System.out.println("图片标记都不一致的数量：" + allDifferent_image.size());
		writeResult2File("allDifferent_image.txt", allDifferent_image);
		System.out.println("图片标记不完全一致的数量：" + oneDifferent_image.size());
		writeResult2File("oneDifferent_image.txt", oneDifferent_image);
		System.out.println("还需要再标记的数量：" + mark_times_left);

	}

	private void writeResult2File(String filename, ArrayList<Long> list) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(filename));
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			for (int i = 0; i < list.size(); i++) {
				bos.write((list.get(i).toString() + "\n").getBytes());
			}
			bos.flush();
			bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int[] compareMarkRecord(MsgDAOimpl dao, long msg_id) {
		int[] result = new int[2];
		ArrayList<MarkRecord> records = dao.getMarkRecordByID(msg_id);
		int postive_text_num = 0;
		int negative_text_num = 0;
		int neutral_text_num = 0;
		int postive_image_num = 0;
		int negative_image_num = 0;
		int neutral_image_num = 0;
		int record_size = records.size();
		for (int i = 0; i < record_size; i++) {
			// 统计情感记录
			if (records.get(i).getEmotion_text().equals("positive")) {
				postive_text_num += 1;
			} else if (records.get(i).getEmotion_text().equals("negative")) {
				negative_text_num += 1;
			} else if (records.get(i).getEmotion_text().equals("neutral")) {
				neutral_text_num += 1;
			}
			if (records.get(i).getEmotion_medias().get(0).equals("postive")) {
				postive_image_num += 1;
			} else if (records.get(i).getEmotion_medias().get(0)
					.equals("negative")) {
				negative_image_num += 1;
			} else if (records.get(i).getEmotion_medias().get(0)
					.equals("neutral")) {
				neutral_image_num += 1;
			}

		}
		if (postive_text_num == record_size || negative_text_num == record_size
				|| neutral_text_num == record_size) {
			result[0] = 0;
		} else if (postive_text_num > 0 && negative_text_num > 0
				&& neutral_text_num > 0) {
			result[0] = 2;
		} else {
			result[0] = 1;
		}
		if (postive_image_num == record_size
				|| negative_image_num == record_size
				|| neutral_image_num == record_size) {
			result[1] = 0;
		} else if (postive_image_num > 0 && negative_image_num > 0
				&& neutral_image_num > 0) {
			result[1] = 2;
		} else {
			result[1] = 1;
		}
		return result;
	}

	// 重新统计标记数量
	private void reCount() {
		MsgDAOimpl dao = new MsgDAOimpl();
		// 1.获得所有活跃用户ID列表
		ArrayList<Long> userIDs = dao.getActiveUserIDs();
		// 2.更新统计记录个数信息
		for (int i = 0; i < userIDs.size(); i++) {
			int count = dao.getUserCounts(userIDs.get(i));
			dao.updateUserCount(userIDs.get(i), count);
		}
		// 3. 更新表及信息的被标记次数
		ArrayList<Long> markIDs = dao.getMsgIDByTimes(MsgDAOimpl.GREATER, 0);
		for (int i = 0; i < markIDs.size(); i++) {
			long msg_id = markIDs.get(i);
			int count = dao.getMarkRecordByID(markIDs.get(i)).size();
			dao.updateMarkTime(msg_id, count);
			System.out.println("msg_id:" + msg_id + "<> count:" + count);
		}
	}

	private void doClean() {
		MsgDAOimpl dao = new MsgDAOimpl();
		ArrayList<MarkRecord> checkedRecords = new ArrayList<MarkRecord>();
		// 1. 查询mark_messages标注记录大于1次的记录
		List<Long> markIds = dao.getMsgIDByTimes(MsgDAOimpl.GREATER, 1);
		// 2. 对每条记录查询mark_records表
		for (int i = 0; i < markIds.size(); i++) {
			long msg_id = markIds.get(i);
			System.out.println("ID:" + msg_id);
			ArrayList<MarkRecord> uncheckRecords = dao
					.getMarkRecordByID(msg_id);
			// 3. 删除相邻时间用户的重复标注
			checkedRecords.addAll(checkRecord(uncheckRecords, dao));
		}
		// 4. 统计标注次数为3次并且两次记录相同的记录(文字和图片分开)
	}

	private ArrayList<MarkRecord> checkRecord(
			ArrayList<MarkRecord> uncheckRecords, MsgDAOimpl dao) {
		HashMap<Long, MarkRecord> temp = new HashMap<Long, MarkRecord>();
		ArrayList<MarkRecord> res = new ArrayList<MarkRecord>();
		System.out.println("Uncheck Size:" + uncheckRecords.size());
		for (int i = 0; i < uncheckRecords.size(); i++) {
			MarkRecord thisRecord = uncheckRecords.get(i);
			if (temp.containsKey(thisRecord.getUser_id())) {
				// 如果相差时间小于一分钟抛弃记录
				if (Math.abs(thisRecord.getMark_at().compareTo(
						temp.get(thisRecord.getUser_id()).getMark_at())) < 60000) {
					dao.delOneRecord(thisRecord.getRecord_id());
					System.out.println("Del");
				}

			} else {
				temp.put(thisRecord.getUser_id(), thisRecord);
				res.add(thisRecord);
				System.out.println("Good");
			}
		}
		return res;
	}

}
