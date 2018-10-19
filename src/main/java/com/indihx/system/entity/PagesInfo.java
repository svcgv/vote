package com.indihx.system.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class PagesInfo implements Serializable{
	public int rows;  //每页显示条数
	public int pages; //当前页数
	public int totle; //总记录数
	public int tot_page; //总页数
	public int start_page; //开始页
	public int end_page; //结束页
	
	public int getTot_page() {
		return tot_page;
	}
	public void setTot_page(String tot_page) {
		this.tot_page = StringUtils.isEmpty(tot_page) == true ? 0 : Integer.parseInt(tot_page);
	}
	public int getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = StringUtils.isEmpty(rows) == true ? 0 : Integer.parseInt(rows);
	}
	public int getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = StringUtils.isEmpty(pages) == true ? 0 : Integer.parseInt(pages);
	}
	public int getTotle() {
		return totle;
	}
	public void setTotle(String totle) {
		this.totle = StringUtils.isEmpty(totle) == true ? 0 : Integer.parseInt(totle);
	}
	public int getStart_page() {
		return start_page;
	}
	public void setStart_page(int start_page) {
		this.start_page = start_page;
	}
	public int getEnd_page() {
		return end_page;
	}
	public void setEnd_page(int end_page) {
		this.end_page = end_page;
	}
	
	
}
