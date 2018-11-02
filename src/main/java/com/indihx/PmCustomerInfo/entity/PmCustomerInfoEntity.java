package com.indihx.PmCustomerInfo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-31 18:24:22
 */
public class PmCustomerInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 分页查询开始数
	private int startNum;
	// 分页查询每页条数
	private int totalNum;
	/**
	 * 
	 */
	private long creatorId;

	private long custId;
	/**
	 * 
	 */

	private String custCnName;
	/**
	 * 
	 */

	private String custCode;
	/**
	 * 
	 */

	private String country;
	/**
	 * 
	 */

	private String sapCode;
	/**
	 * 
	 */

	private String enName;
	/**
	 * 
	 */

	private String custPatTaxesCode;
	/**
	 * 
	 */

	private String custType;
	/**
	 * 
	 */

	private String address;
	/**
	 * 
	 */

	private String cashManagementGroup;
	/**
	 * 
	 */

	private String payCondition;
	/**
	 * 
	 */

	private String tradeCode;
	/**
	 * 
	 */

	private String regionalMarket;
	/**
	 * 
	 */

	private String mainBusiness;
	/**
	 * 
	 */

	private String area;
	/**
	 * 
	 */

	private String custTrade;
	/**
	 * 
	 */

	private String payCycle;
	/**
	 * 
	 */

	private String isUseful;
	/**
	 * 
	 */

	private String groupCompany;
	/**
	 * 
	 */

	private String bgVisiable;
	/**
	 * 
	 */

	private String companyCode;
	/**
	 * 
	 */

	private String companyFuncCode;
	/**
	 * 
	 */

	private String createTime;
	/**
	 * 
	 */

	private long addUserId;
	/**
	 * 
	 */

	private String addTime;
	/**
	 * 
	 */

	private long modifier;
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
	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * 获取：
	 */
	public long getCustId() {
		return custId;
	}

	/**
	 * 设置：
	 */
	public void setCustCnName(String custCnName) {
		this.custCnName = custCnName;
	}

	/**
	 * 获取：
	 */
	public String getCustCnName() {
		return custCnName;
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
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 获取：
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 设置：
	 */
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	/**
	 * 获取：
	 */
	public String getSapCode() {
		return sapCode;
	}

	/**
	 * 设置：
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}

	/**
	 * 获取：
	 */
	public String getEnName() {
		return enName;
	}

	/**
	 * 设置：
	 */
	public void setCustPatTaxesCode(String custPatTaxesCode) {
		this.custPatTaxesCode = custPatTaxesCode;
	}

	/**
	 * 获取：
	 */
	public String getCustPatTaxesCode() {
		return custPatTaxesCode;
	}

	/**
	 * 设置：
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * 获取：
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置：
	 */
	public void setCashManagementGroup(String cashManagementGroup) {
		this.cashManagementGroup = cashManagementGroup;
	}

	/**
	 * 获取：
	 */
	public String getCashManagementGroup() {
		return cashManagementGroup;
	}

	/**
	 * 设置：
	 */
	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}

	/**
	 * 获取：
	 */
	public String getPayCondition() {
		return payCondition;
	}

	/**
	 * 设置：
	 */
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	/**
	 * 获取：
	 */
	public String getTradeCode() {
		return tradeCode;
	}

	/**
	 * 设置：
	 */
	public void setRegionalMarket(String regionalMarket) {
		this.regionalMarket = regionalMarket;
	}

	/**
	 * 获取：
	 */
	public String getRegionalMarket() {
		return regionalMarket;
	}

	/**
	 * 设置：
	 */
	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	/**
	 * 获取：
	 */
	public String getMainBusiness() {
		return mainBusiness;
	}

	/**
	 * 设置：
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 获取：
	 */
	public String getArea() {
		return area;
	}

	/**
	 * 设置：
	 */
	public void setCustTrade(String custTrade) {
		this.custTrade = custTrade;
	}

	/**
	 * 获取：
	 */
	public String getCustTrade() {
		return custTrade;
	}

	/**
	 * 设置：
	 */
	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}

	/**
	 * 获取：
	 */
	public String getPayCycle() {
		return payCycle;
	}

	/**
	 * 设置：
	 */
	public void setIsUseful(String isUseful) {
		this.isUseful = isUseful;
	}

	/**
	 * 获取：
	 */
	public String getIsUseful() {
		return isUseful;
	}

	/**
	 * 设置：
	 */
	public void setGroupCompany(String groupCompany) {
		this.groupCompany = groupCompany;
	}

	/**
	 * 获取：
	 */
	public String getGroupCompany() {
		return groupCompany;
	}

	/**
	 * 设置：
	 */
	public void setBgVisiable(String bgVisiable) {
		this.bgVisiable = bgVisiable;
	}

	/**
	 * 获取：
	 */
	public String getBgVisiable() {
		return bgVisiable;
	}

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
	public void setCompanyFuncCode(String companyFuncCode) {
		this.companyFuncCode = companyFuncCode;
	}

	/**
	 * 获取：
	 */
	public String getCompanyFuncCode() {
		return companyFuncCode;
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
	public void setAddUserId(long addUserId) {
		this.addUserId = addUserId;
	}

	/**
	 * 获取：
	 */
	public long getAddUserId() {
		return addUserId;
	}

	/**
	 * 设置：
	 */
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	/**
	 * 获取：
	 */
	public String getAddTime() {
		return addTime;
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

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

}
