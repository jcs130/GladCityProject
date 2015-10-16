package com.zhongli.dataclear.dao;

import java.util.ArrayList;
import java.util.List;

import com.zhongli.dataclear.model.MarkRecord;

/**
 * 对数据库的操作接口类
 * 
 * @author zhonglili
 *
 */
public interface MsgDAO {

	/**
	 * 查询mark_messages标注记录大于2次的记录 SELECT msg_id FROM happycityweb.mark_messages
	 * where mark_times>2;
	 * 
	 * @param mark_time
	 * @return
	 */
	public ArrayList<Long> getMsgIDByTimes(int mark_time);

	/**
	 * 对每条记录查询mark_records表 SELECT * FROM happycityweb.mark_records where
	 * msg_id=;
	 * 
	 * @param msg_id
	 * @return
	 */
	public ArrayList<MarkRecord> getMarkRecordByID(Long msg_id);

	/**
	 * 删除标注记录 DELETE FROM mark_records WHERE record_id=;
	 * 
	 * @param record_id
	 */
	public void delOneRecord(Long record_id);

}
