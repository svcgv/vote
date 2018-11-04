package com.indihx.PmSaleGroupInfo.entity;

import com.indihx.BaseEntity;
import java.io.Serializable;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @String 2018-11-02 20:19:45
 */
public class PmSaleGroupInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	private long groupId;
	/**
	 * 
	 */

	private String groupName;
	/**
	 * 
	 */

	private long ownerOrgId;
	/**
	 * 
	 */
	
	private String ownerOrgName;

	private String remark;
	/**
	 * 
	 */

	private long creatorId;
	/**
	 * 
	 */

	private String createTime;
	/**
	 * 
	 */

	private long modifier;
	/**
	 * 
	 */

	private String modifyTime;
	/**
	 * 
	 */

	private String isDelete;

	private String groupCode;

	
	public String getOwnerOrgName() {
		return ownerOrgName;
	}

	public void setOwnerOrgName(String ownerOrgName) {
		this.ownerOrgName = ownerOrgName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * 设置：
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	/**
	 * 获取：
	 */
	public long getGroupId() {
		return groupId;
	}

	/**
	 * 设置：
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 获取：
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 设置：
	 */
	public void setOwnerOrgId(long ownerOrgId) {
		this.ownerOrgId = ownerOrgId;
	}

	/**
	 * 获取：
	 */
	public long getOwnerOrgId() {
		return ownerOrgId;
	}

	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
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
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：
	 */
	public String getCreateTime() {
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
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 获取：
	 */
	public String getModifyTime() {
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
