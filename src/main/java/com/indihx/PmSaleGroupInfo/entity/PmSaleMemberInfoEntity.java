package com.indihx.PmSaleGroupInfo.entity;

import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @String 2018-10-30 19:04:20
 */
public class PmSaleMemberInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	private long menberUsrId;

	private String menberUsrName;
	/**
	 * 
	 */

	private String groupCode;
	/**
	 * 
	 */

	private String memberType;
	/**
	 * 
	 */

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

	public String getMenberUsrName() {
		return menberUsrName;
	}

	public void setMenberUsrName(String menberUsrName) {
		this.menberUsrName = menberUsrName;
	}

	/**
	 * 设置：
	 */
	public void setMenberUsrId(long menberUsrId) {
		this.menberUsrId = menberUsrId;
	}

	/**
	 * 获取：
	 */
	public long getMenberUsrId() {
		return menberUsrId;
	}

	/**
	 * 设置：
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * 获取：
	 */
	public String getGroupCode() {
		return groupCode;
	}

	/**
	 * 设置：
	 */
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	/**
	 * 获取：
	 */
	public String getMemberType() {
		return memberType;
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
