package com.indihx.system.entity;

import java.io.Serializable;

/***
 * 
 * <p>
 * 描 述: 按钮信息实体
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年7月25日
 * </p>
 * 
 * @author 严蒙蒙
 */
public class BtnInfo implements Serializable{
    private String btnId;//按钮主键
    
    private String menuId;

    private String btnName;//按钮名称

    private String btnRmk;//按钮说明

    private String btnPage;//按钮所属页面

    private String tmSmp;//时间
    
    private String roleBtnId;//角色按钮表按钮主键
    
    private String roleId;//角色ID

    /**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleBtnId() {
		return roleBtnId;
	}

	public void setRoleBtnId(String roleBtnId) {
		this.roleBtnId = roleBtnId;
	}

	public String getBtnId() {
        return btnId;
    }

    public void setBtnId(String btnId) {
        this.btnId = btnId == null ? null : btnId.trim();
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName == null ? null : btnName.trim();
    }

    public String getBtnRmk() {
        return btnRmk;
    }

    public void setBtnRmk(String btnRmk) {
        this.btnRmk = btnRmk == null ? null : btnRmk.trim();
    }

    public String getBtnPage() {
        return btnPage;
    }

    public void setBtnPage(String btnPage) {
        this.btnPage = btnPage == null ? null : btnPage.trim();
    }

    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}