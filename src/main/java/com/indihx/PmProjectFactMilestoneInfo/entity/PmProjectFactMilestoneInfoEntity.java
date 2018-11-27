package com.indihx.PmProjectFactMilestoneInfo.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-26 16:50:57
 */
public class PmProjectFactMilestoneInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */

private long milestoneId;
	/**
	 * $column.comments
	 */

private long projectId;
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

private String mileDate;
	/**
	 * $column.comments
	 */

private String mileDescript;
	/**
	 * $column.comments
	 */

private String finishStatus;
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

private String isDelete;

	/**
	 * 设置：${column.comments}
	 */
	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getMilestoneId() {
		return milestoneId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getProjectId() {
		return projectId;
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
	public void setMileDate(String mileDate) {
		this.mileDate = mileDate;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getMileDate() {
		return mileDate;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setMileDescript(String mileDescript) {
		this.mileDescript = mileDescript;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getMileDescript() {
		return mileDescript;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getFinishStatus() {
		return finishStatus;
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
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getIsDelete() {
		return isDelete;
	}
}
