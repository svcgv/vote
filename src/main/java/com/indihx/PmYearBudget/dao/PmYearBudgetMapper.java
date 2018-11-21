package com.indihx.PmYearBudget.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmYearBudget.entity.PmYearBudgetEntity;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-18 10:35:36
 */
public interface PmYearBudgetMapper{
	public PmYearBudgetEntity queryObject(String id);
	public List<Map<String, Object>> querySapCodeCount(long userId);
	public void insert(PmYearBudgetEntity entity);
	public void update(PmYearBudgetEntity entity);
	public void delete(String yearBudgetCode);
	public List<PmYearBudgetEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
	public List<Map<String, Object>> queryListByMap(Map<String, Object> entity);
	public void deleteByCreatorId(long userId);
}
