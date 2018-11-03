package com.indihx.PmProductInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmProductInfo.dao.PmProductInfoMapper;
import com.indihx.PmProductInfo.entity.PmProductInfoEntity;
import com.indihx.PmProductInfo.service.PmProductInfoService;


@Service("pmProductInfoService")
public class PmProductInfoServiceImpl implements PmProductInfoService {
	@Resource
   	PmProductInfoMapper pmProductInfoMapper;
   	
   
   	public PmProductInfoEntity queryObject(long id){
   		return pmProductInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmProductInfoEntity entity){
   		pmProductInfoMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmProductInfoEntity entity){
   		pmProductInfoMapper.update(entity);
   	}

	public void delete(long productId){
   		pmProductInfoMapper.delete( productId);
   	}

	public int queryTotal(){
   		return pmProductInfoMapper.queryTotal();
   	}

   	public List<PmProductInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmProductInfoMapper.queryList(entity);
   	}
}
