package com.indihx.PmConfirmBid.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmConfirmBid.entity.PmConfirmBidEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-07 10:07:13
 */
public interface PmConfirmBidService {
	public PmConfirmBidEntity queryObject(long id);
	public long insert(PmConfirmBidEntity entity);
	public void update(PmConfirmBidEntity entity);
	public void delete(long bidId);
	public List<PmConfirmBidEntity> queryList(Map<String, Object> entity);
	public List<PmConfirmBidEntity> queryList(Map<String, Object> entity,Integer pageNum, Integer pageSize);
	public int queryTotal();
   
}

