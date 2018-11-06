package com.indihx.PmCustomerInfo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import com.indihx.PmCustomerInfo.dao.PmCustomerGroupRelationMapper;
import com.indihx.PmCustomerInfo.dao.PmCustomerInfoMapper;
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupRelationEntity;
import com.indihx.PmCustomerInfo.entity.PmCustomerInfoEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerInfoService;


@Service("pmCustomerInfoService")
public class PmCustomerInfoServiceImpl implements PmCustomerInfoService {
	@Resource
   	PmCustomerInfoMapper pmCustomerInfoMapper;
	@Resource
   	PmCustomerGroupRelationMapper pmCustomerGroupRelationMapper;
   
   	public PmCustomerInfoEntity queryObject(long id){
   		return pmCustomerInfoMapper.queryObject(id);
   	}
   	public PmCustomerInfoEntity queryBySapCode(String code){
   		return pmCustomerInfoMapper.queryBySapCode(code);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmCustomerInfoEntity entity){
		List<PmCustomerInfoEntity> queryInfo = null;
		boolean flag = false;
		Map<String ,Object> param = new HashMap<String,Object>();
		if(entity.getSapCode()!=null) {//若有sapCode,且数据库中没有这条数据则执行插入
			param.put("sapCode", entity.getSapCode());
			queryInfo = queryList(param);
			
			if(queryInfo.isEmpty()) {
				flag = true;
			}
		}
		else {
			flag = true;
		}
		
		if(flag) {
			pmCustomerInfoMapper.insert(entity);
  			PmCustomerGroupRelationEntity RelationEntity = new PmCustomerGroupRelationEntity();
   			RelationEntity.setSapCode(entity.getSapCode());
   			RelationEntity.setCustGroupId(entity.getCustGroupId());
   			RelationEntity.setCustCnName(entity.getCustCnName());
   			RelationEntity.setCustId(entity.getCustId());
			pmCustomerGroupRelationMapper.insert(RelationEntity);
		}
   		
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmCustomerInfoEntity entity){
   		pmCustomerInfoMapper.update(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long custId){
   		pmCustomerInfoMapper.delete( custId);
   	}

	public int queryTotal(){
   		return pmCustomerInfoMapper.queryTotal();
   	}

   	public List<PmCustomerInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmCustomerInfoMapper.queryList(entity);
   	}
}
