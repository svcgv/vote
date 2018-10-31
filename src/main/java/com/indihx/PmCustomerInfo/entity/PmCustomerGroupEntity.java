package com.indihx.PmCustomerInfo.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-31 19:17:35
 */
public class PmCustomerGroupEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private String custGroupId;
	/**
	 * 
	 */

private String custGroupName;
	/**
	 * 
	 */

private String creator;
	/**
	 * 
	 */

private long creatorId;
	/**
	 * 
	 */

private Date createTime;
	/**
	 * 
	 */

private long modifier;
	/**
	 * 
	 */

private Date modifyTime;
	/**
	 * 
	 */

private String isDelete;

	/**
	 * 设置：
	 */
	public void setCustGroupId(String custGroupId) {
		this.custGroupId = custGroupId;
	}
	/**
	 * 获取：
	 */
	public String getCustGroupId() {
		return custGroupId;
	}
	/**
	 * 设置：
	 */
	public void setCustGroupName(String custGroupName) {
		this.custGroupName = custGroupName;
	}
	/**
	 * 获取：
	 */
	public String getCustGroupName() {
		return custGroupName;
	}
	/**
	 * 设置：
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * 获取：
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * 设置：
	 */
	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 获取：
	 */
	public long getCreatorId() {
		return creatorId;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setModifier(long modifier) {
		this.modifier = modifier;
	}
	/**
	 * 获取：
	 */
	public long getModifier() {
		return modifier;
	}
	/**
	 * 设置：
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 获取：
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * 设置：
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：
	 */
	public String getIsDelete() {
		return isDelete;
	}
}
