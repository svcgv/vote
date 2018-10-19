package com.indihx.activiti.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.activiti.ActivitiBusiness;
import com.indihx.activiti.ActivitiProcessEnum;
import com.indihx.activiti.ActivitiService;
import com.indihx.activiti.dao.ApplicationIedaMapper;
import com.indihx.activiti.dao.ApplicationMapper;
import com.indihx.activiti.entity.AppNodeInfo;
import com.indihx.activiti.entity.Application;
import com.indihx.activiti.entity.ApplicationIeda;
import com.indihx.activiti.service.IApplicationBiz;
import com.indihx.activiti.vo.ApplicationVo;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.SpringContextBean;


@Service
public class ApplicationBizImpl implements IApplicationBiz {

	@Resource
	private ApplicationMapper appMapper;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private ApplicationIedaMapper appIdeaMapper;

	@Override
	public Map<String, Object> queryToDo(ApplicationVo vo) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(vo.getPages(), vo.getRows(), true); // 分页
		List<Application> appList = appMapper.queryToDo(vo); // 查询代办
		PageInfo<Application> pageInfo = new PageInfo<Application>(appList); // 得到分页参数
		map.put("appList", appList);
		map.put("pageInfo", pageInfo);
		return map;
	}
	
	@Override
	public Application nextCommit(Map<String, Object> reqMap) {
		//String appId = ObjectUtil.toString(reqMap.get("appId"));
		return null;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Application nextCommit(String appId, String nodeId, String userId, String memo,String taskId,String nextOrgId) throws Exception {
		Application app = appMapper.selectByPrimaryKey(ObjectUtil.toLong(appId)); // 得到当前流程信息
		if (StringUtils.isEmpty(taskId)) {
			List<org.activiti.engine.task.Task> tasks = activitiService.getExcuteTask(app);
			//如果taskId为传值，取第一条数据处理
			taskId = tasks.get(0).getId();
		}
		app.setTaskId(taskId);  //把任务ID放入Application中
		boolean isMulti = activitiService.selectNodeIsMulti(app, nodeId);
		ActivitiBusiness business = getBusinessBean(app);
		Map<String, Object> variables=activitiService.getVariables(app); //流程变量中的值
		if(!StringUtils.isEmpty(nextOrgId)){
			if (variables.containsKey("next_org_id")) {
				variables.remove("next_org_id");
			}
			variables.put("next_org_id", nextOrgId);
		}
		Map<String, Object> map = business.handCommitFirst(app,variables); //提交之前调用业务处理类，取需要放到流程变量的值
		if (map == null) 
			map = new HashMap<>();
		Application appNew = new Application();
		if (isMulti) { //当下一个提交节点是会签节点时
			activitiService.saveProcessComment(app, memo,taskId,"03"); // 保存流程意见
			String[] _userId = userId.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
			List<String> userIdList = new ArrayList<String>();
			for (String str : _userId) {
				userIdList.add(str);
			}
			map.put("userIdList", userIdList);
			appNew = activitiService.processNext(app, map,userIdList);// 流程提交下一步
		}else{
			activitiService.saveProcessComment(app, memo,taskId,"01"); // 保存流程意见
			appNew = activitiService.processNext(app, userId, map);// 流程提交下一步
		}
		if (appNew != null) {
			if ("03".equals(app.getCurrStatus())) { // 流程结束
				business.processEnd(appNew);
			} else {
				business.handCopy(app);
			}
		}
		return appNew;
	}

	/***
	 * 得到当前流程的处理类
	 * 
	 * @param app
	 *            实体信息
	 * @return 处理类
	 */
	public ActivitiBusiness getBusinessBean(Application app) {
		if (app.getAppType() != null && StringUtils.isNotEmpty(app.getAppType())) {
			ActivitiProcessEnum activitEnum = ActivitiProcessEnum.getBeanIdByAppType(app.getAppType());
			if (activitEnum == null) {
				throw new BusinessException("当前流程类型"+app.getAppType() +"没有在枚举[ActivitiProcessEnum]中配置，请检查！");
			}
			try {
				ActivitiBusiness business = (ActivitiBusiness) SpringContextBean.getBean(activitEnum.getBeanId());
				if (business == null) {
					throw new BusinessException("当前流程类型"+app.getAppType() +"对应的实现类是否已配置spring注入"+activitEnum.getBeanId());
				}
				return business;
			} catch (Exception e) {
				throw new BusinessException("流程引擎错误："+e.getMessage());
			}
		} else {
			throw new BusinessException("流程类型AppType为空，无法找到对应的流程处理类");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Application rejectTask(String appId, String memo, String taskId) throws Exception {
		Application app = appMapper.selectByPrimaryKey(ObjectUtil.toLong(appId)); // 得到当前流程信息
		app.setTaskId(taskId);
		activitiService.saveProcessComment(app, memo, taskId, "02"); // 保存流程意见
		AppNodeInfo nodeInfo = null;
		// 查询上一个驳回节点的信息
		List<AppNodeInfo> nodeInfos = activitiService.getFrontNode(appId);
		ActivitiBusiness business = getBusinessBean(app);
		if (nodeInfos != null && nodeInfos.size() == 1) { // 当上一个节点只有一个的时候，直接驳回
			nodeInfo = nodeInfos.get(0);
		} else if (nodeInfos != null && nodeInfos.size() > 1) {
			nodeInfo = business.rejectNodeId(app, nodeInfos);
		} else {
			throw new RuntimeException("未找到当前的节点的上一个节点，流程图异常！");
		}
		Application appNew = activitiService.rejectTask(app, nodeInfo); // 退回至提交节点
		if (appNew != null) {
			business.rejectProcess(appNew);
		}
		return appNew;
	}

	@Override
	public List<ApplicationIeda> selectProcessTrack(ApplicationVo vo) {
		return appIdeaMapper.selectProcessTrack(vo.getAppId());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Map<String, Object> queryTaskInfo(ApplicationVo vo,UsrInfo usrInfo) throws Exception {
		Application app = appMapper.selectByPrimaryKey(vo.getAppId());
		//如果当前用户的ID为空，则默认当前点击办理的用户为当前处理人
		if (StringUtils.isEmpty(app.getCurrUserId()) && !"05".equals(app.getCurrStatus())) { 
			app = activitiService.receiveTask(app, usrInfo.getUsrIdStr()); //任务 领取
		}
		app.setTaskId(vo.getTaskId()); //把taskId 放到集合 中
		ActivitiBusiness business = getBusinessBean(app); // 得到流程处理类对象
		Map<String, Object> map = business.queryToDoInfo(app);
		if (map == null) {
			throw new RuntimeException("当前流程未配置代办打开的具体页面，请检查Service层中的queryToDoInfo方法是否已返回！");
		}
		map.put("app", app);
		return map;
	}

	@Override
	public Map<String, Object> queryToDoComp(ApplicationVo vo) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(vo.getPages(), vo.getRows(), true); // 分页
		List<Application> appList = appMapper.queryCompToDo(vo);
		PageInfo<Application> pageInfo = new PageInfo<Application>(appList);
		map.put("pageInfo", pageInfo);
		map.put("appList", appList);
		return map;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Application nextCommit(String appId, String nodeId, String memo) throws Exception {
		Application app = appMapper.selectByPrimaryKey(ObjectUtil.toLong(appId)); // 得到当前流程信息
		activitiService.saveProcessComment(app, memo,null,"01"); // 保存流程意见
		Application appNew = activitiService.processNext(app, null);// 流程提交下一步
		if (appNew != null) {
			ActivitiBusiness business = getBusinessBean(app);
			if ("03".equals(app.getCurrStatus())) { // 流程结束
				business.processEnd(appNew);
			} else {
				business.handCopy(app);
			}
		}
		return appNew;
	}

	@Override
	public Application getApplicationById(String appId) {
		return  appMapper.selectByPrimaryKey(ObjectUtil.toLong(appId));
	}

	

}

