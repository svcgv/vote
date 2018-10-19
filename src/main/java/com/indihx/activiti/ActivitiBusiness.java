package com.indihx.activiti;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indihx.activiti.entity.AppNodeInfo;
import com.indihx.activiti.entity.Application;

/***
 * 
 * <p>描 述: 流程业务逻辑处理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月6日</p>
 * @author 谢追辉
 */
public abstract  class ActivitiBusiness{
	
	private Logger logger = LoggerFactory.getLogger(ActivitiBusiness.class);
	/***
	 * 下个节点有多个节点时，是否需要控制某些节点不显示
	 * @param app 流程对象
	 * @param nodes 节点列表
	 */
	public void removeNextNode(Application app,List<AppNodeInfo> nodes){
		
	}
	
	/***
	 * 当前驳回节点有多个节点，怎么选择驳回到哪个节点，由业务逻辑决定
	 * 默认驳回到流程的上一个节点。存于APPLICATION中的，
	 * 如果复杂的驳回流程，请重写此方法
	 * @param app 流程对象
	 * @param nodes 节点列表
	 */
	public AppNodeInfo rejectNodeId(Application app,List<AppNodeInfo> nodes){
		for (int i = 0; i < nodes.size(); i++) {
			if (app.getFrontNodeId().equals(nodes.get(i).getNodeId())) {
				return nodes.get(i);
			}
		}
		return null;
	}
	
	/***
	 * 流程提交业务逻辑
	 * @param app  实体
	 */
	public abstract void handCopy(Application app);
	
	/***
	 * 流程结束业务逻辑
	 * @param app 实体
	 */
	public abstract void processEnd(Application app);
	
	/***
	 * 每次提交之前的时候，需要处理的业务逻辑
	 * @param app
	 * @return 需要放入工作流中的变量值
	 */
	public abstract Map<String, Object> handCommitFirst(Application app,Map<String, Object> variables);

	/***
	 * 流程驳回业务逻辑
	 * @param app 实体
	 */
	public abstract void rejectProcess(Application app);
	
	/***
	 * 查询当前流程的代办任务
	 * 返回一个Map对象，包括返回值全部放到Map中，map的key为啥，在前台页面就map的key取值
	 * Map中必须包含返回的jsp页面URL的ke为：ConstantStatic.URL
	 * @param app 实体
	 */
	public abstract Map<String, Object> queryToDoInfo(Application app);
	
}
