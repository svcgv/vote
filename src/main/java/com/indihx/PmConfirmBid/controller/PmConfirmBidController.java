package com.indihx.PmConfirmBid.controller;


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
import com.indihx.PmConfirmBid.entity.PmConfirmBidEntity;
import com.indihx.PmConfirmBid.service.PmConfirmBidService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * 
 *
 * @author hb
 * @date 2018-11-06 19:51:55
 */
@Controller
@RequestMapping("/pmconfirmbid")
public class PmConfirmBidController {
    @Autowired
    private PmConfirmBidService pmConfirmBidService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmConfirmBidEntity> pmConfirmBid = pmConfirmBidService.queryList(params);
        return R.ok().put("page", pmConfirmBid);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("bidId") long bidId,HttpSession session){

		PmConfirmBidEntity entity = pmConfirmBidService.queryObject(bidId);
        return R.ok().put("pmConfirmBid", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmConfirmBidEntity pmConfirmBid,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmConfirmBid.setCreatorId(usesr.getUsrId());
    	pmConfirmBid.setCreateTime(DateUtil.getDateTime());
        pmConfirmBidService.insert(pmConfirmBid);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmConfirmBidEntity pmConfirmBid,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmConfirmBid.setModifier(usesr.getUsrId());
    	pmConfirmBid.setModifyTime(DateUtil.getDateTime());
        pmConfirmBidService.update(pmConfirmBid);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long bidId,HttpSession session){
        pmConfirmBidService.delete(bidId);
        return R.ok();
    }

}
