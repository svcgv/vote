package com.indihx.PmProjectGroupInfo.controller;


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
import com.indihx.PmProjectGroupInfo.entity.PmProjectGroupInfoEntity;
import com.indihx.PmProjectGroupInfo.service.PmProjectGroupInfoService;
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
@RequestMapping("/pmprojectgroupinfo")
public class PmProjectGroupInfoController {
    @Autowired
    private PmProjectGroupInfoService pmProjectGroupInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmProjectGroupInfoEntity> pmProjectGroupInfo = pmProjectGroupInfoService.queryList(params);
        return R.ok().put("page", pmProjectGroupInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("projectGroupId") long projectGroupId,HttpSession session){

		PmProjectGroupInfoEntity entity = pmProjectGroupInfoService.queryObject(projectGroupId);
        return R.ok().put("pmProjectGroupInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProjectGroupInfoEntity pmProjectGroupInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectGroupInfo.setCreatorId(usesr.getUsrId());
    	pmProjectGroupInfo.setCreateTime(DateUtil.getDateTime());
        pmProjectGroupInfoService.insert(pmProjectGroupInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProjectGroupInfoEntity pmProjectGroupInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectGroupInfo.setModifier(usesr.getUsrId());
    	pmProjectGroupInfo.setModifyTime(DateUtil.getDateTime());
        pmProjectGroupInfoService.update(pmProjectGroupInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long projectGroupId,HttpSession session){
        pmProjectGroupInfoService.delete(projectGroupId);
        return R.ok();
    }

}
