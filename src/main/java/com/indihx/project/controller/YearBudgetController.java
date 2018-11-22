package com.indihx.project.controller;

import java.util.ArrayList;
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

import com.indihx.AbstractBaseController;
import com.indihx.PmProjectInfo.entity.PmProjectInfoEntity;
import com.indihx.PmProjectInfo.service.PmProjectInfoService;
import com.indihx.PmYearBudget.entity.PmYearBudgetEntity;
import com.indihx.PmYearBudget.entity.PmYearBudgetResponseEntity;
import com.indihx.PmYearBudget.service.PmYearBudgetService;
import com.indihx.comm.util.DateUtil;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;
import com.indihx.util.BeanUtils;
import com.indihx.util.UserUtil;

/**
 * 年度预算
 */

@Controller
@RequestMapping("/project")
public class YearBudgetController extends AbstractBaseController {
	@Autowired private ParamsInfoServiceimpl infoservice;

	@Autowired private PmYearBudgetService pmYearBudgetService;

	@Autowired private PmProjectInfoService pmProjectInfoService;

	@RequestMapping("/yearBudget/index")
	public ModelAndView addCustomView() {

		ModelAndView view = new ModelAndView();

		view.addObject("isUseful", this.infoservice.qryInfoByCode("IS_USEFUL", "01"));
		view.addObject("productType", this.infoservice.qryInfoByCode("PRODUCT_TYPE"));

		view.setViewName("/project/yearBudget/index");
		return view;
	}

	// 客户
	@RequestMapping(value = "/yearBudget/customer", method = RequestMethod.GET)
	public ModelAndView customerFormView(@RequestParam("act") String act) {
		ModelAndView view = new ModelAndView();
		view.addObject("act", act);
		view.setViewName("/project/yearBudget/customer");
		return view;
	}

	// 新form
	@RequestMapping(value = "/yearBudget/form2", method = RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act, @RequestParam("id") String id, HttpSession session) throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoSuchFieldException, SecurityException {
		ModelAndView view = new ModelAndView();

		view.addObject("taxType", infoservice.qryInfoByCode("taxType","00"));
		view.addObject("projectType2", infoservice.qryInfoByCode("projectType","00"));
		view.addObject("currency", infoservice.qryInfoByCode("currency","00"));
		view.addObject("revRecognitionMethod", infoservice.qryInfoByCode("revRecognitionMethod","00"));
		view.addObject("region", infoservice.qryInfoByCode("COUNTRY","00"));
		
		
		view.addObject("isUseful", this.infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("productType", this.infoservice.qryInfoByCode("PRODUCT_TYPE"));

		view.addObject("act", act);
		view.addObject("id", id);
		view.addObject("budgetYear", DateUtil.getNextYearStr());// 当前年+1
		view.addObject("projectType", "01");// 02 项目类型为产品
		
		UsrInfo usesr = UserUtil.getUser(session);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("creatorId", usesr.getUsrId());
		List<PmYearBudgetEntity> pmYearBudget2 = this.pmYearBudgetService.queryList(map);
		if (pmYearBudget2 != null && !pmYearBudget2.isEmpty()) {
			// 重新组装list，返回给前台
			List<PmYearBudgetResponseEntity> responseEntities = new ArrayList<PmYearBudgetResponseEntity>();
			List<Map<String, Object>> pmYearBudgetGroupList = this.pmYearBudgetService.querySapCodeCount(usesr.getUsrId());
			for (Map<String, Object> responseMap : pmYearBudgetGroupList) {
				PmYearBudgetResponseEntity responseEntity = new PmYearBudgetResponseEntity();
				String sapCode = (String) responseMap.get("sapCode");
				String custName = (String) responseMap.get("custName");
				responseEntity.setSapCode(sapCode);
				responseEntity.setCustName(custName);
				
				List<PmYearBudgetEntity> pmYearBudgetTempList = new ArrayList<PmYearBudgetEntity>();

				for (PmYearBudgetEntity yearBudgetEntity : pmYearBudget2) {
					if (sapCode!=null && sapCode.equalsIgnoreCase(yearBudgetEntity.getSapCode())) {
						yearBudgetEntity.setProductIds(yearBudgetEntity.getProductIds());
						yearBudgetEntity.setProductNames(yearBudgetEntity.getProductNames());
						pmYearBudgetTempList.add(yearBudgetEntity);
					}
				}

				responseEntity.setPmYearBudgetEntity(pmYearBudgetTempList);
				responseEntities.add(responseEntity);
			}

			// pmYearBudget = pmYearBudget2;
			view.addObject("list", responseEntities);
		} else {
			String next0101 = DateUtil.getNextYearStr() + "0101";
			Map<String, Object> par = new HashMap<>();
			par.put("userId", usesr.getUsrId());

			par.put("finishProjectEndTime", next0101);
			List<Map<String, Object>> pmYearBudgetGroupList = this.pmYearBudgetService.queryProjectSapCodeCount(par);
			// ToDo 状态为未完结的项目 且客户经理为当前用户的
			Map<String, Object> entity = new HashMap<String, Object>();

			entity.put("isDelete", "00");
			entity.put("custManagerId", usesr.getUsrId());
			entity.put("finishProjectEndTime", next0101);
			List<PmProjectInfoEntity> projectList = this.pmProjectInfoService.queryList(entity);

			List<Map<String, Object>> listMap = BeanUtils.BeanList2MapList(projectList);
			List<PmYearBudgetEntity> pmProject = BeanUtils.MapList2BeanList(listMap, PmYearBudgetEntity.class);
			List<PmYearBudgetResponseEntity> responseEntities = new ArrayList<PmYearBudgetResponseEntity>();

			for (Map<String, Object> responseMap : pmYearBudgetGroupList) {
				PmYearBudgetResponseEntity responseEntity = new PmYearBudgetResponseEntity();
				String sapCode = (String) responseMap.get("sapCode");
				String custName = (String) responseMap.get("custName");
				responseEntity.setSapCode(sapCode);
				responseEntity.setCustName(custName);

				List<PmYearBudgetEntity> pmYearBudgetTempList = new ArrayList<PmYearBudgetEntity>();

				for (PmYearBudgetEntity yearBudgetEntity : pmProject) {
					if (sapCode.equalsIgnoreCase(yearBudgetEntity.getSapCode())) {
						yearBudgetEntity.setProductIds(yearBudgetEntity.getProductIds());
						yearBudgetEntity.setProductNames(yearBudgetEntity.getProductNames());
						pmYearBudgetTempList.add(yearBudgetEntity);
					}
				}

				responseEntity.setPmYearBudgetEntity(pmYearBudgetTempList);
				responseEntities.add(responseEntity);
			}
			view.addObject("list", responseEntities);
		}

		view.setViewName("/project/yearBudget/form2");
		return view;
	}

	@RequestMapping(value = "/yearBudget/edit", method = RequestMethod.GET)
	public ModelAndView editFormView(@RequestParam("act") String act, @RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();

		view.addObject("isUseful", this.infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("productType", this.infoservice.qryInfoByCode("PRODUCT_TYPE", "01"));

		view.addObject("act", act);
		view.addObject("id", id);

		view.setViewName("/project/yearBudget/edit");
		return view;
	}

	// form
	@RequestMapping(value = "/yearBudget/form", method = RequestMethod.GET)
	public ModelAndView formFormView(@RequestParam("act") String act, @RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();
		view.addObject("taxType", infoservice.qryInfoByCode("taxType","00"));
		view.addObject("projectType2", infoservice.qryInfoByCode("projectType","00"));
		view.addObject("currency", infoservice.qryInfoByCode("currency","00"));
		view.addObject("revRecognitionMethod", infoservice.qryInfoByCode("revRecognitionMethod","00"));
		view.addObject("region", infoservice.qryInfoByCode("COUNTRY","00"));
		view.addObject("isUseful", this.infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("productType", this.infoservice.qryInfoByCode("PRODUCT_TYPE"));

		view.addObject("act", act);
		view.addObject("id", id);

		view.setViewName("/project/yearBudget/form");
		return view;
	}

	// product
	@RequestMapping(value = "/yearBudget/product", method = RequestMethod.GET)
	public ModelAndView productFormView(@RequestParam("act") String act, @RequestParam("index") String index, @RequestParam("YIndex") String YIndex) {
		ModelAndView view = new ModelAndView();
		view.addObject("act", act);
		view.addObject("index", index);
		view.addObject("YIndex", YIndex);

		view.setViewName("/project/yearBudget/product");
		return view;
	}

	// trTmpl 根据 客户ID 和客户名称 查 项目 。。。。
	@RequestMapping(value = "/yearBudget/trTempl", method = RequestMethod.GET)
	public ModelAndView trTemplFormView(@RequestParam("custName") String custName, @RequestParam("custId") String custId) {
		ModelAndView view = new ModelAndView();
		view.addObject("custName", custName);
		view.addObject("custId", custId);
		view.addObject("projectType2", infoservice.qryInfoByCode("projectType","00"));
		view.addObject("projectType", "01");// 02 项目类型为产品
		view.addObject("budgetYear", DateUtil.getNextYearStr());// 当前年+1
		view.setViewName("/project/yearBudget/trTempl");
		return view;
	}

	@RequestMapping(value = "/yearBudget/view", method = RequestMethod.GET)
	public ModelAndView viewFormView(@RequestParam("act") String act, @RequestParam("id") String id) {
		ModelAndView view = new ModelAndView();

		view.addObject("isUseful", this.infoservice.qryInfoByCode("IS_USEFUL"));
		view.addObject("productType", this.infoservice.qryInfoByCode("PRODUCT_TYPE", "01"));

		view.addObject("act", act);
		view.addObject("id", id);

		view.setViewName("/project/yearBudget/view");
		return view;
	}

	// wbs
	@RequestMapping(value = "/yearBudget/wbs", method = RequestMethod.GET)
	public ModelAndView wbsFormView(@RequestParam("act") String act, @RequestParam("index") String index, @RequestParam("YIndex") String YIndex) {
		ModelAndView view = new ModelAndView();
		view.addObject("act", act);
		view.addObject("index", index);
		view.addObject("YIndex", YIndex);
		view.setViewName("/project/yearBudget/wbs");
		return view;
	}

}
