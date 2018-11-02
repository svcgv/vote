package com.indihx;

import java.util.Date;

public class BaseEntity {
	// 分页查询开始数
	private int startNum;
	// 分页查询每页条数
	private int totalNum;
	/**
	 * 创建人编号
	 */
	private long creatorId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人编号
	 */
	private long modifier;
	/**
	 * 修改时间
	 */
	private Date modifyTime;

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getModifier() {
		return modifier;
	}

	public void setModifier(long modifier) {
		this.modifier = modifier;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

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
