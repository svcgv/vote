package com.indihx.tender.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.indihx.comm.exception.BusinessException;
import com.indihx.tender.entity.ZTB_EXPERT;
import com.indihx.tender.vo.BidExpertVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: IBidExpertService.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年03月22日 下午5:10:12</p>
 * <p>@author yechonghui</p>
 * <p>@version 1.0</p>
 * <p>IBidExpertService.java</p>
 * <p></p>
 */
public interface IBidExpertService {

	/**
	 * 首页
	 * @param bidExpertVo
	 * @return 评标专家列表
	 */
	Map<String, Object> getExList(UsrInfo user,BidExpertVo bidExpertVo);
	
	/**
	 * 新增保存
	 * @param bidExpertVo
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	boolean addExInfo(UsrInfo usrInfo,BidExpertVo bidExpertVo) throws BusinessException;
	
	/**
	 * 编辑保存
	 * @param bidExpertVo
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	boolean editExInfo(BidExpertVo bidExpertVo) throws BusinessException;
	
	/**
	 * 删除
	 * @param expert_id_list 专家ID集合
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	boolean delExInfo(String[] expert_id_list) throws BusinessException;
	
	/**
	 * 查看详情
	 * @param expert_id 专家ID
	 * @return 成功or失败
	 * @throws BusinessException 
	 */
	ZTB_EXPERT getExInfo(String expert_id) throws BusinessException;

	/**
	 * @param usrInfo
	 * @param myfiles
	 * @param fileTypeId
	 * @param relaPath 
	 * @return
	 */
	Map<String, Object> loadDataInfo(UsrInfo usrInfo,
			MultipartFile[] myfiles, String fileTypeId);

}
