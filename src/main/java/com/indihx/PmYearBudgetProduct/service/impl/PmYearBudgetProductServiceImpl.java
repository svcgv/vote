package com.indihx.PmYearBudgetProduct.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmYearBudgetProduct.dao.PmYearBudgetProductMapper;
import com.indihx.PmYearBudgetProduct.entity.PmYearBudgetProductEntity;
import com.indihx.PmYearBudgetProduct.service.PmYearBudgetProductService;


@Service("pmYearBudgetProductService")
public class PmYearBudgetProductServiceImpl implements PmYearBudgetProductService {
	@Resource
   	PmYearBudgetProductMapper pmYearBudgetProductMapper;
   	
   
   	public PmYearBudgetProductEntity queryObject(long id){
   		return pmYearBudgetProductMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmYearBudgetProductEntity entity){
   		pmYearBudgetProductMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmYearBudgetProductEntity entity){
   		pmYearBudgetProductMapper.update(entity);
   	}

	public void delete(long paymentId){
   		pmYearBudgetProductMapper.delete( paymentId);
   	}

	public int queryTotal(){
   		return pmYearBudgetProductMapper.queryTotal();
   	}

   	public List<PmYearBudgetProductEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmYearBudgetProductMapper.queryList(entity);
   	}
}
