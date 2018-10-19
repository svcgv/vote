package com.indihx.system.entity;

import java.io.Serializable;

/**
 * 
 * <p>描 述: 用户角色信息表</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年4月20日</p>
 * @author 谢追辉
 */
public class UsrRoleKey implements Serializable{
    private Long usrId;  //用户ID

    private Long roleId; //角色ID

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}