package com.indihx.PmProductProjectRelation.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmProductProjectRelation.entity.PmProductProjectRelationEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-03 17:12:14
 */
public interface PmProductProjectRelationService {
	public PmProductProjectRelationEntity queryObject(long id);
	public void insert(PmProductProjectRelationEntity entity);
	public void update(PmProductProjectRelationEntity entity);
	public void delete(long productRelationId);
	public void deleteByProductId(long productId);
	public List<PmProductProjectRelationEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

