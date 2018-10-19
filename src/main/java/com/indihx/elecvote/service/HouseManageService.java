package com.indihx.elecvote.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: HouseManageService.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年03月22日 下午5:10:12</p>
 * <p>@author zhaoyongfeng</p>
 * <p>@version 1.0</p>
 * <p>HouseManageService.java</p>
 * <p></p>
 */
public interface HouseManageService {
	
	Map<String, Object> loadDataInfo(UsrInfo usrInfo,
			MultipartFile[] myfiles, String fileTypeId);
	
	//Map<String, Object> getHouseInfo(UsrInfo usrInfo, String sectName, int currentPage, int pageSize);
	Map<String, Object> getHouseInfo(UsrInfo usrInfo, Map<String, Object> requestMap);
	
	Map<String, Object> getHouseInfoAndSelect(UsrInfo usrInfo, String topicId, Map<String, Object> requestMap, Boolean bool);
	
	VoteHouseInfo getHouseInfoById(BigDecimal infoId);
	
	boolean editHouseInfo (VoteHouseInfo voteHouseInfo);
	
	boolean	delHouseInfo(Map<String, Object> requestMap);
	
	Map<String, Object> addHouseInfo(VoteHouseInfo voteHouseInfo);
}
