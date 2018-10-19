package com.indihx.activiti.service;

import java.util.List;
import java.util.Map;

import com.indihx.activiti.ActivitiBusiness;
import com.indihx.activiti.entity.Application;
import com.indihx.activiti.entity.ApplicationIeda;
import com.indihx.activiti.vo.ApplicationVo;
import com.indihx.system.entity.UsrInfo;

/***
 * 
 * <p>描 述: 流程信息</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月5日</p>
 * @author 谢追辉
 */
public interface IApplicationBiz {
	
	/***
	 * 查询当前用户的代办任务
	 * @param app 查询条件
	 * @return 结果集
	 */
	Map<String, Object> queryToDo(ApplicationVo vo);
	
	/***
	 * 查询当前用户已办理的任务
	 * @param vo 条件
	 * @return
	 */
	Map<String, Object> queryToDoComp(ApplicationVo vo);
	/***
	 * 提交下一步操作
	 * @param appId 流程ID
	 * @param nodeId 节点ID
	 * @param userId 用户ID
	 * @param memo 提交意见
	 * @return
	 * @throws Exception 
	 */
	Application nextCommit(String appId,String nodeId,String userId,String memo,String taskId,String nextOrgId) throws Exception;
	
	/***
	 * 提交下一步操作-到角色
	 * @param appId 流程ID
	 * @param nodeId 节点ID
	 * @param memo 提交意见
	 * @return 实体
	 * @throws Exception 
	 */
	Application nextCommit(String appId,String nodeId,String memo) throws Exception;
	
	/***
	 * 提交下一步操作通用
	 * @param appId 流程ID
	 * @param nodeId 节点ID
	 * @param memo 提交意见
	 * @return 实体
	 */
	Application nextCommit(Map<String, Object> reqMap);
	
	/***
	 * 驳回当前流程
	 * @param appId 流程ID
	 * @param memo 驳回意见
	 * @return 流程对象
	 * @throws Exception 
	 */
	Application rejectTask(String appId,String memo,String taskId) throws Exception;
	
	/***
     * 查询流程跟踪的信息
     * @param vo 流程信息
     * @return 集合
     */
    List<ApplicationIeda>  selectProcessTrack(ApplicationVo vo);
	
    /***
     * 查询要处理任务需要展示的基本信息
     * @param vo 流程信息
     * @return
     * @throws Exception 
     */
    Map<String, Object> queryTaskInfo(ApplicationVo vo,UsrInfo usrInfo) throws Exception;
    
    /***
     * 根据AppId得到当前流程信息
     * @param appId
     * @return
     */
    Application getApplicationById(String appId);
    
    /***
     * 根据流程对象得到业务处理逻辑类
     * @param app
     * @return
     * @throws Exception 
     */
    ActivitiBusiness getBusinessBean(Application app) ;
}
