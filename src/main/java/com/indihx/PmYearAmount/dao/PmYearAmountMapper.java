package com.indihx.PmYearAmount.dao;

import java.util.Map;
import java.util.List;
import com.indihx.PmYearAmount.entity.PmYearAmountEntity;

/**
 * ${comments}
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-26 17:00:52
 */
public interface PmYearAmountMapper{
	public PmYearAmountEntity queryObject(long id);
	public void insert(PmYearAmountEntity entity);
	public void update(PmYearAmountEntity entity);
	public void delete(long yearAmountId);
	public List<PmYearAmountEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
}
