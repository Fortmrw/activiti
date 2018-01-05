package cn.zhr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhr.dao.OrderDao;
import cn.zhr.entity.OmOrder;
import cn.zhr.entity.OrderProcess;
import cn.zhr.exception.ServiceException;
import cn.zhr.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderDao orderDao;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	@Override
	public int deleteByPrimaryKey(Integer id) throws ServiceException {
		return orderDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OmOrder order) throws ServiceException {
		return orderDao.insert(order);
	}

	@Override
	public OmOrder selectByPrimaryKey(Integer id) throws ServiceException {
		return orderDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OmOrder order) throws ServiceException {
		return orderDao.updateByPrimaryKeySelective(order);
	}

	@Override
	public List<OmOrder> listByParam(Map<String, Object> paramMap) throws ServiceException {
		return orderDao.listByParam(paramMap);
	}

	@Override
	public OmOrder selectByOrderNo(String orderNo) throws ServiceException {
		return orderDao.selectByOrderNo(orderNo);
	}

	@Override
	public String findMaxOrderNo() throws ServiceException {
		return orderDao.findMaxOrderNo();
	}

	@Override
	public int saveProcessOrder(OmOrder order) throws ServiceException {
		// 1.启动流程实例 第一个参数：流程定义的key 第二个参数：BusinessKey
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(order.getType(), order.getOrderNo());
		// 得到流程实例的id
		String processInstanceId = processInstance.getProcessInstanceId();
		order.setProcessInstanceId(processInstanceId);
		// order.setStatus("");
		return orderDao.insert(order);
	}

	@Override
	public List<OrderProcess> findOrderTaskList(String username) throws ServiceException {
		// 得到TaskQuery对象
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 设置条件
		taskQuery.taskAssignee(username);// 设置任务的执行者
		// taskQuery.processDefinitionKey(processDefinitionKey);
		// 执行查询，得到任务列表
		List<Task> list = taskQuery.list();

		List<OrderProcess> orderProcessList = new ArrayList<OrderProcess>();

		// 遍历任务
		for (Task task : list) {
			// 根据任务对象，得到processInstanceId
			String processInstanceId = task.getProcessInstanceId();// 得到流程实例的id
			// 得到流程实例的查询器
			ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
			// 设置条件
			processInstanceQuery.processInstanceId(processInstanceId);// 指定流程实例的id
			// 执行查询：根据流程实例的id,得到流程实例的对象
			ProcessInstance processInstance = processInstanceQuery.singleResult();
			// 得到businessKey
			String businessKey = processInstance.getBusinessKey();// businessKey就是订单的orderNo
			OmOrder order = orderDao.selectByOrderNo(businessKey);
			OrderProcess orderProcess = new OrderProcess();
			BeanUtils.copyProperties(order, orderProcess);
			orderProcess.setTaskId(task.getId());
			orderProcess.setTaskName(task.getName());
			orderProcess.setTaskDefinitionKey(task.getTaskDefinitionKey());
			orderProcessList.add(orderProcess);
		}
		return orderProcessList;
	}

	@Override
	public void submitOrderProcess(String userName, String taskId) throws ServiceException {
		// 得到任务查询器
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 设置条件
		taskQuery.taskId(taskId);
		// 一般taskId唯一 
		taskQuery.taskAssignee(userName);
		// 执行查询 得到当前用户的任务对象
		Task task = taskQuery.singleResult();
		if (task != null) {
			// 完成当前用户的任务
			taskService.complete(taskId);
		}else{
			throw  new ServiceException("查无次 "+taskId+"任务");
		}
	}

}
