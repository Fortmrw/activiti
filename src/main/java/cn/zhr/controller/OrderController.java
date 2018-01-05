package cn.zhr.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.zhr.entity.OmOrder;
import cn.zhr.entity.OrderProcess;
import cn.zhr.entity.User;
import cn.zhr.service.OrderService;
import cn.zhr.service.UserService;
import cn.zhr.util.NoGenerate;
import cn.zhr.util.SystemConst;

@Controller
@RequestMapping("/main/order")
public class OrderController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private UserService userService;
	@Resource
	private OrderService orderService;
	@Autowired
	private RepositoryService repositoryService;

	@RequestMapping("/toListOrders")
	public ModelAndView toListOrders(ModelAndView mav) {
		logger.info(new Date().toString() + " :  /exeLogin 执行");
		mav.setViewName("order/listOrder");
		return mav;
	}
	
	private List<Map<String,Object>> getProcessDefinition(){
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();// 得到流程定义的查询器
		List<ProcessDefinition> list = processDefinitionQuery.list();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		for (ProcessDefinition processDefinition : list) {
			Map<String,Object> tempMap = new HashMap<String,Object>();
			tempMap.put("name", processDefinition.getName());
			tempMap.put("key", processDefinition.getKey());
			resultList.add(tempMap);
		}
		return resultList;
	}

	@RequestMapping("/toAddOrder")
	public ModelAndView toAddOrder(ModelAndView mav) {
		logger.info(new Date().toString() + " :  /exeLogin 执行");
		mav.addObject("orderType", getProcessDefinition());
		mav.setViewName("order/addOrder");
		return mav;
	}

	@RequestMapping("/listOrders")
	@ResponseBody
	public String listOrders() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderNo", getString("orderNo"));
		paramMap.put("creater", getString("creater"));
		paramMap.put("status", getString("status"));
		paramMap.put("priceStart", getString("priceStart"));
		paramMap.put("priceEnd", getString("priceEnd"));
		String result = "111";
		logger.info("pageNum:" + getString("pageNum"));
		logger.info("numPerPage:" + getString("numPerPage"));
		logger.info("orderNo:" + getString("orderNo"));
		logger.info("creater:" + getString("creater"));
		logger.info("status:" + getString("status"));
		logger.info("priceStart:" + getString("priceStart"));
		logger.info("priceEnd:" + getString("priceEnd"));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<OmOrder> listByParam = orderService.listByParam(paramMap);
			map.put("rows", listByParam);
			map.put("total", listByParam.size());
			result = "000";
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("result", result);
		return JSON.toJSONString(map);
	}

	/**
	 * 1.启动流程实例 
	 * 2.订单单表中insert一条记录
	 * @return
	 */
	@RequestMapping("/saveOrder")
	@ResponseBody
	public String saveOrder() {
		User user = (User)getHttpSession().getAttribute(SystemConst.LOGIN_USER);
		String price = getString("price");
		String type = getString("type");
		String remark = getString("remark");
		Map<String, Object> resultMap = getHashMap();
		OmOrder order = new OmOrder();
		order.setCreater(user.getName());
		order.setOrderDate(new Date());
		String maxNo = orderService.findMaxOrderNo();
		order.setOrderNo(NoGenerate.getNo(maxNo));
		order.setPrice(new BigDecimal(price));
		order.setRemark(remark);
		order.setType(type);
		order.setStatus("");
		orderService.saveProcessOrder(order);
		resultMap.put("result", "000");
		resultMap.put("resultMsg", "成功");
		return JSON.toJSONString(resultMap);
	}
	
	@RequestMapping("/toOrderTaskList")
	public ModelAndView toOrderTaskList(ModelAndView mav) {
		logger.info(new Date().toString() + " :  /exeLogin 执行");
		mav.setViewName("order/orderTaskList");
		return mav;
	}
	
	
	/**
	 * 查询当前用户的任务列表
	 */
	@ResponseBody
	@RequestMapping("orderTaskList")
	public String orderTaskList() {
		//获取用户对象
		User user = (User)getHttpSession().getAttribute(SystemConst.LOGIN_USER);
		//调用业务逻辑，完成任务列表的查看
		List<OrderProcess> list = orderService.findOrderTaskList(user.getName());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", list.size());
		return JSON.toJSONString(map);
	}
	
	/**
	 * 提交 订单
	 * 对于activiti就是要完成当前的任务
	 */
	@RequestMapping("submitOrder")
	public String submitOrder() {
		String taskId = getString("taskId");
		//获取用户对象
		User user = (User)getHttpSession().getAttribute(SystemConst.LOGIN_USER);
		//调用业务逻辑
		orderService.submitOrderProcess(user.getName(), taskId);
		//再次查询任务列表
		return "redirect:/main/activiti/toOrderTaskList";
	}

}