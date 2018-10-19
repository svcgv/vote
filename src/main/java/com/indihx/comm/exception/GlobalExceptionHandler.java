/**
 * 
 */
package com.indihx.comm.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: GlobalExceptionHandler.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月29日上午10:35:18</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>GlobalExceptionHandler.java</p>
 * <p>系统全局异常处理类</p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = GeneralRuntimeException.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception ex) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", ex.getMessage());
        mav.setViewName("/error/error");
        return mav;
    }

    @ExceptionHandler(value = BusinessException.class)
    public ModelAndView notFoundErrorHandler(HttpServletRequest req, BusinessException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ex.getMessage());
        mav.setViewName("/error/error");
        return mav;
    }

}
