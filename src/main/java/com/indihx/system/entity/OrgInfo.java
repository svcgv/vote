package com.indihx.system.entity;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
/**
 * 机构信息
 * @author ZSS
 *
 */
public class OrgInfo implements Serializable{
private Long orgNo;
private String orgName;
private Long parentOrgNo;
private String parentOrgName;
private String orgType;
private String addres;
private String telNo;
private String operUsr;
private String tmSmp;
private String orgStatus; //单位状态
private String distCode;//归属区县
private String linkMan; //联系人
private String orgMobile; //移动电话
private String email; //Email地址
private String postCode; //邮政编码
private String orgAddr; //办公地址
private String remark; //备注
private String orgTel;//联系电话


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

public Long getOrgNo() {
	return orgNo;
}

public void setOrgNo(Long orgNo) {
	this.orgNo = orgNo;
}
public void setOrgNo(String orgNo) {
	this.orgNo = StringUtils.isEmpty(orgNo) == false ? new Long(orgNo) : null;
}


public String getOrgName() {
	return orgName;
}

public void setOrgName(String orgName) {
	this.orgName = orgName == null ? null : orgName.trim();
}

public Long getParentOrgNo() {
	return parentOrgNo;
}

public void setParentOrgNo(Long parentOrgNo) {
	this.parentOrgNo = parentOrgNo;
}
public void setParentOrgNo(String parentOrgNo){
	 this.parentOrgNo = StringUtils.isEmpty(parentOrgNo) == false ? new Long(parentOrgNo) : null;
}
public String getParentOrgName() {
	return parentOrgName;
}

public void setParentOrgName(String parentOrgName) {
	 this.parentOrgName = parentOrgName == null ? null : parentOrgName.trim();
}

public String getOrgType() {
	return orgType;
}

public void setOrgType(String orgType) {
	 this.orgType = orgType == null ? null : orgType.trim();
}

public String getAddres() {
	return addres;
}

public void setAddres(String addres) {
	 this.addres = addres == null ? null : addres.trim();
}

public String getTelNo() {
	return telNo;
}

public void setTelNo(String telNo) {
	 this.telNo = telNo == null ? null : telNo.trim();
}

public String getOperUsr() {
	return operUsr;
}

public void setOperUsr(String operUsr) {
	 this.operUsr = operUsr == null ? null : operUsr.trim();
}

public String getTmSmp() {
	return tmSmp;
}

public void setTmSmp(String tmSmp) {
	 this.tmSmp = tmSmp == null ? null : tmSmp.trim();
}

}
