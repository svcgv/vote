package com.indihx.org.service.impl;

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
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.datamng.dao.HpbMapper;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.org.dao.CspMapper;
import com.indihx.org.entity.ORG_INFO;
import com.indihx.org.entity.WY_WYGS;
import com.indihx.org.entity.WY_WYGS_TEMP;
import com.indihx.org.service.ICspService;
import com.indihx.org.vo.CspInfoVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.Assert;
import com.indihx.util.ConstantStatic;
import com.indihx.util.EntityVoConverter;
import com.indihx.util.StringUtils;

@Service
public class CspServiceImpl extends ActivitiBusiness implements ICspService {

	@Resource
	private   CspMapper cspinfoMapper;
	@Resource
	private HpbMapper hpbMapper;
	@Resource
	private CommUtilMapper commMapper;
	@Autowired
	private PrimaryKey primaryKey;
	@Resource
	private ApplicationMapper applicationMapper;
	@Resource
	private IProcessServiceDao activitiService;
	
	
	@Override
	public Map<String, Object> cspList(UsrInfo user,CspInfoVo cspVo) {
		try{
			Assert.notNull(user, "用户信息不能为空！");
			WY_WYGS wygs = new WY_WYGS();
			EntityVoConverter.Convert(cspVo, wygs);
			
			String orgType = user.getOrgType();
			if(orgType.equals(InitSysConstants.ORGTYPE_WYGS)) {
				wygs.setWygsid(user.getOrgNo().toString());
			}else if(orgType.equals(InitSysConstants.ORGTYPE_QUJU)) {
				wygs.setHpbid(user.getHpbBaseId());
			}
			
			PageHelper.startPage(cspVo.getPages(), cspVo.getRows());
			List<WY_WYGS> listInfo = cspinfoMapper.cspinfoAll(wygs);
			PageInfo<WY_WYGS> pageInfo = new PageInfo<WY_WYGS>(listInfo);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageInfo", pageInfo);
			map.put("listInfo", listInfo);
			return map;
		}catch(Exception e){
			throw new BusinessException("查询数据错误："+e.getMessage());
		}
	}

	
	
	/* 
	 * (保存企业信息至临时表)
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public Application saveCspInfo(UsrInfo usrInfo,CspInfoVo cspVo) throws Exception{
		try{
			//项目数据状态
			String sjzt = cspVo.getSjzt();
			Assert.hasText(sjzt, "企业数据状态不能为空!");
			
			//保存项目信息
			Application app = null;
			if(sjzt.equals(InitSysConstants.DATA_ZhanCun)){//新增保存
				app=saveAddCspInfo(usrInfo, cspVo);
			}else if(sjzt.equals(InitSysConstants.DATA_XiuGaiZhong)){//修改保存
				app=saveEditCspInfo(usrInfo, cspVo);
			}else if(sjzt.equals(InitSysConstants.DATA_ShanChuZhong)){//删除保存
				app=saveDelCspInfo(usrInfo, cspVo);
			}else{
				throw new BusinessException("操作失败，无效的操作.");
			}
			return app;
		}catch(Exception e){
			throw new BusinessException("操作失败："+ExceptionUtil.getErrorMsg(e));
		}
	}
	
	 
	/**
	 * 保存物业企业信息到临时表
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Application saveAddCspInfo(UsrInfo usrInfo,CspInfoVo cspVo) throws BusinessException {
		try{
			//保存企业信息
			int i = 0 ;
			String wygsid = cspVo.getWygsid();
			WY_WYGS_TEMP wygs = null;
			if(!StringUtils.isEmpty(wygsid)){
				wygs=cspinfoMapper.getTempCspInfo(cspVo);
			}
			
			String id = "";
			WY_WYGS_TEMP temp = new WY_WYGS_TEMP();
			EntityVoConverter.Convert(cspVo, temp);
			if(wygs==null) {
				id = primaryKey.getPrimaryKey();
				temp.setWygsid(id);
				i =cspinfoMapper.saveCspTemp(temp);
			}else{
				i=cspinfoMapper.updateCspTemp(temp);
				id =temp.getWygsid().toString();
			}
			if(i!=1){
				throw new BusinessException("保存物业公司信息失败!");
			}
			
			//启动流程引擎
			Application app=null;
			try{
				Long appId = cspVo.getApp_id();
				if(!StringUtils.isEmpty(appId)) {//如果appId不为空，说明流程已经启动，不需要在启动
					app = applicationMapper.selectByPrimaryKey(appId);
				}else {//如果appId为空，说明流程未启动，需要启动
					Map<String,Object> currRole = new HashMap<String,Object>();
					currRole.put("role_id", usrInfo.getRoleId());//必须设置
					app = activitiService.startProcess(usrInfo, currRole, id,
							FlowKind.CONSTANT_WYGSCJ, String.valueOf(usrInfo.getUsrId()));
					appId = app.getAppId();
					//更新业务副表的流程ID
					temp.setApp_id(appId.toString());//流程ID
					temp.setWygsid(id);
					temp.setSjzt("01");//00正常；01暂存；02修改中；03删除中；04已注销
					cspinfoMapper.updateCspTemp(temp);
				}
			}catch(Exception e){
				throw new BusinessException("流程引擎发生错误："+e.getMessage());
			}
			return app;
		}catch(Exception e){
			throw new BusinessException("操作失败："+e.getMessage());
		}
	}

	
	/**
	 * 保存物业企业信息到临时表
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Application saveEditCspInfo(UsrInfo usrInfo,CspInfoVo cspVo) throws BusinessException {
		try{
			//保存企业信息
			int i = 0 ;
			String wygsid = cspVo.getWygsid();
			Assert.hasText(wygsid, "清选择需要变更企业信息！");
			WY_WYGS_TEMP wygs = null;
			if(!StringUtils.isEmpty(wygsid)){
				wygs=cspinfoMapper.getTempCspInfo(cspVo);
			}
			
			WY_WYGS_TEMP temp = new WY_WYGS_TEMP();
			EntityVoConverter.Convert(cspVo, temp);
			if(wygs==null) {
				i =cspinfoMapper.saveCspTemp(temp);
			}else{
				i=cspinfoMapper.updateCspTemp(temp);
			}
			if(i==1){
				int res = cspinfoMapper.updateCspStatus(temp);//修改正式表数据状态
				if(res!=1)throw new BusinessException("更新物业公司数据状态失败!");
			} else{
				throw new BusinessException("保存物业公司信息失败!");
			}
			
			
			//启动流程引擎
			Application app=null;
			try{
				Long appId = cspVo.getApp_id();
				if(!StringUtils.isEmpty(appId)) {//如果appId不为空，说明流程已经启动，不需要在启动
					app = applicationMapper.selectByPrimaryKey(appId);
				}else {//如果appId为空，说明流程未启动，需要启动
					Map<String,Object> currRole = new HashMap<String,Object>();
					currRole.put("role_id", usrInfo.getRoleId());//必须设置
					app = activitiService.startProcess(usrInfo, currRole, wygsid,
							FlowKind.CONSTANT_WYGSXG, String.valueOf(usrInfo.getUsrId()));
					appId = app.getAppId();
					//更新业务副表的流程ID
					temp.setApp_id(appId.toString());//流程ID
					temp.setWygsid(wygsid);
					temp.setSjzt("02");//00正常；01暂存；02修改中；03删除中；04已注销
					cspinfoMapper.updateCspTemp(temp);
				}
			}catch(Exception e){
				throw new BusinessException("流程引擎发生错误："+e.getMessage());
			}
			return app;
		}catch(Exception e){
			throw new BusinessException("操作失败："+e.getMessage());
		}
	}
	
	
	
	/**
	 * 保存物业企业信息到临时表
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Application saveDelCspInfo(UsrInfo usrInfo,CspInfoVo cspVo) throws BusinessException {
		try{	
			//保存企业信息
			int i = 0 ;
			String wygsid = cspVo.getWygsid();
			Assert.hasText(wygsid, "清选择需要删除企业信息！");
			WY_WYGS_TEMP wygs = null;
			if(!StringUtils.isEmpty(wygsid)){
				wygs=cspinfoMapper.getTempCspInfo(cspVo);
			}
			
			WY_WYGS_TEMP temp = new WY_WYGS_TEMP();
			EntityVoConverter.Convert(cspVo, temp);
			if(wygs==null) {
				i =cspinfoMapper.saveCspTemp(temp);
			}else{
				i=cspinfoMapper.updateCspTemp(temp);
			}
			if(i==1){
				//修改正式表数据状态
				int res = cspinfoMapper.updateCspStatus(temp);
				if(res!=1)throw new BusinessException("更新物业公司数据状态失败!");
			}else{
				throw new BusinessException("保存物业公司信息失败!");
			}
			
			
			//启动流程引擎
			Application app=null;
			try{
				Long appId = cspVo.getApp_id();
				if(!StringUtils.isEmpty(appId)) {//如果appId不为空，说明流程已经启动，不需要在启动
					app = applicationMapper.selectByPrimaryKey(appId);
				}else {//如果appId为空，说明流程未启动，需要启动
					Map<String,Object> currRole = new HashMap<String,Object>();
					currRole.put("role_id", usrInfo.getRoleId());//必须设置
					app = activitiService.startProcess(usrInfo, currRole, wygsid,
							FlowKind.CONSTANT_WYGSZX, String.valueOf(usrInfo.getUsrId()));
					appId = app.getAppId();
					//更新业务副表的流程ID
					temp.setApp_id(appId.toString());//流程ID
					temp.setWygsid(wygsid);
					temp.setSjzt("03");//00正常；01暂存；02修改中；03删除中；04已注销
					cspinfoMapper.updateCspTemp(temp);
				}
			}catch(Exception e){
				throw new BusinessException("流程引擎发生错误："+e.getMessage());
			}
			return app;
		}catch(Exception e){
			throw new BusinessException("操作失败："+e.getMessage());
		}
	}
 
	
	/* (获取提交/发送的临时项目信息)
	 * @see com.indihx.datamng.service.ISectService#getTempSectInfo(java.lang.String)
	 */
	@Override
	public WY_WYGS_TEMP getTempCspInfo(String wygsid) {
		Assert.hasText(wygsid, "物业公司Id不能为空!");
		CspInfoVo vo = new CspInfoVo();
		vo.setWygsid(wygsid);
		WY_WYGS_TEMP wygs = cspinfoMapper.getTempCspInfo(vo);
		return wygs;
	}
	
	 
	@Override
	public WY_WYGS qrCspInfoById(String wygsId) {
		Assert.hasText(wygsId, "企业ID不能为空!");
		WY_WYGS wy= cspinfoMapper.qrCspInfoById(wygsId);
		return wy;
	}

	 
	@Override
	public void handCopy(Application app) {
		// TODO Auto-generated method stub
		
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void processEnd(Application app) {
		try{
			//流程结束--将副本表Copy表的信息插入正式表和历史表
			CspInfoVo vo = new CspInfoVo();
			vo.setApp_id(app.getAppId());//根据流程ID获取项目信息
			WY_WYGS_TEMP copy = cspinfoMapper.getTempCspInfo(vo);
			copy.setSjzt(InitSysConstants.DATA_ZhengChang);//流程中数据为暂存，流程结束设置为正常
			if(StringUtils.isEmpty(copy)){
				throw new BusinessException("根据流程ID获取项目信息失败!");
			}
			EntityVoConverter.Convert(copy, vo);
			vo.setTask_id(app.getTaskId());
			//插入历史表His--历史表只有插入动作
			cspinfoMapper.saveCspHis(vo);
		    //根据不同流程类型处理相应的数据逻辑
		    if(app.getAppType().equals(FlowKind.CONSTANT_WYGSCJ)){//物业公司信息采集流程
		    	//插入正式表
		    	cspinfoMapper.insertCsp(vo);
		    	
		    	String hpbmc="";
				if(!StringUtils.isEmpty(vo.getHpbid())){
					Wy_Hpb hpb=hpbMapper.getHpbInfo(Long.valueOf(vo.getHpbid()));
					hpbmc=hpb.getHpbmc();
				}
		    	
				//验证企业是否已经存在
				List<WY_WYGS> listInfo = cspinfoMapper.validCspIsExists(vo);
				if(!listInfo.isEmpty()){
					throw new BusinessException(vo.getWygsmc()+"名称社会信用代码已经存在，不能重复录入!");
				}
				
		    	//保存组织机构信息表
				ORG_INFO oi=new ORG_INFO();
				oi.setParent_org_no(vo.getHpbid());//上级单位ID
				oi.setParent_org_name(hpbmc);//上级单位名称
				oi.setOrg_no(vo.getWygsid());;//单位ID
				oi.setOrg_name(vo.getWygsmc());;//单位名称
				oi.setOrg_type("05");//单位类别
				oi.setAddres(vo.getWygsdz());//办公地址
				oi.setTel_no(vo.getQydh());//联系电话
				oi.setOper_usr(app.getCreateId());//创建人
				oi.setTm_smp(DateUtil.getSysDate());//创建日期
				oi.setOrg_status("0"); //单位状态  /** 0：正常、1：禁用、2：注销  */
				oi.setLink_man(vo.getQylxr());;//联系人
				oi.setEmail(vo.getQyyx());;//EMAIL地址
				oi.setPost_code(vo.getQyyb());;//邮政编码
				cspinfoMapper.insertOrgInfo(oi);//保存机构信息
				
		    }else if(app.getAppType().equals(FlowKind.CONSTANT_WYGSXG)){//物业公司信息修改流程
		    	//更新正式表
		    	cspinfoMapper.updateCsp(vo);
		    	
		    	//更新组织机构信息表
				ORG_INFO oi=new ORG_INFO();
				oi.setOrg_no(vo.getWygsid());//单位ID
				oi.setOrg_name(vo.getWygsmc());;//单位名称
				oi.setAddres(vo.getWygsdz());//办公地址
				oi.setTel_no(vo.getQydh());//联系电话
				oi.setLink_man(vo.getQylxr());;//联系人
				oi.setEmail(vo.getQyyx());;//EMAIL地址
				oi.setPost_code(vo.getQyyb());;//邮政编码
				cspinfoMapper.updateOrgInfo(oi);//更新机构信息
				
		    }else if(app.getAppType().equals(FlowKind.CONSTANT_WYGSZX)){//物业公司信息删除流程
		    	//删除正式表
		    	cspinfoMapper.deleteCsp(vo.getWygsid());
		    	cspinfoMapper.deleteOrgInfo(vo.getWygsid());
		    	
		    }else{
		    	throw new BusinessException("无对应流程处理逻辑，请联系管理员!");
		    }
		    
		    //清理副本表当前流程数据
		    cspinfoMapper.clearCspCopyInfo(app.getAppId());
		}catch(Exception e){
			throw new BusinessException("操作失败："+e.getMessage());
		}
	}


	@Override
	public Map<String, Object> handCommitFirst(Application app, Map<String, Object> variables) {
		System.out.println(app.getActId());
		Map<String, Object> map  = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(variables.get("next_org_id"))){
			map.put("next_org_id", variables.get("next_org_id"));
		}
		return map;
	}


	@Override
	public void rejectProcess(Application app) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Map<String, Object> queryToDoInfo(Application app) {
		Assert.notNull(app.getAppId(), "流程ID不能为空");
		//根据流程ID获取下副本表的信息
		CspInfoVo vo = new CspInfoVo();
		vo.setApp_id(app.getAppId());
		WY_WYGS_TEMP csp = cspinfoMapper.getTempCspInfo(vo);
		//map存放办理待办任务加载页面显示数据
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("csp", csp);
		map.put("wygsid", csp.getWygsid());
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
		WY_WYGS_TEMP csp = (WY_WYGS_TEMP)map.get("csp");
		//物业类型字典项
		map.put("LOCAL_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",csp.getLocalflag()));//是否本地
		map.put("CERT_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",csp.getCertflag()));//是否三证合一
		map.put("ORG_NATURE",ConstantStatic.createHtmlByCode("ORG_NATURE",csp.getGslx()));//企业类型
		map.put("CSP_LEVEL",ConstantStatic.createHtmlByCode("CSP_LEVEL",csp.getZzdj()));//企业资质
		//区下拉选项
		Wy_Hpb hpb =new Wy_Hpb();
		List<Wy_Hpb> listInfo = hpbMapper.getHpbList(hpb);
		map.put("hpbList", listInfo);
		//根据不同流程节点，设置办理进入的页面
		map.put(ConstantStatic.URL, "/org/cspinfomng/auditCsp");
	}
	
	/**
	 * --进入物业公司办理页面
	 * 物业公司待办(保存或提交)-办理页面
	 * @param map
	 */
	private void getTodoView01(Map<String, Object> map) {
		WY_WYGS_TEMP csp = (WY_WYGS_TEMP)map.get("csp");
		//物业类型字典项
		map.put("LOCAL_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",csp.getLocalflag()));//是否本地
		map.put("CERT_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",csp.getCertflag()));//是否三证合一
		map.put("ORG_NATURE",ConstantStatic.createHtmlByCode("ORG_NATURE",csp.getGslx()));//企业类型
		map.put("CSP_LEVEL",ConstantStatic.createHtmlByCode("CSP_LEVEL",csp.getZzdj()));//企业资质
		//区下拉选项
		Wy_Hpb hpb =new Wy_Hpb();
		List<Wy_Hpb> listInfo = hpbMapper.getHpbList(hpb);
		map.put("hpbList", listInfo);
		//根据不同流程节点，设置办理进入的页面
		map.put(ConstantStatic.URL, "/org/cspinfomng/sendTempCspView");
	}

 
 
}
