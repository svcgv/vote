package com.indihx.elecvote.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.elecvote.dao.VoteApplyInfoMapper;
import com.indihx.elecvote.dao.VoteHouseInfoMapper;
import com.indihx.elecvote.dao.VoteResultSumMapper;
import com.indihx.elecvote.dao.VoteScopeInfoMapper;
import com.indihx.elecvote.dao.VoteTopicInfoMapper;
import com.indihx.elecvote.dao.VoteWorkspaceMapper;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.entity.VoteHouseInfoExample;
import com.indihx.elecvote.entity.VoteResultSum;
import com.indihx.elecvote.entity.VoteScopeInfo;
import com.indihx.elecvote.entity.VoteScopeInfoExample;
import com.indihx.elecvote.entity.VoteTopicInfo;
import com.indihx.elecvote.entity.VoteTopicInfoExample;
import com.indihx.elecvote.entity.VoteTopicInfoExample.Criteria;
import com.indihx.elecvote.entity.VoteWorkspace;
import com.indihx.elecvote.service.VoteApplyService;
import com.indihx.util.StringUtils;


@Service
public class VoteApplyServiceImpl implements VoteApplyService {
	Logger logger = LoggerFactory.getLogger(VoteApplyServiceImpl.class);
	@Autowired
	private PrimaryKey  primaryKey;
	@Autowired
	private VoteApplyInfoMapper voteApplyInfoMapper;
	@Autowired
	private VoteTopicInfoMapper voteTopicInfoMapper;
	@Autowired
	private VoteScopeInfoMapper voteScopeInfoMapper;
	@Autowired
	private VoteHouseInfoMapper voteHouseInfoMapper;
	@Autowired
	private VoteWorkspaceMapper voteWorkspaceMapper;
	@Autowired
	private VoteResultSumMapper voteResultSumMapper;

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Map<String, Object> addApplyInfo(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("status", false);
		VoteTopicInfo voteTopicInfo =  new VoteTopicInfo();
		voteTopicInfo.setTopicId(new BigDecimal(primaryKey.getKeyBySequenceName("TOPIC_ID_SEQ")));
		voteTopicInfo.setTopicName((String)requestMap.get("topicName"));
		voteTopicInfo.setTopicContent((String)requestMap.get("topicContent"));
		voteTopicInfo.setVoteStartDate(DateUtil.format2DB((String)requestMap.get("voteStartDate")));
		voteTopicInfo.setVoteEndDate(DateUtil.format2DB((String)requestMap.get("voteEndDate")));
		voteTopicInfo.setSumHs(new BigDecimal(0));
		voteTopicInfo.setSumArea(new BigDecimal(0));
		voteTopicInfo.setEffectiveStatus(InitSysConstants.VOTE_EFFECTIVE);
		String linkFlag = (String)requestMap.get("linkFlag");
		if (linkFlag != null && !linkFlag.equals("")) {//关联维修工程		
			//增加维修工程信息到vote_workspace表
			VoteWorkspace voteWorkspace = new VoteWorkspace();
			voteWorkspace.setWsId(new BigDecimal(primaryKey.getKeyBySequenceName("WS_ID_SEQ")));
			voteWorkspace.setWsName((String)requestMap.get("wsName"));
			voteWorkspace.setRepairContent((String)requestMap.get("repairContent"));
			voteWorkspace.setRepairReason((String)requestMap.get("repairReason"));
			voteWorkspace.setTotalAmt(new BigDecimal((String)requestMap.get("totalAmt")));
			voteWorkspace.setCreateDate(DateUtil.getSysDate());
			voteWorkspace.setCreateTime(DateUtil.getSysTime());
			voteWorkspace.setWsStage(InitSysConstants.WsStatus_ZhengChang);
			voteWorkspace.setSectId(new BigDecimal((String)requestMap.get("sectId")));
			
			voteWorkspaceMapper.insertSelective(voteWorkspace);
			
			voteTopicInfo.setLinkFlag(linkFlag);
			voteTopicInfo.setWsId(voteWorkspace.getWsId());
			voteTopicInfo.setSectId(voteWorkspace.getSectId());
		}
			
		voteTopicInfo.setCreateDate(DateUtil.getSysDate());
		voteTopicInfo.setCreateTime(DateUtil.getSysTime());
		voteTopicInfo.setVoteStatus(InitSysConstants.VoteStatus_QueDingBiaoJueFanWei);
		
		VoteResultSum voteResultSum = new VoteResultSum();
		voteResultSum.setSumCount(0L);
		voteResultSum.setSumArea(new BigDecimal(0));
		voteResultSum.setTopicId(voteTopicInfo.getTopicId());
		voteResultSum.setAgreeHou(0L);
		voteResultSum.setAgreeHouRate(new BigDecimal(0));
		voteResultSum.setAgreeArea(new BigDecimal(0));
		voteResultSum.setAgreeAreaRate(new BigDecimal(0));
		voteResultSum.setUnagreeHou(0L);
		voteResultSum.setUnagreeHouRate(new BigDecimal(0));
		voteResultSum.setUnagreeArea(new BigDecimal(0));
		voteResultSum.setUnagreeAreaRate(new BigDecimal(0));
		voteResultSum.setQuitHou(0L);
		voteResultSum.setQuitHouRate(new BigDecimal(0));
		voteResultSum.setQuitArea(new BigDecimal(0));
		voteResultSum.setQuitAreaRate(new BigDecimal(0));
		voteResultSum.setNonvoteHou(0L);
		voteResultSum.setNonvoteHouRate(new BigDecimal(0));
		voteResultSum.setNonvoteArea(new BigDecimal(0));
		voteResultSum.setNonvoteAreaRate(new BigDecimal(0));
		voteResultSumMapper.insertSelective(voteResultSum);
		//这个地方要新插入vote_result_sum一条数据
		int count = voteTopicInfoMapper.insertSelective(voteTopicInfo);
		if (count > 0){
			map.put("status", true);
		}
			
		return map;
	}

	@Override
	public Map<String, Object> getApplyInfo(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		try {
			VoteTopicInfoExample voteTopicInfoExample = new VoteTopicInfoExample();
			Criteria criteria = voteTopicInfoExample.createCriteria();
			if(!StringUtils.isEmpty((String)requestMap.get("topicName"))){
				criteria.andTopicNameLike((String)requestMap.get("topicName"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("voteStatus"))){
				criteria.andVoteStatusEqualTo((String)requestMap.get("voteStatus"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("voteStartDate"))) {
				criteria.andCreateDateGreaterThanOrEqualTo(DateUtil.format2DB((String)(requestMap.get("voteStartDate"))));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("voteEndDate"))) {
				criteria.andCreateDateLessThanOrEqualTo(DateUtil.format2DB((String)(requestMap.get("voteEndDate"))));
			}
			voteTopicInfoExample.setOrderByClause("create_date asc");
			int currentPage = 1;
			if (!StringUtils.isEmpty((String)requestMap.get("pages"))) {
				currentPage = Integer.parseInt((String)requestMap.get("pages"));
			}
			PageHelper.startPage(currentPage, InitSysConstants.BIG_SIZE);
			List<VoteTopicInfo> listInfo = voteTopicInfoMapper.selectByExample(voteTopicInfoExample);
			for (VoteTopicInfo topic: listInfo) {
				if (topic.getVoteStatus().equals(InitSysConstants.VoteStatus_QueDingBiaoJueFanWei)) {
					topic.setVoteStatus("待确定表决范围");
				}else if (topic.getVoteStatus().equals(InitSysConstants.VoteStatus_BiaoJueZhong)) {
					topic.setVoteStatus("表决中");
				}else if (topic.getVoteStatus().equals(InitSysConstants.VoteStatus_BiaoJueWanJie)) {
					topic.setVoteStatus("表决完结");
				}else if (topic.getVoteStatus().equals(InitSysConstants.VoteStatus_GuanLianFenHu)) {
					topic.setVoteStatus("已关联分户");
				}
				
				if (topic.getEffectiveStatus().equals(InitSysConstants.VOTE_EFFECTIVE)) {
					topic.setEffectiveStatus("有效");
				}else if (topic.getEffectiveStatus().equals(InitSysConstants.Vote_NONEFFECTIVE)) {
					topic.setEffectiveStatus("无效");
				}
			}
			PageInfo<VoteTopicInfo> pageInfo = new PageInfo<VoteTopicInfo>(listInfo);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageInfo", pageInfo);
			map.put("listInfo", listInfo);
			
			logger.debug("house info: " + new ObjectMapper().writeValueAsString(pageInfo));
			logger.debug("listInfo info: " + new ObjectMapper().writeValueAsString(listInfo));
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
			throw new BusinessException("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Map<String, Object> delApplyInfo(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("status", false);
		String topicIds = (String)requestMap.get("topicId");
		logger.debug("topicids: "+ topicIds);
		VoteTopicInfoExample voteTopicInfoExample = new VoteTopicInfoExample();
		Criteria criteria = voteTopicInfoExample.createCriteria();
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		for (String topicId: topicIds.split(","))
		{
			list.add(new BigDecimal(topicId));
		}
		criteria.andTopicIdIn(list);
		int count = voteTopicInfoMapper.deleteByExample(voteTopicInfoExample);
		if (count > 0) {
			map.put("status", true);
		}
		
		return map;
	}

	@Override
	public Map<String, Object> linkVote(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoteTopicInfo getTopicInfoById(BigDecimal topicId) {
		// TODO Auto-generated method stub
		VoteTopicInfo voteTopicInfo = voteTopicInfoMapper.selectByPrimaryKey(topicId);
		return voteTopicInfo;
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Map<String, Object> updateApplyInfo(VoteTopicInfo voteTopicInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("status", false);
		voteTopicInfo.setVoteStartDate(DateUtil.format2DB((String)voteTopicInfo.getVoteStartDate()));
		voteTopicInfo.setVoteEndDate(DateUtil.format2DB((String)voteTopicInfo.getVoteEndDate()));
		int count = voteTopicInfoMapper.updateByPrimaryKeySelective(voteTopicInfo);
		if (count > 0) {
			map.put("status", true);
		}
		return map;
	}

	/*
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Map<String, Object> linkTopicAndHouseInfo(String topicId, List<VoteHouseInfo> list) {
		// TODO Auto-generated method stub
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("status", false);
		VoteScopeInfo voteScopeInfo = null;
		VoteTopicInfo voteTopicInfo = null;
		VoteHouseInfo voteHouseInfo = null;
		Iterator<VoteHouseInfo> iter = list.iterator();
		while(iter.hasNext()) {
			voteHouseInfo = iter.next();
			voteScopeInfo = new VoteScopeInfo();
			voteScopeInfo.setTopicId(new BigDecimal(topicId));
			voteScopeInfo.setInfoId(voteHouseInfo.getInfoId());
			voteScopeInfo.setInfoArea(voteHouseInfo.getInfoArea());
			voteScopeInfo.setLastModifyTime(DateUtil.getSysDate());
			voteScopeInfoMapper.insertSelective(voteScopeInfo);
		}
		
		Map<String, BigDecimal> map = voteScopeInfoMapper.selectTotalHouse(new BigDecimal(topicId));
		voteTopicInfo = new VoteTopicInfo();
		voteTopicInfo.setTopicId(new BigDecimal(topicId));
		voteTopicInfo.setSumHs(map.get("SUMHS"));
		voteTopicInfo.setSumArea(map.get("SUMAREA"));
		
		int count = voteTopicInfoMapper.updateByPrimaryKeySelective(voteTopicInfo);
		if (count > 0) {
			resMap.put("status", true);
			return resMap;
		}
		
		return resMap;
	}*/

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Map<String, Object> unlinkTopicAndHouseInfo(String topicId, Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("status", false);
		VoteScopeInfo voteScopeInfo = null;
		VoteTopicInfo voteTopicInfo = null;
		VoteHouseInfo voteHouseInfo = null;
		
		List<BigDecimal> infoList = new ArrayList<>();
		if (!StringUtils.isEmpty((String)requestMap.get("infoId"))){
			String infoIds = (String)requestMap.get("infoId");
			Pattern patt = Pattern.compile("\\D");
			for(String infoId: patt.split(infoIds)) {
				if (!StringUtils.isEmpty(infoId)) {
					logger.debug("infoid: "+ infoId);
					infoList.add(new BigDecimal(infoId));
				}
			}
		}
		
		VoteHouseInfoExample voteHouseInfoExample = new VoteHouseInfoExample();
		com.indihx.elecvote.entity.VoteHouseInfoExample.Criteria criteria = voteHouseInfoExample.createCriteria();
		criteria.andInfoIdIn(infoList);
		List<VoteHouseInfo> houseList = voteHouseInfoMapper.selectByExample(voteHouseInfoExample);
		
		Iterator<VoteHouseInfo> iter = houseList.iterator();
		while(iter.hasNext()) {
			VoteScopeInfoExample voteScopeInfoExample = new VoteScopeInfoExample();
			com.indihx.elecvote.entity.VoteScopeInfoExample.Criteria criteriaScope = voteScopeInfoExample.createCriteria();
			voteHouseInfo = iter.next();
			//voteScopeInfo = new VoteScopeInfo();
			//voteScopeInfo.setTopicId(new BigDecimal(topicId));
			//voteScopeInfo.setInfoId(voteHouseInfo.getInfoId());
			criteriaScope.andTopicIdEqualTo(new BigDecimal(topicId));
			criteriaScope.andInfoIdEqualTo(voteHouseInfo.getInfoId());
			voteScopeInfoMapper.deleteByExample(voteScopeInfoExample);
		}
		
		Map<String, BigDecimal> map = voteScopeInfoMapper.selectTotalHouse(new BigDecimal(topicId));
		voteTopicInfo = new VoteTopicInfo();
		voteTopicInfo.setTopicId(new BigDecimal(topicId));
		voteTopicInfo.setSumHs(map.get("SUMHS"));
		voteTopicInfo.setSumArea(map.get("SUMAREA"));
		
		voteTopicInfoMapper.updateByPrimaryKeySelective(voteTopicInfo);
		//这里要计算出需要投票的总户数，总面积
		VoteResultSum  voteResultSum = voteResultSumMapper.selectByPrimaryKey(new BigDecimal(topicId));
		
		//Map<String, BigDecimal> houseMap = voteScopeInfoMapper.selectTotalHouse(new BigDecimal(topicId));
		voteResultSum.setSumCount(map.get("SUMHS").longValue());
		voteResultSum.setSumArea(map.get("SUMAREA"));
		
		int count = voteResultSumMapper.updateByPrimaryKeySelective(voteResultSum);
		if (count > 0) {
			resMap.put("status", true);
			return resMap;
		}

		return resMap;
	}

	@Override
	public Map<String, Object> linkTopicAndHouseInfo(String topicId, Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("status", false);
		VoteScopeInfo voteScopeInfo = null;
		VoteTopicInfo voteTopicInfo = null;
		VoteHouseInfo voteHouseInfo = null;
		
		List<BigDecimal> infoList = new ArrayList<>();
		if (!StringUtils.isEmpty((String)requestMap.get("infoId"))){
			String infoIds = (String)requestMap.get("infoId");
			Pattern patt = Pattern.compile("\\D");
			for(String infoId: patt.split(infoIds)) {
				if (!StringUtils.isEmpty(infoId)) {
					logger.debug("infoid: "+ infoId);
					infoList.add(new BigDecimal(infoId));
				}
			}
		}
		
		VoteHouseInfoExample voteHouseInfoExample = new VoteHouseInfoExample();
		com.indihx.elecvote.entity.VoteHouseInfoExample.Criteria criteria = voteHouseInfoExample.createCriteria();
		criteria.andInfoIdIn(infoList);
		List<VoteHouseInfo> houseList = voteHouseInfoMapper.selectByExample(voteHouseInfoExample);
		
 		Iterator<VoteHouseInfo> iter = houseList.iterator();
		while(iter.hasNext()) {
			voteHouseInfo = iter.next();
			voteScopeInfo = new VoteScopeInfo();
			voteScopeInfo.setTopicId(new BigDecimal(topicId));
			voteScopeInfo.setInfoId(voteHouseInfo.getInfoId());
			voteScopeInfo.setInfoArea(voteHouseInfo.getInfoArea());
			voteScopeInfo.setLastModifyTime(DateUtil.getSysDate());
			voteScopeInfoMapper.insertSelective(voteScopeInfo);
		}
		
		Map<String, BigDecimal> map = voteScopeInfoMapper.selectTotalHouse(new BigDecimal(topicId));
		voteTopicInfo = new VoteTopicInfo();
		voteTopicInfo.setTopicId(new BigDecimal(topicId));
		voteTopicInfo.setSumHs(map.get("SUMHS"));
		voteTopicInfo.setSumArea(map.get("SUMAREA"));
		voteTopicInfo.setVoteStatus(InitSysConstants.VoteStatus_GuanLianFenHu);
		
		voteTopicInfoMapper.updateByPrimaryKeySelective(voteTopicInfo);
		
		//这里要计算出需要投票的总户数，总面积
		VoteResultSum  voteResultSum = voteResultSumMapper.selectByPrimaryKey(new BigDecimal(topicId));
		
		//Map<String, BigDecimal> houseMap = voteScopeInfoMapper.selectTotalHouse(new BigDecimal(topicId));
		voteResultSum.setSumCount(map.get("SUMHS").longValue());
		voteResultSum.setSumArea(map.get("SUMAREA"));
		voteResultSum.setNonvoteHou(map.get("SUMHS").longValue());
		voteResultSum.setNonvoteHouRate(new BigDecimal(100));
		voteResultSum.setNonvoteArea(map.get("SUMAREA"));
		voteResultSum.setNonvoteAreaRate(new BigDecimal(100));
		
		int count = voteResultSumMapper.updateByPrimaryKeySelective(voteResultSum);
		if (count > 0) {
			resMap.put("status", true);
			return resMap;
		}
		
		return resMap;
	}

	@Override
	public Map<String, Object> checkTopicStatus(String topicId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		VoteTopicInfo voteTopicInfo =voteTopicInfoMapper.selectByPrimaryKey(new BigDecimal(topicId));
		
		map.put("status", voteTopicInfo.getVoteStatus());
		map.put("topic", voteTopicInfo);
		return map;
	}

	@Override
	public Map<String, Object> startVote(String topicId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("status", false);
		VoteTopicInfo voteTopicInfo = new VoteTopicInfo();
		voteTopicInfo.setVoteStatus(InitSysConstants.VoteStatus_BiaoJueZhong);
		voteTopicInfo.setTopicId(new BigDecimal(topicId));
		
		int count = voteTopicInfoMapper.updateByPrimaryKeySelective(voteTopicInfo);
		if (count > 0) {
			map.put("status", true);
		}
		
		return map;
	}

	@Override
	public Map<String, Object> endVote(String topicId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("status", false);
		VoteTopicInfo voteTopicInfo = new VoteTopicInfo();
		voteTopicInfo.setTopicId(new BigDecimal(topicId));
		voteTopicInfo.setEffectiveStatus(InitSysConstants.Vote_NONEFFECTIVE);
		int count = voteTopicInfoMapper.updateByPrimaryKeySelective(voteTopicInfo);
		if(count > 0 ) {
			map.put("status", true);
		}
		
		return map;
	}
}
