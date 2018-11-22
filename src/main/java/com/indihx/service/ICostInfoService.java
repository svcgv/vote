package com.indihx.service;

import java.util.List;
import java.util.Map;

import com.indihx.PmCompanyInfo.entity.PmCompanyInfoEntity;
import com.indihx.system.entity.CostInfo;



/***
 * 
 * <p>描 述: 成本中心信息接口</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年11月16日</p>
 * 
 */
public interface ICostInfoService {
	
	public List<CostInfo> qryCostInfoListByOrgId(long orgId);
	public List<CostInfo> qryCostInfoList(Map<String, Object> entity);
	public CostInfo quetyCostInfoByCostId(String costId);
	public void addCostInfo(CostInfo costInfo);
	public void updateCostInfo(CostInfo costInfo);
	public void delCostInfo(long costId);
 
}
