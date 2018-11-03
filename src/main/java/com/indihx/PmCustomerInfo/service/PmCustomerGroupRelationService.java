package com.indihx.PmCustomerInfo.service;

import java.util.List;
import java.util.Map;

import com.indihx.PmCustomerInfo.entity.PmCustomerGroupRelationEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 18:46:08
 */
public interface PmCustomerGroupRelationService {
	public PmCustomerGroupRelationEntity queryObject(long id);
	public void insert(PmCustomerGroupRelationEntity entity);
	public void update(PmCustomerGroupRelationEntity entity);
	public void delete(long custGroupRelationId);
	public List<PmCustomerGroupRelationEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

