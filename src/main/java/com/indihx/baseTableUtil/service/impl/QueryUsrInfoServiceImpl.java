package com.indihx.baseTableUtil.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import com.indihx.baseTableUtil.dao.QueryUsrInfoMapper;
import com.indihx.baseTableUtil.entity.QueryUsrInfoEntity;
import com.indihx.baseTableUtil.service.QueryUsrInfoService;


@Service("QueryUsrInfoServiceImpl")
public class QueryUsrInfoServiceImpl implements QueryUsrInfoService {
	@Resource
   	QueryUsrInfoMapper queryUsrInfoMapper;
   	
   
   	public QueryUsrInfoEntity queryObject(long id){
   		return queryUsrInfoMapper.queryObject(id);
   	}

	public int queryTotal(){
   		return queryUsrInfoMapper.queryTotal();
   	}

   	public List<QueryUsrInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return queryUsrInfoMapper.queryList(entity);
   	}

	@Override
	public List<QueryUsrInfoEntity> queryBySaleGroupCode(String code) {
	
		return queryUsrInfoMapper.queryBySaleGroupCode(code);
	}

	@Override
	public List<QueryUsrInfoEntity> queryUserByRoleCodeAndOrgNo(Map<String, Object> param) {
		
		return queryUsrInfoMapper.queryUserByRoleCodeAndOrgNo(param);
	}

	@Override
	public List<QueryUsrInfoEntity> queryUserByRoleCodeUnderOrgNo(List<BigDecimal> list) {
		
		return queryUsrInfoMapper.queryUserByRoleCodeUnderOrgNo(list);
	}
}
