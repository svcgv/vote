package com.indihx.credit.dao;

import java.util.List;

import com.indihx.credit.entity.CreditFileSign;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CreditFileSignMapper.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月16日上午10:54:01</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>CreditFileSignMapper.java</p>
 * <p></p>
 */
public interface CreditFileSignMapper {

    /***
     * 新增档案
     * @param record
     * @return
     */
    int saveFile(CreditFileSign record);
    
    /***
	 * 删除档案
	 * @param signId
	 * @return
	 */
    int deleteFile(CreditFileSign record);
    
    /***
     * 修改档案
     * @param record
     * @return
     */
    int updateFile(CreditFileSign record);
    
    /***
     * 查询档案
     * @param signId
     * @return
     */
    List<CreditFileSign> getCreditFileList(CreditFileSign record);

    
}
