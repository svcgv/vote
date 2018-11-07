package com.indihx.PmProjectIncommingInfo.controller;


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
import com.indihx.PmProjectIncommingInfo.entity.PmProjectIncommingInfoEntity;
import com.indihx.PmProjectIncommingInfo.service.PmProjectIncommingInfoService;
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
@RequestMapping("/pmprojectincomminginfo")
public class PmProjectIncommingInfoController {
    @Autowired
    private PmProjectIncommingInfoService pmProjectIncommingInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmProjectIncommingInfoEntity> pmProjectIncommingInfo = pmProjectIncommingInfoService.queryList(params);
        return R.ok().put("page", pmProjectIncommingInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("receiveId") long receiveId,HttpSession session){

		PmProjectIncommingInfoEntity entity = pmProjectIncommingInfoService.queryObject(receiveId);
        return R.ok().put("pmProjectIncommingInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProjectIncommingInfoEntity pmProjectIncommingInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectIncommingInfo.setCreatorId(usesr.getUsrId());
    	pmProjectIncommingInfo.setCreateTime(DateUtil.getDateTime());
        pmProjectIncommingInfoService.insert(pmProjectIncommingInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProjectIncommingInfoEntity pmProjectIncommingInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectIncommingInfo.setModifier(usesr.getUsrId());
    	pmProjectIncommingInfo.setModifyTime(DateUtil.getDateTime());
        pmProjectIncommingInfoService.update(pmProjectIncommingInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long receiveId,HttpSession session){
        pmProjectIncommingInfoService.delete(receiveId);
        return R.ok();
    }

}
