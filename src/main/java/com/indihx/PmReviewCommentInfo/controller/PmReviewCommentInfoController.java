package com.indihx.PmReviewCommentInfo.controller;


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
import com.indihx.PmReviewCommentInfo.entity.PmReviewCommentInfoEntity;
import com.indihx.PmReviewCommentInfo.service.PmReviewCommentInfoService;
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
@RequestMapping("/pmreviewcommentinfo")
public class PmReviewCommentInfoController {
    @Autowired
    private PmReviewCommentInfoService pmReviewCommentInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmReviewCommentInfoEntity> pmReviewCommentInfo = pmReviewCommentInfoService.queryList(params);
        return R.ok().put("page", pmReviewCommentInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("commentId") long commentId,HttpSession session){

		PmReviewCommentInfoEntity entity = pmReviewCommentInfoService.queryObject(commentId);
        return R.ok().put("pmReviewCommentInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmReviewCommentInfoEntity pmReviewCommentInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmReviewCommentInfo.setCreatorId(usesr.getUsrId());
    	pmReviewCommentInfo.setCreateTime(DateUtil.getDateTime());
        pmReviewCommentInfoService.insert(pmReviewCommentInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmReviewCommentInfoEntity pmReviewCommentInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmReviewCommentInfo.setModifier(usesr.getUsrId());
    	pmReviewCommentInfo.setModifyTime(DateUtil.getDateTime());
        pmReviewCommentInfoService.update(pmReviewCommentInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long commentId,HttpSession session){
        pmReviewCommentInfoService.delete(commentId);
        return R.ok();
    }

}
