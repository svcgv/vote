package com.indihx.elecvote.service;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.indihx.elecvote.entity.VoteResultSum;
import com.indihx.elecvote.vo.VoteHouse;

public interface VoteResultService {
	
	VoteResultSum getTopicSumInfo(String topicId);
	
	Map<String,  Object> getVoteList(String topicId, String currentPage,HttpServletRequest request);
	
	Map<String, Object> getUnvoteList(Map<String, Object> requestMap);
	
	Map<String, Object> submit(MultipartFile[] myfiles, String topicId, String infoId, String result);
	
	ResponseEntity<byte[]> download(String result);
}
 