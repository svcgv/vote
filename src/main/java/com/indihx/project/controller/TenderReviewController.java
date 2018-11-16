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
 * 投标评审管理
 * */

@Controller
@RequestMapping("/project")
public class TenderReviewController extends AbstractBaseController{
	@Autowired
	private ParamsInfoServiceimpl infoservice;
	@Autowired
    private PmConfirmBidService pmConfirmBidService;
	
	@Autowired
    private PmReviewInfoService pmReviewInfoService;
	
	@Autowired
    private PmFileService pmFileService;

	
	
	@RequestMapping("/tenderReview/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","01"));
		view.addObject("productType",infoservice.qryInfoByCode("PRODUCT_TYPE"));
		view.setViewName("/project/tenderReview/index");
		return view;
	}
	@RequestMapping(value="/tenderReview/form",method=RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/tenderReview/form");
		return view;
	}
	@RequestMapping(value="/tenderReview/edit",method=RequestMethod.GET)
	public ModelAndView editFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/tenderReview/edit");
		return view;
	}
	@RequestMapping(value="/tenderReview/view",method=RequestMethod.GET)
	public ModelAndView viewFormView(@RequestParam("act") String act,@RequestParam("id") long id) {
		ModelAndView view = new ModelAndView();
		
		PmConfirmBidEntity entity = pmConfirmBidService.queryObject(id);
		view.addObject("pmConfirmBid",JSON.toJSONString(entity));
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put("reviewType", "00");
		map.put("isDelete", "01");
		map.put("foreignId", id);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("foreignId", id);
		param.put("isDelete", "00");
		param.put("uploadType", "00");
		List<PmFileEntity> fileList = pmFileService.queryList(param);
		
		map.put("foreignId", id);
		map.put("isDelete", "00");
		
		List<PmReviewInfoEntity> list = pmReviewInfoService.queryList(map);
		map.put("isDelete", "01");
		
		if(list.isEmpty()) {
			view.addObject("reviewId",0);
		}
		else {
			view.addObject("reviewId",list.get(0).getReviewId());
		}
		
		List<PmReviewInfoEntity> list2 = pmReviewInfoService.queryList(map);
		
		view.addObject("reviewHis",JSON.toJSONString(list2));
		view.addObject("act",act);
		view.addObject("id",id);
		view.addObject("fileList",fileList);
		
		view.setViewName("/project/tenderReview/view");
		return view;
	}
	
	// 机构
		@RequestMapping(value="/tenderReview/org",method=RequestMethod.GET)
		public ModelAndView orgFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			
			view.setViewName("/project/tenderReview/org");
			return view;
		}
		
		// 用户
		@RequestMapping(value="/tenderReview/user",method=RequestMethod.GET)
		public ModelAndView userFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/tenderReview/user");
			return view;
		}
		// 评审
		@RequestMapping(value="/tenderReview/review",method=RequestMethod.GET)
		public ModelAndView reviewFormView(@RequestParam("act") String act,@RequestParam("id")long id,HttpSession session){
	    	UsrInfo usesr = UserUtil.getUser(session);
			ModelAndView view = new ModelAndView();
			Map<String,Object> entity = new HashMap<String,Object>();
			entity.put("foreignId", id);
			entity.put("isDelete", "00");
			
			List<PmReviewInfoEntity> list = pmReviewInfoService.queryList(entity);
			entity.put("isDelete", "01");
			List<PmReviewInfoEntity> list2 = pmReviewInfoService.queryList(entity);
			
			if(list.isEmpty()) {
				view.addObject("reviewId",0);
			}
			else {
				view.addObject("reviewId",list.get(0).getReviewId());
			}
			
			view.addObject("reviewHis",list2);
			
			view.addObject("act",act);
			
			
			view.addObject("userName",usesr.getUsrName());
			view.setViewName("/project/tenderReview/review");
			return view;
		}
		// 客户
		@RequestMapping(value="/tenderReview/customer",method=RequestMethod.GET)
		public ModelAndView customerFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/tenderReview/customer");
			return view;
		}
}
