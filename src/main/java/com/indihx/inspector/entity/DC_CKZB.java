package com.indihx.inspector.entity;

import java.io.Serializable;
/**
 * 检查指标
 */
public class DC_CKZB implements Serializable {
	private static final long serialVersionUID = 1L;
	public String theme_id;//主题ID
	public long check_seq;//记分指标流水号ID
	public String getTheme_id() {
		return theme_id;
	}
	public void setTheme_id(String theme_id) {
		this.theme_id = theme_id;
	}
	public long getCheck_seq() {
		return check_seq;
	}
	public void setCheck_seq(long check_seq) {
		this.check_seq = check_seq;
	} 
	
}
