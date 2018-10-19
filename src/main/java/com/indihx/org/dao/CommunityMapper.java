package com.indihx.org.dao;

import java.util.List;

import com.indihx.org.entity.ORG_INFO;
import com.indihx.org.entity.WY_JWH;

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 居委会信息管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月308日 上午 10:30:23</p>
 * <p></p>
 */
public interface CommunityMapper {
	/**
	 * 查询所有居委会信息 
	 * @param wygs
	 * @return
	 */
	List<WY_JWH> communityListAll(WY_JWH jwh) ;
	
	/**
	 * 新增居委会
	 * @param wygs 居委会表
	 * @return
	 */
	int insertCommunity(WY_JWH jwh); 
	
	/**
	 * 新增机构信息
	 * @param orginfo 机构实体表
	 * @return
	 */
	int insertOrgInfo(ORG_INFO orginfo); 
	
	/**
	 * 验证新增居委会是否重复
	 * @param wygs
	 * @return
	 */
	List<WY_JWH> validCommunityIsExists(WY_JWH jwh);
	
	/**
	 * 删除居委会信息
	 * @param wygsId 居委会ID
	 * @return
	 */
    int deleteCommunity(Long jwhId);
    
	/**
	 * 删除机构信息
	 * @param glcid 管理员ID
	 * @return
	 */
    int deleteOrgInfo(String glcid);
    
    /**
     * 查看居委会详情
     * @param wygsId 居委会ID
     * @return
     */
    WY_JWH qrCommunityInfoById(Long jwhId);

    /**
     * 更新居委会详情
     * @param wygs 居委会实体表
     * @return
     */
    int updateCommunity(WY_JWH jwh);
    
	 /**
     * 更新机构详情
     * @param orginfo 机构实体表
     * @return
     */
    int updateOrgInfo(ORG_INFO orginfo);
    
}
