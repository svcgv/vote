package com.indihx.project.controller;

import com.alibaba.fastjson.JSON;
import com.indihx.AbstractBaseController;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.impl.PmProjectInfoServiceImpl;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 项目管理
 */

@Controller
@RequestMapping("/project")
public class ProjectController extends AbstractBaseController {
    @Autowired
    private ParamsInfoServiceimpl infoservice;

    @Autowired
    private PmProjectInfoServiceImpl pmProjectInfoServiceImpl;


    @RequestMapping("/project/index")
    public ModelAndView addCustomView() {

        ModelAndView view = new ModelAndView();

        view.addObject("projectStatus", infoservice.qryInfoByCode("PROJECT_STATUS"));
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE"));

        view.setViewName("/project/project/index");
        return view;
    }

    @RequestMapping(value = "/project/form", method = RequestMethod.GET)
    public ModelAndView customFormView(@RequestParam("act") String act, @RequestParam("id") String id) {
        
    	ModelAndView view = new ModelAndView();
    	
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE"));
        view.addObject("isImportant", infoservice.qryInfoByCode("IS_IMPORTANT"));
   
        view.addObject("act", act);
        view.addObject("id", id);

        view.setViewName("/project/project/form");
        return view;
    }

    @RequestMapping(value = "/project/edit", method = RequestMethod.GET)
    public ModelAndView editFormView(@RequestParam("act") String act, @RequestParam("id") String id) {
        ModelAndView view = new ModelAndView();

        PmProjectInfoEntity entity = pmProjectInfoServiceImpl.queryObject(Long.parseLong(id));
        
        view.addObject("projectStatus", infoservice.qryInfoByCode("PROJECT_STATUS",entity.getProjectStatus()));
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE",entity.getProjectType()));
        view.addObject("isImportant", infoservice.qryInfoByCode("IS_IMPORTANT",entity.getIsImportant()));
        view.addObject("approveStatus", infoservice.qryInfoByCode("APPROVE_STATUS",entity.getApproveStatus()));
        view.addObject("act", act);
        view.addObject("id", id);
        view.addObject("formObj", JSON.toJSONString(entity));

        view.setViewName("/project/project/edit");
        return view;
    }

    @RequestMapping(value = "/project/view", method = RequestMethod.GET)
    public ModelAndView viewFormView(@RequestParam("act") String act, @RequestParam("id") String id) {
        ModelAndView view = new ModelAndView();

        view.addObject("isUseful", infoservice.qryInfoByCode("IS_USEFUL"));
        view.addObject("productType", infoservice.qryInfoByCode("PRODUCT_TYPE", "01"));
        PmProjectInfoEntity entity = pmProjectInfoServiceImpl.queryObject(Long.parseLong(id));

        view.addObject("formObj", JSON.toJSONString(entity));

        view.addObject("act", act);
        view.addObject("id", id);

        view.setViewName("/project/project/view");
        return view;
    }


    // 用户
    @RequestMapping(value = "/project/user", method = RequestMethod.GET)
    public ModelAndView userFormView(@RequestParam("act") String act) {
        ModelAndView view = new ModelAndView();
        view.addObject("act", act);
        view.setViewName("/project/project/user");
        return view;
    }

    // 客户
    @RequestMapping(value = "/project/customer", method = RequestMethod.GET)
    public ModelAndView customerFormView(@RequestParam("act") String act) {
        ModelAndView view = new ModelAndView();
        view.addObject("act", act);
        view.setViewName("/project/project/customer");
        return view;
    }

    // 立项审批
    @RequestMapping(value = "/project/review", method = RequestMethod.GET)
    public ModelAndView projectReviewFormView(@RequestParam("act") String act) {
        ModelAndView view = new ModelAndView();
        view.addObject("act", act);
        view.setViewName("/project/project/review");
        return view;
    }

    @RequestMapping(value = "/project/org", method = RequestMethod.GET)
    public ModelAndView orgFormView(@RequestParam("act") String act) {
        ModelAndView view = new ModelAndView();
        view.addObject("act", act);

        view.setViewName("/project/project/org");
        return view;
    }


    // 项目
    @RequestMapping(value = "/project/project", method = RequestMethod.GET)
    public ModelAndView reviewFormView(@RequestParam("act") String act) {
        ModelAndView view = new ModelAndView();
        view.addObject("act", act);
        view.setViewName("/project/project/project");
        return view;
    }

    // 客户
    @RequestMapping(value = "/project/setMoney", method = RequestMethod.GET)
    public ModelAndView setMoneyFormView(@RequestParam("act") String act) {
        ModelAndView view = new ModelAndView();
        view.addObject("act", act);
        view.setViewName("/project/project/setMoney");
        return view;
    }

    // 投标
    @RequestMapping(value = "/project/tender", method = RequestMethod.GET)
    public ModelAndView tenderFormView(@RequestParam("act") String act) {
        ModelAndView view = new ModelAndView();
        view.addObject("act", act);
        view.setViewName("/project/project/tender");
        return view;
    }

}
