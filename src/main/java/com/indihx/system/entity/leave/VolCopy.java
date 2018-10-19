package com.indihx.system.entity.leave;

import java.io.Serializable;

/***
 * 
 * <p>描 述: 请假流程（副表）</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月4日</p>
 * @author 谢追辉
 */
public class VolCopy implements Serializable{
	private Long voaId;	 //请假ID

    private Long appId;   //流程ID

    private String voaDesc; //请假理由

    private String voaUserId; //请假人

    private String voaDate;  //请假时间

    public Long getVoaId() {
        return voaId;
    }

    public void setVoaId(Long voaId) {
        this.voaId = voaId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getVoaDesc() {
        return voaDesc;
    }

    public void setVoaDesc(String voaDesc) {
        this.voaDesc = voaDesc == null ? null : voaDesc.trim();
    }

    public String getVoaUserId() {
        return voaUserId;
    }

    public void setVoaUserId(String voaUserId) {
        this.voaUserId = voaUserId == null ? null : voaUserId.trim();
    }

    public String getVoaDate() {
        return voaDate;
    }

    public void setVoaDate(String voaDate) {
        this.voaDate = voaDate == null ? null : voaDate.trim();
    }
}