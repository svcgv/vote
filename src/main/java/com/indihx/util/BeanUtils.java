package com.indihx.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeanUtils {
	 public static <T> T Map2Bean(Map<String,Object>map,Class<T> Bean) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
		T a =  Bean.newInstance();
	
		for (String key : map.keySet()) { 
			StringBuffer str = new StringBuffer(key);
			Field field = Bean.getClass().getDeclaredField(camel(str).toString());
			 field.setAccessible(true);
			if(field!=null) {
				field.set(a, map.get(key));
			}
		} 
		
		return a;
	 }
	 
	 public static <T> Map<String,Object> Bean2Map(Class<T> Bean) throws IllegalArgumentException, IllegalAccessException {
		Map<String,Object> map = new HashMap<String,Object>();
		Field[] field = Bean.getClass().getDeclaredFields();
		 for(int i=0;i<field.length;i++) {
			 field[i].setAccessible(true);
			 StringBuffer sb = new StringBuffer(field[i].getName());
			 if(field[i].get(Bean)!=null) {
				 map.put(underline(sb).toString(), field[i].get(Bean));
			 }
		 }
		return map;
	 }
	 
	 /**
		 * 下划线转驼峰
		 * @param str
		 * @return
		 */
		public static StringBuffer camel(StringBuffer str) {
			//利用正则删除下划线，把下划线后一位改成大写
			Pattern pattern = Pattern.compile("_(\\w)");
			Matcher matcher = pattern.matcher(str);
			StringBuffer sb = new StringBuffer(str);
			if(matcher.find()) {
				sb = new StringBuffer();
				//将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
				//正则之前的字符和被替换的字符
				matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
				//把之后的也添加到StringBuffer对象里
				matcher.appendTail(sb);			
			}else {
				return sb;
			}	
			return camel(sb);
		}
		
		
		/**
		 * 驼峰转下划线
		 * @param str
		 * @return
		 */
		public static StringBuffer underline(StringBuffer str) {
			Pattern pattern = Pattern.compile("[A-Z]");
			Matcher matcher = pattern.matcher(str);
			StringBuffer sb = new StringBuffer(str);
			if(matcher.find()) {
				sb = new StringBuffer();
				//将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
				//正则之前的字符和被替换的字符
				matcher.appendReplacement(sb,"_"+matcher.group(0).toLowerCase());
				//把之后的也添加到StringBuffer对象里
				matcher.appendTail(sb);			
			}else {
				return sb;
			}	
			return underline(sb);
		}

	 
}


