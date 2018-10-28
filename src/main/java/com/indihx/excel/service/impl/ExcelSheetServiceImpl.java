package com.indihx.excel.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.indihx.excel.dao.ExcelSheetMapper;
import com.indihx.excel.entity.ExcelSheetEntity;
import com.indihx.excel.service.ExcelSheetService;


@Service("excelSheetService")
public class ExcelSheetServiceImpl implements ExcelSheetService {
	@Resource
	ExcelSheetMapper excelSheetMapper;

	@Override
	public long insert(ExcelSheetEntity entity) {
		// TODO Auto-generated method stub
		return excelSheetMapper.insert(entity);
	}

	@Override
	public List<ExcelSheetEntity> querySheetListByFileId(long id) {
		// TODO Auto-generated method stub
		return excelSheetMapper.querySheetListByFileId(id);
	}
	
    

}
