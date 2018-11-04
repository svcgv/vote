package com.indihx.baseTableUtil.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-04 18:32:49
 */
public class QueryOrgInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long parentOrgNo;
	/**
	 * 
	 */

private String parentOrgName;
	/**
	 * 
	 */

private long orgNo;
	/**
	 * 
	 */

private String orgName;
	/**
	 * 
	 */

private String orgType;
	/**
	 * 
	 */

private String addres;
	/**
	 * 
	 */

private String telNo;
	/**
	 * 
	 */

private String operUsr;
	/**
	 * 
	 */

private String tmSmp;
	/**
	 * 
	 */

private String orgStatus;
	/**
	 * 
	 */

private String linkMan;
	/**
	 * 
	 */

private String email;
	/**
	 * 
	 */

private String postCode;
	/**
	 * 
	 */

private String remark;

private QueryOrgInfoEntity children;



	public QueryOrgInfoEntity getChildren() {
	return children;
}
public void setChildren(QueryOrgInfoEntity children) {
	this.children = children;
}
	/**
	 * 设置：
	 */
	public void setParentOrgNo(long parentOrgNo) {
		this.parentOrgNo = parentOrgNo;
	}
	/**
	 * 获取：
	 */
	public long getParentOrgNo() {
		return parentOrgNo;
	}
	/**
	 * 设置：
	 */
	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	/**
	 * 获取：
	 */
	public String getParentOrgName() {
		return parentOrgName;
	}
	/**
	 * 设置：
	 */
	public void setOrgNo(long orgNo) {
		this.orgNo = orgNo;
	}
	/**
	 * 获取：
	 */
	public long getOrgNo() {
		return orgNo;
	}
	/**
	 * 设置：
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	/**
	 * 获取：
	 */
	public String getOrgType() {
		return orgType;
	}
	/**
	 * 设置：
	 */
	public void setAddres(String addres) {
		this.addres = addres;
	}
	/**
	 * 获取：
	 */
	public String getAddres() {
		return addres;
	}
	/**
	 * 设置：
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	/**
	 * 获取：
	 */
	public String getTelNo() {
		return telNo;
	}
	/**
	 * 设置：
	 */
	public void setOperUsr(String operUsr) {
		this.operUsr = operUsr;
	}
	/**
	 * 获取：
	 */
	public String getOperUsr() {
		return operUsr;
	}
	/**
	 * 设置：
	 */
	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
	}
	/**
	 * 获取：
	 */
	public String getTmSmp() {
		return tmSmp;
	}
	/**
	 * 设置：
	 */
	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}
	/**
	 * 获取：
	 */
	public String getOrgStatus() {
		return orgStatus;
	}
	/**
	 * 设置：
	 */
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	/**
	 * 获取：
	 */
	public String getLinkMan() {
		return linkMan;
	}
	/**
	 * 设置：
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
}
