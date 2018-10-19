package com.indihx.system.entity;

import java.io.Serializable;

public class BtnRole implements Serializable{
    private String tmSmp;//时间
    
    private String btnId;//按钮主键

    private String roleId;//角色主键
    
    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }
    
    public String getBtnId() {
        return btnId;
    }

    public void setBtnId(String btnId) {
        this.btnId = btnId == null ? null : btnId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}