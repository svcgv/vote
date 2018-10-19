package com.indihx.elecvote.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.indihx.AbstractBaseController;
import com.indihx.comm.InitSysConstants;
import com.indihx.elecvote.service.SectManageService;
import com.indihx.system.entity.UsrInfo;

@Controller
@RequestMapping("/sect")
public class SectManageController extends AbstractBaseController{
	@Autowired
	private SectManageService sectManageService;
	
	private Logger logger = LoggerFactory.getLogger(SectManageController.class);
	
	@RequestMapping("/index")
	public String getSectIndex(HttpSession session)
	{
		return "/elecvote/sect/sectInfoIndex";
	}
	
	@RequestMapping("/getSectInfo")
	public @ResponseBody Map<String, Object> getSectInfo(HttpSession session, @RequestBody Map<String, Object> requestMap)
	{
		logger.debug("hello getSectInfo");
		String sectName = (String)requestMap.get("sectName");
		String  currPage = (String)requestMap.get("currPage");
		UsrInfo user = getUser(session);
		logger.debug("sectName: "+ sectName);
		logger.debug("currPage: "+ currPage);
		Map<String, Object> map = sectManageService.getSectInfo(sectName, Integer.parseInt(currPage), InitSysConstants.BIG_SIZE);
		
		return map;
	}
	
	@RequestMapping("/getallsectinfo")
	public @ResponseBody Map<String, Object> getAllSectInfo(@RequestBody Map<String, Object> requestMap){
		logger.debug("hello getSectInfo");
		Map<String, Object> map = sectManageService.getAllSectInfo(requestMap);
		logger.debug("response: "+map);
		return map;
	}
}
