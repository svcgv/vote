package com.indihx.datamng.dao;

import java.util.List;

import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.entity.Wy_Jd;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: 区县信息管理数据库交互接口类</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月29日 上午11:30:56</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>HpbMapper.java</p>
 * <p></p>
 */
public interface HpbMapper {

	/**
	 * 查询区县列表
	 * @param hpb
	 * @return
	 */
	List<Wy_Hpb> getHpbList(Wy_Hpb hpb);

	/**
	 * 插入和更新时验证区县是否已经存在
	 * @param hpb
	 * @return
	 */
	List<Wy_Hpb> validHpbIsExists(Wy_Hpb hpb);
	
	/**
	 * 插入一条区县记录
	 * @param hpb
	 * @return
	 */
	int insertHpbInfo(Wy_Hpb hpb);

	/**
	 * 根据区县ID查询区县信息
	 * @param hpbid
	 * @return
	 */
	Wy_Hpb getHpbInfo(Long hpbid);
	
	/**
	 * 更新区域信息
	 * @param hpb
	 * @return
	 */
	int updateHpbInfo(Wy_Hpb hpb);
	
	/**
	 * 删除区域信息
	 * @param hpbid
	 * @return
	 */
	int deleteHpbInfo(Long hpbid);

	/**
	 * 删除区域时检查区域下是否存在街道
	 * @param hpbid
	 * @return
	 */
	List<Wy_Jd> checkLinkIsExists(Long hpbid);
	
	/**
	 * 
	 * @param hpb
	 * @return
	 */
	List<Wy_Hpb> getHpbListByType(Wy_Hpb hpb);
	
	/**
	 * 
	 * @param hpbid
	 * @return
	 */
	Long getHpbRefOrgId(Long hpbid);
	
	/**
	 * 
	 * @param orgNo
	 * @return
	 */
	Long getOrgRefHpbId(Long orgNo);
}
