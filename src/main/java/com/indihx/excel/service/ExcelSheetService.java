package com.indihx.excel.service;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.indihx.excel.entity.ExcelSheetEntity;

/**
 * ${comments}
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-27 20:54:52
 */
public interface ExcelSheetService {

	public long insert(ExcelSheetEntity entity);
	public List<ExcelSheetEntity> querySheetListByFileId(long id);
	
	
}

