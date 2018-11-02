package com.indihx.PmCustomerInfo.controller;


import java.util.Map;
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
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerGroupService;
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

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params){
       
		List<PmCustomerGroupEntity> pmCustomerGroup = pmCustomerGroupService.queryList(params);
        return R.ok().put("page", pmCustomerGroup);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("custGroupId") String custGroupId){
        
		PmCustomerGroupEntity entity = pmCustomerGroupService.queryObject(custGroupId);
        return R.ok().put("pmCustomerGroup", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmCustomerGroupEntity pmCustomerGroup){
        pmCustomerGroupService.insert(pmCustomerGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmCustomerGroupEntity pmCustomerGroup){
        
        pmCustomerGroupService.update(pmCustomerGroup);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody String custGroupId){
        pmCustomerGroupService.delete(custGroupId);

        return R.ok();
    }

}