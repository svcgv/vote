package com.indihx.datamng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.entity.Wy_Jd;
import com.indihx.datamng.service.impl.StreetServiceImpl;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.datamng.vo.StreetInfoVo;
import com.indihx.system.service.impl.UsrInfoServiceImpl;
import com.indihx.system.vo.UserInfoVo;
import com.indihx.system.vo.UsrRoleVo;
import com.indihx.util.ConstantStatic;
/***
 * 
 * <p>描 述: 街道信息管理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年12月13日</p>
 * @author 
 */
@Controller
@RequestMapping("/street")
public class StreetController extends AbstractBaseController {
	
	@Resource
	private StreetServiceImpl streetImpl;
	@Resource
	private UsrInfoServiceImpl usrInfoImpl;
	/***
	 * 街道管理首页查询
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/qryStreet",method=RequestMethod.GET)
	public ModelAndView qryStreetList(){
		Map<String, Object> map = streetImpl.qryStreetList(new StreetInfoVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/street/qryStreetInfo");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}

	/***
	 * 首页通过查询条件查询数据信息
	 * @param streetVo  
	 * @return
	 */
	@RequestMapping(value="/ajaxQryStreetInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQryStreetInfo(@RequestBody StreetInfoVo streetVo){
		Map<String, Object> map = streetImpl.qryStreetList(streetVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	/***
	 * 打开新增街道页面
	 * @return 返回新增页面VIEW
	 */
	@RequestMapping(value="/openAddStreet",method=RequestMethod.GET)
	public ModelAndView openAddStreet(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/street/addStreet");
		view.addObject("options", streetImpl.qryParentId(new HpbInfoVo()));
		view.addObject("regionType",ConstantStatic.createHtmlByCode("REGION_TYPE"));
		return view;
	}
	//查询父菜单信息
	@RequestMapping(value="/queryAddPara",method=RequestMethod.GET)
	public ModelAndView queryAddPara(@RequestParam("hpblx") String hpblx){
		HpbInfoVo adminRegionVo = new HpbInfoVo();
		adminRegionVo.setHpblx(hpblx);
		Map<String, Object> map = streetImpl.selectByRegionType(adminRegionVo);
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/street/selectRegion");
		view.addObject("region", hpblx);
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("pageInfo", map.get("pageInfo"));
		return view;
	}
	@RequestMapping(value="/qryAdmStreetinfoList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> qryAdmStreetinfoList(@RequestParam("hpblx") String hpblx,@RequestBody HpbInfoVo adminRegionVo ){
		adminRegionVo.setHpblx(hpblx);
		Map<String, Object> map = streetImpl.selectByRegionType(adminRegionVo);
		ModelAndView view = new ModelAndView();
		view.addObject("listInfo",map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		return map;
	}
	
	/***
	 * 街道信息保存
	 * @param StreetInfo
	 * @return 返回成功或失败页
	 */
	@RequestMapping(value="/addStreetInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addStreetInfo(@RequestBody StreetInfoVo streetVo){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = streetImpl.addStreetInfo(streetVo);
		map.put("status", isSuccess);
		return map;
	}
	
	/**
	 * 查看街道信息
	 * @return
	 */
	@RequestMapping(value="/getStreetInfo",method=RequestMethod.GET)
	public ModelAndView getHpbInfo(@RequestParam("jdid") String jdid){
		ModelAndView view = new ModelAndView();
		Wy_Jd street = streetImpl.qryStreetById(jdid);
		view.setViewName("/datamng/street/getStreetInfo");
		view.addObject("info",street);
		Wy_Hpb region = streetImpl.selectByStreetIdKey(jdid);
		view.addObject("region", region);
		HpbInfoVo regionVo=new HpbInfoVo();
		view.addObject("options", streetImpl.qryParentId(regionVo));
		view.addObject("regionType",ConstantStatic.createHtmlByCode("REGION_TYPE",region.getHpblx()));
		return view;
	}
	
	/***
	 * 根据街道ID查询用户信息编辑
	 * @param infoVo
	 * @return
	 */
	@RequestMapping(value="/openUpdateStreet",method=RequestMethod.GET)
	public ModelAndView openUpdateStreet(@RequestParam("jdid") String jdid){
		ModelAndView view = new ModelAndView();
		Wy_Jd street = streetImpl.qryStreetById(jdid);
		view.setViewName("/datamng/street/editStreetInfo");
		view.addObject("info",street);
		Wy_Hpb region = streetImpl.selectByStreetIdKey(jdid);
		view.addObject("region", region);
		HpbInfoVo regionVo=new HpbInfoVo();
		view.addObject("options", streetImpl.qryParentId(regionVo));
		view.addObject("regionType",ConstantStatic.createHtmlByCode("REGION_TYPE",region.getHpblx()));
		return view;
	}
	/**
	 * 编辑保存
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/editStreetInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> editStreetInfo(@RequestBody StreetInfoVo InfoVo){
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=streetImpl.editStreetInfo(InfoVo);
		map.put("status", isSuccess);
		return map;
		
	}
	/**
	 * 删除
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/delStreetInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delStreetInfo(@RequestBody StreetInfoVo InfoVo){
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=streetImpl.deleteStreetInfo(InfoVo);
		map.put("status", isSuccess);
		return map;
		
	}
	/**
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/openAqstreetUserList",method=RequestMethod.GET)
	public ModelAndView openAqzeroUserList(HttpServletRequest req){
		String streetId = req.getParameter("jdid");
		Wy_Jd info = streetImpl.openAqstreetUserList(streetId);
		String pk_table = "Wy_jd";
		Map<String, Object> map = usrInfoImpl.openAqzeroUserListInfo(streetId,pk_table,new UserInfoVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/street/openAqstreetUserList");
		view.addObject("info",info);
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	@RequestMapping(value="/ajaxQryopenAqStreetUserListInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQryAqzeroInfo(HttpServletRequest req,@RequestBody UserInfoVo infoVo){
		String streetId = req.getParameter("jdid");
		String pk_table = "Wy_jd";
		Map<String, Object> map = usrInfoImpl.openAqzeroUserListInfo(streetId,pk_table,infoVo);
		return map;
	}
	/***
	 * 打开设置用户新增页面
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="/addUsrInfo",method=RequestMethod.GET)  
	public ModelAndView addUsrInfo(@RequestParam("jdid") String jdid){
		ModelAndView view = new ModelAndView();
		view.addObject("codeData",streetImpl.qryAddInfo());
		view.addObject("jdid", jdid);
		view.setViewName("/datamng/street/addUsrInfo");
		return view;
	}
	/**
     * 设置用户保存
     * @param InfoVo
     * @return
     */
	@RequestMapping(value="/addSetUserSave",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addSetUserSave(HttpServletRequest req,HttpSession session,@RequestBody UserInfoVo infoVo){
		infoVo.setOperUsr((getUser(session).getUsrId().toString()));
		Map<String, Object> map = new HashMap<String, Object>();
		String pk_table = "Wy_jd";
		String streetId = req.getParameter("jdid");
		boolean isSuccess = usrInfoImpl.addSetUserSave(infoVo,streetId,pk_table);
		map.put("status", isSuccess);
		return map;
	}
	/***
	 * 打开修改用户页面
	 * @param infoVo
	 * @return
	 */
	@RequestMapping(value="/openEditUsrInfo",method=RequestMethod.GET)
	public ModelAndView openEditUsrInfo(@RequestParam("usrId") String usrId){
		ModelAndView view =new ModelAndView();
		UserInfoVo vo = new UserInfoVo();
		vo.setUsrId(usrId);
		view.addObject("codeData",streetImpl.qryEditInfo());
		view.addObject("usrId",usrId);
		view.addObject("usrInfo",usrInfoImpl.qryUsrInfoById(vo));
		view.setViewName("/datamng/street/editUsrInfo");
		return view;
	}
	/***
	 * 通过请求后台打开ModelAndView的弹窗，用get方式
	 * 设置角色页面
	 * @param usrId
	 * @return
	 */
	@RequestMapping(value="/setUsrRole",method=RequestMethod.GET)
	public ModelAndView setUsrRole(@RequestParam("usrId") String usrId){
		String roles="Wy_jd";
		Map<String, Object> map = usrInfoImpl.qryUserRole(usrId,roles);
		ModelAndView view =new ModelAndView();
		view.setViewName("/datamng/street/setUsrRole");
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("usrId", usrId);
		return view;
	}
}
