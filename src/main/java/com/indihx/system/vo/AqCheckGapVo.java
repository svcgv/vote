package com.indihx.system.vo;

import com.indihx.system.vo.BaseVo;

public class AqCheckGapVo extends BaseVo{
	private String id;
	private String buildClass;
	private String checkDays;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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