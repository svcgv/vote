package com.indihx.PmConfirmBid.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-06 19:51:55
 */
public class PmConfirmBidEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long bidId;
	/**
	 * 
	 */

private String bidName;
	/**
	 * 
	 */

private long firstBidAmount;
	/**
	 * 
	 */

private long predictAmount;
	/**
	 * 
	 */

private long predictCost;
	/**
	 * 
	 */

private long predictProfitRate;
	/**
	 * 
	 */

private String predictPeriod;
	/**
	 * 
	 */

private long custId;
	/**
	 * 
	 */

private String custCnName;
	/**
	 * 
	 */

private String custSapCode;
	/**
	 * 
	 */

private long constructionDeptId;
	/**
	 * 
	 */

private String constructionDeptName;
	/**
	 * 
	 */

private long sellDeptId;
	/**
	 * 
	 */

private String sellDeptName;
	/**
	 * 
	 */

private long custManagerId;
	/**
	 * 
	 */

private String custManagerName;
	/**
	 * 
	 */

private String isWorkAreaExplicit;
	/**
	 * 
	 */

private String isChecked;
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
	 * 
	 */

private String paymentPoint;

	/**
	 * 设置：
	 */
	public void setBidId(long bidId) {
		this.bidId = bidId;
	}
	/**
	 * 获取：
	 */
	public long getBidId() {
		return bidId;
	}
	/**
	 * 设置：
	 */
	public void setBidName(String bidName) {
		this.bidName = bidName;
	}
	/**
	 * 获取：
	 */
	public String getBidName() {
		return bidName;
	}
	/**
	 * 设置：
	 */
	public void setFirstBidAmount(long firstBidAmount) {
		this.firstBidAmount = firstBidAmount;
	}
	/**
	 * 获取：
	 */
	public long getFirstBidAmount() {
		return firstBidAmount;
	}
	/**
	 * 设置：
	 */
	public void setPredictAmount(long predictAmount) {
		this.predictAmount = predictAmount;
	}
	/**
	 * 获取：
	 */
	public long getPredictAmount() {
		return predictAmount;
	}
	/**
	 * 设置：
	 */
	public void setPredictCost(long predictCost) {
		this.predictCost = predictCost;
	}
	/**
	 * 获取：
	 */
	public long getPredictCost() {
		return predictCost;
	}
	/**
	 * 设置：
	 */
	public void setPredictProfitRate(long predictProfitRate) {
		this.predictProfitRate = predictProfitRate;
	}
	/**
	 * 获取：
	 */
	public long getPredictProfitRate() {
		return predictProfitRate;
	}
	/**
	 * 设置：
	 */
	public void setPredictPeriod(String predictPeriod) {
		this.predictPeriod = predictPeriod;
	}
	/**
	 * 获取：
	 */
	public String getPredictPeriod() {
		return predictPeriod;
	}
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
	public void setConstructionDeptId(long constructionDeptId) {
		this.constructionDeptId = constructionDeptId;
	}
	/**
	 * 获取：
	 */
	public long getConstructionDeptId() {
		return constructionDeptId;
	}
	/**
	 * 设置：
	 */
	public void setConstructionDeptName(String constructionDeptName) {
		this.constructionDeptName = constructionDeptName;
	}
	/**
	 * 获取：
	 */
	public String getConstructionDeptName() {
		return constructionDeptName;
	}
	/**
	 * 设置：
	 */
	public void setSellDeptId(long sellDeptId) {
		this.sellDeptId = sellDeptId;
	}
	/**
	 * 获取：
	 */
	public long getSellDeptId() {
		return sellDeptId;
	}
	/**
	 * 设置：
	 */
	public void setSellDeptName(String sellDeptName) {
		this.sellDeptName = sellDeptName;
	}
	/**
	 * 获取：
	 */
	public String getSellDeptName() {
		return sellDeptName;
	}
	/**
	 * 设置：
	 */
	public void setCustManagerId(long custManagerId) {
		this.custManagerId = custManagerId;
	}
	/**
	 * 获取：
	 */
	public long getCustManagerId() {
		return custManagerId;
	}
	/**
	 * 设置：
	 */
	public void setCustManagerName(String custManagerName) {
		this.custManagerName = custManagerName;
	}
	/**
	 * 获取：
	 */
	public String getCustManagerName() {
		return custManagerName;
	}
	/**
	 * 设置：
	 */
	public void setIsWorkAreaExplicit(String isWorkAreaExplicit) {
		this.isWorkAreaExplicit = isWorkAreaExplicit;
	}
	/**
	 * 获取：
	 */
	public String getIsWorkAreaExplicit() {
		return isWorkAreaExplicit;
	}
	/**
	 * 设置：
	 */
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
	/**
	 * 获取：
	 */
	public String getIsChecked() {
		return isChecked;
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
	/**
	 * 设置：
	 */
	public void setPaymentPoint(String paymentPoint) {
		this.paymentPoint = paymentPoint;
	}
	/**
	 * 获取：
	 */
	public String getPaymentPoint() {
		return paymentPoint;
	}
}
