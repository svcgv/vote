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
	public ProfitInfo queryProfitInfoByOrgId(Long orgId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgId", orgId);
		ProfitInfo info = mapper.queryProfitInfoByOrgId(param);
		return info;
	}

}
