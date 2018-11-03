package com.indihx.PmConfirmBid.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmConfirmBid.entity.PmConfirmBidEntity;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-03 17:12:08
 */
public interface PmConfirmBidMapper{
	public PmConfirmBidEntity queryObject(long id);
	public void insert(PmConfirmBidEntity entity);
	public void update(PmConfirmBidEntity entity);
	public void delete(long bidId);
	public List<PmConfirmBidEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
