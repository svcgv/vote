package com.indihx.PmYearBudget.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-18 10:35:36
 */
public class PmYearBudgetEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */

private String yearBudgetCode;
private String reviewStatus;
	/**
	 * $column.comments
	 */

private String isNewCust;
	/**
	 * $column.comments
	 */

private String custName;
	/**
	 * $column.comments
	 */

private String sapCode;
	/**
	 * $column.comments
	 */

private String projectName;
	/**
	 * $column.comments
	 */

private String wbs;
	/**
	 * $column.comments
	 */

private String projectType;
	/**
	 * $column.comments
	 */

private String revebueSource;
	/**
	 * $column.comments
	 */

private String companyEntityName;
	/**
	 * $column.comments
	 */

private String companyCode;
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

private String revenueSource;
	/**
	 * $column.comments
	 */

private long custManagerId;
	/**
	 * $column.comments
	 */

private String custManagerName;
	/**
	 * $column.comments
	 */

private String taxType;
	/**
	 * $column.comments
	 */

private String incommingType;
	/**
	 * $column.comments
	 */

private String region;
	/**
	 * $column.comments
	 */

private String curency;
	/**
	 * $column.comments
	 */

private long grossProfitRate;
	/**
	 * $column.comments
	 */

private long budgetJan;
	/**
	 * $column.comments
	 */

private long budgetFeb;
	/**
	 * $column.comments
	 */

private long budgetMar;
	/**
	 * $column.comments
	 */

private long budgetApr;
	/**
	 * $column.comments
	 */

private long budgetMay;
	/**
	 * $column.comments
	 */

private long budgetJun;
	/**
	 * $column.comments
	 */

private long budgetJul;
	/**
	 * $column.comments
	 */

private long budgetAug;
	/**
	 * $column.comments
	 */

private long budgetSep;
	/**
	 * $column.comments
	 */

private long budgetOct;
	/**
	 * $column.comments
	 */

private long budgetNov;
	/**
	 * $column.comments
	 */

private long budgetDec;
	/**
	 * $column.comments
	 */

private long budgetSum;
	/**
	 * $column.comments
	 */

private long creatorId;
	/**
	 * $column.comments
	 */

private String createTime;
	/**
	 * $column.comments
	 */

private long modifier;
	/**
	 * $column.comments
	 */

private String modifyTime;
	/**
	 * $column.comments
	 */

private String isDelete;
	/**
	 * $column.comments
	 */

private String remark;

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
	/**
	 * 设置：${column.comments}
	 */
	public void setIsNewCust(String isNewCust) {
		this.isNewCust = isNewCust;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getIsNewCust() {
		return isNewCust;
	}
	/**
	 * 设置：${column.comments}
	 */
	
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSapCode() {
		return sapCode;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setWbs(String wbs) {
		this.wbs = wbs;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getWbs() {
		return wbs;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getProjectType() {
		return projectType;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setRevebueSource(String revebueSource) {
		this.revebueSource = revebueSource;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getRevebueSource() {
		return revebueSource;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setCompanyEntityName(String companyEntityName) {
		this.companyEntityName = companyEntityName;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getCompanyEntityName() {
		return companyEntityName;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setContractId(long contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getContractId() {
		return contractId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getContractCode() {
		return contractCode;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setRevenueSource(String revenueSource) {
		this.revenueSource = revenueSource;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getRevenueSource() {
		return revenueSource;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setCustManagerId(long custManagerId) {
		this.custManagerId = custManagerId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getCustManagerId() {
		return custManagerId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setCustManagerName(String custManagerName) {
		this.custManagerName = custManagerName;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getCustManagerName() {
		return custManagerName;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getTaxType() {
		return taxType;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setIncommingType(String incommingType) {
		this.incommingType = incommingType;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getIncommingType() {
		return incommingType;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setCurency(String curency) {
		this.curency = curency;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getCurency() {
		return curency;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setGrossProfitRate(long grossProfitRate) {
		this.grossProfitRate = grossProfitRate;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getGrossProfitRate() {
		return grossProfitRate;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetJan(long budgetJan) {
		this.budgetJan = budgetJan;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetJan() {
		return budgetJan;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetFeb(long budgetFeb) {
		this.budgetFeb = budgetFeb;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetFeb() {
		return budgetFeb;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetMar(long budgetMar) {
		this.budgetMar = budgetMar;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetMar() {
		return budgetMar;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetApr(long budgetApr) {
		this.budgetApr = budgetApr;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetApr() {
		return budgetApr;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetMay(long budgetMay) {
		this.budgetMay = budgetMay;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetMay() {
		return budgetMay;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetJun(long budgetJun) {
		this.budgetJun = budgetJun;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetJun() {
		return budgetJun;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetJul(long budgetJul) {
		this.budgetJul = budgetJul;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetJul() {
		return budgetJul;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetAug(long budgetAug) {
		this.budgetAug = budgetAug;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetAug() {
		return budgetAug;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetSep(long budgetSep) {
		this.budgetSep = budgetSep;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetSep() {
		return budgetSep;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetOct(long budgetOct) {
		this.budgetOct = budgetOct;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetOct() {
		return budgetOct;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetNov(long budgetNov) {
		this.budgetNov = budgetNov;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetNov() {
		return budgetNov;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetDec(long budgetDec) {
		this.budgetDec = budgetDec;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetDec() {
		return budgetDec;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetSum(long budgetSum) {
		this.budgetSum = budgetSum;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetSum() {
		return budgetSum;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getCreatorId() {
		return creatorId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setModifier(long modifier) {
		this.modifier = modifier;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getModifier() {
		return modifier;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getModifyTime() {
		return modifyTime;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getRemark() {
		return remark;
	}
}
