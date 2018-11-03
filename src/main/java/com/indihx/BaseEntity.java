package com.indihx;

public class BaseEntity {

	/**
	 * 创建人编号
	 */
	private long creatorId;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 修改人编号
	 */
	private long modifier;
	/**
	 * 修改时间
	 */
	private String modifyTime;

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public long getModifier() {
		return modifier;
	}

	public void setModifier(long modifier) {
		this.modifier = modifier;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	

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
