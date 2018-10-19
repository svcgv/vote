package com.indihx.elecvote.controller;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: HouseManageController.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年7月11日下午1:58:08</p>
 * <p>@author zhaoyongfeng</p>
 * <p>@version 1.0</p>
 * <p>HouseManageController.java</p>
 * 房屋信息管理，包括房屋信息导入，房屋信息查询
 */
@Controller
@RequestMapping("/house")
public class HouseManageController extends AbstractBaseController{
	
	@Autowired
	private HouseManageService houseManageService;
	private  Logger logger = LoggerFactory.getLogger(HouseManageController.class);
	
	@RequestMapping("/index")
	public String  getHouseIndex(HttpSession session)
	{
		return "/elecvote/house/houseInfoIndex";
	}
	
	@RequestMapping("/impHouse")
	public String importHouseInfo(HttpSession session)
	{
		logger.info("in house");
		return "/elecvote/house/impHouse";
	}
	
	@RequestMapping("/getHouseInfo")
	public @ResponseBody Map<String, Object> getHouseInfo(HttpSession session, @RequestBody Map<String, Object> requestMap)

	{
		logger.debug("hello getHouseInfo");
		//String sectName = (String)requestMap.get("sectName");
		//String  currPage = (String)requestMap.get("currPage");
		UsrInfo user = getUser(session);
		//logger.debug("sectName: "+ sectName);
		//logger.debug("currPage: "+ currPage);
		Map<String, Object> map = houseManageService.getHouseInfo(user, requestMap);
		
		return map;
	}
	
	@RequestMapping("/getUnlinkHouseInfoAndSelect")
	public @ResponseBody Map<String, Object> getUnlinkHouseInfoAndSelect(HttpSession session, @RequestBody Map<String, Object> requestMap)
	{
		logger.debug("hello getUnlinkHouseInfoAndSelect");
		UsrInfo user = getUser(session);
		String topicId = (String) session.getAttribute("topicId");
		logger.debug("topicId: "+ topicId);
		Map<String, Object> map = houseManageService.getHouseInfoAndSelect(user,topicId, requestMap, false);
		
		return map;
	}
	
	@RequestMapping("/getLinkHouseInfoAndSelect")
	public @ResponseBody Map<String, Object> getLinkHouseInfoAndSelect(HttpSession session, @RequestBody Map<String, Object> requestMap)
	{
		logger.debug("hello getLinkHouseInfoAndSelect");
		UsrInfo user = getUser(session);
		String topicId = (String) session.getAttribute("topicId");
		logger.debug("topicId: "+ topicId);
		Map<String, Object> map = houseManageService.getHouseInfoAndSelect(user,topicId, requestMap, true);
		
		return map;
	}
	
	@RequestMapping("/addHouse")
	public String addHouseInfoView(HttpSession session)
	{
		return "/elecvote/house/addHouseInfo";
	}
	
	@RequestMapping("/addHouseInfo")
	public @ResponseBody Map<String, Object> addHouseInfo(HttpSession session, @RequestBody VoteHouseInfo voteHouseInfo)
	{
		logger.debug("in addHouseInfo");
		try {
			logger.debug("votehouseinfo: "+new ObjectMapper().writeValueAsString(voteHouseInfo));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("json error", e);
		}
		
		Map<String, Object> map = houseManageService.addHouseInfo(voteHouseInfo);
		return map;
	}
	
	@RequestMapping("/editHouse")
	public ModelAndView editHouseInfoView(@RequestParam String infoId) {
		logger.debug("hello editHouseInfoView");
		VoteHouseInfo voteHouseInfo = houseManageService.getHouseInfoById(new BigDecimal(infoId));
		ModelAndView view = new ModelAndView();
		view.addObject("house", voteHouseInfo);
		view.setViewName("/elecvote/house/editHouseInfo");
		return view;
	}
	
	@RequestMapping("/editHouseInfo")
	public @ResponseBody Map<String, Object> editHouseInfo(@RequestBody VoteHouseInfo voteHouseInfo, HttpSession session)
	{
		logger.debug("hello editHouseInfo");
		try {
			logger.debug("votehouseinfo: "+new ObjectMapper().writeValueAsString(voteHouseInfo));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("json error", e);
		}
		
		boolean bool = houseManageService.editHouseInfo(voteHouseInfo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", bool);
		return map;
	}
	
	@RequestMapping("/delHouseInfo")
	public @ResponseBody Map<String, Object> delHouseInfo(@RequestBody Map<String, Object> requestMap)
	{
		logger.debug("hello delHouseInfo");
		//logger.debug("info_id: "+ map.get("infoId"));
		Boolean bool = houseManageService.delHouseInfo(requestMap);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("status", bool);
		return responseMap;
	}
	
	@RequestMapping("/uploadHouseInfo")
	public @ResponseBody Map<String, Object> uploadHouseInfo(@RequestParam("file") MultipartFile[] myfiles,
			HttpServletRequest request, @RequestParam("fileTypeId") String fileTypeId,HttpSession session)
	{
		UsrInfo usrInfo = getUser(session);
		Map<String, Object> map = houseManageService.loadDataInfo(usrInfo,myfiles,fileTypeId);
		return map;
	}
}
