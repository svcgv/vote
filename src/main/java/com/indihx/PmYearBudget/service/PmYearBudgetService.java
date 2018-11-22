package com.indihx.PmYearBudget.service;

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
public interface PmYearBudgetService {
	public void delete(String yearBudgetCode);
	public void insert(PmYearBudgetEntity entity);
	public void insertList(List<PmYearBudgetEntity> listBean,long userId);
	public void insertMapList(List<Map<String, Object>> listBean,long userId);
	public List<PmYearBudgetEntity> queryList(Map<String, Object> entity);
	public List<Map<String, Object>> queryListByMap(Map<String, Object> entity);
	public PmYearBudgetEntity queryObject(String id);
	public List<Map<String, Object>> queryProjectSapCodeCount(Map<String, Object> entity);
	public List<Map<String, Object>> querySapCodeCount(long userId);
	public int queryTotal();
	public void update(PmYearBudgetEntity entity);
}

