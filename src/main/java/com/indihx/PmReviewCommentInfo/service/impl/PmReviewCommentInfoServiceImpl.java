package com.indihx.PmReviewCommentInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmReviewCommentInfo.dao.PmReviewCommentInfoMapper;
import com.indihx.PmReviewCommentInfo.entity.PmReviewCommentInfoEntity;
import com.indihx.PmReviewCommentInfo.service.PmReviewCommentInfoService;


@Service("pmReviewCommentInfoService")
public class PmReviewCommentInfoServiceImpl implements PmReviewCommentInfoService {
	@Resource
   	PmReviewCommentInfoMapper pmReviewCommentInfoMapper;
   	
   
   	public PmReviewCommentInfoEntity queryObject(long id){
   		return pmReviewCommentInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmReviewCommentInfoEntity entity){
   		pmReviewCommentInfoMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmReviewCommentInfoEntity entity){
   		pmReviewCommentInfoMapper.update(entity);
   	}

	public void delete(long commentId){
   		pmReviewCommentInfoMapper.delete( commentId);
   	}

	public int queryTotal(){
   		return pmReviewCommentInfoMapper.queryTotal();
   	}

   	public List<PmReviewCommentInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmReviewCommentInfoMapper.queryList(entity);
   	}
}
