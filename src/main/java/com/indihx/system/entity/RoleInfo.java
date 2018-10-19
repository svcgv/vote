package com.indihx.system.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/** 
 * 项目名称：wx_mng_maven 
 * 类名称：RoleInfo 
 * 类描述： 
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月9日 下午1:01:32 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
public class RoleInfo implements Serializable{
	private Long roleId;
	private String roleName;
	private String roleRmk;
	private String tmSmp;
	private String crtUser;//创建人
	
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = StringUtils.isEmpty(roleId) == false ? new Long(roleId) : null;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleRmk() {
		return roleRmk;
	}
	public void setRoleRmk(String roleRmk) {
		this.roleRmk = roleRmk;
	}
	public String getTmSmp() {
		return tmSmp;
	}
	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
	}

}
