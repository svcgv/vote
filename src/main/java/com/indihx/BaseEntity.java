package com.indihx;

public class BaseEntity {
	//分页查询开始
	private int page;
	//分页查询每页条数
	private int limit;
	public int getStartNum() {
		return page;
	}
	public void setStartNum(int startNum) {
		this.page = startNum;
	}
	public int getTotalNum() {
		return limit;
	}
	public void setTotalNum(int totalNum) {
		this.limit = totalNum;
	}
	
	
}
