package com.indihx.excel.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.indihx.excel.dao.ExcelCellMapper;
import com.indihx.excel.entity.ExcelCellEntity;
import com.indihx.excel.service.ExcelCellService;


@Service("excelCellService")
public class ExcelCellServiceImpl implements ExcelCellService {

   @Resource
   ExcelCellMapper excelCellMapper;
   
   public long insert(ExcelCellEntity entity) {
	   return excelCellMapper.insert(entity);
   }

@Override
public List<ExcelCellEntity> queryCellListBySheetId(long id) {
	// TODO Auto-generated method stub
	return excelCellMapper.queryCellListBySheetId(id);
}
}
