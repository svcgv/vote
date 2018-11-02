package com.indihx.PmCustomerInfo.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.indihx.comm.util.R;
import com.indihx.system.entity.UsrInfo;
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupRelationEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerGroupRelationService;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.util.PageUtils;


/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 18:46:08
 */
@Controller
@RequestMapping("/pmcustomergrouprelation")
public class PmCustomerGroupRelationController {
    @Autowired
    private PmCustomerGroupRelationService pmCustomerGroupRelationService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){
       
		List<PmCustomerGroupRelationEntity> pmCustomerGroupRelation = pmCustomerGroupRelationService.queryList(params);
        return R.ok().put("page", pmCustomerGroupRelation);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("custGroupRelationId") long custGroupRelationId,HttpSession session){
        
		PmCustomerGroupRelationEntity entity = pmCustomerGroupRelationService.queryObject(custGroupRelationId);
        return R.ok().put("pmCustomerGroupRelation", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmCustomerGroupRelationEntity pmCustomerGroupRelation,HttpSession session){
    	UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
    	pmCustomerGroupRelation.setCreatorId(user.getUsrId());
    	pmCustomerGroupRelation.setCreateTime(new Date());
        
        pmCustomerGroupRelationService.insert(pmCustomerGroupRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmCustomerGroupRelationEntity pmCustomerGroupRelation,HttpSession session){
    	UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
    	pmCustomerGroupRelation.setModifier(user.getUsrId());
    	pmCustomerGroupRelation.setModifyTime(new Date());
        pmCustomerGroupRelationService.update(pmCustomerGroupRelation);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long custGroupRelationId,HttpSession session){
        pmCustomerGroupRelationService.delete(custGroupRelationId);

        return R.ok();
    }

}
