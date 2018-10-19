package com.indihx.comm.util;
/***
 * 
 * <p>描 述: 定义前期物业招投标类型，后续流程变更新增，需要同步更新</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年5月25日</p>
 * @author 谢追辉
 */
public class EarlyProcessType {
	/**招标人申请*/
	public static final String APPLY = "apply"; 
	/**招标备案收件*/
	public static final String RECORD = "record"; 
	/**招标备案审核*/
	public static final String RECORDCHECK = "recordCheck"; 
	/**招标公告发布*/
	public static final String NOTICERELEASE = "noticeRelease"; 
	/**投标报名*/
	public static final String TENDERSIGN = "tenderSign"; 
	/**入围资格审核*/
	public static final String FINALCHECK = "finalCheck"; 
	/**入围名单*/
	public static final String FINALLIST = "finalList"; 
	/**专家抽取**/
	public static final String EXTRACTEPTINFO = "extractEptInfo"; 
	/**开始评标**/
	public static final String BIDEVALUATION = "BidEvaluation"; 
	/**中标获选人确认**/
	public static final String BIDWAITCONFIRM = "BidWaitConfirm"; 
	/**发布中标公告**/
	public static final String BIDNOTICE = "BidNotice"; 
	/**确认中标通知**/
	public static final String CONFIRMBIDNOTICE = "ConfirmBidNotice"; 
	/**发布中标公告**/
	public static final String RELEASENOTICE = "ReleaseNotice"; 
	/**确认中标人**/
	public static final String CONFIRMBIDPERSON = "ConfirmBidPerson";
}
