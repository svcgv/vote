/**
 * 
 */
package com.indihx.credit.entity;

import java.io.Serializable;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CreditAppeal.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月9日下午5:55:37</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>CreditAppeal.java</p>
 * <p></p>
 */
public class CreditAppeal implements Serializable {

	private static final long serialVersionUID = 1L;

	String appeal_code;
	String credit_code;
	String appeal_reasion;
	String app_id;
	String appeal_date;
	String appeal_time;
	String appeal_status;
	String appeal_desc;
	String appeal_remark;
	/**
	 * @return the appeal_code
	 */
	public String getAppeal_code() {
		return appeal_code;
	}
	/**
	 * @param appeal_code the appeal_code to set
	 */
	public void setAppeal_code(String appeal_code) {
		this.appeal_code = appeal_code;
	}
	/**
	 * @return the credit_code
	 */
	public String getCredit_code() {
		return credit_code;
	}
	/**
	 * @param credit_code the credit_code to set
	 */
	public void setCredit_code(String credit_code) {
		this.credit_code = credit_code;
	}
	/**
	 * @return the appeal_reasion
	 */
	public String getAppeal_reasion() {
		return appeal_reasion;
	}
	/**
	 * @param appeal_reasion the appeal_reasion to set
	 */
	public void setAppeal_reasion(String appeal_reasion) {
		this.appeal_reasion = appeal_reasion;
	}
	/**
	 * @return the app_id
	 */
	public String getApp_id() {
		return app_id;
	}
	/**
	 * @param app_id the app_id to set
	 */
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	/**
	 * @return the appeal_date
	 */
	public String getAppeal_date() {
		return appeal_date;
	}
	/**
	 * @param appeal_date the appeal_date to set
	 */
	public void setAppeal_date(String appeal_date) {
		this.appeal_date = appeal_date;
	}
	/**
	 * @return the appeal_time
	 */
	public String getAppeal_time() {
		return appeal_time;
	}
	/**
	 * @param appeal_time the appeal_time to set
	 */
	public void setAppeal_time(String appeal_time) {
		this.appeal_time = appeal_time;
	}
	/**
	 * @return the appeal_status
	 */
	public String getAppeal_status() {
		return appeal_status;
	}
	/**
	 * @param appeal_status the appeal_status to set
	 */
	public void setAppeal_status(String appeal_status) {
		this.appeal_status = appeal_status;
	}
	/**
	 * @return the appeal_desc
	 */
	public String getAppeal_desc() {
		return appeal_desc;
	}
	/**
	 * @param appeal_desc the appeal_desc to set
	 */
	public void setAppeal_desc(String appeal_desc) {
		this.appeal_desc = appeal_desc;
	}
	/**
	 * @return the appeal_remark
	 */
	public String getAppeal_remark() {
		return appeal_remark;
	}
	/**
	 * @param appeal_remark the appeal_remark to set
	 */
	public void setAppeal_remark(String appeal_remark) {
		this.appeal_remark = appeal_remark;
	}
	
	
}
