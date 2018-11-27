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

import com.alibaba.fastjson.JSON;
import com.indihx.AbstractBaseController;
import com.indihx.PmConfirmBid.entity.PmConfirmBidEntity;
import com.indihx.PmConfirmBid.service.PmConfirmBidService;
import com.indihx.PmFile.entity.PmFileEntity;
import com.indihx.PmFile.service.PmFileService;
import com.indihx.PmReviewInfo.entity.PmReviewInfoEntity;
import com.indihx.PmReviewInfo.service.PmReviewInfoService;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;

@Controller
@RequestMapping("/project")
public class TenderExamineController extends AbstractBaseController{

	@Autowired
	private ParamsInfoServiceimpl infoservice;
	@Autowired
    private PmConfirmBidService pmConfirmBidService;
	
	@Autowired
    private PmReviewInfoService pmReviewInfoService;
	
	@Autowired
    private PmFileService pmFileService;

	
	
	@RequestMapping("/tenderExamine/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("/project/tenderExamine/index");
		return view;
	}
	
	@RequestMapping(value="/tenderExamine/view",method=RequestMethod.GET)
	public ModelAndView viewFormView(@RequestParam("act") String act,@RequestParam("id") long id) {
		ModelAndView view = new ModelAndView();
		
		PmConfirmBidEntity entity = pmConfirmBidService.queryObject(id);
		view.addObject("pmConfirmBid",JSON.toJSONString(entity));
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE",entity.getProjectType()));
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put("reviewType", "00");
		map.put("isDelete", "01");
		map.put("foreignId", id);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("foreignId", id);
		param.put("isDelete", "00");
		param.put("uploadType", "00");
		List<PmFileEntity> fileList = pmFileService.queryList(param);
		
		map.put("foreignId", id);
		map.put("isDelete", "00");
		
		List<PmReviewInfoEntity> list = pmReviewInfoService.queryList(map);
		map.put("isDelete", "01");
		
		if(list.isEmpty()) {
			view.addObject("reviewId",0);
		}
		else {
			view.addObject("reviewId",list.get(0).getReviewId());
		}
		
		List<PmReviewInfoEntity> list2 = pmReviewInfoService.queryList(map);
		
		view.addObject("reviewHis",JSON.toJSONString(list2));
		view.addObject("act",act);
		view.addObject("id",id);
		view.addObject("fileList",fileList);
		
		view.setViewName("/project/tenderExamine/view");
		return view;
	}
	
}
