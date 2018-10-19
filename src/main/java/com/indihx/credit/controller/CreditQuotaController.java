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
import com.indihx.credit.commons.CreditConstants;
import com.indihx.credit.entity.CreditQuota;
import com.indihx.credit.service.ICreditQuotaService;
import com.indihx.credit.vo.QuotaVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: QuotaController.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年3月2日下午5:59:29</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>QuotaController.java</p>
 * <p>诚信管理指标体系-用于管理部门定义全市诚信信息的管理指标或依据；相当于制定宪法条款。</p>
 */
@Controller
@RequestMapping("/credit/quota")
public class CreditQuotaController extends AbstractBaseController {

	@Resource
	ICreditQuotaService quotaService;
	
	/**
	 * 首页
	 * 菜单进入首页查询指标列表
	 * 查询登录用户的管理指标列表
	 * @return
	 */
	@RequestMapping(value="/getQuotaList",method=RequestMethod.GET)
	public ModelAndView getQuotaList(HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = quotaService.getQuotaList(user,new QuotaVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/credit/quota/getQuotaList");
		view.addObject("quotaKind",ConstantStatic.createHtmlByCode("CreditQuotaKind"));
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 查询按钮
	 * Ajax首页通过查询条件查询数据信息
	 * @param QuotaVo  
	 * @return
	 */
	@RequestMapping(value="/ajaxQryQuotaList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQryQuotaList(@RequestBody QuotaVo infoVo,HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = quotaService.getQuotaList(user,infoVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/**
	 * 新增按钮
	 * @return
	 */
	@RequestMapping(value="/addQuotaView",method=RequestMethod.GET)
	public ModelAndView addQuotaView(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/credit/quota/addQuotaView");
		view.addObject("status",CreditConstants.QuotaStatus_ZC);
		view.addObject("zbbm",quotaService.getQuotaDefaultCode());
		return view;
	}
	
	/***
	 * 保存按钮
	 * @param QuotaInfo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/saveQuotaInfo.do",method=RequestMethod.POST)
	public @ResponseBody String saveQuotaInfo(@RequestBody QuotaVo infoVo,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		quotaService.saveQuotaInfo(usrInfo,infoVo);
		return "";
	}
	
	/**
	 * 查看按钮
	 * @return
	 */
	@RequestMapping(value="/getQuotaView",method=RequestMethod.GET)
	public ModelAndView getQuotaInfo(@RequestParam("credit_seq") String credit_seq){
		ModelAndView view = new ModelAndView();
		CreditQuota quota = quotaService.getQuotaInfo(credit_seq);
		view.setViewName("/credit/quota/getQuotaView");
		view.addObject("status",CreditConstants.QuotaStatus_ZC);
		view.addObject("quota", quota);
		view.addObject("credit_seq", quota.getCredit_seq());
		return view;
	}
	
	/**
	 * 修改按钮
	 * @param zbid
	 * @return
	 */
	@RequestMapping(value="/editQuotaView",method=RequestMethod.GET)
	public ModelAndView editQuotaView(@RequestParam("credit_seq") String credit_seq){
		ModelAndView view = new ModelAndView();
		CreditQuota quota = quotaService.getQuotaInfo(credit_seq);
		view.setViewName("/credit/quota/editQuotaView");
		view.addObject("status",CreditConstants.QuotaStatus_ZC);
		view.addObject("quota", quota);
		view.addObject("credit_seq", quota.getCredit_seq());
		return view;
	}
	
	/***
	 * 修改按钮
	 * @param QuotaInfo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/updateQuotaInfo.do",method=RequestMethod.POST)
	public @ResponseBody String updateQuotaInfo(@RequestBody QuotaVo infoVo,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		quotaService.updateQuotaInfo(usrInfo,infoVo);
		return "";
	}
	
	
	/***
	 * 删除按钮
	 * @param QuotaInfo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteQuotaInfo.do",method=RequestMethod.POST)
	public @ResponseBody String deleteQuotaInfo(@RequestBody QuotaVo infoVo,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		quotaService.deleteQuotaInfo(usrInfo,infoVo);
		return "";
	}
	
	/***
	 * 禁用按钮
	 * @param QuotaInfo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/validQuotaInfo.do",method=RequestMethod.POST)
	public @ResponseBody String validQuotaInfo(@RequestBody QuotaVo infoVo,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		quotaService.validQuotaInfo(usrInfo,infoVo);
		return "";
	}
	
}
