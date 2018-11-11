package com.indihx.PmProjectInfo.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-09 10:04:00
 */
public class PmProjectInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

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

private long buildDeptId;
	/**
	 * 
	 */

private String buildManagerName;
	/**
	 * 
	 */

private long buildManagerId;
	/**
	 * 
	 */

private String sellDeptName;
	/**
	 * 
	 */

private long sellDeptId;
	/**
	 * 
	 */

private String sellManagerName;
	/**
	 * 
	 */

private long sellManagerId;
	/**
	 * 
	 */

private long technicalDirectorId;
	/**
	 * 
	 */

private String technicalDirectorName;
	/**
	 * 
	 */

private String createProjectTime;
	/**
	 * 
	 */

private String finishProjectTime;
	/**
	 * 
	 */

private String wbs;
	/**
	 * 
	 */

private String custName;
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

private String currentYearFollow;
	/**
	 * 
	 */

private String isContinue;
	/**
	 * 
	 */

private String projectStatus;
	/**
	 * 
	 */

private String state;
	/**
	 * 
	 */

private String projectType;
	/**
	 * 
	 */

private long predictContractAmount;
	/**
	 * 
	 */

private String profitCenter;
	/**
	 * 
	 */

private String profitCode;
	/**
	 * 
	 */

private String costCenter;
	/**
	 * 
	 */

private String costCode;
	/**
	 * 
	 */

private long profitRate;
	/**
	 * 
	 */

private long profitMount;
	/**
	 * 
	 */

private long workLoad;
	/**
	 * 
	 */

private long currendYearIncomming;
	/**
	 * 
	 */

private long currentYearGrossProfit;
	/**
	 * 
	 */

private long allIncomming;
	/**
	 * 
	 */

private long overFlowReportIncomming;
	/**
	 * 
	 */

private String signContractDate;
	/**
	 * 
	 */

private String isSignedContract;
	/**
	 * 
	 */

private String ftpContractReviewResult;
	/**
	 * 
	 */

private String onlineDate;
	/**
	 * 
	 */

private String isOnline;
	/**
	 * 
	 */

private String onlineReportReviewResult;
	/**
	 * 
	 */

private String acceptDate;
	/**
	 * 
	 */

private String isAccept;
	/**
	 * 
	 */

private String ftpReportReviewResult;
	/**
	 * 
	 */

private String onlineDateLater;
	/**
	 * 
	 */

private String acceptDateLater;
	/**
	 * 
	 */

private String onlineNum;
	/**
	 * 
	 */

private String acceptNum;
	/**
	 * 
	 */

private String isImportant;
	/**
	 * 
	 */

private long budget;
	/**
	 * 
	 */

private long currentYearMount;
	/**
	 * 
	 */

private String innerAcceptDate;
	/**
	 * 
	 */

private long netSalary;
	/**
	 * 
	 */

private long yearSalary;
	/**
	 * 
	 */

private long predictProfitRate;
	/**
	 * 
	 */

private long predictCapitaSalary;
	/**
	 * 
	 */

private long predictCapitaCost;
	/**
	 * 
	 */

private long predictWorkload;
	/**
	 * 
	 */

private long employeeCost;
	/**
	 * 
	 */

private long businessTripCost;
	/**
	 * 
	 */

private long otherCost;
	/**
	 * 
	 */

private long accruedChargesWorkers;
	/**
	 * 
	 */

private long accruedChargesProducts;
	/**
	 * 
	 */

private String remark1;
	/**
	 * 
	 */

private String remark2;
	/**
	 * 
	 */

private String remark3;
	/**
	 * 
	 */

private String isDelete;
	/**
	 * 
	 */

private String createTime;
	/**
	 * 
	 */

private long creator;
	/**
	 * 
	 */

private String modifyTime;
	/**
	 * 
	 */

private long modifier;

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
	public void setBuildDeptId(long buildDeptId) {
		this.buildDeptId = buildDeptId;
	}
	/**
	 * 获取：
	 */
	public long getBuildDeptId() {
		return buildDeptId;
	}
	/**
	 * 设置：
	 */
	public void setBuildManagerName(String buildManagerName) {
		this.buildManagerName = buildManagerName;
	}
	/**
	 * 获取：
	 */
	public String getBuildManagerName() {
		return buildManagerName;
	}
	/**
	 * 设置：
	 */
	public void setBuildManagerId(long buildManagerId) {
		this.buildManagerId = buildManagerId;
	}
	/**
	 * 获取：
	 */
	public long getBuildManagerId() {
		return buildManagerId;
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
	public void setSellManagerName(String sellManagerName) {
		this.sellManagerName = sellManagerName;
	}
	/**
	 * 获取：
	 */
	public String getSellManagerName() {
		return sellManagerName;
	}
	/**
	 * 设置：
	 */
	public void setSellManagerId(long sellManagerId) {
		this.sellManagerId = sellManagerId;
	}
	/**
	 * 获取：
	 */
	public long getSellManagerId() {
		return sellManagerId;
	}
	/**
	 * 设置：
	 */
	public void setTechnicalDirectorId(long technicalDirectorId) {
		this.technicalDirectorId = technicalDirectorId;
	}
	/**
	 * 获取：
	 */
	public long getTechnicalDirectorId() {
		return technicalDirectorId;
	}
	/**
	 * 设置：
	 */
	public void setTechnicalDirectorName(String technicalDirectorName) {
		this.technicalDirectorName = technicalDirectorName;
	}
	/**
	 * 获取：
	 */
	public String getTechnicalDirectorName() {
		return technicalDirectorName;
	}
	/**
	 * 设置：
	 */
	public void setCreateProjectTime(String createProjectTime) {
		this.createProjectTime = createProjectTime;
	}
	/**
	 * 获取：
	 */
	public String getCreateProjectTime() {
		return createProjectTime;
	}
	/**
	 * 设置：
	 */
	public void setFinishProjectTime(String finishProjectTime) {
		this.finishProjectTime = finishProjectTime;
	}
	/**
	 * 获取：
	 */
	public String getFinishProjectTime() {
		return finishProjectTime;
	}
	/**
	 * 设置：
	 */
	public void setWbs(String wbs) {
		this.wbs = wbs;
	}
	/**
	 * 获取：
	 */
	public String getWbs() {
		return wbs;
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
	public void setCurrentYearFollow(String currentYearFollow) {
		this.currentYearFollow = currentYearFollow;
	}
	/**
	 * 获取：
	 */
	public String getCurrentYearFollow() {
		return currentYearFollow;
	}
	/**
	 * 设置：
	 */
	public void setIsContinue(String isContinue) {
		this.isContinue = isContinue;
	}
	/**
	 * 获取：
	 */
	public String getIsContinue() {
		return isContinue;
	}
	/**
	 * 设置：
	 */
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	/**
	 * 获取：
	 */
	public String getProjectStatus() {
		return projectStatus;
	}
	/**
	 * 设置：
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	/**
	 * 获取：
	 */
	public String getProjectType() {
		return projectType;
	}
	/**
	 * 设置：
	 */
	public void setPredictContractAmount(long predictContractAmount) {
		this.predictContractAmount = predictContractAmount;
	}
	/**
	 * 获取：
	 */
	public long getPredictContractAmount() {
		return predictContractAmount;
	}
	/**
	 * 设置：
	 */
	public void setProfitCenter(String profitCenter) {
		this.profitCenter = profitCenter;
	}
	/**
	 * 获取：
	 */
	public String getProfitCenter() {
		return profitCenter;
	}
	/**
	 * 设置：
	 */
	public void setProfitCode(String profitCode) {
		this.profitCode = profitCode;
	}
	/**
	 * 获取：
	 */
	public String getProfitCode() {
		return profitCode;
	}
	/**
	 * 设置：
	 */
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	/**
	 * 获取：
	 */
	public String getCostCenter() {
		return costCenter;
	}
	/**
	 * 设置：
	 */
	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}
	/**
	 * 获取：
	 */
	public String getCostCode() {
		return costCode;
	}
	/**
	 * 设置：
	 */
	public void setProfitRate(long profitRate) {
		this.profitRate = profitRate;
	}
	/**
	 * 获取：
	 */
	public long getProfitRate() {
		return profitRate;
	}
	/**
	 * 设置：
	 */
	public void setProfitMount(long profitMount) {
		this.profitMount = profitMount;
	}
	/**
	 * 获取：
	 */
	public long getProfitMount() {
		return profitMount;
	}
	/**
	 * 设置：
	 */
	public void setWorkLoad(long workLoad) {
		this.workLoad = workLoad;
	}
	/**
	 * 获取：
	 */
	public long getWorkLoad() {
		return workLoad;
	}
	/**
	 * 设置：
	 */
	public void setCurrendYearIncomming(long currendYearIncomming) {
		this.currendYearIncomming = currendYearIncomming;
	}
	/**
	 * 获取：
	 */
	public long getCurrendYearIncomming() {
		return currendYearIncomming;
	}
	/**
	 * 设置：
	 */
	public void setCurrentYearGrossProfit(long currentYearGrossProfit) {
		this.currentYearGrossProfit = currentYearGrossProfit;
	}
	/**
	 * 获取：
	 */
	public long getCurrentYearGrossProfit() {
		return currentYearGrossProfit;
	}
	/**
	 * 设置：
	 */
	public void setAllIncomming(long allIncomming) {
		this.allIncomming = allIncomming;
	}
	/**
	 * 获取：
	 */
	public long getAllIncomming() {
		return allIncomming;
	}
	/**
	 * 设置：
	 */
	public void setOverFlowReportIncomming(long overFlowReportIncomming) {
		this.overFlowReportIncomming = overFlowReportIncomming;
	}
	/**
	 * 获取：
	 */
	public long getOverFlowReportIncomming() {
		return overFlowReportIncomming;
	}
	/**
	 * 设置：
	 */
	public void setSignContractDate(String signContractDate) {
		this.signContractDate = signContractDate;
	}
	/**
	 * 获取：
	 */
	public String getSignContractDate() {
		return signContractDate;
	}
	/**
	 * 设置：
	 */
	public void setIsSignedContract(String isSignedContract) {
		this.isSignedContract = isSignedContract;
	}
	/**
	 * 获取：
	 */
	public String getIsSignedContract() {
		return isSignedContract;
	}
	/**
	 * 设置：
	 */
	public void setFtpContractReviewResult(String ftpContractReviewResult) {
		this.ftpContractReviewResult = ftpContractReviewResult;
	}
	/**
	 * 获取：
	 */
	public String getFtpContractReviewResult() {
		return ftpContractReviewResult;
	}
	/**
	 * 设置：
	 */
	public void setOnlineDate(String onlineDate) {
		this.onlineDate = onlineDate;
	}
	/**
	 * 获取：
	 */
	public String getOnlineDate() {
		return onlineDate;
	}
	/**
	 * 设置：
	 */
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	/**
	 * 获取：
	 */
	public String getIsOnline() {
		return isOnline;
	}
	/**
	 * 设置：
	 */
	public void setOnlineReportReviewResult(String onlineReportReviewResult) {
		this.onlineReportReviewResult = onlineReportReviewResult;
	}
	/**
	 * 获取：
	 */
	public String getOnlineReportReviewResult() {
		return onlineReportReviewResult;
	}
	/**
	 * 设置：
	 */
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	/**
	 * 获取：
	 */
	public String getAcceptDate() {
		return acceptDate;
	}
	/**
	 * 设置：
	 */
	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}
	/**
	 * 获取：
	 */
	public String getIsAccept() {
		return isAccept;
	}
	/**
	 * 设置：
	 */
	public void setFtpReportReviewResult(String ftpReportReviewResult) {
		this.ftpReportReviewResult = ftpReportReviewResult;
	}
	/**
	 * 获取：
	 */
	public String getFtpReportReviewResult() {
		return ftpReportReviewResult;
	}
	/**
	 * 设置：
	 */
	public void setOnlineDateLater(String onlineDateLater) {
		this.onlineDateLater = onlineDateLater;
	}
	/**
	 * 获取：
	 */
	public String getOnlineDateLater() {
		return onlineDateLater;
	}
	/**
	 * 设置：
	 */
	public void setAcceptDateLater(String acceptDateLater) {
		this.acceptDateLater = acceptDateLater;
	}
	/**
	 * 获取：
	 */
	public String getAcceptDateLater() {
		return acceptDateLater;
	}
	/**
	 * 设置：
	 */
	public void setOnlineNum(String onlineNum) {
		this.onlineNum = onlineNum;
	}
	/**
	 * 获取：
	 */
	public String getOnlineNum() {
		return onlineNum;
	}
	/**
	 * 设置：
	 */
	public void setAcceptNum(String acceptNum) {
		this.acceptNum = acceptNum;
	}
	/**
	 * 获取：
	 */
	public String getAcceptNum() {
		return acceptNum;
	}
	/**
	 * 设置：
	 */
	public void setIsImportant(String isImportant) {
		this.isImportant = isImportant;
	}
	/**
	 * 获取：
	 */
	public String getIsImportant() {
		return isImportant;
	}
	/**
	 * 设置：
	 */
	public void setBudget(long budget) {
		this.budget = budget;
	}
	/**
	 * 获取：
	 */
	public long getBudget() {
		return budget;
	}
	/**
	 * 设置：
	 */
	public void setCurrentYearMount(long currentYearMount) {
		this.currentYearMount = currentYearMount;
	}
	/**
	 * 获取：
	 */
	public long getCurrentYearMount() {
		return currentYearMount;
	}
	/**
	 * 设置：
	 */
	public void setInnerAcceptDate(String innerAcceptDate) {
		this.innerAcceptDate = innerAcceptDate;
	}
	/**
	 * 获取：
	 */
	public String getInnerAcceptDate() {
		return innerAcceptDate;
	}
	/**
	 * 设置：
	 */
	public void setNetSalary(long netSalary) {
		this.netSalary = netSalary;
	}
	/**
	 * 获取：
	 */
	public long getNetSalary() {
		return netSalary;
	}
	/**
	 * 设置：
	 */
	public void setYearSalary(long yearSalary) {
		this.yearSalary = yearSalary;
	}
	/**
	 * 获取：
	 */
	public long getYearSalary() {
		return yearSalary;
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
	public void setPredictCapitaSalary(long predictCapitaSalary) {
		this.predictCapitaSalary = predictCapitaSalary;
	}
	/**
	 * 获取：
	 */
	public long getPredictCapitaSalary() {
		return predictCapitaSalary;
	}
	/**
	 * 设置：
	 */
	public void setPredictCapitaCost(long predictCapitaCost) {
		this.predictCapitaCost = predictCapitaCost;
	}
	/**
	 * 获取：
	 */
	public long getPredictCapitaCost() {
		return predictCapitaCost;
	}
	/**
	 * 设置：
	 */
	public void setPredictWorkload(long predictWorkload) {
		this.predictWorkload = predictWorkload;
	}
	/**
	 * 获取：
	 */
	public long getPredictWorkload() {
		return predictWorkload;
	}
	/**
	 * 设置：
	 */
	public void setEmployeeCost(long employeeCost) {
		this.employeeCost = employeeCost;
	}
	/**
	 * 获取：
	 */
	public long getEmployeeCost() {
		return employeeCost;
	}
	/**
	 * 设置：
	 */
	public void setBusinessTripCost(long businessTripCost) {
		this.businessTripCost = businessTripCost;
	}
	/**
	 * 获取：
	 */
	public long getBusinessTripCost() {
		return businessTripCost;
	}
	/**
	 * 设置：
	 */
	public void setOtherCost(long otherCost) {
		this.otherCost = otherCost;
	}
	/**
	 * 获取：
	 */
	public long getOtherCost() {
		return otherCost;
	}
	/**
	 * 设置：
	 */
	public void setAccruedChargesWorkers(long accruedChargesWorkers) {
		this.accruedChargesWorkers = accruedChargesWorkers;
	}
	/**
	 * 获取：
	 */
	public long getAccruedChargesWorkers() {
		return accruedChargesWorkers;
	}
	/**
	 * 设置：
	 */
	public void setAccruedChargesProducts(long accruedChargesProducts) {
		this.accruedChargesProducts = accruedChargesProducts;
	}
	/**
	 * 获取：
	 */
	public long getAccruedChargesProducts() {
		return accruedChargesProducts;
	}
	/**
	 * 设置：
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	/**
	 * 获取：
	 */
	public String getRemark1() {
		return remark1;
	}
	/**
	 * 设置：
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	/**
	 * 获取：
	 */
	public String getRemark2() {
		return remark2;
	}
	/**
	 * 设置：
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	/**
	 * 获取：
	 */
	public String getRemark3() {
		return remark3;
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
	public void setCreator(long creator) {
		this.creator = creator;
	}
	/**
	 * 获取：
	 */
	public long getCreator() {
		return creator;
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
	public void setModifier(long modifier) {
		this.modifier = modifier;
	}
	/**
	 * 获取：
	 */
	public long getModifier() {
		return modifier;
	}
}
