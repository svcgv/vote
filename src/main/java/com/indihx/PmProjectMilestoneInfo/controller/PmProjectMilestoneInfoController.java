package com.indihx.PmProjectMilestoneInfo.controller;


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
import com.indihx.PmProjectMilestoneInfo.entity.PmProjectMilestoneInfoEntity;
import com.indihx.PmProjectMilestoneInfo.service.PmProjectMilestoneInfoService;
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
@RequestMapping("/pmprojectmilestoneinfo")
public class PmProjectMilestoneInfoController {
    @Autowired
    private PmProjectMilestoneInfoService pmProjectMilestoneInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmProjectMilestoneInfoEntity> pmProjectMilestoneInfo = pmProjectMilestoneInfoService.queryList(params);
        return R.ok().put("page", pmProjectMilestoneInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("milestoneId") long milestoneId,HttpSession session){

		PmProjectMilestoneInfoEntity entity = pmProjectMilestoneInfoService.queryObject(milestoneId);
        return R.ok().put("pmProjectMilestoneInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProjectMilestoneInfoEntity pmProjectMilestoneInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectMilestoneInfo.setCreatorId(usesr.getUsrId());
    	pmProjectMilestoneInfo.setCreateTime(DateUtil.getDateTime());
        pmProjectMilestoneInfoService.insert(pmProjectMilestoneInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProjectMilestoneInfoEntity pmProjectMilestoneInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectMilestoneInfo.setModifier(usesr.getUsrId());
    	pmProjectMilestoneInfo.setModifyTime(DateUtil.getDateTime());
        pmProjectMilestoneInfoService.update(pmProjectMilestoneInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long milestoneId,HttpSession session){
        pmProjectMilestoneInfoService.delete(milestoneId);
        return R.ok();
    }

}
