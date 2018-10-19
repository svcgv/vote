package com.indihx.quartz.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.indihx.comm.InitSysConstants;
import com.indihx.comm.util.DateUtil;
import com.indihx.elecvote.dao.VoteTopicInfoMapper;
import com.indihx.elecvote.entity.VoteTopicInfo;
import com.indihx.elecvote.entity.VoteTopicInfoExample;
import com.indihx.elecvote.entity.VoteTopicInfoExample.Criteria;

@Component
public class VoteStatusListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired  VoteTopicInfoMapper voteTopicInfoMapper;
	
	 public void execute(){
		 logger.info("starttime: " + DateUtil.getSysDate()+" : "+DateUtil.getSysTime());
		 
		 String date = DateUtil.getSysDate();
		 VoteTopicInfo voteTopicInfo = new VoteTopicInfo();
		 voteTopicInfo.setVoteStatus(InitSysConstants.VoteStatus_BiaoJueWanJie);
		 
		 VoteTopicInfoExample example = new VoteTopicInfoExample();
		 Criteria criteria = example.createCriteria();
		 criteria.andVoteEndDateLessThan(date);
		 criteria.andVoteStatusEqualTo(InitSysConstants.VoteStatus_BiaoJueZhong);
		 
		 voteTopicInfoMapper.updateByExampleSelective(voteTopicInfo, example);
		 
		 logger.info("endtime: " + DateUtil.getSysDate()+" : "+DateUtil.getSysTime());
	 }
}

