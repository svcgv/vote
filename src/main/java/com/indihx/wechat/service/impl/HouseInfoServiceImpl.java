package com.indihx.wechat.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.elecvote.dao.VoteApplyInfoMapper;
import com.indihx.elecvote.dao.VoteHouseInfoMapper;
import com.indihx.elecvote.dao.VoteSectInfoMapper;
import com.indihx.elecvote.entity.VoteApplyInfo;
import com.indihx.elecvote.entity.VoteApplyInfoExample;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.entity.VoteHouseInfoExample;
import com.indihx.elecvote.entity.VoteSectInfo;
import com.indihx.elecvote.entity.VoteSectInfoExample;
import com.indihx.elecvote.entity.VoteSectInfoExample.Criteria;
import com.indihx.util.StringUtils;
import com.indihx.wechat.service.HouseInfoService;

@Service
public class HouseInfoServiceImpl implements HouseInfoService{
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private VoteSectInfoMapper voteSectInfoMapper;
	@Autowired
	private VoteHouseInfoMapper voteHouseInfoMapper;
	@Autowired
	private PrimaryKey  primaryKey;
	@Autowired
	private VoteApplyInfoMapper voteApplyInfoMapper;

	@Override
	public Map<String, Object> getSectInfo(Map<String, Object> requestMap) {
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

	@Override
	public Map<String, Object> getBuildInfo(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String,Object> responseMap = new HashMap<>();
		VoteHouseInfoExample voteHouseInfoExample = new VoteHouseInfoExample();
		try {
			com.indihx.elecvote.entity.VoteHouseInfoExample.Criteria criteria = voteHouseInfoExample.createCriteria();
			criteria.andSectNameEqualTo((String)requestMap.get("sectName"));
			voteHouseInfoExample.setOrderByClause("build_code asc");
			voteHouseInfoExample.setDistinct(true);
			List<VoteHouseInfo> list = voteHouseInfoMapper.selectDistinctBuild(voteHouseInfoExample);
			responseMap.put("listInfo", list);
			responseMap.put("status", true);
			return responseMap;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
			throw new BusinessException("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}

	@Override
	public Map<String, Object> getUnitInfo(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String,Object> responseMap = new HashMap<>();
		VoteHouseInfoExample voteHouseInfoExample = new VoteHouseInfoExample();
		try {
			com.indihx.elecvote.entity.VoteHouseInfoExample.Criteria criteria = voteHouseInfoExample.createCriteria();
			criteria.andSectNameEqualTo((String)requestMap.get("sectName"));
			criteria.andBuildCodeEqualTo((String)requestMap.get("buildCode"));
			voteHouseInfoExample.setOrderByClause("build_code asc");
			voteHouseInfoExample.setDistinct(true);
			List<VoteHouseInfo> list = voteHouseInfoMapper.selectDistinctUnit(voteHouseInfoExample);
			responseMap.put("listInfo", list);
			responseMap.put("status", true);
			return responseMap;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
			throw new BusinessException("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}

	@Override
	public Map<String, Object> getFloorInfo(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String,Object> responseMap = new HashMap<>();
		VoteHouseInfoExample voteHouseInfoExample = new VoteHouseInfoExample();
		try {
			com.indihx.elecvote.entity.VoteHouseInfoExample.Criteria criteria = voteHouseInfoExample.createCriteria();
			criteria.andSectNameEqualTo((String)requestMap.get("sectName"));
			criteria.andBuildCodeEqualTo((String)requestMap.get("buildCode"));
			criteria.andUnitCodeEqualTo((String)requestMap.get("unitCode"));
			voteHouseInfoExample.setOrderByClause("build_code asc");
			voteHouseInfoExample.setDistinct(true);
			List<VoteHouseInfo> list = voteHouseInfoMapper.selectDistinctFloor(voteHouseInfoExample);
			responseMap.put("listInfo", list);
			responseMap.put("status", true);
			return responseMap;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
			throw new BusinessException("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}

	@Override
	public Map<String, Object> getRoomInfo(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String,Object> responseMap = new HashMap<>();
		VoteHouseInfoExample voteHouseInfoExample = new VoteHouseInfoExample();
		try {
			com.indihx.elecvote.entity.VoteHouseInfoExample.Criteria criteria = voteHouseInfoExample.createCriteria();
			criteria.andSectNameEqualTo((String)requestMap.get("sectName"));
			criteria.andBuildCodeEqualTo((String)requestMap.get("buildCode"));
			criteria.andUnitCodeEqualTo((String)requestMap.get("unitCode"));
			criteria.andFloorCodeEqualTo((String)requestMap.get("floorCode"));
			voteHouseInfoExample.setOrderByClause("build_code asc");
			voteHouseInfoExample.setDistinct(true);
			List<VoteHouseInfo> list = voteHouseInfoMapper.selectDistinctRoom(voteHouseInfoExample);
			responseMap.put("listInfo", list);
			responseMap.put("status", true);
			return responseMap;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
			throw new BusinessException("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Map<String, Object> bindHouseInfo(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("status", false);
		/*
		VoteHouseInfo voteHouseInfo = new VoteHouseInfo();
		voteHouseInfo.setLinkPhone((String)requestMap.get("linkPhone"));
		voteHouseInfo.setInfoStatus("01");*/
		
		VoteHouseInfoExample voteHouseInfoExample = new VoteHouseInfoExample();
		com.indihx.elecvote.entity.VoteHouseInfoExample.Criteria criteria = voteHouseInfoExample.createCriteria();
		criteria.andSectNameEqualTo((String)requestMap.get("sectName"));
		criteria.andBuildCodeEqualTo((String)requestMap.get("buildCode"));
		criteria.andUnitCodeEqualTo((String) requestMap.get("unitCode"));
		criteria.andFloorCodeEqualTo((String)requestMap.get("floorCode"));
		criteria.andRoomCodeEqualTo((String)requestMap.get("roomCode"));
		criteria.andCertCodeEqualTo((String)requestMap.get("certCode"));
		List<VoteHouseInfo> list = voteHouseInfoMapper.selectByExample(voteHouseInfoExample);
		if (list.isEmpty()) {
			return map;
		}
		VoteHouseInfo houseInfo = list.get(0);
		VoteApplyInfoExample example = new VoteApplyInfoExample();
		com.indihx.elecvote.entity.VoteApplyInfoExample.Criteria  applyCriteria = example.createCriteria();
		applyCriteria.andInfoIdEqualTo(houseInfo.getInfoId());
		List<VoteApplyInfo> applyList = voteApplyInfoMapper.selectByExample(example);
		if (!applyList.isEmpty()) {
			logger.error("该房屋信息已经绑定过，不能继续绑定");
			map.put("status", false);
			return map;
		}
		
		VoteApplyInfo voteApplyInfo = new VoteApplyInfo();
		voteApplyInfo.setApplyId(new BigDecimal(primaryKey.getKeyBySequenceName("APPLY_ID_SEQ")));
		voteApplyInfo.setInfoId(houseInfo.getInfoId());
		voteApplyInfo.setRegisteDate(DateUtil.getSysDate());
		voteApplyInfo.setRegisteTime(DateUtil.getSysTime());
		voteApplyInfo.setOwnerName(houseInfo.getOwnerName());
		voteApplyInfo.setCertCode(houseInfo.getCertCode());
		voteApplyInfo.setLinkPhone((String)requestMap.get("linkPhone"));
		voteApplyInfo.setUserId((String)requestMap.get("openId"));
		int count = voteApplyInfoMapper.insertSelective(voteApplyInfo);
		if (count > 0) {
			map.put("status", true);
		}
		/*
		int count = voteHouseInfoMapper.updateByExampleSelective(voteHouseInfo, voteHouseInfoExample);
		if (count > 0) {
			map.put("status", true);
		}*/
		return map;
	}

	@Override
	public Map<String, Object> getHouseInfoByOpenId(String openId) {
		// TODO Auto-generated method stub
		List<VoteHouseInfo> listInfo = voteHouseInfoMapper.selectByOpenId(openId);
		Map<String, Object> map = new HashMap<>();
		map.put("listInfo", listInfo);
		return map;
	}

	@Override
	public Map<String, Object> unbindHouseInfo(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("status", false);
		String openId = (String)requestMap.get("openId");
		String infoId = (String)requestMap.get("infoId");
		logger.debug("openId: "+openId+" infoId: "+infoId);
		VoteApplyInfoExample voteApplyInfoExample = new VoteApplyInfoExample();
		com.indihx.elecvote.entity.VoteApplyInfoExample.Criteria criteria = voteApplyInfoExample.createCriteria();
		criteria.andUserIdEqualTo(openId);
		criteria.andInfoIdEqualTo(new BigDecimal(infoId));
		int count = voteApplyInfoMapper.deleteByExample(voteApplyInfoExample);
		if (count > 0) {
			map.put("status", true);
		}
		
		return map;
	}

}
