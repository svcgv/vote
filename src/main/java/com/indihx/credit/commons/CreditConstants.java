/**
 * 
 */
package com.indihx.credit.commons;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CreditConstants.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月5日上午9:26:33</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>CreditConstants.java</p>
 * <p></p>
 */
public class CreditConstants {
	
	//指标状态私有常量
	public final static String QuotaStatus_ZC ="正常";
	public final static String QuotaStatus_SX ="作废";

	//指标适用主体
	public final static String QuotaObject_WYGS ="01";
	public final static String QuotaObject_XMJL ="02";
	public final static String QuotaObject_WYGSXMJL ="03";
	
	//不良信息记录状态
	public final static String RecordStatus_GD ="00";//归档
	public final static String RecordStatus_LR ="01";//建档
	public final static String RecordStatus_GZ ="02";//告知
	public final static String RecordStatus_GZING ="03";//告知中
	
	//异议申诉标志
	public final static String AppealFlag_WeiShenSu ="0";//未异议申诉
	public final static String AppealFlag_YiShenSU ="1";//已异议申诉
	
	//异议申诉状态
	public final static String AppealStatus_APPLY ="01";//申诉申请
	public final static String AppealStatus_AUDIT ="02";//申诉审核
	public final static String AppealStatus_FINISH="03";//申诉完成
}
