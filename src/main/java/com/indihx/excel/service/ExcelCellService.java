package com.indihx.excel.service;

import com.indihx.excel.dao.ExcelCellMapper;
import com.indihx.excel.entity.ExcelCellEntity;
import com.indihx.excel.entity.ExcelFileEntity;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * ${comments}
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-27 20:54:51
 */
public interface ExcelCellService{

	public long insert(ExcelCellEntity entity);
	public List<ExcelCellEntity> queryCellListBySheetId(long id);
	public List<ExcelCellEntity> queryCellListBySheetId(long id,String fixType);
}

