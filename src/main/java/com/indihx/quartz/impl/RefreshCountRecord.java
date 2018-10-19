/**
 * 
 */
package com.indihx.quartz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.indihx.credit.service.ICreditBadRecordService;


/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: RefreshCountRecord.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月30日下午12:41:02</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>RefreshCountRecord.java</p>
 * <p>定时处理任务</p>
 */
public class RefreshCountRecord {

	private static Logger log = LoggerFactory.getLogger(RefreshCountRecord.class);
	
	//调用的方法  
    public void execute(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String dateNowStr = sdf.format(d);
        
        
        //需要执行的job
        log.info(dateNowStr+"定时任务执行了...");
        QuartzTask task = new QuartzTask();
//        task.creditJobCenter();
        task.timerJobTask();
        //设置执行日志
        log.info(dateNowStr+"定时执行成功了...");
        

    }
}
