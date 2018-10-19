/**
 * 
 */
package com.indihx.credit.controller;

import java.util.HashMap;
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
import com.indihx.comm.util.ObjectUtil;
import com.indihx.credit.service.ICreditAppealService;
import com.indihx.credit.service.ICreditBadRecordService;
import com.indihx.credit.service.ICreditQuotaService;
import com.indihx.credit.vo.CreditBadVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CreditAppealController.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月28日上午8:10:21</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>CreditAppealController.java</p>
 * <p>不良信用异议申诉</p>
 */
@Controller
@RequestMapping("/credit/appeal")
public class CreditAppealController extends AbstractBaseController {
	
	@Resource
	ICreditBadRecordService creditBadRecordService;
	@Resource
	ICreditAppealService creditAppealService;
	@Resource
	ICreditQuotaService quotaService;
	
	/**
	 * 异议申请首页
	 * 菜单进入异议申请首页
	 * 查询登录用户的管理
	 * @return
	 */
	@RequestMapping(value="/getBadTempRecordList",method=RequestMethod.GET)
	public ModelAndView getBadTempRecordList(HttpSession session){
		UsrInfo user = getUser(session);
		CreditBadVo vo=new CreditBadVo();
		vo.setWygsid(user.getOrgNo().toString());
		Map<String, Object> map = creditAppealService.getBadTempRecordList(user,vo);
		ModelAndView view = new ModelAndView();
		view.setViewName("/credit/appeal/getAppealList");
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
	@RequestMapping(value="/ajaxQryAppealList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQryRecordList(@RequestBody CreditBadVo infoVo,HttpSession session){
		UsrInfo user = getUser(session);
		if(user.getOrgType().equals(InitSysConstants.ORGTYPE_WYGS)){
			infoVo.setWygsid(user.getOrgNo().toString());
		}
		Map<String, Object> map = creditAppealService.getBadTempRecordList(user,infoVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	
	/**
	 * 根据选中的不良记录
	 * 查看不良详情
	 * @return
	 */
	@RequestMapping(value="/getBadTempRecordView",method=RequestMethod.GET)
	public ModelAndView getBadTempRecordView(@RequestParam("credit_code_sel") String credit_code_sel,@RequestParam("app_id_sel") String app_id_sel,HttpSession session){
		UsrInfo user = getUser(session);
		CreditBadVo infoVo =new CreditBadVo() ;
		infoVo.setApp_id(app_id_sel);
		infoVo.setCredit_code(credit_code_sel);
		Map<String, Object> map = creditAppealService.getBadTempRecordInfo(user,infoVo);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/credit/appeal/getBadRecordView");
		view.addObject("CreditQuotaObject",ConstantStatic.createHtmlByCode("CreditQuotaObject"));
		view.addObject("record", map.get("record"));
		view.addObject("credit_code",map.get("credit_code"));
		view.addObject("fileSigns", creditBadRecordService.getFileType(InitSysFileDocs.TranType_BuLiangXinXiJianDang,ObjectUtil.toString(map.get("credit_code"))));
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("hpbList", map.get("hpbList"));
		return view;
	}
	
	/***
	 * 异议申请
	 * @param session
	 */
	@RequestMapping(value="/addAppealView",method=RequestMethod.GET)
	public ModelAndView addAppealView(@RequestParam("credit_code_sel") String credit_code_sel,@RequestParam("app_id_sel") String app_id_sel,HttpSession session){
		UsrInfo user = getUser(session);
		CreditBadVo infoVo = new CreditBadVo();
		infoVo.setApp_id(app_id_sel);
		infoVo.setCredit_code(credit_code_sel);
		Map<String, Object> map = creditAppealService.getBadTempRecordInfo(user,infoVo);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/credit/appeal/addAppealView");
		view.addObject("record", map.get("record"));
		view.addObject("credit_code",credit_code_sel);
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("fileSigns", creditBadRecordService.getFileType(InitSysFileDocs.TranType_YiYiShenSuShenQing,ObjectUtil.toString("")));
		return view;
	}
	
	/***
	 * 保存按钮
	 * @param CreditBadVo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/saveAppealInfo.do",method=RequestMethod.POST)
	public @ResponseBody Application saveRecordInfo(@RequestBody Map<String, Object> reqMap,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		Application app=creditAppealService.saveAppealInfo(usrInfo,reqMap);
		return app;
	}
	
	/**
	 * 提交
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sendToNextStep.do",method=RequestMethod.POST)
	public @ResponseBody Application sendToNextStep(@RequestBody Map<String, Object> reqMap,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		Application app = creditAppealService.saveAppealInfo(usrInfo,reqMap);
		return app;
	}
	
	
}
