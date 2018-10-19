/**
 * 
 */
package com.indihx.elecvote.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.comm.InitSysConstants;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.entity.VoteTopicInfo;
import com.indihx.elecvote.entity.VoteWorkspace;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.elecvote.service.SectManageService;
import com.indihx.elecvote.service.VoteApplyService;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: VoteApplyController.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年7月10日下午5:29:38</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>VoteApplyController.java</p>
 * 表决申请主体功能
 * 1、创建表决议题，包括表决起始日期、表决议题等，表决状态：拟制表决议题
 * 2、如果议题需要关联维修工程，则创建并关联维修工程（严格验证表单，如果选择是则必须关联工程后才能提交下一步）,表决状态：拟制表决议题
 * 3、关联表决范围，即选择投票表决涉及的房屋,表决状态：待确定表决范围
 * 4、发布表决议题，表决状态：表决中
 * 5、表决时间到期，表决状态：表决完结，使用部也可以关闭已发布的表决议题（已发布的议题用户可以停止表决，状态更为已注销），同时可以提前（表决时间未到截止日期，用户确定完结）完结表决。
 */
@Controller
@RequestMapping("/apply")
public class VoteApplyController {
	
	private Logger logger = LoggerFactory.getLogger(VoteApplyController.class);
	@Autowired
	private VoteApplyService voteApplyService;
	@Autowired
	private SectManageService sectManageService;
	@Autowired
	private HouseManageService houseManageService;
	
	@RequestMapping("/index")
	public String getApplyIndex(HttpSession session)
	{
		return "/elecvote/apply/applyInfoIndex";
	}
	
	@RequestMapping("/addApplyTopic")
	public String addApplyTopic()
	{
		return "/elecvote/apply/addApplyTopicInfo";
	}
	
	@RequestMapping("/addApplyTopicInfo")
	public @ResponseBody Map<String, Object> addApplyTopicInfo(HttpSession session, @RequestBody Map<String, Object> reqeustMap)
	{
		logger.debug("hello addApplyInfo");
		try {
			logger.debug("requestMap: "+ new ObjectMapper().writeValueAsString(reqeustMap));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("josn error: ", e);
		}
		Map<String, Object> map = voteApplyService.addApplyInfo(reqeustMap);
		
		
		return map;
	}
	
	@RequestMapping("/addApplyProject")
	public ModelAndView addApplyProject(HttpSession session)
	{
		List<Map<String,String>> list = new ArrayList<Map<String, String>>();
		list = sectManageService.getSectIdAndName();
		ModelAndView view = new ModelAndView();
		view.setViewName("/elecvote/apply/addApplyProjectInfo");
		try {
			view.addObject("list", new ObjectMapper().writeValueAsString(list));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("json error: ", e);
		}
		
		return view;
	}
	
	@RequestMapping("/addApplyProjectInfo")
	public @ResponseBody Map<String, Object> addApplyProjectInfo(HttpSession session, @RequestBody Map<String, Object> requestMap)
	{
		logger.debug("hello addApplyProjectInfo");
		try {
			logger.debug("requestMap: "+ new ObjectMapper().writeValueAsString(requestMap));
		} catch (Exception e) {
			// TODO: handle exception
		}
		Map<String, Object> map = voteApplyService.addApplyInfo(requestMap);
		
		return map;
	}
	
	@RequestMapping("/getApplyInfo")
	public @ResponseBody Map<String, Object> getApplyInfo(HttpSession session, @RequestBody Map<String, Object> requestMap)
	{
		logger.debug("hello getApplyInfo");
		Map<String, Object> map = voteApplyService.getApplyInfo(requestMap);
		return map;
	}
	
	@RequestMapping("/editApplyTopic")
	public ModelAndView editApplyTopic(@RequestParam String topicId)
	{
		ModelAndView view = new ModelAndView("/elecvote/apply/editApplyTopicInfo");
		VoteTopicInfo voteTopicInfo = voteApplyService.getTopicInfoById(new BigDecimal(topicId));
		view.addObject("topic", voteTopicInfo);
		return view;
	}
	
	@RequestMapping("/editApplyTopicInfo")
	public @ResponseBody Map<String, Object> editApplyTopicInfo(HttpSession session, @RequestBody VoteTopicInfo voteTopicInfo)
	{
		logger.debug("hello editApplyTopicInfo");
		Map<String, Object> map = voteApplyService.updateApplyInfo(voteTopicInfo);
		return map;
	}
	
	@RequestMapping("/delApplyInfo")
	public @ResponseBody Map<String, Object> delApplyInfo(HttpSession session, @RequestBody Map<String, Object> requestMap)
	{
		logger.debug("hello delApplyInfo");
		//String topicId = (String)requestMap.get("topicId");
		Map<String, Object> map = voteApplyService.delApplyInfo(requestMap);
		return map;
	}
	
	@RequestMapping("/linkVoteTopicBefore")
	public ModelAndView  linkVoteTopicBefore(HttpSession session, @RequestParam String topicId)
	{
		logger.debug("hello linkVoteTopicBefore");
		Map<String, Object> map = voteApplyService.checkTopicStatus(topicId);
		ModelAndView view = null;
		if(map.get("status").equals(InitSysConstants.VoteStatus_BiaoJueWanJie)) {
			view  = new ModelAndView("/error/error");
			view.addObject("message","议题已经过期，不能关联投票分户");
		}else {
			session.setAttribute("topicId", topicId);
			view  = new ModelAndView("/elecvote/apply/linkTopicHouseInfo");
			view.addObject("topic", map.get("topic"));
		}
		
		return view;
	}
	
	@RequestMapping("/unlinkVoteTopicBefore")
	public ModelAndView  unlinkVoteTopicBefore(HttpSession session, @RequestParam String topicId)
	{
		logger.debug("hello unlinkVoteTopicBefore");
		Map<String, Object> map = voteApplyService.checkTopicStatus(topicId);
		ModelAndView view = null;
		if(map.get("status").equals(InitSysConstants.VoteStatus_BiaoJueWanJie)) {
			view  = new ModelAndView("/error/error");
			view.addObject("message","议题已经过期，不能关联投票分户");
		}else {
			session.setAttribute("topicId", topicId);
			view  = new ModelAndView("/elecvote/apply/unlinkTopicHouseInfo");
			view.addObject("topic", map.get("topic"));
		}
		
		return view;
	}
	
	@RequestMapping("/linkVoteTopic")
	public ModelAndView  linkVoteTopic(HttpSession session, VoteTopicInfo voteTopicInfo, String sectName)
	{
		logger.debug("hello linkVoteTopic");
		ModelAndView view  = new ModelAndView("/elecvote/apply/linkHouseInfo");
		view.addObject("sectName", sectName);
		return view;
	}
	
	@RequestMapping("/linkProject")
	public ModelAndView  linkProject(HttpSession session, VoteWorkspace requestMap, String sectName)
	{
		logger.debug("hello linkProject");
		try {
			logger.debug("requestMap: "+ new ObjectMapper().writeValueAsString(requestMap));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("json error: ", e);
		}
		//VoteHouseInfo voteHouseInfo = houseManageService.getHouseInfo(null, sectName, currentPage, pageSize)
		ModelAndView view  = new ModelAndView("/elecvote/apply/linkHouseInfo");
		view.addObject("sectName", sectName);
		return view;
	}
	
	//关联投票分户
	@RequestMapping("/linkTopicAndHouseInfo")
	//public @ResponseBody Map<String, Object> linkTopicAndHouseInfo(HttpSession session, @RequestBody List<VoteHouseInfo> list)
	public @ResponseBody Map<String, Object> linkTopicAndHouseInfo(HttpSession session, @RequestBody Map<String, Object> requestMap)
	{
		logger.debug("hello linkTopicAndHouseInfo");
		try {
			logger.debug("requestMap: "+ new ObjectMapper().writeValueAsString(requestMap));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("json error: ");
			return null;
		}
		String topicId = (String) session.getAttribute("topicId");
		logger.debug("topicId: "+ topicId);
		Map<String, Object> map = voteApplyService.linkTopicAndHouseInfo(topicId, requestMap);
		
		return map;
	}
	//解除关联投票分户
	@RequestMapping("/unlinkTopicAndHouseInfo")
	public @ResponseBody Map<String, Object> unlinkTopicAndHouseInfo(HttpSession session, @RequestBody Map<String, Object> requestMap)
	{
		logger.debug("hello unlinkTopicAndHouseInfo");
		try {
			logger.debug("requestMap: "+ new ObjectMapper().writeValueAsString(requestMap));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("json error: ");
			return null;
		}
		String topicId = (String) session.getAttribute("topicId");
		logger.debug("topicId: "+ topicId);
		Map<String, Object> map = voteApplyService.unlinkTopicAndHouseInfo(topicId, requestMap);
		
		return map;
	}
	
	//开始投票
	@RequestMapping("startvote")
	public @ResponseBody Map<String, Object> startVote(@RequestBody Map<String, Object> requestMap){
		logger.debug("hello startvote");
		Map<String, Object> map = voteApplyService.startVote((String)requestMap.get("topicId"));
		
		return map;
	}
	
	//结束投票
	@RequestMapping("endvote")
	public @ResponseBody Map<String, Object> endVote(@RequestBody Map<String, Object> requestMap){
		logger.debug("hello startvote");
		Map<String, Object> map = voteApplyService.endVote((String)requestMap.get("topicId"));
		
		return map;
	}
}
