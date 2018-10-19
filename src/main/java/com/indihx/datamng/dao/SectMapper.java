package com.indihx.datamng.dao;

import java.util.List;

import com.indihx.datamng.entity.Wy_Sect;
import com.indihx.datamng.entity.Wy_Sect_Temp;
import com.indihx.datamng.vo.SectVo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: 数据库库操作接口-其实现类是按照Mybatis模式，必须命名与接口类名字相同的xml配置文件来具体实现数据库操作接口类</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月5日下午12:26:06</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>SectMapper.java</p>
 * <p>注意与之对应的XML映射文件的实现，以及命名规则要求</p>
 */
public interface SectMapper {

	/**
	 * 查询项目信息列表
	 * @param sectVo
	 * @return
	 */
	List<Wy_Sect> getSectList(SectVo sectVo);

	/**
	 * 根据项目ID查询临时表是否已经存在
	 * @param xmid
	 * @return
	 */
	Wy_Sect_Temp getTempSectInfo(SectVo sectVo);


	/**
	 * 保存临时项目信息
	 * @param temp
	 * @return
	 */
	int saveSectTemp(Wy_Sect_Temp temp);


	/**
	 * 更新临时项目信息
	 * @param temp
	 * @return 
	 */
	int updateSectTemp(Wy_Sect_Temp temp);


	/**
	 * 保存历史表项目信息
	 * 历史表只有插入动作
	 * @param copy
	 */
	int saveSectHisInfo(SectVo copy);


	/**
	 * 保存正式表项目信息
	 * @param copy
	 */
	int saveSectInfo(SectVo copy);
	
	
	/**
	 * 更新正式表项目信息
	 * @param copy
	 */
	int updateSectInfo(SectVo copy);
	
	
	/**
	 * 更新正式表项目信息
	 * @param copy
	 */
	int deleteSectInfo(String xmid);

	
	/**
	 * 根据项目ID查询项目信息
	 * @param xmid
	 * @return
	 */
	Wy_Sect getSectInfoById(String xmid);


	/**
	 * 清理副本表信息
	 * @param appId
	 */
	int clearSectCopyInfo(Long appId);


	/**
	 * 修改项目数据状态
	 * 用于控制信息互斥，删除中、修改中数据不能再次发起修改或删除
	 * @param temp
	 * @return
	 */
	int updateInfoStatus(Wy_Sect_Temp temp);

}
