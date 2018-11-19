package com.indihx.PmProjectMilestoneInfo.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-07 20:18:23
 */
public class PmProjectMilestoneInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long milestoneId;
	/**
	 * 
	 */

private long projectId;
	/**
	 * 
	 */

private String projectName;
	/**
	 * 
	 */

private String wbs;
	/**
	 * 
	 */

private String mileDate;
	/**
	 * 
	 */

private String mileDescript;

private String mileType;
	/**
	 * 
	 */

private String finishStatus;
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

private String isDelete;

	/**
	 * 设置：
	 */
	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}
	/**
	 * 获取：
	 */
	public long getMilestoneId() {
		return milestoneId;
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
	public void setMileDate(String mileDate) {
		this.mileDate = mileDate;
	}
	/**
	 * 获取：
	 */
	public String getMileDate() {
		return mileDate;
	}
	/**
	 * 设置：
	 */
	public void setMileDescript(String mileDescript) {
		this.mileDescript = mileDescript;
	}
	/**
	 * 获取：
	 */
	public String getMileDescript() {
		return mileDescript;
	}
	/**
	 * 设置：
	 */
	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}
	/**
	 * 获取：
	 */
	public String getFinishStatus() {
		return finishStatus;
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
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：
	 */
	public String getIsDelete() {
		return isDelete;
	}
	public String getMileType() {
		return mileType;
	}
	public void setMileType(String mileType) {
		this.mileType = mileType;
	}
}
