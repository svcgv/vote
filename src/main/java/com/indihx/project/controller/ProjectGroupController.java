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
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupEntity;
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupRelationEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerGroupRelationService;
import com.indihx.PmCustomerInfo.service.PmCustomerGroupService;
import com.indihx.PmProjectGroupInfo.entity.PmProjectGroupInfoEntity;
import com.indihx.PmProjectGroupInfo.service.PmProjectGroupInfoService;
import com.indihx.comm.InitSysConstants;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;


@Controller
@RequestMapping("/project")
public class ProjectGroupController extends AbstractBaseController{
	@Autowired
	private ParamsInfoServiceimpl infoservice;

	@Autowired
	PmProjectGroupInfoService PmProjectGroupInfoService;
	
	
	@RequestMapping("/projectGroup/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","00"));
	
		view.setViewName("/project/projectGroup/index");
		return view;
	}
	@RequestMapping(value="/projectGroup/form",method=RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("act",act);
		if(id !=null && !"".equals(id)) {
			view.addObject("id",id);
		}
		
		view.setViewName("/project/projectGroup/form");
		return view;
	}
	
	@RequestMapping(value="/projectGroup/edit",method=RequestMethod.GET)
	public ModelAndView formGroupFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		PmProjectGroupInfoEntity pmProjectGroupInfoEntity = PmProjectGroupInfoService.queryObject(Long.valueOf(id));
		
		//编辑时为已存的值
		view.addObject("pmProjectGroupInfoEntity",pmProjectGroupInfoEntity);
		
		view.setViewName("/project/projectGroup/edit");
		return view;
	}
	@RequestMapping(value="/projectGroup/view",method=RequestMethod.GET)
	public ModelAndView viewFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		PmProjectGroupInfoEntity pmProjectGroupInfoEntity = PmProjectGroupInfoService.queryObject(Long.valueOf(id));
		
		//编辑时为已存的值
		view.addObject("pmProjectGroupInfoEntity",pmProjectGroupInfoEntity);
		
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/projectGroup/view");
		return view;
	}
	// 机构
	@RequestMapping(value="/projectGroup/org",method=RequestMethod.GET)
	public ModelAndView orgFormView(@RequestParam("act") String act) {
		ModelAndView view = new ModelAndView();
		view.addObject("act",act);
		
		view.setViewName("/project/projectGroup/org");
		return view;
	}
			
	   // 用户
	@RequestMapping(value="/projectGroup/user",method=RequestMethod.GET)
	public ModelAndView userFormView(@RequestParam("act") String act) {
		ModelAndView view = new ModelAndView();
		view.addObject("act",act);
		view.setViewName("/project/project/user");
		return view;
	}
	//项目
	@RequestMapping(value="/projectGroup/project",method=RequestMethod.GET)
	public ModelAndView reviewFormView(@RequestParam("act") String act) {
		ModelAndView view = new ModelAndView();
		view.addObject("act",act);
		view.setViewName("/project/projectGroup/project");
		return view;
	}
		
	
	
}
