package com.indihx.PmCostInfo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.indihx.PmCompanyInfo.entity.PmCompanyInfoEntity;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.R;
import com.indihx.service.ICostInfoService;
import com.indihx.system.entity.CostInfo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.UserUtil;

@Controller
@RequestMapping("/costInfo")
public class PmCostInfoController {
	
	@Autowired
	private ICostInfoService costInfoService;
	
	 /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list( @RequestBody Map<String, Object> params ,HttpSession session){
    	String str = (String) params.get("queryStr");
    	Map<String,Object> maps = (Map<String,Object>)JSON.parse(str);
		List<CostInfo> costInfo = costInfoService.qryCostInfoList(maps);
        return R.ok().put("page", costInfo);
    }
	
    
    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody CostInfo costInfo,HttpSession session){
    	
    	costInfoService.addCostInfo(costInfo);

        return R.ok();
    }
    
    
    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody CostInfo costInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	
    	costInfoService.updateCostInfo(costInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long costId,HttpSession session){
    	costInfoService.delCostInfo(costId);
        return R.ok();
    }

   
	
}
