package com.indihx.PmSaleGroupInfo.dao;

import java.util.Map;

import com.indihx.PmSaleGroupInfo.entity.PmSaleMemberInfoEntity;

import java.util.List;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 19:04:20
 */
public interface PmSaleMemberInfoMapper{
	public PmSaleMemberInfoEntity queryObject(Long id);
	public void insert(PmSaleMemberInfoEntity entity);
	public void update(PmSaleMemberInfoEntity entity);
	public void delete(String groupCode);
	public List<PmSaleMemberInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
