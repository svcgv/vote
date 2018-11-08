package com.indihx.PmProjectGroupRelationInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmProjectGroupRelationInfo.dao.PmProjectGroupRelationInfoMapper;
import com.indihx.PmProjectGroupRelationInfo.entity.PmProjectGroupRelationInfoEntity;
import com.indihx.PmProjectGroupRelationInfo.service.PmProjectGroupRelationInfoService;


@Service("pmProjectGroupRelationInfoService")
public class PmProjectGroupRelationInfoServiceImpl implements PmProjectGroupRelationInfoService {
	@Resource
   	PmProjectGroupRelationInfoMapper pmProjectGroupRelationInfoMapper;
   	
   
   	public PmProjectGroupRelationInfoEntity queryObject(long id){
   		return pmProjectGroupRelationInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmProjectGroupRelationInfoEntity entity){
   		pmProjectGroupRelationInfoMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmProjectGroupRelationInfoEntity entity){
   		pmProjectGroupRelationInfoMapper.update(entity);
   	}

	public void delete(long relationship){
   		pmProjectGroupRelationInfoMapper.delete( relationship);
   	}

	public int queryTotal(){
   		return pmProjectGroupRelationInfoMapper.queryTotal();
   	}

   	public List<PmProjectGroupRelationInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmProjectGroupRelationInfoMapper.queryList(entity);
   	}
}
