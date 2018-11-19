package com.indihx.PmProjectMilestoneInfo.service;

import java.util.List;
import java.util.Map;

import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectMilestoneInfo.entity.PmProjectMilestoneInfoEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-07 20:18:23
 */
public interface PmProjectMilestoneInfoService {
	public PmProjectMilestoneInfoEntity queryObject(long id);
	public void insert(PmProjectMilestoneInfoEntity entity);
	public void update(PmProjectMilestoneInfoEntity entity);
	public void delete(long milestoneId);
	public List<PmProjectMilestoneInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
	public void insertList(PmProjectInfoEntity pmProjectInfo, long projectId);
	public void queryListInfo(PmProjectInfoEntity pmProjectInfo, long projectId);
   
}

