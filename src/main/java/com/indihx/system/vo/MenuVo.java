package com.indihx.system.vo;

import java.util.List;

/**
 * 
 * <p>
 * 描 述: 类描述
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年2月28日
 * </p>
 * 
 * @author 谢追辉
 */
public class MenuVo extends BaseVo {
	private String menuId; // 菜单ID

	private String menuName;

	private String parentId;
	
	private String parentName;

	private String menuUrl;

	private String operUser;

	private String menuLevel;

	private String tmSmp;

	private List<MenuVo> menuVo;

	private String className;
	
	private String parentIdBy;
	
	private String codeVal;
	
	private String usrName;
	
	private String levelHtml;
	
	private String parentHtml;
	
	private String sortNum;
	
	private String cssIcon;
	
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

	public String getCodeVal() {
		return codeVal;
	}

	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getParentIdBy() {
		return parentIdBy;
	}

	public void setParentIdBy(String parentIdBy) {
		this.parentIdBy = parentIdBy;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<MenuVo> getMenuVo() {
		return menuVo;
	}

	public void setMenuVo(List<MenuVo> menuVo) {
		this.menuVo = menuVo;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getOperUser() {
		return operUser;
	}

	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}



	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getTmSmp() {
		return tmSmp;
	}

	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


}
