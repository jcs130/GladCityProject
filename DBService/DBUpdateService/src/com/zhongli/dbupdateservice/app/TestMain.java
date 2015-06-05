package com.zhongli.dbupdateservice.app;

import java.util.ArrayList;

import com.zhongli.dbupdateservice.dao.impl.JdbcTwetDAO;
import com.zhongli.dbupdateservice.model.EarthSqure;
import com.zhongli.dbupdateservice.model.RegInfo;

/**
 * 测试程序入口
 * 
 * @author zhonglili
 *
 */
public class TestMain {
	public static void main(String[] args) {
		TestMain tm = new TestMain();
		tm.doSth();
	}

	private void doSth() {
		JdbcTwetDAO db = new JdbcTwetDAO();
		// 1.扫描reg数据库，获取所有状态为0的区域并将所有的reg区域的小区域添加
		ArrayList<RegInfo> regs = (ArrayList<RegInfo>) db.getRegInfo(0);
		// 2.根据小区域的坐标向数据库中添加Stream区块
		for (int i = 0; i < regs.size(); i++) {
			ArrayList<EarthSqure> ess = (ArrayList<EarthSqure>) db
					.getStreamSqures(regs.get(i));
			for (int j = 0; j < ess.size(); j++) {
				//db.saveEarthSqure(ess.get(j));
			}
		}

	}
}
