/**
 * 
 */
package com.indihx.credit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.AbstractBaseService;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.credit.commons.CreditConstants;
import com.indihx.credit.dao.CreditQuotaMapper;
import com.indihx.credit.entity.CreditQuota;
import com.indihx.credit.service.ICreditQuotaService;
import com.indihx.credit.vo.QuotaVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.Assert;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: QuotaServiceImpl.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月2日下午6:04:22</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>QuotaServiceImpl.java</p>
 * <p></p>
 */
@Service
public class CreditQuotaServiceImpl extends AbstractBaseService  implements ICreditQuotaService{

	@Resource
	private CreditQuotaMapper creditQuotaMapper;
	@Autowired
	private PrimaryKey primaryKey;
	
	/* (获取诚信指标列表)
	 * @see com.indihx.credit.service.IQuotaService#getQuotaList(com.indihx.system.entity.UsrInfo, com.indihx.credit.vo.QuotaVo)
	 */
	@Override
	public Map<String, Object> getQuotaList(UsrInfo user, QuotaVo quotaVo){
		try{
			Assert.notNull(user, "用户信息不能为空！");
			List<CreditQuota> listInfo = creditQuotaMapper.getQuotaList(quotaVo);
			//设置分页
			PageHelper.startPage(quotaVo.getPages(), quotaVo.getRows());
			PageInfo<CreditQuota> pageInfo = new PageInfo<CreditQuota>(listInfo);
			//响应页面数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageInfo", pageInfo);
			map.put("listInfo", listInfo);
			return map;
		}catch(Exception e){
			throw new BusinessException("查询数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (保存诚信指标信息)
	 * @see com.indihx.credit.service.IQuotaService#saveQuotaInfo(com.indihx.system.entity.UsrInfo, com.indihx.credit.vo.QuotaVo)
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public String saveQuotaInfo(UsrInfo usrInfo, QuotaVo infoVo) throws Exception{
		try{
			Assert.hasText(infoVo.getJfbz(), "记分标志不能为空!");
			Assert.hasText(infoVo.getZbbm(), "指标编码不能为空!");
			Assert.hasText(infoVo.getJfyj(), "记分依据不能为空!");
			Assert.hasText(infoVo.getCkfz(), "记分分值不能为空!");
			infoVo.setCredit_seq(primaryKey.getPrefixDatePrimaryKey(8).toString());
			CreditQuota quota = creditQuotaMapper.getQuotaByCode(infoVo);
			if(!ObjectUtil.isEmpty(quota)){
				throw new BusinessException("不能重复保存，指标编码"+infoVo.getZbbm()+"已经存在!");
			}
			if(infoVo.getJfbz().equals("加分")){
				infoVo.setZblx(InitSysConstants.CreditQuotaKind_LH);
			}else{
				infoVo.setZblx(InitSysConstants.CreditQuotaKind_BL);
			}
			infoVo.setCjrq(DateUtil.getSysDate());
			infoVo.setCjsj(DateUtil.getSysTime());
			infoVo.setOrg_no(usrInfo.getOrgNo().toString());
			infoVo.setCzybh(usrInfo.getUsrId().toString());
			infoVo.setQyrq(DateUtil.getSysDate());
			int result =creditQuotaMapper.saveQuota(infoVo);
			if(result!=1){
				throw new BusinessException("保存诚信指标失败!");
			}
			return "保存诚信指标信息成功!";
		}catch(Exception e){
			return "保存诚信指标信息失败："+ExceptionUtil.getErrorMsg(e);
		}
	}

	/* (获取默认的指标编码)
	 * @see com.indihx.credit.service.ICreditQuotaService#getQuotaDefaultCode()
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public String getQuotaDefaultCode() {
		try{
			return primaryKey.getPrefixDatePrimaryKey(4).toString();
		}catch(Exception e){
			throw new BusinessException(ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (根据指标ID删除指标信息)
	 * @see com.indihx.credit.service.ICreditQuotaService#deleteQuotaInfo(com.indihx.system.entity.UsrInfo, com.indihx.credit.vo.QuotaVo)
	 */
	@Override
	public String deleteQuotaInfo(UsrInfo usrInfo, QuotaVo infoVo) {
		try{
			Assert.hasText(infoVo.getCredit_seq(), "ID不能为空!");
			int result =creditQuotaMapper.deleteQuota(infoVo.getCredit_seq());
			if(result!=1){
				throw new BusinessException("删除诚信指标失败!");
			}
			return "删除诚信指标成功!";
		}catch(Exception e){
			throw new BusinessException(ExceptionUtil.getErrorMsg(e));
		}
	}
	
	/* (保存诚信指标信息)
	 * @see com.indihx.credit.service.IQuotaService#updateQuotaInfo(com.indihx.system.entity.UsrInfo, com.indihx.credit.vo.QuotaVo)
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public String updateQuotaInfo(UsrInfo usrInfo, QuotaVo infoVo) throws Exception{
		try{
			Assert.hasText(infoVo.getCredit_seq(), "记分指标ID不能为空!");
			Assert.hasText(infoVo.getJfbz(), "记分标志不能为空!");
			Assert.hasText(infoVo.getZbbm(), "指标编码不能为空!");
			Assert.hasText(infoVo.getJfyj(), "记分依据不能为空!");
			Assert.hasText(infoVo.getCkfz(), "记分分值不能为空!");
			infoVo.setCredit_seq(infoVo.getCredit_seq());
			if(infoVo.getJfbz().equals("加分")){
				infoVo.setZblx(InitSysConstants.CreditQuotaKind_LH);
			}else{
				infoVo.setZblx(InitSysConstants.CreditQuotaKind_BL);
			}
			infoVo.setCjrq(DateUtil.getSysDate());
			infoVo.setCjsj(DateUtil.getSysTime());
			infoVo.setOrg_no(usrInfo.getOrgNo().toString());
			infoVo.setCzybh(usrInfo.getUsrId().toString());
			infoVo.setQyrq(DateUtil.getSysDate());
			int result =creditQuotaMapper.updateQuota(infoVo);
			if(result!=1){
				throw new BusinessException("修改诚信指标失败!");
			}
			return "修改诚信指标信息成功!";
		}catch(Exception e){
			return "修改诚信指标信息失败："+ExceptionUtil.getErrorMsg(e);
		}
	}

	/* (根据指标ID获取指标详情)
	 * @see com.indihx.credit.service.ICreditQuotaService#getQuotaInfo(java.lang.String)
	 */
	@Override
	public CreditQuota getQuotaInfo(String credit_seq) {
		try{
			Assert.hasText(credit_seq, "记分指标ID不能为空!");
			CreditQuota result =creditQuotaMapper.getQuotaInfo(credit_seq);
			return result;
		}catch(Exception e){
			throw new BusinessException(ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (作废指标)
	 * @see com.indihx.credit.service.ICreditQuotaService#validQuotaInfo(com.indihx.system.entity.UsrInfo, com.indihx.credit.vo.QuotaVo)
	 */
	@Override
	public String validQuotaInfo(UsrInfo usrInfo, QuotaVo infoVo) {
		try{
			Assert.hasText(infoVo.getCredit_seq(), "ID不能为空!");
			infoVo.setSxrq(DateUtil.getSysDate());
			infoVo.setStatus(CreditConstants.QuotaStatus_SX);
			int result =creditQuotaMapper.updateQuotaStatus(infoVo);
			if(result!=1){
				throw new BusinessException("禁用诚信指标失败!");
			}
			return "禁用诚信指标成功!";
		}catch(Exception e){
			throw new BusinessException(ExceptionUtil.getErrorMsg(e));
		}
	}
	

}
