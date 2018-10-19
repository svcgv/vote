/**
 * 
 */
package com.indihx.comm.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: ExceptionUtil.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月9日上午11:12:12</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>ExceptionUtil.java</p>
 * <p>截获最底层异常</p>
 */
public class ExceptionUtil {

	private static Logger log = LoggerFactory.getLogger(ExceptionUtil.class);
	/**
	 * 获取最内存的异常
	 * @param e
	 * @return
	 */
	public static Throwable parseException(Throwable e){
		Throwable tmp = e;
	    int breakPoint = 0;
	    while(tmp.getCause()!=null){
	    	if(tmp.equals(tmp.getCause())){
	    		break;
	    	}
	    	tmp=tmp.getCause();
	    	breakPoint++;
	    	if(breakPoint>1000){
	    		break;
	    	}
	    } 
	    return tmp;
	 }
	
	public static String getErrorMsg(Throwable e){
		log.info(e.getMessage());
		String msg =parseException(e).getMessage();
		return msg;
	}
}
