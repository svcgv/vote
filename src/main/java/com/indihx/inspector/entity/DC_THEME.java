package com.indihx.inspector.entity;

import java.io.Serializable;
/**
 * 检查主题表
 */
public class DC_THEME implements Serializable {
	private static final long serialVersionUID = 1L;
	public String theme_id;//主题ID
	public String theme_name;//主题名称
	public String start_date;//检查开始日期
	public String end_date ;//检查结束日期
	public String design_flag;//是否制定检查
	public String crt_org; //创建单位ID 
	public String crt_user;//创建操作员编号  
	public String crt_date;//创建日期 
	public String crt_time;//创建时间
	public String remark; //备注
	public String status;//状态
	public String issue_date;//发布日期
	public String issue_time;//发布时间
	public String getTheme_id() {
		return theme_id;
	}
	public void setTheme_id(String theme_id) {
		this.theme_id = theme_id;
	}
	public String getTheme_name() {
		return theme_name;
	}
	public void setTheme_name(String theme_name) {
		this.theme_name = theme_name;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getDesign_flag() {
		return design_flag;
	}
	public void setDesign_flag(String design_flag) {
		this.design_flag = design_flag;
	}
	public String getCrt_org() {
		return crt_org;
	}
	public void setCrt_org(String crt_org) {
		this.crt_org = crt_org;
	}
	public String getCrt_user() {
		return crt_user;
	}
	public void setCrt_user(String crt_user) {
		this.crt_user = crt_user;
	}
	public String getCrt_date() {
		return crt_date;
	}
	public void setCrt_date(String crt_date) {
		this.crt_date = crt_date;
	}
	public String getCrt_time() {
		return crt_time;
	}
	public void setCrt_time(String crt_time) {
		this.crt_time = crt_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}
	public String getIssue_time() {
		return issue_time;
	}
	public void setIssue_time(String issue_time) {
		this.issue_time = issue_time;
	}
	
	
	
}
