package com.indihx.wechat.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.dao.FileUploadMapper;
import com.indihx.comm.entity.FileUpload;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.HttpUtils;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.elecvote.dao.VoteApplyInfoMapper;
import com.indihx.elecvote.dao.VoteResultInfoMapper;
import com.indihx.elecvote.dao.VoteResultSumMapper;
import com.indihx.elecvote.dao.VoteScopeInfoMapper;
import com.indihx.elecvote.dao.VoteTopicInfoMapper;
import com.indihx.elecvote.entity.VoteApplyInfo;
import com.indihx.elecvote.entity.VoteApplyInfoExample;
import com.indihx.elecvote.entity.VoteResultInfo;
import com.indihx.elecvote.entity.VoteResultInfoExample;
import com.indihx.elecvote.entity.VoteResultInfoExample.Criteria;
import com.indihx.system.dao.ParamsInfoMapper;
import com.indihx.system.entity.ParamsInfo;
import com.indihx.system.listener.ApplicationStartListener;
import com.indihx.util.StringUtils;
import com.indihx.elecvote.entity.VoteResultSum;
import com.indihx.elecvote.entity.VoteScopeInfo;
import com.indihx.elecvote.entity.VoteTopicInfo;
import com.indihx.wechat.service.VoteInfoService;
import com.indihx.wechat.service.WeChatPlatformService;
import com.indihx.wechat.vo.AccessToken;
import com.indihx.wechat.vo.Attachment;

@Service
public class VoteInfoServiceImpl implements VoteInfoService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private VoteResultInfoMapper voteResultInfoMapper;
	@Autowired
	private VoteScopeInfoMapper  voteScopeInfoMapper;
	@Autowired
	private VoteTopicInfoMapper voteTopicInfoMapper;
	@Autowired
	private VoteResultSumMapper voteResultSumMapper;
	@Autowired
	private PrimaryKey primaryKey;
	@Autowired 
	private FileUploadMapper fileUploadMapper;
	@Autowired
	private WeChatPlatformService weChatPlatformService;
	@Autowired
	private ParamsInfoMapper paramsInfoMapper;
	@Autowired
	private VoteApplyInfoMapper voteApplyInfoMapper;

	@Override
	public Map<String, Object> getVotedInfo(String openId) {
		// TODO Auto-generated method stub
		List<VoteTopicInfo> listInfo = voteTopicInfoMapper.selectVotedByOpenId(openId);
		try {
			logger.debug("listinfo: "+new ObjectMapper().writeValueAsString(listInfo));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error: ", e);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("listInfo", listInfo);
		return map;
	}

	@Override
	public Map<String, Object> getUnvotedInfo(String openId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		/*
		VoteApplyInfoExample voteApplyInfoExample = new VoteApplyInfoExample();
		com.indihx.elecvote.entity.VoteApplyInfoExample.Criteria criteria  = voteApplyInfoExample.createCriteria();
		criteria.andUserIdEqualTo(openId);
		List<VoteApplyInfo>  applyList = voteApplyInfoMapper.selectByExample(voteApplyInfoExample);
		if (applyList.isEmpty()) {
			map.put("status", "NOTREGESTER");
		}*/
		
		List<VoteTopicInfo> listInfo = voteTopicInfoMapper.selectUnvotedByOpenId(openId);
		try {
			logger.debug("listinfo: "+new ObjectMapper().writeValueAsString(listInfo));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("parse json error: ", e);
		}
		map.put("listInfo", listInfo);
		return map;
	}

	@Override
	public Map<String, Object> getVotedInfoDetails(String topicId) {
		// TODO Auto-generated method stub
		VoteTopicInfo voteTopicInfo = voteTopicInfoMapper.selectByPrimaryKey(new BigDecimal(topicId));
		VoteResultInfoExample voteResultInfoExample = new VoteResultInfoExample();
		Criteria criteria = voteResultInfoExample.createCriteria();
		criteria.andTopicIdEqualTo(new BigDecimal(topicId));
		List<VoteResultInfo> list = voteResultInfoMapper.selectByExample(voteResultInfoExample);
		Map<String, Object> map = new HashMap<>();
		map.put("topic", voteTopicInfo);
		map.put("result", list.get(0).getVoteResult());
		return map;
	}

	@Override
	public Map<String, Object> getUnvotedInfoDetails(String topicId) {
		// TODO Auto-generated method stub
		VoteTopicInfo voteTopicInfo = voteTopicInfoMapper.selectByPrimaryKey(new BigDecimal(topicId));
		Map<String, Object> map = new HashMap<>();
		map.put("topic", voteTopicInfo);
		return map;
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Map<String, Object> voteSubmit(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("status", false);
		String topicId = (String)requestMap.get("topicId");
		String openId = (String)requestMap.get("openId");
		String result = (String)requestMap.get("result");
		String serverId = (String)requestMap.get("serverId");
		VoteTopicInfo voteTopicInfo = voteTopicInfoMapper.selectByPrimaryKey(new BigDecimal(topicId));
		//if (voteTopicInfo.getVoteStatus().equals("04") || voteTopicInfo.getVoteStatus().equals("05")) {
		if (!voteTopicInfo.getVoteStatus().equals(InitSysConstants.VoteStatus_BiaoJueZhong)) {
			logger.error("该议题已经过期，不能投票");
			return map;
		}
		
		if (voteTopicInfo.getEffectiveStatus().equals(InitSysConstants.Vote_NONEFFECTIVE)) {
			logger.error("该议题无效，不能投票");
			return map;
		}
		
		VoteResultInfoExample  example = new VoteResultInfoExample();
		Criteria crite = example.createCriteria();
		crite.andTopicIdEqualTo(new BigDecimal(topicId));
		//crite.andInfoIdEqualTo(new BigDecimal(infoId));
		crite.andUserIdEqualTo(openId);
		long counts = voteResultInfoMapper.countByExample(example);
		if (counts > 0) {
			logger.error("该用户已经对该议题投过票， 不能再投");
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
		voteResultInfo.setTopicId(new BigDecimal(topicId));
		voteResultInfo.setCreateDate(DateUtil.getSysDate());
		voteResultInfo.setCreateTime(DateUtil.getSysTime());
		voteResultInfo.setVoteWays("wechat");
		voteResultInfo.setVoteResult(result);
		voteResultInfo.setUserId(openId);
		voteResultInfo.setVoteDate(DateUtil.getSysDate());
		voteResultInfo.setVoteTime(DateUtil.getSysTime());
		
		String path = InitSysConstants.CERT_PATH+voteResultInfo.getVoteDate()+"/";
		String fileName = voteResultInfo.getVoteTime();
		
		//从微信服务器下载文件
		logger.info("before download media");
		Header [] headers = downloadMedia(serverId, path, fileName);
		logger.info("after download media");
		
		FileUpload fileupload = new FileUpload();
		for (Header header: headers) {
    		//System.out.println(header.getName()+" : "+header.getValue());
    		if (header.getName().equals("Content-Type")) {
    			//System.out.println(header.getValue().split("/")[1]);
    			fileupload.setFileType(header.getValue().split("/")[1]);
    		}
    		if (header.getName().equals("Content-disposition")) {
    			String tmp = header.getValue();
    			//System.out.println("filename: " + tmp.substring(tmp.indexOf("filename")+10, tmp.length()-1));
    			fileupload.setOldFileName(tmp.substring(tmp.indexOf("filename")+10, tmp.length()-1));
    		}
    	}
		
		fileupload.setFileName(fileName);
		fileupload.setUploadDate(DateUtil.getSysDate());
		fileupload.setFileAddre(path);
		fileupload.setRelaTable("VOTE_RESULT_INFO");
		
		//查询该议题对应的房屋Id，如果对应该议题的多个房屋都有投票权，则用户拥有多票
		List<VoteScopeInfo> votescopeInfolist = voteScopeInfoMapper.selectByOpenId(topicId, openId);
		for (VoteScopeInfo votescopeInfo: votescopeInfolist) {
			/*不再使用累计的方式，改用从vote_result_info中计算结果*/
			/*
			if (result.equals("01")) {//同意
				Long agreeHou = voteResultSum.getAgreeHou();
				BigDecimal agreeArea = voteResultSum.getAgreeArea();
				voteResultSum.setAgreeHou(agreeHou+1);
				voteResultSum.setAgreeArea(agreeArea.add(votescopeInfo.getInfoArea()));
			}else if (result.equals("02")) {//不同意
				Long unagreeHou = voteResultSum.getUnagreeHou();
				BigDecimal unagreeArea = voteResultSum.getUnagreeArea();
				voteResultSum.setUnagreeHou(unagreeHou+1);
				voteResultSum.setUnagreeArea(unagreeArea.add(votescopeInfo.getInfoArea()));
			}
			*/
			voteResultInfo.setResultId(new BigDecimal(primaryKey.getKeyBySequenceName("RESULT_ID_SEQ")));
			voteResultInfo.setInfoId(votescopeInfo.getInfoId());
			voteResultInfoMapper.insertSelective(voteResultInfo);
			
			fileupload.setFileId(Long.parseLong(primaryKey.getKeyBySequenceName("FILE_ID_SEQ")));
			fileupload.setRelaTabId(voteResultInfo.getResultId().longValue());
			fileUploadMapper.insertSelective(fileupload);
		}
		
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
		/*
		VoteResultInfoExample voteResultInfoExample = new VoteResultInfoExample();
		Criteria criteriaAgree = voteResultInfoExample.createCriteria();
		criteriaAgree.andTopicIdEqualTo(new BigDecimal(topicId));
		criteriaAgree.andVoteResultEqualTo(result);
		Long agreeCount = voteResultInfoMapper.countByExample(voteResultInfoExample);
		
		Criteria criteriaUnagree = voteResultInfoExample.createCriteria();
		criteriaUnagree.andTopicIdEqualTo(new BigDecimal(topicId));
		criteriaUnagree.andVoteResultEqualTo(result)
		Long unagreeCount = */
		
		
		/*上传文件改成使用微信jsapi
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
            OutputStream os=new FileOutputStream(path+"/"+fileName);
            InputStream is=myfiles[0].getInputStream();
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
		}*/
		
		map.put("status", true);
		return map;
	}
	
	private Header []  downloadMedia(String mediaId, String path, String filename) {
		String token = (String)ApplicationStartListener.redisCache.getObject("accessToken");
		if (StringUtils.isEmpty(token)) {
			ParamsInfo  param1 = paramsInfoMapper.selectByPrimaryKey("APPID");
			ParamsInfo  param2 = paramsInfoMapper.selectByPrimaryKey("SECRET");
			AccessToken accessToken = weChatPlatformService.getAccessToken(param1.getParamsVal(), param2.getParamsVal()); 
			ApplicationStartListener.redisCache.putObject("accessToken", accessToken.getAccess_token());
			token = accessToken.getAccess_token();
		}
		String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + token + "&media_id=" + mediaId;
		logger.info("get media url: "+url);
		
		return HttpUtils.httpsDownload(url, path, filename);
	}
}
