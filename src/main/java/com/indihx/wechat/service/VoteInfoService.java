package com.indihx.wechat.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface VoteInfoService {
	
	Map<String,Object> getVotedInfo(String openId);
	
	Map<String, Object> getVotedInfoDetails(String topicId);
	
	Map<String, Object> getUnvotedInfo(String openId);

	Map<String, Object> getUnvotedInfoDetails(String topicId);
	
	Map<String, Object> voteSubmit(Map<String, Object> map);
}
