package cn.zhr.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@Controller
@CrossOrigin
@RequestMapping("/test")
public class TestController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/testHeader")
	@ResponseBody
	public String getAllUser() {
		logger.info(new Date().toString() + "/testHeader 执行");
		
		HttpServletRequest httpRequest = getHttpRequest();
		String wang = httpRequest.getHeader("wang");
		String ya = httpRequest.getHeader("ya");
		String ping = httpRequest.getHeader("ping");
		
//		String channel = httpRequest.getHeader("X-FM-CHANNEL");
//		String openId = httpRequest.getHeader("X-FM-OPEN-ID");
		
		Map <String, Object> map = new HashMap<String,Object>();
		map.put("header", wang+" "+ya +" "+ ping);
		map.put("body", getString("param"));
//		map.put("channel", channel);
//		map.put("openId", openId);
//		map.put("header", getString("header"));
//		map.put("body", getString("body"));
		
		logger.info(new Date().toString() + "/testHeader 结束");
		System.out.println("%%%%%%%%%%"+map);
		return JSON.toJSONString(map);
	}
	@RequestMapping("/toTestHeader")
	public ModelAndView toTestHeader(ModelAndView mav) {
		logger.info(new Date().toString() + "/toTestHeader 执行");
		mav.setViewName("testHeader");
		logger.info(new Date().toString() + "/toTestHeader 结束");
		return mav ;
	}
}
