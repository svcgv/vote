package com.indihx.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.indihx.system.entity.UsrInfo;

public class BeanUtils {
	public static <T extends Object> Map<String, Object> Bean2Map(T Bean) throws IllegalArgumentException, IllegalAccessException {
		// 创建一个新的map
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取bean的fields，遍历，若fields有值则put到map上
		Field[] field = Bean.getClass().getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			field[i].setAccessible(true);
			String sb = field[i].getName();
			if (field[i].get(Bean) != null) {
				map.put(BeanUtils.underline(sb), field[i].get(Bean));
			}
		}
		return map;
	}

	public static <T extends Object> List<Map<String, Object>> BeanList2MapList(List<T> listBean) throws IllegalArgumentException, IllegalAccessException {
		List<Map<String, Object>> mapList = new ArrayList<>();
		if (listBean.isEmpty()) {
			return mapList;
		}

		for (int i = 0; i < listBean.size(); i++) {
			mapList.add(BeanUtils.Bean2Map(listBean.get(i)));
		}

		return mapList;
	}

	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HELLO_WORLD->HelloWorld
	 *
	 * @param name
	 *            转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camel(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母小写
			return name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String camels[] = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 处理真正的驼峰片段
			if (result.length() == 0) {
				// 第一个驼峰片段，全部字母都小写
				result.append(camel.toLowerCase());
			} else {
				// 其他的驼峰片段，首字母大写
				result.append(camel.substring(0, 1).toUpperCase());
				result.append(camel.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {

		UsrInfo a = new UsrInfo();
		a.setUsrName("sdasdad");
		Map<String, Object> map = BeanUtils.Bean2Map(a);
		System.out.println(map.get("USR_NAME"));

	}

	public static <T> T Map2Bean(Map<String, Object> map, Class<T> Bean) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		// 反射生成一个新的bean对象
		T a = Bean.newInstance();
		// 获取bean对象的fields，遍历这个对象的fields。若其中某个field的name转下划线大写后可以在map中找到，则对其赋值
		Field[] fields = a.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			String fieldName = fields[i].getName();

			if (map.get((BeanUtils.underline(fieldName))) != null && !"".equals(map.get((BeanUtils.underline(fieldName))))) {
				Object value = map.get((BeanUtils.underline(fieldName)));
				String type = fields[i].getType().getName();
				if(type.indexOf("BigDecimal")>-1) {
					value = BeanUtils.TransToBigDecimal(value);
				}
				if(type.indexOf("Long")>-1) {
					value = BeanUtils.TransToLong(value);
				}
				if(type.indexOf("long")>-1) {
					value = BeanUtils.TransToLong(value).longValue();
				}
				fields[i].set(a, value);
			}
		}
		return a;
	}

	public static <T> T Map2BeanNoChangeFieldName(Map<String, Object> map, Class<T> Bean) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		// 反射生成一个新的bean对象
		T a = Bean.newInstance();
		// 获取bean对象的fields，遍历这个对象的fields。若其中某个field的name转下划线大写后可以在map中找到，则对其赋值
		Field[] fields = a.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			String fieldName = fields[i].getName();
			if (map.get(fieldName) != null&&!"".equals(map.get(fieldName))) {
				Object value = map.get(fieldName);
				String type = fields[i].getType().getName();
				if(type.indexOf("BigDecimal")>-1) {
					value = BeanUtils.TransToBigDecimal(value);
				}
				if(type.indexOf("Long")>-1) {
					value = BeanUtils.TransToLong(value);
				}
				if(type.indexOf("long")>-1) {
					value = BeanUtils.TransToLong(value).longValue();
				}
				fields[i].set(a, value);
			}
		}
		return a;
	}

	public static <T extends Object> List<T> MapList2BeanList(List<Map<String, Object>> listMap, Class<T> Bean) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		List<T> beanList = new ArrayList<T>();
		if (listMap.isEmpty()) {
			return beanList;
		}

		for (int i = 0; i < listMap.size(); i++) {
			beanList.add(BeanUtils.Map2Bean(listMap.get(i), Bean));
		}
		return beanList;
	}

	public static BigDecimal TransToBigDecimal(Object obj) {
		String str = obj.toString();
		return new BigDecimal(str);
	}

	public static Long TransToLong(Object obj) {
		String str = obj.toString();
		return Long.parseLong(str);
	}

	/**
	 * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HelloWorld->HELLO_WORLD
	 *
	 * @param name
	 *            转换前的驼峰式命名的字符串
	 * @return 转换后下划线大写方式命名的字符串
	 */
	public static String underline(String name) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			// 将第一个字符处理成大写
			result.append(name.substring(0, 1).toUpperCase());
			// 循环处理其余字符
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				// 在大写字母前添加下划线
				if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
					result.append("_");
				}
				// 其他字符直接转成大写
				result.append(s.toUpperCase());
			}
		}
		return result.toString();
	}
}
