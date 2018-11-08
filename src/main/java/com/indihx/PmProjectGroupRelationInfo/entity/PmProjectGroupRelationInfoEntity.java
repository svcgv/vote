package com.indihx.PmProjectGroupRelationInfo.entity;



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
public class PmProjectGroupRelationInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long relationship;
	/**
	 * 
	 */

private long projectGroupId;
	/**
	 * 
	 */

private String wbs;
	/**
	 * 
	 */

private long projectId;
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
	 * 
	 */

private String isDelete;

	/**
	 * 设置：
	 */
	public void setRelationship(long relationship) {
		this.relationship = relationship;
	}
	/**
	 * 获取：
	 */
	public long getRelationship() {
		return relationship;
	}
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
