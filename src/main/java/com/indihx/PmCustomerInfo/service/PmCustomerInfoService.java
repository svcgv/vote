package com.indihx.PmCustomerInfo.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmCustomerInfo.entity.PmCustomerInfoEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 18:46:08
 */
public interface PmCustomerInfoService {
	public PmCustomerInfoEntity queryObject(long id);
	public PmCustomerInfoEntity queryBySapCode(String code);
	public void insert(PmCustomerInfoEntity entity);
	public void update(PmCustomerInfoEntity entity);
	public void delete(long custId);
	public List<PmCustomerInfoEntity> queryList(Map<String, Object> entity);
	public List<PmCustomerInfoEntity> queryList(Map<String, Object> entity,Integer pageNum, Integer pageSize);
	public List<PmCustomerInfoEntity> queryListAll(Map<String, Object> entity,Integer pageNum, Integer pageSize);
	public int queryTotal();
   
}

