package com.indihx.inspector.vo;

import com.indihx.system.vo.BaseVo;

public class ThemeVo extends BaseVo{
	/**
	 * 
	 */
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
	public String quota_seq;//检查指标
	public String sect_id ;//制定检查小区
	public String remark;
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
	public String getQuota_seq() {
		return quota_seq;
	}
	public void setQuota_seq(String quota_seq) {
		this.quota_seq = quota_seq;
	}
	public String getSect_id() {
		return sect_id;
	}
	public void setSect_id(String sect_id) {
		this.sect_id = sect_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	} 
}
