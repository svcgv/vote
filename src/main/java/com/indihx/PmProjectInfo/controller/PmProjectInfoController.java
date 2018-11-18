package com.indihx.PmProjectInfo.controller;


import java.util.Map;
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
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.PmProjectInfoService;
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
    private PmProjectInfoService pmProjectInfoService;
    
    @Autowired
    private IProfitInfoService profitInfoService;
    
    @Autowired
    private ICostInfoService costInfoService;

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
//    	ProfitInfo entity = profitInfoService.queryProfitInfoByOrgId(Long.valueOf(param.get("orgId").toString()));
//    	return R.ok().put("profitInfo", entity);
        return null;
    }
    
    @RequestMapping(value="/queryCostInfo",method=RequestMethod.POST)
    public @ResponseBody  Map<String,Object>  queryCostInfo(@RequestBody Map<String, Object> param ,HttpSession session){
//    	List<CostInfo> list = costInfoService.qryCostInfoListByOrgId(Long.valueOf(param.get("orgId").toString()));
//    	return R.ok().put("page", list);
        return null;
    }
    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProjectInfoEntity pmProjectInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectInfo.setCreatorId(usesr.getUsrId());
    	pmProjectInfo.setCreateTime(DateUtil.getDateTime());
        pmProjectInfoService.insert(pmProjectInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProjectInfoEntity pmProjectInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectInfo.setModifier(usesr.getUsrId());
    	pmProjectInfo.setModifyTime(DateUtil.getDateTime());
        pmProjectInfoService.update(pmProjectInfo);//全部更新
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
