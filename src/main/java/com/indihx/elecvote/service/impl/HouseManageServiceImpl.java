package com.indihx.elecvote.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.elecvote.dao.VoteHouseInfoMapper;
import com.indihx.elecvote.dao.VoteScopeInfoMapper;
import com.indihx.elecvote.dao.VoteSectInfoMapper;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.entity.VoteHouseInfoExample;
import com.indihx.elecvote.entity.VoteHouseInfoExample.Criteria;
import com.indihx.elecvote.entity.VoteScopeInfo;
import com.indihx.elecvote.entity.VoteSectInfo;
import com.indihx.elecvote.entity.VoteSectInfoExample;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.elecvote.vo.HouseInfo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.tender.entity.ZTB_EXPERT;
import com.indihx.util.Assert;
import com.indihx.util.EntityVoConverter;
import com.indihx.util.ImportExcelUtil;
import com.indihx.util.StringUtils;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: HouseManageServiceImpl.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年03月22日 下午5:10:12</p>
 * <p>@author zhaoyongfeng</p>
 * <p>@version 1.0</p>
 * <p>HouseManageServiceImpl.java</p>
 * <p></p>
 */
@Service
public class HouseManageServiceImpl implements HouseManageService{
	
	private Logger logger = LoggerFactory.getLogger(HouseManageServiceImpl.class);
	
	@Resource
	private VoteHouseInfoMapper voteHouseInfoMapper;
	@Resource
	private VoteSectInfoMapper voteSectInfoMapper;
	@Resource
	private VoteScopeInfoMapper voteScopeInfoMapper;
	@Autowired
	private PrimaryKey primaryKey;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Map<String, Object> loadDataInfo(UsrInfo usrInfo, MultipartFile[] myfiles, String fileTypeId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<List<Object>> listob = null;
		InputStream in = null;  
		try{
			Assert.notNull(myfiles, "加载数据异常，数据文件为空！");
			in = myfiles[0].getInputStream();  
			listob = new ImportExcelUtil().getBankListByExcel(in, myfiles[0].getOriginalFilename());			
		}catch(Exception e){
			logger.error("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
			throw new BusinessException("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
		}
		
		int count = importHouseInfo(listob);
		map.put("status", "success");
		map.put("count", count);
		return map;
	}
	/**
	 * 把Excel数据导入vote_house_info表
	 * @param file 
	 * @param path
	 * @throws Exception 
	 */
	private int  importHouseInfo(List<List<Object>> listob)  {
		InputStream in = null;   
		List<Object> obj = null;
		 //调用工具类，加载Excel数据，自动过滤第一行Title
		VoteHouseInfo voteHouseInfo = null;
		VoteSectInfo voteSectInfo = null;
		Set<BigDecimal> set = new HashSet<BigDecimal>();
		VoteSectInfoExample voteSectInfoExample = new VoteSectInfoExample();
		for (int i = 0; i < listob.size(); i++) {
			obj = listob.get(i);
			voteHouseInfo = new VoteHouseInfo();
			voteSectInfo = new VoteSectInfo();
			
			com.indihx.elecvote.entity.VoteSectInfoExample.Criteria criteria = voteSectInfoExample.createCriteria();
			criteria.andSectNameEqualTo(obj.get(0).toString());
			List<VoteSectInfo> sectList = voteSectInfoMapper.selectByExample(voteSectInfoExample);
			if (sectList.isEmpty()){ //如果没有该小区信息，则新增第一条
				//voteSectInfo.setSectId(new BigDecimal(primaryKey.getPrimaryKeyAddDate()));
				voteSectInfo.setSectId(new BigDecimal(primaryKey.getKeyBySequenceName("SECT_ID_SEQ")));
				voteSectInfo.setSectName(obj.get(0).toString());
				/*直接从房屋信息表中汇总，可以把小区id放到一个set里，等房屋信息都导入后再更新set中的小区信息
				voteSectInfo.setHouCount(1L);
				voteSectInfo.setBuildCount(1L);
				voteSectInfo.setInfoArea(new BigDecimal(obj.get(5).toString()));*/
				voteSectInfo.setCreateDate(DateUtil.getSysDate());
				voteSectInfo.setCreateTime(DateUtil.getSysTime());
								
				voteHouseInfo.setSectId(voteSectInfo.getSectId());
				
				voteSectInfoMapper.insert(voteSectInfo);
			}else {//要更新总户数，总栋数，总面积
				voteHouseInfo.setSectId(sectList.get(0).getSectId());
			}
			
			set.add(voteSectInfo.getSectId());
			//voteHouseInfo.setInfoId(new BigDecimal(primaryKey.getPrimaryKeyAddDate()));
			voteHouseInfo.setInfoId(new BigDecimal(primaryKey.getKeyBySequenceName("INFO_ID_SEQ")));
			//voteHouseInfo.setInfoAddr(null);
			voteHouseInfo.setInfoArea(new BigDecimal(obj.get(5).toString()));
			voteHouseInfo.setOwnerName(obj.get(6).toString());
			voteHouseInfo.setCertCode(obj.get(7).toString());
			voteHouseInfo.setSectName(obj.get(0).toString());
			voteHouseInfo.setBuildCode(obj.get(1).toString());
			voteHouseInfo.setUnitCode(obj.get(2).toString());
			voteHouseInfo.setFloorCode(obj.get(3).toString());
			voteHouseInfo.setRoomCode(obj.get(4).toString());
			voteHouseInfo.setCreateDate(DateUtil.getSysDate());
			voteHouseInfo.setCreateTime(DateUtil.getSysTime());
			voteHouseInfo.setInfoStatus(InitSysConstants.INFO_STATUS_ZhegnChang);
			
			voteHouseInfoMapper.insert(voteHouseInfo);
			//logger.debug("house info: " + new ObjectMapper().writeValueAsString(voteHouseInfo));
		}
		logger.debug("sectid: "+set.toString());
		
		Iterator<BigDecimal> iter = set.iterator();
		while(iter.hasNext()){
			voteSectInfo = new VoteSectInfo();
			voteSectInfo.setSectId(iter.next());
			HashMap<String, BigDecimal> map = voteHouseInfoMapper.selectTotalBySectId(voteSectInfo.getSectId());
			for (String key: map.keySet()) {
				logger.debug("key: "+key+" value: "+ map.get(key));
			}
			logger.debug("map: "+map.toString());
			//String infoArea = (String)map.get("INFOAREA");
			voteSectInfo.setInfoArea((BigDecimal)map.get("INFOAREA"));
			voteSectInfo.setBuildCount(map.get("BUILDCOUNT").longValue());
			voteSectInfo.setHouCount(map.get("HOUCOUNT").longValue());
			voteSectInfoMapper.updateByPrimaryKeySelective(voteSectInfo);
		}
		logger.info("importHouseInfo success");
		
		return listob.size();
	}
	/**
	 * 解析Excel数据
	 * @param file 
	 * @param path
	 * @throws Exception 
	 */
	/*
	private List<HouseInfo> loadExcel(MultipartFile file) throws Exception {
		 List<HouseInfo> list = new ArrayList<HouseInfo>();
		 ObjectMapper mapper = new ObjectMapper();
		 InputStream in = null;  
		 List<List<Object>> listob = null;
		 List<Object> obj = null;
		 in = file.getInputStream();  
		 //调用工具类，加载Excel数据，自动过滤第一行Title
		 listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());  
		 HouseInfo houseinfo = null;
		 for (int i = 0; i < listob.size(); i++) {
			 //获取一行数据
			 obj = listob.get(i);
			 //构造数据集
			 houseinfo = new HouseInfo();
			 houseinfo.setSectName(obj.get(0).toString());
			 houseinfo.setBuildCode(obj.get(1).toString());
			 houseinfo.setUnitCode(obj.get(2).toString());
			 houseinfo.setFloorCode(obj.get(3).toString());
			 houseinfo.setRoomCode(obj.get(4).toString());
			 houseinfo.setInfoArea(obj.get(5).toString());
			 houseinfo.setOwnerName(obj.get(6).toString());
			 houseinfo.setCertCode(obj.get(7).toString());
			 
			 //TODO保存数据
			 list.add(houseinfo);
		}	
		logger.debug("house info: " + mapper.writeValueAsString(list));
		
		return list;
	}*/
	@Override
	public Map<String, Object> getHouseInfo(UsrInfo usrInfo, Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		try {
			//获取页面输入参数，并将其转为实体对象
			//VoteHouseInfo voteHouseInfo = new VoteHouseInfo();
			//voteHouseInfo.setSectName(sectName);
			//Map<String, Object> map = new HashMap<>();
			String currentPage = null;
			VoteHouseInfo voteHouseInfo = new VoteHouseInfo();
			VoteHouseInfoExample voteHouseInfoExample = new VoteHouseInfoExample();
			Criteria criteria = voteHouseInfoExample.createCriteria();
			
			if (!StringUtils.isEmpty((String)requestMap.get("sectName"))) {
				criteria.andSectNameEqualTo((String)requestMap.get("sectName"));
				//voteHouseInfo.setSectName((String)requestMap.get("sectName"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("buildCode"))){
				criteria.andBuildCodeEqualTo((String)requestMap.get("buildCode"));
				//voteHouseInfo.setBuildCode((String)requestMap.get("buildCode"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("unitCode"))){
				criteria.andUnitCodeEqualTo((String)requestMap.get("unitCode"));
				//voteHouseInfo.setUnitCode((String)requestMap.get("unitCode"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("floorCode"))){
				criteria.andFloorCodeEqualTo((String)requestMap.get("floorCode"));
				//voteHouseInfo.setFloorCode((String)requestMap.get("floorCode"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("roomCode"))){
				criteria.andRoomCodeEqualTo((String)requestMap.get("roomCode"));
				//map.put("roomCode", (String)requestMap.get("roomCode"));
			}
			
			if (!StringUtils.isEmpty((String)requestMap.get("currPage"))){
				currentPage = (String)requestMap.get("currPage");
			}
			
			
			voteHouseInfoExample.setOrderByClause("info_id ASC");
			
			PageHelper.startPage(Integer.parseInt(currentPage), InitSysConstants.BIG_SIZE);
			//调用Mapper根据参数获取区县列表信息
			List<VoteHouseInfo> listInfo = voteHouseInfoMapper.selectByExample(voteHouseInfoExample);
			logger.debug("listInfo info: " + new ObjectMapper().writeValueAsString(listInfo));
			PageInfo<VoteHouseInfo> pageInfo = new PageInfo<VoteHouseInfo>(listInfo);
			//响应页面数据
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
	
	@Override
	public VoteHouseInfo getHouseInfoById(BigDecimal infoId)
	{
		VoteHouseInfo voteHouseInfo = voteHouseInfoMapper.selectByPrimaryKey(infoId);
		return voteHouseInfo;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public boolean editHouseInfo(VoteHouseInfo voteHouseInfo) {
		// TODO Auto-generated method stub
		voteHouseInfoMapper.updateByPrimaryKeySelective(voteHouseInfo);
		VoteSectInfo voteSectInfo = new VoteSectInfo();
		Map<String, BigDecimal> houseMap = voteHouseInfoMapper.selectTotalBySectId(voteHouseInfo.getSectId());
		voteSectInfo.setSectId(voteHouseInfo.getSectId());
		voteSectInfo.setInfoArea((BigDecimal)houseMap.get("INFOAREA"));
		voteSectInfo.setBuildCount(houseMap.get("BUILDCOUNT").longValue());
		voteSectInfo.setHouCount(houseMap.get("HOUCOUNT").longValue());
		
		int count = voteSectInfoMapper.updateByPrimaryKeySelective(voteSectInfo);
		if (count > 0) {
			return true;
		}else {
			throw new RuntimeException("update sectinfo error");
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public boolean delHouseInfo(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		VoteHouseInfoExample voteHouseInfoExample = new VoteHouseInfoExample();
		Criteria criteria = voteHouseInfoExample.createCriteria();
		
		String infoIds = (String)requestMap.get("infoId");
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		for (String infoId: infoIds.split(","))
		{
			list.add(new BigDecimal(infoId));
		}
		criteria.andInfoIdIn(list);
		
		voteHouseInfoMapper.deleteByExample(voteHouseInfoExample);
		
		String sectIds = (String)requestMap.get("sectId");
		Set<BigDecimal> set = new HashSet<>();
		for (String sectId: sectIds.split(",")) {
			set.add(new BigDecimal(sectId));
		}
		Iterator<BigDecimal> iter = set.iterator();
		while(iter.hasNext()){
			BigDecimal id = iter.next();
			VoteSectInfo voteSectInfo = new VoteSectInfo();
			Map<String, BigDecimal> houseMap = voteHouseInfoMapper.selectTotalBySectId(id);
			voteSectInfo.setSectId(id);
			voteSectInfo.setInfoArea((BigDecimal)houseMap.get("INFOAREA"));
			voteSectInfo.setBuildCount(houseMap.get("BUILDCOUNT").longValue());
			voteSectInfo.setHouCount(houseMap.get("HOUCOUNT").longValue());
			int count = voteSectInfoMapper.updateByPrimaryKeySelective(voteSectInfo);
			if (count < 0) {
				throw new RuntimeException("update sectinfo error");
			}
		}
		
		return true;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Map<String, Object> addHouseInfo(VoteHouseInfo voteHouseInfo) {
		// TODO Auto-generated method stub
		VoteSectInfo voteSectInfo = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", false);
		VoteSectInfoExample voteSectInfoExample = new VoteSectInfoExample();
		com.indihx.elecvote.entity.VoteSectInfoExample.Criteria criteriaSect = voteSectInfoExample.createCriteria();
		criteriaSect.andSectNameEqualTo(voteHouseInfo.getSectName());
		List<VoteSectInfo> sectList = voteSectInfoMapper.selectByExample(voteSectInfoExample);
		if (sectList.isEmpty())
		{
			voteSectInfo = new VoteSectInfo();
			voteSectInfo.setSectId(new BigDecimal(primaryKey.getKeyBySequenceName("SECT_ID_SEQ")));
			voteHouseInfo.setInfoId(new BigDecimal(primaryKey.getKeyBySequenceName("INFO_ID_SEQ")));
			voteSectInfo.setSectName(voteHouseInfo.getSectName());
			voteSectInfo.setHouCount(1L);
			voteSectInfo.setBuildCount(1L);
			voteSectInfo.setInfoArea(voteHouseInfo.getInfoArea());
			voteSectInfo.setCreateDate(DateUtil.getSysDate());
			voteSectInfo.setCreateTime(DateUtil.getSysTime());
							
			voteHouseInfo.setSectId(voteSectInfo.getSectId());
			
			voteSectInfoMapper.insert(voteSectInfo);
			voteHouseInfoMapper.insert(voteHouseInfo);

		}else {//要更新总户数，总栋数，总面积
			voteSectInfo = sectList.get(0);
			voteHouseInfo.setInfoId(new BigDecimal(primaryKey.getKeyBySequenceName("INFO_ID_SEQ")));
			voteHouseInfo.setSectId(voteSectInfo.getSectId());
			voteHouseInfoMapper.insert(voteHouseInfo);
			Map<String, BigDecimal> houseMap = voteHouseInfoMapper.selectTotalBySectId(voteSectInfo.getSectId());
			voteSectInfo.setInfoArea((BigDecimal)houseMap.get("INFOAREA"));
			voteSectInfo.setBuildCount(houseMap.get("BUILDCOUNT").longValue());
			voteSectInfo.setHouCount(houseMap.get("HOUCOUNT").longValue());
			voteSectInfoMapper.updateByPrimaryKeySelective(voteSectInfo);
		}
		
		logger.info("add house info success");
		map.put("status", true);
		return map;
	}
	
	/**
	 * @param topicId 投票id
	 * @param bool 关联标志，true: 查询已经关联该topicid的房屋信息，false:查询没有关联该topic的房屋信息
	 * 
	 */
	@Override
	public Map<String, Object> getHouseInfoAndSelect(UsrInfo usrInfo, String topicId, Map<String, Object> requestMap, Boolean bool) {
		// TODO Auto-generated method stub
		try {
			String currentPage = null;
			String infoIds = null;
			Set<BigDecimal> set = new HashSet<>();
			Map<String, Object> map = new HashMap<>();
			
			//VoteHouseInfoExample voteHouseInfoExample = new VoteHouseInfoExample();
			//Criteria criteria = voteHouseInfoExample.createCriteria();
			map.put("topicId", topicId);
			if (!StringUtils.isEmpty((String)requestMap.get("sectName"))) {
				map.put("sectName", requestMap.get("sectName"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("buildCode"))){
				map.put("buildCode", requestMap.get("buildCode"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("unitCode"))){
				map.put("unitCode", requestMap.get("unitCode"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("floorCode"))){
				map.put("floorCode", requestMap.get("floorCode"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("roomCode"))){
				map.put("roomCode", requestMap.get("roomCode"));
			}
			
			if (!StringUtils.isEmpty((String)requestMap.get("currPage"))){
				currentPage = (String)requestMap.get("currPage");
			}
			if (!StringUtils.isEmpty((String)requestMap.get("infoId"))){
				infoIds = (String)requestMap.get("infoId");
				Pattern patt = Pattern.compile("\\D");
				for(String infoId: patt.split(infoIds)) {
					logger.debug("infoid: "+ infoId);
					if (!StringUtils.isEmpty(infoId)) {
						set.add(new BigDecimal(infoId));
					}
				}
			}
			logger.debug("infoids: "+infoIds);
			
			//topicList = voteScopeInfoMapper.selectInfoIdByPrimaryKey(new BigDecimal(topicId));
			
			//voteHouseInfoExample.setOrderByClause("info_id ASC");
			
			PageHelper.startPage(Integer.parseInt(currentPage), InitSysConstants.BIG_SIZE);
			//调用Mapper根据参数获取区县列表信息
			//List<VoteHouseInfo> list = voteHouseInfoMapper.selectByExample(voteHouseInfoExample);
			List<VoteHouseInfo> list = null;
			if (bool) {
				list = voteHouseInfoMapper.selectLinkHouse(map);
			}else {
				list = voteHouseInfoMapper.selectUnlinkHouse(map);
			}
			
			logger.debug("listInfo info: " + new ObjectMapper().writeValueAsString(list));
			List<HouseInfo> listInfo = new ArrayList<>();
			Iterator<VoteHouseInfo> iter = list.iterator();
			VoteHouseInfo voteHouseInfo = null;
			while(iter.hasNext()) {
				HouseInfo houseInfo = new HouseInfo();
				voteHouseInfo = iter.next();
				BeanUtils.copyProperties( houseInfo,voteHouseInfo);
				if (set.contains(houseInfo.getInfoId())) {
					houseInfo.setIsSelect("Y");
				}
				listInfo.add(houseInfo);
			}
			
			PageInfo<VoteHouseInfo> pageInfo = new PageInfo<VoteHouseInfo>(list);
			//响应页面数据
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("pageInfo", pageInfo);
			resMap.put("listInfo", listInfo);
			
			logger.debug("house info: " + new ObjectMapper().writeValueAsString(pageInfo));
			logger.debug("listInfo info: " + new ObjectMapper().writeValueAsString(listInfo));
			return resMap;
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
			throw new BusinessException("加载文件数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}	
	/*
	@Override
	public Map<String, Object> getHouseInfoAndSelect(UsrInfo usrInfo, String topicId, Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		try {
			String currentPage = null;
			String infoIds = null;
			Set<BigDecimal> set = new HashSet<>();
			
			VoteHouseInfoExample voteHouseInfoExample = new VoteHouseInfoExample();
			Criteria criteria = voteHouseInfoExample.createCriteria();
			if (!StringUtils.isEmpty((String)requestMap.get("sectName"))) {
				criteria.andSectNameEqualTo((String)requestMap.get("sectName"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("buildCode"))){
				criteria.andBuildCodeEqualTo((String)requestMap.get("buildCode"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("unitCode"))){
				criteria.andUnitCodeEqualTo((String)requestMap.get("unitCode"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("floorCode"))){
				criteria.andFloorCodeEqualTo((String)requestMap.get("floorCode"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("roomCode"))){
				criteria.andRoomCodeEqualTo((String)requestMap.get("roomCode"));
			}
			if (!StringUtils.isEmpty((String)requestMap.get("currPage"))){
				currentPage = (String)requestMap.get("currPage");
			}
			if (!StringUtils.isEmpty((String)requestMap.get("infoId"))){
				infoIds = (String)requestMap.get("infoId");
				Pattern patt = Pattern.compile("\\D");
				for(String infoId: patt.split(infoIds)) {
					logger.debug("infoid: "+ infoId);
					if (!StringUtils.isEmpty(infoId)) {
						set.add(new BigDecimal(infoId));
					}
				}
			}
			logger.debug("infoids: "+infoIds);
			
			//topicList = voteScopeInfoMapper.selectInfoIdByPrimaryKey(new BigDecimal(topicId));
			
			voteHouseInfoExample.setOrderByClause("info_id ASC");
			
			PageHelper.startPage(Integer.parseInt(currentPage), 20);
			//调用Mapper根据参数获取区县列表信息
			List<VoteHouseInfo> list = voteHouseInfoMapper.selectByExample(voteHouseInfoExample);
			logger.debug("listInfo info: " + new ObjectMapper().writeValueAsString(list));
			List<HouseInfo> listInfo = new ArrayList<>();
			Iterator<VoteHouseInfo> iter = list.iterator();
			VoteHouseInfo voteHouseInfo = null;
			while(iter.hasNext()) {
				HouseInfo houseInfo = new HouseInfo();
				voteHouseInfo = iter.next();
				BeanUtils.copyProperties( houseInfo,voteHouseInfo);
				if (set.contains(houseInfo.getInfoId())) {
					houseInfo.setIsSelect("Y");
				}
				listInfo.add(houseInfo);
			}
			
			PageInfo<VoteHouseInfo> pageInfo = new PageInfo<VoteHouseInfo>(list);
			//响应页面数据
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
	}	*/
	
}
