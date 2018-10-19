package com.indihx.org.controller;

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
import com.indihx.comm.exception.BusinessException;
import com.indihx.org.entity.WY_WYGS_STAFF;
import com.indihx.org.service.ICspStaffService;
import com.indihx.org.vo.CspStaffVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CspStaffController.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月9日 上午10:58:02</p>
 * <p>@author ChuJinze</p>
 * <p>@version 1.0</p>
 * <p>CspStaffController.java</p>
 * <p></p>
 */

@Controller
@RequestMapping("/cspstaff")
public class CspStaffController extends AbstractBaseController {
	
	@Resource
	private ICspStaffService cspStaffService;
	
	/**
	 * 企业人员信息首页
	 * @return 返回首页jsp页面
	 */
	@RequestMapping(value="/staffList",method=RequestMethod.GET)
	public ModelAndView getCsList(HttpSession session){
		UsrInfo usrInfo = getUser(session);
		Map<String, Object> map = cspStaffService.getStaffList(new CspStaffVo(),usrInfo);
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/cspstaff/getStaffList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("USER_TYPE",ConstantStatic.createHtmlByCode("USER_TYPE"));
		return view;
	}
	
	/***
	 * 根据搜索条件查询
	 * @return 
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/ajaxStaffList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQueryCsList(@RequestBody CspStaffVo cspStaffVo,HttpSession session){
		UsrInfo usrInfo = getUser(session);
		Map<String, Object> map = cspStaffService.getStaffList(cspStaffVo,usrInfo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}

	/***
	 * 打开新增页面
	 * @return 
	 * @return  返回新增页面VIEW
	 */
	@RequestMapping(value="/openAddStaff",method=RequestMethod.GET)
	public ModelAndView openAddStaff(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/cspstaff/addStaffInfo");
		view.addObject("SEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE"));
		view.addObject("EDUCATION",ConstantStatic.createHtmlByCode("EDUCATION"));
		view.addObject("CERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE"));
		view.addObject("USER_TYPE",ConstantStatic.createHtmlByCode("USER_TYPE"));
		return view; 
	}

	/**
     * 新增保存企业人员信息
     * @param CspStaffVo
     * @return
	 * @throws BusinessException 
     */
	@RequestMapping(value="/addStaffInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addStaffInfo(@RequestBody CspStaffVo cspStaffVo,HttpSession session) throws BusinessException{
		UsrInfo usrInfo = getUser(session);
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=cspStaffService.addStaffInfo(cspStaffVo,usrInfo);
		map.put("status", isSuccess);
		return map;
	}

	/**
	 * 删除企业人员信息
	 * @param CspStaffVo
	 * @return
	 */
	@RequestMapping(value="/delStaffInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delStaffInfo(@RequestBody CspStaffVo cspStaffVo){
		String gsryid = cspStaffVo.getGsryid().toString();
		String[] gsryidList = gsryid.split(",");
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=cspStaffService.delStaffInfo(gsryidList);
		map.put("status", isSuccess);
		return map;
	}
	
	/**
	 * 查看企业人员信息
	 * @param gsryid
	 * @return
	 */
	@RequestMapping(value="/openGetStaff",method=RequestMethod.GET)
	public ModelAndView openGetStaff(@RequestParam("gsryid") String gsryid){
		WY_WYGS_STAFF staff = cspStaffService.qrStaffInfoById(gsryid);
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/cspstaff/getStaffInfo");
		view.addObject("staff", staff); 
		view.addObject("SEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE",staff.getRyxb().toString()));
		view.addObject("EDUCATION",ConstantStatic.createHtmlByCode("EDUCATION",staff.getWhcd().toString()));
		view.addObject("CERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE",staff.getZjlx().toString()));
		view.addObject("USER_TYPE",ConstantStatic.createHtmlByCode("USER_TYPE",staff.getRylx().toString()));
		view.addObject("INFO_STATUS",ConstantStatic.createHtmlByCode("INFO_STATUS",staff.getStatus().toString()));
		return view;
	}
	
	/**
	 * 编辑企业人员信息页面
	 * @param gsryid
	 * @return
	 */
	@RequestMapping(value="/openUpdateStaff",method=RequestMethod.GET)
	public ModelAndView openUpdateStaff(@RequestParam("gsryid") String gsryid){
		WY_WYGS_STAFF staff = cspStaffService.qrStaffInfoById(gsryid);
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/cspstaff/editStaffInfo");
		view.addObject("staff", staff); 
		view.addObject("SEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE",staff.getRyxb().toString()));
		view.addObject("EDUCATION",ConstantStatic.createHtmlByCode("EDUCATION",staff.getWhcd().toString()));
		view.addObject("CERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE",staff.getZjlx().toString()));
		view.addObject("USER_TYPE",ConstantStatic.createHtmlByCode("USER_TYPE",staff.getRylx().toString()));
		view.addObject("INFO_STATUS",ConstantStatic.createHtmlByCode("INFO_STATUS",staff.getStatus().toString()));
		return view;
	}

	/**
     * 编辑保存人员信息
     * @param cspSectVo
     * @return
	 * @throws BusinessException 
     */
	@RequestMapping(value="/saveCspInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveCspInfo(@RequestBody CspStaffVo cspStaffVo) throws BusinessException{
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=cspStaffService.editStaffInfo(cspStaffVo);
		map.put("status", isSuccess);
		return map;
	}
	

}
