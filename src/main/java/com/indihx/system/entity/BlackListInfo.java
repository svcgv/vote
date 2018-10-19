package com.indihx.system.entity;

import java.io.Serializable;

public class BlackListInfo implements Serializable{
    private Long blackId;

    private String blackType;

    private String blackSts;

    private Long blackObjId;
    
    private Long createUserId;

    private String blackName;

    private String blackDesc;

    private String createName;

    private String tmSmp;

    private String revokeDate;

    private String revokeDesc;
    
    private Long revokeUserId;

    private String revokeName;

    public Long getBlackId() {
        return blackId;
    }

    public void setBlackId(Long blackId) {
        this.blackId = blackId;
    }

    public String getBlackType() {
        return blackType;
    }

    public void setBlackType(String blackType) {
        this.blackType = blackType == null ? null : blackType.trim();
    }

    public String getBlackSts() {
        return blackSts;
    }

    public void setBlackSts(String blackSts) {
        this.blackSts = blackSts == null ? null : blackSts.trim();
    }

    public Long getBlackObjId() {
        return blackObjId;
    }

    public void setBlackObjId(Long blackObjId) {
        this.blackObjId = blackObjId;
    }
    
    public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getBlackName() {
        return blackName;
    }

    public void setBlackName(String blackName) {
        this.blackName = blackName == null ? null : blackName.trim();
    }

    public String getBlackDesc() {
        return blackDesc;
    }

    public void setBlackDesc(String blackDesc) {
        this.blackDesc = blackDesc == null ? null : blackDesc.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }

    public String getRevokeDate() {
        return revokeDate;
    }

    public void setRevokeDate(String revokeDate) {
        this.revokeDate = revokeDate == null ? null : revokeDate.trim();
    }

    public String getRevokeDesc() {
        return revokeDesc;
    }

    public void setRevokeDesc(String revokeDesc) {
        this.revokeDesc = revokeDesc == null ? null : revokeDesc.trim();
    }
    
    public Long getRevokeUserId() {
		return revokeUserId;
	}

	public void setRevokeUserId(Long revokeUserId) {
		this.revokeUserId = revokeUserId;
	}

	public String getRevokeName() {
        return revokeName;
    }

    public void setRevokeName(String revokeName) {
        this.revokeName = revokeName == null ? null : revokeName.trim();
    }
}