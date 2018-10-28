package com.indihx.excel.dao;

import java.util.List;

import com.indihx.excel.entity.ExcelFileEntity;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-27 20:54:52
 */
public interface ExcelFileMapper {
	public long insert(ExcelFileEntity entity);
	public List<ExcelFileEntity> queryFileInfoByFileCode(String code);
}
