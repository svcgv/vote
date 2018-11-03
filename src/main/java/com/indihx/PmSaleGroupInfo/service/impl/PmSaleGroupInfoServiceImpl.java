package com.indihx.PmSaleGroupInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import com.indihx.PmSaleGroupInfo.dao.PmSaleGroupInfoMapper;
import com.indihx.PmSaleGroupInfo.entity.PmSaleGroupInfoEntity;
import com.indihx.PmSaleGroupInfo.service.PmSaleGroupInfoService;


@Service("pmSaleGroupInfoService")
public class PmSaleGroupInfoServiceImpl implements PmSaleGroupInfoService {
	@Resource
   	PmSaleGroupInfoMapper pmSaleGroupInfoMapper;
   	
   
   	public PmSaleGroupInfoEntity queryObject(String id){
   		return pmSaleGroupInfoMapper.queryObject(id);
   	}

	public void insert(PmSaleGroupInfoEntity entity){
   		pmSaleGroupInfoMapper.insert(entity);
   	}

	public void update(PmSaleGroupInfoEntity entity){
   		pmSaleGroupInfoMapper.update(entity);
   	}

	public void delete(String groupCode){
   		pmSaleGroupInfoMapper.delete( groupCode);
   	}

	public int queryTotal(){
   		return pmSaleGroupInfoMapper.queryTotal();
   	}

   	public List<PmSaleGroupInfoEntity> queryList(Map<String, Object> entity){
   		return pmSaleGroupInfoMapper.queryList(entity);
   	}
}
