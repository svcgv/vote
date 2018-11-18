package com.indihx.PmProjectGroupInfo.controller;


import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.UserUtil;
import com.indihx.PmProjectGroupInfo.entity.PmProjectGroupInfoEntity;
import com.indihx.PmProjectGroupInfo.service.PmProjectGroupInfoService;
import com.indihx.PmProjectGroupRelationInfo.dao.PmProjectGroupRelationInfoMapper;
import com.indihx.PmProjectGroupRelationInfo.entity.PmProjectGroupRelationInfoEntity;
import com.indihx.PmProjectGroupRelationInfo.service.PmProjectGroupRelationInfoService;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.PmProjectInfoService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;


/**
 * 
 *
 * @author hb
 * @date 2018-11-07 20:18:23
 */
@Controller
@RequestMapping("/pmprojectgroupinfo")
public class PmProjectGroupInfoController {
    @Autowired
    private PmProjectGroupInfoService pmProjectGroupInfoService;
    
    @Autowired
    private PmProjectGroupRelationInfoService pmProjectGroupRelationInfoService;
    
    @Autowired
    private PmProjectInfoService pmProjectInfoService;
    
	@Resource
	PmProjectGroupRelationInfoMapper pmProjectGroupRelationInfoMapper;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmProjectGroupInfoEntity> pmProjectGroupInfo = pmProjectGroupInfoService.queryList(params);
        return R.ok().put("page", pmProjectGroupInfo);
    }
    
    @RequestMapping(value="/listChildren",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> listChildren(@RequestBody Map<String, Object> params,HttpSession session){
    	List<PmProjectGroupRelationInfoEntity> pmProjectGroupRelationInfo = pmProjectGroupRelationInfoService.queryList(params);
    	List<PmProjectInfoEntity> list = new ArrayList<PmProjectInfoEntity>();
    	for(PmProjectGroupRelationInfoEntity pmProjectGroupRelationInfoEntity:pmProjectGroupRelationInfo) {
    		list.add(pmProjectInfoService.queryObject(pmProjectGroupRelationInfoEntity.getProjectId()));
    	}
		return R.ok().put("page", list);
    	
    }
    
    @RequestMapping(value="/changeChildren",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> changeChildren(@RequestBody PmProjectGroupInfoEntity params,HttpSession session){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("projectGroupId", params.getProjectGroupId());
    	List<PmProjectGroupRelationInfoEntity> pmProjectGroupRelationInfo = pmProjectGroupRelationInfoService.queryList(map);
    	for(PmProjectGroupRelationInfoEntity pmProjectGroupRelationInfoEntity:pmProjectGroupRelationInfo) {
    		pmProjectGroupRelationInfoService.delete(pmProjectGroupRelationInfoEntity.getRelationship());
    	}
    	
    	UsrInfo usesr = UserUtil.getUser(session);
    	
  		for(String projectId:params.getProjectIds()) {
   			PmProjectGroupRelationInfoEntity pmProjectGroupRelationInfoEntity = new PmProjectGroupRelationInfoEntity();
   			pmProjectGroupRelationInfoEntity.setProjectGroupId(params.getProjectGroupId());
   			pmProjectGroupRelationInfoEntity.setProjectId(Long.valueOf(projectId));
   			pmProjectGroupRelationInfoEntity.setCreatorId(usesr.getUsrId());
   			pmProjectGroupRelationInfoEntity.setCreateTime(DateUtil.getDateTime());
   			pmProjectGroupRelationInfoEntity.setIsDelete("00");
   			pmProjectGroupRelationInfoMapper.insert(pmProjectGroupRelationInfoEntity);
   		}
  		
  		
  		PmProjectGroupInfoEntity pmProjectGroupInfoEntity = pmProjectGroupInfoService.queryObject(params.getProjectGroupId());
  		pmProjectGroupInfoEntity.setModifier(usesr.getUsrId());
  		pmProjectGroupInfoEntity.setModifyTime(DateUtil.getDateTime());
  		pmProjectGroupInfoEntity.setProjectGroupName(params.getProjectGroupName());
        pmProjectGroupInfoService.update(pmProjectGroupInfoEntity);//全部更新
  		
        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("projectGroupId") long projectGroupId,HttpSession session){

		PmProjectGroupInfoEntity entity = pmProjectGroupInfoService.queryObject(projectGroupId);
        return R.ok().put("pmProjectGroupInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProjectGroupInfoEntity pmProjectGroupInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectGroupInfo.setGroupCreateTime(DateUtil.getDateTime());
    	pmProjectGroupInfo.setGroupCreatorId(usesr.getUsrId());
    	pmProjectGroupInfo.setGroupCreatorName(usesr.getUsrName());
    	pmProjectGroupInfo.setCreatorId(usesr.getUsrId());
    	pmProjectGroupInfo.setCreateTime(DateUtil.getDateTime());
        pmProjectGroupInfoService.insert(pmProjectGroupInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProjectGroupInfoEntity pmProjectGroupInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectGroupInfo.setModifier(usesr.getUsrId());
    	pmProjectGroupInfo.setModifyTime(DateUtil.getDateTime());
        pmProjectGroupInfoService.update(pmProjectGroupInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long projectGroupId,HttpSession session){
        pmProjectGroupInfoService.delete(projectGroupId);
        return R.ok();
    }

}
