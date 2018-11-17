package com.indihx.PmContractInfo.controller;


import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.UserUtil;
import com.indihx.PmContractInfo.entity.PmContractInfoEntity;
import com.indihx.PmContractInfo.service.PmContractInfoService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * ${comments}
 *
 * @author hb
 * @date 2018-11-17 11:27:29
 */
@Controller
@RequestMapping("/pmcontractinfo")
public class PmContractInfoController {
    @Autowired
    private PmContractInfoService pmContractInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){
        String str = (String) params.get("queryStr");
        Map<String,Object> maps = (Map<String,Object>) JSON.parse(str);
		List<PmContractInfoEntity> pmContractInfo = pmContractInfoService.queryList(maps);
        return R.ok().put("page", pmContractInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("contractId") long contractId,HttpSession session){

		PmContractInfoEntity entity = pmContractInfoService.queryObject(contractId);
        return R.ok().put("pmContractInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmContractInfoEntity pmContractInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmContractInfo.setCreatorId(usesr.getUsrId());
        pmContractInfo.setYearNumer(DateUtil.getYear(DateUtil.getDateTime()));
    	pmContractInfo.setCreateTime(DateUtil.getDateTime());
        pmContractInfoService.insert(pmContractInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmContractInfoEntity pmContractInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmContractInfo.setModifier(usesr.getUsrId());
    	pmContractInfo.setModifyTime(DateUtil.getDateTime());
        pmContractInfoService.update(pmContractInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long contractId,HttpSession session){
        pmContractInfoService.delete(contractId);
        return R.ok();
    }

}
