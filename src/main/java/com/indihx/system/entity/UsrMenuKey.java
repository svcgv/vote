package com.indihx.system.entity;

import java.io.Serializable;

/***
 * 
 * <p>描 述: 用户菜单表</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年4月24日</p>
 * @author 谢追辉
 */
public class UsrMenuKey implements Serializable{
    private Long usrId;  //用户ID

    private Long menuId; //菜单ID

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}