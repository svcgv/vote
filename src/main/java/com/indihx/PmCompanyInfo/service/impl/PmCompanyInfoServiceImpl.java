package com.indihx.PmCompanyInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import com.indihx.PmCompanyInfo.dao.PmCompanyInfoMapper;
import com.indihx.PmCompanyInfo.entity.PmCompanyInfoEntity;
import com.indihx.PmCompanyInfo.service.PmCompanyInfoService;


@Service("pmCompanyInfoService")
public class PmCompanyInfoServiceImpl implements PmCompanyInfoService {
	@Resource
   	PmCompanyInfoMapper pmCompanyInfoMapper;
   	
   
   	public PmCompanyInfoEntity queryObject(String id){
   		return pmCompanyInfoMapper.queryObject(id);
   	}

	public void insert(PmCompanyInfoEntity entity){
   		pmCompanyInfoMapper.insert(entity);
   	}

	public void update(PmCompanyInfoEntity entity){
   		pmCompanyInfoMapper.update(entity);
   	}

	public void delete(String companyCode){
   		pmCompanyInfoMapper.delete( companyCode);
   	}

	public int queryTotal(){
   		return pmCompanyInfoMapper.queryTotal();
   	}

   	public List<PmCompanyInfoEntity> queryList(Map<String, Object> entity){
   		return pmCompanyInfoMapper.queryList(entity);
   	}
}
