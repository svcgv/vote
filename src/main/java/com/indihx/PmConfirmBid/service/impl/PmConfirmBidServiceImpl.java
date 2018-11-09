package com.indihx.PmConfirmBid.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmConfirmBid.dao.PmConfirmBidMapper;
import com.indihx.PmConfirmBid.entity.PmConfirmBidEntity;
import com.indihx.PmConfirmBid.service.PmConfirmBidService;


@Service("pmConfirmBidService")
public class PmConfirmBidServiceImpl implements PmConfirmBidService {
	@Resource
   	PmConfirmBidMapper pmConfirmBidMapper;
   	
   
   	public PmConfirmBidEntity queryObject(long id){
   		return pmConfirmBidMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public long insert(PmConfirmBidEntity entity){
   		return pmConfirmBidMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmConfirmBidEntity entity){
   		pmConfirmBidMapper.update(entity);
   	}

	public void delete(long bidId){
   		pmConfirmBidMapper.delete( bidId);
   	}

	public int queryTotal(){
   		return pmConfirmBidMapper.queryTotal();
   	}

   	public List<PmConfirmBidEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmConfirmBidMapper.queryList(entity);
   	}
}
