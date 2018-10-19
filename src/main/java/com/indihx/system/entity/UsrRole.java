package com.indihx.system.entity;

import java.io.Serializable;

/***
 * 
 * <p>描 述:  用户角色信息表</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年4月20日</p>
 * @author 谢追辉
 */
public class UsrRole extends UsrRoleKey implements Serializable{
    private String tmSmp;   //录入时间

    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }
}