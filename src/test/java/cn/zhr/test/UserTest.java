package cn.zhr.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import cn.zhr.base.BaseTest;
import cn.zhr.entity.User;
import cn.zhr.service.UserService;

public class UserTest extends BaseTest {

	@Resource
	private UserService userService;

	@Before
	public void before() {
		System.out.println("========Start========");
	}

	@Test
	public void test1() {
		User selectByPrimaryKey = userService.selectByPrimaryKey(1);
		System.out.println(selectByPrimaryKey);
	}
	@Test
	public void test2() {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("name", "wangwu");
		List<User> listByParam = userService.listByParam(paramMap);
		System.out.println(listByParam);
//		User selectByPrimaryKey = userService.selectByPrimaryKey(1);
//		System.out.println(selectByPrimaryKey);
	}
}
