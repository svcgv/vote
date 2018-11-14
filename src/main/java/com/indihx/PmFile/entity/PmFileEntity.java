package com.indihx.PmFile.entity;

import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-06 19:33:25
 */
public class PmFileEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	private String usrName;
	private long fileId;
	/**
	 * 
	 */

	private String fileUploadName;
	/**
	 * 
	 */
	private String fileBusinessType;

	private String filePath;
	/**
	 * 
	 */

	private String fileSaveName;
	/**
	 * 
	 */

	private String uploadType;
	/**
	 * 
	 */

	private long fileSize;
	/**
	 * 
	 */

	private long foreignId;
	/**
	 * 
	 */

	private String foreignCode;
	/**
	 * 
	 */

	private String remark;
	/**
	 * 
	 */

	private long creatorId;
	/**
	 * 
	 */

	private String createTime;
	/**
	 * 
	 */

	private long modifier;
	/**
	 * 
	 */

	private String modifyTime;
	/**
	 * 
	 */

	private String isDelete;

	/**
	 * 设置：
	 */
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	
	public String getUsrName() {
		return usrName;
	}


	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}


	/**
	 * 获取：
	 */
	public long getFileId() {
		return fileId;
	}

	public String getFileBusinessType() {
		return fileBusinessType;
	}

	public void setFileBusinessType(String fileBusinessType) {
		this.fileBusinessType = fileBusinessType;
	}

	/**
	 * 设置：
	 */
	public void setFileUploadName(String fileUploadName) {
		this.fileUploadName = fileUploadName;
	}

	/**
	 * 获取：
	 */
	public String getFileUploadName() {
		return fileUploadName;
	}

	/**
	 * 设置：
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 获取：
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * 设置：
	 */
	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}

	/**
	 * 获取：
	 */
	public String getFileSaveName() {
		return fileSaveName;
	}

	/**
	 * 设置：
	 */
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	/**
	 * 获取：
	 */
	public String getUploadType() {
		return uploadType;
	}

	/**
	 * 设置：
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * 获取：
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * 设置：
	 */
	public void setForeignId(long foreignId) {
		this.foreignId = foreignId;
	}

	/**
	 * 获取：
	 */
	public long getForeignId() {
		return foreignId;
	}

	/**
	 * 设置：
	 */
	public void setForeignCode(String foreignCode) {
		this.foreignCode = foreignCode;
	}

	/**
	 * 获取：
	 */
	public String getForeignCode() {
		return foreignCode;
	}

	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置：
	 */
	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * 获取：
	 */
	public long getCreatorId() {
		return creatorId;
	}

	/**
	 * 设置：
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置：
	 */
	public void setModifier(long modifier) {
		this.modifier = modifier;
	}

	/**
	 * 获取：
	 */
	public long getModifier() {
		return modifier;
	}

	/**
	 * 设置：
	 */
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 获取：
	 */
	public String getModifyTime() {
		return modifyTime;
	}

	/**
	 * 设置：
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * 获取：
	 */
	public String getIsDelete() {
		return isDelete;
	}
}
