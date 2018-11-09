package com.indihx.PmReviewInfo.controller;


import java.util.Map;
import java.util.HashMap;
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
import com.indihx.PmReviewInfo.entity.PmReviewInfoEntity;
import com.indihx.PmReviewInfo.service.PmReviewInfoService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * 
 *
 * @author hb
 * @date 2018-11-08 21:43:00
 */
@Controller
@RequestMapping("/pmreviewinfo")
public class PmReviewInfoController {
    @Autowired
    private PmReviewInfoService pmReviewInfoService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmReviewInfoEntity> pmReviewInfo = pmReviewInfoService.queryList(params);
        return R.ok().put("page", pmReviewInfo);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("reviewId") long reviewId,HttpSession session){

		PmReviewInfoEntity entity = pmReviewInfoService.queryObject(reviewId);
        return R.ok().put("pmReviewInfo", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmReviewInfoEntity pmReviewInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmReviewInfo.setCreatorId(usesr.getUsrId());
    	pmReviewInfo.setCreateTime(DateUtil.getDateTime());
        pmReviewInfoService.insert(pmReviewInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmReviewInfoEntity pmReviewInfo,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmReviewInfo.setModifier(usesr.getUsrId());
    	pmReviewInfo.setModifyTime(DateUtil.getDateTime());
        pmReviewInfoService.update(pmReviewInfo);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long reviewId,HttpSession session){
        pmReviewInfoService.delete(reviewId);
        return R.ok();
    }
    
    /**
     * 列表
     * 审批类型reviewType必传
     */
    @RequestMapping(value="/currentList",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> currentList(@RequestBody Map<String, Object> params,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	params.put("reviewUserCode", usesr.getUsrId());
		List<PmReviewInfoEntity> pmReviewInfo = pmReviewInfoService.queryList(params);
        return R.ok().put("page", pmReviewInfo);
    }

    /**
     * 获取当前用户的审批列表
     */
    @RequestMapping(value="/selectBidReview",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> selectBidReview(@RequestBody Map<String, Object> params,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	Map<String,Object> par = new HashMap<String,Object>();
    	par.put("reviewUserCode", usesr.getUsrId());
    	par.put("reviewType", "00");
		List<PmReviewInfoEntity> pmReviewInfo = pmReviewInfoService.queryList(par);
        return R.ok().put("page", pmReviewInfo);
    }

    
}
