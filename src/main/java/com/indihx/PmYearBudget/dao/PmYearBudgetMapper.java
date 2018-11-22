package com.indihx.PmYearBudget.dao;

import java.util.List;
import java.util.Map;

import com.indihx.PmYearBudget.entity.PmYearBudgetEntity;

/**
 * ${comments}
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-18 10:35:36
 */
public interface PmYearBudgetMapper{
	public void delete(String yearBudgetCode);
	public void deleteByCreatorId(long userId);
	public void insert(PmYearBudgetEntity entity);
	public List<PmYearBudgetEntity> queryList(Map<String, Object> entity);
	public List<Map<String, Object>> queryListByMap(Map<String, Object> entity);
	public PmYearBudgetEntity queryObject(String id);
	public  List<Map<String, Object>> queryProjectSapCodeCount(Map<String, Object> map);
	public List<Map<String, Object>> querySapCodeCount(long userId);
	public int queryTotal();
	public void update(PmYearBudgetEntity entity);
}
