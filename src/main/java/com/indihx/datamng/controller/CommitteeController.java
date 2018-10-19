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
import com.indihx.datamng.entity.Wy_Sq;
import com.indihx.datamng.service.impl.CommitteeServiceImpl;
import com.indihx.datamng.vo.CommitteeVo;
/***
 * 
 * <p>描 述: 社区信息管理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年12月13日</p>
 * @author 
 */
@Controller
@RequestMapping("/cmt")
public class CommitteeController extends AbstractBaseController {
	
	@Resource
	private CommitteeServiceImpl committeeImpl;
	/***
	 * 行政区域管理首页查询
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/qryCmt",method=RequestMethod.GET)
	public ModelAndView qryCommitteeList(){
		Map<String, Object> map = committeeImpl.qryCommitteeList(new CommitteeVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/committee/qryCommitteeInfo");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	/***
	 * 首页通过查询条件查询数据信息
	 * @param regionVo  行政区域实体vo
	 * @return
	 */
	@RequestMapping(value="/ajaxQryCommitteeInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQryCommitteeInfo(@RequestBody CommitteeVo aqCommitteeVo){
		Map<String, Object> map = committeeImpl.qryCommitteeList(aqCommitteeVo);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("listInfo", map.get("listInfo"));
		respMap.put("pageInfo", map.get("pageInfo"));
		return respMap;
	}
	
	/***
	 * 打开新增社区页面
	 * @return 返回新增页面VIEW
	 */
	@RequestMapping(value="/openAddCommittee",method=RequestMethod.GET)
	public ModelAndView openAddCommittee(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/committee/addCommittee");
		Map<String, Object> map = committeeImpl.qryStreetList(new CommitteeVo());
		view.addObject("list", map.get("listInfo"));
		return view;
	}
	/**
     * 保存
     * @param InfoVo
     * @return
     */
	@RequestMapping(value="/addCommitteeInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addCommitteeInfo(@RequestBody CommitteeVo InfoVo){
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=committeeImpl.addCommitteeInfo(InfoVo);
		map.put("status", isSuccess);
		return map;
	}
	
	/**
	 * 编辑页面
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/openUpdateCommittee",method=RequestMethod.GET)
	public ModelAndView openUpdateCommittee(@RequestParam("sqid") String sqid){
		Wy_Sq info = committeeImpl.qryCommitteeInfoById(sqid);
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/committee/editCommittee");
		Map<String, Object> map = committeeImpl.qryStreetList(new CommitteeVo());
		view.addObject("list", map.get("listInfo"));
		view.addObject("info", info);
		return view;
	}
	
	/**
	 * 编辑保存
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/editCommitteeInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> editCommitteeInfo(@RequestBody CommitteeVo InfoVo){
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=committeeImpl.editCommitteeInfo(InfoVo);
		map.put("status", isSuccess);
		return map;
		
	}
	
	/**
	 * 删除
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/delCommitteeInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delCommitteeInfo(@RequestBody CommitteeVo InfoVo){
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=committeeImpl.deleteCommitteeInfo(InfoVo);
		map.put("status", isSuccess);
		return map;
	}
	
	/**
	 * 查看社区信息
	 * @return
	 */
	@RequestMapping(value="/getCommittee",method=RequestMethod.GET)
	public ModelAndView getHpbInfo(@RequestParam("sqid") String sqid){
		Wy_Sq info = committeeImpl.qryCommitteeInfoById(sqid);
		ModelAndView view = new ModelAndView();
		view.setViewName("/datamng/committee/getCommittee");
		Map<String, Object> map = committeeImpl.qryStreetList(new CommitteeVo());
		view.addObject("list", map.get("listInfo"));
		view.addObject("info", info);
		return view;	
	}
	

	
}
