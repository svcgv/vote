package com.indihx.PmCompanyInfo.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @String 2018-10-30 18:46:08
 */
public class PmCompanyInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private long companyId;
private String companyCode;
	/**
	 * 
	 */

private String companyName;
	/**
	 * 
	 */

private String companyAddress;
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

	/**
	 * 设置：
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 * 获取：
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * 设置：
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	/**
	 * 获取：
	 */
	public String getCompanyAddress() {
		return companyAddress;
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

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
}
