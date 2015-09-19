package com.zhongli.happycity.web.controller;

import java.util.ArrayList;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhongli.happycity.extradata.dao.MessageDAO;
import com.zhongli.happycity.extradata.dao.impl.MessageDAOimpl;
import com.zhongli.happycity.extradata.model.MarkMsg2Web;
import com.zhongli.happycity.extradata.model.MarkRecordObj;
import com.zhongli.happycity.extradata.model.StatusMsg;
import com.zhongli.happycity.persistence.model.User;

/**
 * 标记界面的控制器
 * 
 * @author zhonglili
 *
 */
@RestController
@RequestMapping(value = "/annotation")
@Secured("ROLE_USER")
public class markPage {
	private static MessageDAO msgDAO = new MessageDAOimpl(100, 3);

	// 从数据库取twitterID
	@RequestMapping("/getnewmessage")
	public MarkMsg2Web getUnMarkedMessage(@AuthenticationPrincipal User user,
			@RequestParam(value = "user_email", defaultValue = "null") String user_email,
			@RequestParam(value = "lang", defaultValue = "en") String languages,
			@RequestParam(value = "annotate_part", defaultValue = "word_and_media") String annotate_part) {
		System.out.println(user);
		// System.out.println("我要获得"+num+"条数据");
		ArrayList<String> lang = new ArrayList<>();
		String[] tmp = languages.split(",");
		for (int i = 0; i < tmp.length; i++) {
			lang.add(tmp[i]);
		}
		System.out.println("user:" + user_email + "lang:" + lang + "annotate_part" + annotate_part);

		// 连接数据库获得数据
		MarkMsg2Web ma = msgDAO.getOneNewMsg();
		System.out.println("return:" + ma);
		return ma;
	}

	// 向标注记录表更新数据
	@RequestMapping("/sendannotatedmessage")
	public StatusMsg getMarkedMessage(@AuthenticationPrincipal User user,
			@RequestParam(value = "user_email", defaultValue = "null") String user_email,
			@RequestParam(value = "msg_id", defaultValue = "null") long msg_id,
			@RequestParam(value = "text_emotion", defaultValue = "null") String text_emotion,
			@RequestParam(value = "media_emotions", defaultValue = "null") String media_emotions) {
		System.out.println(user);
		// System.out.println("我要获得"+num+"条数据");
		ArrayList<String> medias = new ArrayList<>();
		String[] tmp = media_emotions.split(",");
		for (int i = 0; i < tmp.length; i++) {
			medias.add(tmp[i]);
		}
		msgDAO.recordForMessage(user.getId(), msg_id, text_emotion, medias);
		// 连接数据库更新数据，将标注记录加入到标注记录数据库
		StatusMsg status = new StatusMsg(200, "Success");
		return status;
	}

	@RequestMapping("/getrecentannotation")
	public ArrayList<MarkRecordObj> getRecentMarkedMsg(@AuthenticationPrincipal User user) {
		
		ArrayList<MarkRecordObj> recent = msgDAO.getRecentRecords(4,user.getId());

//		System.out.println("\n\n"+recent1.get(0).getMark_at());
//		System.out.println(recent.get(0).getEmotion_medias());
		return recent;
	}
	
	
	
	@RequestMapping("/getrecordcount")
	public int getMarkedMsgNumber(@AuthenticationPrincipal User user) {
		
		int count = msgDAO.getRecordCount(user.getId());
		return count;
	}
	
	@RequestMapping("/getallrecord")
	public ArrayList<MarkRecordObj> getAllMarkedMsg(@AuthenticationPrincipal User user) {
		
		ArrayList<MarkRecordObj> all = msgDAO.getRecentRecords(0,user.getId());

		return all;
	}
	

}
