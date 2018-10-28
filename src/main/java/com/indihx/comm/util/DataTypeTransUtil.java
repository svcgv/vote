package com.indihx.comm.util;

import java.sql.Timestamp;

/**
 * 数据类型转换工具类
 * @author Ice Cream
 *
 */
public class DataTypeTransUtil {
	public static String getString(String str) {
		if(str==null||str=="") {
			return null;
		}
		return str;
	}
	
	public static Long getLong(Object obj) {
		if (obj == null || obj == "") {
			return null;
		}
		return Long.valueOf(obj.toString());
	}
	/*
	public static Timestamp getTime(String str) {
		
	}*/
}
