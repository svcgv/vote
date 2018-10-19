package com.indihx.comm.util;

import java.math.BigDecimal;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 对象处理实用类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:58:35</p>
 * @author 产品开发部
 * @version 2.0
 * ObjectUtil
 */
public class ObjectUtil extends AbstractUtil {

	/**
	 * 判断String类型对象是否为空(空串或null)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {

		if (str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 把object转换成Long
	 * @param obj 传入
	 * @return 返回类型	
	 */
	public static Long toLong(Object obj){
		if (obj == null || obj == "") {
			return null;
		}
		return Long.valueOf(obj.toString());
	}
	
	/**
	 * 把object转换成Long
	 * @param obj 传入
	 * @return 返回类型	
	 */
	public static String toString(Object obj){
		if (obj == null || obj == "") {
			return null;
		}
		return obj.toString();
	}

	/**
	 * 判断对象是否为空(空串或null)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object obj) {

		if (obj == null || obj.toString().trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个对象数组是否为空(没有成员)
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object[] array) {

		if (array == null || array.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取值为0.00的BigDecimal对象
	 * 
	 * @return
	 */
	public static BigDecimal getZeroBigDecimal() {

		return new BigDecimal(0);
	}
	
	/***
	 * 把Object类型转成BigDecimal
	 * @param obj 输入值
	 * @return BigDecimal
	 */
	public static BigDecimal toBigDecimal(Object obj){
		return BigDecimal.valueOf(Double.parseDouble(obj.toString()));
	}
}
