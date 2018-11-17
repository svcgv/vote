package com.indihx.service.impl;

import java.util.HashMap;
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
	public Map<String, Object> queryProfitInfoByOrgId(Long orgId) {
		
		ProfitInfo info = mapper.queryProfitInfoByOrgId(orgId);
		Map<String, Object> map = new HashMap<>();
		map.put("PROFIT_ID", info.getProfitId());
		map.put("ORG_ID", info.getOrgId());
		map.put("ORG_NAME", info.getOrgName());
		
		return map;
	}

}
