package com.indihx.system.cotroller;
import java.util.HashMap;
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

import com.indihx.AbstractBaseController;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.datamng.vo.StreetInfoVo;
import com.indihx.system.service.impl.OrgInfoServiceImpl;
import com.indihx.system.vo.OrgInfoVo;
/** 
 * 项目名称：wx_mng_maven 
 * 类名称：RoleInfoServiceImpl 
 * 类描述： 机构管理
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月9日 下午2:24:16 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
@Controller
@RequestMapping(value="/org")
public class OrgInfoController  extends AbstractBaseController{
	
	@Autowired 
	private OrgInfoServiceImpl infoService;
	/**
	 * 主页面
	 * @return
	 */
	@RequestMapping(value="/qryOrgInfo",method=RequestMethod.GET)
	public ModelAndView qryOrgInfo(){
		Map<String,Object> map=infoService.qryOrgInfo(new OrgInfoVo());
		ModelAndView view = new ModelAndView();
		view.addObject("listInfo",map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		view.addObject("codeData",infoService.qryAddInfo());
		view.setViewName("/org/qryOrgList");
		return view;
	}
	/**
	 * 查询
	 * @param infovo
	 * @return
	 */
	@RequestMapping(value="/qryOrginfoList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> qryOrginfoList(@RequestBody OrgInfoVo infovo){
		Map<String,Object> map=infoService.qryOrgInfo(infovo);
		return map;
	}
	/**
	 * 打开参数信息新增页面
	 * @return
	 */
	@RequestMapping(value="/openAddOrg",method=RequestMethod.GET)
	public ModelAndView openAddOrg(){
		Map<String,Object> map=infoService.qryparOrgNameInfo();
		ModelAndView view = new ModelAndView();
		view.addObject("codeData",infoService.qryAddInfo());
		view.addObject("parOrgName",map.get("parOrgName"));
		view.addObject("parOrgNo",map.get("parOrgNo"));
		view.setViewName("/org/addOrgInfo");
		return view;
	}

	
	/**
	 * 新增保存
	 * @param infoVo
	 * @return
	 */
	@RequestMapping(value="/addSave",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addSave(@RequestBody OrgInfoVo infoVo,HttpSession session,HttpServletRequest req){
		infoVo.setOperUsr(getUser(session).getUsrName().toString());
		String jdid = req.getParameter("jdid");
		String hpbid = req.getParameter("hpbid");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = infoService.addOrgInfo(infoVo,jdid,hpbid);
		map.put("status", isSuccess);
		return map;
	}
	/**
	 * 编辑页面
	 */
	@RequestMapping(value="/qryRoleInfoById",method=RequestMethod.GET)
	public ModelAndView qryRoleInfoById(@RequestParam("orgNo") String orgNo,@RequestParam("orgType") String orgType){
		OrgInfoVo orginfo = infoService.qryOrginfoList(orgNo);
		ModelAndView view = new ModelAndView();
		orgType = orgType.replaceAll(",", "").trim();
		if("03".equals(orgType)){
			view.addObject("strName", infoService.qryStreetinfoList(orgNo));
		}if("02".equals(orgType)){
			view.addObject("hpbName", infoService.qryHpbinfoList(orgNo));
		}
		view.setViewName("/org/editOrgInfo");
		view.addObject("orginfo", orginfo);
		return view;
	}
	/**
	 * 编辑保存
	 * @param infoVo
	 * @return
	 */
	@RequestMapping(value="/editSave",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> editSave(@RequestBody OrgInfoVo infoVo,HttpSession session){
		infoVo.setOperUsr(getUser(session).getUsrName().toString());
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=infoService.updateOrgInfo(infoVo);
		map.put("status",isSuccess );
		return map;
	}
	/**
	 * 打开机构信息新增选择上级机构页面
	 * @return
	 */
	@RequestMapping(value="/queryAddPara",method=RequestMethod.GET)
	public ModelAndView queryAddPara(@RequestParam("orgType") String orgType){
		ModelAndView view = new ModelAndView();
		
			HpbInfoVo hpbVo = new  HpbInfoVo();
			view.setViewName("/org/org");	
		
		view.addObject("orgType",orgType);
		return view;
	}
	//查询市局信息
	@RequestMapping(value="/qryParentId",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> qryParentId(@RequestBody OrgInfoVo orgInfovo){
		Map<String, String> map = new HashMap<String, String>();
		map.put("parentOrgNo", infoService.qryParentId(orgInfovo));
		map.put("parentOrgName", infoService.qryParentName(orgInfovo));
		return map;
	}
	//查询区局信息
	@RequestMapping(value="/qryStreetOrgId",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> qryStreetOrgId(@RequestParam("hpbid") String hpbid,@RequestBody OrgInfoVo orgInfovo){
		Map<String, String> map = new HashMap<String, String>();
		map.put("parentOrgNo", infoService.qryStreetOrgId(orgInfovo,hpbid));
		map.put("parentOrgName", infoService.qryStreetOrgName(orgInfovo,hpbid));
		return map;
	}
	@RequestMapping(value="/ajaxQryqueryHpbAddPara",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> ajaxQryqueryHpbAddPara(@RequestParam("orgType") String orgType,@RequestBody HpbInfoVo infovo){
		Map<String,Object> map=infoService.qryParentOrgInfo(infovo);
		ModelAndView view = new ModelAndView();
		view.addObject("listInfo",map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		return map;
	}
	@RequestMapping(value="/ajaxQryqueryStreetHpbAddPara",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> ajaxQryqueryStreetAddPara(@RequestParam("orgType") String orgType,@RequestBody StreetInfoVo infovo){
		Map<String,Object> map=infoService.qryParentOrgStreetInfo(infovo);
		ModelAndView view = new ModelAndView();
		view.addObject("listInfo",map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		return map;
	}
	
  /**
   * 删除操作
   * @param infovo
   * @return
   */
	@RequestMapping(value="/delOrgInfo")
	public  @ResponseBody Map<String,Object> delOrgInfo(@RequestBody OrgInfoVo infovo){
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=infoService.delectOrgInfo(infovo);
		map.put("status", isSuccess);
		return map;
	}
	/**
	   * 启用操作
	   * @param infovo
	   * @return
	   */
		@RequestMapping(value="/openStaOrgInfo",method=RequestMethod.POST)
		public  @ResponseBody Map<String,Object> openStaOrgInfo(@RequestBody OrgInfoVo infovo){
			Map<String,Object> map=new HashMap<String,Object>();
			boolean isSuccess=infoService.openStaOrgInfo(infovo);
			map.put("status", isSuccess);
			return map;
		}
		/**
		   * 关闭操作
		   * @param infovo
		   * @return
		   */
			@RequestMapping(value="/closeStaOrgInfo",method=RequestMethod.POST)
			public  @ResponseBody Map<String,Object> closeStaOrgInfo(@RequestBody OrgInfoVo infovo){
				Map<String,Object> map=new HashMap<String,Object>();
				boolean isSuccess=infoService.closeStaOrgInfo(infovo);
				map.put("status", isSuccess);
				return map;
			}
			//查询机构下是否存在用户信息
			@RequestMapping(value="/qryFindUsrInfo",method=RequestMethod.POST)
			public @ResponseBody Map<String, String> qryFindUsrInfo(@RequestBody OrgInfoVo orgInfovo){
				Map<String, String> map = new HashMap<String, String>();
				map.put("userNum", infoService.qryFindUsrInfo(orgInfovo));
				return map;
			}
}
