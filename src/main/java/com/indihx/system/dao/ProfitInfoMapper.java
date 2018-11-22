package com.indihx.system.dao;



import java.util.List;
import java.util.Map;


import com.indihx.system.entity.ProfitInfo;



public interface ProfitInfoMapper {
	
	
	ProfitInfo queryProfitInfoByOrgId(Map<String, Object> param);
	ProfitInfo queryProfitInfoByProfitId(Map<String, Object> param);
	List<ProfitInfo> queryListAll(Map<String, Object> param);
	
	public void addProfitInfo(ProfitInfo info);
	public void updateProfitInfo(ProfitInfo info);
	
	
}
