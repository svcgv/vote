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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.indihx.AbstractBaseController;
import com.indihx.PmConfirmBid.entity.PmConfirmBidEntity;
import com.indihx.PmConfirmBid.service.PmConfirmBidService;
import com.indihx.PmFile.entity.PmFileEntity;
import com.indihx.PmFile.service.PmFileService;
import com.indihx.PmReviewInfo.entity.PmReviewInfoEntity;
import com.indihx.PmReviewInfo.service.PmReviewInfoService;
import com.indihx.comm.InitSysConstants;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;
import com.indihx.util.UserUtil;

/**
 *  立项审批
 * */

@Controller
@RequestMapping("/project")
public class ProjectApproveController extends AbstractBaseController{
	@Autowired
	private ParamsInfoServiceimpl infoservice;
	@Autowired
    private PmConfirmBidService pmConfirmBidService;
	
	@Autowired
    private PmReviewInfoService pmReviewInfoService;
	
	@Autowired
    private PmFileService pmFileService;

	
	
	@RequestMapping("/projectApprove/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","01"));
		view.addObject("productType",infoservice.qryInfoByCode("PRODUCT_TYPE"));
		view.setViewName("/project/projectApprove/index");
		return view;
	}
	@RequestMapping(value="/projectApprove/form",method=RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/projectApprove/form");
		return view;
	}
	@RequestMapping(value="/projectApprove/edit",method=RequestMethod.GET)
	public ModelAndView editFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/projectApprove/edit");
		return view;
	}
	@RequestMapping(value="/projectApprove/view",method=RequestMethod.GET)
	public ModelAndView viewFormView(@RequestParam("act") String act,@RequestParam("id") long id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/projectApprove/view");
		return view;
	}
	
	// 机构
		@RequestMapping(value="/projectApprove/org",method=RequestMethod.GET)
		public ModelAndView orgFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			
			view.setViewName("/project/projectApprove/org");
			return view;
		}
		
		// 用户
		@RequestMapping(value="/projectApprove/user",method=RequestMethod.GET)
		public ModelAndView userFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/projectApprove/user");
			return view;
		}
		// 评审
		@RequestMapping(value="/projectApprove/review",method=RequestMethod.GET)
		public ModelAndView reviewFormView(@RequestParam("act") String act,@RequestParam("id")long id,HttpSession session){
	    	UsrInfo usesr = UserUtil.getUser(session);
			ModelAndView view = new ModelAndView();
			
			view.addObject("act",act);
			
			view.addObject("userName",usesr.getUsrName());
			view.setViewName("/project/projectApprove/review");
			return view;
		}
		// 客户
		@RequestMapping(value="/projectApprove/customer",method=RequestMethod.GET)
		public ModelAndView customerFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/projectApprove/customer");
			return view;
		}
		// 项目
		@RequestMapping(value="/projectApprove/project",method=RequestMethod.GET)
		public ModelAndView projectFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/projectApprove/project");
			return view;
		}
}
