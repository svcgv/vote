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
import com.indihx.activiti.entity.Application;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.datamng.service.IHpbService;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.org.entity.WY_WYGS;
import com.indihx.org.service.ICspService;
import com.indihx.org.vo.CspInfoVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic;
/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 企业信息管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月30日 上午 10:30:23</p>
 * <p></p>
 */
@Controller
@RequestMapping("/cspinfomng")
public class CspController extends AbstractBaseController {
	
	@Resource
	private ICspService cspservice;
	@Resource
	private IHpbService hpbService;
	/***
	 * 物业企业管理首页查询
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/cspList",method=RequestMethod.GET)
	public ModelAndView cspList(HttpSession session){
		UsrInfo user = getUser(session);
		String orgType = user.getOrgType();
		String orgId = ObjectUtil.toString(user.getOrgNo());
		String hpbid = user.getHpbBaseId();
//		String jdid = user.getStreetBaseId();
//		String sqid = user.getCommitteeBaseId();
		CspInfoVo cspVo=new CspInfoVo();
		if(orgType.equals(InitSysConstants.ORGTYPE_SHIJU)){
			//查询所有
		}else if(orgType.equals(InitSysConstants.ORGTYPE_QUJU)){
			cspVo.setHpbid(hpbid);
		}else if(orgType.equals(InitSysConstants.ORGTYPE_WYGS)){
			cspVo.setWygsid(orgId);
		}else if(orgType.equals(InitSysConstants.ORGTYPE_JIEDAO)){
			throw new BusinessException("用户没有权限...");
		}else if(orgType.equals(InitSysConstants.ORGTYPE_JUWEIHUI)){
			throw new BusinessException("用户没有权限...");
		}else{
			throw new BusinessException("用户没有权限...");
		}
		Map<String, Object> map = cspservice.cspList(user,cspVo);
		ModelAndView view = new ModelAndView();
		
		view.setViewName("/org/cspinfomng/cspList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 根据搜索条件查询
	 * @return 
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/ajaxcspList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQueryCspList(@RequestBody CspInfoVo cspVo,HttpSession session){
		UsrInfo user = getUser(session);
		String orgType = user.getOrgType();
		String orgId = ObjectUtil.toString(user.getOrgNo());
		String hpbid = user.getHpbBaseId();
//		String jdid = user.getStreetBaseId();
//		String sqid = user.getCommitteeBaseId();
		if(orgType.equals(InitSysConstants.ORGTYPE_SHIJU)){
			//查询所有
		}else if(orgType.equals(InitSysConstants.ORGTYPE_QUJU)){
			cspVo.setHpbid(hpbid);
		}else if(orgType.equals(InitSysConstants.ORGTYPE_WYGS)){
			cspVo.setWygsid(orgId);
		}else if(orgType.equals(InitSysConstants.ORGTYPE_JIEDAO)){
			throw new BusinessException("用户没有权限...");
		}else if(orgType.equals(InitSysConstants.ORGTYPE_JUWEIHUI)){
			throw new BusinessException("用户没有权限...");
		}else{
			throw new BusinessException("用户没有权限...");
		}
		Map<String, Object> map = cspservice.cspList(user,cspVo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
		
	}
	
	/***
	 * 打开新增物业企业页面
	 * @return 返回新增页面VIEW
	 */
	@RequestMapping(value="/openAddCsp",method=RequestMethod.GET)
	public ModelAndView openAddCsp(){
		ModelAndView view = new ModelAndView(); 
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.setViewName("/org/cspinfomng/addCsp");
		view.addObject("LOCAL_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG"));//是否本地
		view.addObject("CERT_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG"));//是否三证合一
		view.addObject("ORG_NATURE",ConstantStatic.createHtmlByCode("ORG_NATURE"));//企业类型
		view.addObject("CSP_LEVEL",ConstantStatic.createHtmlByCode("CSP_LEVEL"));//企业资质
		view.addObject("sjzt",InitSysConstants.DATA_ZhanCun);
		return view; 
	}
 
	/**
     * 保存企业信息
     * @param InfoVo
     * @return
	 * @throws Exception 
     */
	@RequestMapping(value="/saveCspInfo",method=RequestMethod.POST)
	public @ResponseBody Long saveCspInfo(@RequestBody CspInfoVo cspVo,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		Application app = cspservice.saveCspInfo(usrInfo,cspVo);
		if(app==null)return null;
		return app.getAppId();
	}
	 
	
	
	/**
	 * 打开编辑物业企业页面
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/openUpdateCsp",method=RequestMethod.GET)
	public @ResponseBody ModelAndView openUpdateCsp(@RequestParam("wygsid") String wygsid){
		ModelAndView view = new ModelAndView();
		//查询企业详情
		WY_WYGS csp = cspservice.qrCspInfoById(wygsid);
		view.addObject("csp", csp); 
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.addObject("LOCAL_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",csp.getLocalflag()));//是否本地
		view.addObject("CERT_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",csp.getCertflag()));//是否三证合一
		view.addObject("ORG_NATURE",ConstantStatic.createHtmlByCode("ORG_NATURE",csp.getGslx()));//企业类型
		view.addObject("CSP_LEVEL",ConstantStatic.createHtmlByCode("CSP_LEVEL",csp.getZzdj()));//企业资质
		view.addObject("sjzt",InitSysConstants.DATA_XiuGaiZhong);
		view.setViewName("/org/cspinfomng/editCsp");
		return view;
	}
 
	
	/**
	 * 打开物业企业详情页面
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/openGetCsp",method=RequestMethod.GET)
	public ModelAndView openGetCsp(@RequestParam("wygsid") String wygsid){
		ModelAndView view = new ModelAndView();
		//查询企业详情
		WY_WYGS csp = cspservice.qrCspInfoById(wygsid);
		view.setViewName("/org/cspinfomng/getCsp");
		view.addObject("csp", csp); 
		//查询区列表
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		
		view.addObject("LOCAL_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",csp.getLocalflag()));//是否本地
		view.addObject("CERT_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",csp.getCertflag()));//是否三证合一
		view.addObject("ORG_NATURE",ConstantStatic.createHtmlByCode("ORG_NATURE",csp.getGslx()));//企业类型
		view.addObject("CSP_LEVEL",ConstantStatic.createHtmlByCode("CSP_LEVEL",csp.getZzdj()));//企业资质
		return view;
	}
	 
 
	/**
	 * 打开物业企业详情页面
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/delCsp",method=RequestMethod.GET)
	public ModelAndView delCsp(@RequestParam("wygsid") String wygsid){
		ModelAndView view = new ModelAndView();
		//查询企业详情
		WY_WYGS csp = cspservice.qrCspInfoById(wygsid);
		view.setViewName("/org/cspinfomng/delCsp");
		view.addObject("csp", csp); 
		//查询区列表
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		
		view.addObject("LOCAL_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",csp.getLocalflag()));//是否本地
		view.addObject("CERT_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",csp.getCertflag()));//是否三证合一
		view.addObject("ORG_NATURE",ConstantStatic.createHtmlByCode("ORG_NATURE",csp.getGslx()));//企业类型
		view.addObject("CSP_LEVEL",ConstantStatic.createHtmlByCode("CSP_LEVEL",csp.getZzdj()));//企业资质
		view.addObject("sjzt",InitSysConstants.DATA_ShanChuZhong);
		return view;
	}
 
	
	/***
	 * 新增、编辑 提交按钮
	 * @param appid
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/sendToNextStep.do",method=RequestMethod.POST)
	public @ResponseBody Long sendToNextStep(@RequestBody CspInfoVo cspVo,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		Application app = cspservice.saveCspInfo(usrInfo,cspVo);
		if(app==null)return null;
		return app.getAppId();
	}
	
}
