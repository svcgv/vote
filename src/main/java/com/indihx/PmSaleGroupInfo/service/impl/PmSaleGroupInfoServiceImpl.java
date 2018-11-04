package com.indihx.PmSaleGroupInfo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import com.indihx.PmSaleGroupInfo.dao.PmSaleGroupInfoMapper;
import com.indihx.PmSaleGroupInfo.entity.PmSaleGroupInfoEntity;
import com.indihx.PmSaleGroupInfo.service.PmSaleGroupInfoService;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.RandomUtil;


@Service("pmSaleGroupInfoService")
public class PmSaleGroupInfoServiceImpl implements PmSaleGroupInfoService {
	@Resource
   	PmSaleGroupInfoMapper pmSaleGroupInfoMapper;
   	
   
   	public PmSaleGroupInfoEntity queryObject(long id){
   		return pmSaleGroupInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmSaleGroupInfoEntity entity){
		
   		pmSaleGroupInfoMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmSaleGroupInfoEntity entity){
   		pmSaleGroupInfoMapper.update(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long groupId){
   		pmSaleGroupInfoMapper.delete( groupId);
   	}

	public int queryTotal(){
   		return pmSaleGroupInfoMapper.queryTotal();
   	}

   	public List<PmSaleGroupInfoEntity> queryList(Map<String, Object> entity){
   		return pmSaleGroupInfoMapper.queryList(entity);
   	}
}
