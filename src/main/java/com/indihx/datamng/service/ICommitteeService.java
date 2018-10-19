package com.indihx.datamng.service;

import java.util.Map;

import com.indihx.datamng.entity.Wy_Sq;
import com.indihx.datamng.vo.CommitteeVo;

/***
 * 
 * <p>描 述: 社区信息管理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年12月13日</p>
 * @author 
 */
public interface ICommitteeService {
	
	
	/***
	 * 查询社区列表
	 * @param committeeVo
	 * @return  返回列表 
	 */
	Map<String, Object> qryCommitteeList(CommitteeVo committeeVo);
	
	/***
	 * 保存社区信息
	 * @param regionVo  vo实体
	 * @return 成功or失败
	 */
	boolean addCommitteeInfo(CommitteeVo infoVo);
	
	/***
	 * 根据社区ID查询社区信息
	 * @param id
	 * @return  
	 */
	Wy_Sq qryCommitteeInfoById(String cmtId);
	
	/***
	 * 修改社区信息
	 * @param infoVo  vo
	 * @return 成功or失败
	 */
	boolean editCommitteeInfo(CommitteeVo infoVo);
	
	/***
	 * 根据ID删除信息
	 * @param infoVo 
	 * @return  成功 or 失败 
	 */
	boolean deleteCommitteeInfo(CommitteeVo infoVo);

	
}
