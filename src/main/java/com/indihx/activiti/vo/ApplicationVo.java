package com.indihx.activiti.vo;

import com.indihx.system.vo.BaseVo;

public class ApplicationVo extends BaseVo {
	private Long appId;

    private String actId;

    private String appName;

    private String startDate;

    private String createId;

    private String appType;

    private String endDate;

    private String currStatus;

    private String currUserId;

    private String currNodeId;
    
    private  String currOrgId;
    
    private String taskId;
    
    private String nextOrgId;
    
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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getCurrOrgId() {
		return currOrgId;
	}

	public void setCurrOrgId(String currOrgId) {
		this.currOrgId = currOrgId;
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
