package com.zhongli.happycity;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 * 控制不同页面切换
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * 主欢迎界面
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//返回主界面
		
		return "index";
	}
	/**
	 * 主欢迎界面
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String backhome(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//返回主界面
		
		return "index";
	}
	/**
	 * 返回实时显示Twitter界面
	 * @return
	 */
	@RequestMapping(value = "/twitteronmap", method = RequestMethod.GET)
	public String TOMap() {
		return "TwitteronMap";
		
	}
	
}
