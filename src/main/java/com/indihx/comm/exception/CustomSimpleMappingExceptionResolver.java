package com.indihx.comm.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: 系统全局异常控制引擎</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月30日 下午4:26:18</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>CustomSimpleMappingExceptionResolver.java</p>
 * <p>用户异常全局处理控制类</p>
 */
public class CustomSimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		String viewName = determineViewName(ex, request);
		ex.printStackTrace();  
		if (viewName != null) {// JSP格式返回  
			if (!(request.getHeader("accept").indexOf("application/json") > -1|| (request.getHeader("X-Requested-With")!= null&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
				// 如果不是异步请求
				Integer statusCode = determineStatusCode(request, viewName); 
				if (statusCode != null) {  
					applyStatusCodeIfPossible(request, response, statusCode);  
				}  
				ModelAndView view = new ModelAndView();
				view.setViewName(viewName);
				view.addObject("message", ex.getMessage());
				return view;  
			}else{// Json格式返回
				try {
					PrintWriter writer = response.getWriter();  
					writer.write(parseException(ex).getMessage());  
					writer.flush();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
				return null;  
			}
		}else{
			viewName = "/error/error";
			ModelAndView view = new ModelAndView();
			view.setViewName(viewName);
			view.addObject("message", ex.getMessage());
			return view;  
		}
	}
	
	/**
	 * 获取最内存的异常
	 * @param e
	 * @return
	 */
	private static Throwable parseException(Throwable e){
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

}
