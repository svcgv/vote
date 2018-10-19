package com.indihx.datamng.service;

import java.util.Map;

import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.vo.HpbInfoVo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: IHpbService.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月29日 上午10:27:23</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>IHpbService.java</p>
 * <p></p>
 */
public interface IHpbService {

	/**
	 * 获取全市的区县列表
	 * @param hpbInfoVo
	 * @return
	 */
	Map<String, Object> getHpbList(HpbInfoVo hpbInfoVo);

	/**
	 * 保存区县信息
	 * @param hpbVo
	 * @return
	 * @throws Exception 
	 */
	boolean saveHpbInfo(HpbInfoVo hpbVo) throws Exception;

	/**
	 * 查看新增的区县信息
	 * @param hpbVo
	 * @return
	 * @throws Exception 
	 */
	Wy_Hpb getHpbInfo(String hpbid);
	
	/**
	 * 保存修改的区县信息
	 * @param hpbVo
	 * @return
	 * @throws Exception 
	 */
	boolean saveModifyHpbInfo(HpbInfoVo hpbVo) throws Exception;

	/**
	 * 删除区县信息
	 * @param hpbVo
	 * @return
	 * @throws Exception 
	 */
	boolean deleteHpbInfo(String hpbid) throws Exception;

}
