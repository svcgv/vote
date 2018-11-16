package com.indihx.PmProjectGroupInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.indihx.PmCustomerInfo.entity.PmCustomerGroupRelationEntity;
import com.indihx.PmCustomerInfo.entity.PmCustomerInfoEntity;
import com.indihx.PmProjectGroupInfo.dao.PmProjectGroupInfoMapper;
import com.indihx.PmProjectGroupInfo.entity.PmProjectGroupInfoEntity;
import com.indihx.PmProjectGroupInfo.service.PmProjectGroupInfoService;
import com.indihx.PmProjectGroupRelationInfo.dao.PmProjectGroupRelationInfoMapper;
import com.indihx.PmProjectGroupRelationInfo.entity.PmProjectGroupRelationInfoEntity;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.RandomUtil;


@Service("pmProjectGroupInfoService")
public class PmProjectGroupInfoServiceImpl implements PmProjectGroupInfoService {
	@Resource
   	PmProjectGroupInfoMapper pmProjectGroupInfoMapper;
	@Resource
	PmProjectGroupRelationInfoMapper pmProjectGroupRelationInfoMapper;
   	
   
   	public PmProjectGroupInfoEntity queryObject(long id){
   		return pmProjectGroupInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmProjectGroupInfoEntity entity){
		
		entity.setIsDelete("00");
		pmProjectGroupInfoMapper.insert(entity);
   		for(String projectId:entity.getProjectIds()) {
   			PmProjectGroupRelationInfoEntity pmProjectGroupRelationInfoEntity = new PmProjectGroupRelationInfoEntity();
   			pmProjectGroupRelationInfoEntity.setProjectGroupId(pmProjectGroupInfoMapper.queryMaxId());
   			pmProjectGroupRelationInfoEntity.setProjectId(Long.valueOf(projectId));
   			pmProjectGroupRelationInfoEntity.setCreatorId(entity.getCreatorId());
   			pmProjectGroupRelationInfoEntity.setCreateTime(DateUtil.getDateTime());
   			pmProjectGroupRelationInfoEntity.setIsDelete("00");
   			pmProjectGroupRelationInfoMapper.insert(pmProjectGroupRelationInfoEntity);
   		}
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmProjectGroupInfoEntity entity){
   		pmProjectGroupInfoMapper.update(entity);
   	}

	public void delete(long projectGroupId){
   		pmProjectGroupInfoMapper.delete( projectGroupId);
   	}

	public int queryTotal(){
   		return pmProjectGroupInfoMapper.queryTotal();
   	}

   	public List<PmProjectGroupInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmProjectGroupInfoMapper.queryList(entity);
   	}
}
