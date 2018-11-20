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
import com.indihx.comm.InitSysConstants;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;


@Controller
@RequestMapping("/project")
public class CustomerGroupController extends AbstractBaseController{
	@Autowired
	private ParamsInfoServiceimpl infoservice;

	@Autowired
	PmCustomerGroupService pmCustomerGroupService;
	
	@Autowired
	PmCustomerGroupRelationService pmCustomerGroupRelationService;
	
	@RequestMapping("/customerGroup/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","00"));
	
		view.setViewName("/project/customerGroup/index");
		return view;
	}
	@RequestMapping(value="/customerGroup/form",method=RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("act",act);
		if(id !=null && !"".equals(id)) {
			view.addObject("id",id);
		}
		
		
		view.setViewName("/project/customerGroup/form");
		return view;
	}
	
	@RequestMapping(value="/customerGroup/formGroup",method=RequestMethod.GET)
	public ModelAndView formGroupFormView() {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("/project/customerGroup/formGroup");
		return view;
	}
	// 新增
	@RequestMapping(value="/customerGroup/addForm",method=RequestMethod.GET)
	public ModelAndView addFormFormView() {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("/project/customerGroup/addForm");
		return view;
	}
	
	// 保存
	@RequestMapping(value="/customerGroup/saveCustomerGroup",method=RequestMethod.POST)
	public ModelAndView saveCustomerGroupFormView(@RequestParam("name") String name,@RequestParam("ctnCodes") String ctnCodes) {
		ModelAndView view = new ModelAndView();
		
		Map<String,Object> entity = new HashMap<String,Object>();
		entity.put("custGroupId", 21);
		entity.put("custGroupName", name);
		
//		pmCustomerGroupService.insert(entity);
		
		//view.setViewName("/project/customerGroup/saveCustomerGroup");
		return view;
	}
	
	
	
	// 编辑
	@RequestMapping(value="/customerGroup/editForm",method=RequestMethod.GET)
	public ModelAndView editFormFormView(@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		PmCustomerGroupEntity model= pmCustomerGroupService.queryObject(id);
		view.addObject("ctnGroup",model);
		
		view.setViewName("/project/customerGroup/editForm");
		return view;
	}
	
	// 查看
		@RequestMapping(value="/customerGroup/viewForm",method=RequestMethod.GET)
		public ModelAndView viewFormFormView(@RequestParam("id") String id) {
			ModelAndView view = new ModelAndView();
			PmCustomerGroupEntity model= pmCustomerGroupService.queryObject(id);
			view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL",model.getIsDelete()));
			view.addObject("ctnGroup",model);
			view.setViewName("/project/customerGroup/viewForm");
			return view;
		}
	
	
	
}
