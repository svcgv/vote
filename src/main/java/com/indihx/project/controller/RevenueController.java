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
import com.indihx.PmProductInfo.entity.PmProductInfoEntity;
import com.indihx.PmProductInfo.service.PmProductInfoService;
import com.indihx.comm.InitSysConstants;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;

/**
 * 产品管理
 * */

@Controller
@RequestMapping("/project")
public class RevenueController extends AbstractBaseController{
	@Autowired
	private ParamsInfoServiceimpl infoservice;
	
    @Autowired
    private PmProductInfoService pmProductInfoService;
	
	@RequestMapping("/revenue/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","00"));
		
		view.setViewName("/project/revenue/index");
		return view;
	}
	@RequestMapping(value="/revenue/form",method=RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/revenue/form");
		return view;
	}
	@RequestMapping(value="/revenue/edit",method=RequestMethod.GET)
	public ModelAndView editFormView(@RequestParam("act") String act,@RequestParam("id") long id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));

		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/revenue/edit");
		return view;
	}
	@RequestMapping(value="/revenue/view",method=RequestMethod.GET)
	public ModelAndView viewFormView(@RequestParam("act") String act,@RequestParam("id") long id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/revenue/view");
		return view;
	}
	
	
	// 项目
	@RequestMapping(value="/revenue/project",method=RequestMethod.GET)
	public ModelAndView projectFormView() {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("/project/revenue/project");
		return view;
	}
	
}
