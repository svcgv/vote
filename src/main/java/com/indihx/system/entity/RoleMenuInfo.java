package com.indihx.system.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/** 
 * 项目名称：wx_mng_maven 
 * 类名称：RoleMenuInfo 
 * 类描述： 
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月10日 下午4:38:13 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
public class RoleMenuInfo implements Serializable{
  private Long roleId;
  private Long menuId;
  private String tmSmp;


public Long getRoleId() {
	return roleId;
}


public void setRoleId(Long roleId) {
	this.roleId = roleId;
}

public void setRoleId(String roleId) {
	this.roleId = StringUtils.isEmpty(roleId) == false ? new Long(roleId) : null;
}
public Long getMenuId() {
	return menuId;
}


public void setMenuId(Long menuId) {
	this.menuId = menuId;
}
public void setMenuId(String menuId) {
	this.menuId = StringUtils.isEmpty(menuId) == false ? new Long(menuId) : null;
}

public String getTmSmp() {
	return tmSmp;
}


public void setTmSmp(String tmSmp) {
	this.tmSmp = tmSmp;
}




  
	

}
