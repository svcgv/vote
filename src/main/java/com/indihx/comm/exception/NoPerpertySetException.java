package com.indihx.comm.exception;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: 自定义配置文件加载异常</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月29日 下午1:08:53</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>NoPerpertySetException.java</p>
 * <p>自定义资源属性未配置异常</p>
 */
public class NoPerpertySetException extends BusinessException {
	
	static final long serialVersionUID = -3387516993124220023L;
	
    public NoPerpertySetException(String propertyName) {

    	super("未设置"+propertyName +"属性值，"+propertyName+"不能为空");
    }


}
