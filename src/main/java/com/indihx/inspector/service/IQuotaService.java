package com.indihx.inspector.service;


import java.util.Map;

import com.indihx.inspector.entity.Dc_Khzb;
import com.indihx.inspector.vo.KhzbVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 日常事务督察指标管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月11日 下午 09:07:23</p>
 * <p></p>
 */
public interface IQuotaService {
	/**
	 * 查询指标列表
	 * @param dc_khzb
	 * @return
	 */
	Map<String, Object> queryKhzbList(KhzbVo vo);
	
	
	/**
	 * 查询一级指标列表
	 * @param dc_khzb
	 * @return
	 */
	Map<String, Object> qryOneQuotaList(KhzbVo vo);
	
	
	/**
	 * 新增一个考核指标
	 * @param dc_khzb
	 * @return
	 */
	boolean insertOneKhzb(UsrInfo usrInfo,KhzbVo vo);
	 
	
	/**
	 * 查看检查主题详情信息
	 * @param theme_id 主题id
	 * @return
	 */
	Dc_Khzb getQuota(String check_seq);
	
	
	/**
	 * 根据主键更新一个考核指标
	 * @param dc_khzb
	 * @return
	 */
	boolean updateQuota(KhzbVo vo);
	
	/**
	 * 根据主键删除一个考核指标
	 * @param check_seq
	 * @return
	 */
	boolean deleteOneKhzb(String check_seq);
}
