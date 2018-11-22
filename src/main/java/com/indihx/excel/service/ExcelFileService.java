package com.indihx.excel.service;

import com.indihx.excel.entity.ExcelCellEntity;
import com.indihx.excel.entity.ExcelFileEntity;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ${comments}
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-27 20:54:52
 */
public interface ExcelFileService {

	public long insert(ExcelFileEntity entity);
	public ExcelFileEntity queryFileInfoByFileCode(String code);
	public XSSFWorkbook getExcelByListBeanAndExcelCode(List<Map<String,Object>> list,String code,String sheetName);
}

