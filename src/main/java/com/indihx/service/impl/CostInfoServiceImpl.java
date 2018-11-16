package com.indihx.service.impl;

import java.util.List;

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
		return mapper.queryCostInfoListByOrgId(orgId);
	}

	

}
