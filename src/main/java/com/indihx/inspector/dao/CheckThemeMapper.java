package com.indihx.inspector.dao;

import java.util.List;

import com.indihx.inspector.entity.DC_CKSECT;
import com.indihx.inspector.entity.DC_CKZB;
import com.indihx.inspector.entity.DC_THEME;


public interface CheckThemeMapper {
	/**
	 * 查询所有主题信息
	 * @param wygs
	 * @return
	 */
	List<DC_THEME> qryThemeList(DC_THEME theme);
	
	/**
	 * 保存主题信息
	 * @param temp
	 * @return
	 */
	int insertTheme(DC_THEME theme);
	
	/**
	 * 保存指定检查小区
	 * @param temp
	 * @return
	 */
	int insertCKSECT(DC_CKSECT cksect);
	
	/**
	 * 保存检查指标
	 * @param temp
	 * @return
	 */
	int insertCKZB(DC_CKZB ckzb);
	
	/**
	 * 查看检查主题详情信息
	 * @param theme_id 主题id
	 * @return
	 */
	DC_THEME getCheckTheme(String theme_id);
	
	/**
	 * 查询检查主题制定小区
	 * @param theme_id
	 * @return
	 */
	List<DC_CKSECT> getCheckSect(String theme_id);
	
	
	/**
	 * 查询检查主题制定小区
	 * @param quota
	 * @return
	 */
	List<DC_CKZB> getChecQuotaList(DC_CKZB quota);
	
	
	/**
	 * 更新主题信息
	 * @param temp
	 * @return 
	 */
	int updateTheme(DC_THEME theme);
	
	
	/**
	 * 删除检查主题表
	 * @param wygsId 企业ID
	 * @return
	 */
    int deleteTheme(String id);
    
    /**
	 * 删除指定检查小区
	 * @param wygsId 企业ID
	 * @return
	 */
    int deleteCKSECT(String id);
    
    /**
	 * 删除检查指标
	 * @param wygsId 企业ID
	 * @return
	 */
    int deleteCKZB(String id);
    
    
	/**
	 * 发布检查主题
	 * @param temp
	 * @return 
	 */
	int updatePublishTheme(DC_THEME theme);
	
}
