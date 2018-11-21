package com.indihx.project.controller;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.PmProjectInfoService;
import com.indihx.PmYearBudget.entity.PmYearBudgetEntity;
import com.indihx.PmYearBudget.service.PmYearBudgetService;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.util.R;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;
import com.indihx.util.UserUtil;

/**
 * 年度预算
 * */

@Controller
@RequestMapping("/project")
public class YearBudgetController extends AbstractBaseController{
	@Autowired
	private ParamsInfoServiceimpl infoservice;
	
	 @Autowired
	 private PmYearBudgetService pmYearBudgetService;
	    
	 @Autowired
	 private PmProjectInfoService pmProjectInfoService;
	    
	@RequestMapping("/yearBudget/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","01"));
		view.addObject("productType",infoservice.qryInfoByCode("PRODUCT_TYPE"));
		
		view.setViewName("/project/yearBudget/index");
		return view;
	}
	// 新form
	@RequestMapping(value="/yearBudget/form2",method=RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("id") String id,HttpSession session) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("productType",infoservice.qryInfoByCode("PRODUCT_TYPE"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		view.addObject("budgetYear","2019年");// 当前年+1
		view.addObject("projectType","01");//02  项目类型为产品 
		List<PmProjectInfoEntity>  projectList = new ArrayList<PmProjectInfoEntity>();
		List<PmYearBudgetEntity> pmYearBudget = new ArrayList<PmYearBudgetEntity>();
		
		UsrInfo usesr = UserUtil.getUser(session);
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("creatorId", usesr.getUsrId());
    	List<PmYearBudgetEntity> pmYearBudget2 =  pmYearBudgetService.queryList(map);
		if(pmYearBudget2!=null&&!pmYearBudget2.isEmpty()){
			pmYearBudget = pmYearBudget2;
			view.addObject("list",pmYearBudget);
		}
		else{
			Map<String,Object> entity = new HashMap<String,Object>();
			//ToDo 状态为未完结的项目 且客户经理为当前用户的
			entity.put("isDelete", "00");
			entity.put("custManagerId", usesr.getUsrId());
			projectList = pmProjectInfoService.queryList(entity);
			view.addObject("list",projectList);
		}
		
		view.setViewName("/project/yearBudget/form2");
		return view;
	}
	// form
	@RequestMapping(value="/yearBudget/form",method=RequestMethod.GET)
	public ModelAndView formFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("productType",infoservice.qryInfoByCode("PRODUCT_TYPE"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/yearBudget/form");
		return view;
	}
	@RequestMapping(value="/yearBudget/edit",method=RequestMethod.GET)
	public ModelAndView editFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("productType",infoservice.qryInfoByCode("PRODUCT_TYPE","01"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/yearBudget/edit");
		return view;
	}
	
	@RequestMapping(value="/yearBudget/view",method=RequestMethod.GET)
	public ModelAndView viewFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("productType",infoservice.qryInfoByCode("PRODUCT_TYPE","01"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/yearBudget/view");
		return view;
	}
	// 客户
	@RequestMapping(value="/yearBudget/customer",method=RequestMethod.GET)
	public ModelAndView customerFormView(@RequestParam("act") String act) {
		ModelAndView view = new ModelAndView();
		view.addObject("act",act);
		view.setViewName("/project/yearBudget/customer");
		return view;
	}
	// wbs
	@RequestMapping(value="/yearBudget/wbs",method=RequestMethod.GET)
	public ModelAndView wbsFormView(@RequestParam("act") String act,@RequestParam("index") String index,@RequestParam("YIndex") String YIndex) {
		
		ModelAndView view = new ModelAndView();
		view.addObject("act",act);
		view.addObject("index",index);
		view.addObject("YIndex",YIndex);
		view.setViewName("/project/yearBudget/wbs");
		return view;
	}
	// product
	@RequestMapping(value="/yearBudget/product",method=RequestMethod.GET)
	public ModelAndView productFormView(@RequestParam("act") String act,@RequestParam("index") String index,@RequestParam("YIndex") String YIndex) {
		ModelAndView view = new ModelAndView();
		view.addObject("act",act);
		view.addObject("index",index);
		view.addObject("YIndex",YIndex);
		
		view.setViewName("/project/yearBudget/product");
		return view;
	}
	// trTmpl 根据 客户ID 和客户名称 查 项目 。。。。
	@RequestMapping(value="/yearBudget/trTempl",method=RequestMethod.GET)
	public ModelAndView trTemplFormView(@RequestParam("custName") String custName,@RequestParam("custId") String custId) {
		ModelAndView view = new ModelAndView();
		view.addObject("custName",custName);
		view.addObject("custId",custId);
		view.addObject("projectType","01");//02  项目类型为产品
		view.addObject("budgetYear","2019年");// 当前年+1
		view.setViewName("/project/yearBudget/trTempl");
		return view;
	}
	
	
	
	
	
}
