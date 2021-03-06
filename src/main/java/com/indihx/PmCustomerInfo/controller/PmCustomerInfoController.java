package com.indihx.PmCustomerInfo.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

import com.indihx.comm.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.indihx.PmCustomerInfo.entity.PmCustomerInfoEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerInfoService;
import com.indihx.comm.util.R;
import com.indihx.system.entity.UsrInfo;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.util.DateUtil;
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
    public @ResponseBody
    ResponseData list(@RequestBody Map<String, Object> params, HttpSession session){
    	String str = (String) params.get("queryStr");
    	Map<String,Object> maps = (Map<String,Object>)JSON.parse(str);
//		List<PmCustomerInfoEntity> pmCustomerInfo = pmCustomerInfoService.queryList(maps);
//        R.ok().put("page", pmCustomerInfo);
//        R.ok().put("count", pmCustomerInfoService.queryTotal());
        return new ResponseData(pmCustomerInfoService.queryList(maps,params.get("page")==null?null:(int)params.get("page"),params.get("limit")==null?null:(int)params.get("limit")));
    }

    /**
     * 列表
     */
    @RequestMapping(value="/listAll",method=RequestMethod.POST)
    public @ResponseBody
    ResponseData listAll(@RequestBody Map<String, Object> params, HttpSession session){
        String str = (String) params.get("queryStr");
//        int page = params.get("page")==null?null:(int)params.get("page");
//        int pageSize = (int) params.get("limit");
        Map<String,Object> maps = (Map<String,Object>)JSON.parse(str);
//		List<PmCustomerInfoEntity> pmCustomerInfo = pmCustomerInfoService.queryList(maps);
//        R.ok().put("page", pmCustomerInfo);
//        R.ok().put("count", pmCustomerInfoService.queryTotal());
        return new ResponseData(pmCustomerInfoService.queryListAll(maps,params.get("page")==null?null:(int)params.get("page"),params.get("limit")==null?null:(int)params.get("limit")));
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("custId") long custId,HttpSession session){
        
		PmCustomerInfoEntity entity = pmCustomerInfoService.queryObject(custId);
        return R.ok().put("pmCustomerInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmCustomerInfoEntity pmCustomerInfo,HttpSession session){
    	UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
    	
    	pmCustomerInfo.setAddUserId(user.getUsrId());
    	pmCustomerInfo.setAddTime(DateUtil.getDateTime());
        pmCustomerInfoService.insert(pmCustomerInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmCustomerInfoEntity pmCustomerInfo,HttpSession session){
    	UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
    	pmCustomerInfo.setModifier(user.getUsrId());
//    	pmCustomerInfo.setModifyTime(DateUtil.getDateTime());
        pmCustomerInfoService.update(pmCustomerInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long custId,HttpSession session){
        pmCustomerInfoService.delete(custId);

        return R.ok();
    }
    
    /**
     * 批量
     */
    @RequestMapping(value="/saveList",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> saveList(@RequestBody List<PmCustomerInfoEntity> pmCustomerInfo,HttpSession session){
    	if(pmCustomerInfo.isEmpty()) {
    		return R.error("请传入数据");
    	}
    	UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
       for(int i = 0;i<pmCustomerInfo.size();i++) {
    	   PmCustomerInfoEntity item = pmCustomerInfo.get(i);
    	   item.setAddUserId(user.getUsrId());
    	   pmCustomerInfoService.insert(item);
       }
        return R.ok().put("page", pmCustomerInfo);
    }

}
