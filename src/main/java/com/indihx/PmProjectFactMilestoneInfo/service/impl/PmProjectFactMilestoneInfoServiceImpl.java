package com.indihx.PmProjectFactMilestoneInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmProjectFactMilestoneInfo.dao.PmProjectFactMilestoneInfoMapper;
import com.indihx.PmProjectFactMilestoneInfo.entity.PmProjectFactMilestoneInfoEntity;
import com.indihx.PmProjectFactMilestoneInfo.service.PmProjectFactMilestoneInfoService;


@Service("pmProjectFactMilestoneInfoService")
public class PmProjectFactMilestoneInfoServiceImpl implements PmProjectFactMilestoneInfoService {
	@Resource
   	PmProjectFactMilestoneInfoMapper pmProjectFactMilestoneInfoMapper;
   	
   
   	public PmProjectFactMilestoneInfoEntity queryObject(long id){
   		return pmProjectFactMilestoneInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmProjectFactMilestoneInfoEntity entity){
   		pmProjectFactMilestoneInfoMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmProjectFactMilestoneInfoEntity entity){
   		pmProjectFactMilestoneInfoMapper.update(entity);
   	}

	public void delete(long milestoneId){
   		pmProjectFactMilestoneInfoMapper.delete( milestoneId);
   	}

	public int queryTotal(){
   		return pmProjectFactMilestoneInfoMapper.queryTotal();
   	}

   	public List<PmProjectFactMilestoneInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmProjectFactMilestoneInfoMapper.queryList(entity);
   	}
}
