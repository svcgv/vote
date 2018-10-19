package com.indihx.wechat.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.comm.InitSysConstants;
import com.indihx.system.dao.ParamsInfoMapper;
import com.indihx.system.entity.ParamsInfo;
import com.indihx.wechat.service.VoteInfoService;
import com.indihx.wechat.service.WeChatPlatformService;
import com.indihx.wechat.vo.OauthResponse;

@Controller
@RequestMapping("/WeChat/vote")
public class VoteInfoController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private WeChatPlatformService weChatPlatformService;
	@Autowired
	private VoteInfoService voteInfoService;
	@Autowired
	private ParamsInfoMapper paramsInfoMapper;
	
	@RequestMapping("/getVotedInfo")
	public ModelAndView getVotedInfo(@RequestParam("code")String code) {
		logger.debug("hello getVotedInfo");
		
		ParamsInfo  param1 = paramsInfoMapper.selectByPrimaryKey("APPID");
		ParamsInfo  param2 = paramsInfoMapper.selectByPrimaryKey("SECRET");
		OauthResponse res = weChatPlatformService.getOauthToken(param1.getParamsVal(), param2.getParamsVal(), code);
		
		//String openId = "o4qGX0wla564AVbPFffgfUXG_SHs";
		String openId = res.getOpenid();
		Map<String, Object> map = voteInfoService.getVotedInfo(openId);
		ModelAndView view = new ModelAndView();
		view.setViewName("/elecvote/WeChat/votedList");
		try {
			logger.info("map: "+new ObjectMapper().writeValueAsString(map));
			view.addObject("map", new ObjectMapper().writeValueAsString(map));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error");
		}
		view.addObject("openId",openId);
		return view;
	}
	
	@RequestMapping("/changeVotedInfo")
	public ModelAndView changeVotedInfo(@RequestParam("openId")String openId) {
		logger.debug("hello getVotedInfo");
		Map<String, Object> map = voteInfoService.getVotedInfo(openId);
		ModelAndView view = new ModelAndView();
		view.setViewName("/elecvote/WeChat/votedList");
		try {
			logger.info("map: "+new ObjectMapper().writeValueAsString(map));
			view.addObject("map", new ObjectMapper().writeValueAsString(map));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error");
		}
		view.addObject("openId",openId);
		return view;
	}
	
	@RequestMapping("getVotedInfoDetails")
	public ModelAndView getVotedInfoDetails(@Param("openId") String openId, @Param("topicId") String topicId) {
		logger.debug("hello getVotedInfoDetails");
		Map<String, Object> map = voteInfoService.getVotedInfoDetails(topicId);
		ModelAndView view = new ModelAndView();
		view.setViewName("/elecvote/WeChat/queryVote");
		view.addObject("openId", openId);
		try {
			logger.info("map: "+new ObjectMapper().writeValueAsString(map));
			view.addObject("map", new ObjectMapper().writeValueAsString(map));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error");
		}
		return view;
	}
	
	@RequestMapping("/getUnvotedInfo")
	public ModelAndView getUnvotedInfo(@RequestParam("code")String code) {
		logger.debug("hello getUnvotedInfo");
		
		ParamsInfo  param1 = paramsInfoMapper.selectByPrimaryKey("APPID");
		ParamsInfo  param2 = paramsInfoMapper.selectByPrimaryKey("SECRET");
		OauthResponse res = weChatPlatformService.getOauthToken(param1.getParamsVal(), param2.getParamsVal(), code);
		
		//String openId = "o4qGX0wla564AVbPFffgfUXG_SHs";
		String openId = res.getOpenid();
		Map<String, Object> map = voteInfoService.getUnvotedInfo(openId);
		ModelAndView view = new ModelAndView();
		view.setViewName("/elecvote/WeChat/unvotedList");
		try {
			logger.info("map: "+new ObjectMapper().writeValueAsString(map));
			view.addObject("map", new ObjectMapper().writeValueAsString(map));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error");
		}
		view.addObject("openId",openId);
		return view;
	}
	
	@RequestMapping("/changeUnvotedInfo")
	public ModelAndView changeUnvotedInfo(@RequestParam("openId")String openId) {
		logger.debug("hello getUnvotedInfo");
		Map<String, Object> map = voteInfoService.getUnvotedInfo(openId);
		ModelAndView view = new ModelAndView();
		view.setViewName("/elecvote/WeChat/unvotedList");
		try {
			logger.info("map: "+new ObjectMapper().writeValueAsString(map));
			view.addObject("map", new ObjectMapper().writeValueAsString(map));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error");
		}
		view.addObject("openId",openId);
		return view;
	}
	
	@RequestMapping("getUnvotedInfoDetails")
	public ModelAndView getUnvotedInfoDetails(@RequestParam("openId") String openId, @RequestParam("topicId") String topicId) {
		logger.debug("hello getUnvotedInfoDetails");
		Map<String, Object> map = voteInfoService.getUnvotedInfoDetails(topicId);
		ModelAndView view = new ModelAndView();
		view.setViewName("/elecvote/WeChat/ownerVote");
		try {
			logger.info("map: "+new ObjectMapper().writeValueAsString(map));
			view.addObject("map", new ObjectMapper().writeValueAsString(map));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error");
		}
		view.addObject("openId", openId);
		
		return view;
	}
	
	@RequestMapping("voteSubmit")
	/*public @ResponseBody Map<String, Object> voteSubmit(//@RequestParam("file") MultipartFile[] myfiles,
			@RequestParam("openId") String openId, @RequestParam("topicId") String topicId,
			@RequestParam("result") String result, @RequestParam("serverId") String serverId){*/
	public @ResponseBody Map<String, Object> voteSubmit(@RequestBody Map<String, Object> requestMap){
		logger.debug("hello voteSubmit");
		//logger.debug("filename: "+myfiles[0].getOriginalFilename());
		try {
			logger.info("map: "+new ObjectMapper().writeValueAsString(requestMap));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error");
		}
		Map<String, Object> map = voteInfoService.voteSubmit(requestMap);
		
		return map;
	}
}
