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

import com.indihx.PmContractInfo.entity.PmContractInfoEntity;
import com.indihx.PmContractInfo.service.PmContractInfoService;
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

import com.alibaba.fastjson.JSON;
import com.indihx.AbstractBaseController;
import com.indihx.PmConfirmBid.entity.PmConfirmBidEntity;
import com.indihx.PmConfirmBid.service.PmConfirmBidService;
import com.indihx.PmFile.entity.PmFileEntity;
import com.indihx.PmFile.service.PmFileService;
import com.indihx.comm.InitSysConstants;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;

/**
 * 合同管理
 * */

@Controller
@RequestMapping("/project")
public class ContractController extends AbstractBaseController{
	@Autowired
	private ParamsInfoServiceimpl infoservice;
	@Autowired
    private PmConfirmBidService pmConfirmBidService;
    @Autowired
    private PmFileService pmFileService;
	@Autowired
	private PmContractInfoService pmContractInfoService;
	
	@RequestMapping("/contract/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","00"));
		view.addObject("productType",infoservice.qryInfoByCode("PRODUCT_TYPE"));
		view.addObject("status",infoservice.qryInfoByCode("BID_STATUS"));
		view.setViewName("/project/contract/index");
		return view;
	}
	@RequestMapping(value="/contract/form",method=RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("productType",infoservice.qryInfoByCode("PRODUCT_TYPE"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/contract/form");
		return view;
	}
	@RequestMapping(value="/contract/edit",method=RequestMethod.GET)
	public ModelAndView editFormView(@RequestParam("act") String act,@RequestParam("id") long id) {
		ModelAndView view = new ModelAndView();
		
		PmContractInfoEntity entity = pmContractInfoService.queryObject(id);
		view.addObject("pmContract",JSON.toJSONString(entity));
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("productType",infoservice.qryInfoByCode("PRODUCT_TYPE","01"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/contract/edit");
		return view;
	}
	@RequestMapping(value="/contract/view",method=RequestMethod.GET)
	public ModelAndView viewFormView(@RequestParam("act") String act,@RequestParam("id") long id) {
		ModelAndView view = new ModelAndView();

		PmContractInfoEntity entity = pmContractInfoService.queryObject(id);
		view.addObject("pmContract",JSON.toJSONString(entity));
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("productType",infoservice.qryInfoByCode("PRODUCT_TYPE","01"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/contract/view");
		return view;
	}
	
	// 机构
		@RequestMapping(value="/contract/org",method=RequestMethod.GET)
		public ModelAndView orgFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			
			view.setViewName("/project/contract/org");
			return view;
		}
		
		// 用户
		@RequestMapping(value="/contract/user",method=RequestMethod.GET)
		public ModelAndView userFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/contract/user");
			return view;
		}
		// 评审
		@RequestMapping(value="/contract/review",method=RequestMethod.GET)
		public ModelAndView reviewFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/contract/review");
			return view;
		}
		
		// 客户
		@RequestMapping(value="/contract/customer",method=RequestMethod.GET)
		public ModelAndView customerFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/contract/customer");
			return view;
		}
		
		
	
	
}
