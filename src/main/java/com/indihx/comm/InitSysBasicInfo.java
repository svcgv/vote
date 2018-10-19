package com.indihx.comm;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.indihx.comm.service.impl.InitSysBasicServiceImpl;
import com.indihx.util.EncryptionUtil;

/***
 * 
 * <p>描 述: 初始化系统基本信息</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年4月12日</p>
 * @author 谢追辉
 */
public class InitSysBasicInfo implements InitializingBean, ServletContextAware {
	
	private static Logger logger = LoggerFactory.getLogger(InitSysBasicInfo.class);
	@Resource
	private InitSysBasicServiceImpl intitImpl ;
	//执行
	public void init(){
		logger.info("--->start--------------开始初始化基本信息!----------------");
		intitImpl.clearSession();
		intitImpl.initCodeDataAll();  //数据字典
		intitImpl.initParamsInfo();  //参数加载
		intitImpl.initRegion(); //加载分区区域
		if (!EncryptionUtil.areKeysPresent()) {
			EncryptionUtil.generateKey(); //生成公私钥
		}
		//加载参数
		logger.info("--->end--------------初始化基本信息完成!----------------");
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		init();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
