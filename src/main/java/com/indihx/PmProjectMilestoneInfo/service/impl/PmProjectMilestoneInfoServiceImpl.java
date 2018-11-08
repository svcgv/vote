package com.indihx.PmProjectMilestoneInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmProjectMilestoneInfo.dao.PmProjectMilestoneInfoMapper;
import com.indihx.PmProjectMilestoneInfo.entity.PmProjectMilestoneInfoEntity;
import com.indihx.PmProjectMilestoneInfo.service.PmProjectMilestoneInfoService;


@Service("pmProjectMilestoneInfoService")
public class PmProjectMilestoneInfoServiceImpl implements PmProjectMilestoneInfoService {
	@Resource
   	PmProjectMilestoneInfoMapper pmProjectMilestoneInfoMapper;
   	
   
   	public PmProjectMilestoneInfoEntity queryObject(long id){
   		return pmProjectMilestoneInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmProjectMilestoneInfoEntity entity){
   		pmProjectMilestoneInfoMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmProjectMilestoneInfoEntity entity){
   		pmProjectMilestoneInfoMapper.update(entity);
   	}

	public void delete(long milestoneId){
   		pmProjectMilestoneInfoMapper.delete( milestoneId);
   	}

	public int queryTotal(){
   		return pmProjectMilestoneInfoMapper.queryTotal();
   	}

   	public List<PmProjectMilestoneInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmProjectMilestoneInfoMapper.queryList(entity);
   	}
}
