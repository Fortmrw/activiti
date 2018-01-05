package cn.zhr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zhr.dao.UserDao;
import cn.zhr.entity.User;
import cn.zhr.exception.ServiceException;
import cn.zhr.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public int deleteByPrimaryKey(Integer id) throws ServiceException {
		return userDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User user) throws ServiceException {
		return userDao.insert(user);
	}

	@Override
	public User selectByPrimaryKey(Integer id) throws ServiceException {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User user) throws ServiceException {
		return userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<User> listByParam(Map<String, Object> paramMap) throws ServiceException {
		return userDao.listByParam(paramMap);
	}

	@Override
	public User selectByName(String name) throws ServiceException {
		return userDao.selectByName(name);
	}

}
