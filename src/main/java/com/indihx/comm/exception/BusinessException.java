package com.indihx.comm.exception;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: 自定义系统业务异常</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月29日 下午1:08:53</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>BusinessException.java</p>
 * <p>自定义系统业务逻辑异常</p>
 */
public class BusinessException extends GeneralRuntimeException {

	private static final long serialVersionUID = 1L;

	//预期异常提示信息
	private String message;
	
	public BusinessException(String message){  
		super(message);
		this.message=message;
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.message=message;
	}
	
	public BusinessException(Throwable cause) {
		super(cause);
	}
	
	public String getMessage() {  
		return message;  
	}
	
	public void setMessage(String message) {  
		this.message=message; 
	}
}
