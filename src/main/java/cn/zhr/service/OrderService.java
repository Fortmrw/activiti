package cn.zhr.service;

import java.util.List;
import java.util.Map;

import cn.zhr.entity.OmOrder;
import cn.zhr.entity.OrderProcess;
import cn.zhr.exception.ServiceException;

public interface OrderService {

	/**
	 * 根据参数获取所有记录
	 * 
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
	int insert(OmOrder user) throws ServiceException;
	
	
	int saveProcessOrder(OmOrder user)throws ServiceException;

	/**
	 * 根据主键获取一条数据库记录
	 * 
	 * @param id
	 * @return Notes
	 */
	OmOrder selectByPrimaryKey(Integer id) throws ServiceException;

	/**
	 * 根据主键来更新部分数据库记录
	 * 
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(OmOrder order) throws ServiceException;

	/**
	 * 根据orderNo获取order对象
	 * 
	 * @param orderNo
	 * @return
	 */
	OmOrder selectByOrderNo(String orderNo) throws ServiceException;
	
	/**
	 * 获取 订单当前最大 编号
	 * @return
	 */
	String findMaxOrderNo() throws ServiceException;
	
	/**
	 * 查询当前用户的任务列表
	 * @param username
	 * @return
	 * @throws ServiceException
	 */
	List<OrderProcess> findOrderTaskList(String username)throws ServiceException;
	
	/**
	 * 提交工单
	 * @param userName
	 * @param taskId
	 * @throws ServiceException
	 */
	void submitOrderProcess(String userName,String taskId)throws ServiceException;
}
