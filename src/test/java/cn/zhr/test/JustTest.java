package cn.zhr.test;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.zhr.entity.Bill;

public class JustTest {

	@Test
	public void test1(){
		String str = "201706245";
		String str1 = "20170623";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("str",str);
		map.put("str1",str1);
		System.out.println(map.toString());
		System.out.println(str.compareTo(str1));
		System.out.println(Integer.parseInt(str1)+1);
	}
	@Test
	public void test13(){
		Comparator<Object> com=Collator.getInstance(java.util.Locale.CHINA);
		ArrayList<Object> list = new ArrayList<Object>();
		list.add("河南");
		list.add("北京");
		list.add("厦门");
		list.add("深圳");
		list.add("阿拉善");
		Object[] array = list.toArray();
		Arrays.sort(array,com);
		System.out.println(Arrays.toString(array));
//		System.out.println(Arrays.toString(array));
	}
	@Test
	public void test12(){
		ArrayList<Object> list = new ArrayList<Object>();
		list.add("a");
		list.add(true);
		list.add("a");
		Object[] array = list.toArray();
		System.out.println(Arrays.toString(array));
	}
	@Test
	public void test11(){
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DAY_OF_YEAR, 1000000);
		System.out.println(instance.getTime().getTime());
	}
	@Test
	public void test2(){
		Date date = new Date();
		String datestr = date.toString();
		System.out.println(datestr);
	}
	
	@Test
	public void test3(){
		Long str = 1499930488000L;
		System.out.println(new Date(str).toString());
	}
	@Test
	public void test4(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("key", "value");
		map.put("key1", null);
		System.out.println(JSON.toJSONString(map,SerializerFeature.WriteMapNullValue));
	}
	@Test
	public void test5(){
		System.out.println(Long.MAX_VALUE);
		String str = "20170804141523152";
//		String str = "9223372036854775807";
		long parseLong = Long.parseLong(str);
		System.out.println(parseLong);
		
		BigDecimal  test = new BigDecimal(0);
		test = test.add(new BigDecimal(10));
		System.out.println(test);
	}
	@Test
	public void test6(){
		System.out.println(BigDecimal.valueOf(Double.parseDouble("4.00")).compareTo(BigDecimal.valueOf(Double.parseDouble("5.00"))));
	}
	@Test
	public void test7(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("customerId", 5);
		map.put("reason", "不想买了");
		map.put("storeName", "睿尚城");
		map.put("quantity", 1);
		map.put("unitPrice", 5.00);
		System.out.println(JSON.toJSONString(map));
	}
	@Test
	public void test8(){
		System.out.println(BigDecimal.valueOf(Double.parseDouble("6.00")).compareTo(BigDecimal.valueOf(Double.parseDouble("5.00"))));
	}
	@Test
	public void test9(){
		String str1 = "[{\"address\":\"啊啊啊啊\",\"contact\":\"现在\",\"contactPhone\":\"13366361616\",\"concessionsAmount\":\"0\",\"customerId\":\"39\",\"customerName\":\"张小七\",\"gathering\":\"476.00\",\"productCost\":\"476.00\",\"remark\":\"\",\"storeId\":\"3\",\"storeName\":\"睿婴馆\",\"totalCost\":\"476.00\",\"childList\":[{\"basePrice\":\"420\",\"bigImage\":\"group1/M00/00/04/Ch48Dlm7QYiAC07-AABXl7dfNRU211.jpg\",\"centerImage\":\"\",\"smallImage\":\"\",\"productId\":\"17\",\"productName\":\"真皮书房椅会议室椅职员经理椅实木\",\"quantity\":\"1\",\"unitPrice\":\"476\"}],\"shoppingId\":\"1\"}]";
		String str3 = "[{\"address\":\"啊啊啊啊\",\"contact\":\"现在\",\"contactPhone\":\"13366361616\",\"concessionsAmount\":\"0\",\"customerId\":\"39\",\"customerName\":\"张小七\",\"gathering\":\"476.00\",\"productCost\":\"476.00\",\"remark\":\"\",\"storeId\":\"3\",\"storeName\":\"睿婴馆\",\"totalCost\":\"476.00\",\"childList\":[{\"basePrice\":\"420\",\"bigImage\":\"group1/M00/00/04/Ch48Dlm7QYiAC07-AABXl7dfNRU211.jpg\",\"centerImage\":\"\",\"smallImage\":\"\",\"productId\":\"17\",\"productName\":\"真皮书房椅会议室椅职员经理椅实木\",\"quantity\":\"1\",\"unitPrice\":\"476\"}],\"shoppingId\":\"1\"}]";
//		String str2 = "[{\"unitPrice\":\"5.0\",\"reason\":\"不想买了\",\"quantity\":\"1\",\"customerId\":\"5\",\"storeName\":\"睿尚城\"}]";
//		Object obj= JSON.parse(str2);
//		JSONArray parseArray2 = JSON.parseArray(str2);
		System.out.println(str1);
		JSON.parseArray(str1);
		JSON.parseArray(str3);
		System.out.println("转换完成");
	}
	
	@Test
	public void test10(){
		
		List<Bill> list = new ArrayList<Bill>();
		Bill bill1 = new Bill();
		bill1.setContent("abc");
		Bill bill2 = new Bill();
		bill2.setContent("abe");
		Bill bill3 = new Bill();
		bill3.setContent("abc");
		list.add(bill1);
		list.add(bill2);
		list.add(bill3);
		
		List<Bill> list1 = new ArrayList<Bill>();
		List<String> list2 = new ArrayList<String>();
		
		for (Bill bill : list) {
			String str = bill.getContent();
			if(!list2.contains(str)){
				list2.add(str);
				list1.add(bill);
			}
		}
		System.out.println(list1.size());
		System.out.println(list2);
	}
	
}
