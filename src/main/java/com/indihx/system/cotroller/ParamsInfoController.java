package com.indihx.system.cotroller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.indihx.system.entity.ParamsInfo;
import com.indihx.system.service.impl.ParamsInfoServiceimpl;
import com.indihx.system.vo.ParamsInfoVo;
/** 
 * 项目名称：wx_mng_maven 
 * 类名称：RoleInfoServiceImpl 
 * 类描述： 参数管理
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月9日 下午2:24:16 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
@Controller
@RequestMapping("/para")  
public class ParamsInfoController {
	//日志
	 
		@Autowired
		private ParamsInfoServiceimpl infoservice;
		/**
		 * 主页面
		 * @return
		 */
		@RequestMapping(value="/qryParamsInfo",method=RequestMethod.GET)
        public ModelAndView qryParamsInfo(){
        	Map<String,Object> map=infoservice.qryParamsInfo(new ParamsInfoVo());
        	ModelAndView view = new ModelAndView();
        	view.addObject("listInfo",map.get("listInfo"));
        	view.addObject("pageInfo", map.get("pageInfo"));
        	view.addObject("codeData",infoservice.qryAddInfo());
        	view.setViewName("/para/qryParaList");
        	return view;
        }
	  /**
	   * 查询
	   * @param infoVo
	   * @return
	   */
		@RequestMapping(value="/ajaxQryParaInfo",method=RequestMethod.POST)
		@ResponseBody
		public  Map<String, Object> ajaxQryParaInfo(@RequestBody ParamsInfoVo infoVo){
			Map<String, Object> map = infoservice.qryParamsInfo(infoVo);
			return map;
		}
		/**
		 * 打开参数信息新增页面
		 * @return
		 */
		@RequestMapping(value="/openAddPara",method=RequestMethod.GET)
		public ModelAndView openAddPara(){
			ModelAndView view = new ModelAndView();
			view.addObject("codeData",infoservice.qryAddInfo());
			view.setViewName("/para/addParamsInfo");
			return view;
		}
		/***
		 * 参数信息保存
		 * @param usrInfo
		 * @return 返回成功或失败页
		 */
		@RequestMapping(value="/addParaInfo",method=RequestMethod.POST)
		public @ResponseBody Map<String, Object> addParaInfo(@RequestBody ParamsInfoVo infoVo){
			Map<String, Object> map = new HashMap<String, Object>();
			boolean isSuccess = infoservice.addParamsInfo(infoVo);
			map.put("status", isSuccess);
			return map;
		}
		
		/***
		 * 根据用户ID查询参数信息
		 * @param infoVo
		 * @return
		 */
		@RequestMapping(value="/qryParaInfoById",method=RequestMethod.GET)
		public ModelAndView qryRoleInfoById(@RequestParam("paramsNo") String paramsNo){
			ParamsInfo paramsinfo = infoservice.qryParamsInfoList1(paramsNo);
			ModelAndView view = new ModelAndView();
			view.setViewName("/para/editParamsInfo");
			view.addObject("paramsinfo", paramsinfo);
			return view;
		}
		/**
		 * 编辑保存
		 * @param infoVo
		 * @return
		 */
		@RequestMapping(value="/editSave",method=RequestMethod.POST)
		public @ResponseBody Map<String,Object> editSave(@RequestBody ParamsInfoVo  infoVo){
			Map<String, Object> map = new HashMap<String, Object>();
			boolean isSuccess = infoservice.updParamsInfo(infoVo);
			map.put("status", isSuccess);
			return map;
		}
		/***
		 * 删除
		 * @param infoVo
		 * @return
		 */
		@RequestMapping(value="/delParaInfo",method=RequestMethod.POST)
		public @ResponseBody Map<String, Object> delParaInfo(@RequestBody ParamsInfoVo  infoVo){
			Map<String, Object> map = new HashMap<String, Object>();
			boolean isSucess = infoservice.deleParamsInfo(infoVo);
			map.put("success", isSucess);
			return map;
		}
		/***
		 * 检查入重复
		 * @param infoVo
		 * @return
		 */
		@RequestMapping(value="/checkParaInfo",method=RequestMethod.POST)
		public @ResponseBody Map<String, Object> checkParaInfo(@RequestBody ParamsInfoVo  infoVo){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paraInfo", infoservice.qryParamsInfoList(infoVo.getParamsNo())); 
			return map;
		}
}
