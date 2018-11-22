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
	public ProfitInfo queryProfitInfoByOrgId(Long orgId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgId", orgId);
		ProfitInfo info = mapper.queryProfitInfoByOrgId(param);
		return info;
	}
	
	
	

	@Override
	public List<ProfitInfo> qryListAll(Map<String, Object> entity) {
		
		return mapper.queryListAll(entity);
	}

	@Override
	public void addCostInfo(ProfitInfo profitInfo) {
		mapper.addProfitInfo(profitInfo);
	}

	@Override
	public void updateCostInfo(ProfitInfo profitInfo) {
		
		mapper.updateProfitInfo(profitInfo);
		
	}




	@Override
	public ProfitInfo queryProfitInfoByProfitId(String profitId) {
		
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("profitId", profitId);
		param.put("isDelete", "0");
		return mapper.queryProfitInfoByProfitId(param);
	}

}
