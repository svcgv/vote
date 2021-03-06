package com.indihx.PmProjectProblem.controller;


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
import com.indihx.PmProjectProblem.entity.PmProjectProblemEntity;
import com.indihx.PmProjectProblem.service.PmProjectProblemService;
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
@RequestMapping("/pmprojectproblem")
public class PmProjectProblemController {
    @Autowired
    private PmProjectProblemService pmProjectProblemService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmProjectProblemEntity> pmProjectProblem = pmProjectProblemService.queryList(params);
        return R.ok().put("page", pmProjectProblem);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("problemId") long problemId,HttpSession session){

		PmProjectProblemEntity entity = pmProjectProblemService.queryObject(problemId);
        return R.ok().put("pmProjectProblem", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmProjectProblemEntity pmProjectProblem,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectProblem.setCreatorId(usesr.getUsrId());
    	pmProjectProblem.setCreateTime(DateUtil.getDateTime());
        pmProjectProblemService.insert(pmProjectProblem);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmProjectProblemEntity pmProjectProblem,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmProjectProblem.setModifier(usesr.getUsrId());
    	pmProjectProblem.setModifyTime(DateUtil.getDateTime());
        pmProjectProblemService.update(pmProjectProblem);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long problemId,HttpSession session){
        pmProjectProblemService.delete(problemId);
        return R.ok();
    }

}
