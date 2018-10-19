package com.indihx.activiti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.activiti.ActivitiBusiness;
import com.indihx.activiti.IProcessServiceDao;
import com.indihx.activiti.entity.AppNodeInfo;
import com.indihx.activiti.entity.Application;
import com.indihx.activiti.entity.ApplicationIeda;
import com.indihx.activiti.service.IApplicationBiz;
import com.indihx.activiti.vo.ApplicationVo;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic;

/***
 * 
 * <p>描 述: 工作流程管理类</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月4日</p>
 * @author 谢追辉
 */
@Controller
@RequestMapping("/activiti")
public class ActivitiController extends AbstractBaseController {
	
	@Resource
	private IApplicationBiz applicationBizImpl;
	@Resource
	private IProcessServiceDao activitiService;
	
	/***
	 * 查询代办任务信息
	 * @return 当前用户的代办集合
	 */
	@RequestMapping(value="/queryTaskToDo.do")
	public ModelAndView queryToDo(HttpSession session){
		UsrInfo usrInfo = getUser(session);
		ApplicationVo vo = new ApplicationVo();
		vo.setCurrUserId(usrInfo.getUsrIdStr());
		vo.setCurrOrgId(ObjectUtil.toString(usrInfo.getOrgNo()));
		Map<String, Object> map = applicationBizImpl.queryToDo(vo); //查询当前用户的代办任务
		return returnModel(map, "/activiti/queryTaskTodo");
	}
	
	/**
	 * 分页查询
	 * @param vo 查询条件
	 * @param session 
	 * @return
	 */
	@RequestMapping(value="/ajaxQueryTaskToDo.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQueryToDoPage(@RequestBody ApplicationVo vo,HttpSession session){
		UsrInfo usrInfo = getUser(session);
		vo.setCurrUserId(usrInfo.getUsrIdStr());
		vo.setCurrOrgId(ObjectUtil.toString(usrInfo.getOrgNo()));
		Map<String, Object> map = applicationBizImpl.queryToDo(vo); //查询当前用户的代办任务
		return map;
	}
	
	/***
	 * 返回节点页面
	 * @param vo 条件
	 * @return 页面
	 * @throws Exception
	 */
	@RequestMapping(value="/queryNextNodeByView.do")
	public ModelAndView queryNextNodeByView(ApplicationVo vo) {
		List<AppNodeInfo> listNode = activitiService.getNextNode(ObjectUtil.toString(vo.getAppId()));
		Application app = applicationBizImpl.getApplicationById(vo.getAppId().toString());
		ActivitiBusiness business = applicationBizImpl.getBusinessBean(app);
		business.removeNextNode(app, listNode); //调用业务处理列，是否有不要展示的节点
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listNode", listNode);
		map.put("app", vo);
		return returnModel(map, "/activiti/setProcessUser");
	}
	
	/***
	 * 跳转到当前节点页面
	 * @param vo 实体
	 * @return 成功 or 失败
	 * @throws Exception 
	 */
	@RequestMapping(value="/queryNextNodeByRole.do")
	public ModelAndView queryNextNodeByRole(ApplicationVo vo) throws Exception{
		List<AppNodeInfo> listNode = activitiService.getNextNode(ObjectUtil.toString(vo.getAppId()));
		Application app = applicationBizImpl.getApplicationById(vo.getAppId().toString());
		ActivitiBusiness business = applicationBizImpl.getBusinessBean(app);
		business.removeNextNode(app, listNode); //调用业务处理列，是否有不要展示的节点
		app.setTaskId(vo.getTaskId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listNode", listNode);
		map.put("app", app);
		return returnModel(map, "/activiti/setProcessNode");
	}
	
	/***
	 * 根据选择的节点查询当前节点下的用户信息
	 * @param reqMap 节点
	 * @return 用户信息
	 */
	@RequestMapping(value="/queryUserByNodeId.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> queryUserByNodeId(@RequestBody Map<String, Object> reqMap,HttpSession session){
		String nodeId = ObjectUtil.toString(reqMap.get("nodeId"));
		String appId = ObjectUtil.toString(reqMap.get("appId"));
		String nextOrgId = ObjectUtil.toString(reqMap.get("nextOrgId"));
		UsrInfo usrInfo = getUser(session);
		List<UsrInfo> usrList = activitiService.getUserByNode(nodeId.trim(),appId,nextOrgId,usrInfo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("usrList", usrList);
		return respMap;
	}
	

	/***
	 * 根据选择的节点查询当前节点下的用户信息(未发起流程)
	 * @param reqMap 节点
	 * @return 用户信息
	 */
	@RequestMapping(value="/queryUserByNodeIdNoAppId.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> queryUserByNodeIdNoAppId(@RequestBody Map<String, Object> reqMap,HttpSession session){
		String nodeId = ObjectUtil.toString(reqMap.get("nodeId"));
		String appType = ObjectUtil.toString(reqMap.get("appType"));
		UsrInfo usrInfo = getUser(session);
		List<UsrInfo> usrList = activitiService.getUserByNode(nodeId.trim(),usrInfo,appType);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("usrList", usrList);
		return respMap;
	}
	
	/***
	 * 通用流程提交，所有代办打开的自定义提交页面，提交流程时，全部调用此方法
	 * @param reqMap 请求参数
	 * @return
	 */
	@RequestMapping(value="/currencyCommit.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> currencyCommit(@RequestBody Map<String, Object> reqMap){
		Map<String, Object> respMap = new HashMap<String, Object>();
		Application application = applicationBizImpl.nextCommit(reqMap);
		respMap.put("app", application);
		return respMap;
	}
	
	/***
	 * 提交当前流程,由公共选取用户界面
	 * @param reqMap
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/commitProcess.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> commitProcess(@RequestBody Map<String, Object> reqMap) throws Exception{
		String appId = ObjectUtil.toString(reqMap.get("appId"));
		String nodeId = ObjectUtil.toString(reqMap.get("nodeId"));
		String userId = ObjectUtil.toString(reqMap.get("userId"));
		String memo = ObjectUtil.toString(reqMap.get("memo"));
		String taskId = ObjectUtil.toString(reqMap.get("taskId"));
		String nextOrgId = ObjectUtil.toString(reqMap.get("nextOrgId"));
		Map<String, Object> respMap = new HashMap<String, Object>();
		Application application = applicationBizImpl.nextCommit(appId, nodeId, userId, memo,taskId,nextOrgId);
		respMap.put("app", application);
		return respMap;
	}
	
	/***
	 * 提交到节点，角色
	 * @param reqMap 条件
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/commitProcessByRole.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> commitProcessByRole(@RequestBody Map<String, Object> reqMap) throws Exception{
		String appId = ObjectUtil.toString(reqMap.get("appId"));
		String nodeId = ObjectUtil.toString(reqMap.get("nodeId"));
		String memo = ObjectUtil.toString(reqMap.get("memo"));
		Map<String, Object> respMap = new HashMap<String, Object>();
		Application application = applicationBizImpl.nextCommit(appId, nodeId, memo);
		respMap.put("app", application);
		return respMap;
	}
	
	/***
	 * 驳回当前流程
	 * @param reqMap 请求参数
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/rejectProcess.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> rejectProcess(@RequestBody Map<String, Object> reqMap) throws Exception{
		String appId = ObjectUtil.toString(reqMap.get("appId"));
		String taskId = ObjectUtil.toString(reqMap.get("taskId"));
		String memo = ObjectUtil.toString(reqMap.get("memo"));
		Map<String, Object> respMap = new HashMap<String, Object>();
		Application application = applicationBizImpl.rejectTask(appId, memo,taskId);
		respMap.put("app", application);
		return respMap;
	}
	
	/***
	 * 打开驳回意见页面，工作页面，所有流程驳回都要从这个页面进去
	 * @param vo 实体
	 * @return 
	 */
	@RequestMapping(value="/openRejectMemo.do")
	public ModelAndView openRejectMemo(ApplicationVo vo){
		return returnModel(vo, "/activiti/rejectMemo");
	}
	
	/***
	 * 查询流程跟踪的信息
	 * @param vo 实体
	 * @return
	 */
	@RequestMapping(value="/processTrack.do")
	public ModelAndView processTrack(ApplicationVo vo){
		List<ApplicationIeda> appIdea = applicationBizImpl.selectProcessTrack(vo);
		return returnModel(appIdea, "/activiti/queryProcessTrack");
	}
	
	/***
	 * 打开代办任务页面
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/openToDoTask.do")
	public ModelAndView openToDoTask(ApplicationVo vo,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		Map<String, Object> map = applicationBizImpl.queryTaskInfo(vo,usrInfo);
		String url = ObjectUtil.toString(map.get(ConstantStatic.URL)); //返回的url页面
		return returnModel(map,url);
	}
	
	/***
	 * 查询当前用户的已办任务
	 * @param session
	 * @return 页面
	 */
	@RequestMapping(value="/qeryCompToDo.do")
	public ModelAndView qeryCompToDo(HttpSession session){
		UsrInfo usrInfo = getUser(session);
		ApplicationVo vo = new ApplicationVo();
		vo.setCurrUserId(usrInfo.getUsrIdStr());
		Map<String, Object> map = applicationBizImpl.queryToDoComp(vo);
		return returnModel(map,"/activiti/queryCompTodo");
	}
	
	/**
	 * 分页查询
	 * @param vo 查询条件
	 * @param session 
	 * @return
	 */
	@RequestMapping(value="/ajaxQeryCompToDo.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQueryFinishPage(@RequestBody ApplicationVo vo,HttpSession session){
		UsrInfo usrInfo = getUser(session);
		vo.setCurrUserId(usrInfo.getUsrIdStr());
		vo.setCurrOrgId(ObjectUtil.toString(usrInfo.getOrgNo()));
		Map<String, Object> map = applicationBizImpl.queryToDoComp(vo); //查询当前用户的已办任务
		return map;
	}
}
