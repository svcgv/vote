package com.indihx.activiti.entity;

import java.io.Serializable;

/***
 * 
 * <p>描 述: 流程总表实体类</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月8日</p>
 * @author 谢追辉
 */
public class Application implements Serializable{
	
	
    private Long appId; //流程ID
	
    private String actId;  //工作流ID
	
    private String appName; //流程名称
	
    private String startDate;  //发起时间
	
    private String createId;   //创建用户
	
    private String appType;    //流程类型
	
    private String endDate;  //结束时间
	
    private String currStatus;  //当前状态
	
    private String currUserId;  //当前处理人
	
    private String currNodeId;  //当前处理节点
	
    private String currNodeName;  //当前处理节点名称
    
    private String currRoleId ;//当前处理人角色
    
    private String currOrgId; //当前处理机构
    
    private String frontNodeId; //上一个处理节点
    
    private String frontNodeName; //上一个节点处理名称
    
    private String frontUserId; //上一个节点处理人
    
    private String createUserName; //创建人姓名
    
    private String taskId; //当前任务ID
    
    private String endTime; //当前任务结束时间
    
    private String startTime; //当前任务开始时间
    
    private String nextOrgId;//下一节点处理机构ID
    
    /**
	 * @return the nextOrgId
	 */
	public String getNextOrgId() {
		return nextOrgId;
	}

	/**
	 * @param nextOrgId the nextOrgId to set
	 */
	public void setNextOrgId(String nextOrgId) {
		this.nextOrgId = nextOrgId;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCurrOrgId() {
		return currOrgId;
	}

	public void setCurrOrgId(String currOrgId) {
		this.currOrgId = currOrgId;
	}

	public String getCurrRoleId() {
		return currRoleId;
	}

	public void setCurrRoleId(String currRoleId) {
		this.currRoleId = currRoleId;
	}

	public String getFrontNodeId() {
		return frontNodeId;
	}

	public void setFrontNodeId(String frontNodeId) {
		this.frontNodeId = frontNodeId;
	}

	public String getFrontNodeName() {
		return frontNodeName;
	}

	public void setFrontNodeName(String frontNodeName) {
		this.frontNodeName = frontNodeName;
	}

	public String getFrontUserId() {
		return frontUserId;
	}

	public void setFrontUserId(String frontUserId) {
		this.frontUserId = frontUserId;
	}

	public String getCurrNodeName() {
		return currNodeName;
	}

	public void setCurrNodeName(String currNodeName) {
		this.currNodeName = currNodeName;
	}

	public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId == null ? null : actId.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getCurrStatus() {
        return currStatus;
    }

    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus == null ? null : currStatus.trim();
    }

    public String getCurrUserId() {
        return currUserId;
    }

    public void setCurrUserId(String currUserId) {
        this.currUserId = currUserId == null ? null : currUserId.trim();
    }

    public String getCurrNodeId() {
        return currNodeId;
    }

    public void setCurrNodeId(String currNodeId) {
        this.currNodeId = currNodeId == null ? null : currNodeId.trim();
    }
}