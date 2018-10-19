package com.indihx.elecvote.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.CircularRedirectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.indihx.comm.InitSysBasicInfo;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.dao.FileUploadMapper;
import com.indihx.comm.entity.FileUpload;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.elecvote.dao.VoteHouseInfoMapper;
import com.indihx.elecvote.dao.VoteResultInfoMapper;
import com.indihx.elecvote.dao.VoteResultSumMapper;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.entity.VoteResultInfo;
import com.indihx.elecvote.entity.VoteResultInfoExample;
import com.indihx.elecvote.entity.VoteResultInfoExample.Criteria;
import com.indihx.elecvote.entity.VoteResultSum;
import com.indihx.elecvote.service.VoteResultService;
import com.indihx.elecvote.vo.VoteHouse;


@Service
public class VoteResultServiceImpl implements VoteResultService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private VoteResultSumMapper voteResultSumMapper;
	@Autowired
	private VoteResultInfoMapper voteResultInfoMapper;
	@Autowired
	private VoteHouseInfoMapper voteHouseInfoMapper;
	@Autowired
	private PrimaryKey primaryKey;
	@Autowired
	private FileUploadMapper fileUploadMapper;

	@Override
	public VoteResultSum getTopicSumInfo(String topicId) {
		// TODO Auto-generated method stub
		VoteResultSum voteResultSum = voteResultSumMapper.selectByPrimaryKey(new BigDecimal(topicId));
		return voteResultSum;
	}

	@Override
	public Map<String, Object> getVoteList(String topicId, String currentPage,HttpServletRequest request ) {
		// TODO Auto-generated method stub
		Map<String, Object> resMap = new HashMap<>();
		List<VoteHouse> voteList = new ArrayList<>();
		VoteResultInfoExample voteResultInfoExample = new VoteResultInfoExample();
		Criteria criteria = voteResultInfoExample.createCriteria();
		criteria.andTopicIdEqualTo(new BigDecimal(topicId));
		PageHelper.startPage(Integer.parseInt(currentPage), InitSysConstants.BIG_SIZE);
		List<VoteResultInfo>  resultList = voteResultInfoMapper.selectByExample(voteResultInfoExample);
		for (VoteResultInfo result: resultList) {
			VoteHouse house = new VoteHouse();
			house.setVoteDate(result.getVoteDate());
			
			if(result.getVoteWays().equals("wechat")) {
				house.setVoteWays("公众号");
			}else if (result.getVoteWays().equals("manual")) {
				house.setVoteWays("手工录入");
			}
			if(result.getVoteResult().equals("01")) {
				house.setVoteResult("同意");
			}else if (result.getVoteResult().equals("02")) {
				house.setVoteResult("不同意");
			}
			
			VoteHouseInfo voteHouseInfo = voteHouseInfoMapper.selectByPrimaryKey(result.getInfoId());
			house.setArea(voteHouseInfo.getInfoArea().toString());
			
			String 	address = voteHouseInfo.getBuildCode()+"栋"+voteHouseInfo.getUnitCode()+"单元"
					+voteHouseInfo.getFloorCode()+"层"+voteHouseInfo.getRoomCode()+"室";
			house.setDetailedAddress(address);
			//house.setAttachment("<a href=\""+request.getContextPath()+"/resources/upload/image/IDcard/"
					//+result.getCreateDate()+"/"+result.getCreateTime()+"\">附件</a>");
			house.setAttachment("<a href=\""+request.getContextPath()+"/result/downloadfile?result="+result.getResultId()+"\">附件</a>");
			voteList.add(house);
		}
		PageInfo<VoteResultInfo> pageInfo = new PageInfo<VoteResultInfo>(resultList);
		resMap.put("pageInfo", pageInfo);
		resMap.put("listInfo", voteList);
		
		return resMap;
	}

	/*
	@Override
	public Map<String, Object> getUnvoteList(String topicId, String currentPage) {
		// TODO Auto-generated method stub
		Map<String, Object> resMap = new HashMap<>();
		
		PageHelper.startPage(Integer.parseInt(currentPage), 2);
		List<VoteHouseInfo> houseList = voteHouseInfoMapper.selectUnvoteByTopicId(topicId);
		PageInfo<VoteHouseInfo> pageInfo = new PageInfo<VoteHouseInfo>(houseList);
		resMap.put("pageInfo", pageInfo);
		resMap.put("listInfo", houseList);
		
		return resMap;
	}*/
	@Override
	public Map<String, Object> getUnvoteList(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String, Object> resMap = new HashMap<>();
		String currPage;
		
		if (!StringUtil.isEmpty((String)requestMap.get("currPage"))){
			currPage = (String)requestMap.get("currPage");
		}else {
			currPage = "1";
		}
		
		PageHelper.startPage(Integer.parseInt(currPage), InitSysConstants.BIG_SIZE);
		List<VoteHouseInfo> houseList = voteHouseInfoMapper.selectUnvoteByTopicId(requestMap);
		PageInfo<VoteHouseInfo> pageInfo = new PageInfo<VoteHouseInfo>(houseList);
		resMap.put("pageInfo", pageInfo);
		resMap.put("listInfo", houseList);
		
		return resMap;
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Map<String, Object> submit(MultipartFile[] myfiles, String topicId, String infoId, String result) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("status", false);
		
		VoteResultInfoExample  example = new VoteResultInfoExample();
		Criteria crite = example.createCriteria();
		crite.andTopicIdEqualTo(new BigDecimal(topicId));
		crite.andInfoIdEqualTo(new BigDecimal(infoId));
		long counts = voteResultInfoMapper.countByExample(example);
		if (counts > 0) {
			logger.error("该房屋已经对该议题投过票， 不能再投");
			return map;
		}
		
		
		/*查询投票结果汇总表，暂定发布议题时插入初始数据*/
		VoteResultSum voteResultSum = voteResultSumMapper.selectByPrimaryKey(new BigDecimal(topicId));
		if (voteResultSum == null) {
			logger.error("汇总表没有初始化");
			return map;
		}
		Long sumCount = voteResultSum.getSumCount(); //总房屋数量
		BigDecimal sumArea = voteResultSum.getSumArea(); //总面积
		
		VoteResultInfo voteResultInfo = new VoteResultInfo();
		voteResultInfo.setResultId(new BigDecimal(primaryKey.getKeyBySequenceName("RESULT_ID_SEQ")));
		voteResultInfo.setTopicId(new BigDecimal(topicId));
		voteResultInfo.setCreateDate(DateUtil.getSysDate());
		voteResultInfo.setCreateTime(DateUtil.getSysTime());
		voteResultInfo.setVoteWays("manual"); //补录
		voteResultInfo.setVoteResult(result);
		voteResultInfo.setInfoId(new BigDecimal(infoId));
		voteResultInfo.setVoteDate(DateUtil.getSysDate());
		voteResultInfo.setVoteTime(DateUtil.getSysTime());
		voteResultInfoMapper.insertSelective(voteResultInfo);
		
		VoteResultInfoExample voteResultInfoExample = new VoteResultInfoExample();
		Criteria criteria = voteResultInfoExample.createCriteria();
		criteria.andTopicIdEqualTo(new BigDecimal(topicId));
		criteria.andVoteResultEqualTo(result);
		//总房屋数
		Long Count = voteResultInfoMapper.countByExample(voteResultInfoExample);
		BigDecimal area = voteResultInfoMapper.selectSumArea(topicId, result);
		if (result.equals("01")) {//同意
			voteResultSum.setAgreeArea(area);
			voteResultSum.setAgreeHou(Count);
			voteResultSum.setAgreeHouRate(new BigDecimal(Count).divide(new BigDecimal(sumCount), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
			voteResultSum.setAgreeAreaRate(area.divide(sumArea, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
		}else if (result.equals("02")) {//不同意
			voteResultSum.setUnagreeHou(Count);
			voteResultSum.setUnagreeArea(area);
			voteResultSum.setUnagreeHouRate(new BigDecimal(Count).divide(new BigDecimal(sumCount), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
			voteResultSum.setUnagreeAreaRate(area.divide(sumArea, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
		}
		
		voteResultSum.setNonvoteHou(sumCount-voteResultSum.getAgreeHou()-voteResultSum.getUnagreeHou());
		voteResultSum.setNonvoteHouRate(new BigDecimal(voteResultSum.getNonvoteHou()).divide(new BigDecimal(sumCount), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
		voteResultSum.setNonvoteArea(sumArea.subtract(voteResultSum.getAgreeArea()).subtract(voteResultSum.getUnagreeArea()));
		voteResultSum.setNonvoteAreaRate(voteResultSum.getNonvoteArea().divide(sumArea, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
		
		voteResultSumMapper.updateByPrimaryKeySelective(voteResultSum);
		
		String path = InitSysConstants.CERT_PATH +voteResultInfo.getVoteDate()+"/";
		String fileName = voteResultInfo.getVoteTime();
		
		FileUpload fileupload = new FileUpload();
		fileupload.setFileId(Long.parseLong(primaryKey.getKeyBySequenceName("FILE_ID_SEQ")));
		fileupload.setFileName(fileName);
		fileupload.setUploadDate(DateUtil.getSysDate());
		fileupload.setFileAddre(path);
		fileupload.setRelaTable("VOTE_RESULT_INFO");
		fileupload.setRelaTabId(voteResultInfo.getResultId().longValue());
		fileupload.setOldFileName(myfiles[0].getOriginalFilename());
		fileupload.setFileType(myfiles[0].getContentType().split("/")[1]);
		fileUploadMapper.insertSelective(fileupload);
		
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(path+fileName+"."+fileupload.getFileType()));
            BufferedInputStream is = new BufferedInputStream( myfiles[0].getInputStream());
            int temp;
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();
		} catch (Exception e) {
			// TODO: handle exceptioion
			logger.error("io error: ",e);
			throw new RuntimeException("io error");
		}
		
		map.put("status", true);
		
		return map;
	}

	@Override
	public ResponseEntity<byte[]> download(String result) {
		// TODO Auto-generated method stub
		FileUpload load = new FileUpload();
		load.setRelaTable("VOTE_RESULT_INFO");
		load.setRelaTabId(Long.parseLong(result));
		List<FileUpload> loadList = fileUploadMapper.selectByPrimaryKey(load);
		FileUpload res = loadList.get(0);
		
		byte[] body = null;
		try {
			File file = new File(res.getFileAddre()+res.getFileName()+"."+res.getFileType());
			body = FileUtils.readFileToByteArray(file);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attchement", res.getFileName()+"."+res.getFileType());
		
		return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
	}

}
