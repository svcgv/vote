package com.indihx.excel.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.indihx.excel.dao.ExcelFileMapper;
import com.indihx.excel.entity.ExcelFileEntity;
import com.indihx.excel.service.ExcelFileService;


@Service("execelFileService")
public class ExcelFileServiceImpl implements ExcelFileService {

	@Resource
	ExcelFileMapper excelFileMapper;

	@Override
	public long insert(ExcelFileEntity entity) {
		// TODO Auto-generated method stub
		return excelFileMapper.insert(entity);
	}

	@Override
	public ExcelFileEntity queryFileInfoByFileCode(String code) {
		// TODO Auto-generated method stub
		List<ExcelFileEntity> list = excelFileMapper.queryFileInfoByFileCode(code);
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	

}
