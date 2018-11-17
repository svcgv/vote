package com.indihx.system.dao;

import java.util.List;
import java.util.Map;

import com.indihx.system.entity.CostInfo;


public interface CostInfoMapper {


	List<CostInfo> queryCostInfoListByOrgId(Map<String, Object> param);
	
	
}
