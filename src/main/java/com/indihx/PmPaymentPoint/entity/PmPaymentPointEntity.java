package com.indihx.PmPaymentPoint.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-06 19:33:25
 */
public class PmPaymentPointEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long paymentId;
	/**
	 * 
	 */

private long paymentForeignId;
	/**
	 * 
	 */

private String paymentForeignCode;
	/**
	 * 
	 */

private String paymentType;
	/**
	 * 
	 */

private String paymentDate;
	/**
	 * 
	 */

private long paymentTerm;
	/**
	 * 
	 */

private BigDecimal paymentRate;
	/**
	 * 
	 */

private BigDecimal paymentAmount;
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
	 * 收款/付款编码
	 */
	private String paymentCode;

	/**
	 * 设置：
	 */
	public void setPaymentForeignId(long paymentForeignId) {
		this.paymentForeignId = paymentForeignId;
	}
	/**
	 * 获取：
	 */
	public long getPaymentForeignId() {
		return paymentForeignId;
	}
	/**
	 * 设置：
	 */
	public void setPaymentForeignCode(String paymentForeignCode) {
		this.paymentForeignCode = paymentForeignCode;
	}
	/**
	 * 获取：
	 */
	public String getPaymentForeignCode() {
		return paymentForeignCode;
	}
	/**
	 * 设置：
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * 获取：
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * 设置：
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	/**
	 * 获取：
	 */
	public String getPaymentDate() {
		return paymentDate;
	}
	/**
	 * 设置：
	 */
	public void setPaymentTerm(long paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	/**
	 * 获取：
	 */
	public long getPaymentTerm() {
		return paymentTerm;
	}
	/**
	 * 设置：
	 */
	public void setPaymentRate(BigDecimal paymentRate) {
		this.paymentRate = paymentRate;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getPaymentRate() {
		return paymentRate;
	}
	/**
	 * 设置：
	 */
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
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

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
}
