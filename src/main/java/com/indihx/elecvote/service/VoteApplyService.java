package com.indihx.elecvote.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.entity.VoteTopicInfo;

public interface VoteApplyService {
	Map<String, Object> addApplyInfo(Map<String, Object> requestMap);
	
	Map<String, Object> getApplyInfo(Map<String, Object> requestMap);

	Map<String, Object> delApplyInfo(Map<String, Object> requestMap);

	Map<String, Object> linkVote(Map<String, Object> requestMap);
	
	Map<String, Object> updateApplyInfo(VoteTopicInfo voteTopicInfo);
	
	VoteTopicInfo getTopicInfoById(BigDecimal topicId);

	Map<String, Object> linkTopicAndHouseInfo(String topicId, Map<String, Object> map);
	
	Map<String, Object> unlinkTopicAndHouseInfo(String topicId, Map<String, Object> map);
	
	Map<String, Object> checkTopicStatus(String topicId);
	
	Map<String, Object> startVote(String topicId);
	
	Map<String, Object> endVote(String topicId);

}
