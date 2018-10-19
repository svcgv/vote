package com.indihx.util;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;

/***
 * 
 * <p>描 述: entity与vo相互转换</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年2月28日</p>
 * @author 谢追辉
 */
public class EntityVoConverter {
	private Logger logger = LoggerFactory.getLogger(EntityVoConverter.class);
	private Object obj = null;
	private Object objDto = null;
	
	/***
	 * 外部调用
	 * @param objDto  需要转换的对象
	 * @param obj   转换后的对象
	 */
	public static void Convert(Object objDto, Object obj) {
		try {
			EntityVoConverter converter = new EntityVoConverter();
			converter.entityConvertDto(objDto, obj);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("对象属性转化失败"+ExceptionUtil.getErrorMsg(e));
		} catch (SecurityException e) {
			throw new BusinessException("对象属性转化失败"+ExceptionUtil.getErrorMsg(e));
		} catch (IllegalAccessException e) {
			throw new BusinessException("对象属性转化失败"+ExceptionUtil.getErrorMsg(e));
		} catch (InvocationTargetException e) {
			throw new BusinessException("对象属性转化失败"+ExceptionUtil.getErrorMsg(e));
		}
	}

	public  Object entityConvertDto(Object obj, Object objDto)
			throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException {
		Class<?> clazz = obj.getClass();
		this.obj = obj;
		this.objDto = objDto;
		Class<?> dtoClazz = objDto.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			invoke1(clazz.getDeclaredMethods(), dtoClazz.getDeclaredMethods(), f.getName());
		}
		return objDto;
	}

	private void invoke1(Method[] methods, Method[] methodDtos, String name)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String upperName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		String setterName = "set" + upperName;
		String getterName = "get" + upperName;
		Method method = null;
		Method methodDto = null;
		/* gt; */method = this.getMethodByName(methods, getterName);
		methodDto = this.getMethodByName(methodDtos, setterName);
		if(methodDto == null || method == null){ //当有一个实体不存在get、set方法时，则该字段不进行转换
			return;
		}
		//得到set方法的方法类型
		Type[] ts = methodDto.getGenericParameterTypes();
		String xclass = ts[0].toString(); //class java.lang.String
		//得到get方法
		Type getType = method.getGenericReturnType();
		
		Object _obj = getValue(method, getType.toString(), xclass);
		if(_obj == null){ //当值为空时，直接返回
			return;
		}
		if (method != null && methodDto != null) {
			methodDto.invoke(this.objDto,_obj);
		}
	}
	
	/***
	 * 对于类型不一致的进行转换
	 * @param method 
	 * @param getType  get类型
	 * @param setType  set类型
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	private Object getValue(Method method,String getType,String setType) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Object obj_ = method.invoke(this.obj,null); //得到get的值
		if(obj_ == null || obj_ == ""){ //值为空，直接返回long
			return null;
		}else if(getType.equals(setType)){  //两个类型相等，直接返回
			return obj_;
		}else{
			Object obj=null;
			if("class java.lang.String".equals(getType) && "class java.lang.Long".equals(setType)){
				obj = new Long(obj_.toString());
			}else if("class java.lang.String".equals(setType) && "class java.lang.Long".equals(getType)){
				obj=obj_.toString();
			}else if (setType.equals(getType)) {
				obj = obj_;
			}else{
				logger.info(getType+"暂只支持Long和String互转，其他类型的转换则需要重新扩展！！"+setType);
			}
			return obj;
		}
	}

	private Method getMethodByName(Method[] methods, String methodName) {
		for (Method m : methods) {
			if (m.getName().equals(methodName)) {
				return m;
			}
		}
		return null;
	}
}
