package com.indihx.activiti;

import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import com.indihx.activiti.entity.AppNodeInfo;
import com.indihx.activiti.entity.Application;
import com.indihx.system.entity.UsrInfo;

/***
 * 
 * <p>描 述: 工作流接口</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年4月6日</p>
 * @author 谢追辉
 */
public  interface IProcessServiceDao {

	/***
	 * 启动流程
	 * @param usrInfo  用户信息
	 * @param variables  跟随这个流程的一个Map对象(相当于session),当流程结束map失效,在任意节点都可以存值和取值
	 * @param buindess_key   业务主键
	 * @param processName 流程图ID
	 * @return 流程信息
	 */
	Application startProcess(UsrInfo usrInfo,Map<String, Object> variables,String buindess_key,String processName);
	
	/***
	 * 启动流程，设置第一个节点的处理人
	  * @param usrInfo  用户信息
	 * @param variables  跟随这个流程的一个Map对象(相当于session),当流程结束map失效,在任意节点都可以存值和取值
	 * @param buindess_key   业务主键
	 * @param processName 流程图ID
	 * @param usrId  第一个节点的处理人
	 * @return 流程信息
	 */
	Application startProcess(UsrInfo usrInfo,Map<String, Object> variables,String buindess_key,String processName,String usrId);
	
	/***
	 * 根据业务ID得到流程的map对象数据
	 * @param buisinessKey  业务ID
	 * @return  返回流程的Map对象
	 */
	Map<String, Object> getVariables(Application app);
	
	/***
	 * 保存审核意见至工作流中
	 * @param buisinessKey  业务ID
	 * @param comment 审核意见
	 * @param taskId 任务ID
	 * @param taskType 任务 类型
	 * @return  返回流程ID
	 */
	void saveProcessComment(Application app,String comment,String taskId,String taskType);
	
	/***
	 * 判断当前节点是否为会签节点
	 * @param app
	 * @param nodeId
	 * @return
	 */
	boolean selectNodeIsMulti(Application app,String nodeId);
	
	/***
	 * 流程提交下一步
	 * @param app  流程对象
	 * @param variables  流程的Map对象，如果需要往该对象中存值，则先通过getVariables方法得到该对象
	 * 					再传入，如果不需要存值，则传null
	 * @param comment 审核意见
	 * @return  返回最新流程对象
	 */
	Application processNext(Application app,Map<String, Object> variables);
	
	/***
	 * 流程提交下一步会签节点
	 * @param app  流程对象
	 * @param variables  流程的Map对象，如果需要往该对象中存值，则先通过getVariables方法得到该对象
	 * 					再传入，如果不需要存值，则传null
	 * @param userIdList 会签的用户ID集合
	 * @return
	 */
	Application processNext(Application app,Map<String, Object> variables,List<String> userIdList);
	
	/***
	 * 指定任务人提交下一步
	 * @param app  流程对象
	 * @param variables  流程的Map对象，如果需要往该对象中存值，则先通过getVariables方法得到该对象
	 * 					再传入，如果不需要存值，则传null
	 * @param usrId  指定的用户ID
	 * @return 返回最新流程对象
	 */
	Application processNext(Application app,String usrId,Map<String, Object> variables);
	
	/***
	 * 任务领取
	 * @param buisinessKey  业务ID
	 * @param usrInfo  用户信息
	 * @return 返回taskId
	 */
	Application receiveTask(Application app,String usrId);
	
	/***
	 * 驳回任务，默认驳回至上一个节点，谁提交，就退回至谁那里
	 * @param app 流程总成
	 * @param nodeInfo 驳回的具体节点
	 * @return 流程总成
	 */
	Application rejectTask(Application app,AppNodeInfo nodeInfo);
	
	
	/***
	 * 注销正在运行的流程
	 * @param taskId 任务ID
	 * @return 成功or失败
	 */
	String cancelProcess(String taskId);
	
	/***
	 * 根据当前任务id查询意见列表信息
	 * @param taskId  任务ID
	 * @return   返回一个任务List
	 */
	List<Comment> taskComments(String taskId);
	
	/***
	 * 根据流程ID得到下一个节点的信息
	 * @param appId 流程ID
	 * @return 节点集合
	 */
	List<AppNodeInfo> getNextNode(String appId);
	
	/***
	 * 查询当前节点的上一个节点信息
	 * @param appId 流程ID
	 * @return 节点集合
	 */
	List<AppNodeInfo> getFrontNode(String appId);
	
	/***
	 * 根据当前节点查询用户信息 
	 * @param nodeId 节点ID
	 * @return 用户集合
	 */
	List<UsrInfo> getUserByNode(String nodeId,String appId,String nextOrgId,UsrInfo usrInfo);
	
	/***
	 * 根据当前节点查询用户信息(未发起的流程)
	 * @param nodeId 节点ID
	 * @param appId 流程ID
	 * @param usrInfo 当前用户信息
	 * @return 用户集合
	 */
	List<UsrInfo> getUserByNode(String nodeId, UsrInfo usrInfo,String appType);
	
	
	/***
	 * 流程部署成功后把节点信息保存到ACT_ROLE_NODE表中
	 * @param ment 流程静态信息
	 */
	void saveActRoleNode(String fileName);
	
	List<Task> getExcuteTask(Application app);
}
