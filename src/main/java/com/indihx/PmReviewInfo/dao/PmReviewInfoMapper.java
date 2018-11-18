package com.indihx.PmReviewInfo.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmReviewInfo.entity.PmReviewInfoEntity;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-08 21:43:00
 */
public interface PmReviewInfoMapper{
	public PmReviewInfoEntity queryObject(long id);
	public void insert(PmReviewInfoEntity entity);
	public void update(PmReviewInfoEntity entity);
	public void delete(long reviewId);
	public List<PmReviewInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
	public void updateIsDelete(PmReviewInfoEntity entity);
	public List<Map<String,Object>> selectBidReview(Map<String,Object> entity);
}
