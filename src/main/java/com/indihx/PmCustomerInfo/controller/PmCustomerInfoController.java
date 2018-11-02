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

import com.indihx.PmCustomerInfo.entity.PmCustomerInfoEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerInfoService;
import com.indihx.PmCustomerInfo.service.impl.PmCustomerInfoServiceImpl;
import com.indihx.comm.util.R;
import com.indihx.comm.util.PageUtils;


/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 18:46:08
 */
@Controller
@RequestMapping("/pmcustomerinfo")
public class PmCustomerInfoController {
    @Autowired
    private PmCustomerInfoService pmCustomerInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params){
       
		List<PmCustomerInfoEntity> pmCustomerInfo = pmCustomerInfoService.queryList(params);
        return R.ok().put("page", pmCustomerInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("custId") long custId){
        
		PmCustomerInfoEntity entity = pmCustomerInfoService.queryObject(custId);
        return R.ok().put("pmCustomerInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmCustomerInfoEntity pmCustomerInfo){
        pmCustomerInfoService.insert(pmCustomerInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmCustomerInfoEntity pmCustomerInfo){
        
        pmCustomerInfoService.update(pmCustomerInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long custId){
        pmCustomerInfoService.delete(custId);

        return R.ok();
    }
    
    /**
     * 列表
     */
    @RequestMapping(value="/saveList",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> saveList(List<PmCustomerInfoEntity> pmCustomerInfo){
    	if(pmCustomerInfo.isEmpty()) {
    		return R.error("请传入数据");
    	}
       for(int i = 0;i<pmCustomerInfo.size();i++) {
    	   PmCustomerInfoEntity item = pmCustomerInfo.get(i);
    	   pmCustomerInfoService.insert(item);
       }
        return R.ok().put("page", pmCustomerInfo);
    }

}
