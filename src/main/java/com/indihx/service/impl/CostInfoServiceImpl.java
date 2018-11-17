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
	public List<Map<String,Object>> qryCostInfoListByOrgId(Long orgId) {
		
		
		List<CostInfo> costInfos = mapper.queryCostInfoListByOrgId(orgId); 
		List<Map<String, Object>> maps = new ArrayList<>();
		
		for (CostInfo costInfo :costInfos) {
			Map<String, Object> map = new HashMap<>();
			map.put("CONST_ID", costInfo.getCostId());
			map.put("ORG_ID", costInfo.getOrgId());
			map.put("ORG_NAME", costInfo.getOrgName());
			maps.add(map);
		}
		return maps;
	}

	

}
