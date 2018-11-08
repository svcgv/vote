package com.indihx.PmProjectIncommingInfo.entity;



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
public class PmProjectIncommingInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long receiveId;
	/**
	 * 
	 */

private long projectId;
	/**
	 * 
	 */

private String wbs;
	/**
	 * 
	 */

private String projectName;
	/**
	 * 
	 */

private long receiveMount;
	/**
	 * 
	 */

private String receiveDate;
	/**
	 * 
	 */

private long creator;
	/**
	 * 
	 */

private String createTime;
	/**
	 * 
	 */

private String receivables;
	/**
	 * 
	 */

private String isDelete;

	/**
	 * 设置：
	 */
	public void setReceiveId(long receiveId) {
		this.receiveId = receiveId;
	}
	/**
	 * 获取：
	 */
	public long getReceiveId() {
		return receiveId;
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
	public void setReceiveMount(long receiveMount) {
		this.receiveMount = receiveMount;
	}
	/**
	 * 获取：
	 */
	public long getReceiveMount() {
		return receiveMount;
	}
	/**
	 * 设置：
	 */
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	/**
	 * 获取：
	 */
	public String getReceiveDate() {
		return receiveDate;
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
	public void setReceivables(String receivables) {
		this.receivables = receivables;
	}
	/**
	 * 获取：
	 */
	public String getReceivables() {
		return receivables;
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
