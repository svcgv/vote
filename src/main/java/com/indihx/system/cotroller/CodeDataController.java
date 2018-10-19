package com.indihx.system.cotroller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.system.entity.CodeData;
import com.indihx.system.service.impl.CodeDataServiceImpl;
import com.indihx.system.vo.CodeDataVo;

/***
 * 
 * <p>描 述: 数据字典管理 </p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年3月14日</p>
 * @author 严蒙蒙
 */
@Controller
@RequestMapping("/code")  
public class CodeDataController {
	
	//日志
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(CodeDataController.class); 
	
	@Autowired
	private CodeDataServiceImpl ICodeDataService;
	
	/***
	 * 查询字典信息，返回首页面列表显示
	 * @return 返回页面模版
	 */
	@RequestMapping(value="/qryCodeData",method=RequestMethod.GET)  
	public ModelAndView qryCodeData(){
		Map<String, Object> map= ICodeDataService.qryCodeDataList(new CodeDataVo());
		ModelAndView view = new ModelAndView();
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		view.setViewName("/code/qryCodeList");
		return view;
	}
	
	/*分页、筛选条件的查询*/
	@RequestMapping(value="/ajaxQryCodeData",method=RequestMethod.POST)  
	@ResponseBody
	public  Map<String, Object> ajaxQryCodeData(@RequestBody CodeDataVo dataVo){
		Map<String, Object> map = ICodeDataService.qryCodeDataList(dataVo);
		return map;
	}
	
	
	/**
	 * 打开字典信息新增页面
	 * @return
	 */
	/*@RequestMapping(value="/openCodeData",method=RequestMethod.POST)
	public String openAddUsr(){
		log.info("打开字典信息新增页面！！");
		return "/code/addCodeData";
	}*/
	
	/***
	 * 字典信息保存
	 * @param dataVo
	 * @return 返回成功或失败页
	 */
	@RequestMapping(value="/addSave",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addCodeData(@RequestBody CodeDataVo dataVo){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = ICodeDataService.addCodeData(dataVo);
		map.put("status", isSuccess);
		return map;
	}
	
	/***
	 * 根据字典ID查询信息
	 * @param dataVo
	 * @return
	 */
	@RequestMapping(value="/qryCodeDataById",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> qryCodeDataById(@RequestBody CodeDataVo dataVo){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeData", ICodeDataService.qryCodeDataById(dataVo));
		return map;
	}
	
	
	/**
	 * 打开修改页面，根据联合主键查询当前数据字典信息
	 * @param codeNo ,codeKeyBy 联合主键
	 * @return
	 */
	@RequestMapping(value="/openUpdateCode",method=RequestMethod.GET)
	public ModelAndView openUpdateCode(@RequestParam("codeNo") String codeNo,@RequestParam("codeKeyBy") String codeKeyBy){
		CodeDataVo dataVo=new CodeDataVo();
		dataVo.setCodeKeyBy(codeKeyBy);
		dataVo.setCodeNoBy(codeNo);
		CodeData code = ICodeDataService.qryCodeDataById(dataVo);
		ModelAndView view = new ModelAndView();
		view.setViewName("/code/editCodeData");
		view.addObject("code", code);
		return view;
	}
	
	/***
	 * 修改字典信息的保存
	 * @param dataVo
	 * @return
	 */
	@RequestMapping(value="/editSave",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> editSave(@RequestBody CodeDataVo dataVo){
		Map<String, Object> respMap = new HashMap<String, Object>();
		boolean isSucess = ICodeDataService.updCodeDataById(dataVo);
		respMap.put("success", isSucess);
		return respMap;
	}
	
	/***
	 * 删除数据字典信息
	 * @param dataVo
	 * @return
	 */
	@RequestMapping(value="/delCodeData",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> delCodeData(@RequestBody CodeDataVo dataVo){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSucess = ICodeDataService.deleteCodeData(dataVo);
		map.put("success", isSucess);
		return map;
	}
	
}
