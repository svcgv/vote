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
   	/**
   	 * 插入数据前先通过评审类型、外键id或code将其它的评审置为无效
   	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmReviewInfoEntity entity){
		pmReviewInfoMapper.updateIsDelete(entity);
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
	@Override
	public List<Map<String, Object>> selectBidReview(Map<String,Object> entity) {
		return pmReviewInfoMapper.selectBidReview(entity);
	}
	@Override
	public List<Map<String, Object>> selectProjectReview(Map<String, Object> par) {
		return pmReviewInfoMapper.selectProjectReview(par);
	}
}
