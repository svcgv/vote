package com.indihx.excel.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-28 10:35:48
 */
public class ExcelFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	private long excelFileId;
	/**
	 * $column.comments
	 */
	private String excelFileCnName;
	/**
	 * $column.comments
	 */
	private String excelFileEnName;
	/**
	 * $column.comments
	 */
	private String excelDescripte;
	/**
	 * $column.comments
	 */
	private String remark;

	/**
	 * 设置：${column.comments}
	 */
	public void setExcelFileId(long excelFileId) {
		this.excelFileId = excelFileId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getExcelFileId() {
		return excelFileId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setExcelFileCnName(String excelFileCnName) {
		this.excelFileCnName = excelFileCnName;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getExcelFileCnName() {
		return excelFileCnName;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setExcelFileEnName(String excelFileEnName) {
		this.excelFileEnName = excelFileEnName;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getExcelFileEnName() {
		return excelFileEnName;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setExcelDescripte(String excelDescripte) {
		this.excelDescripte = excelDescripte;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getExcelDescripte() {
		return excelDescripte;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getRemark() {
		return remark;
	}
}
