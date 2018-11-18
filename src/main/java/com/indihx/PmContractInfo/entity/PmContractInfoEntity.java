package com.indihx.PmContractInfo.entity;



import com.indihx.BaseEntity;
import com.indihx.PmPaymentPoint.entity.PmPaymentPointEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-17 11:27:29
 */
public class PmContractInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */

private long contractId;
	/**
	 * $column.comments
	 */

private String contractCode;
	/**
	 * $column.comments
	 */

private String contractName;
	/**
	 * $column.comments
	 */

private String contractAmount;
	/**
	 * $column.comments
	 */

private long taxRate;
	/**
	 * $column.comments
	 */

private long afterTaxContractAmount;
	/**
	 * $column.comments
	 */

private String contractStartTime;
	/**
	 * $column.comments
	 */

private String contractEndTime;
	/**
	 * $column.comments
	 */

private String signContractDate;
	/**
	 * $column.comments
	 */

private String isAgree;
	/**
	 * $column.comments
	 */

private String yearNumer;
	/**
	 * $column.comments
	 */

private String sellDeptName;
	/**
	 * $column.comments
	 */

private String custManagerName;
	/**
	 * $column.comments
	 */

private String oaFlowCode;
	/**
	 * $column.comments
	 */

private String companyCode;
	/**
	 * $column.comments
	 */

	private long custManagerId;

	private long sellDeptId;

private long custId;
	/**
	 * $column.comments
	 */

private String custSapCode;
	/**
	 * $column.comments
	 */

private String custName;
	/**
	 * $column.comments
	 */

private String remark;
	/**
	 * $column.comments
	 */

private String isDelete;
	/**
	 * $column.comments
	 */
	private List<PmPaymentPointEntity> paymentPoint;


	public long getContractId() {
		return contractId;
	}

	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	public long getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(long taxRate) {
		this.taxRate = taxRate;
	}

	public long getAfterTaxContractAmount() {
		return afterTaxContractAmount;
	}

	public void setAfterTaxContractAmount(long afterTaxContractAmount) {
		this.afterTaxContractAmount = afterTaxContractAmount;
	}

	public String getContractStartTime() {
		return contractStartTime;
	}

	public void setContractStartTime(String contractStartTime) {
		this.contractStartTime = contractStartTime;
	}

	public String getContractEndTime() {
		return contractEndTime;
	}

	public void setContractEndTime(String contractEndTime) {
		this.contractEndTime = contractEndTime;
	}

	public String getSignContractDate() {
		return signContractDate;
	}

	public void setSignContractDate(String signContractDate) {
		this.signContractDate = signContractDate;
	}

	public String getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(String isAgree) {
		this.isAgree = isAgree;
	}

	public String getYearNumer() {
		return yearNumer;
	}

	public void setYearNumer(String yearNumer) {
		this.yearNumer = yearNumer;
	}

	public String getSellDeptName() {
		return sellDeptName;
	}

	public void setSellDeptName(String sellDeptName) {
		this.sellDeptName = sellDeptName;
	}

	public String getCustManagerName() {
		return custManagerName;
	}

	public void setCustManagerName(String custManagerName) {
		this.custManagerName = custManagerName;
	}

	public String getOaFlowCode() {
		return oaFlowCode;
	}

	public void setOaFlowCode(String oaFlowCode) {
		this.oaFlowCode = oaFlowCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getCustSapCode() {
		return custSapCode;
	}

	public void setCustSapCode(String custSapCode) {
		this.custSapCode = custSapCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public long getCustManagerId() {
		return custManagerId;
	}

	public void setCustManagerId(long custManagerId) {
		this.custManagerId = custManagerId;
	}

	public long getSellDeptId() {
		return sellDeptId;
	}

	public void setSellDeptId(long sellDeptId) {
		this.sellDeptId = sellDeptId;
	}

	public List<PmPaymentPointEntity> getPaymentPoint() {
		return paymentPoint;
	}

	public void setPaymentPoint(List<PmPaymentPointEntity> paymentPoint) {
		this.paymentPoint = paymentPoint;
	}
}
