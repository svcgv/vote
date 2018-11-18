package com.indihx.PmPaymentPoint.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmPaymentPoint.entity.PmPaymentPointEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-06 19:33:25
 */
public interface PmPaymentPointService {
	public PmPaymentPointEntity queryObject(long id);
	public void insert(PmPaymentPointEntity entity);
	public void update(PmPaymentPointEntity entity);
	public void delete(long paymentId);
	public void deleteByForeignId(long paymentForeignId);
	public List<PmPaymentPointEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

