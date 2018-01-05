package cn.zhr.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;

import cn.zhr.wxPay.MyWxpayConfig;

@Controller
@RequestMapping("/main/notes")
public class NotesController extends BaseController {

	public String testPay(){
		HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", "1231312");
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://test.letiantian.me/wxpay/notify");
        data.put("trade_type", "NATIVE");//NATIVE 扫码支付 JSAPI 公众号
        data.put("product_id", "12");
        Map<String, String> unifiedOrderMap =new HashMap<String,String>();
		try {
			WXPay wxpay = new WXPay(new MyWxpayConfig());
			unifiedOrderMap = wxpay.unifiedOrder(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(unifiedOrderMap);
	}
}