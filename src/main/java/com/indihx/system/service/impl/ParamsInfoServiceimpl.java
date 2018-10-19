package com.indihx.system.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.AbstractBaseService;
import com.indihx.comm.util.BasicParameterInfo;
import com.indihx.comm.util.DateUtil;
import com.indihx.system.dao.ParamsInfoMapper;
import com.indihx.system.entity.ParamsInfo;
import com.indihx.system.service.IParamsInfoService;
import com.indihx.system.vo.ParamsInfoVo;
import com.indihx.util.ConstantStatic;
import com.indihx.util.EntityVoConverter;
/** 
 * 项目名称：wx_mng_maven 
 * 类名称：RoleInfoServiceImpl 
 * 类描述： 参数管理
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月9日 下午2:24:16 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
@Service
public class ParamsInfoServiceimpl  extends AbstractBaseService implements IParamsInfoService{
	private static Logger logger = LoggerFactory.getLogger(ParamsInfoServiceimpl.class);
	@Autowired
	private ParamsInfoMapper infomapper;
	
	/**
	 * 主页面
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> qryParamsInfo(ParamsInfoVo infovo) {
		ParamsInfo params=new ParamsInfo();
		params.setParamsName(infovo.getParamsName());
		params.setParamsNo(infovo.getParamsVal());
		params.setParamsType(infovo.getParamsType());
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(infovo.getPages(), infovo.getRows(), true);
		List<ParamsInfo> listInfo=infomapper.qryParamsInfoAll(params);
		List<ParamsInfoVo> listVo = new ArrayList<ParamsInfoVo>();
		for (ParamsInfo ruleInfo2 : listInfo) {
			ParamsInfoVo infoVo = new ParamsInfoVo();
			EntityVoConverter.Convert(ruleInfo2, infoVo);
			infoVo.setParamsType(BasicParameterInfo.getCodeVal("PARAMS_TYPE", ruleInfo2.getParamsType()));
			listVo.add(infoVo);
		}
		PageInfo pageInfo = new PageInfo(listInfo);
		map.put("listInfo", listVo);
		map.put("pageInfo", pageInfo);
		return map;
	}
	@Override
	public Map<String, Object> qryAddInfo(){
		// 查询预警主体
		String ewTypeHtml = ConstantStatic.createHtmlByCode("PARAMS_TYPE");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ewTypeHtml", ewTypeHtml);
		return map;
	}
    /**
     * 新增保存
     */
	@Override
	public boolean addParamsInfo(ParamsInfoVo infovo) {
		ParamsInfo params=new ParamsInfo();
		params.setParamsNo(infovo.getParamsNo());
		params.setParamsName(infovo.getParamsName());
		params.setParamsVal(infovo.getParamsVal());
		params.setTmSmp(DateUtil.getSysDate());
		params.setParamsType(infovo.getParamsType());
       int num=infomapper.insertSelective(params);
       if (num > 0) {
			return true;
		}
		return false;
	
	}
	/***
	 * 根据用户ID查询参数信息
	 * @param infoVo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public ParamsInfo qryParamsInfoList(String  paramsNo) {
		String paraId = paramsNo.replaceAll(",", "").trim();
		return infomapper.selectByPrimaryKey(paraId);
	}
	
	/***
	 * 编辑信息
	 * @param infoVo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public ParamsInfo qryParamsInfoList1(String  paramsNo) {
		String paraId = paramsNo.replaceAll(",", "").trim();
		ParamsInfo info = infomapper.selectByPrimaryKey(paraId);
		ParamsInfo infoVo = new ParamsInfo();
		EntityVoConverter.Convert(info, infoVo);
		// 查询预警主体
		String ewTypeHtml = ConstantStatic.createHtmlByCode("PARAMS_TYPE",infoVo.getParamsType());
		infoVo.setEwTypeHtml(ewTypeHtml);
		return infoVo;
	}
	/**
	 * 编辑保存
	 * @param infoVo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean updParamsInfo(ParamsInfoVo infovo) {
		try {
			ParamsInfo params=new ParamsInfo();
			String paraId = infovo.getParamsNo().replaceAll(",", "").trim();
			params.setParamsNo(paraId);
			params.setParamsName(infovo.getParamsName());
			params.setParamsVal(infovo.getParamsVal());
			params.setTmSmp(DateUtil.getSysDate());
			params.setParamsType(infovo.getParamsType());
			int upd=infomapper.updateByPrimaryKeySelective(params);
			if(upd<1) {
				return false;	
			}
		} catch (Exception e) {
			logger.info("系统异常！！");
			e.printStackTrace();
			//使用trycatch时，必须手动加上事务回滚，spring默认只会回滚runtimeException异常才回回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();      
		}
		
		return true;
	}
	/***
	 * 删除
	 * @param infoVo
	 * @return
	 */
	@Override
	public boolean deleParamsInfo(ParamsInfoVo infovo) {
		String paraId = infovo.getParamsNo();
		String[] str = paraId.split(",");
		for (int i = 0; i < str.length; i++) {
			if (!StringUtils.isEmpty(str[i])) {
				infomapper.deleteByPrimaryKey(str[i]);
			}
		}
		return true;
	}

}
