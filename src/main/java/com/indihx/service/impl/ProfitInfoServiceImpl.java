package com.indihx.service.impl;

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
		
		return mapper.queryProfitInfoByOrgId(orgId);
	}

}
