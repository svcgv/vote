
package com.indihx.system.cotroller;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.indihx.doorls.service.impl.ProjectServiceImpl;



public class FirstController {
	
	/*@Resource
	private ProjectServiceImpl doorls;
	
	 @RequestMapping(value = "/drools")
	    @ResponseBody
	  public String testdrools(){
		 doorls.runProjectRule();
		 return null;
	 }*/

    @RequestMapping(value = "/test1")
    @ResponseBody
    public String test(){
        // 加载配置文件activiti.cfg.xml，创建引擎，如果出现null，可能原因
        //1.加载路径不是根目录。
        //2.依赖包不完全
        // 获取配置文件后，引擎开始创建数据库。
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 获取流程储存服务组件
        RepositoryService rs = engine.getRepositoryService();
        // 获取运行时服务组件
        RuntimeService rse = engine.getRuntimeService();
        // 获取流程中的任务TASK组件
        TaskService ts = engine.getTaskService();
        // 部署流程，只要是符合BPMN2规范的XML文件，理论上都可以被ACTIVITI部署
        rs.createDeployment().addClasspathResource("com/pzr/demo2/diagrams/MyProcess.bpmn").deploy();
        // 开启流程，myprocess是流程的ID
        rse.startProcessInstanceByKey("myProcess");
        // 查询历史表中的Task
        List<Task> task = ts.createTaskQuery().list();
        Task task1 = task.get(task.size()-1);
        System.out.println("第一环节："+task1);
        System.out.println("推动流程到下一环节："+task1);
        ts.complete(task1.getId());
        task1 = ts.createTaskQuery().executionId(task1.getExecutionId()).singleResult();
        System.out.println("第二环节：" + task1);
        return "测试成功";
    }

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/test2")
    @ResponseBody
    public String test2(){
        StringBuffer sb = new StringBuffer();
        // 部署流程，只要是符合BPMN2规范的XML文件，理论上都可以被ACTIVITI部署
        repositoryService.createDeployment().addClasspathResource("diagrams/MyProcess.bpmn").deploy();
        // 开启流程，myprocess是流程的ID
        runtimeService.startProcessInstanceByKey("myProcess");
        // 查询历史表中的Task
        List<Task> task = taskService.createTaskQuery().list();
        Task task1 = task.get(task.size()-1);
        sb.append("第一环节："+task1 +"<br/>");
        sb.append("推动流程到下一环节："+task1+"<br/>");
        taskService.complete(task1.getId());
        task1 = taskService.createTaskQuery().executionId(task1.getExecutionId()).singleResult();
        sb.append("第二环节：" + task1+"<br/>");
        return sb.toString();
    }
}
