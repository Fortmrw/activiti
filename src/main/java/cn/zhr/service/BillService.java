package cn.zhr.service;

import java.util.List;
import java.util.Map;

import cn.zhr.entity.Bill;
import cn.zhr.exception.ServiceException;

public interface BillService {
	/**
	 * 根据参数获取所有记录
	 * 
	 * @return
	 */
	List<Bill> listByParam(Map<String, Object> paramMap) throws ServiceException;

	/**
	 * 根据主键删除数据库的记录
	 * 
	 * @param id
	 * @return int
	 */
	int deleteByPrimaryKey(Integer id) throws ServiceException;

	/**
	 * 插入数据库记录
	 * 
	 * @param record
	 * @return int
	 */
	int insert(Bill record) throws ServiceException;

	/**
	 * 根据主键获取一条数据库记录
	 * 
	 * @param id
	 * @return Bill
	 */
	Bill selectByPrimaryKey(Integer id) throws ServiceException;

	/**
	 * 根据主键来更新部分数据库记录
	 * 
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(Bill record) throws ServiceException;
	
	/**
	 * 根据参数查询首页数据
	 * @param paramMap
	 * @return
	 */
	Map<String,String> selectCostByParam(Map<String, Object> paramMap) throws ServiceException;
	
	/**
	 * 获取 账单当前最大 编号
	 * @return
	 */
	String findMaxBillNo() throws ServiceException;
}
