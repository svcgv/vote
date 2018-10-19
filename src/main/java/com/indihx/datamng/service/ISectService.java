package com.indihx.datamng.service;

import java.util.Map;

import com.indihx.activiti.entity.Application;
import com.indihx.datamng.entity.Wy_Sect_Temp;
import com.indihx.datamng.entity.Wy_Sect;
import com.indihx.datamng.vo.SectVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: 业务层接口</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月5日下午12:04:33</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>ISectService.java</p>
 * <p></p>
 */
public interface ISectService {

	/**
	 * 获取项目列表
	 * @param sectVo
	 * @return
	 */
	Map<String, Object> getSectList(UsrInfo user,SectVo sectVo);

	/**
	 * 保存项目信息
	 * @param usrInfo 
	 * @param infoVo
	 * @return
	 * @throws Exception 
	 */
	Application saveAddSectInfo(UsrInfo usrInfo, SectVo infoVo) throws Exception;

	/**
	 * 保存项目信息
	 * @param usrInfo 
	 * @param infoVo
	 * @return
	 * @throws Exception 
	 */
	Application saveEditSectInfo(UsrInfo usrInfo, SectVo infoVo) throws Exception;

	
	/**
	 * 提交时保存项目信息
	 * @param usrInfo 
	 * @param infoVo
	 * @return
	 * @throws Exception 
	 */
	Application saveSectInfo(UsrInfo usrInfo, SectVo infoVo) throws Exception;
	
	/**
	 * 查看项目信息
	 * @param xmid
	 * @return
	 */
	Wy_Sect getSectInfo(String xmid);

	/**
	 * 加载街道列表
	 * @param infoVo
	 * @return
	 */
	Map<String, Object> getJdList(SectVo infoVo);

	/**
	 * 加载社区列表
	 * @param infoVo
	 * @return
	 */
	Map<String, Object> getSqList(SectVo infoVo);

	/**
	 * 获取发送/提交的临时项目信息
	 * @param xmid
	 * @return
	 */
	Wy_Sect_Temp getTempSectInfo(String xmid);
	
	/**
	 * 查询项目管理处
	 * @param xmid
	 * @param wygsid
	 * @return
	 */
	Map<String, Object> getCsList(SectVo  vo);

	/**
	 * 查询项目经理
	 * @param vo
	 * @return
	 */
	Map<String, Object> getXmjlList(SectVo vo);

	

	
}
