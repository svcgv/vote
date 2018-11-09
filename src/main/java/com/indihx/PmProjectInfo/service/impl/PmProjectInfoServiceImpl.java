package com.indihx.PmProjectInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmProjectInfo.dao.PmProjectInfoMapper;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.PmProjectInfoService;


@Service("pmProjectInfoService")
public class PmProjectInfoServiceImpl implements PmProjectInfoService {
	@Resource
   	PmProjectInfoMapper pmProjectInfoMapper;
   	
   
   	public PmProjectInfoEntity queryObject(long id){
   		return pmProjectInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmProjectInfoEntity entity){
   		pmProjectInfoMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmProjectInfoEntity entity){
   		pmProjectInfoMapper.update(entity);
   	}

	public void delete(long projectId){
   		pmProjectInfoMapper.delete( projectId);
   	}

	public int queryTotal(){
   		return pmProjectInfoMapper.queryTotal();
   	}

   	public List<PmProjectInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmProjectInfoMapper.queryList(entity);
   	}
}
