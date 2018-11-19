package com.indihx.excel.dao;

import java.util.List;
import java.util.Map;

import com.indihx.excel.entity.ExcelCellEntity;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-27 20:54:51
 */
public interface ExcelCellMapper{
	public long insert(ExcelCellEntity entity);
	public List<ExcelCellEntity> queryCellListBySheetId(long id);
	public List<ExcelCellEntity> queryCellListBySheetIdAndFixType(Map<String,Object> map);
}
