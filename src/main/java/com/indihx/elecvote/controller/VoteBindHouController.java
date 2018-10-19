/**
 * 
 */
package com.indihx.elecvote.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.system.entity.UsrInfo;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: VoteBindHouController.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年7月10日下午1:58:08</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>VoteBindHouController.java</p>
 * 公众号绑定房屋
 * 1、绑定房屋，输入房屋相关属性匹配成功自动绑定，保存账号和房屋的绑定关系
 * 2、查询已绑定的房屋信息
 * 3、删除已绑定的房屋信息
 */
@Controller
@RequestMapping("/WeChatbind")
public class VoteBindHouController extends AbstractBaseController {
	
	/**
	 * 绑定房屋界面
	 * @param session
	 * @return
	 */
	@RequestMapping("index")
	public ModelAndView bindHouseView(HttpSession session){
		UsrInfo user = getUser(session);
		
		//项目下拉框
		//TODO
		
		//楼幢下拉框
		//TODO
		
		//单元下拉框
		//TODO
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/elecvote/WeChat/bindHouse");
		view.addObject("","");
		return view;
	}
	
	/**
	 * 保存綁定房屋与用户关系
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveBindHouse.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveRecordInfo(@RequestBody Map<String, Object> reqMap,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);

		//TODO
		
		return null;
	}
	
	/**
	 * 查看已绑定房屋界面
	 * @param session
	 * @return
	 */
	public ModelAndView getBindHouseList(HttpSession session){
		UsrInfo user = getUser(session);

		//TODO
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/elecvote/bind/getBindHouseList");
		view.addObject("","");
		return view;
	}
	
	/**
	 * 删除已綁定房屋
	 * @param reqMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/unBindHouse.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> unBindHouse(@RequestBody Map<String, Object> reqMap,HttpSession session) throws Exception{
		UsrInfo usrInfo = getUser(session);

		//TODO
		
		return null;
	}
}
