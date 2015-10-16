package com.zhongli.dataclear.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zhongli.dataclear.dao.impl.MsgDAOimpl;
import com.zhongli.dataclear.model.MarkRecord;

public class DataClearMain {

	public static void main(String[] args) {
		DataClearMain dc = new DataClearMain();
//		dc.doClean();
		dc.reCount();
	}

	// 重新统计标记数量
	private void reCount() {
		MsgDAOimpl dao = new MsgDAOimpl();
//		// 1.获得所有活跃用户ID列表
//		ArrayList<Long> userIDs = dao.getActiveUserIDs();
//		// 2.更新统计记录个数信息
//		for (int i = 0; i < userIDs.size(); i++) {
//			int count = dao.getUserCounts(userIDs.get(i));
//			dao.updateUserCount(userIDs.get(i), count);
//		}
		// 3. 更新表及信息的被标记次数
		ArrayList<Long> markIDs = dao.getMsgIDByTimes(0);
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
		List<Long> markIds = dao.getMsgIDByTimes(1);
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
