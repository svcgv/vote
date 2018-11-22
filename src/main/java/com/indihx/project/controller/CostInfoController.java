package com.indihx.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.service.ICostInfoService;

@Controller
@RequestMapping("/project")
public class CostInfoController  extends AbstractBaseController{

	@Autowired
	private ICostInfoService costService;
	
	
	@RequestMapping("/cost/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
		//view.addObject("isUseful",costInfoService.qryInfoByCode("IS_USEFUL","00"));
		
		view.setViewName("/project/cost/index");
		return view;
	}
	
	
	@RequestMapping(value="/cost/form",method=RequestMethod.GET)
	public ModelAndView costFormView(@RequestParam("act") String act,@RequestParam("id") Long id) {
		ModelAndView view = new ModelAndView();
		
//		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		//view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","00"));
		
		view.addObject("act",act);
		if(id !=null) {
			view.addObject("id",id);
		}

		view.setViewName("/project/cost/form");
		return view;
	}
	
	
	@RequestMapping(value="/cost/edit",method=RequestMethod.GET)
	public ModelAndView costFormEdit(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
//		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		//view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","00"));
		
		view.addObject("Cost",costService.quetyCostInfoByCostId(id));
		
		view.addObject("act",act);
		if(id !=null) {
			view.addObject("id",id);
		}

		view.setViewName("/project/cost/edit");
		return view;
	}
	
	@RequestMapping(value="/cost/view",method=RequestMethod.GET)
	public ModelAndView costFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
//		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		//view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","00"));
		
		view.addObject("Cost",costService.quetyCostInfoByCostId(id));
		
		view.addObject("act",act);
		if(id !=null) {
			view.addObject("id",id);
		}

		view.setViewName("/project/cost/view");
		return view;
	}
	
	
	@RequestMapping(value="/cost/org",method=RequestMethod.GET)
	public ModelAndView orgFormView(@RequestParam("act") String act) {
		ModelAndView view = new ModelAndView();
		view.addObject("act",act);
		view.setViewName("/project/cost/org");
		return view;
	}
	
	
}
