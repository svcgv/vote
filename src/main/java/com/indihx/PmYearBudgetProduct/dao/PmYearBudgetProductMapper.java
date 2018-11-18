package com.indihx.PmYearBudgetProduct.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmYearBudgetProduct.entity.PmYearBudgetProductEntity;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-18 10:35:35
 */
public interface PmYearBudgetProductMapper{
	public PmYearBudgetProductEntity queryObject(long id);
	public void insert(PmYearBudgetProductEntity entity);
	public void update(PmYearBudgetProductEntity entity);
	public void delete(long paymentId);
	public List<PmYearBudgetProductEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
