package com.indihx.PmContractInfo.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmContractInfo.entity.PmContractInfoEntity;
/**
 * ${comments}
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-17 11:27:29
 */
public interface PmContractInfoService {
	public PmContractInfoEntity queryObject(long id);
	public void insert(PmContractInfoEntity entity);
	public void update(PmContractInfoEntity entity);
	public void delete(long contractId);
	public List<PmContractInfoEntity> queryList(Map<String, Object> entity);
	public List<PmContractInfoEntity> queryList(Map<String, Object> entity,Integer pageNum, Integer pageSize);
	public int queryTotal();
   
}

