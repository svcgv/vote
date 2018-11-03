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

import com.indihx.PmSaleGroupInfo.entity.PmSaleGroupInfoEntity;
import com.indihx.PmSaleGroupInfo.service.PmSaleGroupInfoService;
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
@RequestMapping("/pmsalegroupinfo")
public class PmSaleGroupInfoController {
    @Autowired
    private PmSaleGroupInfoService pmSaleGroupInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params){
       
		List<PmSaleGroupInfoEntity> pmSaleGroupInfo = pmSaleGroupInfoService.queryList(params);
        return R.ok().put("page", pmSaleGroupInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("groupCode") String groupCode){
        
		PmSaleGroupInfoEntity entity = pmSaleGroupInfoService.queryObject(groupCode);
        return R.ok().put("pmSaleGroupInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmSaleGroupInfoEntity pmSaleGroupInfo){
        pmSaleGroupInfoService.insert(pmSaleGroupInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmSaleGroupInfoEntity pmSaleGroupInfo){
        
        pmSaleGroupInfoService.update(pmSaleGroupInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody String groupCode){
        pmSaleGroupInfoService.delete(groupCode);

        return R.ok();
    }

}
