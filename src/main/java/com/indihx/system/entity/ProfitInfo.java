package com.indihx.system.entity;

import java.io.Serializable;

import com.indihx.system.vo.BaseVo;


/***
 * 
 * <p>描 述: 利润中心信息</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年11月16日</p>
 * 
 */
public class ProfitInfo extends BaseVo{

	private Long orgId;
	
	private String profitId;
	
	private String orgName;
	
	private String isDelete;
	
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getProfitId() {
		return profitId;
	}

	public void setProfitId(String profitId) {
		this.profitId = profitId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	
	
	
}
