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
import com.indihx.PmSaleGroupInfo.entity.PmSaleGroupInfoEntity;
import com.indihx.PmSaleGroupInfo.service.impl.PmSaleGroupInfoServiceImpl;
import com.indihx.baseTableUtil.entity.QueryUsrInfoEntity;
import com.indihx.baseTableUtil.service.QueryUsrInfoService;
import com.indihx.comm.InitSysConstants;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;

/**
 * 销售团队管理
 * */

@Controller
@RequestMapping("/project")
public class SellTeamController extends AbstractBaseController{
	@Autowired
	private ParamsInfoServiceimpl infoservice;
	@Autowired
	PmSaleGroupInfoServiceImpl pmSaleGroupInfoServiceImpl;
	@Autowired
	QueryUsrInfoService queryUsrInfoService;
	@RequestMapping("/sellTeam/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","00"));
		
		view.setViewName("/project/sellTeam/index");
		return view;
	}
	//新增表单
	@RequestMapping(value="/sellTeam/form",method=RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		
		view.addObject("act",act);
		if(id !=null && !"".equals(id)) {
			view.addObject("id",id);
		}
		
		view.setViewName("/project/sellTeam/form");
		return view;
	}
	//修改表单
	@RequestMapping(value="/sellTeam/edit",method=RequestMethod.GET)
	public ModelAndView editFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("groupCode", id);
		
		List<PmSaleGroupInfoEntity> list = pmSaleGroupInfoServiceImpl.queryList(map);
		List<QueryUsrInfoEntity> usrs = queryUsrInfoService.queryBySaleGroupCode(list.get(0).getGroupCode());
		
		view.addObject("group",list.get(0));
		view.addObject("users",usrs);
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/sellTeam/edit");
		return view;
	}
	//查看表单
	@RequestMapping(value="/sellTeam/view",method=RequestMethod.GET)
	public ModelAndView viewFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("groupCode", id);
		List<PmSaleGroupInfoEntity> list = pmSaleGroupInfoServiceImpl.queryList(map);
		List<QueryUsrInfoEntity> usrs = queryUsrInfoService.queryBySaleGroupCode(list.get(0).getGroupCode());
		
		view.addObject("group",list.get(0));
		view.addObject("users",usrs);
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/sellTeam/view");
		return view;
	}
	
	
	// 机构
	@RequestMapping(value="/sellTeam/org",method=RequestMethod.GET)
	public ModelAndView orgFormView() {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("/project/sellTeam/org");
		return view;
	}
	
	// 用户
	@RequestMapping(value="/sellTeam/user",method=RequestMethod.GET)
	public ModelAndView userFormView() {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("/project/sellTeam/user");
		return view;
	}
	
	
}
