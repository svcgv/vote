package com.indihx.PmProductInfo.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmProductInfo.entity.PmProductInfoEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-03 17:12:19
 */
public interface PmProductInfoService {
	public PmProductInfoEntity queryObject(long id);
	public void insert(PmProductInfoEntity entity);
	public void update(PmProductInfoEntity entity);
	public void delete(long productId);
	public List<PmProductInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

