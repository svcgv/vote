package com.indihx.PmContractInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmContractInfo.dao.PmContractInfoMapper;
import com.indihx.PmContractInfo.entity.PmContractInfoEntity;
import com.indihx.PmContractInfo.service.PmContractInfoService;


@Service("pmContractInfoService")
public class PmContractInfoServiceImpl implements PmContractInfoService {
	@Resource
   	PmContractInfoMapper pmContractInfoMapper;
   	
   
   	public PmContractInfoEntity queryObject(long id){
   		return pmContractInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmContractInfoEntity entity){
   		pmContractInfoMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmContractInfoEntity entity){
   		pmContractInfoMapper.update(entity);
   	}

	public void delete(long contractId){
   		pmContractInfoMapper.delete( contractId);
   	}

	public int queryTotal(){
   		return pmContractInfoMapper.queryTotal();
   	}

   	public List<PmContractInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmContractInfoMapper.queryList(entity);
   	}
}
