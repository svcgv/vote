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
public class ExcelSheetEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	private long excelSheetId;
	/**
	 * $column.comments
	 */
	private long excelFileId;
	/**
	 * $column.comments
	 */
	private String excelSheetCnName;
	/**
	 * $column.comments
	 */
	private String excelSheetEnName;
	/**
	 * $column.comments
	 */
	private String remark;
	/**
	 * $column.comments
	 */
	private String isValid;

	/**
	 * 设置：${column.comments}
	 */
	public void setExcelSheetId(long excelSheetId) {
		this.excelSheetId = excelSheetId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public long getExcelSheetId() {
		return excelSheetId;
	}
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
	public void setExcelSheetCnName(String excelSheetCnName) {
		this.excelSheetCnName = excelSheetCnName;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getExcelSheetCnName() {
		return excelSheetCnName;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setExcelSheetEnName(String excelSheetEnName) {
		this.excelSheetEnName = excelSheetEnName;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getExcelSheetEnName() {
		return excelSheetEnName;
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
	/**
	 * 设置：${column.comments}
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getIsValid() {
		return isValid;
	}
}
