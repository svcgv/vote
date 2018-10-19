package com.indihx.system.entity;

import java.io.Serializable;

public class BaseOrgRel implements Serializable{
	private long relId;
	private String orgNo;
	private String pkId;
	private String pkTable;
	
	public long getRelId() {
		return relId;
	}
	public void setRelId(long relId) {
		this.relId = relId;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getPkId() {
		return pkId;
	}
	public void setPkId(String pkId) {
		this.pkId = pkId;
	}
	public String getPkTable() {
		return pkTable;
	}
	public void setPkTable(String pkTable) {
		this.pkTable = pkTable;
	}
	
	
}