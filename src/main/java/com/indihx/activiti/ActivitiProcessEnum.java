package com.indihx.activiti;

import org.apache.commons.lang.StringUtils;

/***
 * 
 * <p>
 * 描 述: 流程对应的Service处理类，Service主要处理流程提交对正副表的操作，流程结束对正表的操作
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2018年1月5日
 * </p>
 * 
 * @author 谢追辉
 */
public enum ActivitiProcessEnum {

	LEAVE_PROCESS("LEAVE_PROCESS", "leaveInfoServiceImpl"),
	
	LEAVE_HUIQIAN("LEAVE_HUIQIAN", "leaveInfoServiceImpl"),
	
	INSIDE_APPRAISAL("INSIDE_APPRAISAL", "acceptContractImpl"),
	
	HOUSE_BUILDING("HOUSE_BUILDING","aqBuildFromServiceImpl"),
	
	HOUSE_MODIFY("HOUSE_MODIFY","aqBuildModifyServiceImpl"),
	
	HOUSE_DELETE("HOUSE_DELETE","aqBuildDeleteServiceImpl"),
	
	//项目信息管理流程-流程类型|业务实现类名首字母小写
	SECT_ADD_FLOW(FlowKind.CONSTANT_XMCJ,"sectServiceImpl"),
	SECT_EDIT_FLOW(FlowKind.CONSTANT_XMXG,"sectServiceImpl"),
	SECT_DELETE_FLOW(FlowKind.CONSTANT_XMZX,"sectServiceImpl"),
	//物业公司管理流程
	CSP_ADD_FLOW(FlowKind.CONSTANT_WYGSCJ,"cspServiceImpl"),
	CSP_XG_FLOW(FlowKind.CONSTANT_WYGSXG,"cspServiceImpl"),
	CSP_DEL_FLOW(FlowKind.CONSTANT_WYGSZX,"cspServiceImpl"),
	//诚信档案管理
	CREDIT_BAD_FLOW(FlowKind.CONSTANT_BADRECORD,"creditBadRecordServiceImpl"),
	CREDIT_APPEAL_FLOW(FlowKind.CONSTANT_BADAPPEAL,"creditApealServiceImpl")
	;

	private String beanId;// 状态值
	private  String appType; // 流程类型

	private ActivitiProcessEnum(String appType, String beanId) {
		this.beanId = beanId;
		this.appType = appType;
	}

	public String getBeanId() {
		return beanId;
	}

	public String getAppType() {
		return appType;
	}
	
	/***
	 * 根据类型得到枚举信息
	 * @param appType 流程类型
	 * @return 枚举
	 */
	public static ActivitiProcessEnum getBeanIdByAppType(String appType) {
		for (ActivitiProcessEnum processEnum : ActivitiProcessEnum.values()) {
			if (StringUtils.equals(appType, processEnum.appType)) {
				return processEnum;
			}
		}
		return null;
	}
}
