package com.indihx.project.controller;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.indihx.PmCustomerInfo.entity.PmCustomerGroupRelationEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerGroupRelationService;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.indihx.AbstractBaseController;
import com.indihx.PmCustomerInfo.entity.PmCustomerGroupEntity;
import com.indihx.PmCustomerInfo.entity.PmCustomerInfoEntity;
import com.indihx.PmCustomerInfo.service.PmCustomerGroupService;
import com.indihx.PmCustomerInfo.service.PmCustomerInfoService;
import com.indihx.comm.InitSysConstants;
import com.indihx.elecvote.entity.VoteHouseInfo;
import com.indihx.elecvote.service.HouseManageService;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;


@Controller
@RequestMapping("/project")
public class CustomerController extends AbstractBaseController{
	@Autowired
	private ParamsInfoServiceimpl infoservice;
	
    @Autowired
    private PmCustomerInfoService pmCustomerInfoService;
    
    @Autowired
    private PmCustomerGroupService pmCustomerGroupService;

	@Autowired
	private PmCustomerGroupRelationService pmCustomerGroupRelationService;
	@RequestMapping("/customer/index")
	public ModelAndView addCustomView() {
		
		ModelAndView view = new ModelAndView();
		
//		view.addObject("custType",infoservice.qryInfoByCode("CUST_TYPE","01"));
//		view.addObject("custTrade",infoservice.qryInfoByCode("CUST_TRADE","01"));
//		view.addObject("payCycle",infoservice.qryInfoByCode("PAY_CYCLE","01"));
//		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","00"));
//		view.addObject("bgVisiable",infoservice.qryInfoByCode("BG_VISIABLE","01"));
//		view.addObject("country",infoservice.qryInfoByCode("COUNTRY","01"));

		view.addObject("custType",infoservice.qryInfoByCode("CUST_TYPE"));
		view.addObject("custTrade",infoservice.qryInfoByCode("CUST_TRADE","01"));
		view.addObject("tradeCode",infoservice.qryInfoByCode("TRADE_CODE","01"));
		view.addObject("regionalMarket",infoservice.qryInfoByCode("REGIONAL_MARKET","00"));
		view.addObject("payCycle",infoservice.qryInfoByCode("PAY_CYCLE","01"));
		view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","00"));
		view.addObject("bgVisiable",infoservice.qryInfoByCode("BG_VISIABLE","01"));
		view.addObject("country",infoservice.qryInfoByCode("COUNTRY","00"));

		view.setViewName("/project/customer/index");
		return view;
	}
	@RequestMapping(value="/customer/form",method=RequestMethod.GET)
	public ModelAndView customFormView(@RequestParam("act") String act,@RequestParam("custId") long custId) {
		ModelAndView view = new ModelAndView();
		
		Map<String,Object> maps = new HashMap<String, Object>();
		maps.put("isDelete", "00");
		List<PmCustomerGroupEntity> pmCustomerGroup = pmCustomerGroupService.queryList(maps);
		view.addObject("customerGroup",pmCustomerGroup);
		

		
		if(!act.equalsIgnoreCase("add")) {
			PmCustomerInfoEntity custom = pmCustomerInfoService.queryObject(custId);
			Map<String,Object> relationMap = new HashMap<String, Object>();
			relationMap.put("custId", custId);
			List<PmCustomerGroupRelationEntity> pmCustomerGroupRelationEntity = pmCustomerGroupRelationService.queryList(relationMap);
//			PmCustomerGroupEntity customGroupEntity = pmCustomerGroupService.queryObject(custId);
			//代码待改善，可以通过写sql的方式直接获取，暂时先通过遍历，且目前并不完整
			if(!pmCustomerGroupRelationEntity.isEmpty()){
				for(PmCustomerGroupEntity groupId:pmCustomerGroup){
					if(groupId.getCustGroupId().equalsIgnoreCase(pmCustomerGroupRelationEntity.get(0).getCustGroupId())){
						view.addObject("customerGroupValue",groupId.getCustGroupId());
						view.addObject("customerGroupName",groupId.getCustGroupName());
					}

				}
			}else{
				view.addObject("customerGroupValue",null);
				view.addObject("customerGroupName","选择客户群");
			}
			view.addObject("Custom",custom);
			view.addObject("custType",infoservice.qryInfoByCode("CUST_TYPE",custom.getCustType()));
			view.addObject("custTrade",infoservice.qryInfoByCode("CUST_TRADE",custom.getCustTrade()));
			view.addObject("tradeCode",infoservice.qryInfoByCode("TRADE_CODE",custom.getTradeCode()));
			view.addObject("regionalMarket",infoservice.qryInfoByCode("REGIONAL_MARKET",custom.getRegionalMarket()));
			view.addObject("payCycle",infoservice.qryInfoByCode("PAY_CYCLE",custom.getPayCycle()));
			view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL",custom.getIsUseful()));
			view.addObject("bgVisiable",infoservice.qryInfoByCode("BG_VISIABLE",custom.getBgVisiable()));
			view.addObject("country",infoservice.qryInfoByCode("COUNTRY",custom.getCountry()));
		}else {
			view.addObject("custType",infoservice.qryInfoByCode("CUST_TYPE"));
			view.addObject("custTrade",infoservice.qryInfoByCode("CUST_TRADE","01"));
			view.addObject("tradeCode",infoservice.qryInfoByCode("TRADE_CODE","01"));
			view.addObject("regionalMarket",infoservice.qryInfoByCode("REGIONAL_MARKET","00"));
			view.addObject("payCycle",infoservice.qryInfoByCode("PAY_CYCLE","01"));
			view.addObject("isUseful",infoservice.qryInfoByCode("IS_USEFUL","00"));
			view.addObject("bgVisiable",infoservice.qryInfoByCode("BG_VISIABLE","01"));
			view.addObject("country",infoservice.qryInfoByCode("COUNTRY","00"));
		}
		view.addObject("act",act);
		view.addObject("custId",custId);
		
		view.setViewName("/project/customer/form");
		return view;
	}
	
	
	
	@RequestMapping(value="/customer/getAllCustomerList",method=RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> getAllCustomerList(@RequestBody Map<String, Object> requestMap){
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String ,Object> map = new HashMap<String ,Object>();
    	map.put("sapCode", "1000002207");
    	map.put("custCnName", "中国工商银行股份有限公司南通分行");
    	list.add(map);
    	
    	Map<String ,Object> map1 = new HashMap<String ,Object>();
    	map.put("sapCode", "1000002207");
    	map.put("custCnName", "中广核核技术发展股份有限公司");
    	list.add(map1);
    	
    	Map<String ,Object> map2 = new HashMap<String ,Object>();
    	map.put("sapCode", "1000002197");
    	map.put("custCnName", "兴业银行股份有限公司");
    	list.add(map2);
    	
    	Map<String ,Object> map3 = new HashMap<String ,Object>();
    	map.put("sapCode", "1000002196");
    	map.put("custCnName", "上海砾阳软件有限公司");
    	list.add(map3);
    	
		return list;
	}
	
	
	
}
