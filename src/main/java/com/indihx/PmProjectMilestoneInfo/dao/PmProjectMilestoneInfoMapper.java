package com.indihx.PmProjectMilestoneInfo.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmProjectMilestoneInfo.entity.PmProjectMilestoneInfoEntity;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-07 20:18:23
 */
public interface PmProjectMilestoneInfoMapper{
	public PmProjectMilestoneInfoEntity queryObject(long id);
	public void insert(PmProjectMilestoneInfoEntity entity);
	public void update(PmProjectMilestoneInfoEntity entity);
	public void delete(long milestoneId);
	public List<PmProjectMilestoneInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
