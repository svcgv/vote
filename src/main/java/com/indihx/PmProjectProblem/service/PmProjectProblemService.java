package com.indihx.PmProjectProblem.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmProjectProblem.entity.PmProjectProblemEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-07 20:18:23
 */
public interface PmProjectProblemService {
	public PmProjectProblemEntity queryObject(long id);
	public void insert(PmProjectProblemEntity entity);
	public void update(PmProjectProblemEntity entity);
	public void delete(long problemId);
	public List<PmProjectProblemEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

