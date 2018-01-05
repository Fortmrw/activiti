package cn.zhr.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.zhr.entity.User;
import cn.zhr.service.BillService;
import cn.zhr.service.UserService;
import cn.zhr.util.SystemConst;

@Controller
@RequestMapping("login")
public class LoginController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private UserService userService;
	@Resource
	private BillService billService;

	@RequestMapping("/toLogin")
	public ModelAndView toLogin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping("/exeLogin")
	@ResponseBody
	public String exeLogin() {
		logger.info(new Date().toString() + " :  /exeLogin 执行");
		String username = getString("username");
		String password = getString("password");
		Subject subject = SecurityUtils.getSubject();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			getHttpSession().setAttribute(SystemConst.LOGIN_USER, userService.selectByName(username));
			resultMap.put("result", "000");
			resultMap.put("resultMsg", "成功");
		} catch (AuthenticationException e) {
			logger.error("异常信息",e);
			resultMap.put("result", "111");
			resultMap.put("resultMsg", e.getMessage());
		}
		return JSON.toJSONString(resultMap);
	}
	
	/**
	 * 验证用户
	 * @return
	 */
	@RequestMapping("/existUser")
	@ResponseBody
	public String existUser() {
		logger.info(new Date().toString() + " :  /exeLogin 执行");
		String username = getString("username");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			User selectByName = userService.selectByName(username);
			if(selectByName!=null){
				resultMap.put("valid", false);
			}else{
				resultMap.put("valid", true);
			}
		} catch (AuthenticationException e) {
			logger.error("异常信息",e);
			resultMap.put("valid", false);
			resultMap.put("result", "111");
			resultMap.put("resultMsg", e.getMessage());
		}
		return JSON.toJSONString(resultMap);
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
	
	/**
	 * 执行注册
	 * @return
	 */
	@RequestMapping("/exeRegister")
	public String exeRegister() {
		logger.info(new Date().toString() + " :  /exeRegister 执行");
		String username = getString("username");
		String password = getString("password");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		User user = new User();
		try {
			user.setCreateTime(new Date());
			user.setName(username);
			user.setPassword(password);
			userService.insert(user);
			resultMap.put("result", "000");
			resultMap.put("resultMsg", "成功");
		} catch (AuthenticationException e) {
			logger.error("异常信息",e);
			resultMap.put("result", "111");
			resultMap.put("resultMsg", e.getMessage());
		}
		return JSON.toJSONString(resultMap);
	}
	
}