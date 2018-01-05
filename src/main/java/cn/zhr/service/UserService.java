package cn.zhr.service;

import java.util.List;
import java.util.Map;

import cn.zhr.entity.User;
import cn.zhr.exception.ServiceException;

public interface UserService {

	/**
	 * 根据参数获取所有记录
	 * 
	 * @return
	 */
	List<User> listByParam(Map<String, Object> paramMap) throws ServiceException;

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
	int insert(User user) throws ServiceException;

	/**
	 * 根据主键获取一条数据库记录
	 * 
	 * @param id
	 * @return Notes
	 */
	User selectByPrimaryKey(Integer id) throws ServiceException;

	/**
	 * 根据主键来更新部分数据库记录
	 * 
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(User user) throws ServiceException;

	/**
	 * 根据name获取user对象
	 * 
	 * @param name
	 * @return
	 */
	User selectByName(String name) throws ServiceException;
}
