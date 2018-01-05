package cn.zhr.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.zhr.entity.Bill;
import cn.zhr.entity.User;
import cn.zhr.enums.EarningTypeEnum;
import cn.zhr.enums.PayTypeEnum;
import cn.zhr.service.BillService;
import cn.zhr.service.UserService;
import cn.zhr.util.DateUtil;
import cn.zhr.util.NoGenerate;

@Controller
@RequestMapping("/main/bill")
public class BillController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private UserService userService;
	@Resource
	private BillService billService;

	@RequestMapping("/toWelcome")
	public ModelAndView toWelcome() {
		logger.info(new Date().toString() + " :  /exeLogin 执行");
		ModelAndView mav = new ModelAndView();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		String sysDateString = DateUtil.getSysDateString();
		paramMap.put("createTimeStart", sysDateString + " 00:00:00");
		paramMap.put("createTimeEnd", sysDateString + " 23:59:59");
		String someDayBefore = DateUtil.getSomeDayBefore("yyyy-MM-dd", 1);
		paramMap1.put("createTimeStart", someDayBefore + " 00:00:00");
		paramMap1.put("createTimeEnd", someDayBefore + " 23:59:59");
		try {
			paramMap.put("tranType", "1");// 收入
			Map<String, String> todayIn = billService.selectCostByParam(paramMap);// 今日收入
			paramMap.put("tranType", "0");// 支出
			Map<String, String> todayGet = billService.selectCostByParam(paramMap);// 今日支出
			//
			paramMap1.put("tranType", "1");// 收入
			Map<String, String> yserterIn = billService.selectCostByParam(paramMap1);// 昨日收入
			paramMap1.put("tranType", "0");// 支出
			Map<String, String> yserterinGet = billService.selectCostByParam(paramMap1);// 昨日支出

			// 待验证的订单
			mav.addObject("todayIn", todayIn == null ? "0.00" : todayIn.get("money"));
			mav.addObject("todayGet", todayGet == null ? "0.00" : todayGet.get("money"));
			mav.addObject("yserterIn", yserterIn == null ? "0.00" : yserterIn.get("money"));
			mav.addObject("yserterinGet", yserterinGet == null ? "0.00" : yserterinGet.get("money"));
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
		mav.setViewName("welcome");
		return mav;
	}

	@RequestMapping("/toListBills")
	public ModelAndView toListBills() {
		logger.info(new Date().toString() + " :  /exeLogin 执行");
		ModelAndView mav = new ModelAndView();
		// mav.addObject("payTypeInfo", PayTypeEnum.values());
		// mav.addObject("earningTypeInfo", EarningTypeEnum.values());
		mav.setViewName("bill/listBill");
		return mav;
	}

	@RequestMapping("/toAddBill")
	public ModelAndView toAddBill() {
		logger.info(new Date().toString() + " :  /exeLogin 执行");
		ModelAndView mav = new ModelAndView();
		mav.addObject("payTypeInfo", PayTypeEnum.values());
		mav.addObject("earningTypeInfo", EarningTypeEnum.values());
		mav.setViewName("bill/addBill");
		return mav;
	}

	@RequestMapping("/listBills")
	@ResponseBody
	public String listBills() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title", getString("title"));
		paramMap.put("billNo", getString("billNo"));
		paramMap.put("tranType", getString("type"));
		paramMap.put("totalCostStart", getString("totalCostStart"));
		paramMap.put("totalCostEnd", getString("totalCostEnd"));
		paramMap.put("createTimeStart", getString("createTimeStart"));
		paramMap.put("createTimeEnd", getString("createTimeEnd"));
		String result = "111";
		logger.info("pageNum:" + getString("pageNum"));
		logger.info("numPerPage:" + getString("numPerPage"));
		logger.info("title:" + getString("title"));
		logger.info("billNo:" + getString("billNo"));
		logger.info("tranType:" + getString("type"));
		logger.info("totalCostStart:" + getString("totalCostStart"));
		logger.info("totalCostEnd:" + getString("totalCostEnd"));
		logger.info("createTimeStart:" + getString("createTimeStart"));
		logger.info("createTimeEnd:" + getString("createTimeEnd"));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Bill> listByParam = billService.listByParam(paramMap);
			map.put("rows", listByParam);
			map.put("total", listByParam.size());
		} catch (Exception e) {
			map.put("dataList", null);
			e.printStackTrace();
		}
		map.put("result", result);
		return JSON.toJSONString(map);
	}

	@RequestMapping("/saveBill")
	@ResponseBody
	public String saveBill() {
		User user = new User();
		String tranType = getString("tranType");
		String payType = getString("payType");
		String earningType = getString("earningType");
		String title = getString("title");
		String content = getString("content");
		String tranTarget = getString("tranTarget");
		String totalCost = getString("totalCost");
		String remark = getString("remark");
		Map<String, Object> resultMap = getHashMap();
		Bill bill = new Bill();
		String maxNo = billService.findMaxBillNo();
		bill.setContent(content);
		bill.setBillNo(NoGenerate.getNo(maxNo));
		bill.setCreateTime(DateUtil.getSysDateTimeString());
		bill.setCreator(user.getName());
		bill.setCreatorId(1L);
		bill.setEarningType(Integer.valueOf(earningType));
		bill.setIsDelete(1);
		bill.setPayType(Integer.valueOf(payType));
		bill.setRemark(remark);
		bill.setStatus(0);
		bill.setTitle(title);
		bill.setTotalCost(totalCost);
		bill.setTranTarget(tranTarget);
		bill.setTranType(Integer.valueOf(tranType));
		billService.insert(bill);
		resultMap.put("result", "000");
		resultMap.put("resultMsg", "成功");
		return JSON.toJSONString(resultMap);
	}

	@RequestMapping("/testPage")
	@ResponseBody
	public String testPage() {
		// Map<String,Object> resultMap = getHashMap();
		String str = "<!DOCTYPE html><html><head><title></title></head><body><h1>test</h1></body></html>";
		// resultMap.put("page", str);
		// return JSON.toJSONString(resultMap);
		try {
			getHttpResponse().getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}