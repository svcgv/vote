package com.indihx.PmProjectFactMilestoneInfo.controller;


import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.UserUtil;
import com.indihx.PmProjectFactMilestoneInfo.entity.PmProjectFactMilestoneInfoEntity;
import com.indihx.PmProjectFactMilestoneInfo.service.PmProjectFactMilestoneInfoService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;

/**
 * ${comments}
 *
 * @author hb
 * @date 2018-11-26 16:50:57
 */
@Controller
@RequestMapping("/pmprojectfactmilestoneinfo")
public class PmProjectFactMilestoneInfoController {
    @Autowired
    private PmProjectFactMilestoneInfoService pmProjectFactMilestoneInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmProjectFactMilestoneInfoEntity> pmProjectFactMilestoneInfo = pmProjectFactMilestoneInfoService.queryList(params);
        return R.ok().put("page", pmProjectFactMilestoneInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("milestoneId") long milestoneId,HttpSession session){

		PmProjectFactMilestoneInfoEntity entity = pmProjectFactMilestoneInfoService.queryObject(milestoneId);
        return R.ok().put("pmProjectFactMilestoneInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProjectFactMilestoneInfoEntity pmProjectFactMilestoneInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectFactMilestoneInfo.setCreatorId(usesr.getUsrId());
    	pmProjectFactMilestoneInfo.setCreateTime(DateUtil.getDateTime());
        pmProjectFactMilestoneInfoService.insert(pmProjectFactMilestoneInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProjectFactMilestoneInfoEntity pmProjectFactMilestoneInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectFactMilestoneInfo.setModifier(usesr.getUsrId());
    	pmProjectFactMilestoneInfo.setModifyTime(DateUtil.getDateTime());
        pmProjectFactMilestoneInfoService.update(pmProjectFactMilestoneInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long milestoneId,HttpSession session){
        pmProjectFactMilestoneInfoService.delete(milestoneId);
        return R.ok();
    }

}
