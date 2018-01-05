package cn.zhr.dao;

import java.util.List;
import java.util.Map;

import cn.zhr.entity.User;
import cn.zhr.exception.ServiceException;

public interface UserDao {
	/**
	 * 根据参数 获取所有记录
	 * 
	 * @param paramMap
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
	int insert(User record) throws ServiceException;

	/**
	 * 根据主键获取一条数据库记录
	 * 
	 * @param id
	 * @return User
	 */
	User selectByPrimaryKey(Integer id) throws ServiceException;

	/**
	 * 根据主键来更新部分数据库记录
	 * 
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(User record) throws ServiceException;

	/**
	 * 根据name获取一条数据库记录
	 * 
	 * @param id
	 * @return User
	 */
	User selectByName(String name) throws ServiceException;

}