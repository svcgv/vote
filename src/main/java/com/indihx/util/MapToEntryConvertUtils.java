package com.indihx.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.util.ObjectUtil;


public class MapToEntryConvertUtils {

	/**
	 * 复制map中的同名值到目标对象
	 * @param source 被复制的map
	 * @param dest 目标对象
	 * @return
	 */
	public static void convertMaptoBean(@SuppressWarnings("rawtypes") Map source, Object destBean) {
		try {
			@SuppressWarnings("rawtypes")
			Iterator itNew = source.keySet().iterator();
			while( itNew.hasNext() ) {
				String colname = (String)itNew.next();
				@SuppressWarnings("rawtypes")
				Class clzDest = PropertyUtils.getPropertyType(destBean, colname);
				if( clzDest == null )
					continue;
				Object value = source.get(colname);
				if( ObjectUtil.isEmpty(value) )
					continue;
				if(clzDest.isArray())
					continue;
				if( clzDest.getName().endsWith("String") )
					value = value.toString();
				else if( clzDest.getName().endsWith("Long") )
					value = new Long(value.toString());
				else if( clzDest.getName().endsWith("BigDecimal") )
					value = new java.math.BigDecimal(value.toString());
				else if( clzDest.getName().endsWith("Integer") )
					value = new Integer(value.toString());
				else
					throw new RuntimeException("不支持的数据类型转化！" + clzDest.getName());
				PropertyUtils.setSimpleProperty(destBean, colname, value);
			}
		}
		catch(Exception e) {
			throw new BusinessException("对象属性转化失败"+ExceptionUtil.getErrorMsg(e));
		}
	}
	
	/**
	 * 将bean对象转换为map
	 * @param obj
	 * @param mp
	 */
	public static void convertEnToMap(Object obj,Map <String ,Object > mp ){
		
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(obj);
		int len = propertys.length;
		try{
		for (int i = 0; i < len; i++) {
			String name = propertys[i].getName();
			if (PropertyUtils.isReadable(obj, name)&&(mp.get(name)==null) && (!"class".equals(name))) {
				Object  value= PropertyUtils.getSimpleProperty(obj, name);
				if(value instanceof Map || value instanceof Object[]||value instanceof HashSet ) continue;
				mp.put(name, value);
			}						
		}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("系统异常，对象转换异常！", e);
		}
	}

	/**
	 * 将bean对象转换为map,如果map中有值，将覆盖掉原来的值（一般用在从数据库中查询出来，将页面的值覆盖）
	 * @param obj
	 * @param mp
	 */
	public static void convertEnToMapCoverageMap(Object obj,Map <String ,Object > mp ){
		
		PropertyDescriptor propertys[] = PropertyUtils.getPropertyDescriptors(obj);
		int len = propertys.length;
		try{
		for (int i = 0; i < len; i++) {
			String name = propertys[i].getName();
			if (PropertyUtils.isReadable(obj, name) && (!"class".equals(name))) {
				Object  value= PropertyUtils.getSimpleProperty(obj, name);
				if(!ObjectUtil.isEmpty(value)){
					if(value instanceof Map || value instanceof Object[]||value instanceof HashSet ) continue;
					mp.put(name, value);
				}
			}						
		}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("系统异常，对象转换异常！", e);
		}
	}
}
