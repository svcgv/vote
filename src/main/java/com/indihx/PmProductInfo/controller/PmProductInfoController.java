package com.indihx.PmProductInfo.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import java.util.List;
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
import com.alibaba.fastjson.JSON;
import com.indihx.PmProductInfo.entity.PmProductInfoEntity;
import com.indihx.PmProductInfo.service.PmProductInfoService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * 
 *
 * @author hb
 * @date 2018-11-03 17:12:19
 */
@Controller
@RequestMapping("/pmproductinfo")
public class PmProductInfoController {
    @Autowired
    private PmProductInfoService pmProductInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){
    	String str = (String) params.get("queryStr");
    	Map<String,Object> maps = (Map<String,Object>)JSON.parse(str);
		List<PmProductInfoEntity> pmProductInfo = pmProductInfoService.queryList(maps);
        return R.ok().put("page", pmProductInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("productId") long productId,HttpSession session){

		PmProductInfoEntity entity = pmProductInfoService.queryObject(productId);
        return R.ok().put("pmProductInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProductInfoEntity pmProductInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProductInfo.setCreatorId(usesr.getUsrId());
    	pmProductInfo.setCreateTime(DateUtil.getDateTime());
        pmProductInfoService.insert(pmProductInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProductInfoEntity pmProductInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProductInfo.setModifier(usesr.getUsrId());
    	pmProductInfo.setModifyTime(DateUtil.getDateTime());
        pmProductInfoService.update(pmProductInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long productId,HttpSession session){
        pmProductInfoService.delete(productId);
        return R.ok();
    }

}
