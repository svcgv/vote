package com.indihx.PmCustomerInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import com.indihx.PmCustomerInfo.dao.PmCustomerInfoMapper;
import com.indihx.PmCustomerInfo.entity.PmCustomerInfoEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerInfoService;


@Service("pmCustomerInfoService")
public class PmCustomerInfoServiceImpl implements PmCustomerInfoService {
	@Resource
   	PmCustomerInfoMapper pmCustomerInfoMapper;
   	
   
   	public PmCustomerInfoEntity queryObject(long id){
   		return pmCustomerInfoMapper.queryObject(id);
   	}

	public void insert(PmCustomerInfoEntity entity){
   		pmCustomerInfoMapper.insert(entity);
   	}

	public void update(PmCustomerInfoEntity entity){
   		pmCustomerInfoMapper.update(entity);
   	}

	public void delete(long custId){
   		pmCustomerInfoMapper.delete( custId);
   	}

	public int queryTotal(){
   		return pmCustomerInfoMapper.queryTotal();
   	}

   	public List<PmCustomerInfoEntity> queryList(Map<String, Object> entity){
   		return pmCustomerInfoMapper.queryList(entity);
   	}
}
