package com.indihx.credit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.activiti.entity.Application;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.InitSysFileDocs;
import com.indihx.credit.commons.CreditConstants;
import com.indihx.credit.entity.CreditBadQuota;
import com.indihx.credit.entity.CreditBadRecord;
import com.indihx.credit.entity.CreditBadRecordTemp;
import com.indihx.credit.service.ICreditBadRecordService;
import com.indihx.credit.service.ICreditQuotaService;
import com.indihx.credit.vo.BadQuotaVo;
import com.indihx.credit.vo.CreditBadVo;
import com.indihx.credit.vo.QuotaVo;
import com.indihx.datamng.service.IHpbService;
import com.indihx.datamng.service.ISectService;
import com.indihx.datamng.vo.SectVo;
import com.indihx.org.service.ICspService;
import com.indihx.org.vo.CspInfoVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CreditBadRecordController.java</p>
 * <p>版    权: Copyright (c) 2018</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月9日下午6:01:31</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>CreditBadRecordController.java</p>
 * <p>不良信用档案管理-不良信用建档-告知</p>
 */
@Controller
@RequestMapping("/credit/badrecord")
public class CreditBadRecordController extends AbstractBaseController{

	@Resource
	ICreditBadRecordService creditBadRecordService;
	@Resource
	ICreditQuotaService quotaService;
	@Resource
	ISectService sectService;
	@Resource
	IHpbService hpbService;
	@Resource
	ICspService cspService;
	
	/**
	 * 首页
	 * 菜单进入首页查询不良信息列表
	 * 查询登录用户的管理
	 * @return
	 */
	@RequestMapping(value="/getBadRecordList",method=RequestMethod.GET)
	public ModelAndView getBadRecordList(HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = creditBadRecordService.getBadRecordList(user,new CreditBadVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/credit/badrecord/getBadRecordList");
		view.addObject("CreditQuotaObject",ConstantStatic.createHtmlByCode("CreditQuotaObject"));
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("hpbList", map.get("hpbList"));
		return view;
	}
	
	/***
	 * 查询按钮
	 * Ajax首页通过查询条件查询数据信息
	 * @param CreditBadVo
	 * @return
	 */
	@RequestMapping(value="/ajaxQryRecordList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQryRecordList(@RequestBody CreditBadVo infoVo,HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = creditBadRecordService.getBadRecordList(user,infoVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/**
	 * 新增按钮
	 * @return
	 */
	@RequestMapping(value="/addBadRecordView",method=RequestMethod.GET)
	public ModelAndView addBadRecordView(HttpSession session){
		UsrInfo user = getUser(session);
		ModelAndView view = new ModelAndView();
		view.setViewName("/credit/badrecord/addBadRecordView");
		//基本信息
		view.addObject("credit_status",CreditConstants.RecordStatus_LR);//新增时默认为建档，系统根据暂存或提交确认最后的状态
		view.addObject("info_from",ConstantStatic.createHtmlByCode("CreditInfoFrom"));
		view.addObject("bjlx",ConstantStatic.createHtmlByCode("CreditQuotaObject"));
		view.addObject("bllx",ConstantStatic.createHtmlByCode("CreditBadRecordKind"));
		view.addObject("hpbList", creditBadRecordService.getHpbList(user));
		//附件列表
		view.addObject("fileSigns", creditBadRecordService.getFileType(InitSysFileDocs.TranType_BuLiangXinXiJianDang,""));
		//指标列表
		QuotaVo Vo =new QuotaVo();
		Vo.setZblx(InitSysConstants.CreditQuotaKind_BL);
		Map<String, Object> map =quotaService.getQuotaList(user, Vo);
		view.addObject("listInfo",map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		return view;
	}
	
	/***
	 * 选择项目按钮
	 * @param session
	 */
	@RequestMapping(value="/openSectList",method=RequestMethod.GET)
	public ModelAndView openSectList(HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = sectService.getSectList(user, new SectVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/credit/badrecord/openSectList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 选择项目查询按钮
	 * Ajax首页通过查询条件查询数据信息
	 * @param sectVo  
	 * @return
	 */
	@RequestMapping(value="/ajaxSectList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxSectList(@RequestBody SectVo sectVo,HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = sectService.getSectList(user,sectVo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/***
	 * 选择物业公司按钮
	 * @param session
	 */
	@RequestMapping(value="/openCspList",method=RequestMethod.GET)
	public ModelAndView openCspList(HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = cspService.cspList(user,new CspInfoVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/sect/openCspList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 选择物业公司查询按钮
	 * Ajax首页通过查询条件查询数据信息
	 * @param sectVo  
	 * @return
	 */
	@RequestMapping(value="/ajaxcspList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxcspList(@RequestBody CspInfoVo cspVo,HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = cspService.cspList(user,cspVo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/***
	 * 选择项目经理按钮
	 * @param session
	 */
	@RequestMapping(value="/openXmjlList",method=RequestMethod.GET)
	public ModelAndView openXmjlList(HttpSession session){
		Map<String, Object> map = sectService.getXmjlList(new SectVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/credit/badrecord/openXmjlList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("wygsid", map.get("wygsid"));
		return view;
	}
	
	/***
	 * 选择项目经理查询按钮
	 * Ajax首页通过查询条件查询数据信息
	 * @param sectVo  
	 * @return
	 */
	@RequestMapping(value="/ajaxXmjlList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxXmjlList(@RequestBody SectVo infoVo){
		Map<String, Object> map = sectService.getXmjlList(infoVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/***
	 * 保存按钮
	 * @param CreditBadVo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/saveRecordInfo.do",method=RequestMethod.POST)
	public @ResponseBody Application saveRecordInfo(@RequestBody Map<String, Object> reqMap,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		Application app=creditBadRecordService.saveBadRecordInfo(usrInfo,reqMap);
		return app;
	}
	
	/**
	 * 查询不良档案基本信息
	 * 查询登录用户的管理
	 * @return
	 */
	@RequestMapping(value="/ajaxQryQuotaList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQryQuotaList(@RequestBody BadQuotaVo infoVo,HttpSession session){
		Map<String, Object> map = creditBadRecordService.getBadQuotaList(infoVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/***
	 * 不良信息录入提交按钮
	 * @param CreditBadVo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/sendToNextStep.do",method=RequestMethod.POST)
	public @ResponseBody Application sendToNextStep(@RequestBody Map<String, Object> reqMap,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		Application app = creditBadRecordService.saveBadRecordInfo(usrInfo,reqMap);
		return app;
	}
	
	
	/***
	 * 保存告知单信息
	 * @param CreditBadVo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/saveNotifyInfo.do",method=RequestMethod.POST)
	public @ResponseBody Application saveNotifyInfo(@RequestBody Map<String, Object> reqMap,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		Application app = creditBadRecordService.saveNotifyRecord(usrInfo,reqMap);
		return app;
	}
	
	/***
	 * 打印不良信用告知单
	 * @param session
	 */
	@RequestMapping(value="/printBadRecordView",method=RequestMethod.GET)
	public ModelAndView printBadRecordView(@RequestParam("credit_code") String credit_code,@RequestParam("app_id") String app_id){
		CreditBadVo infoVo = new CreditBadVo();
		infoVo.setApp_id(app_id);;
		infoVo.setCredit_code(credit_code);
		CreditBadRecordTemp info = creditBadRecordService.getBadRecordTemp(infoVo);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/credit/badrecord/printBadRecordView");
		view.addObject("info", info);
		return view;
	}
	
	/***
	 * 查看不良档案详情
	 * @param session
	 */
	@RequestMapping(value="/getBadRecordView",method=RequestMethod.GET)
	public ModelAndView getBadRecordView(@RequestParam("credit_code_sel") String credit_code_sel){

		//根据诚信档案编号查询不良档案基本信息
		CreditBadRecordTemp info = creditBadRecordService.getBadRecord(credit_code_sel);
		//根据诚信档案编号查询记分指标列表
		List<CreditBadQuota> quota=creditBadRecordService.getQuotaList(credit_code_sel);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/credit/badrecord/getBadRecordView");
		//不良信用基础信息
		view.addObject("record", info);
		//不良信用指标依据
		view.addObject("listInfo", quota);
		//附件列表
		view.addObject("fileSigns", creditBadRecordService.getFileType(InitSysFileDocs.TranType_BuLiangXinXiJianDang,credit_code_sel));
		return view;
	}
}
