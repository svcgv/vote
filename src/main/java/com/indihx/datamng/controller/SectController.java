package com.indihx.datamng.controller;

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
import com.indihx.datamng.entity.Wy_Sect;
import com.indihx.datamng.service.IHpbService;
import com.indihx.datamng.service.ISectService;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.datamng.vo.SectVo;
import com.indihx.org.service.ICspService;
import com.indihx.org.vo.CspInfoVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic;
import com.indihx.util.StringUtils;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: 控制器-Controller</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月5日上午11:18:05</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>SectController.java</p>
 * <p>注意：控制器与页面按钮呈一一对应关系，故而其方法命名必须规范以便运维，增加代码的易读性。</p>
 */
@Controller
@RequestMapping("/sect")
public class SectController extends AbstractBaseController{

	@Resource
	ISectService sectService;
	@Resource
	IHpbService hpbService;
	@Resource
	ICspService cspService;
	/**
	 * 首页
	 * 菜单进入首页查询项目列表
	 * 查询登录用户的管理项目列表
	 * @return
	 */
	@RequestMapping(value="/getSectList",method=RequestMethod.GET)
	public ModelAndView getSectList(HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = sectService.getSectList(user,new SectVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/sect/getSectList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("hpbList", map.get("hpbList"));
		return view;
	}
	
	/***
	 * 查询按钮
	 * Ajax首页通过查询条件查询数据信息
	 * @param sectVo  
	 * @return
	 */
	@RequestMapping(value="/ajaxQrySectList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQrySectList(@RequestBody SectVo infoVo,HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = sectService.getSectList(user,infoVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/***
	 * onChange事件触发
	 * 异步根据选中的区县ID加载对应的街道列表
	 * Ajax首页通过查询条件查询数据信息
	 * @param sectVo
	 * @return
	 */
	@RequestMapping(value="/ajaxGetJdList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getJdList(String hpbid){
		SectVo infoVo = new SectVo();
		infoVo.setHpbid(hpbid);
		Map<String, Object> map = sectService.getJdList(infoVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("jdlist", map.get("jdlist"));
		return respMap;
	}
	
	/***
	 * onChange事件触发
	 * 异步根据选中的街道ID加载对应的社区列表
	 * Ajax首页通过查询条件查询数据信息
	 * @param sectVo
	 * @return
	 */
	@RequestMapping(value="/ajaxGetSqList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getSqList(String jdid){
		SectVo infoVo = new SectVo();
		infoVo.setJdid(jdid);
		Map<String, Object> map = sectService.getSqList(infoVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("sqlist", map.get("sqlist"));
		return respMap;
	}
	
	/**
	 * 新增按钮
	 * @return
	 */
	@RequestMapping(value="/addSectView",method=RequestMethod.GET)
	public ModelAndView addSectView(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/sect/addSectView");
		//物业类型字典项
		view.addObject("SECTTYPE",ConstantStatic.createHtmlByCode("SECTTYPE"));
		view.addObject("SECTTYPESUB1",ConstantStatic.createHtmlByCode("SECTTYPESUB1"));
		view.addObject("SECTTYPESUB2",ConstantStatic.createHtmlByCode("SECTTYPESUB2"));
		
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.addObject("sjzt",InitSysConstants.DATA_ZhanCun);
		return view;
	}
	
	/***
	 * 保存按钮
	 * @param SectInfo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/saveSectInfo.do",method=RequestMethod.POST)
	public @ResponseBody Long saveSectInfo(@RequestBody SectVo infoVo,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		Application app = sectService.saveSectInfo(usrInfo,infoVo);
		if(app==null)return null;
		return app.getAppId();
	}
	
	/***
	 * 提交按钮
	 * @param SectInfo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/sendToNextStep.do",method=RequestMethod.POST)
	public @ResponseBody Application sendToNextStep(@RequestBody SectVo infoVo,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);
		Application app = sectService.saveSectInfo(usrInfo,infoVo);
		if(app==null)return null;
		return app;
	}
	
	/**
	 * 查看按钮
	 * @return
	 */
	@RequestMapping(value="/getSectView",method=RequestMethod.GET)
	public ModelAndView getSectInfo(@RequestParam("xmid") String xmid){
		ModelAndView view = new ModelAndView();
		Wy_Sect sect = sectService.getSectInfo(xmid);
		view.setViewName("/datamng/sect/getSectView");
		view.addObject("sect", sect);
		view.addObject("xmid", sect.getXmid());
		return view;
	}
	
	/**
	 * 修改按钮
	 * @param xmid
	 * @return
	 */
	@RequestMapping(value="/editSectView",method=RequestMethod.GET)
	public ModelAndView editSectView(@RequestParam("xmid") String xmid){
		ModelAndView view = new ModelAndView();
		Wy_Sect sect = sectService.getSectInfo(xmid);
		//物业类型字典项
		view.addObject("SECTTYPE",ConstantStatic.createHtmlByCode("SECTTYPE",sect.getWyxz()));
		view.addObject("SECTTYPESUB1",ConstantStatic.createHtmlByCode("SECTTYPESUB1",sect.getWylx()));
		view.addObject("SECTTYPESUB2",ConstantStatic.createHtmlByCode("SECTTYPESUB2",sect.getWylx()));
		//区下拉选项
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		SectVo infoVo = new SectVo();
		infoVo.setHpbid(sect.getHpbid());
		infoVo.setJdid(sect.getJdid());
		infoVo.setSqid(sect.getSqid());
		if(!StringUtils.isEmpty(sect.getHpbid())){
			//街道下拉选项
			Map<String, Object> jdmap =sectService.getJdList(infoVo);
			String jdselect = (String) jdmap.get("jdlist");
			view.addObject("jdlist", jdselect);
			if(!StringUtils.isEmpty(sect.getJdid())){
				//社区下拉选项
				Map<String, Object> sqmap =sectService.getSqList(infoVo);
				String sqselect = (String) sqmap.get("sqlist");
				view.addObject("sqlist", sqselect);
			}
		}
		
		view.setViewName("/datamng/sect/editSectView");
		view.addObject("sect", sect);
		view.addObject("xmid",xmid);
		view.addObject("sjzt",InitSysConstants.DATA_XiuGaiZhong);
		return view;
	}
	
	/***
	 * 删除按钮
	 * @param SectInfo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteSectView",method=RequestMethod.GET)
	public ModelAndView deleteSectView(@RequestParam("xmid") String xmid){
		ModelAndView view = new ModelAndView();
		Wy_Sect sect = sectService.getSectInfo(xmid);
		view.setViewName("/datamng/sect/deleteSectView");
		view.addObject("sect", sect);
		view.addObject("xmid",xmid);
		view.addObject("sjzt",InitSysConstants.DATA_ShanChuZhong);
		return view;
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
	 * 选择管理处按钮
	 * @param session
	 */
	@RequestMapping(value="/openGlcList",method=RequestMethod.GET)
	public ModelAndView openGlcList(@RequestParam("xmid") String xmid,@RequestParam("wygsid") String wygsid,HttpSession session){
		SectVo  vo = new SectVo();
		vo.setXmid(xmid);
		vo.setWygsid(wygsid);
		Map<String, Object> map = sectService.getCsList(vo);
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/sect/openGlcList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("wygsid", map.get("wygsid"));
		return view;
	}
	
	/***
	 * 选择项目经理按钮
	 * @param session
	 */
	@RequestMapping(value="/openXmjlList",method=RequestMethod.GET)
	public ModelAndView openXmjlList(@RequestParam("xmid") String xmid,@RequestParam("wygsid") String wygsid,HttpSession session){
		SectVo  vo = new SectVo();
		vo.setXmid(xmid);
		vo.setWygsid(wygsid);
		Map<String, Object> map = sectService.getXmjlList(vo);
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/sect/openXmjlList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("wygsid", map.get("wygsid"));
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
	 * 选择管理处查询按钮
	 * Ajax首页通过查询条件查询数据信息
	 * @param sectVo  
	 * @return
	 */
	@RequestMapping(value="/ajaxcspSectList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxcspSectList(@RequestBody SectVo infoVo){
		Map<String, Object> map = sectService.getCsList(infoVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
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
}
