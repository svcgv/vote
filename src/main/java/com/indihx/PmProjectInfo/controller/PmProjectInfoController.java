package com.indihx.PmProjectInfo.controller;


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
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.PmProjectInfoService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * 
 *
 * @author hb
 * @date 2018-11-09 10:04:00
 */
@Controller
@RequestMapping("/pmprojectinfo")
public class PmProjectInfoController {
    @Autowired
    private PmProjectInfoService pmProjectInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmProjectInfoEntity> pmProjectInfo = pmProjectInfoService.queryList(params);
        return R.ok().put("page", pmProjectInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("projectId") long projectId,HttpSession session){

		PmProjectInfoEntity entity = pmProjectInfoService.queryObject(projectId);
        return R.ok().put("pmProjectInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProjectInfoEntity pmProjectInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectInfo.setCreatorId(usesr.getUsrId());
    	pmProjectInfo.setCreateTime(DateUtil.getDateTime());
        pmProjectInfoService.insert(pmProjectInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProjectInfoEntity pmProjectInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectInfo.setModifier(usesr.getUsrId());
    	pmProjectInfo.setModifyTime(DateUtil.getDateTime());
        pmProjectInfoService.update(pmProjectInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long projectId,HttpSession session){
        pmProjectInfoService.delete(projectId);
        return R.ok();
    }

}
