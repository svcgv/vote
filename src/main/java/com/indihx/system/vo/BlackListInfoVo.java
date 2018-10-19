package com.indihx.system.vo;

public class BlackListInfoVo extends BaseVo{
	private String blackId;
	
	private String blackdId;

    private String blackType;

    private String blackSts;

    private String blackObjId;

    private String blackName;

    private String blackDesc;

    private String createUserId;

    private String tmSmp;

    private String revokeDate;

    private String revokeDesc;

    private String revokeUserId;

    public String getBlackId() {
        return blackId;
    }

    public void setBlackId(String blackId) {
        this.blackId = blackId;
    }

    public String getBlackdId() {
		return blackdId;
	}

	public void setBlackdId(String blackdId) {
		this.blackdId = blackdId;
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

    public String getBlackObjId() {
        return blackObjId;
    }

    public void setBlackObjId(String blackObjId) {
        this.blackObjId = blackObjId;
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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
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

    public String getRevokeUserId() {
        return revokeUserId;
    }

    public void setRevokeUserId(String revokeUserId) {
        this.revokeUserId = revokeUserId == null ? null : revokeUserId.trim();
    }
}