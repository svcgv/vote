package com.indihx.system.entity;

import java.io.Serializable;

public class AqCheckGap implements Serializable{
	private long id;
	private String buildClass;
	private String checkDays;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBuildClass() {
		return buildClass;
	}
	public void setBuildClass(String buildClass) {
		this.buildClass = buildClass;
	}
	public String getCheckDays() {
		return checkDays;
	}
	public void setCheckDays(String checkDays) {
		this.checkDays = checkDays;
	}
	
   
}