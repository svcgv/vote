/**
 * 
 */
package com.indihx.credit.service;

import java.util.Map;

import com.indihx.credit.entity.CreditQuota;
import com.indihx.credit.vo.QuotaVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: IQuotaService.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月2日下午6:03:36</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>IQuotaService.java</p>
 * <p></p>
 */
public interface ICreditQuotaService {

	/**
	 * 获取诚信指标依据列表
	 * @param user
	 * @param quotaVo
	 * @return
	 */
	Map<String, Object> getQuotaList(UsrInfo user, QuotaVo quotaVo);

	/**
	 * 保存诚信指标依据信息
	 * @param usrInfo
	 * @param infoVo
	 * @return
	 * @throws Exception 
	 */
	String saveQuotaInfo(UsrInfo usrInfo, QuotaVo infoVo) throws Exception;

	/**
	 * 获取默认的指标编码
	 * @return
	 */
	String getQuotaDefaultCode();

	/**
	 * 删除指标信息
	 * @param usrInfo
	 * @param infoVo
	 * @return
	 */
	String deleteQuotaInfo(UsrInfo usrInfo, QuotaVo infoVo);

	/**
	 * 更新指标信息
	 * @param usrInfo
	 * @param infoVo
	 * @return
	 * @throws Exception
	 */
	String updateQuotaInfo(UsrInfo usrInfo, QuotaVo infoVo) throws Exception;

	/**
	 * 根据指标ID获取指标详情
	 * @param credit_seq
	 * @return
	 */
	CreditQuota getQuotaInfo(String credit_seq);

	/**
	 * 禁用指标或作废指标
	 * @param usrInfo
	 * @param infoVo
	 * @return
	 */
	String validQuotaInfo(UsrInfo usrInfo, QuotaVo infoVo);

}
