package com.indihx.PmSaleGroupInfo.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 19:04:20
 */
public class PmSaleMemberInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private Long menberUsrId;
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

private Long creatorId;
	/**
	 * 
	 */

private Date createTime;
	/**
	 * 
	 */

private Long modifier;
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
	public void setMenberUsrId(Long menberUsrId) {
		this.menberUsrId = menberUsrId;
	}
	/**
	 * 获取：
	 */
	public Long getMenberUsrId() {
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
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 获取：
	 */
	public Long getCreatorId() {
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
	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}
	/**
	 * 获取：
	 */
	public Long getModifier() {
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
