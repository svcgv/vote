package com.indihx.org.service;

import java.util.Map;

import com.indihx.comm.exception.BusinessException;
import com.indihx.org.entity.WY_JWH;
import com.indihx.org.vo.CommunityVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 居委会信息管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月8日 上午 10:30:23</p>
 * <p></p>
 */
public interface ICommunityService {

	/***
	 * 查询居委会列表
	 * @param CommunityVo  
	 * @return  返回列表 
	 */
	Map<String, Object> communityList(CommunityVo CommunityVo);

	/***
	 * 保存从新增页面过来的数据
	 * @param CommunityVo  居委会实体
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	boolean addCommunityInfo(UsrInfo usrInfo,CommunityVo CommunityVo) throws BusinessException;
	
	/***
	 * 根据居委会ID查询居委会详情
	 * @param id 居委会ID
	 * @return  居委会详细信息
	 */
	WY_JWH qrCommunityInfoById(String jwhId);
	
	/***
	 * 保存修改后的居委会信息
	 * @param CommunityVo  居委会实体
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	boolean editCommunityInfo(CommunityVo CommunityVo) throws BusinessException;	
	
	/***
	 * 根据居委会ID删除居委会信息
	 * @param wygsIdList  id集合
	 * @return  成功 or 失败 
	 */
	boolean delCommunityInfo(String[] jwhIdList);
	 
	
}
