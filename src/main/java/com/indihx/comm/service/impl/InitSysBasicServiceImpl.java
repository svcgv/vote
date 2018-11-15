package com.indihx.comm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.indihx.AbstractBaseService;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.service.IInitSysBasicService;
import com.indihx.comm.util.BasicParameterInfo;
import com.indihx.datamng.dao.HpbMapper;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.system.dao.CodeDataMapper;
import com.indihx.system.dao.ParamsInfoMapper;
import com.indihx.system.dao.UsrInfoMapper;
import com.indihx.system.entity.CodeData;
import com.indihx.system.entity.ParamsInfo;
import com.indihx.system.entity.UsrInfo;

/***
 * 
 * <p>描 述: 初始化基本信息</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年4月12日</p>
 * @author 谢追辉
 */
@Service
public class InitSysBasicServiceImpl extends AbstractBaseService implements IInitSysBasicService {
	
	private static Logger logger = LoggerFactory.getLogger(InitSysBasicServiceImpl.class);
	
	@Resource
	private CodeDataMapper codeMapper;
	
	@Resource
	private ParamsInfoMapper paramsMapper;
	
	@Resource
	private HpbMapper hpbMapper;
	
	@Resource
	private UsrInfoMapper userMapper; 
	
	@Override
	public void initCodeDataAll() {
		logger.info("--->1.0 开始加载数据字典项");
		Map<String, List<CodeData>> map = new HashMap<String,List<CodeData>>();
		List<String> list = codeMapper.qryCodeNo();
		CodeData codeData = new CodeData();
		for (String codeNo : list) {
			codeData.setCodeNo(codeNo);
			List<CodeData> codeList = codeMapper.qryCodeDataAll(codeData);
			map.put(codeNo, codeList);
		}
		BasicParameterInfo.setCodeMap(map); //把数据字段的数据存到缓存中
		logger.info("--->1.0 数据字典项加载完毕");
	}

	@Override
	public void initParamsInfo() {
		logger.info("--->2.0 开始加载参数信息");
		Map<String, ParamsInfo> map = new HashMap<String,ParamsInfo>();
		List<ParamsInfo> list = paramsMapper.qryParamsInfoAll(new ParamsInfo());
		for (ParamsInfo paramsInfo : list) {
			map.put(paramsInfo.getParamsNo(), paramsInfo);
		}
		BasicParameterInfo.setParmsMap(map);
		logger.info("--->2.0 参数信息加载完毕");
	}

	@Override
	public void initRegion() {
		logger.info("--->3.0 开始分区区域信息");
		Wy_Hpb hpb = new Wy_Hpb();
		hpb.setHpblx("01");  //查找区的记录保存
		List<Wy_Hpb> list01 =hpbMapper.getHpbListByType(hpb); //查询区信息
		hpb.setHpblx("00"); 
		List<Wy_Hpb> list00 =hpbMapper.getHpbListByType(hpb); //查询市的信息
		hpb.setHpblx("02"); 
		List<Wy_Hpb> list02 =hpbMapper.getHpbListByType(hpb); //查询县的信息
		Map<String, List<Wy_Hpb>> map = new HashMap<String, List<Wy_Hpb>>();
		map.put("00", list00);
		map.put("01", list01);
		map.put("02", list02);
		BasicParameterInfo.setRegionMap(map);
		logger.info("--->3.0 分区区域信息加载完毕");
	}
	
	/**
	 * 清理session
	 * @param infoVo
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
	public void clearSession(){
		try {
			logger.info("--->0.0 开始清理用户Session");
			UsrInfo infoVo = new UsrInfo();
			infoVo.setUsrSta("0");
			userMapper.clearUserSession(infoVo);
			logger.info("--->0.0 清理用户Session完毕");
		} catch (Exception e) {
			throw new BusinessException("清理用户session失败："+ExceptionUtil.getErrorMsg(e));
		}
	}
}
