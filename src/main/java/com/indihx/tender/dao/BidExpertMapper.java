package com.indihx.tender.dao;

import java.util.List;

import com.indihx.org.entity.WY_GLC;
import com.indihx.tender.entity.ZTB_EXPERT;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: BidExpertMapper.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年03月23日 上午8:51:36</p>
 * <p>@author yechonghui</p>
 * <p>@version 1.0</p>
 * <p>BidExpertMapper.java</p>
 * <p></p>
 */
public interface BidExpertMapper {
	/**
	 * 查询评标专家列表
	 * @param expert
	 * @return
	 */
	List<ZTB_EXPERT> getExList(ZTB_EXPERT expert);
	
	/**
	 * 新增评标专家
	 * @param expert
	 * @return
	 */
	int insertEx(ZTB_EXPERT expert);
	
	/**
	 * 编辑评标专家
	 * @param expert
	 * @return
	 */
	int updateEx(ZTB_EXPERT expert);
	
	/**
	 * 验证新增人员是否重复
	 * @param glc
	 * @return
	 */
	List<ZTB_EXPERT> validExIsExists(ZTB_EXPERT expert);
	
	/**
	 * 删除专家信息
	 * @param expert_id 专家ID
	 * @return
	 */
	int deleteEx(Long expert_id); 
	
	/**
	 * 查询专家详细信息
	 * @param expert_id 专家ID
	 * @return
	 */
	ZTB_EXPERT getExInfo(Long expert_id); 
	
}
