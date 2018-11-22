package com.indihx.PmCustomerInfo.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.indihx.comm.util.R;
import com.indihx.system.entity.UsrInfo;
import com.alibaba.fastjson.JSON;
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupEntity;
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupRelationEntity;
import com.indihx.PmCustomerInfo.entity.PmCustomerInfoEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerGroupRelationService;
import com.indihx.PmCustomerInfo.service.PmCustomerGroupService;
import com.indihx.PmCustomerInfo.service.PmCustomerInfoService;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.util.PageUtils;


/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-31 19:17:35
 */
@Controller
@RequestMapping("/pmcustomergroup")
public class PmCustomerGroupController {
    @Autowired
    private PmCustomerGroupService pmCustomerGroupService;
    
    @Autowired
    private PmCustomerGroupRelationService pmCustomerGroupRelationService;

    @Autowired
    private PmCustomerInfoService pmCustomerInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody ResponseData list(@RequestBody Map<String, Object> params,HttpSession session){
    	String str = (String) params.get("queryStr");
    	Map<String,Object> maps = (Map<String,Object>)JSON.parse(str);
        return new ResponseData(pmCustomerGroupService.queryList(maps,params.get("page")==null?null:(int)params.get("page"),params.get("limit")==null?null:(int)params.get("limit")));
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("custGroupId") String custGroupId,HttpSession session){
        
		PmCustomerGroupEntity entity = pmCustomerGroupService.queryObject(custGroupId);
        return R.ok().put("pmCustomerGroup", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmCustomerGroupEntity pmCustomerGroup,HttpSession session){
        
        UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
        pmCustomerGroup.setCreatorId(user.getUsrId());
        pmCustomerGroup.setCreator(user.getUsrName());
        pmCustomerGroup.setCreateTime(DateUtil.formatFromDB(DateUtil.getSysDate()));
        pmCustomerGroupService.insert(pmCustomerGroup);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmCustomerGroupEntity pmCustomerGroup,HttpSession session){
    	UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
        pmCustomerGroup.setModifier(user.getUsrId());
        pmCustomerGroup.setModifyTime(DateUtil.formatFromDB(DateUtil.getSysDate()));
        pmCustomerGroupService.update(pmCustomerGroup);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody String custGroupId,HttpSession session){
        pmCustomerGroupService.delete(custGroupId);
        
        return R.ok();
    }
    
    
    @RequestMapping(value="/changeRelation",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> changeRelation(@RequestBody  Map<String, Object> params,HttpSession session){
        String groupId= (String) params.get("custGroupId");
    	String groupName= (String) params.get("name");
    	List<String> ctnCodes = (List<String>) params.get("ctnCodes");
    	PmCustomerGroupEntity pmCustomerGroup = new PmCustomerGroupEntity();
    	pmCustomerGroup.setCustGroupId(groupId);
    	pmCustomerGroup.setCustGroupName(groupName);
    	UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
        pmCustomerGroup.setModifier(user.getUsrId());
        pmCustomerGroup.setModifyTime(DateUtil.formatFromDB(DateUtil.getSysDate()));
        pmCustomerGroupService.update(pmCustomerGroup);//全部更新
        pmCustomerGroupRelationService.deleteByGroupId(groupId);
        if(!ctnCodes.isEmpty()) {
        	String code=null;
        	PmCustomerInfoEntity cust = null;
        	PmCustomerGroupRelationEntity entity = new PmCustomerGroupRelationEntity();
        	entity.setCustGroupId(groupId);
        	for(int i=0;i<ctnCodes.size();i++) {
        		code = ctnCodes.get(i);
        		entity.setSapCode(code);
        		cust = pmCustomerInfoService.queryBySapCode(code);
        		entity.setCustCnName(cust.getCustCnName());
        		entity.setCustId(cust.getCustId());
        		pmCustomerGroupRelationService.insert(entity);
        	}
        }
        return R.ok();
    }
    

}
