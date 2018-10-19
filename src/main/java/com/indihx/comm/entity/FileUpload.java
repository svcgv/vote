package com.indihx.comm.entity;

import java.io.Serializable;

/***
 * 
 * <p>描 述: 文件管理实体类</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月2日</p>
 * @author zhengwei
 */
public class FileUpload implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long fileId;   //文件ID

    private String oldFileName;  //老文件名称

    private String fileName;  //文件名称

    private String uploadDate;  //上传时间

    private String fileAddre;   //文件地址

    private String fileSts;   //文件状态

    private String uploadUser;  //上传人

    private String relaTable;   //关联表

    private Long relaTabId;   //关联表ID
    
    private String fileSize; // 文件大小
    
    private String fileType; // 文件类型
    
    private String fileTypeId; // 文件类型配置ID

    public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName == null ? null : oldFileName.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate == null ? null : uploadDate.trim();
    }

    public String getFileAddre() {
        return fileAddre;
    }

    public void setFileAddre(String fileAddre) {
        this.fileAddre = fileAddre == null ? null : fileAddre.trim();
    }

    public String getFileSts() {
        return fileSts;
    }

    public void setFileSts(String fileSts) {
        this.fileSts = fileSts == null ? null : fileSts.trim();
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser == null ? null : uploadUser.trim();
    }

    public String getRelaTable() {
        return relaTable;
    }

    public void setRelaTable(String relaTable) {
        this.relaTable = relaTable == null ? null : relaTable.trim();
    }

    public Long getRelaTabId() {
        return relaTabId;
    }

    public void setRelaTabId(Long relaTabId) {
        this.relaTabId = relaTabId;
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
    
}