package com.indihx.excel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

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

	public List<ExcelCellEntity> getExcelByFileCode(String code) {
		ExcelFileEntity file = this.queryFileInfoByFileCode(code);
		List<ExcelSheetEntity> sheetList = this.excelSheetService.querySheetListByFileId(file.getExcelFileId());
		Map<String, Object> tableData = new HashMap<String, Object>();
		List<ExcelCellEntity> cellListRes = null;
		if (sheetList.isEmpty()) {
			return null;
		} else {
			int len = sheetList.size();
			for (int i = 0; i < len; i++) {
				ExcelSheetEntity sheet = sheetList.get(i);
				List<ExcelCellEntity> cellList = this.excelCellService.queryCellListBySheetId(sheet.getExcelSheetId());
				cellListRes = cellList;
				tableData.put(sheet.getExcelSheetEnName(), cellList);
			}
		}
		return cellListRes;
	}

	public List<ExcelCellEntity> getExcelByFileCode(String code,String type) {
		ExcelFileEntity file = this.queryFileInfoByFileCode(code);
		List<ExcelSheetEntity> sheetList = this.excelSheetService.querySheetListByFileId(file.getExcelFileId());
		Map<String, Object> tableData = new HashMap<String, Object>();
		List<ExcelCellEntity> cellListRes = null;
		if (sheetList.isEmpty()) {
			return null;
		} else {
			int len = sheetList.size();
			for (int i = 0; i < len; i++) {
				ExcelSheetEntity sheet = sheetList.get(i);
				List<ExcelCellEntity> cellList = this.excelCellService.queryCellListBySheetId(sheet.getExcelSheetId(),type);
				cellListRes = cellList;
				tableData.put(sheet.getExcelSheetEnName(), cellList);
			}
		}
		return cellListRes;
	}

	@Override
	public  XSSFWorkbook getExcelByListBeanAndExcelCode(List<Map<String,Object>> list, String fileCode,String sheetName) {
		// TODO Auto-generated method stub

		List<ExcelCellEntity> fixedList = this.getExcelByFileCode(fileCode,"01");
		List<ExcelCellEntity> cellList = this.getExcelByFileCode(fileCode,"00");


			XSSFWorkbook xwb = new XSSFWorkbook();
			XSSFSheet sheet = xwb.createSheet(sheetName);

			if(fixedList!=null&!fixedList.isEmpty()) {
				for(int i=0;i<fixedList.size();i++) {
					ExcelCellEntity cellEnt = fixedList.get(i);
					XSSFRow row = this.getRomBySheet(sheet,cellEnt.getExcelCellRowNum());
					XSSFCell cell = this.getRomBySheet(row,cellEnt.getExcelCellColNum());
					cell.setCellValue(cellEnt.getExcelDefaultValue());
				}
			}

			if(list==null||list.isEmpty()) {
				return xwb;
			}

			if(cellList!=null&!cellList.isEmpty()) {
				int baseRow = cellList.get(0).getExcelCellRowNum();
				for(int j=0;j<list.size();j++) {
					Map<String,Object> item = list.get(j);
					for(int k=0;k<fixedList.size();k++) {
						ExcelCellEntity ent = fixedList.get(k);
						if(item.get(ent.getExcelCellEnName())!=null) {
							XSSFRow row = this.getRomBySheet(sheet,baseRow);
							XSSFCell cell = this.getRomBySheet(row,ent.getExcelCellColNum());
							cell.setCellValue(item.get(ent.getExcelCellEnName()).toString());
						}
					}
					baseRow++;
				}
			}


		return xwb;
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

	public XSSFCell getRomBySheet(XSSFRow row,int i){
		XSSFCell xr = row.getCell(i);
		if(xr!=null) {
			return xr;
		}
		return row.createCell(i);
	}

	public XSSFRow getRomBySheet(XSSFSheet sheet,int i){
		XSSFRow xr = sheet.getRow(i);
		if(xr!=null) {
			return xr;
		}
		return sheet.createRow(i);
	}

	@Override
	public long insert(ExcelFileEntity entity) {
		// TODO Auto-generated method stub
		return this.excelFileMapper.insert(entity);
	}

	@Override
	public ExcelFileEntity queryFileInfoByFileCode(String code) {
		// TODO Auto-generated method stub
		List<ExcelFileEntity> list = this.excelFileMapper.queryFileInfoByFileCode(code);
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}


}
