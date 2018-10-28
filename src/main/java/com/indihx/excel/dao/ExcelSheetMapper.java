package com.indihx.excel.dao;

import java.util.List;

import com.indihx.excel.entity.ExcelFileEntity;
import com.indihx.excel.entity.ExcelSheetEntity;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-27 20:54:52
 */
public interface ExcelSheetMapper {
	public long insert(ExcelSheetEntity entity);
	public List<ExcelSheetEntity> querySheetListByFileId(long id);
}
