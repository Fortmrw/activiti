package cn.zhr.dao;

import java.util.List;
import java.util.Map;

import cn.zhr.entity.OmOrder;
import cn.zhr.exception.ServiceException;

public interface OrderDao {
	/**
	 * 根据参数 获取所有记录
	 * 
	 * @param paramMap
	 * @return
	 */
	List<OmOrder> listByParam(Map<String, Object> paramMap) throws ServiceException;

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
	int insert(OmOrder record) throws ServiceException;

	/**
	 * 根据主键获取一条数据库记录
	 * 
	 * @param id
	 * @return OmOrder
	 */
	OmOrder selectByPrimaryKey(Integer id) throws ServiceException;

	/**
	 * 根据主键来更新部分数据库记录
	 * 
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(OmOrder record) throws ServiceException;

	/**
	 * 根据orderNo获取一条数据库记录
	 * 
	 * @param orderNo
	 * @return OmOrder
	 */
	OmOrder selectByOrderNo(String orderNo) throws ServiceException;
	
	/**
	 * 获取 订单当前最大 订单号
	 * @return
	 */
	String findMaxOrderNo() throws ServiceException;

}