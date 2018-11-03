package com.indihx.PmProductProjectRelation.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmProductProjectRelation.dao.PmProductProjectRelationMapper;
import com.indihx.PmProductProjectRelation.entity.PmProductProjectRelationEntity;
import com.indihx.PmProductProjectRelation.service.PmProductProjectRelationService;


@Service("pmProductProjectRelationService")
public class PmProductProjectRelationServiceImpl implements PmProductProjectRelationService {
	@Resource
   	PmProductProjectRelationMapper pmProductProjectRelationMapper;
   	
   
   	public PmProductProjectRelationEntity queryObject(long id){
   		return pmProductProjectRelationMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmProductProjectRelationEntity entity){
   		pmProductProjectRelationMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmProductProjectRelationEntity entity){
   		pmProductProjectRelationMapper.update(entity);
   	}

	public void delete(long productRelationId){
   		pmProductProjectRelationMapper.delete( productRelationId);
   	}

	public int queryTotal(){
   		return pmProductProjectRelationMapper.queryTotal();
   	}

   	public List<PmProductProjectRelationEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmProductProjectRelationMapper.queryList(entity);
   	}
}
