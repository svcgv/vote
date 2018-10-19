package com.indihx.inspector.service;

import java.util.Map;
 
import com.indihx.inspector.entity.DC_THEME;
import com.indihx.inspector.vo.ThemeVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 制定检查主题</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月27日  上午 09:07:23</p>
 * <p></p>
 */
public interface IThemeService {
	/**
	 * 查询主题列表
	 * @param ThemeVo vo
	 * @return
	 */
	Map<String, Object> qryThemeList(ThemeVo vo);
	
	/**
	 * 新增检查主题
	 * @param ThemeVo vo
	 * @return
	 */
	boolean saveCheckTheme(UsrInfo usrInfo,ThemeVo vo);
	
	/**
	 * 查看检查主题详情信息
	 * @param theme_id 主题id
	 * @return
	 */
	DC_THEME getCheckTheme(String theme_id);
	
	/**
	 * 查询检查主题制定小区
	 * @param theme_id 主题id
	 * @return
	 */
	Map<String,Object> getCheckSect(String theme_id);
	
	/**
	 * 更新检查主题
	 * @param ThemeVo vo
	 * @return
	 */
	boolean updateCheckTheme(ThemeVo vo);
	
	/**
	 * 删除检查主题
	 * @param theme_id 主题id
	 * @return
	 */
	boolean delCheckTheme(String id);
	
	
	/**
	 * 查询主题列表
	 * @param ThemeVo vo
	 * @return
	 */
	Map<String, Object> qryQuotaJson(String themeId);
	
	
	/**
	 * 发布检查主题
	 * @param ThemeVo vo
	 * @return
	 */
	boolean updatePublishTheme(String theme_id);
}
