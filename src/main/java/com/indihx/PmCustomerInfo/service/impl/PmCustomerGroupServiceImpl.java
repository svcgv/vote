package com.indihx.PmCustomerInfo.service.impl;

import org.springframework.stereotype.Service;

import com.indihx.PmCustomerInfo.dao.PmCustomerGroupMapper;
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupEntity;
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
   	
   
   	public PmCustomerGroupEntity queryObject(String id){
   		return pmCustomerGroupMapper.queryObject(id);
   	}

	public void insert(PmCustomerGroupEntity entity){
		if(entity.getCustGroupId()==null) {
			entity.setCustGroupId(DateUtil.getSysDate()+RandomUtil.generateString(4));
		}
   		pmCustomerGroupMapper.insert(entity);
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
