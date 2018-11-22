package com.indihx.project.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.indihx.AbstractBaseController;
import com.indihx.PmProjectGroupInfo.entity.PmProjectGroupInfoEntity;
import com.indihx.PmProjectGroupInfo.service.PmProjectGroupInfoService;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.impl.PmProjectInfoServiceImpl;
import com.indihx.PmProjectMilestoneInfo.service.PmProjectMilestoneInfoService;
import com.indihx.PmReviewInfo.entity.PmReviewInfoEntity;
import com.indihx.PmReviewInfo.service.PmReviewInfoService;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;
import com.indihx.util.UserUtil;

/**
 *  立项审批
 * */

@Controller
@RequestMapping("/project")
public class ProjectApproveController extends AbstractBaseController{
	
	@Autowired
	private ParamsInfoServiceimpl infoservice;

	    @Autowired
	    private PmProjectInfoServiceImpl pmProjectInfoServiceImpl;
	    
	    @Autowired
	    private PmProjectGroupInfoService pmProjectGroupInfoService;
	    
		@Autowired
		PmProjectMilestoneInfoService pmProjectMilestoneInfoService;
		
		@Autowired
	    private PmReviewInfoService pmReviewInfoService;

	@RequestMapping("/projectApprove/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
        view.addObject("projectStatus", infoservice.qryInfoByCode("PROJECT_STATUS","00"));
        view.addObject("projectType", infoservice.qryInfoByCode("PROJECT_TYPE","00"));
        view.addObject("approveStatus", infoservice.qryInfoByCode("APPROVE_STATUS","00"));
		
		view.setViewName("/project/projectApprove/index");
		return view;
	}
	
	@RequestMapping(value="/projectApprove/form",method=RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/projectApprove/form");
		return view;
	}
	
	@RequestMapping(value="/projectApprove/edit",method=RequestMethod.GET)
	public ModelAndView editFormView(@RequestParam("act") String act,@RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL"));
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/projectApprove/edit");
		return view;
	}
	
	@RequestMapping(value="/projectApprove/view",method=RequestMethod.GET)
	public ModelAndView viewFormView(@RequestParam("act") String act,@RequestParam("id") long id) {
		ModelAndView view = new ModelAndView();
		
		view.addObject("act",act);
		view.addObject("id",id);
		
		view.setViewName("/project/projectApprove/view");
		return view;
	}
	
	// 机构
		@RequestMapping(value="/projectApprove/org",method=RequestMethod.GET)
		public ModelAndView orgFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			
			view.setViewName("/project/projectApprove/org");
			return view;
		}
		
		// 用户
		@RequestMapping(value="/projectApprove/user",method=RequestMethod.GET)
		public ModelAndView userFormView(@RequestParam("act") String act,@RequestParam("orgNo") String orgNo,@RequestParam("roleCode") String roleCode) {
			ModelAndView view = new ModelAndView();
		    view.addObject("act", act);
			view.addObject("orgNo",orgNo);
			view.addObject("roleCode",roleCode);
			view.setViewName("/project/projectApprove/user");
			return view;
		}
		// 评审
		@RequestMapping(value="/projectApprove/review",method=RequestMethod.GET)
		public ModelAndView reviewFormView(@RequestParam("act") String act,@RequestParam("id")long id,HttpSession session){
			
			UsrInfo usesr = UserUtil.getUser(session);
			ModelAndView view = new ModelAndView();

		        PmProjectInfoEntity entity = pmProjectInfoServiceImpl.queryObject(id);
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
		        
		        
		    	Map<String ,Object> map = new HashMap<String,Object>();
				map.put("reviewType", "01");
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
				view.addObject("userName",usesr.getUsrName());
			
			view.setViewName("/project/projectApprove/review");
			return view;
		}
		// 客户
		@RequestMapping(value="/projectApprove/customer",method=RequestMethod.GET)
		public ModelAndView customerFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/projectApprove/customer");
			return view;
		}
		// 项目
		@RequestMapping(value="/projectApprove/project",method=RequestMethod.GET)
		public ModelAndView projectFormView(@RequestParam("act") String act) {
			ModelAndView view = new ModelAndView();
			view.addObject("act",act);
			view.setViewName("/project/projectApprove/project");
			return view;
		}
}
