package com.indihx.datamng.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.datamng.service.IHpbService;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.util.ConstantStatic;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: HpbController.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月29日 上午9:58:16</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>HpbController.java</p>
 * <p></p>
 */
@Controller
@RequestMapping("/hpb")
public class HpbController extends AbstractBaseController {
	
	@Resource
	private IHpbService hpbService;

	/**
	 * 菜单进入首页查询区县列表
	 * @return
	 */
	@RequestMapping(value="/getHpbList",method=RequestMethod.GET)
	public ModelAndView getHpbList(){
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/hpb/getHpbList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * Ajax首页通过查询条件查询数据信息
	 * @param streetVo  
	 * @return
	 */
	@RequestMapping(value="/ajaxgetHpbList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQryStreetInfo(@RequestBody HpbInfoVo hpbInfoVo){
		Map<String, Object> map = hpbService.getHpbList(hpbInfoVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/**
	 * 新增区县页面
	 * @return
	 */
	@RequestMapping(value="/addHpbView",method=RequestMethod.GET)
	public ModelAndView addHpbView(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/hpb/addHpbView");
		view.addObject("regionType",ConstantStatic.createHtmlByCode("REGION_TYPE"));
		return view;
	}
	
	/***
	 * 区县信息保存
	 * @param StreetInfo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/saveHpbInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveHpbInfo(@RequestBody HpbInfoVo hpbVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = hpbService.saveHpbInfo(hpbVo);
		map.put("status", isSuccess);
		return map;
	}
	

	/**
	 * 查看区县信息
	 * @return
	 */
	@RequestMapping(value="/getHpbInfo",method=RequestMethod.GET)
	public ModelAndView getHpbInfo(@RequestParam("hpbid") String hpbid){
		ModelAndView view = new ModelAndView();
		Wy_Hpb hpb = hpbService.getHpbInfo(hpbid);
		view.setViewName("/datamng/hpb/getHpbInfo");
		view.addObject("hpb", hpb);
		return view;
	}
	
	/**
	 * 查看区县信息
	 * @return
	 */
	@RequestMapping(value="/editHpbView",method=RequestMethod.GET)
	public ModelAndView editHpbView(@RequestParam("hpbid") String hpbid){
		ModelAndView view = new ModelAndView();
		Wy_Hpb hpb = hpbService.getHpbInfo(hpbid);
		view.setViewName("/datamng/hpb/editHpbView");
		view.addObject("hpb", hpb);
		view.addObject("hpbid",hpbid);
		view.addObject("regionType",ConstantStatic.createHtmlByCode("REGION_TYPE",hpb.getHpblx()));
		return view;
	}

	/***
	 * 区县信息修改保存
	 * @param StreetInfo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/saveModifyHpbInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveModifyHpbInfo(@RequestBody HpbInfoVo hpbVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = hpbService.saveModifyHpbInfo(hpbVo);
		map.put("status", isSuccess);
		return map;
	}
	
	/***
	 * 区县信息删除
	 * @param StreetInfo
	 * @return 返回成功或失败页
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteHpbInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteHpbInfo(@RequestBody HpbInfoVo hpbVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = hpbService.deleteHpbInfo(hpbVo.getHpbid());
		map.put("status", isSuccess);
		return map;
	}

}
