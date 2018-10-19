package com.indihx.wechat.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.comm.InitSysConstants;
import com.indihx.system.dao.ParamsInfoMapper;
import com.indihx.system.entity.ParamsInfo;
import com.indihx.wechat.service.HouseInfoService;
import com.indihx.wechat.service.WeChatPlatformService;
import com.indihx.wechat.service.impl.HouseInfoServiceImpl;
import com.indihx.wechat.vo.OauthResponse;

@Controller
@RequestMapping("/WeChat/House")
public class HouseInfoController  extends AbstractBaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private HouseInfoService houseInfoService;
	@Autowired
	private WeChatPlatformService weChatPlatformService;
	@Autowired
	private ParamsInfoMapper paramsInfoMapper;
	
	@RequestMapping("test")
	public String test() {
		return "/elecvote/WeChat/test";
	}
	
	@RequestMapping("index")
	public ModelAndView bindHouseView(@RequestParam("code")String code, HttpSession session){
		/*
		logger.debug("code: "+code);
		ModelAndView view = new ModelAndView();
		if (session.getAttribute("openId") != null) {
			view.addObject("openId", session.getAttribute("openId"));
			logger.info("openId: "+(String)session.getAttribute("opneId"));
		}else {
			OauthResponse res = weChatPlatformService.getOauthToken(InitSysConstants.APPID, InitSysConstants.SECRET, code);
			session.setAttribute("openId", res.getOpenid());
			view.addObject("openId",res.getOpenid());
			logger.info("openid: "+res.getOpenid());
			logger.debug("openid 2: "+(String)session.getAttribute("openId"));
		}*/
		ModelAndView view = new ModelAndView();
		
		ParamsInfo  param1 = paramsInfoMapper.selectByPrimaryKey("APPID");
		ParamsInfo  param2 = paramsInfoMapper.selectByPrimaryKey("SECRET");
		OauthResponse res = weChatPlatformService.getOauthToken(param1.getParamsVal(), param2.getParamsVal(), code);
		
		String openId = res.getOpenid();
		//String openId = "o4qGX0wla564AVbPFffgfUXG_SHs";
		view.setViewName("/elecvote/WeChat/bindHouse");
		view.addObject("openId",openId);
		
		return view;
	}
	
	@RequestMapping("/getSectInfo")
	public @ResponseBody Map<String, Object> getSectInfo(@RequestBody Map<String, Object> requestMap){
		logger.debug("hello getSectInfo");
		Map<String, Object> map = houseInfoService.getSectInfo(requestMap);
		logger.debug("response: "+map);
		return map;
	}
	
	@RequestMapping("/getBuildInfo")
	public @ResponseBody Map<String, Object> getBuildInfo(@RequestBody Map<String, Object> requestMap){
		logger.debug("hello getBuildInfo");
		Map<String, Object> map = houseInfoService.getBuildInfo(requestMap);
		try {
			logger.debug("respnose: "+new ObjectMapper().writeValueAsString(map));
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error: ", e);
		}
		return map;
	}
	
	@RequestMapping("/getUnitInfo")
	public @ResponseBody Map<String, Object> getUnitInfo(@RequestBody Map<String, Object> requestMap){
		logger.debug("hello getUnitInfo");
		Map<String, Object> map = houseInfoService.getUnitInfo(requestMap);
		return map;
	}
	
	@RequestMapping("/getFloorInfo")
	public @ResponseBody Map<String, Object> getFloorInfo(@RequestBody Map<String, Object> requestMap){
		logger.debug("hello getFloorInfo");
		Map<String, Object> map = houseInfoService.getFloorInfo(requestMap);
		return map;
	}
	
	@RequestMapping("/getRoomInfo")
	public @ResponseBody Map<String, Object> getRoomInfo(@RequestBody Map<String, Object> requestMap){
		logger.debug("getRoomInfo");
		Map<String, Object> map = houseInfoService.getRoomInfo(requestMap);
		return map;
	}
	
	@RequestMapping("/bindHouseInfo")
	public @ResponseBody Map<String, Object> bindHouseInfo(@RequestBody Map<String, Object> requestMap){
		logger.debug("hello bindHouseInfo");
		Map<String, Object> map = houseInfoService.bindHouseInfo(requestMap);
		return map;
	}
	@RequestMapping("unbindHouseInfo")
	public @ResponseBody Map<String, Object> unbindHouseInfo(@RequestBody Map<String, Object> requestMap){
		logger.debug("hello unbindHouseInfo");
		Map<String, Object> map = houseInfoService.unbindHouseInfo(requestMap);
		return map;
	}
	
	@RequestMapping("/getHouseInfo")
	public ModelAndView getHouseInfo(@RequestParam("code")String code, HttpSession session){
		logger.debug("hello getHouseInfo");
		logger.debug("code: "+code);
		ModelAndView view = new ModelAndView();
		Map<String, Object> map = null;
		/*
		if (session.getAttribute("openId") != null) {
			//view.addObject("openId", session.getAttribute("openId"));
			map = houseInfoService.getHouseInfoByOpenId((String)session.getAttribute("openId"));
			view.addObject("openId", (String)session.getAttribute("opneId"));
			logger.info("openId: "+(String)session.getAttribute("opneId"));
		}else {
			OauthResponse res = weChatPlatformService.getOauthToken(InitSysConstants.APPID, InitSysConstants.SECRET, code);
			session.setAttribute("openId", res.getOpenid());
			map = houseInfoService.getHouseInfoByOpenId(res.getOpenid());
			view.addObject("openId", res.getOpenid());
		}*/
		
		ParamsInfo  param1 = paramsInfoMapper.selectByPrimaryKey("APPID");
		ParamsInfo  param2 = paramsInfoMapper.selectByPrimaryKey("SECRET");
		OauthResponse res = weChatPlatformService.getOauthToken(param1.getParamsVal(), param2.getParamsVal(), code);
		
		//String openId="o4qGX0wla564AVbPFffgfUXG_SHs";
		String openId = res.getOpenid();
		//map = houseInfoService.getHouseInfoByOpenId(openId);
		map = houseInfoService.getHouseInfoByOpenId(openId);
		 
		view.setViewName("/elecvote/WeChat/myHouse");
		try {
			view.addObject("list", new ObjectMapper().writeValueAsString(map.get("listInfo")));
			//view.addObject("openId", openId);
			view.addObject("openId", openId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error");
		}
		return view;
	}
}
