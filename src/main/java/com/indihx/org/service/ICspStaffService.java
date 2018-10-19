package com.indihx.org.service;

import java.util.Map;

import com.indihx.comm.exception.BusinessException;
import com.indihx.org.entity.WY_WYGS_STAFF;
import com.indihx.org.vo.CspStaffVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: ICspStaffService.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月9日 上午11:33:08</p>
 * <p>@author ChuJinze</p>
 * <p>@version 1.0</p>
 * <p>ICspStaffService.java</p>
 * <p></p>
 */
public interface ICspStaffService {

	/**
	 * 获取人员信息列表
	 * @param cspStaffVo
	 * @return 人员信息列表
	 */
	Map<String, Object> getStaffList(CspStaffVo cspStaffVo,UsrInfo usrInfo);
	
	/**
	 * 保存从新增页面过来的数据
	 * @param cspSectVo  企业人员实体
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	boolean addStaffInfo(CspStaffVo cspStaffVo,UsrInfo usrInfo) throws BusinessException;
	
	/***
	 * 根据管理处ID删除人员信息
	 * @param glcList  id集合
	 * @return  成功 or 失败 
	 */
	boolean delStaffInfo(String[] gsryidList);
	
	/***
	 * 根据管理处ID查询人员信息
	 * @param glcid  id
	 * @return  管理处信息详情
	 */
	WY_WYGS_STAFF qrStaffInfoById(String gsryid);
	
	/**
	 * 保存从编辑页面过来的数据
	 * @param cspSectVo  管理处实体
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	boolean editStaffInfo(CspStaffVo cspStaffVo) throws BusinessException;
	
	
	
	

}
