package com.indihx.PmProjectIncommingInfo.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmProjectIncommingInfo.entity.PmProjectIncommingInfoEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-07 20:18:23
 */
public interface PmProjectIncommingInfoService {
	public PmProjectIncommingInfoEntity queryObject(long id);
	public void insert(PmProjectIncommingInfoEntity entity);
	public void update(PmProjectIncommingInfoEntity entity);
	public void delete(long receiveId);
	public List<PmProjectIncommingInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

