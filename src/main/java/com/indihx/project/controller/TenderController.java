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

/**
 * 投标管理
 * */

@Controller
@RequestMapping("/project")
public class TenderController extends AbstractBaseController{
	@Autowired
	private ParamsInfoServiceimpl infoservice;
	@Autowired
    private PmConfirmBidService pmConfirmBidService;
    @Autowired
    private PmFileService pmFileService;
    @Autowired
    private PmReviewInfoService pmReviewInfoService;
	@RequestMapping("/tender/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("status",infoservice.qryInfoByCode("BID_STATUS","00"));
		view.setViewName("/project/tender/index");
		return view;
	}
	@RequestMapping(value="/tender/form",method=RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE","00"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/tender/form");
		return view;
	}
	@RequestMapping(value="/tender/edit",method=RequestMethod.GET)
	public ModelAndView editFormView(@RequestParam("act") String act,@RequestParam("id") long id) {
		ModelAndView view = new ModelAndView();
		
		PmConfirmBidEntity entity = pmConfirmBidService.queryObject(id);
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("foreignId", id);
		maps.put("isDelete", "00");
		view.addObject("file",pmFileService.queryList(maps));
		view.addObject("pmConfirmBid",JSON.toJSONString(entity));
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE",entity.getProjectType()));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/tender/edit");
		return view;
	}
	@RequestMapping(value="/tender/view",method=RequestMethod.GET)
	public ModelAndView viewFormView(@RequestParam("act") String act,@RequestParam("id") long id) {
		ModelAndView view = new ModelAndView();
		
		PmConfirmBidEntity entity = pmConfirmBidService.queryObject(id);
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("foreignId", id);
		maps.put("isDelete", "00");
		List<PmFileEntity> pmFiles = pmFileService.queryList(maps);
		view.addObject("file",pmFiles);
		view.addObject("pmConfirmBid",JSON.toJSONString(entity));
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE",entity.getProjectType()));
		
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put("reviewType", "00");
		map.put("isDelete", "01");
		map.put("foreignId", id);
		
		List<PmReviewInfoEntity> list2 = pmReviewInfoService.queryList(map);
		view.addObject("reviewHis",JSON.toJSONString(list2));
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/tender/view");
		return view;
	}
	
	// 机构
		@RequestMapping(value="/tender/org",method=RequestMethod.GET)
		public ModelAndView orgFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			
			view.setViewName("/project/tender/org");
			return view;
		}
		
		// 用户
		@RequestMapping(value="/tender/user",method=RequestMethod.GET)
		public ModelAndView userFormView(@RequestParam("act") String act,@RequestParam("orgNo") String orgNo,@RequestParam("roleCode") String roleCode) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.addObject("orgNo",orgNo);
			view.addObject("roleCode",roleCode);
			view.setViewName("/project/tender/user");
			return view;
		}
		// 评审
		@RequestMapping(value="/tender/review",method=RequestMethod.GET)
		public ModelAndView reviewFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/tender/review");
			return view;
		}
		
		// 客户
		@RequestMapping(value="/tender/customer",method=RequestMethod.GET)
		public ModelAndView customerFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/tender/customer");
			return view;
		}
		
		// 客户
		@RequestMapping(value="/tender/setMoney",method=RequestMethod.GET)
		public ModelAndView setMoneyFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/tender/setMoney");
			return view;
		}
	
	
}
