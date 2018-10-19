/**
 * 
 */
package com.indihx.comm;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: 系统内公共常量定义</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月9日下午2:25:43</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>InitSysConstants.java</p>
 * <p>用于定义系统全局常量</p>
 */
public class InitSysConstants {
	
	//系统全局session名
	public final static String  USER_SESSION="PMS_USER";

	/*市局机构ID*/
	public final static String  OrgIdShiJu ="1000";//市局ID
	
	/*机构类型-与字典值对应*/
	public final static String  ORGTYPE_SHIJU ="01";//市局
	public final static String  ORGTYPE_QUJU ="02";//区局
	public final static String  ORGTYPE_JIEDAO ="03";//街道办
	public final static String  ORGTYPE_JUWEIHUI ="04";//居委会
	public final static String  ORGTYPE_WYGS ="05";//物业公司
	public final static String  ORGTYPE_YZDH ="06";//业主大会
	
	/*系统角色-与系统Role配置值对应(之后有改动统一再此修改与表中一致--有新增的或不同的，不能改变以上的值只能增加)*/
	public final static String  ROLE_CMD ="999";//系统管理员
	public final static String  ROLE_SHIJU ="1000";//市局
	public final static String  ROLE_QUJU ="1001";//区局
	public final static String  ROLE_JIEDAO ="1002";//街道办
	public final static String  ROLE_JUWEIHUI ="1003";//居委会
	public final static String  ROLE_WYGS ="1004";//物业公司
	
	/*数据状态*/
	public final static String  DATA_ZhengChang ="00";//正常---正式表中有效数据
	public final static String  DATA_ZhanCun ="01";//暂存---流程中的过程数据-一般存在于临时或副本表中
	public final static String  DATA_YiZhuXiao ="04";//已注销----正式表中无效数据
	public final static String  DATA_XiuGaiZhong ="02";//修改中----正式表中数据正在修改申请（此类数据不能再次发起修改和删除流程）
	public final static String  DATA_ShanChuZhong ="03";//删除中----正式表中数据正在删除/注销申请（此类数据不能再次发起修改和删除流程）
	

	/*机构状态-与字典值对应*/
	public final static String  ORG_STATUS_ZhengChangShiYong ="0";
	public final static String  ORG_STATUS_JinYogn ="1";
	public final static String  ORG_STATUS_YiZhuXiao ="2";
	
	/*人员类型-与字典值对应*/
	public final static String  USER_TYPE_XMJL ="01";
	
	/*数据状态-与字典值对应*/
	public final static String  INFO_STATUS_ZhegnChang ="00";
	public final static String  INFO_STATUS_ZanCun ="01";
	public final static String  INFO_STATUS_XiuGaiZhong ="02";
	public final static String  INFO_STATUS_ShanChuZhong ="03";
	public final static String  INFO_STATUS_YiZhuXiao ="04";

	/*证件类型-与字典值对应*/
	public final static String  CERTTYPE_SFZ ="1";
	
	/*性别-与字典值对应*/
	public final static String  SEXTYPE_NAN ="1";
	public final static String  SEXTYPE_NV ="2";
	
	/*文化程度-与字典值对应*/
	public final static String  EDUCATION_ChuZhong ="1";
	public final static String  EDUCATION_GaoZhong ="2";
	public final static String  EDUCATION_DaZhuan ="3";
	public final static String  EDUCATION_BenKe ="4";
	public final static String  EDUCATION_ShuoShiYanJiuSheng ="5";
	public final static String  EDUCATION_BoShiYanJiuSheng ="6";
	
	/*诚信指标类型-与字典值对应*/
	public final static String  CreditQuotaKind_LH ="01";//良好信息
	public final static String  CreditQuotaKind_BL ="02";//不良信息
	
	
	/*电子表决结果-与字典值对应*/
	public final static String  VoteResult_TY ="01";//同意
	public final static String  VoteResult_BTY="02";//不同意
	public final static String  VoteResult_QQ ="03";//弃权
	
	/*电子表决方式-与字典值对应*/
	public final static String  VoteWays_DZBJ ="01";//电子表决
	public final static String  VoteWays_SDBL ="02";//手动补录
	
	/*电子表决状态-与字典值对应*/
	//public final static String  VoteStatus_ZhiDingBiaoJueYiTi ="01";//拟制表决议题
	public final static String  VoteStatus_QueDingBiaoJueFanWei ="01";//待确定表决范围
	public final static String  VoteStatus_GuanLianFenHu = "02";//已关联分户
	public final static String  VoteStatus_BiaoJueZhong  ="03";//表决中
	public final static String  VoteStatus_BiaoJueWanJie ="04";//表决完结
	//public final static String  VoteStatus_ZhuXiao ="05";//已注销
	
	public final static String	VOTE_EFFECTIVE = "00"; //有效
	public final static String  Vote_NONEFFECTIVE = "01"; //无效
	
	public final static String  WsStatus_ZhengChang = "00";
	
	/*微信公众号相关参数, 放到PARAMS_INFO表里*/
	//public final static String TOKEN = "11111111";
	//public final static String APPID = "wx0faa55f1a53f29c5";
	//public final static String SECRET = "a9a586b82bfb51e89a35a51a443d517f";
	
	/*定义一个页面size*/
	public final static int BIG_SIZE = 20;
	public final static int MIDDLE_SIZE = 10;
	public final static int SMALL_SIZE = 5;
	
	//投票系统身份证上传路径
	public final static String CERT_PATH = "d:/vote/idcard/";
}
