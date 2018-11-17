package com.indihx.PmContractProjectRelation.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-17 11:28:34
 */
public class PmContractProjectRelationEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */

private long contractProjectRelationId;
	/**
	 * $column.comments
	 */

private long contractId;
	/**
	 * $column.comments
	 */

private String wbs;
	/**
	 * $column.comments
	 */

private long projectId;

	/**
	 * 设置：${column.comments}
	 */
	public void setContractProjectRelationId(long contractProjectRelationId) {
		this.contractProjectRelationId = contractProjectRelationId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getContractProjectRelationId() {
		return contractProjectRelationId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setContractId(long contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getContractId() {
		return contractId;
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
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getProjectId() {
		return projectId;
	}
}
