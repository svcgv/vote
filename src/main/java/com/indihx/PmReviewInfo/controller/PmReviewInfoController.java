package com.indihx.PmReviewInfo.controller;


import java.util.Map;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.util.StringUtils;

import com.indihx.system.entity.UsrInfo;
import com.indihx.util.BeanUtils;
import com.indihx.util.ReviewUtils;
import com.indihx.util.UserUtil;
import com.alibaba.fastjson.JSON;
import com.indihx.PmConfirmBid.entity.PmConfirmBidEntity;
import com.indihx.PmConfirmBid.service.PmConfirmBidService;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.PmProjectInfoService;
import com.indihx.PmReviewInfo.entity.PmReviewInfoEntity;
import com.indihx.PmReviewInfo.service.PmReviewInfoService;
import com.indihx.baseTableUtil.entity.QueryUsrInfoEntity;
import com.indihx.baseTableUtil.service.QueryUsrInfoService;
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
    @Autowired
    private PmConfirmBidService pmConfirmBidService;
    @Autowired
    private PmProjectInfoService pmProjectInfoService;
    @Autowired
    private QueryUsrInfoService usrInfoService;
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
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    @RequestMapping(value="/selectBidReview",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> selectBidReview(@RequestBody Map<String, Object> params,HttpSession session) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
    	String str = (String) params.get("queryStr");
    	Map<String,Object> par = (Map<String,Object>)JSON.parse(str);
    	PmReviewInfoEntity entity = BeanUtils.Map2Bean(par, PmReviewInfoEntity.class);
    	UsrInfo usesr = UserUtil.getUser(session);
    	par.put("reviewUserCode", usesr.getUsrId());
    	par.put("reviewType", "00");
    	
		List<Map<String, Object>> pmReviewInfo = pmReviewInfoService.selectBidReview(par);
        return R.ok().put("page", pmReviewInfo);
    }
    
    @RequestMapping(value="/selectProjectReview",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> selectProjectReview(@RequestBody Map<String, Object> params,HttpSession session){
    	String str = (String) params.get("queryStr");
    	Map<String,Object> par = (Map<String,Object>)JSON.parse(str);
    	UsrInfo usesr = UserUtil.getUser(session);
    	par.put("reviewUserCode", usesr.getUsrId());
    	par.put("reviewType", "01");
    	List<Map<String, Object>> pmReviewInfo = pmReviewInfoService.selectProjectReview(par);
        return R.ok().put("page", pmReviewInfo);
    }

    /**
     * 修改
     */
    @RequestMapping(value="/submit",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> submit(@RequestBody PmReviewInfoEntity pmReviewInfo,HttpSession session){
    	 UsrInfo usesr = UserUtil.getUser(session);
    	 //创建一个新的对象用于插入下一条审批数据
    	 PmReviewInfoEntity reviewEntity = new PmReviewInfoEntity();
         //获取当前数据库的审批数据
    	 PmReviewInfoEntity base = pmReviewInfoService.queryObject(pmReviewInfo.getReviewId());
    	 String result = pmReviewInfo.getResult();
    	 base.setResult(result);
    	 if(pmReviewInfo.getCommentDetail()!=null) {
    		 base.setCommentDetail(pmReviewInfo.getCommentDetail());
    	 }
    	 base.setModifier(usesr.getUsrId());
    	 base.setModifyTime(DateUtil.getDateTime());
    	 //更新审批意见，结果
    	 pmReviewInfoService.update(base);
    	 
    	 reviewEntity.setCreatorId(usesr.getUsrId());
    	 reviewEntity.setCreateTime(DateUtil.getDateTime());
    	 reviewEntity.setReviewType(pmReviewInfo.getReviewType());
    	 reviewEntity.setForeignId(base.getForeignId());
    	 
    	 
    	 if("00".equals(pmReviewInfo.getReviewType())) {
    		 PmConfirmBidEntity pmConfirmBid = pmConfirmBidService.queryObject(base.getForeignId());
    		 String state = ReviewUtils.getNextState(pmConfirmBid.getStatus(),pmReviewInfo.getResult());
    		 pmConfirmBid.setStatus(state );
    		 pmConfirmBidService.update(pmConfirmBid);
    		 //若未拒绝且未完成则提交到下个人手上
    		 if(!("00".equals(state)||"04".equals(state))) {
    			 if("02".equals(state)) {
    				 reviewEntity.setReviewUserCode(pmConfirmBid.getSellDeptManagerId());
    				 reviewEntity.setReviewUserName(pmConfirmBid.getSellDeptManagerName());
    			 }
    			 else {
    				 reviewEntity.setReviewUserCode(pmConfirmBid.getTechnicalDirectorId());
    				 reviewEntity.setReviewUserName(pmConfirmBid.getTechnicalDirectorName());
    			 }
    			 pmReviewInfoService.insert(reviewEntity);
    		 }
    		 else {
    			 base.setIsDelete("01");
    			 pmReviewInfoService.update(base);
    		 }
    	 }
    	 
    	 
    	 if("01".equals(pmReviewInfo.getReviewType())) {
    		 PmProjectInfoEntity pmProjectInfoEntity = pmProjectInfoService.queryObject(base.getForeignId());
    		 String approveStatus = ReviewUtils.getProjectNextState(pmProjectInfoEntity.getApproveStatus(),pmReviewInfo.getResult(),pmProjectInfoEntity);
    		 pmProjectInfoEntity.setApproveStatus(approveStatus);
    		 pmProjectInfoService.update(pmProjectInfoEntity);
    		 
    		 if(!("00".equals(approveStatus)||"04".equals(approveStatus))) {
    			 if("02".equals(approveStatus)) {
    				 reviewEntity.setReviewUserCode(pmProjectInfoEntity.getSellManagerId());
    				 reviewEntity.setReviewUserName(pmProjectInfoEntity.getSellManagerName());
    			 }
    			 
    			 
    			 if("03".equals(approveStatus)) {
    				 List<BigDecimal> list = new ArrayList<BigDecimal>();
    				 list.add(new BigDecimal(1000));
    				 Map<String,Object> queryParam = new HashMap<String,Object>();
    			     queryParam.put("orgList", list);
    				 queryParam.put("roleCode", "MAIN_MANAGER");
    				 List<QueryUsrInfoEntity> list1= usrInfoService.queryUserByRoleCodeUnderOrgNo(queryParam);
    				 if(list1.size()>0) {
    					 reviewEntity.setReviewUserCode(list1.get(0).getUsrId());
        				 reviewEntity.setReviewUserName(list1.get(0).getUsrName());
    				 }
    			 }
    			 
    			 pmReviewInfoService.insert(reviewEntity);
    		 } else {
    			 base.setIsDelete("01");
    			 pmReviewInfoService.update(base);
    		 }
    		 
    	 }
         
         return R.ok();
    }
    
}
