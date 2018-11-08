package com.indihx.PmProjectGroupRelationInfo.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmProjectGroupRelationInfo.entity.PmProjectGroupRelationInfoEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-07 20:18:23
 */
public interface PmProjectGroupRelationInfoService {
	public PmProjectGroupRelationInfoEntity queryObject(long id);
	public void insert(PmProjectGroupRelationInfoEntity entity);
	public void update(PmProjectGroupRelationInfoEntity entity);
	public void delete(long relationship);
	public List<PmProjectGroupRelationInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

