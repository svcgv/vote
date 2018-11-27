package com.indihx.PmYearAmount.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmYearAmount.dao.PmYearAmountMapper;
import com.indihx.PmYearAmount.entity.PmYearAmountEntity;
import com.indihx.PmYearAmount.service.PmYearAmountService;


@Service("pmYearAmountService")
public class PmYearAmountServiceImpl implements PmYearAmountService {
	@Resource
   	PmYearAmountMapper pmYearAmountMapper;
   	
   
   	public PmYearAmountEntity queryObject(long id){
   		return pmYearAmountMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmYearAmountEntity entity){
   		pmYearAmountMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmYearAmountEntity entity){
   		pmYearAmountMapper.update(entity);
   	}

	public void delete(long yearAmountId){
   		pmYearAmountMapper.delete( yearAmountId);
   	}

	public int queryTotal(){
   		return pmYearAmountMapper.queryTotal();
   	}

   	public List<PmYearAmountEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmYearAmountMapper.queryList(entity);
   	}
}
