package com.indihx.PmReviewInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmReviewInfo.dao.PmReviewInfoMapper;
import com.indihx.PmReviewInfo.entity.PmReviewInfoEntity;
import com.indihx.PmReviewInfo.service.PmReviewInfoService;


@Service("pmReviewInfoService")
public class PmReviewInfoServiceImpl implements PmReviewInfoService {
	@Resource
   	PmReviewInfoMapper pmReviewInfoMapper;
   	
   
   	public PmReviewInfoEntity queryObject(long id){
   		return pmReviewInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmReviewInfoEntity entity){
   		pmReviewInfoMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmReviewInfoEntity entity){
   		pmReviewInfoMapper.update(entity);
   	}

	public void delete(long reviewId){
   		pmReviewInfoMapper.delete( reviewId);
   	}

	public int queryTotal(){
   		return pmReviewInfoMapper.queryTotal();
   	}

   	public List<PmReviewInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmReviewInfoMapper.queryList(entity);
   	}
}
