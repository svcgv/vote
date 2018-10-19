package com.indihx.quartz.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.indihx.quartz.IQuartzTask;
/***
 * 
 * <p>描 述: 定时器实现类</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月3日</p>
 * @author 谢追辉
 */
@Service
public class DemoQuartz implements IQuartzTask {

	private Logger log = LoggerFactory.getLogger(DemoQuartz.class);
	
	@Override
	public void excute() {
		 log.info("定时器已执行！");
	}

}
