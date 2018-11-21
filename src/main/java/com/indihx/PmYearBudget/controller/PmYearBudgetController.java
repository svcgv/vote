package com.indihx.PmYearBudget.controller;


import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.BeanUtils;
import com.indihx.util.UserUtil;
import com.indihx.comm.util.R;
import com.indihx.comm.util.RandomUtil;
import com.indihx.excel.service.ExcelFileService;
import com.indihx.excel.service.ExcelSheetService;
import com.indihx.comm.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.indihx.PmProjectInfo.service.PmProjectInfoService;
import com.indihx.PmYearBudget.entity.PmYearBudgetEntity;
import com.indihx.PmYearBudget.service.PmYearBudgetService;
import com.indihx.comm.InitSysConstants;
/**
 * ${comments}
 *
 * @author hb
 * @date 2018-11-18 10:35:36
 */
@Controller
@RequestMapping("/pmyearbudget")
public class PmYearBudgetController {
    @Autowired
    private PmYearBudgetService pmYearBudgetService;
    
    @Autowired
    private PmProjectInfoService pmProjectInfoService;
    
    @Autowired
    ExcelFileService excelFileService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmYearBudgetEntity> pmYearBudget = pmYearBudgetService.queryList(params);
        return R.ok().put("page", pmYearBudget);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("yearBudgetCode") String yearBudgetCode,HttpSession session){

		PmYearBudgetEntity entity = pmYearBudgetService.queryObject(yearBudgetCode);
        return R.ok().put("pmYearBudget", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmYearBudgetEntity pmYearBudget,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmYearBudget.setCreatorId(usesr.getUsrId());
    	pmYearBudget.setCreateTime(DateUtil.getDateTime());
    	pmYearBudget.setYearBudgetCode(RandomUtil.getCodeByType("YS"));
        pmYearBudgetService.insert(pmYearBudget);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmYearBudgetEntity pmYearBudget,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmYearBudget.setModifier(usesr.getUsrId());
    	pmYearBudget.setModifyTime(DateUtil.getDateTime());
        pmYearBudgetService.update(pmYearBudget);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody String yearBudgetCode,HttpSession session){
        pmYearBudgetService.delete(yearBudgetCode);
        return R.ok();
    }

    /**
     * 保存多个,全删全插
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    @RequestMapping(value="/saveList",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> saveList(@RequestBody Map<String,Object> pmYearBudget,HttpSession session) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
    	UsrInfo usesr = UserUtil.getUser(session);
    	
    	List<Map<String,Object>> listMap = (List<Map<String, Object>>) pmYearBudget.get("budgetList");
    	List<PmYearBudgetEntity> listBean = BeanUtils.MapList2BeanList(listMap, PmYearBudgetEntity.class);
        pmYearBudgetService.insertList(listBean,usesr.getUsrId());
        return R.ok();
    }
    
    /**
     * 列表
     */
    @RequestMapping(value="/queryAllListForUser",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> queryAllListForUser(@RequestBody Map<String, Object> params,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("creatorId", usesr.getUsrId());
		List<PmYearBudgetEntity> pmYearBudget = pmYearBudgetService.queryList(map);
		if(pmYearBudget==null||pmYearBudget.isEmpty()) {
			Map<String,Object> entity = new HashMap<String,Object>();
			//ToDo 状态为未完结的项目 且客户经理为当前用户的
			entity.put("isDelete", "00");
			entity.put("custManagerId", usesr.getUsrId());
			 return R.ok().put("page",pmProjectInfoService.queryList(entity));
		}
		
        return R.ok().put("page", pmYearBudget);
    }

    
    /**
     * 导出excel
     * @param pmYearBudget
     * @param session
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IOException 
     */
    @RequestMapping(value="/exportExcel",method=RequestMethod.POST)
    public @ResponseBody void exportExcel(@RequestBody Map<String,Object> map, HttpSession session, HttpServletResponse response) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException{
    	UsrInfo usesr = UserUtil.getUser(session);
    	List<Map<String,Object>> list = pmYearBudgetService.queryListByMap(map);
    	 XSSFWorkbook wb = excelFileService.getExcelByListBeanAndExcelCode(list, "yearBudget","年度预算");
    	 ByteArrayOutputStream os = new ByteArrayOutputStream();
    	 wb.write(os);
    	 byte[] data = os.toByteArray();
    	 os.close();
    	 response.reset();  
 		 response.setHeader("Content-Disposition", "attachment;filename="+"年度预算");
         response.addHeader("Content-Length", "" + data.length);  
         response.setContentType("application/octet-stream; charset=UTF-8"); 
         IOUtils.write(data, response.getOutputStream());  
    }
    
    
    
}
