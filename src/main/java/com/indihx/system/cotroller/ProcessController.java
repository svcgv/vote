package com.indihx.system.cotroller;

import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.activiti.IProcessServiceDao;

/***
 * 
 * <p>描 述: 流程部署管理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年5月25日</p>
 * @author 谢追辉
 */
@Controller
@RequestMapping("/process")
public class ProcessController {
	
	@Resource
	private IProcessServiceDao activitiService;
	
	/***
	 * 流程部署页
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/leave/processDeploy");
		return view;
	}
	
	/***
	 * 部署流程
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/deployProcess",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> deployProcess(@RequestBody Map<String, Object> map){
		if(!map.containsKey("fileName")){
			map.put("msg", "文件对象fileName不存在！");
		}else if(map.get("fileName") == null || map.get("fileName") == ""){
			map.put("msg", "文件名为空，请检查！");
		}else{
			String fileName = map.get("fileName").toString();
			String processName= "流程部署"+fileName;
			if(map.get("processName") != null && map.get("processName") != ""){
				processName = map.get("processName").toString();
			}
			// 加载配置文件activiti.cfg.xml，创建引擎，如果出现null，可能原因
	        //1.加载路径不是根目录。
	        //2.依赖包不完全
	        // 获取配置文件后，引擎开始创建数据库。
	        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
	        // 获取流程储存服务组件
	        RepositoryService rs = engine.getRepositoryService();
	        // 部署流程，只要是符合BPMN2规范的XML文件，理论上都可以被ACTIVITI部署
	        try {
	        	rs.createDeployment().name(processName).addClasspathResource("diagrams/"+fileName+".bpmn")
	             .addClasspathResource("diagrams/"+fileName+".png").deploy();
	        	activitiService.saveActRoleNode("diagrams/"+fileName+".bpmn");
	        	map.put("msg", "流程部署成功！");
			} catch (Exception e) {
				e.printStackTrace();
				 map.put("msg", "流程部署失败！");
			}
		}
		return map;	
		
	}
	
}
