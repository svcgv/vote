/**
 * 
 */
package com.indihx.credit.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.indihx.activiti.ActivitiBusiness;
import com.indihx.activiti.FlowKind;
import com.indihx.activiti.IProcessServiceDao;
import com.indihx.activiti.dao.ApplicationMapper;
import com.indihx.activiti.entity.Application;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.InitSysFileDocs;
import com.indihx.comm.dao.FileUploadMapper;
import com.indihx.comm.entity.FileUpload;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.credit.commons.CreditConstants;
import com.indihx.credit.dao.CreditAppealMapper;
import com.indihx.credit.dao.CreditBadRecordMapper;
import com.indihx.credit.dao.CreditFileSignMapper;
import com.indihx.credit.dao.CreditQuotaMapper;
import com.indihx.credit.entity.CreditAppeal;
import com.indihx.credit.entity.CreditBadQuotaTemp;
import com.indihx.credit.entity.CreditBadRecord;
import com.indihx.credit.entity.CreditBadRecordTemp;
import com.indihx.credit.entity.CreditFileSign;
import com.indihx.credit.service.ICreditAppealService;
import com.indihx.credit.vo.BadQuotaVo;
import com.indihx.credit.vo.CreditBadVo;
import com.indihx.datamng.dao.HpbMapper;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.Assert;
import com.indihx.util.ConstantStatic;
import com.indihx.util.EntityVoConverter;
import com.indihx.util.MapToEntryConvertUtils;
import com.indihx.util.StringUtils;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CreditApealServiceImpl.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月28日上午8:23:39</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>CreditApealServiceImpl.java</p>
 * <p></p>
 */
@Service
public class CreditApealServiceImpl extends ActivitiBusiness implements ICreditAppealService{

	@Resource
	private HpbMapper hpbMapper;
	@Resource
	private CreditQuotaMapper creditQuotaMapper;
	@Resource
	private CreditBadRecordMapper creditBadRecordMapper;
	@Resource
	private CreditAppealMapper creditAppealMapper;
	@Resource
	private ApplicationMapper applicationMapper;
	@Resource
	private FileUploadMapper fileUploadMapper;
	@Resource
	private CreditFileSignMapper signMapper;
	@Resource
	private IProcessServiceDao activitiService;
	@Autowired
	private PrimaryKey primaryKey;
	
	/* (临时数据与历史数据操作处理)
	 * @see com.indihx.activiti.ActivitiBusiness#handCopy(com.indihx.activiti.entity.Application)
	 */
	@Override
	public void handCopy(Application app) {
		//此处无需处理
	}

	/* (流程结束处理逻辑)
	 * @see com.indihx.activiti.ActivitiBusiness#processEnd(com.indihx.activiti.entity.Application)
	 */
	@Override
	public void processEnd(Application app) {
		Assert.notNull(app.getAppId(), "流程ID不能为空!");
		
		//异议申诉基本信息
		CreditAppeal infoVo = new CreditAppeal();
		infoVo.setApp_id(app.getAppId().toString());
		CreditAppeal appeal = creditAppealMapper.getAppealInfo(infoVo);
		if(ObjectUtil.isEmpty(appeal)){
			throw new BusinessException("获取异议申诉申请信息失败!");
		}
		
		//将临时表的档案提交正式表
		CreditBadVo badVo = new CreditBadVo();
		badVo.setCredit_code(appeal.getCredit_code());
		CreditBadRecordTemp tmep = creditBadRecordMapper.getBadRecordTemp(badVo);
		if(tmep==null){
			throw new BusinessException("未找到提交的诚信档案。");
		}
		badVo.setCredit_status(CreditConstants.RecordStatus_GD);//归档状态
		int res = creditBadRecordMapper.updateCreditInfoTemp(badVo);
		if(res!=1){
			throw new BusinessException("诚信档案建档失败!");
		}
		CreditBadRecord record = new CreditBadRecord();
		EntityVoConverter.Convert(tmep, record);
		record.setGdrq(DateUtil.formatFromDB(DateUtil.getSysDate()));
		record.setGdsj(DateUtil.formatTimeFromDB(DateUtil.getSysTime()));
		record.setGdczybh(app.getCurrUserId());
		record.setCredit_status(CreditConstants.RecordStatus_GD);//归档状态
		creditBadRecordMapper.saveCreditBadInfo(record);
		
		//将涉及的指标信息提交正式表
		creditBadRecordMapper.saveCreditQuotaInfo(record.getCredit_code());
	}

	/* (提交前业务逻辑)
	 * @see com.indihx.activiti.ActivitiBusiness#handCommitFirst(com.indihx.activiti.entity.Application, java.util.Map)
	 */
	@Override
	public Map<String, Object> handCommitFirst(Application app,
			Map<String, Object> variables) {
		//异议申诉基本信息
		CreditAppeal infoVo = new CreditAppeal();
		infoVo.setApp_id(app.getAppId().toString());
		CreditAppeal appeal = creditAppealMapper.getAppealInfo(infoVo);
		if(ObjectUtil.isEmpty(appeal)){
			throw new BusinessException("获取异议申诉申请信息失败!");
		}
		int result= 0;
		if(app.getCurrRoleId().equals(InitSysConstants.ROLE_WYGS)){//当时物业公司待办时
			//如果是物业公司提交，提交后将申诉状态修改
			infoVo.setAppeal_status(CreditConstants.AppealStatus_APPLY);
			infoVo.setAppeal_code(appeal.getAppeal_code());
			result=creditAppealMapper.updateCreditAppealInfo(infoVo);
		}else if(app.getCurrRoleId().equals(InitSysConstants.ROLE_QUJU)){
			//如果是区局提交，提交后将申诉状态修改
			infoVo.setAppeal_status(CreditConstants.AppealStatus_FINISH);
			infoVo.setAppeal_code(appeal.getAppeal_code());
			result=creditAppealMapper.updateCreditAppealInfo(infoVo);
		}else{
			throw new BusinessException("未找到代办理的数据。");
		}
		
		if(result!=1){
			throw new BusinessException("异议申诉状态更新失败!");
		}
		
		//设置下一步操作单位信息ID
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("next_org_id", variables.get("next_org_id"));
		return map;
	}

	/* (驳回更新申诉状态)
	 * @see com.indihx.activiti.ActivitiBusiness#rejectProcess(com.indihx.activiti.entity.Application)
	 */
	@Override
	public void rejectProcess(Application app) {
		//异议申诉基本信息
		CreditAppeal infoVo = new CreditAppeal();
		infoVo.setApp_id(app.getAppId().toString());
		CreditAppeal appeal = creditAppealMapper.getAppealInfo(infoVo);
		if(ObjectUtil.isEmpty(appeal)){
			throw new BusinessException("获取异议申诉申请信息失败!");
		}
		int result= 0;
		if(app.getCurrRoleId().equals(InitSysConstants.ROLE_QUJU)){
			//如果是区局提交，提交后将申诉状态修改
			infoVo.setAppeal_status(CreditConstants.AppealStatus_APPLY);
			infoVo.setAppeal_code(appeal.getAppeal_code());
			result=creditAppealMapper.updateCreditAppealInfo(infoVo);
		}
		if(result!=1){
			throw new BusinessException("异议申诉状态更新失败!");
		}
	}

	/* (办理待办任务页面)
	 * @see com.indihx.activiti.ActivitiBusiness#queryToDoInfo(com.indihx.activiti.entity.Application)
	 */
	@Override
	public Map<String, Object> queryToDoInfo(Application app) {
		try{
			Assert.notNull(app.getAppId(), "流程ID不能为空");
			Map<String,Object> map = new HashMap<String,Object>();
			//异议申诉基本信息
			CreditAppeal infoVo = new CreditAppeal();
			infoVo.setApp_id(app.getAppId().toString());
			CreditAppeal appeal = creditAppealMapper.getAppealInfo(infoVo);
			if(ObjectUtil.isEmpty(appeal)){
				throw new BusinessException("获取异议申诉申请信息失败!");
			}
			String credit_code = appeal.getCredit_code();
			if(ObjectUtil.isEmpty(credit_code)){
				throw new BusinessException("数据异常，异议申诉申请信息对应诚信记录编号不能为空!");
			}
			map.put("appeal", appeal);
			//诚信档案基本信息
			CreditBadVo recordVo = new CreditBadVo();
			recordVo.setCredit_code(credit_code);
			CreditBadRecordTemp record = creditBadRecordMapper.getBadRecordTemp(recordVo);
			if(ObjectUtil.isEmpty(record)){
				throw new BusinessException("获取不良信用档案信息失败!");
			}
			map.put("record", record);
			//上传文件信息
			map.put("fileSigns", getFileType(InitSysFileDocs.TranType_YiYiShenSuShenQing,appeal.getAppeal_code()));
			//指标依据列表
			BadQuotaVo vo =new BadQuotaVo();
			vo.setCredit_code(ObjectUtil.toString(appeal.getCredit_code()));
			List<CreditBadQuotaTemp> quotaList =creditBadRecordMapper.getBadQuotaTempList(vo);
			map.put("listInfo", quotaList);
			//异议申诉编号、诚信档案编号
			map.put("appeal_code", appeal.getAppeal_code());
			map.put("credit_code", appeal.getCredit_code());
			
			if(app.getCurrRoleId().equals(InitSysConstants.ROLE_WYGS)){//当时物业公司待办时
				map.put(ConstantStatic.URL, "/credit/appeal/sendTempView");
			}else if(app.getCurrRoleId().equals(InitSysConstants.ROLE_QUJU)){
				map.put(ConstantStatic.URL, "/credit/appeal/sendToDoView");
			}else{
				throw new BusinessException("未找到代办理的数据。");
			}
			
			return map;
		}catch(Exception e){
			throw new BusinessException("加载办理数据失败："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (查询告知中的企业不良信用列表)
	 * @see com.indihx.credit.service.ICreditAppealService#getBadTempRecordList(com.indihx.system.entity.UsrInfo, com.indihx.credit.vo.CreditBadVo)
	 */
	@Override
	public Map<String, Object> getBadTempRecordList(UsrInfo user,
			CreditBadVo creditBadVo) {
		
		try{
			Assert.notNull(user, "用户信息不能为空！");
			creditBadVo.setWygsid(user.getOrgNo().toString());//本企业的不良信用记录
			creditBadVo.setCredit_status(CreditConstants.RecordStatus_GZING);//告知中状态的不良信用
			List<CreditBadRecordTemp> listInfo = creditAppealMapper.getAppealBadRecordList(creditBadVo);
			
			//初始化查询区列表
			Wy_Hpb hpb = new Wy_Hpb();
			List<Wy_Hpb> hpbList = hpbMapper.getHpbList(hpb);
			
			//设置分页
			PageHelper.startPage(creditBadVo.getPages(), creditBadVo.getRows());
			PageInfo<CreditBadRecordTemp> pageInfo = new PageInfo<CreditBadRecordTemp>(listInfo);
			//响应页面数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageInfo", pageInfo);
			map.put("listInfo", listInfo);
			map.put("hpbList", hpbList);
			return map;
		}catch(Exception e){
			throw new BusinessException("查询数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (查看不良详情)
	 * @see com.indihx.credit.service.ICreditAppealService#getBadTempRecordInfo(com.indihx.system.entity.UsrInfo, com.indihx.credit.vo.CreditBadVo)
	 */
	@Override
	public Map<String, Object> getBadTempRecordInfo(UsrInfo user,
			CreditBadVo infoVo) {
		CreditBadRecordTemp record = creditBadRecordMapper.getBadRecordTemp(infoVo);
		//map存放办理待办任务加载页面显示数据
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("record",record);
		map.put("credit_code",record.getCredit_code());
		BadQuotaVo vo =new BadQuotaVo();
		vo.setCredit_code(ObjectUtil.toString(map.get("credit_code")));
		List<CreditBadQuotaTemp> quotaList =creditBadRecordMapper.getBadQuotaTempList(vo);
		map.put("listInfo", quotaList);
		return map;
	}

	/* (保存异议申诉)
	 * @see com.indihx.credit.service.ICreditAppealService#saveAppealInfo(com.indihx.system.entity.UsrInfo, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public Application saveAppealInfo(UsrInfo usrInfo,
			Map<String, Object> reqMap) {
		try{
			Assert.notNull(usrInfo, "登录超时，用户信息不能为空!");
			Assert.hasText(ObjectUtil.toString(reqMap.get("credit_code")),"不良信用记录档案编号不能为空!");
			Assert.hasText(ObjectUtil.toString(reqMap.get("appeal_reasion")),"不良信用异议申诉原因不能为空!");
			
			//诚信档案记录编号
			String credit_code=reqMap.get("credit_code").toString();
			
			//参数处理转换
			CreditAppeal infoVo = new CreditAppeal();
			MapToEntryConvertUtils.convertMaptoBean(reqMap,infoVo);
		
			//保存、更新异议申诉申请信息
			String appeal_code = null;
			int result = 0;
			if(StringUtils.isEmpty(reqMap.get("appeal_code"))){
				appeal_code =primaryKey.getPrefixDatePrimaryKey(8).toString();
				infoVo.setAppeal_code(appeal_code);
				infoVo.setAppeal_date(DateUtil.formatFromDB(DateUtil.getSysDate()));
				infoVo.setAppeal_time(DateUtil.formatTimeFromDB(DateUtil.getSysTime()));
				infoVo.setAppeal_status(CreditConstants.AppealStatus_APPLY);//异议申诉申请
				result =creditAppealMapper.saveCreditAppealInfo(infoVo);
			}else{
				appeal_code = reqMap.get("appeal_code").toString();
				infoVo.setAppeal_status(CreditConstants.AppealStatus_APPLY);//异议申诉申请
				result=creditAppealMapper.updateCreditAppealInfo(infoVo);
			}
			if(result!=1){
				throw new BusinessException("数据错误!");
			}
			
			//申诉通过后的指标记分核正信息
			BigDecimal scroe = new BigDecimal(0);
			Object credit_key = reqMap.get("credit_seq");
			if(!ObjectUtil.isEmpty(credit_key)){
				if(credit_key instanceof String){
					String credit_seq = (String)credit_key;
					String score = (String)reqMap.get("score");
					String clyj = (String)reqMap.get("clyj");
					CreditBadQuotaTemp badQuota = new CreditBadQuotaTemp();
					badQuota.setCredit_seq(credit_seq);
					badQuota.setCredit_code(infoVo.getCredit_code());//诚信编号
					badQuota.setScore(score);//各指标依据最终记分
					badQuota.setClyj(clyj);//各指标依据记分处理意见
					creditBadRecordMapper.updateCreditBadQuota(badQuota);
					scroe = new BigDecimal(score);
				}else{
					List<String> credit_seq = (ArrayList<String>) credit_key;
					List<String> score = (ArrayList<String>)reqMap.get("score");
					List<String> clyj = (ArrayList<String>)reqMap.get("clyj");
					if(ObjectUtil.isEmpty(infoVo.getCredit_code()))
						throw new BusinessException("诚信档案编号不能为空!");
					for (int i = 0; i < credit_seq.size(); i++) {
						//保存本次更新后的对应的不良依据
						CreditBadQuotaTemp badQuota = new CreditBadQuotaTemp();
						badQuota.setCredit_seq(credit_seq.get(i));
						badQuota.setCredit_code(infoVo.getCredit_code());//诚信编号
						badQuota.setScore(score.get(i));//各指标依据最终记分
						badQuota.setClyj(clyj.get(i));//各指标依据记分处理意见
						creditBadRecordMapper.updateCreditBadQuota(badQuota);
						
						scroe=scroe.add(new BigDecimal(badQuota.getScore()));
					}
				}
			}
			
			//更新申诉标志和最终记分
			CreditBadVo vo = new CreditBadVo();
			vo.setCredit_code(credit_code);//诚信档案记录编号
			vo.setSsbz(CreditConstants.AppealFlag_YiShenSU);//申诉标志更新
			vo.setScore(scroe.toString());
			CreditBadRecordTemp info = creditBadRecordMapper.getBadRecordTemp(vo);
			if(ObjectUtil.isEmpty(info)){
				throw new BusinessException("未查找到不良信用信息。");
			}
			creditBadRecordMapper.updateCreditInfoTemp(vo);
			
			//保存附件资料
			FileUpload record=null;
			CreditFileSign signRecord = null;
			if(!ObjectUtil.isEmpty(reqMap.get("signIds"))){
				if(reqMap.get("signIds") instanceof String){
					String signIds = (String) reqMap.get("signIds");
					//删除上一次签收档案
					signRecord = new CreditFileSign();
					signRecord.setSignId(signIds);
					signMapper.deleteFile(signRecord);
					//保存本次签收的档案
					record = new FileUpload();
					record.setRelaTabId(Long.valueOf(signIds));//关联操作ID
					Long fileTypeId = fileUploadMapper.getFileTypeId(Long.valueOf(signIds));
					if(fileTypeId == null){
						throw new BusinessException("请对签收的电子附件进行上传后再保存.");
					}
					signRecord = new CreditFileSign();
					signRecord.setSignId(signIds);
					signRecord.setCredit_code(appeal_code);//异议申诉编码
					signRecord.setFileTypeId(ObjectUtil.toLong(fileTypeId));
					signRecord.setIsSign("1");
					signMapper.saveFile(signRecord);
				}else{
					List<String> signIds = (ArrayList<String>) reqMap.get("signIds");
					for (int i = 0; i < signIds.size(); i++) {
						//删除上一次签收档案
						signRecord = new CreditFileSign();
						signRecord.setSignId(signIds.get(i));
						signMapper.deleteFile(signRecord);
						//保存本次签收的档案
						record = new FileUpload();
						record.setRelaTabId(Long.valueOf(signIds.get(i)));//关联操作ID
						Long fileTypeId = fileUploadMapper.getFileTypeId(Long.valueOf(signIds.get(i)));
						if(fileTypeId == null){
							throw new BusinessException("请对签收的电子附件进行上传后再保存.");
						}
						signRecord = new CreditFileSign();
						signRecord.setSignId(signIds.get(i));
						signRecord.setCredit_code(appeal_code);//异议申诉编码
						signRecord.setFileTypeId(ObjectUtil.toLong(fileTypeId));
						signRecord.setIsSign("1");
						signMapper.saveFile(signRecord);
					}
				}
			}
			//启动流程引擎
			Application app=null;
			try{
				Object appId =reqMap.get("app_id");
				Long appKey =null;
				if(!StringUtils.isEmpty(appId)) {//如果appId不为空，说明流程已经启动，不需要在启动
					appKey = Long.valueOf(appId.toString());
					app = applicationMapper.selectByPrimaryKey(appKey);
					app.setNextOrgId(info.getJldwid());//向记录单位申诉
					result = creditAppealMapper.updateCreditAppealInfo(infoVo);
					if(result!=1){
						throw new BusinessException("数据错误!");
					}
				}else {//如果appId为空，说明流程未启动，需要启动
					Map<String,Object> variables = new HashMap<String,Object>();
					variables.put("role_id", usrInfo.getRoleId());//必须设置
					variables.put("next_org_id", info.getJldwid());//向记录单位申诉
					app = activitiService.startProcess(usrInfo, variables, appeal_code,
							FlowKind.CONSTANT_BADAPPEAL, String.valueOf(usrInfo.getUsrId()));
					appKey = app.getAppId();
					//更新业务副表的流程ID
					infoVo.setApp_id(appKey.toString());//流程ID
					infoVo.setCredit_code(credit_code);;//诚信记录ID
					infoVo.setAppeal_code(appeal_code);//异议申诉编号
					result = creditAppealMapper.updateCreditAppealInfo(infoVo);
					if(result!=1){
						throw new BusinessException("数据错误!");
					}
				}
			}catch(Exception e){
				throw new BusinessException("流程引擎发生错误，"+ExceptionUtil.getErrorMsg(e));
			}
			return app;
		}catch(Exception e){
			throw new BusinessException("保存异议申诉申请失败："+ExceptionUtil.getErrorMsg(e));
		}
	}
	
	/**
	 * (获取上传的附件类型)
	 * @param tran_type
	 * @param credit_code
	 * @return
	 */
	public List<CreditFileSign> getFileType(String tran_type,String credit_code) {
		try{
			CreditFileSign cfg = new CreditFileSign();
			cfg.setTranType(tran_type);
			cfg.setCredit_code(credit_code);
			List<CreditFileSign> list=signMapper.getCreditFileList(cfg);
			List<CreditFileSign> files = new ArrayList<CreditFileSign>();
			CreditFileSign sign = null;
			String signId = null;
			for (int i = 0; i < list.size(); i++) {
				sign = new CreditFileSign();
				sign.setFileTypeId(list.get(i).getFileTypeId());//系统配置的文件类别ID
				sign.setFileType(list.get(i).getFileType());//系统配置的文件类型
				if(ObjectUtil.isEmpty(list.get(i).getSignId())){
					signId = primaryKey.getFileKey().toString();
				}else{
					signId = list.get(i).getSignId();
				}
				sign.setSignId(signId);//本次用户操作的档案类型ID，每一次操作不同用户都生成新的
				sign.setIsSign(list.get(i).getIsSign());//是否签收，默认未签收
				files.add(sign);
			}
			return files;
		}catch(Exception e){
			throw new BusinessException("查询电子资料信息失败："+ExceptionUtil.getErrorMsg(e));
		}
	}

}
