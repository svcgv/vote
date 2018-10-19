package com.indihx.org.dao;

import java.util.List;

import com.indihx.org.entity.ORG_INFO;
import com.indihx.org.entity.WY_HOC_STAFF;
import com.indihx.org.entity.WY_YWH;

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 业委会信息管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月1日 上午 10:30:23</p>
 * <p></p>
 */
public interface HocMapper {
	/**
	 * 查询所有业委会信息 
	 * @param wygs
	 * @return
	 */
	List<WY_YWH> hocListAll(WY_YWH ywh);
	
	/**
	 * 新增业委会
	 * @param wygs 业委会表
	 * @return
	 */
	int insertHoc(WY_YWH ywh); 
	
	/**
	 * 新增机构信息
	 * @param orginfo 机构实体表
	 * @return
	 */
	int insertOrgInfo(ORG_INFO orginfo); 
	
	/**
	 * 验证新增业委会是否重复
	 * @param wygs
	 * @return
	 */
	List<WY_YWH> validHocIsExists(WY_YWH ywh);
	
	/**
	 * 删除业委会信息
	 * @param wygsId 业委会ID
	 * @return
	 */
    int deleteHoc(Long ywhId);
    
	/**
	 * 删除机构信息
	 * @param glcid 管理员ID
	 * @return
	 */
    int deleteOrgInfo(String glcid);
    
    /**
     * 查看业委会详情
     * @param wygsId 业委会ID
     * @return
     */
    WY_YWH qrHocInfoById(Long ywhId);

    /**
     * 更新业委会详情
     * @param wygs 业委会实体表
     * @return
     */
    int updateHoc(WY_YWH ywh);
    
    
	 /**
     * 更新机构详情
     * @param orginfo 机构实体表
     * @return
     */
    int updateOrgInfo(ORG_INFO orginfo);
    
    
	/**
	 * 新增业委会人员
	 * @param wygs 业委会人员
	 * @return
	 */
	int insertHocStaff(WY_HOC_STAFF hoc_staff); 
	
	
	/**
     * 查看业委会人员详情
     * @param wygsId 业委会ID
     * @param rylx 人员类型
     * @return
     */
    WY_HOC_STAFF qrHocStaffInfoById(Long ywhid,Long rylx);
    
    
	 /**
	 * 删除业委会人员信息
	 * @param wygsId 业委会人员ID
	 * @return
	 */
     
     int deleteHocStaff(Long ywhid);
     
     /**
     * 更新业委会人员详情
     * @param wygs 业委会人员实体表
     * @return
     */ 
    int updateHocStaff(WY_HOC_STAFF staff); 
	
    
}
