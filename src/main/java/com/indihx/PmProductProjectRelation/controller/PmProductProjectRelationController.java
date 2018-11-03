package com.indihx.PmProductProjectRelation.controller;


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
import com.indihx.PmProductProjectRelation.entity.PmProductProjectRelationEntity;
import com.indihx.PmProductProjectRelation.service.PmProductProjectRelationService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * 
 *
 * @author hb
 * @date 2018-11-03 17:12:14
 */
@Controller
@RequestMapping("/pmproductprojectrelation")
public class PmProductProjectRelationController {
    @Autowired
    private PmProductProjectRelationService pmProductProjectRelationService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmProductProjectRelationEntity> pmProductProjectRelation = pmProductProjectRelationService.queryList(params);
        return R.ok().put("page", pmProductProjectRelation);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("productRelationId") long productRelationId,HttpSession session){

		PmProductProjectRelationEntity entity = pmProductProjectRelationService.queryObject(productRelationId);
        return R.ok().put("pmProductProjectRelation", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProductProjectRelationEntity pmProductProjectRelation,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProductProjectRelation.setCreatorId(usesr.getUsrId());
    	pmProductProjectRelation.setCreateTime(DateUtil.getDateTime());
        pmProductProjectRelationService.insert(pmProductProjectRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProductProjectRelationEntity pmProductProjectRelation,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProductProjectRelation.setModifier(usesr.getUsrId());
    	pmProductProjectRelation.setModifyTime(DateUtil.getDateTime());
        pmProductProjectRelationService.update(pmProductProjectRelation);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long productRelationId,HttpSession session){
        pmProductProjectRelationService.delete(productRelationId);
        return R.ok();
    }

}
