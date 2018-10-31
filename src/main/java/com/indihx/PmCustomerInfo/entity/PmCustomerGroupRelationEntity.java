package com.indihx.PmCustomerInfo.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 18:46:08
 */
public class PmCustomerGroupRelationEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long custGroupRelationId;
	/**
	 * 
	 */

private long custGroupId;
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

private String sapCode;

	/**
	 * 设置：
	 */
	public void setCustGroupRelationId(long custGroupRelationId) {
		this.custGroupRelationId = custGroupRelationId;
	}
	/**
	 * 获取：
	 */
	public long getCustGroupRelationId() {
		return custGroupRelationId;
	}
	/**
	 * 设置：
	 */
	public void setCustGroupId(long custGroupId) {
		this.custGroupId = custGroupId;
	}
	/**
	 * 获取：
	 */
	public long getCustGroupId() {
		return custGroupId;
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
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	/**
	 * 获取：
	 */
	public String getSapCode() {
		return sapCode;
	}
}
