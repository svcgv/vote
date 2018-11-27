package com.indihx.PmProjectFactMilestoneInfo.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmProjectFactMilestoneInfo.entity.PmProjectFactMilestoneInfoEntity;
/**
 * ${comments}
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-26 16:50:57
 */
public interface PmProjectFactMilestoneInfoService {
	public PmProjectFactMilestoneInfoEntity queryObject(long id);
	public void insert(PmProjectFactMilestoneInfoEntity entity);
	public void update(PmProjectFactMilestoneInfoEntity entity);
	public void delete(long milestoneId);
	public List<PmProjectFactMilestoneInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

