package com.indihx.baseTableUtil.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.indihx.baseTableUtil.entity.QueryUsrInfoEntity;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-04 18:32:49
 */
public interface QueryUsrInfoMapper{
	public QueryUsrInfoEntity queryObject(long id);
	
	public List<QueryUsrInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
	public List<QueryUsrInfoEntity> queryBySaleGroupCode(String code);
	/**
	 * 
	 * @param param{orgNo:,roleCode:''}
	 * @return
	 */
	public List<QueryUsrInfoEntity> queryUserByRoleCodeAndOrgNo(Map<String,Object> param);
	
	public List<QueryUsrInfoEntity> queryUserByRoleCodeUnderOrgNo(Map<String,Object> list);
	
}
