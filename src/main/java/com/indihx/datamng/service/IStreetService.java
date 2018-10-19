package com.indihx.datamng.service;

import java.util.Map;

import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.entity.Wy_Jd;
import com.indihx.datamng.vo.StreetInfoVo;
import com.indihx.datamng.vo.HpbInfoVo;;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: IStreetService.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月29日 上午10:27:23</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>IStreetService.java</p>
 * <p></p>
 */
public interface IStreetService {
	
	/***
	 * 查询街道列表
	 * @param regionVo  行政区域Vo条件
	 * @return  返回列表 
	 */
	Map<String, Object> qryStreetList(StreetInfoVo streetVo);
	
	/***
	 * 保存街道信息
	 * @param streetVo  vo实体
	 * @return 成功or失败
	 */
	boolean addStreetInfo(StreetInfoVo streetVo);
	
	/***
	 * 保存修改后的行政信息
	 * @param streetVo  vo
	 * @return 成功or失败
	 */
	boolean editStreetInfo(StreetInfoVo infoVo);
	
	/***
	 * 根据ID删除信息
	 * @param adminList  id集合
	 * @return  成功 or 失败 
	 */
	boolean deleteStreetInfo(StreetInfoVo infoVo);
	
	

	Wy_Jd qryStreetById(String streetId);

	String qryParentId(HpbInfoVo regionVo);

	Map<String, Object> selectByRegionType(HpbInfoVo adminRegionVo);

	Wy_Hpb selectByStreetIdKey(String streetId);

	Wy_Jd openAqstreetUserList(String streetId);

	Map<String, Object> qryAddInfo();

	Map<String, Object> qryEditInfo();
	
}
