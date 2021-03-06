package com.indihx.PmProjectGroupInfo.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmProjectGroupInfo.entity.PmProjectGroupInfoEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-07 20:18:23
 */
public interface PmProjectGroupInfoService {
	public PmProjectGroupInfoEntity queryObject(long id);
	public void insert(PmProjectGroupInfoEntity entity);
	public void update(PmProjectGroupInfoEntity entity);
	public void delete(long projectGroupId);
	public List<PmProjectGroupInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

