/**
 * 
 */
package com.indihx.credit.dao;

import java.util.List;

import com.indihx.credit.entity.CreditQuota;
import com.indihx.credit.vo.QuotaVo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: QuotaMapper.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月5日上午9:30:23</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>QuotaMapper.java</p>
 * <p></p>
 */
public interface CreditQuotaMapper {

	/**
	 * @param quotaVo
	 * @return
	 */
	List<CreditQuota> getQuotaList(QuotaVo quotaVo);

	/**
	 * @param infoVo
	 * @return
	 */
	int saveQuota(QuotaVo infoVo);

	/**
	 * @param quotaVo
	 * @return
	 */
	CreditQuota getQuotaByCode(QuotaVo quotaVo);
	
	/**
	 * @param credit_seq
	 * @return
	 */
	CreditQuota getQuotaInfo(String credit_seq);

	/**
	 * 
	 * @param credit_seq
	 * @return
	 */
	int deleteQuota(String credit_seq);

	/**
	 * @param infoVo
	 * @return
	 */
	int updateQuota(QuotaVo infoVo);

	/**
	 * @param infoVo
	 * @return
	 */
	int updateQuotaStatus(QuotaVo infoVo);

}
