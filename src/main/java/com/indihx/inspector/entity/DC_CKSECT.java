package com.indihx.inspector.entity;

import java.io.Serializable;
/**
 * 指定检查小区表
 */
public class DC_CKSECT implements Serializable {
	private static final long serialVersionUID = 1L;
	public String theme_id;//主题ID
	public String sect_id;//小区ID
	public String getTheme_id() {
		return theme_id;
	}
	public void setTheme_id(String theme_id) {
		this.theme_id = theme_id;
	}
	public String getSect_id() {
		return sect_id;
	}
	public void setSect_id(String sect_id) {
		this.sect_id = sect_id;
	}
}
