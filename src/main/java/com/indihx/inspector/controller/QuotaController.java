package com.indihx.inspector.controller;

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
import com.indihx.inspector.entity.Dc_Khzb;
import com.indihx.inspector.service.IQuotaService;
import com.indihx.inspector.vo.KhzbVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic;

/**
 * <p>标    题: 物业管理信息系统</p>
 * <p>描    述: 日常事务督察指标管理</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月11日 下午 09:05:15</p>
 * <p></p>
 */

@Controller
@RequestMapping("/quotamng")
public class QuotaController extends AbstractBaseController {
	
	@Resource
	IQuotaService quotaService;
	
	/**
	 * 查询指标列表首页
	 * @return
	 */
	@RequestMapping(value="/qryQuotaList",method=RequestMethod.GET)
	public ModelAndView qryQuotaList(){
		Map<String, Object> map = quotaService.queryKhzbList(new KhzbVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/inspector/quotamng/qryQuotaList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 根据搜索条件查询
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/ajaxqryQuotaList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> qryQuotaList(@RequestBody KhzbVo vo){
		Map<String, Object> map = quotaService.queryKhzbList(vo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	
	/**
	 * 查询指标列表首页
	 * @return
	 */
	@RequestMapping(value="/openAddQuota",method=RequestMethod.GET)
	public ModelAndView openAddQuota(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/inspector/quotamng/addQuota");
		Map<String, Object> map = quotaService.qryOneQuotaList(new KhzbVo());
		view.addObject("list", map.get("listInfo"));
		view.addObject("NORM_TYPE",ConstantStatic.createHtmlByCode("NORM_TYPE"));
		view.addObject("NORM_LEVEL",ConstantStatic.createHtmlByCode("NORM_LEVEL"));
		view.addObject("NORM_RECORD",ConstantStatic.createHtmlByCode("NORM_RECORD"));
		return view;
	}
	
	/**
	 * 保存新增指标信息
	 * @param khzbVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value="/saveQuotaInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveQuotaInfo(@RequestBody KhzbVo khzbVo,HttpSession session) throws BusinessException{
		UsrInfo usrInfo = getUser(session);
		boolean isSuccess=quotaService.insertOneKhzb(usrInfo,khzbVo);
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("status", isSuccess);
		return map;
	}
	
	
	/**
	 * 查询指标列表首页
	 * @return
	 */
	@RequestMapping(value="/openUpdateQuota",method=RequestMethod.GET)
	public ModelAndView openUpdateQuota(@RequestParam("check_seq") String check_seq){
		ModelAndView view = new ModelAndView();
		view.setViewName("/inspector/quotamng/editQuota");
		//查询指标详情
		Dc_Khzb  khzb = quotaService.getQuota(check_seq); 
		//查询所有一级指标
		Map<String, Object> map = quotaService.qryOneQuotaList(new KhzbVo());
		view.addObject("list", map.get("listInfo"));
		view.addObject("khzb", khzb);
		view.addObject("NORM_TYPE",ConstantStatic.createHtmlByCode("NORM_TYPE",khzb.getZblb()));
		view.addObject("NORM_LEVEL",ConstantStatic.createHtmlByCode("NORM_LEVEL",khzb.getZbcj()));
		view.addObject("NORM_RECORD",ConstantStatic.createHtmlByCode("NORM_RECORD",khzb.getJfbz()));
		return view;
	}
	
	
	/**
     * 编辑保存检查主题信息
     * @param cspSectVo
     * @return
	 * @throws BusinessException 
     */
	@RequestMapping(value="/savaEditQuota",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> savaEditQuota(@RequestBody KhzbVo vo,HttpSession session) throws BusinessException{
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=quotaService.updateQuota(vo);
		map.put("status", isSuccess);
		return map;
	}
	
	
	/**
	 * 删除检查指标信息
	 * @param cspSectVo
	 * @return
	 */
	@RequestMapping(value="/delQuota",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delQuota(@RequestBody  Map<String,Object> params){
		Map<String,Object> map=new HashMap<String,Object>();
		String check_seq=params.get("check_seq").toString();
		boolean isSuccess=quotaService.deleteOneKhzb(check_seq);
		map.put("status", isSuccess);
		return map;
	}
	
	
	/**
	 * 查询指标列表首页
	 * @return
	 */
	@RequestMapping(value="/openGetQuota",method=RequestMethod.GET)
	public ModelAndView openGetQuota(@RequestParam("check_seq") String check_seq){
		ModelAndView view = new ModelAndView();
		view.setViewName("/inspector/quotamng/getQuota");
		//查询指标详情
		Dc_Khzb  khzb = quotaService.getQuota(check_seq); 
		//查询所有一级指标
		Map<String, Object> map = quotaService.qryOneQuotaList(new KhzbVo());
		view.addObject("list", map.get("listInfo"));
		view.addObject("khzb", khzb);
		view.addObject("NORM_TYPE",ConstantStatic.createHtmlByCode("NORM_TYPE",khzb.getZblb()));
		view.addObject("NORM_LEVEL",ConstantStatic.createHtmlByCode("NORM_LEVEL",khzb.getZbcj()));
		view.addObject("NORM_RECORD",ConstantStatic.createHtmlByCode("NORM_RECORD",khzb.getJfbz()));
		return view;
	}
	
}
