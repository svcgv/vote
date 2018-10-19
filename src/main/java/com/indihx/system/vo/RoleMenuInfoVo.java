package com.indihx.system.vo;
/** 
 * 项目名称：wx_mng_maven 
 * 类名称：RoleMenuInfoVo 
 * 类描述： 
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月10日 下午4:43:16 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
public class RoleMenuInfoVo  extends BaseVo{
	  private String roleId;
	  private String menuId;
	  private String tmSmp;
	  public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getTmSmp() {
		return tmSmp;
	}
	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
	}
	

}
