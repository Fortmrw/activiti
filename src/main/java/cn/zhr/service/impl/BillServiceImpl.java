package cn.zhr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.zhr.dao.BillDao;
import cn.zhr.entity.Bill;
import cn.zhr.exception.ServiceException;
import cn.zhr.service.BillService;

@Service("billService")
public class BillServiceImpl implements BillService {

	@Resource
	private BillDao billDao;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public int deleteByPrimaryKey(Integer id)  {
		//返回结果
		int result =0;
		try{
			result =  billDao.deleteByPrimaryKey(id);
		}catch(Exception e){
			logger.error("根据Id删除账单异常",e);
			throw ServiceException.DB_DELETE_ERROR;
		}
		return result;
	}

	@Override
	public int insert(Bill record)  {
		//返回结果
		int result =0;
		try{
			result =  billDao.insert(record);
		}catch(Exception e){
			logger.error("创建账单异常",e);
			throw ServiceException.DB_INSERT_ERROR;
		}
		return result;
	}

	@Override
	public Bill selectByPrimaryKey(Integer id)  {
		return billDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Bill record)  {
		return billDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Bill> listByParam(Map<String, Object> paramMap)  {
		return billDao.listByParam(paramMap);
	}

	@Override
	public Map<String, String> selectCostByParam(Map<String, Object> paramMap)  {
		return billDao.selectCostByParam(paramMap);
	}

	@Override
	public String findMaxBillNo()  {
		return billDao.findMaxBillNo();
	}

}
