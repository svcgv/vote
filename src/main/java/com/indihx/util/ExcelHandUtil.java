package com.indihx.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 
 * <p>描 述: Excel表格处理列，支持2003 和2007</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年5月8日</p>
 * @author 谢追辉
 */
public class ExcelHandUtil {
	private static Logger logger = LoggerFactory.getLogger(ExcelHandUtil.class);

	/***
	 * 得到一个Excel处理对象
	 * @param path  Excel路径
	 * @return  返回一个workbook
	 */
	public static Workbook getWorkBook(String path){
		InputStream input =null;
		Workbook workbook = null;
		try {
			input = new FileInputStream(path);
			workbook = new XSSFWorkbook(input);
		} catch (FileNotFoundException e) {
			logger.error("文件流处理错误，Excel表格错误，"+path);
			e.printStackTrace();
		}catch (IOException e) {
			logger.error("建立WorkBokk错误，"+path);
			e.printStackTrace();
		}
		return workbook;
	}
	
	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	public static String getStringCellValue(Cell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}
	
}
