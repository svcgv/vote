package com.indihx.PmConfirmBid.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmConfirmBid.entity.PmConfirmBidEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-06 19:51:55
 */
public interface PmConfirmBidService {
	public PmConfirmBidEntity queryObject(long id);
	public void insert(PmConfirmBidEntity entity);
	public void update(PmConfirmBidEntity entity);
	public void delete(long bidId);
	public List<PmConfirmBidEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

