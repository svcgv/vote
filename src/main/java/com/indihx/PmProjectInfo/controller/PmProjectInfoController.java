package com.indihx.PmProjectInfo.controller;


import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.indihx.comm.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.indihx.system.entity.CostInfo;
import com.indihx.system.entity.ProfitInfo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.UserUtil;
import com.indihx.PmConfirmBid.entity.PmConfirmBidEntity;
import com.indihx.PmConfirmBid.service.PmConfirmBidService;
import com.indihx.PmProjectGroupRelationInfo.entity.PmProjectGroupRelationInfoEntity;
import com.indihx.PmProjectGroupRelationInfo.service.PmProjectGroupRelationInfoService;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.PmProjectInfoService;
import com.indihx.PmProjectMilestoneInfo.entity.PmProjectMilestoneInfoEntity;
import com.indihx.PmProjectMilestoneInfo.service.PmProjectMilestoneInfoService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.service.ICostInfoService;
import com.indihx.service.IProfitInfoService;

/**
 * 
 *
 * @author hb
 * @date 2018-11-09 10:04:00
 */
@Controller
@RequestMapping("/pmprojectinfo")
public class PmProjectInfoController {
	
	@Autowired
	private PmProjectGroupRelationInfoService pmProjectGroupRelationInfoService;
	
    @Autowired
    private PmProjectInfoService pmProjectInfoService;
    
    @Autowired
    private IProfitInfoService profitInfoService;
    
    @Autowired
    private ICostInfoService costInfoService;
    
    @Autowired
    private PmConfirmBidService pmConfirmBidService;
    
    @Autowired
    private PmProjectMilestoneInfoService pmProjectMilestoneInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody
    ResponseData list(@RequestBody Map<String, Object> params, HttpSession session){
        String str = (String) params.get("queryStr");
        Map<String,Object> maps = (Map<String,Object>) JSON.parse(str);
        return new ResponseData(pmProjectInfoService.queryList(maps,params.get("page")==null?null:(int)params.get("page"),params.get("limit")==null?null:(int)params.get("limit")));
    }

    
    @RequestMapping(value="/listTender",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> listTender(@RequestBody Map<String, Object> params,HttpSession session){
    	String str = (String) params.get("queryStr");
    	Map<String,Object> maps = (Map<String,Object>)JSON.parse(str);
    	maps.put("status", "04");
    	maps.put("isDelete", "00");
		List<PmConfirmBidEntity> confirmList = pmConfirmBidService.queryList(maps);
		
		Map<String,Object> entity = new HashMap<String,Object>();
		entity.put("isDelete", "00");
		List<PmProjectInfoEntity> projectList = pmProjectInfoService.queryList(entity);
		
		List<PmConfirmBidEntity> isRef = new ArrayList<PmConfirmBidEntity>();
		
		boolean notexsit = true;
		
		for(PmConfirmBidEntity confirm:confirmList) {
			for(PmProjectInfoEntity project:projectList) {
				if(confirm.getBidId() == project.getBidId()) {
					notexsit = false;
				}
			}
			
			if(notexsit) {
				isRef.add(confirm);
			}
		}
        return R.ok().put("page", isRef);
    }

    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("projectId") long projectId,HttpSession session){

		PmProjectInfoEntity entity = pmProjectInfoService.queryObject(projectId);
        return R.ok().put("pmProjectInfo", entity);
    }
    
    @RequestMapping(value="/queryProfitInfo",method=RequestMethod.POST)
    public @ResponseBody  Map<String,Object>  queryProfitInfo(@RequestBody Map<String, Object> param ,HttpSession session){
    	ProfitInfo entity = profitInfoService.queryProfitInfoByOrgId(Long.valueOf(param.get("orgId").toString()));
    	return R.ok().put("profitInfo", entity);
    }
    
    @RequestMapping(value="/queryCostInfo",method=RequestMethod.POST)
    public @ResponseBody  Map<String,Object>  queryCostInfo(@RequestBody Map<String, Object> param ,HttpSession session){
    	List<CostInfo> list = costInfoService.qryCostInfoListByOrgId(Long.valueOf(param.get("orgId").toString()));
    	return R.ok().put("page", list);
    }
    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProjectInfoEntity pmProjectInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectInfo.setCreatorId(usesr.getUsrId());
    	pmProjectInfo.setCreateTime(DateUtil.getDateTime());
        long projectId = pmProjectInfoService.insert(pmProjectInfo);
        
        if(pmProjectInfo.getBelongProjectGroupId()>0) {
        	Map<String, Object> entity = new HashMap<String, Object>();
        	entity.put("projectId", projectId);
        	entity.put("projectGroupId", pmProjectInfo.getBelongProjectGroupId());
        	List<PmProjectGroupRelationInfoEntity> relList = pmProjectGroupRelationInfoService.queryList(entity);
        	if(relList.size()==0) {
        		PmProjectGroupRelationInfoEntity pmProjectGroupRelationInfoEntity = new PmProjectGroupRelationInfoEntity();
        		pmProjectGroupRelationInfoEntity.setProjectGroupId(pmProjectInfo.getBelongProjectGroupId());
        		pmProjectGroupRelationInfoEntity.setProjectId(projectId);
        		pmProjectGroupRelationInfoEntity.setIsDelete("00");
        		pmProjectGroupRelationInfoEntity.setCreatorId(usesr.getUsrId());
        		pmProjectGroupRelationInfoEntity.setCreateTime(DateUtil.getDateTime());
        		pmProjectGroupRelationInfoService.insert(pmProjectGroupRelationInfoEntity);
        	}
        }
        
        pmProjectMilestoneInfoService.insertList(pmProjectInfo,projectId);
        
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProjectInfoEntity pmProjectInfo,HttpSession session){
    	
    	PmProjectInfoEntity old = pmProjectInfoService.queryObject(pmProjectInfo.getProjectId());
    	
    	Map<String, Object> entity = new HashMap<String, Object>();
    	entity.put("projectId", old.getProjectId());
    	entity.put("projectGroupId", old.getBelongProjectGroupId());
    	List<PmProjectGroupRelationInfoEntity> relList = pmProjectGroupRelationInfoService.queryList(entity);
    	if(relList.size()>0) {
    		PmProjectGroupRelationInfoEntity pmProjectGroupRelationInfoEntity = relList.get(0);
    		
    		if(pmProjectGroupRelationInfoEntity.getProjectGroupId() != pmProjectInfo.getBelongProjectGroupId()) {
    			pmProjectGroupRelationInfoEntity.setProjectGroupId(pmProjectInfo.getBelongProjectGroupId());
    			pmProjectGroupRelationInfoService.update(pmProjectGroupRelationInfoEntity);
    		}
    	}
    	
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectInfo.setModifier(usesr.getUsrId());
    	pmProjectInfo.setModifyTime(DateUtil.getDateTime());
        pmProjectInfoService.update(pmProjectInfo);//全部更新
        
        
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("projectId", pmProjectInfo.getProjectId());
    	List<PmProjectMilestoneInfoEntity>  PmProjectMilestoneInfo = pmProjectMilestoneInfoService.queryList(map);
    	for(PmProjectMilestoneInfoEntity pmProjectMilestoneInfoEntity:PmProjectMilestoneInfo) {
    		pmProjectMilestoneInfoService.delete(pmProjectMilestoneInfoEntity.getMilestoneId());
    	}
    	
        pmProjectMilestoneInfoService.insertList(pmProjectInfo,pmProjectInfo.getProjectId());
        
        return R.ok();
    }
    
    
    @RequestMapping(value="/updateDelete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> updateDelete(@RequestBody PmProjectInfoEntity pmProjectInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectInfo.setModifier(usesr.getUsrId());
    	pmProjectInfo.setModifyTime(DateUtil.getDateTime());
        pmProjectInfoService.update(pmProjectInfo);//全部更新
        
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("projectId", pmProjectInfo.getProjectId());
    	List<PmProjectMilestoneInfoEntity>  PmProjectMilestoneInfo = pmProjectMilestoneInfoService.queryList(map);
    	for(PmProjectMilestoneInfoEntity pmProjectMilestoneInfoEntity:PmProjectMilestoneInfo) {
    		pmProjectMilestoneInfoEntity.setIsDelete("01");
    		pmProjectMilestoneInfoEntity.setModifier(usesr.getUsrId());
    		pmProjectMilestoneInfoEntity.setModifyTime(DateUtil.getDateTime());
    		pmProjectMilestoneInfoService.update(pmProjectMilestoneInfoEntity);
    	}
        
        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long projectId,HttpSession session){
        pmProjectInfoService.delete(projectId);
        return R.ok();
    }

}
