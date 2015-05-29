package com.zhongli.dao;

import java.sql.Time;
import java.util.List;

import com.zhongli.model.TwetMsg;

/**
 * 数据库操作接口类
 * @author John
 *
 */
public interface TwetDAO {
	/**
	 * 向数据库中插入推特数据
	 * @param tweet
	 */
	public void insert(TwetMsg tweet);
	
	/**
	 * 向数据库中插入感情数据
	 * @param id
	 * @param motionType 感情种类
	 * @param confidence
	 */
	public void updateEmotion(int id,String motionType,double confidence) ;
	
	/**
	 * 根据消息ID获取推特
	 * @param id
	 * @return
	 */
	public TwetMsg findByID(int id);

	/**
	 * 返回最新的推特
	 * @return
	 */
	public TwetMsg findByMaxID();
	
	/**
	 * 根据传入的参数读取指定的数据
	 * @param location_lat1
	 * @param location_lan1
	 * @param location_lat2
	 * @param location_lan2
	 * @param year_start
	 * @param year_end
	 * @param month_start
	 * @param month_end
	 * @param day_start
	 * @param day_end
	 * @param time_start
	 * @param tame_end
	 * @return
	 */
	public List<TwetMsg> findByParameters(double location_lat1,double location_lan1,double location_lat2,double location_lan2,int year_start,int year_end,int month_start,int month_end,int day_start,int day_end,Time time_start,Time tame_end,String language,String emotion);
}
