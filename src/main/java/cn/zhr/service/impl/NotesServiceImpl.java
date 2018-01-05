package cn.zhr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zhr.dao.NotesDao;
import cn.zhr.entity.Notes;
import cn.zhr.exception.ServiceException;
import cn.zhr.service.NotesService;

@Service("notesService")
public class NotesServiceImpl implements NotesService {

	@Resource
	private NotesDao notesDao;

	@Override
	public int deleteByPrimaryKey(Integer id) throws ServiceException {
		return notesDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Notes record) throws ServiceException {
		return notesDao.insert(record);
	}

	@Override
	public Notes selectByPrimaryKey(Integer id) throws ServiceException {
		return notesDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Notes record) throws ServiceException {
		return notesDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Notes> listByParam(Map<String, Object> paramMap) throws ServiceException {
		return notesDao.listByParam(paramMap);
	}

}
