package cn.zhr.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.zhr.base.BaseTest;
import cn.zhr.entity.Notes;
import cn.zhr.service.BillService;
import cn.zhr.service.NotesService;

public class NotesTest extends BaseTest {
	@Resource
	private NotesService notesService;
	
	@Resource
	private BillService billService;

	@Before
	public void before() {
		System.out.println("========Start========");
	}

	@After
	public void after() {
		System.out.println("========End========");
	}

	@Test
	public void test1() {
		Notes selectByPrimaryKey = notesService.selectByPrimaryKey(1);
		System.out.println(selectByPrimaryKey);
	}
	
	@Test
	public void test3() {
		String maxNo = billService.findMaxBillNo();
		System.out.println(maxNo);
	}

	@Test
	public void test2() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("content", "郭健");
		List<Notes> listByParam = notesService.listByParam(paramMap);
		System.out.println(listByParam);
	}
}
