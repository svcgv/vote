package com.indihx.system.vo;

/**
 * 机构信息表
 * @author ZSS
 *
 */
public class OrgInfoVo extends BaseVo {
	private String orgNo;
	private String orgNo1;
	private String orgName;
	private String parentOrgNo;
	private String parentOrgName;
	private String orgType;
	private String addres;
	private String telNo;
	private String operUsr;
	private String tmSmp;
	private String codeVal;
    private String ewTypeHtml; 
    private String orgStatus; //单位状态
    private String distCode;//归属区县
    private String linkMan; //联系人
    private String orgMobile; //移动电话
    private String email; //Email地址
    private String postCode; //邮政编码
    private String orgAddr; //办公地址
    private String remark; //备注
    private String orgTel; //联系电话
    
	public String getOrgTel() {
		return orgTel;
	}
	public void setOrgTel(String orgTel) {
		this.orgTel = orgTel;
	}
	public String getOrgStatus() {
		return orgStatus;
	}
	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}
	public String getDistCode() {
		return distCode;
	}
	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getOrgMobile() {
		return orgMobile;
	}
	public void setOrgMobile(String orgMobile) {
		this.orgMobile = orgMobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getOrgAddr() {
		return orgAddr;
	}
	public void setOrgAddr(String orgAddr) {
		this.orgAddr = orgAddr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setOrgNo1(String orgNo1) {
		this.orgNo1 = orgNo1;
	}
	public String getEwTypeHtml() {
		return ewTypeHtml;
	}
	public void setEwTypeHtml(String ewTypeHtml) {
		this.ewTypeHtml = ewTypeHtml;
	}
	public String getCodeVal() {
		return codeVal;
	}
	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
	}
	private String parentOrgNoBy;
	
	public String getParentOrgNoBy() {
		return parentOrgNoBy;
	}
	public void setParentOrgNoBy(String parentOrgNoBy) {
		this.parentOrgNoBy = parentOrgNoBy;
	}
	public String getOrgNo1() {
		return orgNo1;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentOrgNo() {
		return parentOrgNo;
	}
	public void setParentOrgNo(String parentOrgNo) {
		this.parentOrgNo = parentOrgNo;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}
	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getOperUsr() {
		return operUsr;
	}
	public void setOperUsr(String operUsr) {
		this.operUsr = operUsr;
	}
	public String getTmSmp() {
		return tmSmp;
	}
	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
	}
 
	
}
