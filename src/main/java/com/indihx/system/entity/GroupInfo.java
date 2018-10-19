package com.indihx.system.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class GroupInfo implements Serializable{
    private Long groupId;//组ID

    private String groupName;//组名称

    private String tmSmp;//时间
    
    /**get组ID*/
    public Long getGroupId() {
        return groupId;
    }
    /**set组ID*/
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    
    /**set组ID*/
    public void setGroupId(String groupId) {
        this.groupId = StringUtils.isEmpty(groupId) == false ? new Long(groupId) : null;
    }
    
    /**get组名称*/
    public String getGroupName() {
        return groupName;
    }
    /**set组名称*/
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }
    /**get时间*/
    public String getTmSmp() {
        return tmSmp;
    }
    /**set时间*/
    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }
}