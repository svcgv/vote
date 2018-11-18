package com.indihx.PmYearBudgetProduct.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-18 10:35:35
 */
public class PmYearBudgetProductEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */

private long paymentId;
	/**
	 * $column.comments
	 */

private long productId;
	/**
	 * $column.comments
	 */

private String yearBudgetCode;

	/**
	 * 设置：${column.comments}
	 */
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getPaymentId() {
		return paymentId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getProductId() {
		return productId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setYearBudgetCode(String yearBudgetCode) {
		this.yearBudgetCode = yearBudgetCode;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getYearBudgetCode() {
		return yearBudgetCode;
	}
}
