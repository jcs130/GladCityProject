package com.zhongli.happycity;

import com.zhongli.happycity.extradata.dao.MessageDAO;
import com.zhongli.happycity.extradata.dao.impl.MessageDAOimpl;

public class Application {
	public static MessageDAO msgDAO = new MessageDAOimpl(100, 3);
}