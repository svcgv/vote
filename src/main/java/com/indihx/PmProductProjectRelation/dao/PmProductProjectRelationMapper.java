package com.indihx.PmProductProjectRelation.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmProductProjectRelation.entity.PmProductProjectRelationEntity;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-03 17:12:14
 */
public interface PmProductProjectRelationMapper{
	public PmProductProjectRelationEntity queryObject(long id);
	public void insert(PmProductProjectRelationEntity entity);
	public void update(PmProductProjectRelationEntity entity);
	public void delete(long productRelationId);
	public List<PmProductProjectRelationEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
