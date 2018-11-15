package com.indihx.PmCustomerInfo.dao;

import java.util.Map;

import com.indihx.PmCustomerInfo.entity.PmCustomerGroupRelationEntity;

import java.util.List;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 18:46:08
 */
public interface PmCustomerGroupRelationMapper{
	public PmCustomerGroupRelationEntity queryObject(long id);
	public void insert(PmCustomerGroupRelationEntity entity);
	public void update(PmCustomerGroupRelationEntity entity);
	public void updateByCustId(PmCustomerGroupRelationEntity entity);
	public void delete(long custGroupRelationId);
	public List<PmCustomerGroupRelationEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
	
	public void deleteByGroupId(String id);
}
