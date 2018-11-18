package com.indihx.system.dao;



import java.util.Map;

import com.indihx.system.entity.ProfitInfo;



public interface ProfitInfoMapper {
	
	
	ProfitInfo queryProfitInfoByOrgId(Map<String, Object> param);

}
