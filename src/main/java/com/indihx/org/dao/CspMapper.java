package com.indihx.org.dao;

import java.util.List;

import com.indihx.org.entity.ORG_INFO;
import com.indihx.org.entity.WY_WYGS;
import com.indihx.org.entity.WY_WYGS_TEMP;
import com.indihx.org.vo.CspInfoVo;

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 企业信息管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月30日 上午 10:30:23</p>
 * <p></p>
 */
public interface CspMapper {
	/**
	 * 查询所有企业信息 
	 * @param wygs
	 * @return
	 */
	List<WY_WYGS> cspinfoAll(WY_WYGS wygs);
	

	/**
	 * 根据物业公司ID查询临时表是否已经存在
	 * @param xmid
	 * @return
	 */
	WY_WYGS_TEMP getTempCspInfo(CspInfoVo cspinfoVo);
	
	
	/**
	 * 保存临时项目信息
	 * @param temp
	 * @return
	 */
	int saveCspTemp(WY_WYGS_TEMP temp);
	
	
	/**
	 * 更新临时项目信息
	 * @param temp
	 * @return 
	 */
	int updateCspTemp(WY_WYGS_TEMP temp);
	
	

	/**
	 * 保存历史表企业信息
	 * @param copy
	 */
	int saveCspHis(CspInfoVo copy);

	
	
	
	/**
	 * 新增机构信息
	 * @param orginfo 机构实体表
	 * @return
	 */
	int insertOrgInfo(ORG_INFO orginfo); 
	
	/**
	 * 新增正式表物业企业信息
	 * @param wygs 企业表
	 * @return
	 */
	int insertCsp(CspInfoVo copy); 
	
	/**
	 * 验证新增企业是否重复
	 * @param wygs
	 * @return
	 */
	List<WY_WYGS> validCspIsExists(CspInfoVo vo);
	
	
	/**
	 * 删除机构信息
	 * @param wygsId 企业ID
	 * @return
	 */
    int deleteOrgInfo(String wygsId);
    
	
	/**
	 * 删除企业信息
	 * @param wygsId 企业ID
	 * @return
	 */
    int deleteCsp(String wygsId);
    
    
    /**
     * 查看企业详情
     * @param wygsId 企业ID
     * @return
     */
    WY_WYGS qrCspInfoById(String wygsId);

    
    /**
     * 更新机构详情
     * @param orginfo 机构实体表
     * @return
     */
    int updateOrgInfo(ORG_INFO orginfo);
    
   
    /**
     * 更新正式表物业企业详情
     * @param wygs 企业实体表
     * @return
     */
    int updateCsp(CspInfoVo copy);
	
    
	/**
	 * 清理物业临时表信息
	 * @param appId
	 */
	int clearCspCopyInfo(Long appId);
	
	
	/**
	 * 修改项目数据状态
	 * 用于控制信息互斥，删除中、修改中数据不能再次发起修改或删除
	 * @param temp
	 * @return
	 */
	int updateCspStatus(WY_WYGS_TEMP temp);
	
	
}
