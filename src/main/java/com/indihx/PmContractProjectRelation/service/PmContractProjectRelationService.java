package com.indihx.PmContractProjectRelation.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmContractProjectRelation.entity.PmContractProjectRelationEntity;
/**
 * ${comments}
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-17 11:28:34
 */
public interface PmContractProjectRelationService {
	public PmContractProjectRelationEntity queryObject(long id);
	public void insert(PmContractProjectRelationEntity entity);
	public void update(PmContractProjectRelationEntity entity);
	public void delete(long contractProjectRelationId);
	public List<PmContractProjectRelationEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

