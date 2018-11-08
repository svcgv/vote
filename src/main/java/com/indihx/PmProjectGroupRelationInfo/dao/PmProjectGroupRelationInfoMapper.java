package com.indihx.PmProjectGroupRelationInfo.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmProjectGroupRelationInfo.entity.PmProjectGroupRelationInfoEntity;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-07 20:18:23
 */
public interface PmProjectGroupRelationInfoMapper{
	public PmProjectGroupRelationInfoEntity queryObject(long id);
	public void insert(PmProjectGroupRelationInfoEntity entity);
	public void update(PmProjectGroupRelationInfoEntity entity);
	public void delete(long relationship);
	public List<PmProjectGroupRelationInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
