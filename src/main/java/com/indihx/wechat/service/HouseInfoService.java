package com.indihx.wechat.service;


import java.util.Map;


public interface HouseInfoService {
	Map<String, Object> getSectInfo(Map<String, Object> requestMap);
	
	Map<String, Object> getBuildInfo(Map<String, Object> requestMap);
	
	Map<String, Object> getUnitInfo(Map<String, Object> requestMap);
	
	Map<String, Object> getFloorInfo(Map<String, Object> requestMap);
	
	Map<String, Object> getRoomInfo(Map<String, Object> requestMap);
	
	Map<String, Object> bindHouseInfo(Map<String, Object> requestMap);
	
	Map<String, Object> unbindHouseInfo(Map<String, Object> requestMap);
	
	Map<String, Object> getHouseInfoByOpenId(String openId);
}
