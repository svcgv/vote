package com.indihx.PmProjectInfo.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.indihx.PmProjectInfo.dao.PmProjectInfoMapper;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.PmProjectInfoService;
import com.indihx.comm.util.RandomUtil;


@Service("pmProjectInfoService")
public class PmProjectInfoServiceImpl implements PmProjectInfoService {
	@Resource
   	PmProjectInfoMapper pmProjectInfoMapper;
   	private static String type="XM";
   
   	public PmProjectInfoEntity queryObject(long id){
   		return pmProjectInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public long insert(PmProjectInfoEntity entity){
		entity.setIsDelete("00");
		entity.setProjectStatus("00");
		entity.setApproveStatus("00");
		entity.setProjectCode(RandomUtil.getCodeByType(type));
   		pmProjectInfoMapper.insert(entity);
   		return entity.getProjectId();
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmProjectInfoEntity entity){
   		pmProjectInfoMapper.update(entity);
   	}

	public void delete(long projectId){
   		pmProjectInfoMapper.delete( projectId);
   	}

	public int queryTotal(){
   		return pmProjectInfoMapper.queryTotal();
   	}

   	public List<PmProjectInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmProjectInfoMapper.queryList(entity);
   	}
	public List<PmProjectInfoEntity> queryList(Map<String, Object> entity,Integer pageNum, Integer pageSize){
		if(pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
			entity.put("isDelete", "00");
		}
		return pmProjectInfoMapper.queryList(entity);
	}
}
