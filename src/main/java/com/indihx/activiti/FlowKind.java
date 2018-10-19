/**
 * 
 */
package com.indihx.activiti;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: 流程类型常量</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月8日下午5:32:18</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>FlowKind.java</p>
 * <p>与字典表中配置名字必须相同</p>
 */
public class FlowKind {

	//项目采集流程
	public final static String  CONSTANT_XMCJ ="SECTADDFLOW";
	//项目修改流程
	public final static String  CONSTANT_XMXG ="SECTEDITFLOW";
	//项目注销流程
	public final static String  CONSTANT_XMZX ="SECTDELEFLOW";
	
	//物业公司采集流程
	public final static String  CONSTANT_WYGSCJ ="CSPADDFLOW";
	//物业公司修改流程
	public final static String  CONSTANT_WYGSXG ="CSPXGFLOW";
	//物业公司注销流程
	public final static String  CONSTANT_WYGSZX ="CSPDELFLOW";
	
	//不良信息建档
	public final static String  CONSTANT_BADRECORD ="CREDITRECORDBL";
	//不良信息异议申诉
	public final static String  CONSTANT_BADAPPEAL ="CREDITAPPEAL";
}
