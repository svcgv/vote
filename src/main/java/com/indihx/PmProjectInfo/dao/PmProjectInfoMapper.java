package com.indihx.PmProjectInfo.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-09 10:04:00
 */
public interface PmProjectInfoMapper{
	public PmProjectInfoEntity queryObject(long id);
	public void insert(PmProjectInfoEntity entity);
	public void update(PmProjectInfoEntity entity);
	public void delete(long projectId);
	public List<PmProjectInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
