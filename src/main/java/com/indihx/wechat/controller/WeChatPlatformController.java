package com.indihx.wechat.controller;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.indihx.AbstractBaseController;
import com.indihx.wechat.service.WeChatPlatformService;
import com.indihx.wechat.vo.RequestMessage;
import com.indihx.wechat.vo.ResponseMessage;


@Controller
@RequestMapping("/WeChat")
public class WeChatPlatformController extends AbstractBaseController{
	private Logger logger = LoggerFactory.getLogger(WeChatPlatformController.class);
	
	@Autowired
	private WeChatPlatformService weChatPlatformService;
	
	@RequestMapping(method=RequestMethod.GET)
	public  @ResponseBody String checkGet(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce,@RequestParam("echostr") String echostr)
	{
		logger.debug("wechat check");
		logger.debug("signature: "+signature+" timestamp: "+timestamp+" nonce: "+nonce+" echostr: "+echostr);
		Boolean bool = weChatPlatformService.checkSignature(signature, timestamp, nonce);
		if (bool == true) {
			logger.debug("check success.");
			return echostr;
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ResponseMessage checkPost(@RequestBody RequestMessage request ){
		logger.debug("handle message");
		ResponseMessage response = weChatPlatformService.handlerMessage(request);
		try {
			logger.debug("response: "+ new ObjectMapper().writeValueAsString(response));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error: ", e);
		}
		return response;
	}

	@RequestMapping("/getconfig")
	public @ResponseBody Map<String, Object> getConfig(@RequestParam("url") String url){
		logger.debug("hello getConfig,url: "+url);
		Map<String, Object> map = weChatPlatformService.getConfig(url);
		try {
			logger.debug("response: "+ new ObjectMapper().writeValueAsString(map));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error: ", e);
		}
		
		return map;
	}
}
