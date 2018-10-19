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
import com.indihx.comm.util.ObjectUtil;
import com.indihx.org.entity.WY_GLC;
import com.indihx.org.entity.WY_WYGS;
import com.indihx.org.entity.WY_WYGS_STAFF;
import com.indihx.org.service.ICspSectService;
import com.indihx.org.service.ICspService;
import com.indihx.org.service.ICspStaffService;
import com.indihx.org.vo.CspInfoVo;
import com.indihx.org.vo.CspSectVo;
import com.indihx.org.vo.CspStaffVo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.ConstantStatic;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: CspSectController.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月5日 下午6:32:57</p>
 * <p>@author ChuJinze</p>
 * <p>@version 1.0</p>
 * <p>CspSectController.java</p>
 * <p></p>
 */

@Controller
@RequestMapping("/cspsect")
public class CspSectController extends AbstractBaseController {
	
	@Resource
	private ICspSectService cspSectService ;
	@Resource
	private ICspService cspservice;
	@Resource
	private ICspStaffService csservice;
	
	/**
	 * 物业管理处首页
	 * @return 返回首页jsp页面
	 */
	@RequestMapping(value="/csList",method=RequestMethod.GET)
	public ModelAndView getCsList(HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = cspSectService.getCsList(user,new CspSectVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/cspsect/getCsList");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 根据搜索条件查询
	 * @return 
	 * @return  返回首页jsp页面
	 */
	@RequestMapping(value="/ajaxcsList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQueryCsList(@RequestBody CspSectVo cspSectVo,HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = cspSectService.getCsList(user,cspSectVo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	/***
	 * 打开新增管理处页面
	 * @return 
	 * @return  返回新增页面VIEW
	 */
	@RequestMapping(value="/openAddCsp",method=RequestMethod.GET)
	public ModelAndView openAddhoc(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/cspsect/addCsInfo");
		return view; 
	}

	/**
     * 新增保存管理处信息
     * @param cspSectVo
     * @return
	 * @throws BusinessException 
     */
	@RequestMapping(value="/addCsInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addCsInfo(@RequestBody CspSectVo cspSectVo,HttpSession session) throws BusinessException{
		UsrInfo user = getUser(session);
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=cspSectService.addCsInfo(user,cspSectVo);
		map.put("status", isSuccess);
		return map;
	}

	/**
	 * 删除管理处信息
	 * @param cspSectVo
	 * @return
	 */
	@RequestMapping(value="/delCsInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delCspInfo(@RequestBody CspSectVo cspSectVo){
		String glcid = cspSectVo.getGlcid().toString();
		String[] glcidList = glcid.split(",");
		Map<String,Object> map=new HashMap<String,Object>();
		boolean isSuccess=cspSectService.delCsInfo(glcidList);
		map.put("status", isSuccess);
		return map;
	}
	
	/**
	 * 打开管理处详情页面
	 * @param glcid
	 * @return
	 */
	@RequestMapping(value="/openGetCs",method=RequestMethod.GET)
	public ModelAndView openGetCs(@RequestParam("glcid") String glcid){
		//查询小区管理处信息
		WY_GLC glc = cspSectService.qrCsInfoById(glcid);
		//查询小区管理归属的物业公司信息
		WY_WYGS wygs = cspservice.qrCspInfoById(glc.getWygsid().toString());
		//查询小区管理处在管的项目经理
		WY_WYGS_STAFF cs =new WY_WYGS_STAFF();
		String gsryid=glc.getGsryid().toString();
		if(!ObjectUtil.isEmpty(gsryid)) {
			cs=csservice.qrStaffInfoById(gsryid);
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/cspsect/getCsInfo");
		view.addObject("glc", glc); 
		view.addObject("wygs", wygs); 
		view.addObject("cs", cs); 
		view.addObject("ORG_STATUS",ConstantStatic.createHtmlByCode("ORG_STATUS",glc.getStatus()));
		return view;
	}
	
	/**
	 * 编辑管理处详情页面
	 * @param glcid
	 * @return
	 */
	@RequestMapping(value="/openUpdateCs",method=RequestMethod.GET)
	public ModelAndView openUpdateCs(@RequestParam("glcid") String glcid){
		//查询小区管理处信息
		WY_GLC glc = cspSectService.qrCsInfoById(glcid);
		//查询小区管理归属的物业公司信息
		WY_WYGS wygs = cspservice.qrCspInfoById(glc.getWygsid().toString());
		//查询小区管理处在管的项目经理
		WY_WYGS_STAFF cs =csservice.qrStaffInfoById(glc.getGsryid().toString());
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/cspsect/editCsInfo");
		view.addObject("glc", glc); 
		view.addObject("wygs", wygs); 
		view.addObject("cs", cs); 
		view.addObject("ORG_STATUS",ConstantStatic.createHtmlByCode("ORG_STATUS",glc.getStatus()));
		return view;
	}

	/**
     * 编辑保存管理处信息
     * @param cspSectVo
     * @return
	 * @throws BusinessException 
     */
	@RequestMapping(value="/saveCsInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveCsInfo(@RequestBody CspSectVo cspSectVo,HttpSession session) throws BusinessException{
		Map<String,Object> map= new HashMap<String, Object>();
		boolean isSuccess=cspSectService.editCsInfo(cspSectVo);
		map.put("status", isSuccess);
		return map;
	}
	
	/**
	 * 物业公司列表首页
	 * @return
	 */
	@RequestMapping(value="/wygsList",method=RequestMethod.GET)
	public ModelAndView getWygsList(HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = cspservice.cspList(user,new CspInfoVo());
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/cspsect/selectWygs");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 根据搜索条件查询物业公司
	 * @return 
	 * @return  返回选择物业公司页面
	 */
	@RequestMapping(value="/ajaxWygsList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxQueryWygsList(@RequestBody CspInfoVo cspVo,HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = cspservice.cspList(user,cspVo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	
	/**
	 * 项目经理列表首页
	 * @return
	 */ 
	@RequestMapping(value="/cspPersonList",method=RequestMethod.GET)
	public ModelAndView cspPersonList(@RequestParam("wygsid") String wygsid,HttpSession session){
		UsrInfo user = getUser(session);
		CspStaffVo vo=new CspStaffVo();
		vo.setWygsid(new Long(wygsid));
		Map<String, Object> map = csservice.getStaffList(vo,user);
		ModelAndView view = new ModelAndView();
		view.setViewName("/org/cspsect/selectPerson");
		view.addObject("pageInfo", map.get("pageInfo"));
		view.addObject("listInfo", map.get("listInfo"));
		return view;
	}
	
	/***
	 * 根据搜索条件查询物业公司
	 * @return 
	 * @return  返回选择物业公司页面
	 */
	@RequestMapping(value="/ajaxcspPersonList",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> ajaxcspPersonList(@RequestBody CspInfoVo cspVo,HttpSession session){
		UsrInfo user = getUser(session);
		Map<String, Object> map = cspservice.cspList(user,cspVo); 
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("pageInfo", map.get("pageInfo"));
		respMap.put("listInfo", map.get("listInfo"));
		return respMap;
	}
	
	


}
