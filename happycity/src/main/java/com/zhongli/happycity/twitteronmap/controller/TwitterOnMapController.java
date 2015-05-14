package com.zhongli.happycity.twitteronmap.controller;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongli.NLPPart.alchemyapi.api.AlchemyAPI;
import com.zhongli.dao.TwetDAO;
import com.zhongli.model.ShowMsg;
import com.zhongli.model.TwetMsg;

@Controller
public class TwitterOnMapController {
	@RequestMapping(value = "/ajaxgetloc", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public @ResponseBody String getNewLoc() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ApplicationContext context = new ClassPathXmlApplicationContext("springmvc.xml");
		TwetDAO twitterDAO = (TwetDAO) context.getBean("twetDAO");
		TwetMsg tMsg = twitterDAO.findByMaxID();
		ShowMsg sMsg = new ShowMsg(tMsg);
		// 如果没有情绪标记则取得标记
		if (tMsg.getEmotion()==null) {
			// 判断语言并调用情感判断的API判断情感色彩
			if (tMsg.getLanguage().equals("en")) {
				AlchemyAPI alchemyObj = AlchemyAPI
						.GetInstanceFromString("b232c9bbb50818d45e1ecd2f14ea0bc47bdea8d1");
				try {
					Document doc = alchemyObj.TextGetTextSentiment(tMsg
							.getMsg());
					System.out.println(getStringFromDocument(doc));
					// 使用 DOM解析返回的XML文档
					String emotion = doc.getElementsByTagName("type").item(0)
							.getTextContent();
					double score;
					if (emotion.equals("neutral")) {
						score=0;
					} else {
						score = Double.parseDouble(doc
								.getElementsByTagName("score").item(0)
								.getTextContent());
					}
					sMsg.setEmotion(emotion);
					twitterDAO.updateEmotion(tMsg.getId(), emotion, score);
				} catch (XPathExpressionException | IOException | SAXException
						| ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println(tMsg.getLanguage() + "不是英语");
			}
		} else {
			System.out.println("情绪已标记");
		}
			String json = mapper.writeValueAsString(sMsg);
			
			System.out.println(json);
			return json;

	}

	private static String getStringFromDocument(Document doc) {
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);

			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
