package com.indihx.PmYearBudget.entity;

import java.math.BigDecimal;

import com.indihx.BaseEntity;

/**
 * ${comments}
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-18 10:35:36
 */
public class PmYearBudgetEntity extends BaseEntity  {

	/**
	 * $column.comments
	 */
	private String budgetYear;

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

	private String revenueSource;
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
	private String poSow;
	private String productNames;
	private String productIds;
	private String[] productIdArr;
	private String[] productNameArr;



	private String productList;

	private BigDecimal taxes;

	private BigDecimal taxRate;

	private BigDecimal contractMoney;

	private BigDecimal manyYearRev;

	private BigDecimal curYearRev;

	private BigDecimal lastRev;

	private BigDecimal afterTax;

	private String revRecognitionMethod;

	private String isChoseProduct;


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

	private String currency;
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
	public BigDecimal getAfterTax() {
		return this.afterTax;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetApr() {
		return this.budgetApr;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetAug() {
		return this.budgetAug;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetDec() {
		return this.budgetDec;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetFeb() {
		return this.budgetFeb;
	}

	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetJan() {
		return this.budgetJan;
	}

	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetJul() {
		return this.budgetJul;
	}

	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetJun() {
		return this.budgetJun;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetMar() {
		return this.budgetMar;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetMay() {
		return this.budgetMay;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetNov() {
		return this.budgetNov;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetOct() {
		return this.budgetOct;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetSep() {
		return this.budgetSep;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getBudgetSum() {
		return this.budgetSum;
	}

	public String getBudgetYear() {
		return this.budgetYear;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getCompanyCode() {
		return this.companyCode;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getCompanyEntityName() {
		return this.companyEntityName;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getContractCode() {
		return this.contractCode;
	}

	/**
	 * 获取：${column.comments}
	 */
	public long getContractId() {
		return this.contractId;
	}

	public BigDecimal getContractMoney() {
		return this.contractMoney;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getContractName() {
		return this.contractName;
	}

	/**
	 * 获取：${column.comments}
	 */
	@Override
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 获取：${column.comments}
	 */
	@Override
	public long getCreatorId() {
		return this.creatorId;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getCurency() {
		return this.currency;
	}

	public String getCurrency() {
		return this.currency;
	}

	public BigDecimal getCurYearRev() {
		return this.curYearRev;
	}

	/**
	 * 获取：${column.comments}
	 */
	public long getCustManagerId() {
		return this.custManagerId;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getCustManagerName() {
		return this.custManagerName;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getCustName() {
		return this.custName;
	}

	/**
	 * 获取：${column.comments}
	 */
	public long getGrossProfitRate() {
		return this.grossProfitRate;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getIncommingType() {
		return this.incommingType;
	}

	public String getIsChoseProduct() {
		return this.isChoseProduct;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getIsDelete() {
		return this.isDelete;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getIsNewCust() {
		return this.isNewCust;
	}

	public BigDecimal getLastRev() {
		return this.lastRev;
	}

	public BigDecimal getManyYearRev() {
		return this.manyYearRev;
	}

	/**
	 * 获取：${column.comments}
	 */
	@Override
	public long getModifier() {
		return this.modifier;
	}

	/**
	 * 获取：${column.comments}
	 */
	@Override
	public String getModifyTime() {
		return this.modifyTime;
	}

	public String getPoSow() {
		return this.poSow;
	}

	public String[] getProductIdArr() {
		return this.productIdArr;
	}

	public String getProductIds() {
		return this.productIds;
	}

	public String getProductList() {
		return this.productList;
	}

	public String[] getProductNameArr() {
		return this.productNameArr;
	}

	public String getProductNames() {
		return this.productNames;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getProjectType() {
		return this.projectType;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getRegion() {
		return this.region;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getRemark() {
		return this.remark;
	}

	public String getRevenueSource() {
		return this.revenueSource;
	}

	/**
	 * 获取：${column.comments}
	 */

	public String getReviewStatus() {
		return this.reviewStatus;
	}

	/**
	 * 获取：${column.comments}
	 */

	public String getRevRecognitionMethod() {
		return this.revRecognitionMethod;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getSapCode() {
		return this.sapCode;
	}

	public BigDecimal getTaxes() {
		return this.taxes;
	}

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getTaxType() {
		return this.taxType;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getWbs() {
		return this.wbs;
	}

	/**
	 * 获取：${column.comments}
	 */
	public String getYearBudgetCode() {
		return this.yearBudgetCode;
	}

	public void setAfterTax(BigDecimal afterTax) {
		this.afterTax = afterTax;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetApr(long budgetApr) {
		this.budgetApr = budgetApr;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetAug(long budgetAug) {
		this.budgetAug = budgetAug;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetDec(long budgetDec) {
		this.budgetDec = budgetDec;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetFeb(long budgetFeb) {
		this.budgetFeb = budgetFeb;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetJan(long budgetJan) {
		this.budgetJan = budgetJan;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetJul(long budgetJul) {
		this.budgetJul = budgetJul;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetJun(long budgetJun) {
		this.budgetJun = budgetJun;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetMar(long budgetMar) {
		this.budgetMar = budgetMar;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetMay(long budgetMay) {
		this.budgetMay = budgetMay;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetNov(long budgetNov) {
		this.budgetNov = budgetNov;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetOct(long budgetOct) {
		this.budgetOct = budgetOct;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetSep(long budgetSep) {
		this.budgetSep = budgetSep;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setBudgetSum(long budgetSum) {
		this.budgetSum = budgetSum;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setCompanyEntityName(String companyEntityName) {
		this.companyEntityName = companyEntityName;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	public void setContractMoney(BigDecimal contractMoney) {
		this.contractMoney = contractMoney;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	/**
	 * 设置：${column.comments}
	 */
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置：${column.comments}
	 */
	@Override
	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setCurency(String currency) {
		this.currency = currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setCurYearRev(BigDecimal curYearRev) {
		this.curYearRev = curYearRev;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setCustManagerId(long custManagerId) {
		this.custManagerId = custManagerId;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setCustManagerName(String custManagerName) {
		this.custManagerName = custManagerName;
	}

	/**
	 * 设置：${column.comments}
	 */

	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setGrossProfitRate(long grossProfitRate) {
		this.grossProfitRate = grossProfitRate;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setIncommingType(String incommingType) {
		this.incommingType = incommingType;
	}

	public void setIsChoseProduct(String isChoseProduct) {
		this.isChoseProduct = isChoseProduct;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setIsNewCust(String isNewCust) {
		this.isNewCust = isNewCust;
	}

	public void setLastRev(BigDecimal lastRev) {
		this.lastRev = lastRev;
	}

	public void setManyYearRev(BigDecimal manyYearRev) {
		this.manyYearRev = manyYearRev;
	}

	/**
	 * 设置：${column.comments}
	 */
	@Override
	public void setModifier(long modifier) {
		this.modifier = modifier;
	}

	/**
	 * 设置：${column.comments}
	 */
	@Override
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public void setPoSow(String poSow) {
		this.poSow = poSow;
	}

	public void setProductIdArr(String[] productIdArr) {
		this.productIdArr = productIdArr;
	}

	public void setProductIds(String productIds) {
		if (productIds != null) {
			this.productIdArr=productIds.split(",");
		}
		this.productIds = productIds;
	}

	public void setProductList(String productList) {
		this.productList = productList;
	}

	public void setProductNameArr(String[] productNameArr) {
		this.productNameArr = productNameArr;
	}

	public void setProductNames(String productNames) {
		if (productNames != null) {
			this.productNameArr=productNames.split(",");
		}
		this.productNames = productNames;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setRevenueSource(String revenueSource) {
		this.revenueSource = revenueSource;
	}

	/**
	 * 设置：${column.comments}
	 */


	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public void setRevRecognitionMethod(String revRecognitionMethod) {
		this.revRecognitionMethod = revRecognitionMethod;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setWbs(String wbs) {
		this.wbs = wbs;
	}

	/**
	 * 设置：${column.comments}
	 */
	public void setYearBudgetCode(String yearBudgetCode) {
		this.yearBudgetCode = yearBudgetCode;
	}
}
