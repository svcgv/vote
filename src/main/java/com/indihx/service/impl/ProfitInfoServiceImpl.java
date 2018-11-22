package com.indihx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indihx.service.IProfitInfoService;
import com.indihx.system.dao.ProfitInfoMapper;
import com.indihx.system.entity.ProfitInfo;

@Service
public class ProfitInfoServiceImpl implements IProfitInfoService{

	@Autowired
	private ProfitInfoMapper mapper;

	@Override
	public void addCostInfo(ProfitInfo profitInfo) {
		this.mapper.addProfitInfo(profitInfo);
	}




	@Override
	public List<ProfitInfo> qryListAll(Map<String, Object> entity) {

		entity.put("isDelete", "0");
		return this.mapper.queryListAll(entity);
	}

	@Override
	public ProfitInfo queryProfitInfoByOrgId(Long orgId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgId", orgId);
		param.put("isDelete", "0");
		ProfitInfo info = this.mapper.queryProfitInfoByOrgId(param);
		return info;
	}

	@Override
	public ProfitInfo queryProfitInfoByProfitId(String profitId) {


		Map<String, Object> param = new HashMap<String, Object>();
		param.put("profitId", profitId);
		param.put("isDelete", "0");
		return this.mapper.queryProfitInfoByProfitId(param);
	}




	@Override
	public void updateCostInfo(ProfitInfo profitInfo) {

		this.mapper.updateProfitInfo(profitInfo);

	}

}
