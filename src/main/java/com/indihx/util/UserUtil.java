package com.indihx.util;

import javax.servlet.http.HttpSession;

import com.indihx.comm.InitSysConstants;
import com.indihx.system.entity.UsrInfo;

public class UserUtil {
	public static UsrInfo getUser(HttpSession session){
		UsrInfo usrInfo = (UsrInfo) session.getAttribute(InitSysConstants.USER_SESSION);
		return usrInfo;
	}
}
