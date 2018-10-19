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
import com.indihx.datamng.service.IHpbService;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.org.entity.WY_JWH;
import com.indihx.org.service.ICommunityService;
import com.indihx.org.vo.CommunityVo;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 居委会信息管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月8日 下午 14:30:23</p>
 * <p></p>
 */
@Controller
@RequestMapping("/community")
public class CommunityController   extends AbstractBaseController{

	@Resource
	private ICommunityService communityservice;
	
	@Resource
	private IHpbService hpbService;
	
	/***
	 * 居委会管理首页查询
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/communityList",method=RequestMethod.GET)
	public ModelAndView hocList(){
		Map<String, Object> map = communityservice.communityList(new CommunityVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/community/communityList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 根据搜索条件查询
	 * @return 
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/ajaxcommunityList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxcommunityList(@RequestBody CommunityVo CommunityVo){
		Map<String, Object> map = communityservice.communityList(CommunityVo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
		
	}
	
	/***
	 * 打开新增居委会页面
	 * @return 返回新增页面VIEW
	 */
	@RequestMapping(value="/openAddCommunity",method=RequestMethod.GET)
	public ModelAndView openAddCommunity(){
		ModelAndView view = new ModelAndView();
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.setViewName("/org/community/addCommunity");
		return view; 
	}
 
	/**
     * 保存居委会信息
     * @param InfoVo
     * @return
	 * @throws BusinessException 
     */
	@RequestMapping(value="/saveCommunityInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveCommunityInfo(@RequestBody CommunityVo CommunityVo,HttpSession session) throws BusinessException{
		UsrInfo user = getUser(session);
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=communityservice.addCommunityInfo(user,CommunityVo);
		map.put("status", isSuccess);
		return map;
	}
	
	/**
	 * 打开编辑居委会页面
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/openUpdateCommunity",method=RequestMethod.GET)
	public ModelAndView openUpdateCommunity(@RequestParam("jwhid") String  jwhId){
		WY_JWH hoc = communityservice.qrCommunityInfoById(jwhId);
		ModelAndView view = new ModelAndView();
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.addObject("hoc", hoc); 
		view.setViewName("/org/community/editCommunity");
		return view;
	}
 
	
	/**
	 * 打开居委会详情页面
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/openGetCommunity",method=RequestMethod.GET)
	public ModelAndView openGetCommunity(@RequestParam("jwhid") String jwhid){
		WY_JWH jwh = communityservice.qrCommunityInfoById(jwhid);
		ModelAndView view = new ModelAndView();
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.addObject("jwh", jwh); 
		view.setViewName("/org/community/getCommunity");
		return view;
	}
	 
	/**
	 * 编辑保存居委会页面
	 * @param InfoVo
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/editCommunityInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> editCommunityInfo(@RequestBody CommunityVo CommunityVo) throws BusinessException{
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=communityservice.editCommunityInfo(CommunityVo);
		map.put("status", isSuccess);
		return map;
		
	}
	
	/**
	 * 删除居委会信息
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/delCommunityInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delCommunityInfo(@RequestBody CommunityVo CommunityVo){
		String jwhId = CommunityVo.getJwhid();
		String[] jwhIdList = jwhId.split(",");
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=communityservice.delCommunityInfo(jwhIdList);
		map.put("status", isSuccess);
		return map;
	}
	
}
