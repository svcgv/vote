package com.indihx.PmProjectProblem.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmProjectProblem.dao.PmProjectProblemMapper;
import com.indihx.PmProjectProblem.entity.PmProjectProblemEntity;
import com.indihx.PmProjectProblem.service.PmProjectProblemService;


@Service("pmProjectProblemService")
public class PmProjectProblemServiceImpl implements PmProjectProblemService {
	@Resource
   	PmProjectProblemMapper pmProjectProblemMapper;
   	
   
   	public PmProjectProblemEntity queryObject(long id){
   		return pmProjectProblemMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmProjectProblemEntity entity){
   		pmProjectProblemMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmProjectProblemEntity entity){
   		pmProjectProblemMapper.update(entity);
   	}

	public void delete(long problemId){
   		pmProjectProblemMapper.delete( problemId);
   	}

	public int queryTotal(){
   		return pmProjectProblemMapper.queryTotal();
   	}

   	public List<PmProjectProblemEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmProjectProblemMapper.queryList(entity);
   	}
}
