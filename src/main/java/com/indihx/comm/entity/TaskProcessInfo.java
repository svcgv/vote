package com.indihx.comm.entity;

/***
 * 
 * <p>描 述: 工作流数据实体信息表关于业务主表信息</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年4月6日</p>
 * @author 谢追辉
 */
public class TaskProcessInfo  {

	private String taskId;  //任务ID
	
	private String taskName; //任务名称
	
	private String buisinessKey; //业务ID
	
	private String taskType; //任务类型  0 已有处理人(已领取)  1 没有处理人(待领取)
	
	private String currNodeName;  //任务名称
	
	private String currNode;  //当前节点ID
	
	private String currUsr;  //当前处理人
	
	private String taskStatus; //当前任务状态 01 已领取  02待领取
	
	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getBuisinessKey() {
		return buisinessKey;
	}

	public void setBuisinessKey(String buisinessKey) {
		this.buisinessKey = buisinessKey;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getCurrNodeName() {
		return currNodeName;
	}

	public void setCurrNodeName(String currNodeName) {
		this.currNodeName = currNodeName;
	}

	public String getCurrNode() {
		return currNode;
	}

	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}

	public String getCurrUsr() {
		return currUsr;
	}

	public void setCurrUsr(String currUsr) {
		this.currUsr = currUsr;
	}

}
