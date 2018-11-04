package com.indihx.baseTableUtil.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.indihx.baseTableUtil.entity.QueryOrgInfoEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-04 18:32:49
 */
public interface QueryOrgInfoService {
	public QueryOrgInfoEntity queryObject(BigDecimal id);
	
	public List<QueryOrgInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
	public List<Map<String,Object>> queryChildrenOrg(BigDecimal orgNo);

	public Map<String, Object> getFullChildrenTree(BigDecimal i);
}

