package com.indihx.PmProjectIncommingInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmProjectIncommingInfo.dao.PmProjectIncommingInfoMapper;
import com.indihx.PmProjectIncommingInfo.entity.PmProjectIncommingInfoEntity;
import com.indihx.PmProjectIncommingInfo.service.PmProjectIncommingInfoService;


@Service("pmProjectIncommingInfoService")
public class PmProjectIncommingInfoServiceImpl implements PmProjectIncommingInfoService {
	@Resource
   	PmProjectIncommingInfoMapper pmProjectIncommingInfoMapper;
   	
   
   	public PmProjectIncommingInfoEntity queryObject(long id){
   		return pmProjectIncommingInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmProjectIncommingInfoEntity entity){
   		pmProjectIncommingInfoMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmProjectIncommingInfoEntity entity){
   		pmProjectIncommingInfoMapper.update(entity);
   	}

	public void delete(long receiveId){
   		pmProjectIncommingInfoMapper.delete( receiveId);
   	}

	public int queryTotal(){
   		return pmProjectIncommingInfoMapper.queryTotal();
   	}

   	public List<PmProjectIncommingInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmProjectIncommingInfoMapper.queryList(entity);
   	}
}
