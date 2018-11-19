package com.indihx.excel.service.impl;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.indihx.excel.dao.ExcelFileMapper;
import com.indihx.excel.entity.ExcelCellEntity;
import com.indihx.excel.entity.ExcelFileEntity;
import com.indihx.excel.entity.ExcelSheetEntity;
import com.indihx.excel.service.ExcelCellService;
import com.indihx.excel.service.ExcelFileService;
import com.indihx.excel.service.ExcelSheetService;


@Service("execelFileService")
public class ExcelFileServiceImpl implements ExcelFileService {
	@Resource
	ExcelCellService excelCellService;
	@Resource
	ExcelSheetService excelSheetService;
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
	
	@Override
	public <T> XSSFWorkbook getExcelByListBeanAndExcelCode(List<T> list, String fileCode,String sheetName) {
		// TODO Auto-generated method stub
		
		List<ExcelCellEntity> fixedList = getExcelByFileCode(fileCode,"01");
		List<ExcelCellEntity> cellList = getExcelByFileCode(fileCode,"00");
		
		
		Map<String, Object> map = new HashMap<String, Object>();
			XSSFWorkbook xwb = new XSSFWorkbook();
			XSSFSheet sheet = xwb.createSheet(sheetName);
			
			
			xwb.getSheetAt(0);
		return xwb;
	}
	
	public XSSFRow getRomBySheet(XSSFSheet sheet,int i){
		XSSFRow xr = sheet.getRow(i);
		if(xr!=null) {
			return xr;
		}
		return sheet.createRow(i);
	}
	public List<ExcelCellEntity> getExcelByFileCode(String code) {
		ExcelFileEntity file = queryFileInfoByFileCode(code);
		List<ExcelSheetEntity> sheetList = excelSheetService.querySheetListByFileId(file.getExcelFileId());
		Map<String, Object> tableData = new HashMap<String, Object>();
		List<ExcelCellEntity> cellListRes = null;
		if (sheetList.isEmpty()) {
			return null;
		} else {
			int len = sheetList.size();
			for (int i = 0; i < len; i++) {
				ExcelSheetEntity sheet = sheetList.get(i);
				List<ExcelCellEntity> cellList = excelCellService.queryCellListBySheetId(sheet.getExcelSheetId());
				cellListRes = cellList;
				tableData.put(sheet.getExcelSheetEnName(), cellList);
			}
		}
		return cellListRes;
	}
	
	public List<ExcelCellEntity> getExcelByFileCode(String code,String type) {
		ExcelFileEntity file = queryFileInfoByFileCode(code);
		List<ExcelSheetEntity> sheetList = excelSheetService.querySheetListByFileId(file.getExcelFileId());
		Map<String, Object> tableData = new HashMap<String, Object>();
		List<ExcelCellEntity> cellListRes = null;
		if (sheetList.isEmpty()) {
			return null;
		} else {
			int len = sheetList.size();
			for (int i = 0; i < len; i++) {
				ExcelSheetEntity sheet = sheetList.get(i);
				List<ExcelCellEntity> cellList = excelCellService.queryCellListBySheetId(sheet.getExcelSheetId());
				cellListRes = cellList;
				tableData.put(sheet.getExcelSheetEnName(), cellList);
			}
		}
		return cellListRes;
	}

	/**
	 * 获取表格类型 00-列表，01固定字段
	 * 
	 * @param list
	 * @return
	 */
	public String getExcelType(List<ExcelCellEntity> list) {
		ExcelCellEntity cell = list.get(0);
		if ("00".equals(cell.getExcelCellType())) {
			return "00";
		}
		return "01";
	}
	
	
}
