package com.indihx.PmCompanyInfo.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmCompanyInfo.entity.PmCompanyInfoEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 18:46:08
 */
public interface PmCompanyInfoService {
	public PmCompanyInfoEntity queryObject(long id);
	public void insert(PmCompanyInfoEntity entity);
	public void update(PmCompanyInfoEntity entity);
	public void delete(long companyId);
	public List<PmCompanyInfoEntity> queryList(Map<String, Object> entity);
	public List<PmCompanyInfoEntity> queryList(Map<String, Object> entity,Integer pageNum, Integer pageSize);
	public int queryTotal();
   
}

