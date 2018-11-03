package com.indihx.PmSaleGroupInfo.controller;


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
import com.indihx.PmSaleGroupInfo.entity.PmSaleMemberInfoEntity;
import com.indihx.PmSaleGroupInfo.service.PmSaleMemberInfoService;
import com.indihx.comm.util.PageUtils;


/**
 * 
 *
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-10-30 19:04:20
 */
@Controller
@RequestMapping("/pmsalememberinfo")
public class PmSaleMemberInfoController {
    @Autowired
    private PmSaleMemberInfoService pmSaleMemberInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params){
       
		List<PmSaleMemberInfoEntity> pmSaleMemberInfo = pmSaleMemberInfoService.queryList(params);
        return R.ok().put("page", pmSaleMemberInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("menberUsrId") Long menberUsrId){
        
		PmSaleMemberInfoEntity entity = pmSaleMemberInfoService.queryObject(menberUsrId);
        return R.ok().put("pmSaleMemberInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmSaleMemberInfoEntity pmSaleMemberInfo){
        pmSaleMemberInfoService.insert(pmSaleMemberInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmSaleMemberInfoEntity pmSaleMemberInfo){
        
        pmSaleMemberInfoService.update(pmSaleMemberInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody Long menberUsrId){
        pmSaleMemberInfoService.delete(menberUsrId);

        return R.ok();
    }

}
