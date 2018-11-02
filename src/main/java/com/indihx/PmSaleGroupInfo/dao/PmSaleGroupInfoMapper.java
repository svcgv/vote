package com.indihx.PmSaleGroupInfo.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmSaleGroupInfo.entity.PmSaleGroupInfoEntity;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-02 20:19:45
 */
public interface PmSaleGroupInfoMapper{
	public PmSaleGroupInfoEntity queryObject(long id);
	public void insert(PmSaleGroupInfoEntity entity);
	public void update(PmSaleGroupInfoEntity entity);
	public void delete(long groupId);
	public List<PmSaleGroupInfoEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
