package com.indihx.org.dao;

import java.util.List;

import com.indihx.org.entity.WY_WYGS_STAFF;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CspStaffMapper.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月9日 上午10:59:24</p>
 * <p>@author ChuJinze</p>
 * <p>@version 1.0</p>
 * <p>CspStaffMapper.java</p>
 * <p></p>
 */
public interface CspStaffMapper {
	
	/**
	 * 查询当前企业的人员信息
	 * @param staff
	 * @return
	 */
	List<WY_WYGS_STAFF> getStaffList(WY_WYGS_STAFF staff);
	
	/**
	 * 新增人员信息
	 * @param glc
	 * @return
	 */
	int insertStaff(WY_WYGS_STAFF staff);

	/**
	 * 删除人员信息
	 * @param glc
	 * @return
	 */
	int deleteStaff(Long gsryid); 

	/**
	 * 查看人员信息
	 * @param glc
	 * @return
	 */
	WY_WYGS_STAFF qrStaffInfoById(Long gsryid); 

	/**
	 * 更新人员信息
	 * @param glc
	 * @return
	 */
	int updateStaff(WY_WYGS_STAFF staff);

	
	
	

}
