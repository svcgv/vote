package com.indihx.credit.dao;

import java.util.List;

import com.indihx.credit.entity.CreditBadQuota;
import com.indihx.credit.entity.CreditBadQuotaTemp;
import com.indihx.credit.entity.CreditBadRecord;
import com.indihx.credit.entity.CreditBadRecordTemp;
import com.indihx.credit.vo.BadQuotaVo;
import com.indihx.credit.vo.CreditBadVo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CreditBadRecordMapper.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月12日上午8:47:13</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>CreditBadRecordMapper.java</p>
 * <p></p>
 */
public interface CreditBadRecordMapper {

	/**
	 * @param creditBadVo
	 * @return
	 */
	List<CreditBadRecord> getBadRecordList(CreditBadVo creditBadVo);

	/**
	 * @param creditBadVo
	 * @return
	 */
	List<CreditBadRecordTemp> getCreditBadRecordTempList(CreditBadVo creditBadVo);
	
	/**
	 * @param infoVo
	 * @return
	 */
	CreditBadRecordTemp getBadRecordTemp(CreditBadVo infoVo);
	
	/**
	 * @param temp
	 * @return
	 */
	int saveCreditBadInfoTemp(CreditBadVo infoVo);

	/**
	 * @param temp
	 */
	int updateCreditInfoTemp(CreditBadVo infoVo);

	/**
	 * @param credit_code
	 */
	void deleteBadQuota(String credit_code);

	/**
	 * @param badQuota
	 */
	void saveCreditBadQuota(CreditBadQuotaTemp badQuota);

	/**
	 * @param vo
	 * @return
	 */
	List<CreditBadQuotaTemp> getBadQuotaList(BadQuotaVo vo);

	/**
	 * @param vo
	 * @return
	 */
	List<CreditBadQuotaTemp> getBadQuotaTempList(BadQuotaVo vo);

	/**
	 * @param badQuota
	 */
	void updateCreditBadQuota(CreditBadQuotaTemp badQuota);

	/**
	 * @param record
	 */
	void saveCreditBadInfo(CreditBadRecord record);

	/**
	 * @param credit_code
	 */
	void saveCreditQuotaInfo(String credit_code);

	/**
	 * @param credit_code_sel
	 * @return 
	 */
	CreditBadRecordTemp getCreditBadRecordDocment(String credit_code_sel);

	/**
	 * @param credit_code_sel
	 * @return
	 */
	List<CreditBadQuota> getCreditRecordQuotaList(String credit_code_sel);

}
