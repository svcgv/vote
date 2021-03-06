package com.indihx.system.entity;

import java.io.Serializable;

import com.indihx.system.vo.BaseVo;

/***
 * 
 * <p>描 述: 成本中心信息</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年11月16日</p>
 * 
 */
public class CostInfo extends BaseVo{
	
	
	

	private Long orgId;
	
	private String costId;
	
	private String orgName;
	
	private String isDelete;
	

	
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getCostId() {
		return costId;
	}

	public void setCostId(String costId) {
		this.costId = costId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
