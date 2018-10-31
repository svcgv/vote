package com.indihx;

public class BaseEntity {
	//分页查询开始数
	private int startNum;
	//分页查询每页条数
	private int totalNum;
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	
}
