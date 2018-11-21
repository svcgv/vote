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
	public PmYearBudgetEntity queryObject(String id);
	public void insert(PmYearBudgetEntity entity);
	public void update(PmYearBudgetEntity entity);
	public void delete(String yearBudgetCode);
	public List<PmYearBudgetEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
	public void insertList(List<PmYearBudgetEntity> listBean,long userId);
	public List<Map<String, Object>> queryListByMap(Map<String, Object> entity);
}

