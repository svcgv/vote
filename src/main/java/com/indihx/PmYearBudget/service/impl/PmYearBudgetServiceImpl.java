package com.indihx.PmYearBudget.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.indihx.PmYearBudget.dao.PmYearBudgetMapper;
import com.indihx.PmYearBudget.entity.PmYearBudgetEntity;
import com.indihx.PmYearBudget.service.PmYearBudgetService;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.RandomUtil;

@Service("pmYearBudgetService")
public class PmYearBudgetServiceImpl implements PmYearBudgetService {
	@Resource PmYearBudgetMapper pmYearBudgetMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(String yearBudgetCode) {
		this.pmYearBudgetMapper.delete(yearBudgetCode);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmYearBudgetEntity entity) {
		this.pmYearBudgetMapper.insert(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertList(List<PmYearBudgetEntity> listBean, long userId) {
		this.pmYearBudgetMapper.deleteByCreatorId(userId);
		if (listBean != null && !listBean.isEmpty()) {
			for (int i = 0; i < listBean.size(); i++) {

				PmYearBudgetEntity pmYearBudget = listBean.get(i);
				if (pmYearBudget.getBudgetYear() == null || "".equals(pmYearBudget.getBudgetYear())) {
					pmYearBudget.setBudgetYear(DateUtil.getNextYearStr());
				}
				pmYearBudget.setIsDelete("00");
				pmYearBudget.setCreatorId(userId);
				pmYearBudget.setCreateTime(DateUtil.getDateTime());
				pmYearBudget.setYearBudgetCode(RandomUtil.getCodeByType("YS"));
				this.pmYearBudgetMapper.insert(pmYearBudget);
			}
		}
	}

	@Override
	public List<PmYearBudgetEntity> queryList(Map<String, Object> entity) {
		if (entity.get("isDelete") == null || "".equals(entity.get("isDelete"))) {
			entity.put("isDelete", "00");
		}
		return this.pmYearBudgetMapper.queryList(entity);
	}

	@Override
	public List<Map<String, Object>> queryListByMap(Map<String, Object> entity) {

		return this.pmYearBudgetMapper.queryListByMap(entity);
	}

	@Override
	public PmYearBudgetEntity queryObject(String id) {
		return this.pmYearBudgetMapper.queryObject(id);
	}

	@Override
	public List<Map<String, Object>> queryProjectSapCodeCount(Map<String, Object> entity) {

		return this.pmYearBudgetMapper.queryProjectSapCodeCount(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Map<String, Object>> querySapCodeCount(long userId) {
		return this.pmYearBudgetMapper.querySapCodeCount(userId);
	}

	@Override
	public int queryTotal() {
		return this.pmYearBudgetMapper.queryTotal();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmYearBudgetEntity entity) {
		this.pmYearBudgetMapper.update(entity);
	}
}
