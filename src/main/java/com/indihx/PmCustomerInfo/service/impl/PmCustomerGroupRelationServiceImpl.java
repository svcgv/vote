package com.indihx.PmCustomerInfo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.indihx.PmCustomerInfo.dao.PmCustomerGroupRelationMapper;
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupRelationEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerGroupRelationService;

import java.util.Map;
import java.util.List;
import javax.annotation.Resource;


@Service("pmCustomerGroupRelationService")
public class PmCustomerGroupRelationServiceImpl implements PmCustomerGroupRelationService {
	@Resource
   	PmCustomerGroupRelationMapper pmCustomerGroupRelationMapper;
   	
   
   	public PmCustomerGroupRelationEntity queryObject(long id){
   		return pmCustomerGroupRelationMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmCustomerGroupRelationEntity entity){
   		pmCustomerGroupRelationMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmCustomerGroupRelationEntity entity){
   		pmCustomerGroupRelationMapper.update(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long custGroupRelationId){
   		pmCustomerGroupRelationMapper.delete( custGroupRelationId);
   	}

	public int queryTotal(){
   		return pmCustomerGroupRelationMapper.queryTotal();
   	}

   	public List<PmCustomerGroupRelationEntity> queryList(Map<String, Object> entity){
   		return pmCustomerGroupRelationMapper.queryList(entity);
   	}
   	
   	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteByGroupId(String groupId){
   		pmCustomerGroupRelationMapper.deleteByGroupId( groupId);
   	}
}
