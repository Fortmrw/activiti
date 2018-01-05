package cn.zhr.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.zhr.entity.User;
import cn.zhr.service.UserService;

@Controller
@RequestMapping("/main/user")
public class UserController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private UserService userService;

	@RequestMapping("/userList")
	public ModelAndView getAllUser() {
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> hashMap = getHashMap();
		List<User> listByParam = userService.listByParam(hashMap);
		mav.addObject("userList", listByParam);
		mav.setViewName("welcome");
		logger.info(new Date().toString() + "/userList 执行");
		return mav;
	}
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping("/toRegister")
	public ModelAndView toRegister() {
		logger.info(new Date().toString() + "/userList 执行");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register");
		return mav;
	}
}