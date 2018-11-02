package com.indihx.PmCustomerInfo.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indihx.PmCustomerInfo.dao.PmCustomerGroupMapper;
import com.indihx.PmCustomerInfo.dao.PmCustomerGroupRelationMapper;
import com.indihx.PmCustomerInfo.dao.PmCustomerInfoMapper;
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupEntity;
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupRelationEntity;
import com.indihx.PmCustomerInfo.entity.PmCustomerInfoEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerGroupService;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.RandomUtil;

import java.util.Map;
import java.util.List;
import javax.annotation.Resource;


@Service("pmCustomerGroupService")
public class PmCustomerGroupServiceImpl implements PmCustomerGroupService {
	@Resource
   	PmCustomerGroupMapper pmCustomerGroupMapper;
   	
	@Resource
   	PmCustomerGroupRelationMapper pmCustomerGroupRelationMapper;
   
	@Resource
   	PmCustomerInfoMapper pmCustomerInfoMapper;
	
   	public PmCustomerGroupEntity queryObject(String id){
   		return pmCustomerGroupMapper.queryObject(id);
   	}

   	@Transactional
	public void insert(PmCustomerGroupEntity entity){
		String custGroupId = DateUtil.getSysDate()+RandomUtil.generateString(4);
		if(entity.getCustGroupId()==null) {
//			String custGroupId = DateUtil.getSysDate()+RandomUtil.generateString(4);
			entity.setCustGroupId(custGroupId);
		}
   		pmCustomerGroupMapper.insert(entity);
   		for(String sapCode:entity.getSapCode()) {
   			PmCustomerGroupRelationEntity RelationEntity = new PmCustomerGroupRelationEntity();
   			PmCustomerInfoEntity customEntity = pmCustomerInfoMapper.queryBySapCode(sapCode);
   			RelationEntity.setSapCode(sapCode);
   			RelationEntity.setCustGroupId(custGroupId);
   			RelationEntity.setCustCnName(customEntity.getCustCnName());
   			RelationEntity.setCustId(customEntity.getCustId());
//   			RelationEntity.set
   			pmCustomerGroupRelationMapper.insert(RelationEntity);
   		}
   		
   	}

	public void update(PmCustomerGroupEntity entity){
   		pmCustomerGroupMapper.update(entity);
   	}

	public void delete(String custGroupId){
   		pmCustomerGroupMapper.delete( custGroupId);
   	}

	public int queryTotal(){
   		return pmCustomerGroupMapper.queryTotal();
   	}

   	public List<PmCustomerGroupEntity> queryList(Map<String, Object> entity){
   		return pmCustomerGroupMapper.queryList(entity);
   	}
}
