package com.indihx.PmProjectProblem.entity;



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
public class PmProjectProblemEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long problemId;
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

private String problemType;
	/**
	 * 
	 */

private String problemDetail;
	/**
	 * 
	 */

private long creatorId;
	/**
	 * 
	 */

private String creatorName;
	/**
	 * 
	 */

private String createTime;
	/**
	 * 
	 */

private String problemStatus;
	/**
	 * 
	 */

private String isDelete;

	/**
	 * 设置：
	 */
	public void setProblemId(long problemId) {
		this.problemId = problemId;
	}
	/**
	 * 获取：
	 */
	public long getProblemId() {
		return problemId;
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
	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}
	/**
	 * 获取：
	 */
	public String getProblemType() {
		return problemType;
	}
	/**
	 * 设置：
	 */
	public void setProblemDetail(String problemDetail) {
		this.problemDetail = problemDetail;
	}
	/**
	 * 获取：
	 */
	public String getProblemDetail() {
		return problemDetail;
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
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	/**
	 * 获取：
	 */
	public String getCreatorName() {
		return creatorName;
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
	public void setProblemStatus(String problemStatus) {
		this.problemStatus = problemStatus;
	}
	/**
	 * 获取：
	 */
	public String getProblemStatus() {
		return problemStatus;
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
}
