package com.indihx.elecvote.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.indihx.elecvote.entity.VoteSectInfo;

public interface SectManageService {
	Map<String, Object> getSectInfo(String sectName, int currentPage, int pageSize);
	VoteSectInfo	getSectInfoById(BigDecimal sectId);
	List<Map<String,String>> getSectIdAndName();
	
	Map<String, Object> getAllSectInfo(Map<String, Object> requestMap);
}
