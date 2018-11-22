package com.indihx.PmProjectMilestoneInfo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectMilestoneInfo.dao.PmProjectMilestoneInfoMapper;
import com.indihx.PmProjectMilestoneInfo.entity.PmProjectMilestoneInfoEntity;
import com.indihx.PmProjectMilestoneInfo.service.PmProjectMilestoneInfoService;


@Service("pmProjectMilestoneInfoService")
public class PmProjectMilestoneInfoServiceImpl implements PmProjectMilestoneInfoService {
	@Resource
   	PmProjectMilestoneInfoMapper pmProjectMilestoneInfoMapper;
   	
   
   	public PmProjectMilestoneInfoEntity queryObject(long id){
   		return pmProjectMilestoneInfoMapper.queryObject(id);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(PmProjectMilestoneInfoEntity entity){
   		pmProjectMilestoneInfoMapper.insert(entity);
   	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PmProjectMilestoneInfoEntity entity){
   		pmProjectMilestoneInfoMapper.update(entity);
   	}

	public void delete(long milestoneId){
   		pmProjectMilestoneInfoMapper.delete( milestoneId);
   	}

	public int queryTotal(){
   		return pmProjectMilestoneInfoMapper.queryTotal();
   	}

   	public List<PmProjectMilestoneInfoEntity> queryList(Map<String, Object> entity){
   		if(entity.get("isDelete")==null||"".equals(entity.get("isDelete"))) {
   			entity.put("isDelete", "00");
   		}
   		return pmProjectMilestoneInfoMapper.queryList(entity);
   	}
	@Override
	public void insertList(PmProjectInfoEntity pmProjectInfo, long projectId) {
		if(pmProjectInfo.getRequirement()!="" || pmProjectInfo.getRequirementremarks()!="") {
			PmProjectMilestoneInfoEntity entity = new PmProjectMilestoneInfoEntity();
			entity.setProjectId(projectId);
			entity.setMileDate(pmProjectInfo.getRequirement());
			entity.setMileDescript(pmProjectInfo.getRequirementremarks());
			entity.setMileType("requirement");
			entity.setIsDelete("00");
			entity.setCreateTime(pmProjectInfo.getModifyTime());
			entity.setCreatorId(pmProjectInfo.getModifier());
			insert(entity);
		}
		
		
		if(pmProjectInfo.getDesign()!="" || pmProjectInfo.getDesignRemarks()!="") {
			PmProjectMilestoneInfoEntity entity = new PmProjectMilestoneInfoEntity();
			entity.setProjectId(projectId);
			entity.setMileDate(pmProjectInfo.getDesign());
			entity.setMileDescript(pmProjectInfo.getDesignRemarks());
			entity.setMileType("design");
			entity.setIsDelete("00");
			entity.setCreateTime(pmProjectInfo.getModifyTime());
			entity.setCreatorId(pmProjectInfo.getModifier());
			insert(entity);
		}
		
		if(pmProjectInfo.getDevlopment()!="" || pmProjectInfo.getDevlopmentRemarks()!="") {
			PmProjectMilestoneInfoEntity entity = new PmProjectMilestoneInfoEntity();
			entity.setProjectId(projectId);
			entity.setMileDate(pmProjectInfo.getDevlopment());
			entity.setMileDescript(pmProjectInfo.getDevlopmentRemarks());
			entity.setMileType("devlopment");
			entity.setIsDelete("00");
			entity.setCreateTime(pmProjectInfo.getModifyTime());
			entity.setCreatorId(pmProjectInfo.getModifier());
			insert(entity);
		}
		
		if(pmProjectInfo.getTest()!="" || pmProjectInfo.getTestRemarks()!="") {
			PmProjectMilestoneInfoEntity entity = new PmProjectMilestoneInfoEntity();
			entity.setProjectId(projectId);
			entity.setMileDate(pmProjectInfo.getTest());
			entity.setMileDescript(pmProjectInfo.getTestRemarks());
			entity.setMileType("test");
			entity.setIsDelete("00");
			entity.setCreateTime(pmProjectInfo.getModifyTime());
			entity.setCreatorId(pmProjectInfo.getModifier());
			insert(entity);
		}
		
		
		if(pmProjectInfo.getOnline()!="" || pmProjectInfo.getOnlineRemarks()!="") {
			PmProjectMilestoneInfoEntity entity = new PmProjectMilestoneInfoEntity();
			entity.setProjectId(projectId);
			entity.setMileDate(pmProjectInfo.getOnline());
			entity.setMileDescript(pmProjectInfo.getOnlineRemarks());
			entity.setMileType("online");
			entity.setIsDelete("00");
			entity.setCreateTime(pmProjectInfo.getModifyTime());
			entity.setCreatorId(pmProjectInfo.getModifier());
			insert(entity);
		}
		
		if(pmProjectInfo.getCheck()!="" || pmProjectInfo.getCheckRemarks()!="") {
			PmProjectMilestoneInfoEntity entity = new PmProjectMilestoneInfoEntity();
			entity.setProjectId(projectId);
			entity.setMileDate(pmProjectInfo.getCheck());
			entity.setMileDescript(pmProjectInfo.getCheckRemarks());
			entity.setMileType("check");
			entity.setIsDelete("00");
			entity.setCreateTime(pmProjectInfo.getModifyTime());
			entity.setCreatorId(pmProjectInfo.getModifier());
			insert(entity);
		}
		
		if(pmProjectInfo.getProduction()!="" || pmProjectInfo.getProductionRemarks()!="") {
			PmProjectMilestoneInfoEntity entity = new PmProjectMilestoneInfoEntity();
			entity.setProjectId(projectId);
			entity.setMileDate(pmProjectInfo.getProduction());
			entity.setMileDescript(pmProjectInfo.getProductionRemarks());
			entity.setMileType("production");
			entity.setIsDelete("00");
			entity.setCreateTime(pmProjectInfo.getModifyTime());
			entity.setCreatorId(pmProjectInfo.getModifier());
			insert(entity);
		}
	}
	@Override
	public void queryListInfo(PmProjectInfoEntity pmProjectInfo, long projectId) {
	   	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("projectId", projectId);
    	List<PmProjectMilestoneInfoEntity>  pmProjectMilestoneInfo = queryList(map);
    	for(PmProjectMilestoneInfoEntity entity:pmProjectMilestoneInfo) {
    		
    		if("requirement".equals(entity.getMileType())) {
    			pmProjectInfo.setRequirement(entity.getMileDate());
    			pmProjectInfo.setRequirementremarks(entity.getMileDescript());
    		}
    		
    		if("design".equals(entity.getMileType())) {
    			pmProjectInfo.setDesign(entity.getMileDate());
    			pmProjectInfo.setDesignRemarks(entity.getMileDescript());
    		}
    		
    		
    		if("devlopment".equals(entity.getMileType())) {
    			pmProjectInfo.setDevlopment(entity.getMileDate());
    			pmProjectInfo.setDevlopmentRemarks(entity.getMileDescript());
    		}
    		
    		if("test".equals(entity.getMileType())) {
    			pmProjectInfo.setTest(entity.getMileDate());
    			pmProjectInfo.setTestRemarks(entity.getMileDescript());
    		}
    		
    		if("online".equals(entity.getMileType())) {
    			pmProjectInfo.setOnline(entity.getMileDate());
    			pmProjectInfo.setOnlineRemarks(entity.getMileDescript());
    		}
    		
    		if("check".equals(entity.getMileType())) {
    			pmProjectInfo.setCheck(entity.getMileDate());
    			pmProjectInfo.setCheckRemarks(entity.getMileDescript());
    		}
    		
    		if("production".equals(entity.getMileType())) {
    			pmProjectInfo.setProduction(entity.getMileDate());
    			pmProjectInfo.setProductionRemarks(entity.getMileDescript());
    		}
    		
    	}
	}
}
