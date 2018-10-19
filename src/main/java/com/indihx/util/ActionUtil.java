/**
 * 
 */
package com.indihx.util;

import javax.servlet.http.HttpServletRequest;


import com.indihx.comm.InitSysConstants;
import com.indihx.comm.exception.BusinessException;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: ActionUtil.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年4月13日上午10:15:59</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>ActionUtil.java</p>
 * <p></p>
 */
public class ActionUtil {

	public static final String AUDIT_UNLOCKED_TIPMSG = "该用户已经被解锁，请退出！";
	public static final int AUDIT_SYSTEM_ERROR = -2; //系统异常
	public static final int AUDIT_NOSESSION = 0;//用户SESSION已经不存在
	public static final int AUDIT_PASS = 1;//SESSION验证通过
	public static final int AUDIT_UNLOCKED = 2;//需解锁-重新登录过
	
	private ActionUtil() {

	}

	/**
	 * 得到URI，例如：/jsp/a0101/getList.do
	 * @param request
	 * @return
	 * 
	 */
	public static String getURI(HttpServletRequest request) {
		return request.getServletPath();
	}

	/**
	 * 确定当前SESSION的User对象实例
	 * @param request
	 * @return
	 */
	public static UsrInfo getUser(HttpServletRequest request) {
		UsrInfo user = (UsrInfo)request.getSession().getAttribute(InitSysConstants.USER_SESSION);
		if( user == null ) {
			throw new BusinessException("系统异常：无法取得用户登录信息！");
		}
		else {
			return user;
		}
	}
}
