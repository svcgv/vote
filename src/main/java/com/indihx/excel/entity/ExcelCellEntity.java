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
public class ExcelCellEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	private long excelCellId;
	/**
	 * $column.comments
	 */
	private long excelSheetId;
	/**
	 * $column.comments
	 */
	private String excelCellCnName;
	/**
	 * $column.comments
	 */
	private String excelCellEnName;
	/**
	 * $column.comments
	 */
	private int excelCellColNum;
	/**
	 * $column.comments
	 */
	private int excelCellRowNum;
	/**
	 * $column.comments
	 */
	private String excelCellType;
	/**
	 * $column.comments
	 */
	private String excelDataType;
	/**
	 * $column.comments
	 */
	private String excelDefaultValue;
	/**
	 * $column.comments
	 */
	private String isDelete;
	public long getExcelCellId() {
		return excelCellId;
	}
	public void setExcelCellId(long excelCellId) {
		this.excelCellId = excelCellId;
	}
	public long getExcelSheetId() {
		return excelSheetId;
	}
	public void setExcelSheetId(long excelSheetId) {
		this.excelSheetId = excelSheetId;
	}
	public String getExcelCellCnName() {
		return excelCellCnName;
	}
	public void setExcelCellCnName(String excelCellCnName) {
		this.excelCellCnName = excelCellCnName;
	}
	public String getExcelCellEnName() {
		return excelCellEnName;
	}
	public void setExcelCellEnName(String excelCellEnName) {
		this.excelCellEnName = excelCellEnName;
	}
	public int getExcelCellColNum() {
		return excelCellColNum;
	}
	public void setExcelCellColNum(int excelCellColNum) {
		this.excelCellColNum = excelCellColNum;
	}
	public int getExcelCellRowNum() {
		return excelCellRowNum;
	}
	public void setExcelCellRowNum(int excelCellRowNum) {
		this.excelCellRowNum = excelCellRowNum;
	}
	public String getExcelCellType() {
		return excelCellType;
	}
	public void setExcelCellType(String excelCellType) {
		this.excelCellType = excelCellType;
	}
	public String getExcelDataType() {
		return excelDataType;
	}
	public void setExcelDataType(String excelDataType) {
		this.excelDataType = excelDataType;
	}
	public String getExcelDefaultValue() {
		return excelDefaultValue;
	}
	public void setExcelDefaultValue(String excelDefaultValue) {
		this.excelDefaultValue = excelDefaultValue;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
