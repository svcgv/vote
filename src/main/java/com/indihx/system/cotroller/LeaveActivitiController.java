package com.indihx.system.cotroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.activiti.entity.Application;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.entity.leave.VolCopy;
import com.indihx.system.service.impl.LeaveInfoServiceImpl;
import com.indihx.system.vo.LeaveInfoVo;
/***
 * 
 * <p>描 述: 请假流程管理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年3月29日</p>
 * @author 谢追辉
 */
@Controller
@RequestMapping(value = "/activiti")
public class LeaveActivitiController extends AbstractBaseController{
	
	@Autowired
	private LeaveInfoServiceImpl infoService;
	
	@RequestMapping(value="/qryLeaveList",method=RequestMethod.GET)  
	public ModelAndView qryLeaveList(HttpSession session){
		Map<String, Object> map = infoService.qryWait(getUser(session),new LeaveInfoVo());
		return returnModel(map, "/leave/qryProcessList");
	}
	
	/***
	 * 流程部署
	 * @return
	 */
	@RequestMapping(value="/deployProcess",method=RequestMethod.POST)  
	public @ResponseBody Map<String, Object> deployProcess(){
		// 加载配置文件activiti.cfg.xml，创建引擎，如果出现null，可能原因
        //1.加载路径不是根目录。
        //2.依赖包不完全
        // 获取配置文件后，引擎开始创建数据库。
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 获取流程储存服务组件
        RepositoryService rs = engine.getRepositoryService();
        // 部署流程，只要是符合BPMN2规范的XML文件，理论上都可以被ACTIVITI部署
        rs.createDeployment().name("流程部署").addClasspathResource("diagrams/StaffLeaveProcess.bpmn")
        .addClasspathResource("diagrams/StaffLeaveProcess.png").deploy();
		return null;	
	}
	
	/***
	 * 新增
	 * @param leaveVo
	 * @return
	 */
	@RequestMapping(value="/saveLeaveInfo",method=RequestMethod.POST)  
	public @ResponseBody Map<String, Object> saveLeaveInfo(@RequestBody LeaveInfoVo leaveVo,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		UsrInfo usrInfo = getUser(session);
		VolCopy volCopy = infoService.addLeave(leaveVo,usrInfo);
		map.put("volCopy", volCopy);
		return map;
	}
	
	
	
	/***
	 * 发起提交流程到下一步
	 * @param leaveInfoVo
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/startProcess",method=RequestMethod.POST)  
	public @ResponseBody  Map<String, Object> startProcess(@RequestBody LeaveInfoVo leaveInfoVo,HttpSession session){
		UsrInfo usrInfo = getUser(session);
		Application app = infoService.startVol(usrInfo, leaveInfoVo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("app", app);
		return map;
	} 
	
	
	/**
	 * 审核
	 * @param leaveInfoVo
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/reviewNext",method=RequestMethod.POST)  
	public @ResponseBody boolean reviewNext(@RequestBody LeaveInfoVo leaveInfoVo,HttpSession session){
		boolean sta = infoService.rejectProcess(getUser(session), leaveInfoVo);
		return sta;
	}
	
	/***
	 * 跳转请假审核界面
	 * @param app 流程数据
	 * @return 请假页面
	 */
	@RequestMapping(value="/handlLeaveProcess")  
	public ModelAndView handlLeaveProcess(Application app){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", app.getAppId());
		VolCopy volCopy = infoService.selectByLeaveCopy(map);
		map.put("vol", volCopy);
		return returnModel(map, "/leave/handlLeaveProcess");
	}
}
