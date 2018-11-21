package com.indihx.PmYearBudget.entity;


import com.indihx.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-18 10:35:36
 */
public class PmYearBudgetResponseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sapCode;

	private String custName;

	private List<PmYearBudgetEntity> pmYearBudgetEntity;

	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public List<PmYearBudgetEntity> getPmYearBudgetEntity() {
		return pmYearBudgetEntity;
	}

	public void setPmYearBudgetEntity(List<PmYearBudgetEntity> pmYearBudgetEntity) {
		this.pmYearBudgetEntity = pmYearBudgetEntity;
	}
}
