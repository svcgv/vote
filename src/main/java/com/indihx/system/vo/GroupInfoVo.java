package com.indihx.system.vo;

public class GroupInfoVo extends BaseVo{
    private String groupId;//组ID
    private String groupdId;
    private String groupName;//组名称

    private String tmSmp;//时间
    
    /**get组ID*/
    public String getGroupId() {
        return groupId;
    }
    /**set组ID*/
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    public String getGroupdId() {
		return groupdId;
	}
	public void setGroupdId(String groupdId) {
		this.groupdId = groupdId;
	}
	/**get组名称*/
    public String getGroupName() {
        return groupName;
    }
    /**set组名称*/
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    /**get时间*/
    public String getTmSmp() {
        return tmSmp;
    }
    /**set时间*/
    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp;
    }
}