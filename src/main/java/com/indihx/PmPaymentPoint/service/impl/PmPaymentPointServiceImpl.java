package com.indihx.PmPaymentPoint.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmPaymentPoint.dao.PmPaymentPointMapper;
import com.indihx.PmPaymentPoint.entity.PmPaymentPointEntity;
import com.indihx.PmPaymentPoint.service.PmPaymentPointService;


@Service("pmPaymentPointService")
public class PmPaymentPointServiceImpl implements PmPaymentPointService {
	@Resource
   	PmPaymentPointMapper pmPaymentPointMapper;
   	
   
   	public PmPaymentPointEntity queryObject(long id){
   		return pmPaymentPointMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmPaymentPointEntity entity){
   		pmPaymentPointMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmPaymentPointEntity entity){
   		pmPaymentPointMapper.update(entity);
   	}

	public void delete(long paymentId){
   		pmPaymentPointMapper.delete( paymentId);
   	}

	public int queryTotal(){
   		return pmPaymentPointMapper.queryTotal();
   	}

   	public List<PmPaymentPointEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmPaymentPointMapper.queryList(entity);
   	}
}
