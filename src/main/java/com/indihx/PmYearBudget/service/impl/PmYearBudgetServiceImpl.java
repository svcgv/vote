package com.indihx.PmYearBudget.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmYearBudget.dao.PmYearBudgetMapper;
import com.indihx.PmYearBudget.entity.PmYearBudgetEntity;
import com.indihx.PmYearBudget.service.PmYearBudgetService;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.RandomUtil;

@Service("pmYearBudgetService")
public class PmYearBudgetServiceImpl implements PmYearBudgetService {
	@Resource
	PmYearBudgetMapper pmYearBudgetMapper;

	public PmYearBudgetEntity queryObject(String id) {
		return pmYearBudgetMapper.queryObject(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmYearBudgetEntity entity) {
		pmYearBudgetMapper.insert(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmYearBudgetEntity entity) {
		pmYearBudgetMapper.update(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(String yearBudgetCode) {
		pmYearBudgetMapper.delete(yearBudgetCode);
	}

	public int queryTotal() {
		return pmYearBudgetMapper.queryTotal();
	}

	public List<PmYearBudgetEntity> queryList(Map<String, Object> entity) {
		if (entity.get("isDelete") == null || "".equals(entity.get("isDelete"))) {
			entity.put("isDelete", "00");
		}
		return pmYearBudgetMapper.queryList(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertList(List<PmYearBudgetEntity> listBean, long userId) {
		pmYearBudgetMapper.deleteByCreatorId(userId);
		if (listBean != null && !listBean.isEmpty()) {
			for (int i = 0; i < listBean.size(); i++) {

				PmYearBudgetEntity pmYearBudget = listBean.get(i);
				
				pmYearBudget.setIsDelete("00");
				pmYearBudget.setCreatorId(userId);
				pmYearBudget.setCreateTime(DateUtil.getDateTime());
				pmYearBudget.setYearBudgetCode(RandomUtil.getCodeByType("YS"));
				pmYearBudgetMapper.insert(pmYearBudget);
			}
		}
	}

	public List<Map<String, Object>> queryListByMap(Map<String, Object> entity) {

		return pmYearBudgetMapper.queryListByMap(entity);
	}
}
