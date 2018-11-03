package com.indihx.PmPaymentPoint.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmPaymentPoint.entity.PmPaymentPointEntity;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-03 17:11:55
 */
public interface PmPaymentPointMapper{
	public PmPaymentPointEntity queryObject(long id);
	public void insert(PmPaymentPointEntity entity);
	public void update(PmPaymentPointEntity entity);
	public void delete(long paymentId);
	public List<PmPaymentPointEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
