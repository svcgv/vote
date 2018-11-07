package com.indihx.PmProjectGroupRelationInfo.controller;


import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpSession;
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
import com.indihx.PmProjectGroupRelationInfo.entity.PmProjectGroupRelationInfoEntity;
import com.indihx.PmProjectGroupRelationInfo.service.PmProjectGroupRelationInfoService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * 
 *
 * @author hb
 * @date 2018-11-07 20:18:23
 */
@Controller
@RequestMapping("/pmprojectgrouprelationinfo")
public class PmProjectGroupRelationInfoController {
    @Autowired
    private PmProjectGroupRelationInfoService pmProjectGroupRelationInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmProjectGroupRelationInfoEntity> pmProjectGroupRelationInfo = pmProjectGroupRelationInfoService.queryList(params);
        return R.ok().put("page", pmProjectGroupRelationInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("relationship") long relationship,HttpSession session){

		PmProjectGroupRelationInfoEntity entity = pmProjectGroupRelationInfoService.queryObject(relationship);
        return R.ok().put("pmProjectGroupRelationInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProjectGroupRelationInfoEntity pmProjectGroupRelationInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectGroupRelationInfo.setCreatorId(usesr.getUsrId());
    	pmProjectGroupRelationInfo.setCreateTime(DateUtil.getDateTime());
        pmProjectGroupRelationInfoService.insert(pmProjectGroupRelationInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProjectGroupRelationInfoEntity pmProjectGroupRelationInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectGroupRelationInfo.setModifier(usesr.getUsrId());
    	pmProjectGroupRelationInfo.setModifyTime(DateUtil.getDateTime());
        pmProjectGroupRelationInfoService.update(pmProjectGroupRelationInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long relationship,HttpSession session){
        pmProjectGroupRelationInfoService.delete(relationship);
        return R.ok();
    }

}
