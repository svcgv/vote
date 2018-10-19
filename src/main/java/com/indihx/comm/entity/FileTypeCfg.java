/**
 * 
 */
package com.indihx.comm.entity;

import java.io.Serializable;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: FileTypeCfg.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月15日下午5:20:38</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>FileTypeCfg.java</p>
 * <p></p>
 */
public class FileTypeCfg implements Serializable{

	private static final long serialVersionUID = 1L;
	
	long id;
	String tranType;
	String fileType;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	
}
