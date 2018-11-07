package com.indihx.PmProjectGroupInfo.entity;



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
public class PmProjectGroupInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long projectGroupId;
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

private String projectGroupName;
	/**
	 * 
	 */

private String groupManagerCode;
	/**
	 * 
	 */

private long groupManagerId;
	/**
	 * 
	 */

private long groupCreatorId;
	/**
	 * 
	 */

private String groupCreatorName;
	/**
	 * 
	 */

private String groupCreateTime;
	/**
	 * 
	 */

private String groupStatus;
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
	 * 设置：
	 */
	public void setProjectGroupId(long projectGroupId) {
		this.projectGroupId = projectGroupId;
	}
	/**
	 * 获取：
	 */
	public long getProjectGroupId() {
		return projectGroupId;
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
	public void setProjectGroupName(String projectGroupName) {
		this.projectGroupName = projectGroupName;
	}
	/**
	 * 获取：
	 */
	public String getProjectGroupName() {
		return projectGroupName;
	}
	/**
	 * 设置：
	 */
	public void setGroupManagerCode(String groupManagerCode) {
		this.groupManagerCode = groupManagerCode;
	}
	/**
	 * 获取：
	 */
	public String getGroupManagerCode() {
		return groupManagerCode;
	}
	/**
	 * 设置：
	 */
	public void setGroupManagerId(long groupManagerId) {
		this.groupManagerId = groupManagerId;
	}
	/**
	 * 获取：
	 */
	public long getGroupManagerId() {
		return groupManagerId;
	}
	/**
	 * 设置：
	 */
	public void setGroupCreatorId(long groupCreatorId) {
		this.groupCreatorId = groupCreatorId;
	}
	/**
	 * 获取：
	 */
	public long getGroupCreatorId() {
		return groupCreatorId;
	}
	/**
	 * 设置：
	 */
	public void setGroupCreatorName(String groupCreatorName) {
		this.groupCreatorName = groupCreatorName;
	}
	/**
	 * 获取：
	 */
	public String getGroupCreatorName() {
		return groupCreatorName;
	}
	/**
	 * 设置：
	 */
	public void setGroupCreateTime(String groupCreateTime) {
		this.groupCreateTime = groupCreateTime;
	}
	/**
	 * 获取：
	 */
	public String getGroupCreateTime() {
		return groupCreateTime;
	}
	/**
	 * 设置：
	 */
	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}
	/**
	 * 获取：
	 */
	public String getGroupStatus() {
		return groupStatus;
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
}
