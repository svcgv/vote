package com.indihx.system.cotroller;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.system.entity.RoleInfo;
import com.indihx.system.service.impl.RoleInfoServiceImpl;
import com.indihx.system.vo.RoleInfoVo;
import com.indihx.system.vo.RoleMenuInfoVo;

/** 
 * 项目名称：wx_mng_maven 
 * 类名称：RoleInfoController 
 * 类描述： 角色管理
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月9日 下午12:57:34 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
@Controller
@RequestMapping(value="/role")
public class RoleInfoController {
	
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(RoleInfoController.class);
	@Autowired
	private RoleInfoServiceImpl roleInfo;
    /**
     * 主页面
     * @return
     */
	@RequestMapping(value="/qryRoleInfo",method=RequestMethod.GET)
	public  ModelAndView qryRoleInfo(){
		Map<String,Object> map=roleInfo.qryRoleInfoList(new RoleInfoVo());
		ModelAndView view= new ModelAndView();
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("pageInfo", map.get("pageInfo"));
		view.setViewName("/role/qryRoleList");
		return view;
		
	}
	/**
	 * 查询
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/ajaxQryRoleInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> ajaxQryRoleInfo(@RequestBody RoleInfoVo InfoVo){
		Map<String,Object> map=roleInfo.qryRoleInfoList(InfoVo);
		return map;
		
	}
	/***
	 * 打开新增规则页面
	 * @return 返回新增页面VIEW
	 */
	@RequestMapping(value="/openAddRule",method=RequestMethod.GET)
	public ModelAndView openAddRule(){
		ModelAndView view = new ModelAndView();
		view.setViewName("role/addRoleInfo");
		return view;
	}
	
    /**
     * 保存
     * @param InfoVo
     * @return
     */
	
	@RequestMapping(value="/addRoleInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addRoleInfo(@RequestBody RoleInfoVo InfoVo){
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=roleInfo.addRoleInfo(InfoVo);
		map.put("status", isSuccess);
		return map;
		
	}
	/**
	 * 编辑页面
	 * @param InfoVo
	 * @return
	 */
	
	@RequestMapping(value="/qryRoleInfoById",method=RequestMethod.GET)
	public ModelAndView qryRoleInfoById(@RequestParam("roleId") String roleId){
		RoleInfo roleinfo = roleInfo.qryRoleInfoById(roleId);
		ModelAndView view = new ModelAndView();
		view.setViewName("/role/editRoleInfo");
		view.addObject("ruleInfo", roleinfo);
		return view;
	}

	/**
	 * 编辑保存
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/editSave",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> editSave(@RequestBody RoleInfoVo InfoVo){
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=roleInfo.updRoleInfoById(InfoVo);
		map.put("status", isSuccess);
		return map;
		
	}
	/**
	 * 删除
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/delRoleInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delRoleInfo(@RequestBody RoleInfoVo InfoVo){
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=roleInfo.deleteRoleInfo(InfoVo);
		map.put("status", isSuccess);
		return map;
		
	}
	/***
	 * 打开新增规则页面
	 * @return 返回新增页面VIEW
	 */
	@RequestMapping(value="/AddRule",method=RequestMethod.GET)
	public ModelAndView AddRule(){
		ModelAndView view = new ModelAndView();
		view.setViewName("role/qryMenuList");
		return view;
	}
	

	/***
	 * 查询用户菜单
	 * @param session  session
	 * @return  集合
	 */
	@RequestMapping(value="/qryMenuList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> qryMenuList(@RequestBody Map<String, String> reqMap){
		String roleId = reqMap.get("roleId").replaceAll(",", "").trim();
		Map<String, Object> map = roleInfo.qryMenuJson(roleId);
		return map;
	}
	/**
	 * 保存角色菜单
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/saveRoleInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveRoleInfo(@RequestBody RoleMenuInfoVo InfoVo){
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=roleInfo.saveRoleInfo(InfoVo);
		map.put("status", isSuccess);
		return map;
		
	}
}
