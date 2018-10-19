package com.indihx.org.dao;

import java.util.List;

import com.indihx.datamng.vo.SectVo;
import com.indihx.org.entity.ORG_INFO;
import com.indihx.org.entity.WY_GLC;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CspSectMapper.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月5日 下午6:47:36</p>
 * <p>@author ChuJinze</p>
 * <p>@version 1.0</p>
 * <p>CspSectMapper.java</p>
 * <p></p>
 */
public interface CspSectMapper {
	
	/**
	 * 查询管理处列表
	 * @param glc
	 * @return
	 */
	List<WY_GLC> getCsList(WY_GLC glc);

	/**
	 * 查询管理处列表
	 * @param glc
	 * @return
	 */
	List<WY_GLC> getCspSectList(SectVo sectVo);
	
	/**
	 * 新增管理处
	 * @param glc
	 * @return
	 */
	int insertCs(WY_GLC glc); 
	
	/**
	 * 新增机构信息
	 * @param orginfo 机构实体表
	 * @return
	 */
	int insertOrgInfo(ORG_INFO orginfo); 
	
	/**
	 * 验证新增管理处是否重复
	 * @param glc
	 * @return
	 */
	List<WY_GLC> validCsIsExists(WY_GLC glc);

	/**
	 * 删除机构信息
	 * @param glcid 管理员ID
	 * @return
	 */
    int deleteOrgInfo(String glcid);
    
    
	/**
	 * 删除管理处
	 * @param glcid 管理员ID
	 * @return
	 */
	int deleteCs(Long glcid); 

	
	/**
	 * 查看管理处
	 * @param glc
	 * @return
	 */
	WY_GLC qrCsInfoById(Long glcid); 

	/**
	 * 更新管理处
	 * @param glc
	 * @return
	 */
	int updateCs(WY_GLC glc); 
	
	 /**
     * 更新机构详情
     * @param orginfo 机构实体表
     * @return
     */
    int updateOrgInfo(ORG_INFO orginfo);
	
	
}
