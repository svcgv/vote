package com.indihx.wechat.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indihx.comm.InitSysConstants;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.HttpUtils;
import com.indihx.comm.util.StringUtil;
import com.indihx.system.dao.ParamsInfoMapper;
import com.indihx.system.entity.ParamsInfo;
import com.indihx.system.listener.ApplicationStartListener;
import com.indihx.util.StringUtils;
import com.indihx.wechat.service.WeChatPlatformService;
import com.indihx.wechat.vo.AccessToken;
import com.indihx.wechat.vo.OauthResponse;
import com.indihx.wechat.vo.RequestMessage;
import com.indihx.wechat.vo.ResponseMessage;
import com.indihx.wechat.vo.TicketResponse;

@Service
public class WeChatPlatformServiceImpl implements WeChatPlatformService{
	private Logger logger = LoggerFactory.getLogger(WeChatPlatformServiceImpl.class);
	@Autowired
	private ParamsInfoMapper paramsInfoMapper;

	@Override
	public boolean checkSignature(String signature, String timestamp, String nonce) {
		// TODO Auto-generated method stub
		String[] arr = new String[3];
		arr[0] = timestamp;
		arr[1] = nonce;
		//arr[2] = InitSysConstants.TOKEN;
		ParamsInfo  param = paramsInfoMapper.selectByPrimaryKey("TOKEN");
		arr[2] = param.getParamsVal();
		
		Arrays.sort(arr);
		StringBuilder build = new StringBuilder(); 
		for (String s : arr) {
			build.append(s);
		}
		logger.debug("build: "+build);
		String res = DigestUtils.shaHex(build.toString());
		logger.debug("res: "+res);
		if (res.equals(signature))
			return true;
		
		return false;
	}
	
	@Override
	public AccessToken getAccessToken(String appId, String appSecret) {
		// TODO Auto-generated method stub
		String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appId, appSecret);
		logger.info("get token url: "+ url);
		String response = HttpUtils.httpsGet(url);
		logger.debug("response: "+response);
		AccessToken accessToken = new AccessToken();
		try {
			accessToken = new ObjectMapper().readValue(response, accessToken.getClass());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("json error: ", e);
		}
		return accessToken;
	}

	public static void main(String[] args) {
		WeChatPlatformServiceImpl weChat = new WeChatPlatformServiceImpl();
		/*检验签名*/
		//weChat.checkSignature("2a9525b4633da40f89808b8b160dde820dbd1eb4","1532571133","1616781375");
		/*获取token*/
		weChat.getAccessToken("wx0faa55f1a53f29c5", "a9a586b82bfb51e89a35a51a443d517f");
	}

	@Override
	public ResponseMessage handlerMessage(RequestMessage request) {
		// TODO Auto-generated method stub
		try {
			logger.debug("request message: "+ new ObjectMapper().writeValueAsString(request) );
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error: ", e);
		}
		ResponseMessage response = new ResponseMessage();
		response.setFromUserName(request.getToUserName());
		response.setToUserName(request.getFromUserName());
		response.setCreateTime(request.getCreateTime());
		response.setMsgType(request.getMsgType());
		
		if (request.getMsgType().equals("event")) {
			
		}else if(request.getMsgType().equals("text")) {
			
		}else if(request.getMsgType().equals("image")) {
			
		}else if(request.getMsgType().equals("voice")) {
			
		}else if(request.getMsgType().equals("video")) {
			
		}else if (request.getMsgType().equals("location")) {
			
		}else if (request.getMsgType().equals("link")) {
			
		}
		
		response.setContent("你好，欢迎关注哈尔滨投票系统");
		
		return response;
	}
	
	ResponseMessage handleEvent(RequestMessage request) {
		ResponseMessage response = new ResponseMessage();
		
		if (request.getEvent().equals("subscribe") && StringUtils.isEmpty(request.getEventKey())) {//订阅
			
		}else if (request.getEvent().equals("unsubscribe")) {//取消订阅
			
		}else if (request.getEvent().equals("subscribe") && !StringUtils.isEmpty(request.getEventKey())) {//用户扫码
			
		}else if (request.getEvent().equals("LOCATION")) {//用户上传位置信息
			
		}else if (request.getEvent().equals("CLICK")) {//自定义菜单
			
		}
		
		return response;
	}
	
	ResponseMessage handleText(RequestMessage request) {
		ResponseMessage response = new ResponseMessage();
		return response;
	}

	@Override
	public OauthResponse getOauthToken(String appId, String appSecret, String code) {
		// TODO Auto-generated method stub
		String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", appId, appSecret,code);
		logger.debug("url: "+url);
		String response = HttpUtils.httpsGet(url);
		logger.debug("oauthresponse: "+ response);
		OauthResponse oauthResponse = new OauthResponse();
		try {
			oauthResponse = new ObjectMapper().readValue(response, oauthResponse.getClass());
			/*
			if (oauthResponse.getErrcode().equals("40163")) {//code been used
				}*/	
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("json error: ", e);
		}
		if (!StringUtils.isEmpty(oauthResponse.getErrcode())) {
			logger.error("errcode: "+oauthResponse.getErrcode()+" errmsg: "+oauthResponse.getErrmsg());
			throw new BusinessException("get oauthtoken exception");
		}
		return oauthResponse;
	}

	@Override
	public Map<String, Object> getConfig(String url) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		TicketResponse  ticket = getTicket();
		Map<String, String> configMap = sign(ticket.getTicket(), url);
		map.put("configMap", configMap);
		
		return map;
	}
	
	private TicketResponse getTicket() {
		// TODO Auto-generated method stub
		String token = (String)ApplicationStartListener.redisCache.getObject("accessToken");
		if (StringUtils.isEmpty(token)) {
			ParamsInfo  param1 = paramsInfoMapper.selectByPrimaryKey("APPID");
			ParamsInfo  param2 = paramsInfoMapper.selectByPrimaryKey("SECRET");
			AccessToken accessToken = getAccessToken(param1.getParamsVal(), param2.getParamsVal()); 
			ApplicationStartListener.redisCache.putObject("accessToken", accessToken.getAccess_token());
			token = accessToken.getAccess_token();
		}
		String url = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi",token);
		logger.debug("getticket url: "+url);
		String response = HttpUtils.httpsGet(url);
		logger.debug("response: "+response);
		TicketResponse ticket = new TicketResponse();
		try {
			ticket = new ObjectMapper().readValue(response, ticket.getClass());
			logger.info("ticket: "+ ticket.getTicket());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("json error: ", e);
		}
		return ticket;
	}
	
	private Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
        	logger.error("sha error",e);
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
        	logger.error("sha error",e);
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ParamsInfo  param1 = paramsInfoMapper.selectByPrimaryKey("APPID");
        ret.put("appId", param1.getParamsVal());

        return ret;
    }
	
	private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
