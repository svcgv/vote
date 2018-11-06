package com.indihx.PmReviewCommentInfo.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmReviewCommentInfo.entity.PmReviewCommentInfoEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-06 19:33:25
 */
public interface PmReviewCommentInfoService {
	public PmReviewCommentInfoEntity queryObject(long id);
	public void insert(PmReviewCommentInfoEntity entity);
	public void update(PmReviewCommentInfoEntity entity);
	public void delete(long commentId);
	public List<PmReviewCommentInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

