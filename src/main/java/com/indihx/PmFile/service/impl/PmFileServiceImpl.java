package com.indihx.PmFile.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmFile.dao.PmFileMapper;
import com.indihx.PmFile.entity.PmFileEntity;
import com.indihx.PmFile.service.PmFileService;


@Service("pmFileService")
public class PmFileServiceImpl implements PmFileService {
	@Resource
   	PmFileMapper pmFileMapper;
   	
   
   	public PmFileEntity queryObject(long id){
   		return pmFileMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmFileEntity entity){
   		pmFileMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmFileEntity entity){
   		pmFileMapper.update(entity);
   	}

	public void delete(long fileId){
   		pmFileMapper.delete( fileId);
   	}

	public int queryTotal(){
   		return pmFileMapper.queryTotal();
   	}

   	public List<PmFileEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmFileMapper.queryList(entity);
   	}
}
