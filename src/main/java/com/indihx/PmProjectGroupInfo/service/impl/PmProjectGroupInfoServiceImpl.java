package com.indihx.PmProjectGroupInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmProjectGroupInfo.dao.PmProjectGroupInfoMapper;
import com.indihx.PmProjectGroupInfo.entity.PmProjectGroupInfoEntity;
import com.indihx.PmProjectGroupInfo.service.PmProjectGroupInfoService;


@Service("pmProjectGroupInfoService")
public class PmProjectGroupInfoServiceImpl implements PmProjectGroupInfoService {
	@Resource
   	PmProjectGroupInfoMapper pmProjectGroupInfoMapper;
   	
   
   	public PmProjectGroupInfoEntity queryObject(long id){
   		return pmProjectGroupInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmProjectGroupInfoEntity entity){
   		pmProjectGroupInfoMapper.insert(entity);
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
