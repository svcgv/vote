package com.indihx.system.entity;

import java.io.Serializable;

public class MenuQryEntity implements Serializable{
	
	private String usrId;  //用户ID
	private String menuLevel; //用户级别
	private String parentId;  //父菜单
	private String isHome; // 是否首页展示
	
	public String getIsHome() {
		return isHome;
	}
	public void setIsHome(String isHome) {
		this.isHome = isHome;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	
}
