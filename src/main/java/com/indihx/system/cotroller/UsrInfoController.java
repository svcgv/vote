package com.indihx.system.cotroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.StringUtil;
import com.indihx.AbstractBaseController;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.UsrInfoServiceImpl;
import com.indihx.system.vo.OrgInfoVo;
import com.indihx.system.vo.UserInfoVo;
import com.indihx.system.vo.RoleInfoVo;
import com.indihx.util.MapToEntryConvertUtils;

/***
 * 
 * <p>描 述: 用户管理 </p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年2月16日</p>
 * @author 谢追辉
 */
@Controller
@RequestMapping("/usr")  
public class UsrInfoController extends AbstractBaseController {
	@Autowired
	private UsrInfoServiceImpl IUsrInfoService;
	
	/***
	 * 查询用户信息，返回用页面
	 * @return 返回页面模版
	 */
	@RequestMapping(value="/qryUsrInfo",method=RequestMethod.GET)  
	public ModelAndView qryUsrInfo(){
		Map<String, Object> map= IUsrInfoService.qryUsrInfoList(new UserInfoVo());
		ModelAndView view = new ModelAndView();
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		view.setViewName("/usr/qryUsrList");
		return view;
	}
	
	@RequestMapping(value="/qryUsrList",method=RequestMethod.POST)  
	public ModelAndView qryUsrList(){
		Map<String, Object> map= IUsrInfoService.qryUsrInfoList(new UserInfoVo());
		ModelAndView view = new ModelAndView();
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		view.setViewName("/usr/qryUsrList");
		return view;
	}
	
	@RequestMapping(value="/ajaxQryUsrInfo",method=RequestMethod.POST)  
	@ResponseBody
	public  Map<String, Object> ajaxQryUsrInfo(@RequestBody UserInfoVo infoVo){
		Map<String, Object> map = IUsrInfoService.qryUsrInfoList(infoVo);
		return map;
	}
	/***
	 * 打开新增页面
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/addUsrInfo",method=RequestMethod.GET)  
	public ModelAndView addUsrInfo(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/usr/addUsrInfo");
		return view;
	}
	
	
	/**
	 * 打开机构信息新增选择所属机构页面
	 * @return
	 */
	@RequestMapping(value="/queryAddPara",method=RequestMethod.GET)
	public ModelAndView queryAddPara(@RequestParam("type") String type){
		ModelAndView view = new ModelAndView();
		
			HpbInfoVo hpbVo = new  HpbInfoVo();
			view.setViewName("/usr/org");	
		
		view.addObject("type",type);
		return view;
	}
	
	/***
	 * 用户信息保存
	 * @param usrInfo
	 * @return 返回成功或失败页
	 */
	@RequestMapping(value="/addSave",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addUsrInfo(HttpSession session,@RequestBody UserInfoVo infoVo){
		infoVo.setOperUsr((getUser(session).getUsrId().toString()));
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = IUsrInfoService.addUsrInfo(infoVo);
		map.put("status", isSuccess);
		return map;
	}
	
	/***
	 * 根据用户ID查询用户信息
	 * @param infoVo
	 * @return
	 */
	@RequestMapping(value="/qryUsrInfoById",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> qryUsrInfoById(@RequestBody UserInfoVo infoVo){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usrInfo", IUsrInfoService.qryUsrInfoById(infoVo));
		return map;
	}
	
	/***
	 * 打开修改页面
	 * @param infoVo
	 * @return
	 */
	@RequestMapping(value="/openEditUsrInfo",method=RequestMethod.POST)
	public ModelAndView openEditUsrInfo(@RequestParam("usrId") String usrId){
		String roles="";
		Map<String, Object> map = IUsrInfoService.qryUserRole(usrId,roles);
		ModelAndView view =new ModelAndView();
		UserInfoVo vo = new UserInfoVo();
		vo.setUsrId(usrId);
		view.addObject("usrId",usrId);
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("usrInfo",IUsrInfoService.qryUsrInfoById(vo));
		view.addObject("orgName",IUsrInfoService.qryUsrOrgNameInfoById(usrId));
		view.setViewName("/usr/editUsrInfo");
		return view;
	}
	
	/***
	 * 修改保存
	 * @param infoVo
	 * @return
	 */
	@RequestMapping(value="/editSave",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> editSave(HttpSession session,@RequestBody UserInfoVo infoVo){
		infoVo.setOperUsr((getUser(session).getUsrId().toString()));
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSucess = IUsrInfoService.updUserInfoById(infoVo);
		map.put("success", isSucess);
		return map;
	}
	
	/***
	 * 修改用户信息
	 * @param infoVo
	 * @return
	 */
	@RequestMapping(value="/setUserInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> setUserInfo(HttpSession session,@RequestBody Map<String, String> reqMap){
		UserInfoVo infoVo = new UserInfoVo();
		MapToEntryConvertUtils.convertMaptoBean(reqMap, infoVo);
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSucess = IUsrInfoService.setUserInfo(infoVo,reqMap.get("pass_word_new"));
		map.put("success", isSucess);
		return map;
	}
	
	/***
	 * 删除
	 * @param infoVo
	 * @return
	 */
	@RequestMapping(value="/delUsrInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> delUsrInfo(@RequestBody UserInfoVo infoVo){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSucess = IUsrInfoService.deleteUsrInfo(infoVo);
		map.put("success", isSucess);
		return map;
	}
	
	/***
	 * 通过请求后台打开ModelAndView的弹窗，用get方式
	 * 设置角色页面
	 * @param usrId
	 * @return
	 */
	@RequestMapping(value="/setUsrRole",method=RequestMethod.GET)
	public ModelAndView setUsrRole(@RequestParam("usrId") String usrId){
		String roles="";
		Map<String, Object> map = IUsrInfoService.qryUserRole(usrId,roles);
		ModelAndView view =new ModelAndView();
		view.setViewName("/usr/setUsrRole");
		view.addObject("listInfo",map.get("listInfo"));
		view.addObject("usrId", usrId);
		return view;
	}
	
	/**
	 * 设置角色页面     查询
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/ajaxQryRoleInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> ajaxQryRoleInfo(HttpServletRequest req,@RequestBody RoleInfoVo InfoVo){
		String roles="";
		String usrId =req.getParameter("usrId");
		Map<String, Object> map=IUsrInfoService.qryUserRole(usrId,roles,InfoVo.getRoleName());
		return map;
		
	}
	
	/***
	 * 保存角色人员关系
	 * @return 成功or失败
	 */
	@RequestMapping(value="/addRoleUser",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addRoleUser(@RequestBody Map<String, String> reqMap){
		String roleId = reqMap.get("roleId");
		String usrId = reqMap.get("usrId");
		String[] strList = StringUtil.isEmpty(roleId) == true ? new String[0] : roleId.split(",");
		boolean sucess = IUsrInfoService.saveUserRole(strList, usrId);
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("sucess", sucess);
		return map;
	}
	
	/***
	 * 查询用户菜单
	 * @param session  session
	 * @return  集合
	 */
	@RequestMapping(value="/qryMenuByUser",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> qryMenuByUser(@RequestBody Map<String, String> reqMap){
		String usrId = reqMap.get("usrId");
		Map<String, Object> map = IUsrInfoService.qryMenuJson(usrId);
		return map;
	}
	
	/***
	 * 保存用户菜单信息
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value="/saveUserMenu",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveUserMenu(@RequestBody Map<String, String> reqMap){
		String menuId = reqMap.get("menuId");
		String usrId = reqMap.get("usrId");
		String[] menuList = menuId.split("\\|");
		boolean sucess = IUsrInfoService.saveUserMenu(menuList, usrId);
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("sucess", sucess);
		return map;
	}
	
	/***
	 * 打开机构选择页面
	 * @param reqMap  请求参数
	 * @return
	 */
	@RequestMapping(value="/openOrgInfo",method=RequestMethod.GET)
	public  ModelAndView openOrgInfo(@RequestParam("orgType") String orgType){
		List<Map<String, Object>> list = IUsrInfoService.qryOrgListByType(orgType,null);
		ModelAndView  view= new ModelAndView();
		view.setViewName("/usr/openOrgInfo");
		view.addObject("listInfo", list);
		view.addObject("orgType", orgType);
		return view;
	}
	
	@RequestMapping(value="/ajaxQryOrgInfo",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> ajaxQryOrgInfo(@RequestBody Map<String, String> reqMap){
		String orgType = reqMap.get("orgType");
		String orgName = reqMap.get("orgName");
		List<Map<String, Object>> list = IUsrInfoService.qryOrgListByType(orgType,orgName);
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("listInfo", list);
		return map;
	}
	
	/***
	 * 打开新增页面
	 * @return
	 */
	@RequestMapping(value="/openaddUsrInfo")
	public String openAddUsrInfo(){
		return "/usr/addUsrInfo";
	}
	@RequestMapping(value="/openUserOrgInfo",method=RequestMethod.GET)
	public  ModelAndView openUserOrgInfo(HttpServletRequest req){
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("01","01");
		map1.put("02","02");
		map1.put("03","03");
		map1.put("04","04");
		map1.put("05","05");
		map1.put("06","06");
	    String def_org_role = req.getParameter("orgType");
	    String[] def_role_arr = null;
	    String[] def_org_type_arr=null;
	    if(def_org_role!=null&&!"".equals(def_org_role)){
	    	def_role_arr = def_org_role.split(",");
	    	def_org_type_arr = new String[def_role_arr.length];
		    for(int i=0;i<def_role_arr.length;i++){
		    	def_org_type_arr[i]=(String)map1.get(def_role_arr[i]);
		    }
	    }
		Map<String, Object> map= IUsrInfoService.qryOrgListByRoleId(def_org_type_arr,new OrgInfoVo());
		ModelAndView view = new ModelAndView();
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		view.addObject("orgType",def_org_role);
		view.setViewName("/usr/openOrgInfo");
		return view;
	}
	@RequestMapping(value="/ajaxQryopenUserOrgInfo",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> ajaxQryopenUserOrgInfo(HttpServletRequest req,@RequestBody OrgInfoVo infoVo){
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("01","01");
		map1.put("02","02");
		map1.put("03","03");
		map1.put("04","04");
		map1.put("05","05");
		map1.put("06","06");
	    String def_org_role = req.getParameter("orgType");
	    String[] def_role_arr = null;
	    String[] def_org_type_arr=null;
	    if(def_org_role!=null&&!"".equals(def_org_role)){
	    	def_role_arr = def_org_role.split(",");
	    	def_org_type_arr = new String[def_role_arr.length];
		    for(int i=0;i<def_role_arr.length;i++){
		    	def_org_type_arr[i]=(String)map1.get(def_role_arr[i]);
		    }
	    }
	    Map<String, Object> map= IUsrInfoService.qryOrgListByRoleId(def_org_type_arr,infoVo);
	    Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("listInfo", map.get("listInfo"));
		respMap.put("pageInfo", map.get("pageInfo"));
		return respMap;
	}
	/**
	   * 启用操作
	   * @param infovo
	   * @return
	   */
		@RequestMapping(value="/openStaUsrInfo",method=RequestMethod.POST)
		public  @ResponseBody Map<String,Object> openStaUsrInfo(@RequestBody UserInfoVo infovo){
			Map<String,Object> map=new HashMap<String,Object>();
			boolean isSuccess=IUsrInfoService.openStaUsrInfo(infovo);
			map.put("status", isSuccess);
			return map;
		}
		/**
		   * 关闭操作
		   * @param infovo
		   * @return
		   */
			@RequestMapping(value="/closeStaUsrInfo",method=RequestMethod.POST)
			public  @ResponseBody Map<String,Object> closeStaUsrInfo(@RequestBody UserInfoVo infovo){
				Map<String,Object> map=new HashMap<String,Object>();
				boolean isSuccess=IUsrInfoService.closeStaUsrInfo(infovo);
				map.put("status", isSuccess);
				return map;
			}
	
	@RequestMapping(value="/getUsrByRoleId.do",method=RequestMethod.POST)
	public @ResponseBody List<UsrInfo> qryUsrByRoleId(@RequestBody Map<String, Object> reqMap ){
		String roleId = String.valueOf(reqMap.get("roleId"));
		return IUsrInfoService.qryUsrByRoleId(roleId);
	}
}
