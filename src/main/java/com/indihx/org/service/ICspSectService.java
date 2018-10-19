package com.indihx.org.service;

import java.util.Map;

import com.indihx.comm.exception.BusinessException;
import com.indihx.org.entity.WY_GLC;
import com.indihx.org.vo.CspSectVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: ICspSectService.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月5日 下午6:49:33</p>
 * <p>@author ChuJinze</p>
 * <p>@version 1.0</p>
 * <p>ICspSectService.java</p>
 * <p></p>
 */
public interface ICspSectService {

	/**
	 * 获取管理处列表
	 * @param cspSectVo
	 * @return 管理处列表
	 */
	Map<String, Object> getCsList(UsrInfo user,CspSectVo cspSectVo);
	
	/**
	 * 保存从新增页面过来的数据
	 * @param cspSectVo  管理处实体
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	boolean addCsInfo(UsrInfo usrInfo,CspSectVo cspSectVo) throws BusinessException;
	
	/***
	 * 根据管理处ID删除管理处信息
	 * @param glcList  id集合
	 * @return  成功 or 失败 
	 */
	boolean delCsInfo(String[] glcList);
	
	/***
	 * 根据管理处ID查询管理处信息
	 * @param glcid  id
	 * @return  管理处信息详情
	 */
	WY_GLC qrCsInfoById(String glcid);
	
	/**
	 * 保存从编辑页面过来的数据
	 * @param cspSectVo  管理处实体
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	boolean editCsInfo(CspSectVo cspSectVo) throws BusinessException;
	
	

}
