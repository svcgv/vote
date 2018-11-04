package com.indihx.PmSaleGroupInfo.service;

import java.util.List;
import java.util.Map;

import com.indihx.PmSaleGroupInfo.entity.PmSaleMemberInfoEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 19:04:20
 */
public interface PmSaleMemberInfoService {
	public PmSaleMemberInfoEntity queryObject(Long id);
	public void insert(PmSaleMemberInfoEntity entity);
	public void update(PmSaleMemberInfoEntity entity);
	public void delete(String groupCode);
	public List<PmSaleMemberInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

