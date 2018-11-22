package com.indihx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indihx.service.ICostInfoService;
import com.indihx.system.dao.CostInfoMapper;
import com.indihx.system.entity.CostInfo;


@Service
public class CostInfoServiceImpl implements ICostInfoService{

	
	@Autowired
	private CostInfoMapper mapper;

	@Override
	public List<CostInfo> qryCostInfoListByOrgId(long orgId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgId", orgId);
		param.put("isDelete", "0");
		
		List<CostInfo> costInfos = mapper.queryCostInfoListByOrgId(param); 
	
		return costInfos;
	}

	@Override
	public List<CostInfo> qryCostInfoList(Map<String, Object> entity) {
		
		entity.put("isDelete", "0");
		return mapper.qryCostInfoList(entity);
	}
	

	@Override
	public CostInfo quetyCostInfoByCostId(String costId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("costId", costId);
		param.put("isDelete", "0");
		return mapper.qryCostInfoByCostId(param);
	}

	@Override
	public void addCostInfo(CostInfo costInfo) {
		
		mapper.addCostInfo(costInfo);
	}

	@Override
	public void updateCostInfo(CostInfo costInfo) {
		
		
		mapper.updateCostInfo(costInfo);
		
	}

	

	@Override
	public void delCostInfo(long costId) {
		mapper.delete(costId);
	}

	

}
