package com.indihx.tender.controller;

import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.comm.exception.BusinessException;
import com.indihx.datamng.service.IHpbService;
import com.indihx.datamng.vo.HpbInfoVo;
import com.indihx.tender.entity.ZTB_EXPERT;
import com.indihx.tender.service.IBidExpertService;
import com.indihx.tender.vo.BidExpertVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: BidExpertController.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月5日 下午6:32:57</p>
 * <p>@author ychonghui</p>
 * <p>@version 1.0</p>
 * <p>BidExpertController.java</p>
 * <p></p>
 */

@Controller
@RequestMapping("/expert")
public class BidExpertController extends AbstractBaseController {
	
	@Resource
	private IBidExpertService bidExpertService;
	@Resource
	private IHpbService hpbService;
	
	
	/**
	 * 首页
	 * @return view首页
	 */
	@RequestMapping(value="/exList",method=RequestMethod.GET)
	public ModelAndView getExList(HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = bidExpertService.getExList(user,new BidExpertVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/tender/expert/getExList");
		/*view.addObject("EXPERT_TYPE",ConstantStatic.createHtmlByCode("EXPERT_TYPE"));*/
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 查询
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/ajaxExList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQueryCsList(@RequestBody BidExpertVo bidExpertVo,HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = bidExpertService.getExList(user,bidExpertVo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/***
	 * 新增页面
	 * @return view新增
	 */
	@RequestMapping(value="/addExpert",method=RequestMethod.GET)
	public ModelAndView AddExInfoView(){
		ModelAndView view = new ModelAndView();
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.addObject("SEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE"));
		view.addObject("CERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE"));
		view.addObject("EXPERT_TYPE",ConstantStatic.createHtmlByCode("EXPERT_TYPE"));
		view.setViewName("/tender/expert/addExInfo");
		return view; 
	}

	/**
     * 新增保存
     * @param bidExpertVo
     * @return map
     */
	@RequestMapping(value="/addExInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addExInfo(@RequestBody BidExpertVo bidExpertVo,HttpSession session) throws BusinessException{
		UsrInfo user = getUser(session);
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess = bidExpertService.addExInfo(user,bidExpertVo);
		map.put("status", isSuccess);
		return map;
	}
	
	/***
	 * 导入页面
	 * @return view新增
	 */
	@RequestMapping(value="/impExpert",method=RequestMethod.GET)
	public ModelAndView impExInfoView(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/tender/expert/impExpert");
		return view; 
	}
	
	 /**
	 * 编辑页面
	 * @param expert_id
	 * @return view编辑
	 */
	 @RequestMapping(value="/editExpert",method=RequestMethod.GET)
	 public ModelAndView editExInfoView(@RequestParam("expert_id") String expert_id){
		//查询专家详细信息
		ZTB_EXPERT expert = bidExpertService.getExInfo(expert_id);
		
		ModelAndView view = new ModelAndView();
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.setViewName("/tender/expert/editExInfo");
		view.addObject("SEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE",expert.getExpert_six().toString()));
		view.addObject("CERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE",expert.getCert_type().toString()));
		view.addObject("EXPERT_TYPE",ConstantStatic.createHtmlByCode("EXPERT_TYPE",expert.getExpert_type().toString()));
		view.addObject("expert",expert); 
		return view;
	 }
	
	 /**
	  * 编辑保存
	  * 
	  */
	 @RequestMapping(value="/editExInfo",method=RequestMethod.POST)
	 public @ResponseBody Map<String,Object> editExInfo(@RequestBody BidExpertVo bidExpertVo,HttpSession session) throws BusinessException{
		 Map<String,Object> map = new HashMap<String, Object>();
		 boolean isSuccess = bidExpertService.editExInfo(bidExpertVo);
		 map.put("status", isSuccess);
		 return map;
	 }
	 
	/**
	 * 删除
	 * 
	 * @return map
	 */
	 @RequestMapping(value="/delExInfo",method=RequestMethod.POST)
	 public @ResponseBody Map<String,Object> delExInfo(@RequestBody BidExpertVo bidExpertVo){
		String expert_id = bidExpertVo.getExpert_id().toString();
		String[] expert_id_list = expert_id.split(",");
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=bidExpertService.delExInfo(expert_id_list);
		map.put("status", isSuccess);
		return map;
	 }
	 
	 /**
	 * 详情页面
	 * @param expert_id
	 * @return
	 */
	 @RequestMapping(value="/qryExInfo",method=RequestMethod.GET)
	 public ModelAndView ExInfoView(@RequestParam("expert_id") String expert_id){
		//查询专家详细信息
		ZTB_EXPERT expert = bidExpertService.getExInfo(expert_id);
		
		ModelAndView view = new ModelAndView();
		Map<String, Object> map = hpbService.getHpbList(new HpbInfoVo());
		view.addObject("hpbList", map.get("listInfo"));
		view.setViewName("/tender/expert/getExInfo");
		view.addObject("SEXTYPE",ConstantStatic.createHtmlByCode("SEXTYPE",expert.getExpert_six().toString()));
		view.addObject("CERTTYPE",ConstantStatic.createHtmlByCode("CERTTYPE",expert.getCert_type().toString()));
		view.addObject("EXPERT_TYPE",ConstantStatic.createHtmlByCode("EXPERT_TYPE",expert.getExpert_type().toString()));
		view.addObject("expert",expert); 
		return view;
	 }
	 
	 @RequestMapping(value = "/loadExcelData.do", method = RequestMethod.POST)
	 public @ResponseBody Map<String, Object> uploadMulti(@RequestParam("file") MultipartFile[] myfiles,
				HttpServletRequest request, @RequestParam("fileTypeId") String fileTypeId,HttpSession session) { 
		 UsrInfo usrInfo = getUser(session);
		 Map<String, Object> map = bidExpertService.loadDataInfo(usrInfo,myfiles,fileTypeId);
		 return map;
	 }
}
