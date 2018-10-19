/**
 * 
 */
package com.indihx.credit.service;

import java.util.Map;

import com.indihx.activiti.entity.Application;
import com.indihx.credit.vo.CreditBadVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: ICreditAppealService.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月28日上午8:22:31</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>ICreditAppealService.java</p>
 * <p></p>
 */
public interface ICreditAppealService {

	/**
	 * @param user
	 * @param creditBadVo
	 * @return
	 */
	Map<String, Object> getBadTempRecordList(UsrInfo user,
			CreditBadVo creditBadVo);


	/**
	 * @param user
	 * @param infoVo
	 * @return
	 */
	Map<String, Object> getBadTempRecordInfo(UsrInfo user, CreditBadVo infoVo);


	/**
	 * @param usrInfo
	 * @param reqMap
	 * @return
	 */
	Application saveAppealInfo(UsrInfo usrInfo, Map<String, Object> reqMap);

}
