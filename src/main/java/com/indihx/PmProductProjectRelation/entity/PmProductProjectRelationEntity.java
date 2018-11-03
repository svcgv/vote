package com.indihx.PmProductProjectRelation.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-03 17:12:14
 */
public class PmProductProjectRelationEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long productRelationId;
	/**
	 * 
	 */

private String productName;
	/**
	 * 
	 */

private long productId;
	/**
	 * 
	 */

private long productGroupId;
	/**
	 * 
	 */

private long projectId;
	/**
	 * 
	 */

private String buildDeptName;
	/**
	 * 
	 */

private String custName;
	/**
	 * 
	 */

private String custCode;
	/**
	 * 
	 */

private String custSapCode;
	/**
	 * 
	 */

private String projectName;
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
	public void setProductRelationId(long productRelationId) {
		this.productRelationId = productRelationId;
	}
	/**
	 * 获取：
	 */
	public long getProductRelationId() {
		return productRelationId;
	}
	/**
	 * 设置：
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}
	/**
	 * 获取：
	 */
	public long getProductId() {
		return productId;
	}
	/**
	 * 设置：
	 */
	public void setProductGroupId(long productGroupId) {
		this.productGroupId = productGroupId;
	}
	/**
	 * 获取：
	 */
	public long getProductGroupId() {
		return productGroupId;
	}
	/**
	 * 设置：
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：
	 */
	public long getProjectId() {
		return projectId;
	}
	/**
	 * 设置：
	 */
	public void setBuildDeptName(String buildDeptName) {
		this.buildDeptName = buildDeptName;
	}
	/**
	 * 获取：
	 */
	public String getBuildDeptName() {
		return buildDeptName;
	}
	/**
	 * 设置：
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * 获取：
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * 设置：
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	/**
	 * 获取：
	 */
	public String getCustCode() {
		return custCode;
	}
	/**
	 * 设置：
	 */
	public void setCustSapCode(String custSapCode) {
		this.custSapCode = custSapCode;
	}
	/**
	 * 获取：
	 */
	public String getCustSapCode() {
		return custSapCode;
	}
	/**
	 * 设置：
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：
	 */
	public String getProjectName() {
		return projectName;
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
