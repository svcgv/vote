package com.indihx.PmFile.service;

import java.util.List;
import java.util.Map;
import com.indihx.PmFile.entity.PmFileEntity;
/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-06 19:33:25
 */
public interface PmFileService {
	public PmFileEntity queryObject(long id);
	public void insert(PmFileEntity entity);
	public void update(PmFileEntity entity);
	public void delete(long fileId);
	public List<PmFileEntity> queryList(Map<String, Object> entity);
	public int queryTotal();
   
}

