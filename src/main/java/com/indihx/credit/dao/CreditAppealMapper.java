/**
 * 
 */
package com.indihx.credit.dao;

import java.util.List;

import com.indihx.credit.entity.CreditAppeal;
import com.indihx.credit.entity.CreditBadRecordTemp;
import com.indihx.credit.vo.CreditBadVo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CreditAppealMapper.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月28日上午8:24:56</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>CreditAppealMapper.java</p>
 * <p></p>
 */
public interface CreditAppealMapper {


	/**
	 * @param creditBadVo
	 * @return
	 */
	List<CreditBadRecordTemp> getAppealBadRecordList(CreditBadVo creditBadVo);

	/**
	 * @param infoVo
	 * @return
	 */
	int saveCreditAppealInfo(CreditAppeal infoVo);

	/**
	 * @param infoVo
	 * @return
	 */
	int updateCreditAppealInfo(CreditAppeal infoVo);

	/**
	 * @param infoVo
	 * @return
	 */
	CreditAppeal getAppealInfo(CreditAppeal infoVo);
}
