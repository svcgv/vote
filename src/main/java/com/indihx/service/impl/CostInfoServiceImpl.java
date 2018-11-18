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
	public List<CostInfo> qryCostInfoListByOrgId(Long orgId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgId", orgId);
		
		List<CostInfo> costInfos = mapper.queryCostInfoListByOrgId(param); 
	
		return costInfos;
	}

	

}
