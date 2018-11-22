package com.indihx.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.service.impl.ProfitInfoServiceImpl;
import com.indihx.system.entity.ProfitInfo;

@Controller
@RequestMapping("/project")
public class ProfitInfoController extends AbstractBaseController{

	
	@Autowired
	private ProfitInfoServiceImpl serviceImpl;
	
	@RequestMapping("/profit/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
		//view.addObject("isUseful",costInfoService.qryInfoByCode("IS_USEFUL","00"));
		
		view.setViewName("/project/profit/index");
		return view;
	}
	
	//新增表单
		@RequestMapping(value="/profit/form",method=RequestMethod.GET)
		public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
			ModelAndView view = new ModelAndView();
			
			//view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
			
			view.addObject("act",act);
			if(id !=null && !"".equals(id)) {
				view.addObject("id",id);
			}
			
			view.setViewName("/project/profit/form");
			return view;
		}
		
		
		//修改表单
		@RequestMapping(value="/profit/edit",method=RequestMethod.GET)
		public ModelAndView editFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
			ModelAndView view = new ModelAndView();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("groupCode", id);
			
			ProfitInfo info = serviceImpl.queryProfitInfoByProfitId(id);
			
			
			
			view.addObject("profit",info);
			
			view.addObject("act",act);
			view.addObject("id",id);
			
			view.setViewName("/project/profit/edit");
			return view;
		}
		
		
		//查看表单
		@RequestMapping(value="/profit/view",method=RequestMethod.GET)
		public ModelAndView viewFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
			ModelAndView view = new ModelAndView();
			ProfitInfo info = serviceImpl.queryProfitInfoByProfitId(id);
			
			view.addObject("profit",info);
			
			
			view.addObject("act",act);
			view.addObject("id",id);
			
			view.setViewName("/project/profit/view");
			return view;
		}

		
		// 机构
		@RequestMapping(value="/profit/org",method=RequestMethod.GET)
		public ModelAndView orgFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			
			view.addObject("act", act);
			
			view.setViewName("/project/profit/org");
			return view;
		}
}
