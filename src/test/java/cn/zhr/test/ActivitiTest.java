package cn.zhr.test;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zhr.base.BaseTest;

public class ActivitiTest extends BaseTest {
	@Autowired
	ProcessEngineConfiguration configuration;
//	@Autowired
	ProcessEngine engine;
	private String processDefinitionKey = "myProcess";
	
	@Test
	//处理一个任务，到这里我们的zhangsan获取到这个任务后，需要处理完它
	public void completeProcessInstance(){
		//完成一个流程
		//任务的Id 后期整合后会通过当前登录人身份查询到该用户的任务，然后获取到该id
//		String taskId = "12505";
		String taskId = "15002";
		TaskService taskService = engine.getTaskService();
		//根据任务Id 完成该任务
		taskService.complete(taskId);
	}
	
	@Test
	//我们发现上面打印的流程定义的id是我们最后一次上传的流程定义，所以我们得出结论，
	/**
	 * 多次上传相同id的流程定义，根据流程定义启动流程实例，会取版本最新的流程定义
	 */
	public void queryProcessInstance(){
		//查询当前处理人的任务，我们之前在流程定义的时候写死第一个节点（也就是创建采购单这一步的处理人是zhangsan）
		//获取 查询任务使用的service
		TaskService taskService = engine.getTaskService();
		//获取任务查询对象
		TaskQuery createTaskQuery = taskService.createTaskQuery();
//		createTaskQuery.taskAssignee("zhangsan");
		createTaskQuery.taskAssignee("lisi");
		//查询该条件下的所有任务
		List<Task> list = createTaskQuery.list();
		int index=0;
		for (Task task : list) {
			System.out.println("=========="+index);
			System.out.println("当前任务ID : "+task.getId());
			System.out.println("当前任务所属流程定义ID : "+task.getProcessDefinitionId());
			System.out.println("当前任务的key : "+task.getTaskDefinitionKey());
		}
		//我们通过zhangsan查询到当前任务的id和所属流程定义id，
		//如果填写的不是zhangsan而是别的就会查询不到，因为我们指定的任务处理人是zhangsan
	}
	
	@Test
	//上面的结果输出了三条流程定义，并且部署的id不同和流程定义的id都不相同，带着这个疑问，我们在下面启动一个流程实例并详细讲解下
	public void startProcessInstance(){
		//启动一个流程实例
		RuntimeService runtimeService = engine.getRuntimeService();

		//根据流程定义的Key启动一个流程实例
		ProcessInstance startProcessInstanceByKey = runtimeService.startProcessInstanceByKey(processDefinitionKey);
		System.out.println("流程实例ID : "+startProcessInstanceByKey.getId());
		System.out.println("流程定义ID : "+startProcessInstanceByKey.getProcessDefinitionId());
	}
	@Test
	public void queryProcessDefinition(){
		//在启动一个流程实例之前我们再重复上传上面的流程定义，接着我们根据流程定义的id（purchasingflow）来查询这个流程定义看看会发生什么情况
		//查看流程定义
		//资源服务service
		RepositoryService repositoryService = engine.getRepositoryService();
		//创建流程定义查询对象
		ProcessDefinitionQuery definitionQuery  = repositoryService.createProcessDefinitionQuery();
		//设置流程定义的Key 的查询条件
		definitionQuery.processDefinitionKey(processDefinitionKey);
		//查询所有的流程定义
		List<ProcessDefinition> list = definitionQuery.list();
		int x=0;
		for (ProcessDefinition processDefinition : list) {
			System.out.println("============"+(x++));
			System.out.println("流程ID : "+processDefinition.getId());
			System.out.println("流程资源名 : "+processDefinition.getResourceName());
			System.out.println("流程部署ID : "+processDefinition.getDeploymentId());
		}
	}
	@Test
	public void deployProcessDefinition(){
		//利用activiti的api将上面定义好的工作流bpmn文件和png文件上传到activiti的数据库
		//根据引擎获取资源service
		RepositoryService repositoryService = engine.getRepositoryService();
		//获取部署的文件
		String bpmnName="MyProcess.bpmn";
		String pngName="MyProcess.png";
		InputStream bpmnNameAsStream = this.getClass().getClassLoader().getResourceAsStream(bpmnName);
		InputStream pngNameAsStream = this.getClass().getClassLoader().getResourceAsStream(pngName);
//		BpmnModel bpmnModel = new BpmnModel();
//		Process process = new Process();
//		process.setId("myProcess");
//		bpmnModel.addProcess(process);
		//部署生成的图片
		
		//添加这两个文件进行部署
		DeploymentBuilder createDeployment = repositoryService.createDeployment();
//		Deployment deploy = createDeployment.addBpmnModel(bpmnName, bpmnModel).deploy();
		Deployment deploy = createDeployment
				.addInputStream(bpmnName, bpmnNameAsStream)
				.addInputStream(pngName, pngNameAsStream)
				.deploy();
		
		System.out.println("部署id:"+deploy.getId());
		System.out.println("部署的name:"+deploy.getDeploymentTime());
	}
	
	@Test
	public void test1(){
		System.out.println(configuration);
	}
	@Test
	public void test0(){
		ProcessEngine buildProcessEngine = configuration.buildProcessEngine();
		System.out.println(buildProcessEngine);
	}
}
