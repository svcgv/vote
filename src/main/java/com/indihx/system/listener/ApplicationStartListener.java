package com.indihx.system.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.indihx.cache.RedisCache;
import com.indihx.comm.InitSysConstants;
import com.indihx.system.dao.ParamsInfoMapper;
import com.indihx.system.entity.ParamsInfo;
import com.indihx.wechat.service.WeChatPlatformService;
import com.indihx.wechat.vo.AccessToken;


public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent>{
	private Logger logger = LoggerFactory.getLogger(getClass());
	public static RedisCache  redisCache = new RedisCache("12345");
	
	@Autowired
	private WeChatPlatformService 	weChatPlatformService;
	@Autowired
	private ParamsInfoMapper paramsInfoMapper;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() != null){
			System.out.println("start application get token onApplicationEvent");
			ApplicationContext application= event.getApplicationContext();
			logger.info("----------------onApplicationEvent get token---------------------------");
			//RedisCache redis = new RedisCache("12345");
			//redisCache.putObject("token", "12_idp8NMQQKlIK1VVGn6R2348DTNQzJtwlSHSX_J13UM6HmyIRrEYFz4sIC5Rmj5HGQ3SVuOhWASVcnhJgdX5mZKDHOmwKyXCYgxnyL3axADXBAPPYsTAYNVah0x6p33XHL_IJ2cNNigvuVrk4JZIdAAAPFO");
			//System.out.println("put redis over: "+ redisCache.getObject("token"));
		
			// TODO Auto-generated method stub
			
			Runnable runnbale = new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(true) {
						try {
							ParamsInfo  param1 = paramsInfoMapper.selectByPrimaryKey("APPID");
							ParamsInfo  param2 = paramsInfoMapper.selectByPrimaryKey("SECRET");
							AccessToken accessToken = weChatPlatformService.getAccessToken(param1.getParamsVal(), param2.getParamsVal());
							redisCache.putObject("accessToken", accessToken.getAccess_token());
							logger.info("token: "+accessToken.getAccess_token());
							if (accessToken.getExpires_in() > 300) {
								Thread.sleep((accessToken.getExpires_in()-300)*1000);
							}else {
								Thread.sleep(1000*300);
							}
						} catch (Exception e) {
							// TODO: handle exception
							logger.error("get accesstoken error!!!");
						}	
					}			
				}
			};
			
			new Thread(runnbale).start();
		}
	}

}
