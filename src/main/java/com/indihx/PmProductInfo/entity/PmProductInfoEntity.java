package com.indihx.PmProductInfo.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-03 17:12:19
 */
public class PmProductInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long productId;
	/**
	 * 
	 */

private String productCode;
	/**
	 * 
	 */

private String productName;
	/**
	 * 
	 */

private long productSuggestPrice;
	/**
	 * 
	 */

private String developmentDeptName;
	/**
	 * 
	 */

private BigDecimal developmentDeptId;
	/**
	 * 
	 */

private String developmentManagerName;
	/**
	 * 
	 */

private long developmentManagerId;
	/**
	 * 
	 */

private String startSaleDate;
	/**
	 * 
	 */

private String productType;
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
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * 获取：
	 */
	public String getProductCode() {
		return productCode;
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
	public void setProductSuggestPrice(long productSuggestPrice) {
		this.productSuggestPrice = productSuggestPrice;
	}
	/**
	 * 获取：
	 */
	public long getProductSuggestPrice() {
		return productSuggestPrice;
	}
	/**
	 * 设置：
	 */
	public void setDevelopmentDeptName(String developmentDeptName) {
		this.developmentDeptName = developmentDeptName;
	}
	/**
	 * 获取：
	 */
	public String getDevelopmentDeptName() {
		return developmentDeptName;
	}
	/**
	 * 设置：
	 */
	public void setDevelopmentDeptId(BigDecimal developmentDeptId) {
		this.developmentDeptId = developmentDeptId;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getDevelopmentDeptId() {
		return developmentDeptId;
	}
	/**
	 * 设置：
	 */
	public void setDevelopmentManagerName(String developmentManagerName) {
		this.developmentManagerName = developmentManagerName;
	}
	/**
	 * 获取：
	 */
	public String getDevelopmentManagerName() {
		return developmentManagerName;
	}
	/**
	 * 设置：
	 */
	public void setDevelopmentManagerId(long developmentManagerId) {
		this.developmentManagerId = developmentManagerId;
	}
	/**
	 * 获取：
	 */
	public long getDevelopmentManagerId() {
		return developmentManagerId;
	}
	/**
	 * 设置：
	 */
	public void setStartSaleDate(String startSaleDate) {
		this.startSaleDate = startSaleDate;
	}
	/**
	 * 获取：
	 */
	public String getStartSaleDate() {
		return startSaleDate;
	}
	/**
	 * 设置：
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 获取：
	 */
	public String getProductType() {
		return productType;
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
