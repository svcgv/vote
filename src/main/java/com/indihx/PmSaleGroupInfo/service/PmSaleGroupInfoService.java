package com.indihx.PmSaleGroupInfo.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmSaleGroupInfo.entity.PmSaleGroupInfoEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 18:46:08
 */
public interface PmSaleGroupInfoService {
	public PmSaleGroupInfoEntity queryObject(String id);
	public void insert(PmSaleGroupInfoEntity entity);
	public void update(PmSaleGroupInfoEntity entity);
	public void delete(String groupCode);
	public List<PmSaleGroupInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

