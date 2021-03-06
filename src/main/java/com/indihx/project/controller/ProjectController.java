package com.indihx.project.controller;

import com.alibaba.fastjson.JSON;
import com.indihx.AbstractBaseController;
import com.indihx.PmFile.service.PmFileService;
import com.indihx.PmProjectGroupInfo.entity.PmProjectGroupInfoEntity;
import com.indihx.PmProjectGroupInfo.service.PmProjectGroupInfoService;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.impl.PmProjectInfoServiceImpl;
import com.indihx.PmProjectMilestoneInfo.service.PmProjectMilestoneInfoService;
import com.indihx.comm.util.BasicParameterInfo;
import com.indihx.system.entity.CodeData;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;
import com.indihx.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Autowired
    private PmProjectGroupInfoService pmProjectGroupInfoService;
    
	@Autowired
	PmProjectMilestoneInfoService pmProjectMilestoneInfoService;
	
	@Autowired
	private PmFileService pmFileService;


    @RequestMapping("/project/index")
    public ModelAndView addCustomView() {

        ModelAndView view = new ModelAndView();

        view.addObject("projectStatus", infoservice.qryInfoByCode("PROJECT_STATUS","00"));
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE","00"));

        view.setViewName("/project/project/index");
        return view;
    }

    @RequestMapping(value = "/project/form", method = RequestMethod.GET)
    public ModelAndView customFormView(@RequestParam("act") String act, @RequestParam("id") String id) {
        
    	ModelAndView view = new ModelAndView();
    	
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE","00"));
        view.addObject("isImportant", infoservice.qryInfoByCode("IS_IMPORTANT","00"));
        view.addObject("projectTypeSelected","00");
        
    	Map<String,Object> maps = new HashMap<String, Object>();
		maps.put("isDelete", "00");
		List<PmProjectGroupInfoEntity> pmProjectGroupInfo = pmProjectGroupInfoService.queryList(maps);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<option value='' select>--请选择--</option>");
		for (PmProjectGroupInfoEntity entity : pmProjectGroupInfo) {
			buffer.append("<option value='"+entity.getProjectGroupId()+"'>"+entity.getProjectGroupName()+"</option>");
		}
		Map<String, Object> code = new HashMap<String, Object>();
		code.put("ewTypeHtml", buffer.toString());
		
		view.addObject("projectGroup", code);
   
        view.addObject("act", act);
        view.addObject("id", id);

        view.setViewName("/project/project/form");
        return view;
    }

    @RequestMapping(value = "/project/edit", method = RequestMethod.GET)
    public ModelAndView editFormView(@RequestParam("act") String act, @RequestParam("id") String id) {
        ModelAndView view = new ModelAndView();
			
        PmProjectInfoEntity entity = pmProjectInfoServiceImpl.queryObject(Long.parseLong(id));
        pmProjectMilestoneInfoService.queryListInfo(entity, entity.getProjectId());
        view.addObject("projectStatus", infoservice.qryInfoByCode("PROJECT_STATUS",entity.getProjectStatus()));
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE",entity.getProjectType()));
        view.addObject("isImportant", infoservice.qryInfoByCode("IS_IMPORTANT",entity.getIsImportant()));
        view.addObject("approveStatus", infoservice.qryInfoByCode("APPROVE_STATUS",entity.getApproveStatus()));
        view.addObject("projectTypeSelected",entity.getProjectType());
        Map<String,Object> maps = new HashMap<String, Object>();
		maps.put("isDelete", "00");
		List<PmProjectGroupInfoEntity> pmProjectGroupInfo = pmProjectGroupInfoService.queryList(maps);
        
        
        StringBuffer buffer = new StringBuffer();
		for (PmProjectGroupInfoEntity entity1: pmProjectGroupInfo) {
			buffer.append("<option value='"+entity1.getProjectGroupId()+"' ");
			if(entity1.getProjectGroupId()==entity.getBelongProjectGroupId())
			{
				buffer.append(" selected='selected' ");
			}
			buffer.append(" >"+entity1.getProjectGroupName()+"</option> ");
		}
		Map<String, Object> code = new HashMap<String, Object>();
		code.put("ewTypeHtml", buffer.toString());
		
		view.addObject("projectGroup", code);
        
        view.addObject("act", act);
        view.addObject("id", id);
        view.addObject("formObj", JSON.toJSONString(entity));
        
    	Map<String,Object> filemaps = new HashMap<String,Object>();
    	filemaps.put("foreignId", id);
    	filemaps.put("isDelete", "00");
		view.addObject("file",pmFileService.queryList(filemaps));

        view.setViewName("/project/project/edit");
        return view;
    }

    @RequestMapping(value = "/project/view", method = RequestMethod.GET)
    public ModelAndView viewFormView(@RequestParam("act") String act, @RequestParam("id") String id) {
        ModelAndView view = new ModelAndView();

        PmProjectInfoEntity entity = pmProjectInfoServiceImpl.queryObject(Long.parseLong(id));
        pmProjectMilestoneInfoService.queryListInfo(entity, entity.getProjectId());
        view.addObject("projectStatus", infoservice.qryInfoByCode("PROJECT_STATUS",entity.getProjectStatus()));
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE",entity.getProjectType()));
        view.addObject("isImportant", infoservice.qryInfoByCode("IS_IMPORTANT",entity.getIsImportant()));
        view.addObject("approveStatus", infoservice.qryInfoByCode("APPROVE_STATUS",entity.getApproveStatus()));
        view.addObject("projectTypeSelected",entity.getProjectType());
        
        Map<String,Object> maps = new HashMap<String, Object>();
		maps.put("isDelete", "00");
		List<PmProjectGroupInfoEntity> pmProjectGroupInfo = pmProjectGroupInfoService.queryList(maps);
        
        
        StringBuffer buffer = new StringBuffer();
		for (PmProjectGroupInfoEntity entity1: pmProjectGroupInfo) {
			buffer.append("<option value='"+entity1.getProjectGroupId()+"' ");
			if(entity1.getProjectGroupId()==entity.getBelongProjectGroupId())
			{
				buffer.append(" selected='selected' ");
			}
			buffer.append(" >"+entity1.getProjectGroupName()+"</option> ");
		}
		Map<String, Object> code = new HashMap<String, Object>();
		code.put("ewTypeHtml", buffer.toString());
		
		view.addObject("projectGroup", code);

        view.addObject("act", act);
        view.addObject("id", id);
        view.addObject("formObj", JSON.toJSONString(entity));
        
    	Map<String,Object> filemaps = new HashMap<String,Object>();
    	filemaps.put("foreignId", id);
    	filemaps.put("isDelete", "00");
		view.addObject("file",pmFileService.queryList(filemaps));
        
        view.setViewName("/project/project/view");
        return view;
    }


    // 用户
    @RequestMapping(value = "/project/user", method = RequestMethod.GET)
    public ModelAndView userFormView(@RequestParam("act") String act,@RequestParam("orgNo") String orgNo,@RequestParam("roleCode") String roleCode) {
        ModelAndView view = new ModelAndView();
        view.addObject("act", act);
		view.addObject("orgNo",orgNo);
		view.addObject("roleCode",roleCode);
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
    
    
    // 客户
    @RequestMapping(value = "/project/costCode", method = RequestMethod.GET)
    public ModelAndView costCodeFormView(@RequestParam("act") String act,@RequestParam("orgId") String orgId) {
        ModelAndView view = new ModelAndView();
        view.addObject("act", act);
        view.addObject("orgId", orgId);
        view.setViewName("/project/project/costCode");
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
    
    //合同 
    @RequestMapping(value = "/project/contract", method = RequestMethod.GET)
    public ModelAndView contractFormView() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/project/project/contract");
        return view;
    }
    
    //产品 
    @RequestMapping(value = "/project/product", method = RequestMethod.GET)
    public ModelAndView productFormView() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/project/project/product");
        return view;
    }
}
