package com.indihx.inspector.dao;

import java.util.List;

import com.indihx.inspector.entity.Dc_Khzb;

public interface QuotaMapper {
	
	/**
	 * 查询指标列表
	 * @param dc_khzb
	 * @return
	 */
	List<Dc_Khzb> qryQuotaList(Dc_Khzb dc_khzb);
	
	/**
	 * 查看检查指标详情
	 * @param check_seq  
	 * @return
	 */
	Dc_Khzb getQuota(String check_seq);
	
	/**
	 * 新增一个考核指标
	 * @param dc_khzb
	 * @return
	 */
	int insertQuota(Dc_Khzb dc_khzb);
	
	/**
	 * 根据主键更新一个考核指标
	 * @param dc_khzb
	 * @return
	 */
	int updateQuota(Dc_Khzb dc_khzb);
	
	/**
	 * 根据主键删除一个考核指标
	 * @param check_seq
	 * @return
	 */
	int deleteQuota(long check_seq);
}
