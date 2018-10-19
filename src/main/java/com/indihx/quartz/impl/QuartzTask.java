/**
 * 
 */
package com.indihx.quartz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indihx.activiti.entity.Application;
import com.indihx.credit.dao.CreditBadRecordMapper;
import com.indihx.credit.entity.CreditBadRecordTemp;
import com.indihx.credit.service.ICreditBadRecordService;
import com.indihx.credit.vo.CreditBadVo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: QuartzTask.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月30日下午1:03:10</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>QuartzTask.java</p>
 * <p></p>
 */
@Service
public class QuartzTask {

	@Autowired
    private ICreditBadRecordService creditBadRecordService;
	@Resource
    private CreditBadRecordMapper creditRecordMapper;
	
	public void creditJobCenter(){

		//获取诚信档案状态为告知中，申诉标志位未进行异议申诉的不良档案
		List<CreditBadRecordTemp> recordList = creditRecordMapper.getCreditBadRecordTempList(new CreditBadVo());
		//异议申诉期限到期将诚信档案状态更改为已归档，并正式建档；同时调用流程引擎将不良诚信建档流程办结。
		List<Application> list=creditBadRecordService.bulidCreditDocment(recordList);
		
	}

	/**
	 * 定时任务处理逻辑
	 */
	public void timerJobTask() {
		System.out.println(creditRecordMapper);
		System.out.println("正在执行定时任务....");
		
	}
}
