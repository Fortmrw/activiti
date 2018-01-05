package cn.zhr.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
@RequestMapping("/main/activiti")
public class ActivitiController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RepositoryService repositoryService;
//	@Autowired
//	private RuntimeService runtimeService;
//	@Autowired
//	private TaskService taskService;
//	@Autowired
//	private ManagementService managementService;

	/**
	 * 去往模型列表
	 */
	@RequestMapping("/toListModel")
	public ModelAndView tolistModel(ModelAndView mav) {
		logger.info(new Date().toString() + " :  /toListModel 执行");
		mav.setViewName("activiti/listModel");
		logger.info(new Date().toString() + " :  /toListModel 结束");
		return mav;
	}
	/**
	 * 模型列表
	 */
	@ResponseBody
	@RequestMapping("/listModel")
	public String list() {
		List<Model> list = repositoryService.createModelQuery().list();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", list.size());
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("toCreateModel")
	public String toCreate(){
	 return "activiti/addModel";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("/create")
	public void create(String name, String key, String description) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			Model modelData = repositoryService.newModel();

			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			description = StringUtils.defaultString(description);

			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(name);
			modelData.setKey(StringUtils.defaultString(key));
			// 保存模型
			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

			getHttpResponse()
					.sendRedirect(getHttpRequest().getContextPath() + "/activiti/modeler.html?modelId=" + modelData.getId());
		} catch (Exception e) {
			logger.error("创建模型失败：", e);
		}
	}

	/**
	 * 进入到部署的页面
	 */
	@ResponseBody
	@RequestMapping("deploy")
	public String deploy() {
		String modelId = getString("modelId");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String resultMsg = "模型部署流程失败";
		String result = "111";
		try {
			Model modelData = repositoryService.getModel(modelId);
			ObjectNode modelNode = (ObjectNode) new ObjectMapper()
					.readTree(repositoryService.getModelEditorSource(modelData.getId()));
			byte[] bpmnBytes = null;

			BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
			bpmnBytes = new BpmnXMLConverter().convertToXML(model);

			String processName = modelData.getName() + ".bpmn20.xml";
			Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
					.addString(processName, new String(bpmnBytes)).deploy();
			resultMsg = "部署成功，部署ID=" + deployment.getId();
			result = "000";
		} catch (Exception e) {
			logger.error("根据模型部署流程失败：modelId={}", modelId, e);
		}
		resultMap.put("resultMsg", resultMsg);
		resultMap.put("result", result);
		return JSON.toJSONString(resultMap);
	}
	
	/**
	 * 去往查询部署流程列表
	 */
	@RequestMapping("/toListProcess")
	public ModelAndView tolistProcess(ModelAndView mav) {
		logger.info(new Date().toString() + " :  /tolistProcess 执行");
		mav.setViewName("activiti/listProcess");
		logger.info(new Date().toString() + " :  /tolistProcess 结束");
		return mav;
	}

	/**
	 * 查询部署的流程
	 */
	@ResponseBody
	@RequestMapping("/listProcess")
	public String listProcessDefinition() {
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();// 得到流程定义的查询器
		List<ProcessDefinition> list = processDefinitionQuery.list();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		for (ProcessDefinition processDefinition : list) {
			Map<String,Object> tempMap = new HashMap<String,Object>();
			tempMap.put("id", processDefinition.getId());
			tempMap.put("deploymentId", processDefinition.getDeploymentId());
			tempMap.put("name", processDefinition.getName());
			tempMap.put("key", processDefinition.getKey());
			tempMap.put("version", processDefinition.getVersion());
			tempMap.put("description", processDefinition.getDescription());
			resultList.add(tempMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", resultList);
		map.put("total", resultList.size());
		return JSON.toJSONString(map);
	}

	/**
	 * 查看资源文件
	 */

	public void findResource() {
		String processDefinitionId = getString("processDefinitionId");
		String resourceType = getString("resourceType");
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		processDefinitionQuery.processDefinitionId(processDefinitionId);// 设置流程定义的ID
		ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
		InputStream is = null;
		if (processDefinition != null) {
			String deploymentID = processDefinition.getDeploymentId();
			if ("bpmn".equals(resourceType)) {
				is = repositoryService.getResourceAsStream(deploymentID, processDefinition.getResourceName());
			} else if ("png".equals(resourceType)) {
				is = repositoryService.getResourceAsStream(deploymentID, processDefinition.getDiagramResourceName());
			}
		}
		HttpServletResponse response = getHttpResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			OutputStream os = response.getOutputStream();
			IOUtils.copy(is, os);
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * 导出model的xml文件
	 */
	@RequestMapping("export")
	public void export(String modelId, HttpServletResponse response) {
		try {
			Model modelData = repositoryService.getModel(modelId);
			BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
			JsonNode editorNode = new ObjectMapper()
					.readTree(repositoryService.getModelEditorSource(modelData.getId()));
			BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
			BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
			byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

			ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
			IOUtils.copy(in, response.getOutputStream());
			String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			response.flushBuffer();
		} catch (Exception e) {
			logger.error("导出model的xml文件失败：modelId={}", modelId, e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "deleteModle")
	public String deleteModle() {
		String modelId = getString("modelId");
		repositoryService.deleteModel(modelId);
		return "redirect:/main/activiti/toListModel";
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteDeploy")
	public String deleteDeploy() {
		String deploymentId = getString("deploymentId");
		String result = "111";
		String resultMsg= "未知异常";
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//true 代表级联操作
			repositoryService.deleteDeployment(deploymentId,true);
			result = "000";
			resultMsg ="成功";
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg = e.getMessage();
		}
		map.put("result", result);
		map.put("resultMsg", resultMsg);
		return JSON.toJSONString(map);
	}

}
