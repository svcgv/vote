/**
 * 
 */
package com.indihx.activiti.service;

import java.util.List;

import com.indihx.system.entity.UsrInfo;


/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: IActivitiUserBiz.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年4月23日上午11:49:02</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>IActivitiUserBiz.java</p>
 * <p>用于同步系统的用户至Activiti的用户，目的是实现Activiti对任务分配到：指定人、指定组</p>
 */
public interface IActivitiUserBiz {

	/**
	 * 同步系统采集用户至activiti的用户
	 * @param user
	 * @param roleIds
	 * @param synToActiviti
	 * @return
	 */
	int snyActivitiUser(UsrInfo user, List<Long> roleIds, boolean synToActiviti);
}
