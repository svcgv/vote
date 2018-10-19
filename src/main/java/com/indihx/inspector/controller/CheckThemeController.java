package com.indihx.inspector.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.comm.exception.BusinessException;
import com.indihx.datamng.service.ISectService;
import com.indihx.datamng.vo.SectVo;
import com.indihx.inspector.entity.DC_THEME;
import com.indihx.inspector.service.IThemeService;
import com.indihx.inspector.vo.ThemeVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic; 

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 制定检查主题管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月11日 下午 09:05:15</p>
 * <p></p>
 */

@Controller
@RequestMapping("/checktheme")
public class CheckThemeController extends AbstractBaseController {

	@Resource
	private IThemeService service;
	@Resource
	private ISectService sectService;
	
	/***
	 * 查看主题信息
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/qryThemeList",method=RequestMethod.GET)
	public ModelAndView cspList(){
		Map<String, Object> map = service.qryThemeList(new ThemeVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/inspector/checktheme/qryThemeList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	/***
	 * 根据搜索条件查询
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/ajaxqryThemeList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQueryCsList(@RequestBody ThemeVo vo){
		Map<String, Object> map = service.qryThemeList(vo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/***
	 * 打开新增主题页面
	 * @return 
	 * @return  返回新增页面VIEW
	 */
	@RequestMapping(value="/openAddTheme",method=RequestMethod.GET)
	public ModelAndView openAddhoc(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/inspector/checktheme/addThemeInfo");
		view.addObject("DESIGN_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG"));//是否制定检查
		return view; 
	}
	
	/***
	 * 选择项目按钮
	 * @param session
	 */
	@RequestMapping(value="/openSectList",method=RequestMethod.GET)
	public ModelAndView openSectList(HttpSession session){
		UsrInfo usrInfo = getUser(session);
		Map<String, Object> map = sectService.getSectList(usrInfo,new SectVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/inspector/checktheme/openSectList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 选择项目查询按钮
	 * Ajax首页通过查询条件查询数据信息
	 * @param sectVo  
	 * @return
	 */
	@RequestMapping(value="/ajaxSectList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxSectList(@RequestBody SectVo infoVo,HttpSession session){
		UsrInfo usrInfo = getUser(session);
		Map<String, Object> map = sectService.getSectList(usrInfo,infoVo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/**
     * 新增保存检查主题页面
     * @param cspSectVo
     * @return
	 * @throws BusinessException 
     */
	@RequestMapping(value="/saveAddThemeInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveAddThemeInfo(@RequestBody ThemeVo vo,HttpSession session) throws BusinessException{
		UsrInfo usrInfo = getUser(session);
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=service.saveCheckTheme(usrInfo,vo);
		map.put("status", isSuccess);
		return map;
	}
	/**
	 * 编辑检查主题详情页面
	 * @param glcid
	 * @return
	 */
	@RequestMapping(value="/openUpdateTheme",method=RequestMethod.GET)
	public ModelAndView openUpdateTheme(@RequestParam("themeid") String theme_id){
		DC_THEME theme = service.getCheckTheme(theme_id);//查看主题信息
		Map<String,Object> map = service.getCheckSect(theme_id);//查看制定小区
		ModelAndView view = new ModelAndView();
		view.setViewName("/inspector/checktheme/editThemeInfo");
		view.addObject("theme", theme); 
		view.addObject("sect_name_arr", map.get("sectName")); 
		view.addObject("sect_id_arr", map.get("sectId")); 
		view.addObject("DESIGN_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",theme.getDesign_flag()));//是否制定检查
		return view;
	}

	/**
     * 编辑保存检查主题信息
     * @param cspSectVo
     * @return
	 * @throws BusinessException 
     */
	@RequestMapping(value="/saveEditThemeInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveEditThemeInfo(@RequestBody ThemeVo vo,HttpSession session) throws BusinessException{
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=service.updateCheckTheme(vo);
		map.put("status", isSuccess);
		return map;
	}
	
	
	/**
	 * 查看检查主题详情页面
	 * @param glcid
	 * @return
	 */
	@RequestMapping(value="/openGetTheme",method=RequestMethod.GET)
	public ModelAndView openGetTheme(@RequestParam("themeid") String theme_id){
		DC_THEME theme = service.getCheckTheme(theme_id);
		Map<String,Object> map  = service.getCheckSect(theme_id);
		ModelAndView view = new ModelAndView();
		view.setViewName("/inspector/checktheme/getThemeInfo");
		view.addObject("theme", theme); 
		view.addObject("sect_name_arr", map.get("sectName")); 
		view.addObject("sect_id_arr", map.get("sectId")); 
		view.addObject("DESIGN_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",theme.getDesign_flag()));//是否制定检查
		return view;
	}
	
	
	/**
	 * 删除检查主题信息
	 * @param cspSectVo
	 * @return
	 */
	@RequestMapping(value="/delThemeInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delThemeInfo(@RequestBody  Map<String,Object> params){
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=service.delCheckTheme(params.get("themeid").toString());
		map.put("status", isSuccess);
		return map;
	}
	
	/***
	 * 查询用户菜单
	 * @param session  session
	 * @return  集合
	 */
	@RequestMapping(value="/qryQuotaList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> qryMenuList(@RequestBody Map<String, String> reqMap){
		String themeId = reqMap.get("theme_id").replaceAll(",", "").trim();
		Map<String, Object> map = service.qryQuotaJson(themeId);
		return map;
	}
	
	
	
	/**
     * 编辑保存检查主题信息
     * @param cspSectVo
     * @return
	 * @throws BusinessException 
     */
	@RequestMapping(value="/pulishThemeInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> pulishThemeInfo(@RequestBody  Map<String,Object> params){
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=service.updatePublishTheme(params.get("themeid").toString());
		map.put("status", isSuccess);
		return map;
	}
}
