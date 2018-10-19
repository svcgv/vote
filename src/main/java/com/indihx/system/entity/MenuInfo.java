package com.indihx.system.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/***
 * 
 * <p>
 * 描 述: 菜单信息
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年2月24日
 * </p>
 * 
 * @author 谢追辉
 */
public class MenuInfo implements Serializable{
	private Long menuId; // 菜单ID

	private String menuName;

	private Long parentId;

	private String menuUrl;

	private Long operUser;

	private String menuLevel;

	public String getIsHome() {
		return isHome;
	}

	public void setIsHome(String isHome) {
		this.isHome = isHome;
	}

	public String getHomeIcon() {
		return homeIcon;
	}

	public void setHomeIcon(String homeIcon) {
		this.homeIcon = homeIcon;
	}

	private String className;
	
	private String levelHtml;
	
	private String parentHtml;
	
	private String sortNum;
	
	private String cssIcon;
	
	private String isHome; // 是否首页展示

	private String homeIcon; // 首页展示图标

	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

	public String getParentHtml() {
		return parentHtml;
	}

	public void setParentHtml(String parentHtml) {
		this.parentHtml = parentHtml;
	}

	public String getLevelHtml() {
		return levelHtml;
	}

	public void setLevelHtml(String levelHtml) {
		this.levelHtml = levelHtml;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	private Long tmSmp;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = StringUtils.isEmpty(menuId) == true ? null : new Long(menuId);
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = StringUtils.isEmpty(parentId) == true ? null : new Long(parentId);
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public Long getOperUser() {
		return operUser;
	}

	public void setOperUser(Long operUser) {
		this.operUser = operUser;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel == null ? null : menuLevel.trim();
	}

	public Long getTmSmp() {
		return tmSmp;
	}

	public void setTmSmp(Long tmSmp) {
		this.tmSmp = tmSmp;
	}

	public void setTmSmp(String tmSmp) {
		this.tmSmp = StringUtils.isEmpty(tmSmp) == true ? null : new Long(tmSmp);
	}

	/**
	 * @return the cssIcon
	 */
	public String getCssIcon() {
		return cssIcon;
	}

	/**
	 * @param cssIcon the cssIcon to set
	 */
	public void setCssIcon(String cssIcon) {
		this.cssIcon = cssIcon;
	}

}