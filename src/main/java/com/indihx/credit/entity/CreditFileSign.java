/**
 * 
 */
package com.indihx.credit.entity;

import java.io.Serializable;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CreditFileSign.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月16日上午10:46:11</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>CreditFileSign.java</p>
 * <p></p>
 */
public class CreditFileSign implements Serializable{

	String signId;
	String credit_code;
	String fileTypeId;
	String fileType;
	String isSign;
	String tranType;
	
	/**
	 * @return the signId
	 */
	public String getSignId() {
		return signId;
	}
	/**
	 * @param signId the signId to set
	 */
	public void setSignId(String signId) {
		this.signId = signId;
	}
	
	/**
	 * @param signId the signId to set
	 */
	public void setSignId(Long signId) {
		this.signId = signId.toString();
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
	 * @return the isSign
	 */
	public String getIsSign() {
		return isSign;
	}
	/**
	 * @param isSign the isSign to set
	 */
	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}
	
	/**
	 * @return the fileTypeId
	 */
	public String getFileTypeId() {
		return fileTypeId;
	}
	/**
	 * @param fileTypeId the fileTypeId to set
	 */
	public void setFileTypeId(String fileTypeId) {
		this.fileTypeId = fileTypeId;
	}
	/**
	 * @param fileTypeId the fileTypeId to set
	 */
	public void setFileTypeId(Long fileTypeId) {
		this.fileTypeId = fileTypeId.toString();
	}
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @return the tranType
	 */
	public String getTranType() {
		return tranType;
	}
	/**
	 * @param tranType the tranType to set
	 */
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	
}
