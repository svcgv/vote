package com.indihx.PmSaleGroupInfo.controller;


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

import com.indihx.PmSaleGroupInfo.entity.PmSaleGroupInfoEntity;
import com.indihx.PmSaleGroupInfo.service.PmSaleGroupInfoService;
import com.indihx.comm.util.R;
import com.indihx.system.entity.UsrInfo;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.util.PageUtils;


/**
 * 
 *
 * @author hb
 * @date 2018-11-02 20:19:45
 */
@Controller
@RequestMapping("/pmsalegroupinfo")
public class PmSaleGroupInfoController {
    @Autowired
    private PmSaleGroupInfoService pmSaleGroupInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){
		List<PmSaleGroupInfoEntity> pmSaleGroupInfo = pmSaleGroupInfoService.queryList(params);
        return R.ok().put("page", pmSaleGroupInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("groupId") long groupId,HttpSession session){
		PmSaleGroupInfoEntity entity = pmSaleGroupInfoService.queryObject(groupId);
        return R.ok().put("pmSaleGroupInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmSaleGroupInfoEntity pmSaleGroupInfo,HttpSession session){
    	UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
    	pmSaleGroupInfo.setCreatorId(user.getUsrId());
    	pmSaleGroupInfo.setCreateTime(new Date());
        pmSaleGroupInfoService.insert(pmSaleGroupInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmSaleGroupInfoEntity pmSaleGroupInfo,HttpSession session){
    	UsrInfo	user= (UsrInfo)session.getAttribute(InitSysConstants.USER_SESSION);
    	pmSaleGroupInfo.setModifier(user.getUsrId());
    	pmSaleGroupInfo.setModifyTime(new Date());
        pmSaleGroupInfoService.update(pmSaleGroupInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long groupId,HttpSession session){
        pmSaleGroupInfoService.delete(groupId);
        return R.ok();
    }

}
