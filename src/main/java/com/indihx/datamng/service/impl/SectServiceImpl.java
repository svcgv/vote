package com.indihx.datamng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
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
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.InfoCodeUtil;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.datamng.dao.CommitteeMapper;
import com.indihx.datamng.dao.HpbMapper;
import com.indihx.datamng.dao.SectMapper;
import com.indihx.datamng.dao.StreetMapper;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.entity.Wy_Jd;
import com.indihx.datamng.entity.Wy_Sect_Temp;
import com.indihx.datamng.entity.Wy_Sq;
import com.indihx.datamng.entity.Wy_Sect;
import com.indihx.datamng.service.ISectService;
import com.indihx.datamng.vo.SectVo;
import com.indihx.org.dao.CspSectMapper;
import com.indihx.org.dao.CspStaffMapper;
import com.indihx.org.entity.WY_GLC;
import com.indihx.org.entity.WY_WYGS_STAFF;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.Assert;
import com.indihx.util.ConstantStatic;
import com.indihx.util.EntityVoConverter;
import com.indihx.util.StringUtils;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: 业务层接口实现类</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月5日下午12:23:06</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>SectServiceImpl.java</p>
 * <p>注意DB事务控制和业务逻辑检查异常处理以及参数的断言</p>
 */
@Service
@EnableCaching
public class SectServiceImpl extends ActivitiBusiness implements ISectService {

	@Resource
	private SectMapper sectMapper;
	@Resource
	private HpbMapper hpbMapper;
	@Resource
	private StreetMapper streetMapper;
	@Resource
	private CommitteeMapper commMapper;
	@Resource
	private CspSectMapper cspSectMapper;
	@Resource
	private CspStaffMapper cspStaffMapper;
	@Autowired
	private PrimaryKey primaryKey;
	@Autowired
	private InfoCodeUtil infoCode;
	@Resource
	private ApplicationMapper applicationMapper;
	@Resource
	private IProcessServiceDao activitiService;
	/* 
	 * 查询项目列表
	 * @see com.indihx.datamng.service.ISectService#getSectList(com.indihx.datamng.vo.SectVo)
	 */
	@Override
	public Map<String, Object> getSectList(UsrInfo user,SectVo sectVo) throws BusinessException{
		try{
			Assert.notNull(user, "用户信息不能为空！");
			
			String orgType = user.getOrgType();
			String orgId = ObjectUtil.toString(user.getOrgNo());
			String hpbid = user.getHpbBaseId();
			String jdid = user.getStreetBaseId();
			String sqid = user.getCommitteeBaseId();
			if(orgType.equals(InitSysConstants.ORGTYPE_SHIJU)){
				//查询所有
			}else if(orgType.equals(InitSysConstants.ORGTYPE_QUJU)){
				sectVo.setHpbid(hpbid);
			}else if(orgType.equals(InitSysConstants.ORGTYPE_WYGS)){
				sectVo.setWygsid(orgId);
			}else if(orgType.equals(InitSysConstants.ORGTYPE_JIEDAO)){
				sectVo.setJdid(jdid);
			}else if(orgType.equals(InitSysConstants.ORGTYPE_JUWEIHUI)){
				sectVo.setSqid(sqid);
			}else{
				throw new BusinessException("用户没有权限...");
			}
			
			//调用Mapper根据参数获取项目列表信息
			List<Wy_Sect> listInfo = sectMapper.getSectList(sectVo);
			//初始化查询区列表
			Wy_Hpb hpb = new Wy_Hpb();
			hpb.setHpbid(sectVo.getHpbid());
			List<Wy_Hpb> hpbList = hpbMapper.getHpbList(hpb);
			//设置分页
			PageHelper.startPage(sectVo.getPages(), sectVo.getRows());
			PageInfo<Wy_Sect> pageInfo = new PageInfo<Wy_Sect>(listInfo);
			//响应页面数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageInfo", pageInfo);
			map.put("listInfo", listInfo);
			map.put("hpbList",hpbList);
			return map;
		}catch(Exception e){
			throw new BusinessException("查询数据错误："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/* 
	 * (保存项目信息至临时表)
	 * @see com.indihx.datamng.service.ISectService#saveAddSectInfo(com.indihx.datamng.vo.SectVo)
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
	@Override
	public Application saveAddSectInfo(UsrInfo usrInfo,SectVo infoVo) throws Exception{
		try{
			//保存项目信息
			int i = 0 ;
			String id = "";
			String code="";
			Wy_Sect_Temp temp = new Wy_Sect_Temp();
			Long next_org_id = null;
			if(!StringUtils.isEmpty(infoVo.getHpbid())){
				Wy_Hpb hpb=hpbMapper.getHpbInfo(Long.valueOf(infoVo.getHpbid()));
				infoVo.setHpbmc(hpb.getHpbmc());
				code = hpb.getHpbbm();
				next_org_id = hpbMapper.getHpbRefOrgId(Long.valueOf(infoVo.getHpbid()));
			}
			if(!StringUtils.isEmpty(infoVo.getJdid())){
				Wy_Jd jd=streetMapper.selectByPrimaryKey(Long.valueOf(infoVo.getJdid()));
				infoVo.setJdmc(jd.getJdmc());
			}
			if(!StringUtils.isEmpty(infoVo.getSqid())){
				Wy_Sq sq=commMapper.selectByPrimaryKey(Long.valueOf(infoVo.getSqid()));
				infoVo.setSqmc(sq.getSqmc());
			}
			EntityVoConverter.Convert(infoVo, temp);
			if(ObjectUtil.isEmpty(infoVo.getXmid())) {
				id = primaryKey.getPrimaryKey();
				temp.setXmid(id);//项目ID
				temp.setCjsj(DateUtil.formatFromDB(DateUtil.getSysDate()));//创建时间
				temp.setXmbm(infoCode.getFormatCode(code.substring(code.length()-2), 6));//项目编码
				temp.setSjzt(InitSysConstants.DATA_ZhanCun);//00正常；01暂存；02修改中；03删除中；04已注销
				i =sectMapper.saveSectTemp(temp);
			}else{
				temp.setXmid(infoVo.getXmid());
				i=sectMapper.updateSectTemp(temp);
				id = temp.getXmid();
			}
			if(i!=1){
				throw new BusinessException("保存项目信息失败!");
			}
			
			//启动流程引擎
			Application app=null;
			try{
				Long appId = infoVo.getApp_id();
				if(!StringUtils.isEmpty(appId)) {//如果appId不为空，说明流程已经启动，不需要在启动
					app = applicationMapper.selectByPrimaryKey(appId);
					app.setNextOrgId(ObjectUtil.toString(next_org_id));
				}else {//如果appId为空，说明流程未启动，需要启动
					Map<String,Object> variables = new HashMap<String,Object>();
					variables.put("role_id", usrInfo.getRoleId());//必须设置
					variables.put("next_org_id", next_org_id);
					app = activitiService.startProcess(usrInfo, variables, id,
							FlowKind.CONSTANT_XMCJ, String.valueOf(usrInfo.getUsrId()));
					appId = app.getAppId();
					//更新业务副表的流程ID
					temp.setApp_id(appId.toString());//流程ID
					temp.setXmid(id);//项目ID
					temp.setSjzt(InitSysConstants.DATA_ZhanCun);//00正常；01暂存；02修改中；03删除中；04已注销
					sectMapper.updateSectTemp(temp);
				}
			}catch(Exception e){
				throw new BusinessException("流程引擎发生错误："+ExceptionUtil.getErrorMsg(e));
			}
			return app;
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
	}
	
	/* 
	 * (保存项目信息至临时表)
	 * @see com.indihx.datamng.service.ISectService#saveEditSectInfo(com.indihx.datamng.vo.SectVo)
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public Application saveEditSectInfo(UsrInfo usrInfo,SectVo infoVo) throws Exception{
		try{	
			//保存项目信息
			int i = 0 ;
			String xmid = infoVo.getXmid();
			Wy_Sect_Temp sect = null;
			if(!StringUtils.isEmpty(xmid)){
				sect=sectMapper.getTempSectInfo(infoVo);
			}else{
				throw new BusinessException("项目ID不能为空!");
			}
			String code="";
			Wy_Sect_Temp temp = new Wy_Sect_Temp();
			Long next_org_id = null;
			if(!StringUtils.isEmpty(infoVo.getHpbid())){
				Wy_Hpb hpb=hpbMapper.getHpbInfo(Long.valueOf(infoVo.getHpbid()));
				infoVo.setHpbmc(hpb.getHpbmc());
				code = hpb.getHpbbm();
				next_org_id = hpbMapper.getHpbRefOrgId(Long.valueOf(infoVo.getHpbid()));
			}
			if(!StringUtils.isEmpty(infoVo.getJdid())){
				Wy_Jd jd=streetMapper.selectByPrimaryKey(Long.valueOf(infoVo.getJdid()));
				infoVo.setJdmc(jd.getJdmc());
			}
			if(!StringUtils.isEmpty(infoVo.getSqid())){
				Wy_Sq sq=commMapper.selectByPrimaryKey(Long.valueOf(infoVo.getSqid()));
				infoVo.setSqmc(sq.getSqmc());
			}
			EntityVoConverter.Convert(infoVo, temp);
			if(sect==null) {
				temp.setXmid(xmid);//项目ID
				temp.setCjsj(DateUtil.formatFromDB(DateUtil.getSysDate()));//创建时间
				temp.setXmbm(infoCode.getFormatCode(code.substring(code.length()-2), 6));//项目编码
				temp.setSjzt(InitSysConstants.DATA_XiuGaiZhong);//00正常；01暂存；02修改中；03删除中；04已注销
				i =sectMapper.saveSectTemp(temp);
			}else{
				temp.setXmid(xmid);//项目ID
				temp.setCjsj(DateUtil.formatFromDB(DateUtil.getSysDate()));//创建时间
				temp.setXmbm(infoCode.getFormatCode(code.substring(code.length()-2), 6));//项目编码
				i=sectMapper.updateSectTemp(temp);
			}
			if(i==1){
				//修改正式表数据状态
				int res = sectMapper.updateInfoStatus(temp);
				if(res!=1)throw new BusinessException("更新项目数据状态失败!");
			}else{
				throw new BusinessException("保存项目信息失败!");
			}
			
			//启动流程引擎
			Application app=null;
			try{
				Long appId = infoVo.getApp_id();
				if(!StringUtils.isEmpty(appId)) {//如果appId不为空，说明流程已经启动，不需要在启动
					app = applicationMapper.selectByPrimaryKey(appId);
					app.setNextOrgId(ObjectUtil.toString(next_org_id));
				}else {//如果appId为空，说明流程未启动，需要启动
					Map<String,Object> variables = new HashMap<String,Object>();
					variables.put("role_id", usrInfo.getRoleId());//必须设置
					variables.put("next_org_id", next_org_id);
					app = activitiService.startProcess(usrInfo, variables, xmid,
							FlowKind.CONSTANT_XMXG, String.valueOf(usrInfo.getUsrId()));
					appId = app.getAppId();
					//更新业务副表的流程ID
					temp.setApp_id(appId.toString());//流程ID
					temp.setXmid(xmid);//项目ID
					temp.setSjzt(InitSysConstants.DATA_XiuGaiZhong);//00正常；01暂存；02修改中；03删除中；04已注销
					sectMapper.updateSectTemp(temp);
				}
			}catch(Exception e){
				throw new BusinessException("流程引擎发生错误："+ExceptionUtil.getErrorMsg(e));
			}
			return app;
		}catch(Exception e){
			throw new BusinessException("操作失败："+ExceptionUtil.getErrorMsg(e));
		}
	}
	
	/**
	 * 删除保存
	 * @param usrInfo
	 * @param infoVo
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
	private Application saveDeleSectInfo(UsrInfo usrInfo, SectVo infoVo) throws Exception{
		try{	
			//保存项目信息
			int i = 0 ;
			String xmid = infoVo.getXmid();
			Wy_Sect ws =  null;
			Wy_Sect_Temp sect = null;
			if(!StringUtils.isEmpty(xmid)){
				sect=sectMapper.getTempSectInfo(infoVo);
				ws = sectMapper.getSectInfoById(xmid);
			}else{
				throw new BusinessException("项目ID不能为空!");
			}
			
			//检查删除的项目在正式表是否存在
			if(ObjectUtil.isEmpty(ws)){
				throw new BusinessException("未查询到删除的项目信息！");
			}
			
			//根据项目所属区获取对应区局，设置流程中下一步的审核机构ID 
			Long next_org_id = null;
			if(!StringUtils.isEmpty(ws.getHpbid())){
				next_org_id = hpbMapper.getHpbRefOrgId(Long.valueOf(ws.getHpbid()));
			}
			
			Wy_Sect_Temp temp = new Wy_Sect_Temp();
			//将正式表的信息放置的副表
			EntityVoConverter.Convert(ws, temp);
			if(sect==null) {
				temp.setXmid(xmid);//项目ID
				temp.setCjsj(DateUtil.formatFromDB(DateUtil.getSysDate()));//创建时间
				temp.setSjzt(InitSysConstants.DATA_ShanChuZhong);//00正常；01暂存；02修改中；03删除中；04已注销
				i =sectMapper.saveSectTemp(temp);
			}else{
				temp.setXmid(xmid);//项目ID
				temp.setCjsj(DateUtil.formatFromDB(DateUtil.getSysDate()));//创建时间
				i=sectMapper.updateSectTemp(temp);
			}
			if(i==1){
				//修改正式表数据状态
				int res = sectMapper.updateInfoStatus(temp);
				if(res!=1)throw new BusinessException("更新项目数据状态失败!");
			}else{
				throw new BusinessException("保存项目信息失败!");
			}
			
			//启动流程引擎
			Application app=null;
			try{
				Long appId = infoVo.getApp_id();
				if(!StringUtils.isEmpty(appId)) {//如果appId不为空，说明流程已经启动，不需要在启动
					app = applicationMapper.selectByPrimaryKey(appId);
					app.setNextOrgId(ObjectUtil.toString(next_org_id));
				}else {//如果appId为空，说明流程未启动，需要启动
					Map<String,Object> variables = new HashMap<String,Object>();
					variables.put("role_id", usrInfo.getRoleId());//必须设置
					variables.put("next_org_id", next_org_id);
					app = activitiService.startProcess(usrInfo, variables, xmid,
							FlowKind.CONSTANT_XMZX, String.valueOf(usrInfo.getUsrId()));
					appId = app.getAppId();
					//更新业务副表的流程ID
					temp.setApp_id(appId.toString());//流程ID
					temp.setXmid(xmid);//项目ID
					temp.setSjzt(InitSysConstants.DATA_ShanChuZhong);//00正常；01暂存；02修改中；03删除中；04已注销
					sectMapper.updateSectTemp(temp);
				}
			}catch(Exception e){
				throw new BusinessException("流程引擎发生错误："+ExceptionUtil.getErrorMsg(e));
			}
			return app;
		}catch(Exception e){
			throw new BusinessException("操作失败："+ExceptionUtil.getErrorMsg(e));
		}
	}
	
	/* 
	 * (保存项目信息至临时表)
	 * @see com.indihx.datamng.service.ISectService#saveSectInfo(com.indihx.datamng.vo.SectVo)
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public Application saveSectInfo(UsrInfo usrInfo,SectVo infoVo) throws Exception{
		try{
			//项目数据状态
			String sjzt = infoVo.getSjzt();
			Assert.hasText(sjzt, "项目数据状态不能为空!");
			
			//保存项目信息
			Application app = null;
			if(sjzt.equals(InitSysConstants.DATA_ZhanCun)){//新增保存
				app=saveAddSectInfo(usrInfo, infoVo);
			}else if(sjzt.equals(InitSysConstants.DATA_XiuGaiZhong)){//修改保存
				app=saveEditSectInfo(usrInfo, infoVo);
			}else if(sjzt.equals(InitSysConstants.DATA_ShanChuZhong)){//删除保存
				app=saveDeleSectInfo(usrInfo, infoVo);
			}else{
				throw new BusinessException("操作失败，无效的操作.");
			}
			return app;
		}catch(Exception e){
			throw new BusinessException("操作失败："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (根据项目ID获取项目信息)
	 * @see com.indihx.datamng.service.ISectService#getSectInfo(java.lang.String)
	 */
	@Override
	public Wy_Sect getSectInfo(String xmid) {
		Assert.hasText(xmid, "项目ID不能为空!");
		Wy_Sect sect = sectMapper.getSectInfoById(xmid);
		return sect;
	}

	/* (加载街道信息列表)
	 * @see com.indihx.datamng.service.ISectService#getJdList(com.indihx.datamng.vo.SectVo)
	 */
	@Override
	public Map<String, Object> getJdList(SectVo infoVo) {
		Wy_Jd jd = new Wy_Jd();
		jd.setHpbid(infoVo.getHpbid());
		List<Wy_Jd> jdlist = streetMapper.qryStreetAll(jd);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='' >--请选择--</option>");
		for (Wy_Jd street : jdlist) {
			buffer.append("<option value='"+street.getJdid()+"' ");
			if(!StringUtils.isEmpty(infoVo.getJdid())&&infoVo.getJdid().equals(street.getJdid().toString())){
				buffer.append(" selected='selected' ");
			}
			buffer.append(" >"+street.getJdmc()+"</option> ");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jdlist", buffer.toString());
		return map;
	}

	/* (加载社区信息列表)
	 * @see com.indihx.datamng.service.ISectService#getSqList(com.indihx.datamng.vo.SectVo)
	 */
	@Override
	public Map<String, Object> getSqList(SectVo infoVo) {
		Wy_Sq sq = new Wy_Sq();
		sq.setHpbid(infoVo.getHpbid());
		sq.setJdid(infoVo.getJdid());
		List<Wy_Sq> sqlist = commMapper.qryAqCommitteeAll(sq);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='' >--请选择--</option>");
		for (Wy_Sq comm : sqlist) {
			buffer.append("<option value='"+comm.getSqid()+"' ");
			if(!StringUtils.isEmpty(infoVo.getSqid())&&infoVo.getSqid().equals(comm.getSqid())){
				buffer.append(" selected='selected' ");
			}
			buffer.append(" >"+comm.getSqmc()+"</option> ");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sqlist", buffer.toString());
		return map;
	}

	/* (获取提交/发送的临时项目信息)
	 * @see com.indihx.datamng.service.ISectService#getTempSectInfo(java.lang.String)
	 */
	@Override
	public Wy_Sect_Temp getTempSectInfo(String xmid) {
		Assert.hasText(xmid, "项目ID不能为空!");
		SectVo vo = new SectVo();
		vo.setXmid(xmid);
		Wy_Sect_Temp sect = sectMapper.getTempSectInfo(vo);
		return sect;
	}

	/* (流程提交业务逻辑)
	 * @see com.indihx.activiti.ActivitiBusiness#handCopy(com.indihx.activiti.entity.Application)
	 */
	@Override
	public void handCopy(Application app) {
		// TODO Auto-generated method stub
		
	}

	/* (流程结束业务逻辑,流程结束时需要处理的逻辑)
	 * @see com.indihx.activiti.ActivitiBusiness#processEnd(com.indihx.activiti.entity.Application)
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public void processEnd(Application app) {
		
		try{
			//流程结束--将副本表Copy表的信息插入正式表和历史表
			SectVo vo = new SectVo();
			vo.setApp_id(app.getAppId());//根据流程ID获取项目信息
			Wy_Sect_Temp copy = sectMapper.getTempSectInfo(vo);
			copy.setSjzt(InitSysConstants.DATA_ZhengChang);//流程中数据为暂存，流程结束设置为正常
			if(StringUtils.isEmpty(copy)){
				throw new BusinessException("根据流程ID获取项目信息失败!");
			}
			EntityVoConverter.Convert(copy, vo);
			vo.setTask_id(app.getTaskId());
			//插入历史表His--历史表只有插入动作
		    sectMapper.saveSectHisInfo(vo);
		    //根据不同流程类型处理相应的数据逻辑
		    if(app.getAppType().equals(FlowKind.CONSTANT_XMCJ)){//项目信息采集流程
		    	//插入正式表
		    	vo.setSjzt(InitSysConstants.DATA_ZhengChang);
			    sectMapper.saveSectInfo(vo);
		    }else if(app.getAppType().equals(FlowKind.CONSTANT_XMXG)){//项目信息修改流程
		    	//更新正式表
		    	vo.setSjzt(InitSysConstants.DATA_ZhengChang);
		    	sectMapper.updateSectInfo(vo);
		    }else if(app.getAppType().equals(FlowKind.CONSTANT_XMZX)){//项目信息删除流程
		    	//删除正式表
			    sectMapper.deleteSectInfo(vo.getXmid());
		    }else{
		    	throw new BusinessException("无对应流程处理逻辑，请联系管理员!");
		    }
		    
		    //清理副本表当前流程数据
		    sectMapper.clearSectCopyInfo(app.getAppId());
		}catch(Exception e){
			throw new BusinessException("操作失败："+ExceptionUtil.getErrorMsg(e));
		}
	}

	/* (每次提交之前的时候，需要处理的业务逻辑)
	 * @see com.indihx.activiti.ActivitiBusiness#handCommitFirst(com.indihx.activiti.entity.Application, java.util.Map)
	 */
	@Override
	public Map<String, Object> handCommitFirst(Application app,
			Map<String, Object> variables) {
		// TODO Auto-generated method stub
		System.out.println(app.getActId());
		Map<String, Object> map  = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(variables.get("next_org_id"))){
			map.put("next_org_id", variables.get("next_org_id"));
		}
		return map;
	}

	/* (流程驳回业务逻辑)
	 * @see com.indihx.activiti.ActivitiBusiness#rejectProcess(com.indihx.activiti.entity.Application)
	 */
	@Override
	public void rejectProcess(Application app) {
		// TODO Auto-generated method stub
		
	}

	/* (查询当前流程的代办任务，返回一个Map对象，包括返回值全部放到Map中，map的key为啥，在前台页面就map的key取值,Map中必须包含返回的jsp页面URL的ke为：ConstantStatic.URL)
	 * @see com.indihx.activiti.ActivitiBusiness#queryToDoInfo(com.indihx.activiti.entity.Application)
	 * @param app 实体
	 */
	@Override
	public Map<String, Object> queryToDoInfo(Application app) {
		Assert.notNull(app.getAppId(), "流程ID不能为空");
		//根据流程ID获取下副本表的信息
		SectVo vo = new SectVo();
		vo.setApp_id(app.getAppId());
		Wy_Sect_Temp sect = sectMapper.getTempSectInfo(vo);
		//map存放办理待办任务加载页面显示数据
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sect", sect);
		map.put("xmid", sect.getXmid());
		//根据流程节点实现不同角色待办信息页面
		if(app.getCurrRoleId().equals(InitSysConstants.ROLE_WYGS)){//当时物业公司待办时
			getTodoView01(map);
		}else if(app.getCurrRoleId().equals(InitSysConstants.ROLE_QUJU)&&app.getCurrNodeId().equals("QuJuAudit")){//当时区物业主管部门待办,节点为区局审核时
			getTodoView02(map);
		}else if(app.getCurrRoleId().equals(InitSysConstants.ROLE_QUJU)&&!app.getCurrNodeId().equals("QuJuAudit")){//当时区物业主管部门待办,节点不是区局审核时	
			getTodoView01(map);
		}else if(app.getCurrRoleId().equals(InitSysConstants.ROLE_SHIJU)){//当时市局物业主管部门待办时	
			getTodoView01(map);	
		}else{
			throw new BusinessException("没有此权限，请联系系统管理员!");
		}
		return map;
	}

	/**
	 * --进入区局办理页面
	 * 区局待办(审核通过或退回)-办理页面
	 * @param map
	 */
	private void getTodoView02(Map<String, Object> map) {
		//根据不同流程节点，设置办理进入的页面
		map.put(ConstantStatic.URL, "/datamng/sect/auditSectView");
	}

	/**
	 * --进入物业公司办理页面
	 * 物业公司待办(保存或提交)-办理页面
	 * @param map
	 */
	private void getTodoView01(Map<String, Object> map) {
		Wy_Sect_Temp sect = (Wy_Sect_Temp)map.get("sect");
		//物业类型字典项
		map.put("SECTTYPE",ConstantStatic.createHtmlByCode("SECTTYPE",sect.getWyxz()));
		map.put("SECTTYPESUB1",ConstantStatic.createHtmlByCode("SECTTYPESUB1",sect.getWylx()));
		map.put("SECTTYPESUB2",ConstantStatic.createHtmlByCode("SECTTYPESUB2",sect.getWylx()));
		//区下拉选项
		Wy_Hpb hpb =new Wy_Hpb();
		List<Wy_Hpb> listInfo = hpbMapper.getHpbList(hpb);
		map.put("hpbList", listInfo);
		if(!StringUtils.isEmpty(sect.getHpbid())){
			//街道下拉选项
			Wy_Jd jd = new Wy_Jd();
			jd.setHpbid(sect.getHpbid());
			List<Wy_Jd> jdlist = streetMapper.qryStreetAll(jd);
			String jdselect = getJdSectList(jdlist,sect.getJdid());
			map.put("jdlist", jdselect);
			if(!StringUtils.isEmpty(sect.getJdid())){
				//社区下拉选项
				Wy_Sq sq = new Wy_Sq();
				sq.setHpbid(sect.getHpbid());
				sq.setJdid(sect.getJdid());
				List<Wy_Sq> sqlist = commMapper.qryAqCommitteeAll(sq);
				String sqselect = getSqSectList(sqlist,sect.getSqid());
				map.put("sqlist", sqselect);
			}
		}
		//根据不同流程节点，设置办理进入的页面
		map.put(ConstantStatic.URL, "/datamng/sect/sendTempSectView");
	}

	/**
	 * 构造社区下拉框
	 * @param sqlist
	 * @param sqid
	 * @return
	 */
	private String getSqSectList(List<Wy_Sq> sqlist, String sqid) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='' >--请选择--</option>");
		for (Wy_Sq comm : sqlist) {
			buffer.append("<option value='"+comm.getSqid()+"' ");
			if(!StringUtils.isEmpty(sqid)&&sqid.equals(comm.getSqid())){
				buffer.append(" selected='selected' ");
			}
			buffer.append(" >"+comm.getSqmc()+"</option> ");
		}
		return buffer.toString();
	}

	/**
	 * 构造街道下拉框
	 * @param jdlist
	 * @param jdid
	 * @return
	 */
	private String getJdSectList(List<Wy_Jd> jdlist, String jdid) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='' >--请选择--</option>");
		for (Wy_Jd street : jdlist) {
			buffer.append("<option value='"+street.getJdid()+"' ");
			if(!StringUtils.isEmpty(jdid) && jdid.equals(street.getJdid().toString())){
				buffer.append(" selected='selected' ");
			}
			buffer.append(" >"+street.getJdmc()+"</option> ");
		}
		return buffer.toString();
	}

	/* (选择物业管理处)
	 * @see com.indihx.datamng.service.ISectService#getCsList(java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> getCsList(SectVo  sectVo) {
		Assert.hasLength(sectVo.getWygsid(), "物业公司ID不能为空!");
		List<WY_GLC> listInfo = cspSectMapper.getCspSectList(sectVo);
		//设置分页
		PageHelper.startPage(sectVo.getPages(), sectVo.getRows());
		PageInfo<WY_GLC> pageInfo = new PageInfo<WY_GLC>(listInfo);
		//响应页面数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		map.put("wygsid",sectVo.getWygsid());
		return map;
	}
	
	/* (选择物业管理处)
	 * @see com.indihx.datamng.service.ISectService#getXmjlList(java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> getXmjlList(SectVo  sectVo) {
		Assert.hasLength(sectVo.getWygsid(), "物业公司ID不能为空!");
		WY_WYGS_STAFF staff = new WY_WYGS_STAFF();
		staff.setWygsid(Long.valueOf(sectVo.getWygsid()));
		List<WY_WYGS_STAFF> listInfo = cspStaffMapper.getStaffList(staff);
		//设置分页
		PageHelper.startPage(sectVo.getPages(), sectVo.getRows());
		PageInfo<WY_WYGS_STAFF> pageInfo = new PageInfo<WY_WYGS_STAFF>(listInfo);
		//响应页面数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("listInfo", listInfo);
		map.put("wygsid",sectVo.getWygsid());
		return map;
	}
}
