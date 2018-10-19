package com.indihx.credit.service;

import java.util.List;
import java.util.Map;

import com.indihx.activiti.entity.Application;
import com.indihx.credit.entity.CreditBadQuota;
import com.indihx.credit.entity.CreditBadRecord;
import com.indihx.credit.entity.CreditBadRecordTemp;
import com.indihx.credit.entity.CreditFileSign;
import com.indihx.credit.vo.BadQuotaVo;
import com.indihx.credit.vo.CreditBadVo;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: ICreditBadRecordService.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月12日上午8:41:55</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>ICreditBadRecordService.java</p>
 * <p></p>
 */
public interface ICreditBadRecordService {

	/**
	 * 查询不良信用记录列表
	 * @param user
	 * @param creditBadVo
	 * @return
	 */
	Map<String, Object> getBadRecordList(UsrInfo user, CreditBadVo creditBadVo);

	/**
	 * 获取受理区局列表
	 * @param user
	 * @return
	 */
	List<Wy_Hpb> getHpbList(UsrInfo user);

	/**
	 * 保存不良信用至临时表
	 * @param usrInfo
	 * @param infoVo
	 * @return
	 */
	Application saveBadRecordInfo(UsrInfo usrInfo, Map<String, Object> reqMap);

	/**
	 * 根据业务标志获取附件库
	 * @param creditdocment
	 * @return
	 */
	List<CreditFileSign> getFileType(String tran_type,String credit_code);

	/**
	 * 根据流程ID或credit_code查询不良信息
	 * @param infoVo
	 * @return
	 */
	CreditBadRecordTemp getBadRecordTemp(CreditBadVo infoVo);

	/**
	 * ajax查询记分指标依据
	 * @param infoVo
	 * @return
	 */
	Map<String, Object> getBadQuotaList(BadQuotaVo infoVo);

	/**
	 * 保存告知单信息
	 * @param usrInfo
	 * @param reqMap
	 * @return
	 */
	Application saveNotifyRecord(UsrInfo usrInfo, Map<String, Object> reqMap);

	/**
	 * @param credit_code_sel
	 * @return
	 */
	CreditBadRecordTemp getBadRecord(String credit_code_sel);

	/**
	 * @param credit_code_sel
	 * @return
	 */
	List<CreditBadQuota> getQuotaList(String credit_code_sel);

	/**
	 * @param recordList
	 */
	List<Application> bulidCreditDocment(List<CreditBadRecordTemp> recordList);


}
