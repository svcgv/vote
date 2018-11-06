package com.indihx.PmFile.controller;


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
import com.indihx.PmFile.entity.PmFileEntity;
import com.indihx.PmFile.service.PmFileService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * 
 *
 * @author hb
 * @date 2018-11-06 19:33:25
 */
@Controller
@RequestMapping("/pmfile")
public class PmFileController {
    @Autowired
    private PmFileService pmFileService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmFileEntity> pmFile = pmFileService.queryList(params);
        return R.ok().put("page", pmFile);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("fileId") long fileId,HttpSession session){

		PmFileEntity entity = pmFileService.queryObject(fileId);
        return R.ok().put("pmFile", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmFileEntity pmFile,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmFile.setCreatorId(usesr.getUsrId());
    	pmFile.setCreateTime(DateUtil.getDateTime());
        pmFileService.insert(pmFile);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmFileEntity pmFile,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmFile.setModifier(usesr.getUsrId());
    	pmFile.setModifyTime(DateUtil.getDateTime());
        pmFileService.update(pmFile);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long fileId,HttpSession session){
        pmFileService.delete(fileId);
        return R.ok();
    }

}
