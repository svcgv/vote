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
import com.indihx.org.entity.WY_HOC_STAFF;
import com.indihx.org.entity.WY_YWH;
import com.indihx.org.service.IHocService;
import com.indihx.org.vo.HocVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic;

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 业委会信息管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月1日 下午 14:30:23</p>
 * <p></p>
 */
@Controller
@RequestMapping("/hoc")
public class HocController extends AbstractBaseController {

	
	@Resource
	private IHocService hocservice;
	@Resource
	private IHpbService hpbService;
	
	/***
	 * 业委会管理首页查询
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/hocList",method=RequestMethod.GET)
	public ModelAndView hocList(HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = hocservice.hocList(user,new HocVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/hocmng/hocList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 根据搜索条件查询
	 * @return 
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/ajaxhocList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQueryhocList(@RequestBody HocVo hocVo,HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = hocservice.hocList(user,hocVo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/***
	 * 打开新增业委会页面
	 * @return 返回新增页面VIEW
	 */
	@RequestMapping(value="/openAddHoc",method=RequestMethod.GET)
	public ModelAndView openAddhoc(){
		ModelAndView view = new ModelAndView();
		//查询区信息
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.setViewName("/org/hocmng/addHoc");
		view.addObject("IS_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG"));
		view.addObject("CERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE"));
		view.addObject("SEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE"));
		view.addObject("EDUCATION",ConstantStatic.createHtmlByCode("EDUCATION"));
		return view; 
	}
 
	/**
     * 保存业委会信息
     * @param InfoVo
     * @return
	 * @throws BusinessException 
     */
	@RequestMapping(value="/saveHocInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addHocInfo(@RequestBody HocVo hocvo,HttpSession session) throws BusinessException{
		Map<String,Object> map= new HashMap<String, Object>();
		UsrInfo user = getUser(session);
		boolean isSuccess=hocservice.addHocInfo(user,hocvo);
		map.put("status", isSuccess);
		return map;
	}
	
	/**
	 * 打开编辑业委会页面
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/openUpdateHoc",method=RequestMethod.GET)
	public ModelAndView openUpdateHoc(@RequestParam("ywhid") String ywhid){
		ModelAndView view = new ModelAndView();
		//查询业委会信息
		WY_YWH hoc = hocservice.qrHocInfoById(ywhid);
		//查询主任信息
		WY_HOC_STAFF zr=hocservice.qrHocStaffInfoById(new Long(ywhid),new Long(1));
		//查询预留印玺副主任信息
		WY_HOC_STAFF yl=hocservice.qrHocStaffInfoById(new Long(ywhid), new Long(2));
		//查询副主任
		WY_HOC_STAFF fzr=hocservice.qrHocStaffInfoById(new Long(ywhid), new Long(3));
		//查询区信息
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.setViewName("/org/hocmng/editHoc");
		view.addObject("hoc", hoc); 
		view.addObject("zr", zr); 
		view.addObject("yl", yl); 
		view.addObject("fzr", fzr); 
		view.addObject("IS_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",hoc.getSfkz()));//是否刻章
		view.addObject("ZRCERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE",zr.getZjlx()));//证件类型-主任
		view.addObject("ZRSEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE",zr.getRyxb()));//人员性别-主任
		view.addObject("ZREDUCATION",ConstantStatic.createHtmlByCode("EDUCATION",zr.getWhcd()));//文化程度-主任
		view.addObject("YLCERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE",yl.getZjlx()));//证件类型-预留副主任
		view.addObject("YLSEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE",yl.getRyxb()));//人员性别-预留副主任
		view.addObject("YLEDUCATION",ConstantStatic.createHtmlByCode("EDUCATION",yl.getWhcd()));//文化程度-预留副主任
		view.addObject("FZRCERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE",fzr.getZjlx()));//证件类型-副主任
		view.addObject("FZRSEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE",fzr.getRyxb()));//人员性别-副主任
		view.addObject("FZREDUCATION",ConstantStatic.createHtmlByCode("EDUCATION",fzr.getWhcd()));//文化程度-副主任
		return view;
	}
 
	
	/**
	 * 打开业委会详情页面
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/openGetHoc",method=RequestMethod.GET)
	public ModelAndView openGethoc(@RequestParam("ywhid") String ywhid){
		ModelAndView view = new ModelAndView();
		//查询业委会信息
		WY_YWH hoc = hocservice.qrHocInfoById(ywhid);
		//查询主任信息
		WY_HOC_STAFF zr=hocservice.qrHocStaffInfoById(new Long(ywhid),new Long(1));
		//查询预留印玺副主任信息
		WY_HOC_STAFF yl=hocservice.qrHocStaffInfoById(new Long(ywhid), new Long(2));
		//查询副主任
		WY_HOC_STAFF fzr=hocservice.qrHocStaffInfoById(new Long(ywhid), new Long(3));
		//查询区信息
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.setViewName("/org/hocmng/getHoc");
		view.addObject("hoc", hoc); 
		view.addObject("zr", zr); 
		view.addObject("yl", yl); 
		view.addObject("fzr", fzr); 
		view.addObject("IS_FLAG",ConstantStatic.createHtmlByCode("IS_FLAG",hoc.getSfkz()));//是否刻章
		view.addObject("ZRCERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE",zr.getZjlx()));//证件类型-主任
		view.addObject("ZRSEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE",zr.getRyxb()));//人员性别-主任
		view.addObject("ZREDUCATION",ConstantStatic.createHtmlByCode("EDUCATION",zr.getWhcd()));//文化程度-主任
		view.addObject("YLCERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE",yl.getZjlx()));//证件类型-预留副主任
		view.addObject("YLSEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE",yl.getRyxb()));//人员性别-预留副主任
		view.addObject("YLEDUCATION",ConstantStatic.createHtmlByCode("EDUCATION",yl.getWhcd()));//文化程度-预留副主任
		view.addObject("FZRCERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE",fzr.getZjlx()));//证件类型-副主任
		view.addObject("FZRSEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE",fzr.getRyxb()));//人员性别-副主任
		view.addObject("FZREDUCATION",ConstantStatic.createHtmlByCode("EDUCATION",fzr.getWhcd()));//文化程度-副主任
		return view;
	}
	 
	/**
	 * 编辑保存业委会页面
	 * @param InfoVo
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/editHocInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> edithocInfo(@RequestBody HocVo hocVo) throws BusinessException{
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=hocservice.editHocInfo(hocVo);
		map.put("status", isSuccess);
		return map;
		
	}
	
	/**
	 * 删除业委会信息
	 * @param InfoVo
	 * @return
	 */
	@RequestMapping(value="/delHocInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delHocInfo(@RequestBody HocVo hocVo){
		String ywhId = hocVo.getYwhid();
		String[] ywhIdList = ywhId.split(",");
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=hocservice.delHocInfo(ywhIdList);
		map.put("status", isSuccess);
		return map;
	}
	
	
}
