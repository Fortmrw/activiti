package cn.zhr.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;

import cn.zhr.base.BaseTest;
import cn.zhr.wxPay.MyWxpayConfig;

public class WxpayTest extends  BaseTest  {
	@Before
	public void before() {
		System.out.println("========Start========");
	}

	@Test
	public void test1() {
		HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", "1231312");
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://test.letiantian.me/wxpay/notify");
        data.put("trade_type", "JSAPI");//NATIVE 扫码支付 JSAPI 公众号
        data.put("product_id", "12");
        Map<String, String> unifiedOrderMap =new HashMap<String,String>();
		try {
			WXPay wxpay = new WXPay(new MyWxpayConfig());
			unifiedOrderMap = wxpay.unifiedOrder(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(JSON.toJSONString(unifiedOrderMap));
	}

}
