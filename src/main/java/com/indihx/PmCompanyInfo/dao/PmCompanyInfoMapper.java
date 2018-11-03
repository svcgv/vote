package com.indihx.PmCompanyInfo.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmCompanyInfo.entity.PmCompanyInfoEntity;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 18:46:08
 */
public interface PmCompanyInfoMapper{
	public PmCompanyInfoEntity queryObject(String id);
	public void insert(PmCompanyInfoEntity entity);
	public void update(PmCompanyInfoEntity entity);
	public void delete(String companyCode);
	public List<PmCompanyInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
