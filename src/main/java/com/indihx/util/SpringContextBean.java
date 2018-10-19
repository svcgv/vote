package com.indihx.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/***
 * 
 * <p>
 * 描 述: 得到Spring的实体bean名称
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2018年1月3日
 * </p>
 * 
 * @author 谢追辉
 */
public class SpringContextBean {
	
	private static ApplicationContext context ;


	/***
	 * 得到注入到spring中的实体
	 * @param beanId 实体名称
	 * @return 返回实体对象
	 */
	public static Object getBean(String beanId) {
		if (context == null) {
			context = ContextLoader.getCurrentWebApplicationContext();
		}
		if (StringUtils.isEmpty(beanId) || context == null) {
			return null;
		}
		return context.getBean(beanId);
	}

}
