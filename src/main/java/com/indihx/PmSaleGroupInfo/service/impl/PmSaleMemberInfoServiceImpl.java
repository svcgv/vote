package com.indihx.PmSaleGroupInfo.service.impl;

import org.springframework.stereotype.Service;

import com.indihx.PmSaleGroupInfo.dao.PmSaleMemberInfoMapper;
import com.indihx.PmSaleGroupInfo.entity.PmSaleMemberInfoEntity;
import com.indihx.PmSaleGroupInfo.service.PmSaleMemberInfoService;

import java.util.Map;
import java.util.List;
import javax.annotation.Resource;


@Service("pmSaleMemberInfoService")
public class PmSaleMemberInfoServiceImpl implements PmSaleMemberInfoService {
	@Resource
   	PmSaleMemberInfoMapper pmSaleMemberInfoMapper;
   	
   
   	public PmSaleMemberInfoEntity queryObject(Long id){
   		return pmSaleMemberInfoMapper.queryObject(id);
   	}

	public void insert(PmSaleMemberInfoEntity entity){
   		pmSaleMemberInfoMapper.insert(entity);
   	}

	public void update(PmSaleMemberInfoEntity entity){
   		pmSaleMemberInfoMapper.update(entity);
   	}

	public void delete(Long menberUsrId){
   		pmSaleMemberInfoMapper.delete( menberUsrId);
   	}

	public int queryTotal(){
   		return pmSaleMemberInfoMapper.queryTotal();
   	}

   	public List<PmSaleMemberInfoEntity> queryList(Map<String, Object> entity){
   		return pmSaleMemberInfoMapper.queryList(entity);
   	}
}
