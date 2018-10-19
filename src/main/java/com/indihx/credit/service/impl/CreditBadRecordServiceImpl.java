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
import com.indihx.activiti.entity.AppNodeInfo;
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
import com.indihx.credit.dao.CreditBadRecordMapper;
import com.indihx.credit.dao.CreditFileSignMapper;
import com.indihx.credit.dao.CreditQuotaMapper;
import com.indihx.credit.entity.CreditBadQuota;
import com.indihx.credit.entity.CreditBadQuotaTemp;
import com.indihx.credit.entity.CreditBadRecord;
import com.indihx.credit.entity.CreditBadRecordTemp;
import com.indihx.credit.entity.CreditFileSign;
import com.indihx.credit.entity.CreditQuota;
import com.indihx.credit.service.ICreditBadRecordService;
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
 * <p>描    述: CreditBadRecordServiceImpl.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月12日上午8:44:29</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>CreditBadRecordServiceImpl.java</p>
 * <p></p>
 */
@Service
public class CreditBadRecordServiceImpl extends ActivitiBusiness implements ICreditBadRecordService {

	@Resource
	private HpbMapper hpbMapper;
	@Resource
	private CreditQuotaMapper creditQuotaMapper;
	@Resource
	private CreditBadRecordMapper creditBadRecordMapper;
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
	
	/* (non-Javadoc)
	 * @see com.indihx.activiti.ActivitiBusiness#handCopy(com.indihx.activiti.entity.Application)
	 */
	@Override
	public void handCopy(Application app) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.indihx.activiti.ActivitiBusiness#processEnd(com.indihx.activiti.entity.Application)
	 */
	@Override
	public void processEnd(Application app) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see com.indihx.activiti.ActivitiBusiness#removeNextNode(com.indihx.activiti.entity.Application, java.util.List)
	 */
	@Override
	public void removeNextNode(Application app, java.util.List<com.indihx.activiti.entity.AppNodeInfo> nodes) {
		 if(app.getCurrRoleId().equals(InitSysConstants.ROLE_QUJU)&&app.getCurrNodeId().equals("BadRecordNotify")){
			 AppNodeInfo node = new AppNodeInfo();
			 node.setNodeName("End");
			 nodes.remove(node);
		 }
	};

	/* (non-Javadoc)
	 * @see com.indihx.activiti.ActivitiBusiness#handCommitFirst(com.indihx.activiti.entity.Application, java.util.Map)
	 */
	@Override
	public Map<String, Object> handCommitFirst(Application app,
			Map<String, Object> variables) {
		Map<String, Object> map  = new HashMap<String, Object>();
		//设置下一步的操作的机构ID
		if(app.getCurrRoleId().equals(InitSysConstants.ROLE_WYGS)){//当时物业公司待办时
			map.put("next_org_id", variables.get("next_org_id"));
		}else if(app.getCurrRoleId().equals(InitSysConstants.ROLE_QUJU)&&app.getCurrNodeId().equals("RecordBad")){//当时区物业主管部门待办,节点为不良信息录入时
			map.put("next_org_id", variables.get("next_org_id"));//不良信息录入后提交至区局告知
		}else if(app.getCurrRoleId().equals(InitSysConstants.ROLE_QUJU)&&app.getCurrNodeId().equals("BadRecordNotify")){
			map.put("next_org_id", variables.get("next_org_id"));//区局告知后提交至物业企业
		}else{
			throw new BusinessException("没有此权限，请联系系统管理员!");
		}
		
		return map;
	}

	/* (non-Javadoc)
	 * @see com.indihx.activiti.ActivitiBusiness#rejectProcess(com.indihx.activiti.entity.Application)
	 */
	@Override
	public void rejectProcess(Application app) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.indihx.activiti.ActivitiBusiness#queryToDoInfo(com.indihx.activiti.entity.Application)
	 */
	@Override
	public Map<String, Object> queryToDoInfo(Application app) {
		try{
			Assert.notNull(app.getAppId(), "流程ID不能为空");
			//根据流程ID获取下副本表的信息
			CreditBadVo infoVo = new CreditBadVo();
			infoVo.setApp_id(app.getAppId().toString());
			CreditBadRecordTemp record = creditBadRecordMapper.getBadRecordTemp(infoVo);
			//map存放办理待办任务加载页面显示数据
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("record",record);
			map.put("credit_code",record.getCredit_code());
			//根据流程节点实现不同角色待办信息页面
			if(app.getCurrRoleId().equals(InitSysConstants.ROLE_WYGS)){//当时物业公司待办时
				getTodoView01(map);//不良信息异议申请
			}else if(app.getCurrRoleId().equals(InitSysConstants.ROLE_QUJU)&&app.getCurrNodeId().equals("RecordBad")){//当时区物业主管部门待办,节点为不良信息录入时
				getTodoView02(map);//不良信息录入
			}else if(app.getCurrRoleId().equals(InitSysConstants.ROLE_QUJU)&&app.getCurrNodeId().equals("BadRecordNotify")){
				getTodoView03(map);//不良信息告知
			}else if(app.getCurrRoleId().equals(InitSysConstants.ROLE_SHIJU)){//当时市局物业主管部门待办时
				getTodoView01(map);//不良信息异议受理
			}else{
				throw new BusinessException("没有此权限，请联系系统管理员!");
			}
			return map;
		}catch(Exception e){
			throw new BusinessException("提交诚信不良信息失败："+ExceptionUtil.getErrorMsg(e));
		}
	}
	
	/**
	 * @param map
	 */
	private void getTodoView03(Map<String, Object> map) {
		map.put("fileSigns", getFileType(InitSysFileDocs.TranType_BuLiangXinXiJianDang,ObjectUtil.toString(map.get("credit_code"))));
		CreditBadRecordTemp record =(CreditBadRecordTemp)map.get("record");
		record.setZgqx("7");//默认整改期限7天
		record.setSsqx("10");//默认申诉期限10天
		map.remove("record");//删除原来的对象
		map.put("record", record);//将赋值后的对象重新赋值
		
		BadQuotaVo vo =new BadQuotaVo();
		vo.setCredit_code(ObjectUtil.toString(map.get("credit_code")));
		List<CreditBadQuotaTemp> quotaList =creditBadRecordMapper.getBadQuotaTempList(vo);
		//设置分页
		PageHelper.startPage(vo.getPages(), vo.getRows());
		PageInfo<CreditBadQuotaTemp> pageInfo = new PageInfo<CreditBadQuotaTemp>(quotaList);
		map.put("listInfo", quotaList);
		map.put("pageInfo", pageInfo);
		//根据不同流程节点，设置办理进入的页面
		map.put(ConstantStatic.URL, "/credit/badrecord/sendToDoView");
	}

	/**
	 * @param map
	 */
	private void getTodoView02(Map<String, Object> map) {
		CreditBadRecordTemp record = (CreditBadRecordTemp)map.get("record");
		map.put("info_from",ConstantStatic.createHtmlByCode("CreditInfoFrom",record.getInfo_from()));
		map.put("bjlx",ConstantStatic.createHtmlByCode("CreditQuotaObject",record.getBjlx()));
		map.put("bllx",ConstantStatic.createHtmlByCode("CreditBadRecordKind",record.getBllx()));
		//区下拉选项
		Wy_Hpb hpb =new Wy_Hpb();
		List<Wy_Hpb> listInfo = hpbMapper.getHpbList(hpb);
		map.put("hpbList", listInfo);
		map.put("fileSigns", getFileType(InitSysFileDocs.TranType_BuLiangXinXiJianDang,ObjectUtil.toString(map.get("credit_code"))));
		
		BadQuotaVo vo =new BadQuotaVo();
		vo.setCredit_code(ObjectUtil.toString(map.get("credit_code")));
		List<CreditBadQuotaTemp> quotaList =creditBadRecordMapper.getBadQuotaList(vo);
		//设置分页
		PageHelper.startPage(vo.getPages(), vo.getRows());
		PageInfo<CreditBadQuotaTemp> pageInfo = new PageInfo<CreditBadQuotaTemp>(quotaList);
		map.put("listInfo", quotaList);
		map.put("pageInfo", pageInfo);
		//根据不同流程节点，设置办理进入的页面
		map.put(ConstantStatic.URL, "/credit/badrecord/sendTempView");
	}

	/**
	 * @param map
	 */
	private void getTodoView01(Map<String, Object> map) {
		map.put("fileSigns", getFileType(InitSysFileDocs.TranType_BuLiangXinXiJianDang,ObjectUtil.toString(map.get("credit_code"))));
		BadQuotaVo vo =new BadQuotaVo();
		vo.setCredit_code(ObjectUtil.toString(map.get("credit_code")));
		List<CreditBadQuotaTemp> quotaList =creditBadRecordMapper.getBadQuotaTempList(vo);
		//设置分页
		PageHelper.startPage(vo.getPages(), vo.getRows());
		PageInfo<CreditBadQuotaTemp> pageInfo = new PageInfo<CreditBadQuotaTemp>(quotaList);
		map.put("listInfo", quotaList);
		map.put("pageInfo", pageInfo);
		//根据不同流程节点，设置办理进入的页面
		map.put(ConstantStatic.URL, "/credit/badrecord/getAppealView");
	}

	/* (查询不良信用记录列表)
	 * @see com.indihx.credit.service.ICreditBadRecordService#getBadRecordList(com.indihx.system.entity.UsrInfo, com.indihx.credit.vo.CreditBadVo)
	 */
	@Override
	public Map<String, Object> getBadRecordList(UsrInfo user,
			CreditBadVo creditBadVo) {
		try{
			Assert.notNull(user, "用户信息不能为空！");
			List<CreditBadRecord> listInfo = creditBadRecordMapper.getBadRecordList(creditBadVo);
			
			//初始化查询区列表
			Wy_Hpb hpb = new Wy_Hpb();
			if(user.getOrgType().equals(InitSysConstants.ORGTYPE_QUJU)){
				hpb.setHpbid(user.getHpbBaseId());
			}
			List<Wy_Hpb> hpbList = hpbMapper.getHpbList(hpb);
			
			//设置分页
			PageHelper.startPage(creditBadVo.getPages(), creditBadVo.getRows());
			PageInfo<CreditBadRecord> pageInfo = new PageInfo<CreditBadRecord>(listInfo);
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

	/* (获取受理区局列表)
	 * @see com.indihx.credit.service.ICreditBadRecordService#getHpbList(com.indihx.system.entity.UsrInfo)
	 */
	@Override
	public List<Wy_Hpb> getHpbList(UsrInfo user) {
		try{
			Assert.notNull(user, "用户信息不能为空！");
			//初始化查询区列表
			Wy_Hpb hpb = new Wy_Hpb();
			if(user.getOrgType().equals(InitSysConstants.ORGTYPE_QUJU)){
				hpb.setHpbid(user.getHpbBaseId());
			}
			List<Wy_Hpb> hpbList = hpbMapper.getHpbList(hpb);
			return hpbList;
		}catch(Exception e){
			throw new BusinessException("查询数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (保存不良信用至临时表)
	 * @see com.indihx.credit.service.ICreditBadRecordService#saveBadRecordInfo(com.indihx.system.entity.UsrInfo, com.indihx.credit.vo.CreditBadVo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public Application saveBadRecordInfo(UsrInfo usrInfo, Map<String, Object> reqMap) {
		try{
			Assert.notNull(usrInfo, "登录超时，用户信息不能为空!");
			
			//参数处理转换
			CreditBadVo infoVo = new CreditBadVo();
			MapToEntryConvertUtils.convertMaptoBean(reqMap,infoVo);
			if(!StringUtils.isEmpty(reqMap.get("hpbid"))){
				Wy_Hpb hpb=hpbMapper.getHpbInfo(Long.valueOf(reqMap.get("hpbid").toString()));
				infoVo.setHpbmc(hpb.getHpbmc());
			}
			//保存不良信用记录基本信息
			String credit_code = null;
			int result = 0;
			if(StringUtils.isEmpty(reqMap.get("credit_code"))){
				credit_code =primaryKey.getPrefixDatePrimaryKey(8).toString();
				infoVo.setCredit_code(credit_code);
				infoVo.setJldwid(usrInfo.getOrgNo().toString());
				infoVo.setJlczybh(usrInfo.getUsrId().toString());
				infoVo.setCredit_status(CreditConstants.RecordStatus_LR);//新增暂存-建档状态
				result =creditBadRecordMapper.saveCreditBadInfoTemp(infoVo);
			}else{
				credit_code = reqMap.get("credit_code").toString();
				infoVo.setCredit_status(CreditConstants.RecordStatus_GZ);//提交后-告知状态
				result=creditBadRecordMapper.updateCreditInfoTemp(infoVo);
			}
			if(result!=1){
				throw new BusinessException("数据错误!");
			}
			
			//保存不良信用指标依据信息
			BigDecimal ckf = new BigDecimal(0);
			BigDecimal jlf = new BigDecimal(0);
			Object credit_key = reqMap.get("credit_seq");
			if(!ObjectUtil.isEmpty(credit_key)){
				if(credit_key instanceof String){
					String credit_seq = (String) credit_key;
					String jlfs = (String)reqMap.get("jlfs");
					String jfsm = (String)reqMap.get("jfsm");
					//保存本次更新后的对应的不良依据
					CreditQuota quota =creditQuotaMapper.getQuotaInfo(credit_seq);
					CreditBadQuotaTemp badQuota = new CreditBadQuotaTemp();
					EntityVoConverter.Convert(quota, badQuota);
					badQuota.setApp_id(infoVo.getApp_id());
					badQuota.setCredit_code(infoVo.getCredit_code());//诚信编号
					badQuota.setCkf(quota.getCkfz());//各指标依据参考记分
					badQuota.setJlf(jlfs);//各指标依据记分
					badQuota.setJfsm(jfsm);//各指标依据记分说明
					creditBadRecordMapper.saveCreditBadQuota(badQuota);
					ckf=new BigDecimal(badQuota.getCkf());
					jlf=new BigDecimal(jlfs);
				}else{
					List<String> credit_seq = (ArrayList<String>) credit_key;
					List<String> jlfs = (ArrayList<String>)reqMap.get("jlfs");
					List<String> jfsm = (ArrayList<String>)reqMap.get("jfsm");
					
					//删除上次诚信记录编号对应的不良依据
					creditBadRecordMapper.deleteBadQuota(infoVo.getCredit_code());
					for (int i = 0; i < credit_seq.size(); i++) {
						//保存本次更新后的对应的不良依据
						CreditQuota quota =creditQuotaMapper.getQuotaInfo(credit_seq.get(i));
						CreditBadQuotaTemp badQuota = new CreditBadQuotaTemp();
						EntityVoConverter.Convert(quota, badQuota);
						badQuota.setApp_id(infoVo.getApp_id());
						badQuota.setCredit_code(infoVo.getCredit_code());//诚信编号
						badQuota.setCkf(quota.getCkfz());//各指标依据参考记分
						badQuota.setJlf(jlfs.get(i));//各指标依据记分
						badQuota.setJfsm(jfsm.get(i));//各指标依据记分说明
						creditBadRecordMapper.saveCreditBadQuota(badQuota);
						ckf=ckf.add(new BigDecimal(badQuota.getCkf()));
						jlf=jlf.add(new BigDecimal(badQuota.getJlf()));
					}
					infoVo.setCkf(ckf.toString());
					infoVo.setJlf(jlf.toString());
				}
				
			}
			
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
					signRecord.setCredit_code(credit_code);//异议申诉编码
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
						signRecord.setCredit_code(credit_code);//异议申诉编码
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
					app.setNextOrgId(ObjectUtil.toString(usrInfo.getOrgNo()));
					result = creditBadRecordMapper.updateCreditInfoTemp(infoVo);
					if(result!=1){
						throw new BusinessException("数据错误!");
					}
				}else {//如果appId为空，说明流程未启动，需要启动
					Map<String,Object> variables = new HashMap<String,Object>();
					variables.put("role_id", usrInfo.getRoleId());//必须设置
					variables.put("next_org_id", usrInfo.getOrgNo());
					app = activitiService.startProcess(usrInfo, variables, credit_code,
							FlowKind.CONSTANT_BADRECORD, String.valueOf(usrInfo.getUsrId()));
					appKey = app.getAppId();
					//更新业务副表的流程ID
					infoVo.setApp_id(appKey.toString());//流程ID
					infoVo.setCredit_code(credit_code);;//诚信记录ID
					result = creditBadRecordMapper.updateCreditInfoTemp(infoVo);
					if(result!=1){
						throw new BusinessException("数据错误!");
					}
				}
			}catch(Exception e){
				throw new BusinessException("流程引擎发生错误："+ExceptionUtil.getErrorMsg(e));
			}
			return app;
		}catch(Exception e){
			throw new BusinessException("保存诚信不良信息失败："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (获取上传的附件类型)
	 * @see com.indihx.credit.service.ICreditBadRecordService#getFileType(java.lang.String)
	 */
	@Override
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

	/* (根据流程ID获取不良信息详情)
	 * @see com.indihx.credit.service.ICreditBadRecordService#getBadRecordTemp(com.indihx.credit.vo.CreditBadVo)
	 */
	@Override
	public CreditBadRecordTemp getBadRecordTemp(CreditBadVo infoVo) {
		try{
			CreditBadRecordTemp record = creditBadRecordMapper.getBadRecordTemp(infoVo);
			return record;
		}catch(Exception e){
			throw new BusinessException("查询记分指标依据列表失败："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (查询记分指标依据)
	 * @see com.indihx.credit.service.ICreditBadRecordService#getBadQuotaList(com.indihx.credit.vo.BadQuotaVo)
	 */
	@Override
	public Map<String, Object> getBadQuotaList(BadQuotaVo infoVo) {
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			BadQuotaVo vo =new BadQuotaVo();
			vo.setCredit_code(ObjectUtil.toString(infoVo.getCredit_code()));
			List<CreditBadQuotaTemp> quotaList =creditBadRecordMapper.getBadQuotaTempList(vo);
			//设置分页
			PageHelper.startPage(vo.getPages(), vo.getRows());
			PageInfo<CreditBadQuotaTemp> pageInfo = new PageInfo<CreditBadQuotaTemp>(quotaList);
			map.put("listInfo", quotaList);
			map.put("pageInfo", pageInfo);
			return map;
		}catch(Exception e){
			throw new BusinessException("查询记分指标依据列表失败："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (保存告知单信息)
	 * @see com.indihx.credit.service.ICreditBadRecordService#saveNotifyRecord(com.indihx.system.entity.UsrInfo, java.util.Map)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Application saveNotifyRecord(UsrInfo usrInfo,
			Map<String, Object> reqMap) {
		try{
			Assert.notNull(usrInfo, "登录超时，用户信息不能为空!");
			Assert.notNull(reqMap.get("credit_code"), "诚信档案记录编号不能为空!");
			Assert.notNull(reqMap.get("app_id"), "流程ID不能为空!");
			Assert.notNull(reqMap.get("gzrq"), "整改日期不能为空!");
			Assert.notNull(reqMap.get("ssqx"), "申诉期限不能为空!");
			Assert.notNull(reqMap.get("zgqx"), "整改期限不能为空!");
			//检查流程信息
			Object appId =reqMap.get("app_id");
			Long appKey = Long.valueOf(appId.toString());
			Application app = applicationMapper.selectByPrimaryKey(appKey);
			if(ObjectUtil.isEmpty(app)){
				throw new BusinessException("查询流程信息失败!");
			}
			
			//取告知单位ID
			CreditBadVo infoVo = new CreditBadVo();
			infoVo.setApp_id(reqMap.get("app_id").toString());;
			infoVo.setCredit_code(reqMap.get("credit_code").toString());
			infoVo.setCredit_status(CreditConstants.RecordStatus_GZING);//告知后-告知中状态--此时物业企业可以进行异议申诉
			infoVo.setGzrq(ObjectUtil.toString(reqMap.get("gzrq")));
			infoVo.setGzsj(ObjectUtil.toString(reqMap.get("gzsj")));
			infoVo.setGzdwid(usrInfo.getOrgNo().toString());
			infoVo.setGzczybh(usrInfo.getUsrId().toString());
			infoVo.setSsqx(ObjectUtil.toString(reqMap.get("ssqx")));
			infoVo.setSsjzrq(DateUtil.getDateByDays(infoVo.getGzrq(), Integer.valueOf(infoVo.getSsqx())));
			infoVo.setZgqx(ObjectUtil.toString(reqMap.get("zgqx")));
			infoVo.setZgjzrq(DateUtil.getDateByDays(infoVo.getGzrq(), Integer.valueOf(infoVo.getZgqx())));
			infoVo.setSsbz(CreditConstants.AppealFlag_WeiShenSu);//未申诉标志
			CreditBadRecordTemp info = creditBadRecordMapper.getBadRecordTemp(infoVo);
			String org_id =info.getWygsid();
			if(ObjectUtil.isEmpty(info)||ObjectUtil.isEmpty(org_id)){
				throw new BusinessException("保存不良信用告知单信息失败!");
			}
			//更新告知单信息
			creditBadRecordMapper.updateCreditInfoTemp(infoVo);
		
			//设置提交后的下一步操作单位ID
			app.setNextOrgId(ObjectUtil.toString(org_id));
			return app;
		}catch(Exception e){
			throw new BusinessException("更新流程信息失败："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (查询诚信不良档案基本信息)
	 * @see com.indihx.credit.service.ICreditBadRecordService#getBadRecord(java.lang.String)
	 */
	@Override
	public CreditBadRecordTemp getBadRecord(String credit_code_sel) {
		try{
			Assert.hasText(credit_code_sel, "诚信档案记录编号不能为空!");
			CreditBadRecordTemp record = creditBadRecordMapper.getCreditBadRecordDocment(credit_code_sel);
			return record;
		}catch(Exception e){
			throw new BusinessException("获取诚信档案信息失败!");
		}
	}

	/* (获取记分指标信息)
	 * @see com.indihx.credit.service.ICreditBadRecordService#getQuotaList(java.lang.String)
	 */
	@Override
	public List<CreditBadQuota> getQuotaList(String credit_code_sel) {
		try{
			Assert.hasText(credit_code_sel, "诚信档案记录编号不能为空!");
			List<CreditBadQuota> list = creditBadRecordMapper.getCreditRecordQuotaList(credit_code_sel);
			return list;
		}catch(Exception e){
			throw new BusinessException("获取诚信档案指标信息失败!");
		}
	}

	/* (归档办结)
	 * @see com.indihx.credit.service.ICreditBadRecordService#bulidCreditDocment(java.util.List)
	 */
	@Override
	public List<Application> bulidCreditDocment(
			List<CreditBadRecordTemp> recordList) {
		try{
			String ssjzrq = null;
			String app_id= null;
			String credit_code=null;
			for (int i = 0; i < recordList.size(); i++) {
				ssjzrq=recordList.get(i).getSsjzrq();
				//申诉日期到期
				if(DateUtil.compare_date(DateUtil.formatDateTimeFromDB(DateUtil.getSysDate()+"000000"),DateUtil.formatDateTimeFromDB(ssjzrq+"000000"))==1){
					app_id=recordList.get(i).getApp_id();
					credit_code =recordList.get(i).getApp_id();
					System.out.println(credit_code);
				}
			}
			return null;
		}catch(Exception e){
			throw new BusinessException("建档失败!");
		}
	}
}
