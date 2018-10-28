package com.indihx.excel.cotroller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.comm.util.DataTypeTransUtil;
import com.indihx.excel.entity.ExcelCellEntity;
import com.indihx.excel.entity.ExcelFileEntity;
import com.indihx.excel.entity.ExcelSheetEntity;
import com.indihx.excel.service.ExcelCellService;
import com.indihx.excel.service.ExcelFileService;
import com.indihx.excel.service.ExcelSheetService;

/***
 * 
 * <p>
 * 描 述: 用户管理
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年2月16日
 * </p>
 * 
 * @author 谢追辉
 */
@Controller
@RequestMapping("/excel")
public class ExcelController extends AbstractBaseController {
	@Resource
	ExcelCellService excelCellService;
	@Resource
	ExcelSheetService excelSheetService;
	@Resource
	ExcelFileService excelFileService;
	/*
	 * @Autowired private TableHeaderInfoService tableHeaderInfoService;
	 */

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	/***
	 * 查询用户信息，返回用页面
	 * 
	 * @return 返回页面模版
	 * @throws IOException
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> qryUsrInfo(@RequestParam("file") MultipartFile[] myfiles, @RequestParam("fileCode") String fileCode, HttpServletRequest request) throws IOException {

		List<ExcelCellEntity> cellList = getExcelByFileCode(fileCode);
		String SheetType = getExcelType(cellList);

		Map<String, Object> map = new HashMap<String, Object>();
		if (myfiles.length > 0) {
			MultipartFile f = myfiles[0];
			CommonsMultipartFile cFile = (CommonsMultipartFile) f;
			DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
			InputStream inputStream = fileItem.getInputStream();
			XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
			// for(int i = 0;i<xwb.getNumberOfSheets();i++) {
			XSSFSheet sx = xwb.getSheetAt(0);
			/*XSSFRow row = sx.getRow(33);
			XSSFCell cell = row.getCell(2);
			String res = cell.toString();
			map.put(res, res);*/
			Map<String, Object> resMap = getDataFromExcelByList(sx,cellList);
			// }
			inputStream.close();
			return resMap;
		}
		map.put("error", "解析失败");
		return map;
	}

	/**
	 * 获取表格尺寸
	 * 
	 * @param sx
	 * @return
	 */
	public int getExcelSize(XSSFSheet sx) {
		return sx.getLastRowNum();
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

	/**
	 * 通过fileCode获取cellList
	 * 
	 * @param code
	 * @return
	 */
	public List<ExcelCellEntity> getExcelByFileCode(String code) {
		ExcelFileEntity file = excelFileService.queryFileInfoByFileCode(code);
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
	 * 列表的方式解析表格
	 * 
	 * @return map("list",List<map>)
	 */
	public Map<String, Object> getDataFromExcelByList(XSSFSheet sx,List<ExcelCellEntity> cellList) {
		List<Map<String,Object>> res = new ArrayList<>();
		int rowNum = getExcelSize(sx);
		int row = cellList.get(0).getExcelCellRowNum();
		for(int i=row;i<rowNum;i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			for(int j = 0;j<cellList.size();j++) {
				ExcelCellEntity cell = cellList.get(j);
				XSSFRow xr = sx.getRow(i);
				XSSFCell cellEntity = xr.getCell(cell.getExcelCellColNum());
				String value = null;
				if(cellEntity!= null) {
					value = xr.toString();
				}
				map.put(cell.getExcelCellEnName(), value);
				res.add(map);
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", res);
		return map;
	}

	/**
	 * 固定坐标的方式解析表格
	 * 
	 * @return map{}
	 */
	public Map<String, Object> getDataFromExcelByLocation(XSSFSheet sx,List<ExcelCellEntity> cellList) {
		List<Map<String,Object>> res = new ArrayList<>();
		int rowNum = getExcelSize(sx);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", res);
		return map;
	}

	@RequestMapping(value = "/uploadFileView")
	public ModelAndView uploadFileView(String relaTable, String relaTabId, String relaTypeId) {
		ExcelCellEntity entity = new ExcelCellEntity();
		entity.setExcelSheetId(11l);
		entity.setExcelCellId(1l);
		entity.setIsDelete("00");
		excelCellService.insert(entity);
		Map<String, Object> map = new HashMap<>();
		ModelAndView view = new ModelAndView();
		view.setViewName("/cust/testExcel");
		return view;
	}

	@RequestMapping(value = "/problemReport")
	public ModelAndView problemReport() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/cust/problemReport");
		return view;
	}

	@RequestMapping(value = "/detailList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> detailList(@RequestBody Map<String, Object> param,
			HttpServletRequest request) {
		/*
		 * TableHeaderInfoEntity entity = new TableHeaderInfoEntity();
		 * 
		 * entity.setTableId(tableId); List<TableInfoEntity> tableHeaderInfo =
		 * tableHeaderInfoService.queryDetail(entity);
		 */
		Map<String, Object> a = new HashMap<String, Object>();
		a.put("tableHeaderInfo", "ok");
		a.put("param", param);
		return a;
	}

}
