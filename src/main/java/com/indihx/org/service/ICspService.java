package com.indihx.org.service;

import java.util.Map;

import com.indihx.activiti.entity.Application;
import com.indihx.comm.exception.BusinessException;
import com.indihx.org.entity.WY_WYGS;
import com.indihx.org.entity.WY_WYGS_TEMP;
import com.indihx.org.vo.CspInfoVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 企业信息管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月30日 上午 10:30:23</p>
 * <p></p>
 */
public interface ICspService {

	/***
	 * 查询企业列表
	 * @param cspinfovo  
	 * @return  返回列表 
	 */
	Map<String, Object> cspList(UsrInfo user,CspInfoVo cspinfovo);
	
	/***
	 * 保存从新增页面过来的数据
	 * @param cspinfovo  企业实体
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	Application saveAddCspInfo(UsrInfo usrInfo, CspInfoVo cspVo) throws BusinessException;
	
	
	/***
	 * 根据企业ID查询企业详情
	 * @param id 企业ID
	 * @return  企业详细信息
	 */
	WY_WYGS qrCspInfoById(String wygsId);
	
	
	/***
	 * 保存修改后的企业信息
	 * @param cspinfovo  企业实体
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	Application saveEditCspInfo(UsrInfo usrInfo,CspInfoVo cspinfovo) throws BusinessException;	
	
	
	/***
	 * 保存删除后的企业信息
	 * @param cspinfovo  企业实体
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	Application saveDelCspInfo(UsrInfo usrInfo,CspInfoVo cspinfovo) throws BusinessException;	
	
	 
	/**
	 * 获取发送/提交的临时物业公司信息
	 * @param wygsid
	 * @return
	 */
	WY_WYGS_TEMP getTempCspInfo(String wygsid);
	
	/**
	 * (新增、编辑、删除)发送/提交物业信息至下一流程
	 * @param infoVo
	 * @return
	 */
	Application saveCspInfo(UsrInfo usrInfo, CspInfoVo cspVo) throws Exception;

	
}
