package com.indihx.PmCustomerInfo.dao;

import java.util.Map;

import com.indihx.PmCustomerInfo.entity.PmCustomerGroupEntity;

import java.util.List;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-31 19:17:35
 */
public interface PmCustomerGroupMapper{
	public PmCustomerGroupEntity queryObject(String id);
	public void insert(PmCustomerGroupEntity entity);
	public void update(PmCustomerGroupEntity entity);
	public void delete(String custGroupId);
	public List<PmCustomerGroupEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
