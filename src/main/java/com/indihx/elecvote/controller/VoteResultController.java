/**
 * 
 */
package com.indihx.elecvote.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.comm.InitSysConstants;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.entity.VoteResultSum;
import com.indihx.elecvote.entity.VoteTopicInfo;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.elecvote.service.VoteApplyService;
import com.indihx.elecvote.service.VoteResultService;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: VoteResultController.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年7月10日下午5:24:29</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>VoteResultController.java</p>
 * 电子表决或手动补录表决结果
 * ---电子表决
 * 1、查询待表决议题
 * 2、保存电子表决结果
 * 2、查询已表决议题
 * 3、查看表决结果详情
 * 4、同步更新表决汇总信息表
 * ---手动补录表决结果
 * 1、查询表决议题（同电子表决）
 * 2、手动补录表决录入，并保存表决结果
 * 3、查看一表决议题（同电子表决）
 * 4、查看表决结果详情（同电子表决）
 * 5、同步更新表决汇总信息表（同电子表决）
 */
@Controller
@RequestMapping("/result")
public class VoteResultController extends AbstractBaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private VoteResultService  voteResultService;
	@Autowired
	private HouseManageService houseManageService;
	@Autowired
	private VoteApplyService voteApplyService;
	
	@RequestMapping("/index")
	public String getResultIndex() {
		return "/elecvote/result/resultInfoIndex";
	}
	
	@RequestMapping("getTopicSumInfo")
	public ModelAndView getTopicSumInfo(@RequestParam("topicId") String topicId) {
		ModelAndView view = new ModelAndView("/elecvote/result/resultSumInfo");
		VoteResultSum result = voteResultService.getTopicSumInfo(topicId);
		view.addObject("result", result);
		
		VoteTopicInfo topic = voteApplyService.getTopicInfoById(new BigDecimal(topicId));
		
		view.addObject("topic", topic);
		return view;
	}
	
	@RequestMapping("getVoteList")
	public ModelAndView getVoteList(@RequestParam("topicId") String topicId) {
		ModelAndView view  = new ModelAndView("/elecvote/result/voteListInfo");
		view.addObject("topicId", topicId);
		VoteTopicInfo topic = voteApplyService.getTopicInfoById(new BigDecimal(topicId));
		
		view.addObject("topic", topic);
		return view;
	}
	
	@RequestMapping("getVoteListInfo")
	public @ResponseBody Map<String, Object> getVoteListInfo(@RequestBody Map<String, Object> requestMap,HttpServletRequest request){
		logger.debug("context path: "+request.getContextPath());
		String topicId = (String)requestMap.get("topicId");
		String currPage = (String)requestMap.get("currPage");
		Map<String, Object> map = voteResultService.getVoteList(topicId, currPage,request);
		return map;
	}
	
	@RequestMapping("getUnvoteList")
	public ModelAndView getUnvoteList(@RequestParam("topicId") String topicId) {
		VoteTopicInfo topic = voteApplyService.getTopicInfoById(new BigDecimal(topicId));
		if (topic.getEffectiveStatus().equals(InitSysConstants.Vote_NONEFFECTIVE)) {
			ModelAndView  v = new ModelAndView("/error/error");
			v.addObject("message", "该议题无效，不能补录");
			return v;
		}else {
			if (!topic.getVoteStatus().equals(InitSysConstants.VoteStatus_BiaoJueWanJie)) {
				ModelAndView  v = new ModelAndView("/error/error");
				v.addObject("message", "该议题还没有表决完成，不能补录");
				return v;
			}
		}
		
		ModelAndView view  = new ModelAndView("/elecvote/result/unvoteListInfo");
		view.addObject("topicId", topicId);
		return view;
	}
	@RequestMapping("getUnvoteListInfo")
	public @ResponseBody Map<String, Object> getUnvoteListInfo(@RequestBody Map<String, Object> requestMap){
		String topicId = (String)requestMap.get("topicId");
		String currPage = (String)requestMap.get("currPage");
		Map<String, Object> map = voteResultService.getUnvoteList(requestMap);
		return map;
	}
	
	@RequestMapping("checkin")
	public  ModelAndView  checkin(@RequestParam("topicId") String topicId, 
			@RequestParam("infoId") String infoId){
		VoteHouseInfo  house = houseManageService.getHouseInfoById(new BigDecimal(infoId));
		VoteTopicInfo topic = voteApplyService.getTopicInfoById(new BigDecimal(topicId));
		if (topic.getEffectiveStatus().equals(InitSysConstants.Vote_NONEFFECTIVE)) {
			ModelAndView  v = new ModelAndView("/error/error");
			v.addObject("message", "该议题无效，不能补录");
			return v;
		}else {
			if (!topic.getVoteStatus().equals(InitSysConstants.VoteStatus_BiaoJueWanJie)) {
				ModelAndView  v = new ModelAndView("/error/error");
				v.addObject("message", "该议题还没有表决完成，不能补录");
				return v;
			}
		}
		ModelAndView view = new ModelAndView("/elecvote/result/checkin");
		view.addObject("house", house);
		view.addObject("topic", topic);
		return view;
	}
	
	@RequestMapping("checkinSubmit")
	public @ResponseBody Map<String, Object> submit(@RequestParam("file") MultipartFile[] myfiles,
			@RequestParam("infoId") String infoId, @RequestParam("topicId") String topicId,
			@RequestParam("result") String result) {
		
		Map<String, Object> map = voteResultService.submit(myfiles, topicId, infoId, result);
		return map;
	}
	
	@RequestMapping("downloadfile")
	public ResponseEntity<byte[]> download(@RequestParam("result") String result){
		
		ResponseEntity<byte[]> response = voteResultService.download(result);
		
		return response; 
	}
}
