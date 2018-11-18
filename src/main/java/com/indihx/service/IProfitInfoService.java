package com.indihx.service;


import com.indihx.system.entity.ProfitInfo;

/***
 * 
 * <p>描 述: 成本中心信息接口</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年11月16日</p>
 * 
 */
public interface IProfitInfoService {

	
	ProfitInfo queryProfitInfoByOrgId(Long orgId);
	
	
}
