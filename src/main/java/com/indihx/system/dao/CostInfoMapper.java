package com.indihx.system.dao;

import java.util.List;
import java.util.Map;

import com.indihx.system.entity.CostInfo;


public interface CostInfoMapper {


	List<CostInfo> queryCostInfoListByOrgId(Map<String, Object> param);
	public List<CostInfo> qryCostInfoList(Map<String, Object> entity);
	CostInfo  qryCostInfoByCostId(Map<String, Object> param);
	public void addCostInfo(CostInfo costInfo);
	public void updateCostInfo(CostInfo costInfo);
	public void delete(long costId);
	
}
