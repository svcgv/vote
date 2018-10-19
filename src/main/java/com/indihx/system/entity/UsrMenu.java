package com.indihx.system.entity;

import java.io.Serializable;

/***
 * 
 * <p>描 述:用户菜单表</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年4月24日</p>
 * @author 谢追辉
 */
public class UsrMenu extends UsrMenuKey implements Serializable{
    private String tmSmp;  //时间戳

    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }
}