package com.indihx.elecvote.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.util.StringUtil;
import com.indihx.elecvote.dao.VoteSectInfoMapper;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.entity.VoteHouseInfoExample;
import com.indihx.elecvote.entity.VoteSectInfo;
import com.indihx.elecvote.entity.VoteSectInfoExample;
import com.indihx.elecvote.entity.VoteSectInfoExample.Criteria;
import com.indihx.elecvote.service.SectManageService;
import com.indihx.util.StringUtils;

@Service
public class SectManageServiceImpl implements SectManageService {
	
	@Resource
	private VoteSectInfoMapper voteSectInfoMapper;
	private Logger logger = LoggerFactory.getLogger(SectManageServiceImpl.class);

	@Override
	public Map<String, Object> getSectInfo(String sectName, int currentPage, int pageSize) {
		// TODO Auto-generated method stub				
		try {
			VoteSectInfoExample voteSectInfoExample = new VoteSectInfoExample();
			voteSectInfoExample.setOrderByClause("sect_id asc");
			if (sectName != null && !sectName.equals(""))
			{
				Criteria criteria = voteSectInfoExample.createCriteria();
				//criteria.andSectNameEqualTo(sectName);
				criteria.andSectNameLike("%"+sectName+"%");
			}
			
			//获取页面输入参数，并将其转为实体对象
			PageHelper.startPage(currentPage, pageSize);
			List<VoteSectInfo> listInfo = voteSectInfoMapper.selectByExample(voteSectInfoExample);
			logger.debug("listInfo info: " + new ObjectMapper().writeValueAsString(listInfo));
			PageInfo<VoteSectInfo> pageInfo = new PageInfo<VoteSectInfo>(listInfo);
			//响应页面数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageInfo", pageInfo);
			map.put("listInfo", listInfo);
			
			logger.debug("page info: " + new ObjectMapper().writeValueAsString(pageInfo));
			logger.debug("listInfo info: " + new ObjectMapper().writeValueAsString(listInfo));
			return map;
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
			throw new BusinessException("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}

	@Override
	public VoteSectInfo getSectInfoById(BigDecimal sectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> getSectIdAndName() {
		// TODO Auto-generated method stub
		try {
			VoteSectInfoExample voteSectInfoExample = new VoteSectInfoExample();
			voteSectInfoExample.setOrderByClause("sect_Name asc");
			List<VoteSectInfo> listInfo = voteSectInfoMapper.selectByExample(voteSectInfoExample);
			Iterator<VoteSectInfo> iter = listInfo.iterator();
			List<Map<String,String>> listRes = new ArrayList<>();
			while(iter.hasNext())
			{
				VoteSectInfo voteSectInfo = iter.next();
				Map<String,String> map = new HashMap<String,String>();
				//map.put(voteSectInfo.getSectId().toString(), voteSectInfo.getSectName());
				map.put("sectId", voteSectInfo.getSectId().toString());
				map.put("sectName", voteSectInfo.getSectName());
				listRes.add(map);
			}
			
			return listRes;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
			throw new BusinessException("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}

	@Override
	public Map<String, Object> getAllSectInfo(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String,Object> responseMap = new HashMap<>();
		VoteSectInfoExample voteSectInfoExample = new VoteSectInfoExample();
		Criteria criteria = voteSectInfoExample.createCriteria();
		if (!StringUtils.isEmpty((String)requestMap.get("sectName"))){
			criteria.andSectNameLike((String)requestMap.get("sectName"));
		}
		voteSectInfoExample.setOrderByClause("sect_name asc");
		List<VoteSectInfo> list = voteSectInfoMapper.selectByExample(voteSectInfoExample);
		responseMap.put("listInfo", list);
		responseMap.put("status", true);
		return responseMap;
	}
}
