package com.indihx.PmCompanyInfo.controller;


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

import com.indihx.system.entity.UsrInfo;
import com.alibaba.fastjson.JSON;
import com.indihx.PmCompanyInfo.entity.PmCompanyInfoEntity;
import com.indihx.PmCompanyInfo.service.PmCompanyInfoService;
import com.indihx.comm.util.R;
import com.indihx.util.UserUtil;
import com.indihx.comm.util.PageUtils;


/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 18:46:08
 */
@Controller
@RequestMapping("/pmcompanyinfo")
public class PmCompanyInfoController {
    @Autowired
    private PmCompanyInfoService pmCompanyInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody ResponseData list( @RequestBody Map<String, Object> params ,HttpSession session){
    	String str = (String) params.get("queryStr");
    	Map<String,Object> maps = (Map<String,Object>)JSON.parse(str);
        return new ResponseData(pmCompanyInfoService.queryList(maps,params.get("page")==null?null:(int)params.get("page"),params.get("limit")==null?null:(int)params.get("limit")));
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("companyCode") long companyCode,HttpSession session){
        
		PmCompanyInfoEntity entity = pmCompanyInfoService.queryObject(companyCode);
        return R.ok().put("pmCompanyInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmCompanyInfoEntity pmCompanyInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmCompanyInfo.setCreatorId(usesr.getUsrId());
        pmCompanyInfo.setCreateTime(DateUtil.formatFromDB(DateUtil.getSysDate()));
        pmCompanyInfoService.insert(pmCompanyInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmCompanyInfoEntity pmCompanyInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmCompanyInfo.setModifier(usesr.getUsrId());
        pmCompanyInfo.setModifyTime(DateUtil.formatFromDB(DateUtil.getSysDate()));
        pmCompanyInfoService.update(pmCompanyInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long companyId,HttpSession session){
        pmCompanyInfoService.delete(companyId);

        return R.ok();
    }

}
