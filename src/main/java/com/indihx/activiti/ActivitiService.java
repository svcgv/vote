package com.indihx.activiti;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.CommentEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.indihx.activiti.dao.ActRoleNodeMapper;
import com.indihx.activiti.dao.ApplicationIedaMapper;
import com.indihx.activiti.dao.ApplicationMapper;
import com.indihx.activiti.entity.ActRoleNode;
import com.indihx.activiti.entity.AppNodeInfo;
import com.indihx.activiti.entity.Application;
import com.indihx.activiti.entity.ApplicationIeda;
import com.indihx.comm.util.BasicParameterInfo;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.system.dao.OrgInfoMapper;
import com.indihx.system.dao.UsrInfoMapper;
import com.indihx.system.entity.OrgInfo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.EntityVoConverter;

/***
 * 
 * <p>
 * 描 述: 工作流流程处理类
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年4月5日
 * </p>
 * 
 * @author 谢追辉
 */
@Repository
public class ActivitiService implements IProcessServiceDao {

	private static Logger log = LoggerFactory.getLogger(ActivitiService.class);

	@Resource
	private ProcessEngine engine;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private ApplicationMapper appMapper;
	@Resource
	private ActRoleNodeMapper roleNodeMapper;
	@Resource
	private PrimaryKey primaryKey;
	@Resource
	private ApplicationIedaMapper appIedaMapper;
	@Resource
	private ActivitiTransition activitiTransition;
	@Resource
	private OrgInfoMapper orgInfoMapper;
	@Resource
	private UsrInfoMapper usrInfoMapper;

	@Override
	public Application startProcess(UsrInfo usrInfo, Map<String, Object> variables, String buindess_key,
			String processName) {
		variables.put("buindess_key", buindess_key); // 业务ID
		variables.put("applyUsrId", usrInfo.getUsrId().toString()); // 流程发起人
		IdentityService indent = engine.getIdentityService();
		indent.setAuthenticatedUserId(usrInfo.getUsrId().toString()); // 设置流程发起人
		// 启动任务 参数说明：1、流程图ID 2、业务ID放到流程表中 3、跟随整个流程的数据，相当于session，失效时间为流程开始到结束
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processName, buindess_key,
				variables);
		String appName = BasicParameterInfo.getCodeVal("APP_TYPE", processName); // 流程名称
		log.info("--->[" + appName + "]流程启动成功!,流程ID为：[" + processInstance.getId() + "]");
		// 保存流程数据到APPLICATIO中
		Application app = new Application();
		app.setActId(processInstance.getId()); // 工作流ID
		long appId = primaryKey.getPrimaryKeyAddDateLong();
		app.setAppId(appId); // 流程ID
		app.setAppName(appName); // 流程名称
		app.setAppType(processName); // 流程类型
		app.setCreateId(usrInfo.getUsrIdStr()); // 发起人
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(app.getActId())
				.singleResult();
		String activitiId = execution.getActivityId();
		app.setCurrNodeId(activitiId); // 当前节点 员工节点
		Task task = taskService.createTaskQuery().processInstanceId(app.getActId()).singleResult(); // 得到当前任务
		String nodeName = task.getName();
		app.setCurrNodeName(nodeName);
		app.setCurrStatus("01"); // 当前状态 启动
		int num = appMapper.insertSelective(app);
		if (num < 1) {
			throw new RuntimeException("用户" + usrInfo.getUsrName() + "发起请假流程发起失败！！！！APPLICATION保存失败 业务ID");
		}
		// 保存审核意见信息
		saveProcessComment(app, appName+"流程启动", task.getId(),"00");
		return app;
	}

	@Override
	public Application startProcess(UsrInfo usrInfo, Map<String, Object> variables, String buindess_key,
			String processName, String usrId) {
		if (variables == null) {
			variables = new HashMap<String, Object>();
		}
		Application app = startProcess(usrInfo, variables, buindess_key, processName);
		/** ----领取任务start---- */
		Task task = taskService.createTaskQuery().processInstanceId(app.getActId()).singleResult(); // 得到当前任务
		taskService.claim(task.getId(), usrId); // 当前用户领取任务
		log.info("--->用户:[" + usrId + "]领取了任务：[" + task.getId() + "]");
		app.setCurrUserId(usrId);
		// 流程发起，取当前流程发起者的所属机构，后续优化：如果有指定操作人的，则需要改造此处，查询ACT_ROLE_NODE中当前节点的所属机构
		app.setCurrOrgId(ObjectUtil.toString(usrInfo.getOrgNo()));
		app.setCurrRoleId(ObjectUtil.toString(usrInfo.getRoleId()));
		app.setStartDate(DateUtil.getDateTime()); // 开始时间
		app.setTaskId(task.getId());
		app.setNextOrgId(ObjectUtil.toString(variables.get("next_org_id")));//下一个节点机构ID
		int num = appMapper.updateByPrimaryKeySelective(app);
		if (num < 1) {
			throw new RuntimeException("用户" + usrInfo.getUsrName() + "发起请假流程发起失败！！！！APPLICATION保存失败 业务ID");
		}
		return app;
	}

	@Override
	public Map<String, Object> getVariables(Application app) {
		if (app.getTaskId() == null || app.getTaskId() == "") {
			List<Task> task = taskService.createTaskQuery().processInstanceId(app.getActId()).list();
			if (task == null) {
				throw new RuntimeException("--->流程[" + app.getAppId() + "]不存在关联的流程！！请仔细核查！");
			}
			app.setTaskId(task.get(0).getId());
		}
		Map<String, Object> variables = taskService.getVariables(app.getTaskId());
		return variables;
	}

	@Override
	public void saveProcessComment(Application app, String comment, String taskId, String taskType) {
		// 得到当前业务的流程
		Task task = null;
		if (StringUtils.isEmpty(taskId)) {
			task = taskService.createTaskQuery().processInstanceId(app.getActId()).singleResult();
		} else {
			task = taskService.createTaskQuery().taskId(taskId).singleResult();
		}
		if (task == null) {
			throw new RuntimeException("--->流程[" + app.getAppId() + "]不存在关联的流程！！请仔细核查！");
		}
		Authentication.setAuthenticatedUserId(task.getAssignee()); // 指定当前节点用户
		if (StringUtils.isEmpty(comment)) {
			comment = "";
		}
		taskService.deleteComments(task.getId(), task.getProcessInstanceId()); // 先删除，再新增
		taskService.addComment(task.getId(), task.getProcessInstanceId(), CommentEntity.TYPE_COMMENT, comment); // 增加意见
		log.info("--->流程ID[" + task.getId() + "],由" + task.getAssignee() + "保存了审核意见！！");
		ApplicationIeda idea = new ApplicationIeda();
		idea.setAppId(app.getAppId()); // 流程主键
		idea.setIdeaId(primaryKey.getPrimaryKeyLong()); // 主键
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(app.getActId())
				.singleResult();
		String activitiId = execution.getActivityId();
		idea.setNodeId(activitiId); // 节点ID
		idea.setNodeName(app.getCurrNodeName()); // 节点名称
		idea.setTaskId(task.getId()); // 流程任务ID
		idea.setNote(comment);
		idea.setTaskType(taskType);
		appIedaMapper.insertSelective(idea);
	}

	@Override
	public Application processNext(Application app, Map<String, Object> variables) {
		// 得到当前业务的流程
		Task task = null;
		// 当任务ID不为空时，已任务ID去查找，为空则以流程ID查找，如果当前节点是会签节点，则必须以taskId去找，否则会报错
		if (StringUtils.isNotEmpty(app.getTaskId())) {
			task = taskService.createTaskQuery().taskId(app.getTaskId()).singleResult();
		} else {
			task = taskService.createTaskQuery().processInstanceId(app.getActId()).singleResult();
			app.setTaskId(task.getId());
		}
		if (task == null) {
			throw new RuntimeException("--->流程[" + app.getAppId() + "]不存在关联的流程！！请仔细核查！");
		}
		Authentication.setAuthenticatedUserId(task.getAssignee()); // 指定当前节点用户
		if (variables == null) {
			taskService.complete(task.getId()); // 流程流转到下一步
		} else {
			Map<String, Object> map = getVariables(app);
			map.putAll(variables);
			taskService.complete(task.getId(), map); // 流程流转到下一步
		}
		log.info("--->流程ID[" + task.getId() + "],由" + task.getAssignee() + "提交了下一步！！");
		// 查询当前流程运行中的流程节点信息，如果为空，则表示流程已结束
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(app.getActId())
				.singleResult();
		if (execution != null) {
			String activitiId = execution.getActivityId();
			List<Task> taskNews = taskService.createTaskQuery().processInstanceId(app.getActId()).list(); // 查询最新的流程信息
			Task taskNew = taskNews.get(0); // 如果
											// 有多条取第一条任务，取节点的公共信息,多条任务一般只会出现在会签节点处
			// 当APPLICATION中的当前节点和提交之后任务的节点一样，则表示当前处在会签节点，并不具备提交下一步的操作，不要更新Applicaiton的值，直接返回
			if (StringUtils.isEmpty(activitiId) && app.getCurrNodeId().equals(taskNew.getTaskDefinitionKey()))
				return app;
			app.setFrontNodeId(app.getCurrNodeId()); // 上一个节点ID
			app.setFrontNodeName(app.getCurrNodeName()); // 上一个节点名称
			app.setFrontUserId(task.getAssignee()); // 上一个节点用户
			app.setCurrNodeName(taskNew.getName()); // 节点名称
			app.setCurrStatus("02"); // 审核中
			if (StringUtils.isEmpty(activitiId)) { // 当无法从ExecutionEntity得到当前的节点，则表示当前节点为会签节点，则从task中取接口
				activitiId = taskNew.getTaskDefinitionKey();
				app.setCurrStatus("05"); // 会签中
			}
			app.setCurrNodeId(activitiId); // 当前节点
			app.setCurrUserId(""); // 当前处理人
			// 得到当前处理角色
			ActRoleNode roleNode = new ActRoleNode();
			roleNode.setNodeId(activitiId);
			roleNode.setAppType(app.getAppType());
			List<ActRoleNode> actRoleNodes = roleNodeMapper.selectByPrimaryKey(roleNode);
			if (actRoleNodes != null && actRoleNodes.size() > 0) {
				String currRoleId = ObjectUtil.toString(actRoleNodes.get(0).getRoleId());
				app.setCurrRoleId(currRoleId); // 角色ID
				//如果指定了提交审核机构
				if(variables.get("next_org_id")!=null){
					app.setCurrOrgId(variables.get("next_org_id").toString());
				}else{
					UsrInfo usrInfo = usrInfoMapper.selectByPrimaryKey(ObjectUtil.toLong(task.getAssignee()));
					String orgNo = getCurrNodeIdOrgNo(usrInfo, actRoleNodes.get(0));
					app.setCurrOrgId(orgNo);	
				}
			} else {
				throw new RuntimeException("当前节点未配置对应的角色信息或配置了多条，请检查下ACT_ROLE_NODE表的配置！！");
			}
		} else {
			app.setFrontNodeId(app.getCurrNodeId()); // 上一个节点ID
			app.setFrontNodeName(app.getCurrNodeName()); // 上一个节点名称
			app.setFrontUserId(app.getCurrUserId()); // 上一个节点用户
			app.setCurrStatus("03"); // 已结束
			app.setCurrNodeId("END");
			app.setCurrNodeName("已结束");
			app.setCurrUserId(""); // 当前处理人
			app.setEndDate(DateUtil.formatDateTimeFromDB(DateUtil.getSysDate(), DateUtil.getSysTime()));
		}
		// 更新流程总表中的信息
		appMapper.updateByPrimaryKeySelective(app); // 更新流程总
		return app;
	}

	@Override
	public Application processNext(Application app, String usrId, Map<String, Object> variables) {
		Application appNew = processNext(app, variables);
		List<Task> task = taskService.createTaskQuery().processInstanceId(app.getActId()).list();
		// 处理人为空；会签状态下；无任务时。不指定处理人
		if (task != null && !StringUtils.isEmpty(usrId) && !"05".equals(app.getCurrStatus())
				&& !"03".equals(app.getCurrStatus())) {
			receiveTask(appNew, usrId);
		}
		return appNew;
	}

	@Override
	public Application processNext(Application app, Map<String, Object> variables, List<String> userIdList) {
		Application appNew = processNext(app, variables);
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(app.getActId()).list();
		if (tasks.size() != userIdList.size()) {
			throw new RuntimeException("当前会签节点生成的任务个数和处理人不一致，请检查！");
		}
		// 循环把每个任务设置处理人
		for (int i = 0; i < tasks.size(); i++) {
			taskService.setAssignee(tasks.get(i).getId(), userIdList.get(i).trim());
		}
		return appNew;
	}

	@Override
	public Application receiveTask(Application app, String usrId) {
		// 得到当前业务的流程
		Task task = taskService.createTaskQuery().processInstanceId(app.getActId()).singleResult();
		if (task == null) {
			log.info("--->业务ID[" + app.getAppName() + "]不存在关联的流程！！请仔细核查！");
			return null;
		}
		taskService.claim(task.getId(), usrId);
		app.setCurrUserId(usrId);
		appMapper.updateByPrimaryKeySelective(app); // 更新当前处理人字段
		return app;
	}

	/***
	 * 查询历史记录
	 * 
	 * @param proc_inst_id_
	 */
	public void qryHis(String proc_inst_id_) {
		List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(proc_inst_id_).list();
		for (HistoricTaskInstance hi : list) {
			System.out.println(hi.getAssignee() + " " + hi.getName() + " " + hi.getStartTime());
		}
	}

	@Override
	public String cancelProcess(String taskId) {
		TaskInfo taskInfo = taskService.createTaskQuery().taskId(taskId).singleResult();
		runtimeService.deleteProcessInstance(taskInfo.getProcessInstanceId(), "招标人自行注销！");
		log.info("流程ID" + taskInfo.getProcessInstanceId() + "已被发起人成功注销！" + taskInfo.getName());
		return taskInfo.getProcessInstanceId();
	}

	@Override
	public List<Comment> taskComments(String taskId) {
		List<Comment> listComment = taskService.getTaskComments(taskId);
		return listComment;
	}

	@Override
	public List<AppNodeInfo> getNextNode(String appId) {
		return getNode(appId, "01");
	}

	@Override
	public List<AppNodeInfo> getFrontNode(String appId) {
		return getNode(appId, "02");
	}

	/***
	 * 得到当前节点的两端的节点
	 * 
	 * @param appId
	 * @param type
	 *            01表示出去的节点 02 表示进入的节点
	 * @return 节点列表
	 */
	private List<AppNodeInfo> getNode(String appId, String type) {
		List<AppNodeInfo> list = new ArrayList<AppNodeInfo>();
		Application application = appMapper.selectByPrimaryKey(new Long(appId));
		if ("05".equals(application.getCurrStatus())) { // 当处在会签节点的时候，则不需要查询下一个节点，直接提交
			return list;
		}
		// 得到当前的任务信息，如果当前节点为会签节点，则取第一条数据即可，会签节点，节点信息都一样，只是任务信息不一样
		Task task = taskService.createTaskQuery().processInstanceId(application.getActId()).list().get(0); //
		// 然后根据当前任务获取当前流程的流程定义，然后根据流程定义获得所有的节点：
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(task.getProcessDefinitionId());
		List<ActivityImpl> activitiList = def.getActivities(); // rs是指RepositoryService的实例
		// 根据任务获取当前流程执行ID，执行实例以及当前流程节点的ID：
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery()
				.executionId(application.getActId()).singleResult();
		String activitiId = execution.getActivityId();
		for (ActivityImpl activityImpl : activitiList) {
			String id = activityImpl.getId();
			if (activitiId.equals(id)) {
				List<PvmTransition> transitions = null;
				if ("02".equals(type))
					transitions = activityImpl.getIncomingTransitions();
				else if ("01".equals(type)) {
					transitions = activityImpl.getOutgoingTransitions();// 获取从某个节点出来的所有线路
				} else {
					throw new RuntimeException("查询节点类型为空，01查询出去节点，02查询进入的节点！");
				}
				for (PvmTransition tr : transitions) {
					PvmActivity ac = null;
					if ("02".equals(type))
						ac = tr.getSource(); // 取来源
					else
						ac = tr.getDestination(); // 获取线路的终点节点
					AppNodeInfo nodeInfo = new AppNodeInfo();
					nodeInfo.setNodeId(ac.getId());
					nodeInfo.setNodeName(ac.getProperty("name") + "");
					nodeInfo.setMulti(false);
					Object obj = ac.getProperty("multiInstance");
					if (obj != null)
						nodeInfo.setMulti(true);
					list.add(nodeInfo);
				}
				break;
			}
		}
		return list;
	}

	@Override
	public List<UsrInfo> getUserByNode(String nodeId, String appId,String nextOrgId, UsrInfo usrInfo) {
		Application application = appMapper.selectByPrimaryKey(new Long(appId));
		ActRoleNode roleNode = new ActRoleNode();
		roleNode.setAppType(application.getAppType());
		roleNode.setNodeId(nodeId);
		List<ActRoleNode> roleList = roleNodeMapper.selectByPrimaryKey(roleNode);
		if (roleList == null || roleList.size() != 1) {
			if(!nodeId.toUpperCase().equals("FINISH")){
				throw new RuntimeException("当前节点未配置对应的角色信息或配置了多条，请检查下ACT_ROLE_NODE表的配置！！");
			}
		}
		ActRoleNode roleNode2 = roleList.get(0); // 得到当前配置的角色信息
		String orgNo = null;
		if(ObjectUtil.isEmpty(nextOrgId)){
			orgNo = getCurrNodeIdOrgNo(usrInfo, roleNode2);	
		}else{
			orgNo = nextOrgId;
		}
		List<UsrInfo> listUsr = roleNodeMapper.queryUserByNode(nodeId, application.getAppType(), orgNo);
		return listUsr;
	}

	@Override
	public List<UsrInfo> getUserByNode(String nodeId, UsrInfo usrInfo,String appType) {
		ActRoleNode roleNode = new ActRoleNode();
		roleNode.setAppType(appType);
		roleNode.setNodeId(nodeId);
		List<ActRoleNode> roleList = roleNodeMapper.selectByPrimaryKey(roleNode);
		if (roleList == null || roleList.size() != 1) {
			throw new RuntimeException("当前节点未配置对应的角色信息或配置了多条，请检查下ACT_ROLE_NODE表的配置！！");
		}
		ActRoleNode roleNode2 = roleList.get(0); // 得到当前配置的角色信息
		String orgNo = getCurrNodeIdOrgNo(usrInfo, roleNode2);
		List<UsrInfo> listUsr = roleNodeMapper.queryUserByNode(nodeId, appType, orgNo);
		return listUsr;
	}
	
	/***
	 * 得到当前节点的机构ID
	 * 
	 * @param nodeId
	 *            节点
	 * @param usrInfo
	 *            用户信息
	 * @param roleNode2
	 * @return
	 */
	private String getCurrNodeIdOrgNo(UsrInfo usrInfo, ActRoleNode roleNode2) {
		String orgNo = "";
		if (roleNode2.getOrgId() != null) { // 如果当前机构ID不为空，则找当前机构的用户信息
			orgNo = ObjectUtil.toString(roleNode2.getOrgId());
		} else { // 如果当前结构为空，则查找机构orgType
			String orgType = roleNode2.getOrgType();
			if (StringUtils.isEmpty(orgType)) {
				throw new RuntimeException("当前流程为对节点" + roleNode2.getNodeId() + "配置机构信息，请检查下ACT_ROLE_NODE表的配置！！");
			}
			// 01 取当前机构 02 取当前机构的父机构
			if ("01".equals(orgType)) {
				orgNo = ObjectUtil.toString(usrInfo.getOrgNo());
			} else if ("02".equals(orgType)) {
				String sonOrgNo = ObjectUtil.toString(usrInfo.getOrgNo());
				// 查询父机构编号
				OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(ObjectUtil.toLong(sonOrgNo));
				orgNo = ObjectUtil.toString(orgInfo.getParentOrgNo());
			} else {
				throw new RuntimeException("当前流程为对节点" + roleNode2.getNodeId() + "配置配置的ORG_TYPE为" + orgType
						+ ",当前类型无法识别，请检查下ACT_ROLE_NODE表的配置！！");
			}
		}
		return orgNo;
	}

	@Override
	public Application rejectTask(Application app, AppNodeInfo nodeInfo) {
		Application appNew = new Application();
		// 得到当前的任务信息
		Task task = taskService.createTaskQuery().processInstanceId(app.getActId()).singleResult(); // 得到当前任务
		String activityId = nodeInfo.getNodeId(); // 上一个节点
		Map<String, Object> variables = getVariables(app); // 得到流程对象
		try {
			// 驳回到上一节点
			activitiTransition.turnTransition(task.getId(), activityId, variables);
			appNew.setTaskId(task.getId()); // 保存生一个节点的任务ID

			// 更新APPLICATION表中的数据
			EntityVoConverter.Convert(app, appNew);
			// 上一节点信息
			appNew.setFrontNodeId(app.getCurrNodeId());
			appNew.setFrontNodeName(app.getCurrNodeName());
			appNew.setFrontUserId(app.getCurrUserId());

			// 当前节点信息
			appNew.setCurrNodeId(nodeInfo.getNodeId()); // 当前节点
			appNew.setCurrNodeName(nodeInfo.getNodeName()); // 当前节点名称

			List<Map<String, Object>> maps = appMapper.queryUsrInfoByFlowNode(appNew.getActId(), nodeInfo.getNodeId());
			// 暂时认定所有节点的只会有一个处理人，驳回都是驳回给原处理人
			appNew.setCurrUserId(ObjectUtil.toString(maps.get(0).get("USR_ID"))); // 当前用户
			appNew.setCurrOrgId(ObjectUtil.toString(maps.get(0).get("ORG_NO")));
			// 查询最新的任务信息
			task = taskService.createTaskQuery().processInstanceId(app.getActId()).singleResult();
			// 上一节点用户领取任务
			taskService.claim(task.getId(), ObjectUtil.toString(maps.get(0).get("USR_ID")));
			// 查找当前节点的角色
			ActRoleNode roleNode = new ActRoleNode();
			roleNode.setNodeId(nodeInfo.getNodeId());
			roleNode.setAppType(app.getAppType());
			List<ActRoleNode> actRoleNodes = roleNodeMapper.selectByPrimaryKey(roleNode);
			appNew.setCurrRoleId(actRoleNodes.get(0).getRoleId().toString());
			appNew.setCurrStatus("04");
			appMapper.updateByPrimaryKeySelective(appNew);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appNew;
	}

	@Override
	public boolean selectNodeIsMulti(Application app, String nodeId) {
		List<AppNodeInfo> nodeInfos = getNextNode(app.getAppId().toString());
		for (AppNodeInfo appNodeInfo : nodeInfos) {
			if (nodeId.equals(appNodeInfo.getNodeId())) {
				return appNodeInfo.isMulti();
			}
		}
		return false;
	}

	@Override
	public void saveActRoleNode(String fileName) {
		InputStream resouceStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
		XMLInputFactory xif = XMLInputFactory.newInstance();
		InputStreamReader in;
		XMLStreamReader xtr;
		try {
			in = new InputStreamReader(resouceStream, "UTF-8");
			xtr = xif.createXMLStreamReader(in);
			BpmnModel model = new BpmnXMLConverter().convertToBpmnModel(xtr);
			org.activiti.bpmn.model.Process process = model.getMainProcess();
			String appType = process.getId();
			Collection<FlowElement> flowElements = process.getFlowElements();
			ActRoleNode delNode = new ActRoleNode();
			delNode.setAppType(appType);
			roleNodeMapper.deleteByPrimaryKey(delNode);
			for (FlowElement e : flowElements) {

				if (e.getClass().toString().indexOf("UserTask") > -1) {
					ActRoleNode roleNode = new ActRoleNode();
					roleNode.setId(primaryKey.getPrimaryKeyLong());
					roleNode.setNodeId(e.getId());
					roleNode.setAppType(appType);
					roleNode.setNodeName(e.getName());
					roleNodeMapper.insertSelective(roleNode);
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Task> getExcuteTask(Application app) {
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(app.getActId()).list();
		return tasks;
	}

}
