package com.indihx.PmConfirmBid.controller;


import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.indihx.PmReviewInfo.entity.PmReviewInfoEntity;
import com.indihx.PmReviewInfo.service.PmReviewInfoService;
import com.indihx.comm.util.RandomUtil;
import com.indihx.comm.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ReviewUtils;
import com.indihx.util.UserUtil;
import com.alibaba.fastjson.JSON;
import com.indihx.PmConfirmBid.entity.PmConfirmBidEntity;
import com.indihx.PmConfirmBid.service.PmConfirmBidService;
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
 * @date 2018-11-07 10:07:13
 */
@Controller
@RequestMapping("/pmconfirmbid")
public class PmConfirmBidController {
    @Autowired
    private PmConfirmBidService pmConfirmBidService;
    @Autowired
    private PmFileService pmFileService;
    @Autowired
    private PmReviewInfoService pmReviewInfoService;
    
    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody ResponseData list(@RequestBody Map<String, Object> params,HttpSession session){
    	String str = (String) params.get("queryStr");
    	Map<String,Object> maps = (Map<String,Object>)JSON.parse(str);
        return new ResponseData(pmConfirmBidService.queryList(maps,params.get("page")==null?null:(int)params.get("page"),params.get("limit")==null?null:(int)params.get("limit")));
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
        pmConfirmBid.setStatus("00");
        String bidCode = "TB"+DateUtil.getSysDate()+ RandomUtil.generateString(4);
        if(pmConfirmBid.getBidCode() == null) {
            pmConfirmBid.setBidCode(bidCode);
        }
        pmConfirmBidService.insert(pmConfirmBid);
        long id = pmConfirmBid.getBidId();
        if(pmConfirmBid.getFileIds()!=null&&!"".equals(pmConfirmBid.getFileIds())) {
        	String fileIds = pmConfirmBid.getFileIds();
        	String[] ids = fileIds.split(",");
        	PmFileEntity pm = new PmFileEntity();
        	pm.setForeignId(id);
        	for(int i=0;i<ids.length;i++) {
        		pm.setFileId(Long.parseLong(ids[i]));
        		pmFileService.update(pm);
        		
        	}
        }
        
        return R.ok();
    }

    /**
     * 保存并提交
     */
    @RequestMapping(value="/save2",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> saveAndConfirm(@RequestBody PmConfirmBidEntity pmConfirmBid,HttpSession session){
        UsrInfo usesr = UserUtil.getUser(session);
        pmConfirmBid.setCreatorId(usesr.getUsrId());
        pmConfirmBid.setCreateTime(DateUtil.getDateTime());
        pmConfirmBid.setStatus("01");
        String bidCode = "TB"+DateUtil.getSysDate()+ RandomUtil.generateString(4);
        if(pmConfirmBid.getBidCode() == null) {
            pmConfirmBid.setBidCode(bidCode);
        }
        pmConfirmBidService.insert(pmConfirmBid);
        long id = pmConfirmBid.getBidId();
        PmReviewInfoEntity reviewEntity = new PmReviewInfoEntity();
        reviewEntity.setForeignId(id);
        reviewEntity.setReviewType("00");
        reviewEntity.setReviewUserCode(pmConfirmBid.getConstructionDeptManagerId());
        reviewEntity.setReviewUserName(pmConfirmBid.getConstructionDeptManagerName());
//        reviewEntity.setCreatorId(usesr.getUsrId());
//        reviewEntity.setCreateTime(DateUtil.getDateTime());
        pmReviewInfoService.insert(reviewEntity);
        if(pmConfirmBid.getFileIds()!=null&&!"".equals(pmConfirmBid.getFileIds())) {
            String fileIds = pmConfirmBid.getFileIds();
            String[] ids = fileIds.split(",");
            PmFileEntity pm = new PmFileEntity();
            pm.setForeignId(id);
            for(int i=0;i<ids.length;i++) {
                pm.setFileId(Long.parseLong(ids[i]));
                pmFileService.update(pm);

            }
        }

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
        if(pmConfirmBid.getFileIds()!=null && !"".equals(pmConfirmBid.getFileIds())) {
        	String fileIds = pmConfirmBid.getFileIds();
        	String[] ids = fileIds.split(",");
        	PmFileEntity pm = new PmFileEntity();
        	pm.setForeignId(pmConfirmBid.getBidId());
        	for(int i=0;i<ids.length;i++) {
        		pm.setFileId(Long.parseLong(ids[i]));
        		pmFileService.update(pm);
        		
        	}
        }
        return R.ok();
    }

    /**
     * 确认审批
     */
    @RequestMapping(value="/submit",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> submit(@RequestBody PmConfirmBidEntity pmConfirmBid,HttpSession session){
        UsrInfo usesr = UserUtil.getUser(session);
        pmConfirmBid.setModifier(usesr.getUsrId());
        pmConfirmBid.setModifyTime(DateUtil.getDateTime());
        PmConfirmBidEntity entity = pmConfirmBidService.queryObject(pmConfirmBid.getBidId());
        pmConfirmBid.setStatus( ReviewUtils.getNextState(entity.getStatus(),"00"));
        long id = pmConfirmBid.getBidId();
        //插入投标审批记录
        PmReviewInfoEntity reviewEntity = new PmReviewInfoEntity();
        if(!(StringUtils.hasLength(pmConfirmBid.getTechnicalDirectorName())&&StringUtils.hasLength(pmConfirmBid.getConstructionDeptManagerName())&& StringUtils.hasLength(pmConfirmBid.getSellDeptManagerName()))) {
        	return R.error("请选择评审人");
        }
       
        reviewEntity.setForeignId(id);
        reviewEntity.setReviewType("00");
        reviewEntity.setReviewUserCode(pmConfirmBid.getConstructionDeptManagerId());
        reviewEntity.setReviewUserName(pmConfirmBid.getConstructionDeptManagerName());
//        reviewEntity.setCreatorId(usesr.getUsrId());
//        reviewEntity.setCreateTime(DateUtil.getDateTime());
        pmReviewInfoService.insert(reviewEntity);
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

    /**
     * 提交评审
     */
    @RequestMapping(value="/startReview",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> startReview(@RequestBody PmConfirmBidEntity pmConfirmBid,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmConfirmBid.setModifier(usesr.getUsrId());
    	pmConfirmBid.setModifyTime(DateUtil.getDateTime());
        pmConfirmBidService.update(pmConfirmBid);//全部更新
        return R.ok();
    }
}
