package com.indihx.org.service;

import java.util.Map;

import com.indihx.comm.exception.BusinessException;
import com.indihx.org.entity.WY_HOC_STAFF;
import com.indihx.org.entity.WY_YWH;
import com.indihx.org.vo.HocVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 业委会信息管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月30日 上午 10:30:23</p>
 * <p></p>
 */
public interface IHocService {

	/***
	 * 查询业委会列表
	 * @param hocvo  
	 * @return  返回列表 
	 */
	Map<String, Object> hocList(UsrInfo user,HocVo hocvo);

	/***
	 * 保存从新增页面过来的数据
	 * @param hocvo  业委会实体
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	boolean addHocInfo(UsrInfo usrInfo,HocVo hocvo) throws BusinessException;
	
	/***
	 * 根据业委会ID查询业委会详情
	 * @param id 业委会ID
	 * @return  业委会详细信息
	 */
	WY_YWH qrHocInfoById(String wygsId);
	
	/***
	 * 保存修改后的业委会信息
	 * @param hocvo  业委会实体
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	boolean editHocInfo(HocVo hocvo) throws BusinessException;	
	
	/***
	 * 根据业委会ID删除业委会信息
	 * @param wygsIdList  id集合
	 * @return  成功 or 失败 
	 */
	boolean delHocInfo(String[] ywhIdList);
	 
	
	/**
     * 查看业委会人员详情
     * @param wygsId 业委会人员ID
     * @param rylx 业委会人员类型
     * @return
     */
	WY_HOC_STAFF qrHocStaffInfoById(Long ywhid,Long rylx);
 
	
}
