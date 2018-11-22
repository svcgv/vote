package com.indihx.PmContractProjectRelation.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmContractProjectRelation.dao.PmContractProjectRelationMapper;
import com.indihx.PmContractProjectRelation.entity.PmContractProjectRelationEntity;
import com.indihx.PmContractProjectRelation.service.PmContractProjectRelationService;


@Service("pmContractProjectRelationService")
public class PmContractProjectRelationServiceImpl implements PmContractProjectRelationService {
	@Resource
   	PmContractProjectRelationMapper pmContractProjectRelationMapper;
   	
   
   	public PmContractProjectRelationEntity queryObject(long id){
   		return pmContractProjectRelationMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmContractProjectRelationEntity entity){
   		pmContractProjectRelationMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmContractProjectRelationEntity entity){
   		pmContractProjectRelationMapper.update(entity);
   	}

	public void delete(long contractProjectRelationId){
   		pmContractProjectRelationMapper.delete( contractProjectRelationId);
   	}

	public int queryTotal(){
   		return pmContractProjectRelationMapper.queryTotal();
   	}

   	public List<PmContractProjectRelationEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmContractProjectRelationMapper.queryList(entity);
   	}
	public List<PmContractProjectRelationEntity> queryListProject(Map<String, Object> entity){
		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
			entity.put("isDelete", "00");
		}
		return pmContractProjectRelationMapper.queryListProject(entity);
	}
}
