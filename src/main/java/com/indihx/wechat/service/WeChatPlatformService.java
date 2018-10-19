package com.indihx.wechat.service;

import java.util.Map;

import com.indihx.wechat.vo.AccessToken;
import com.indihx.wechat.vo.OauthResponse;
import com.indihx.wechat.vo.RequestMessage;
import com.indihx.wechat.vo.ResponseMessage;
import com.indihx.wechat.vo.TicketResponse;

public interface WeChatPlatformService {
	boolean checkSignature(String signature, String timestamp, String nonce);
	
	AccessToken getAccessToken(String appId, String appSecret);
	
	OauthResponse getOauthToken(String appId, String appSecret, String code);
	
	ResponseMessage handlerMessage(RequestMessage request);
	
	//TicketResponse getTicket();
	
	Map<String, Object> getConfig(String url);
}
