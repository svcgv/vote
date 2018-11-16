package com.indihx.system.dao;

import java.util.List;

import com.indihx.system.entity.CostInfo;


public interface CostInfoMapper {

	
	

	
	List<CostInfo> queryCostInfoListByOrgId(Long orgId);
	
	
}
