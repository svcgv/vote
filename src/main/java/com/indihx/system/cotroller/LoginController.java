package com.indihx.system.cotroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.exception.BusinessException;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.LoginServiceImpl;
import com.indihx.system.service.impl.UsrInfoServiceImpl;
import com.indihx.system.vo.MenuVo;
import com.indihx.system.vo.UserInfoVo;
/***
 * 
 * <p>描 述: 用户登录</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年2月24日</p>
 * @author 谢追辉
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController  extends AbstractBaseController{
	@Autowired
	private LoginServiceImpl ILoginService;
	@Autowired
	private UsrInfoServiceImpl IUsrInfoService;
	/***
	 * 用户登录
	 * @return
	 */
	@RequestMapping(value="/loginInit",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> loginInit(@RequestBody UserInfoVo usrVo,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		map = ILoginService.loginCheck(usrVo,session);
		return map;
	}
	
	/***
	 * 加载用户菜单
	 * @return 菜单集合
	 */
	@RequestMapping(value="/index")
	public ModelAndView qryIndex(HttpSession session){
		ModelAndView view = new ModelAndView();
		//view.addObject("menuList", ILoginService.qryMenuList(session));
		view.addObject("usrInfo", session.getAttribute(InitSysConstants.USER_SESSION));
		view.setViewName("/init/index");
		return view;
	}
	
	@RequestMapping(value="/getMenuByUser.do",method=RequestMethod.GET)
	public @ResponseBody String getMenuByUser(@RequestParam("usrId") String usrId,HttpSession session) throws Exception{
		String menu =ILoginService.qryMenuJson(usrId,session);
		return menu;
	}
	
	/**
	 * 退出系统
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/outLogin")
	public ModelAndView outSys(HttpSession session){
		if(session==null){
			throw new BusinessException("用户session已经失效。");
		}
		UsrInfo usrInfo = (UsrInfo) session.getAttribute(InitSysConstants.USER_SESSION);
		session.removeAttribute("usrInfo");
		session.invalidate();
		if(usrInfo!=null){
			ILoginService.clearSession(usrInfo,session);
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("/login");
		return view;
	}
	
	/**
	 * 打开设置密码页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/setUserPassword")
	public ModelAndView setUserPassword(HttpSession session){
		UsrInfo usrInfo = (UsrInfo) session.getAttribute(InitSysConstants.USER_SESSION);
		ModelAndView view = new ModelAndView();
		view.setViewName("/init/setUserPassword");
		view.addObject("usrInfo", usrInfo);
		return view;
	}
	
	
	/**
	 * 打开个人资料页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getProfile")
	public ModelAndView getProfile(HttpSession session){
		UsrInfo usrInfo = (UsrInfo) session.getAttribute(InitSysConstants.USER_SESSION);
		ModelAndView view = new ModelAndView();
		view.setViewName("/init/getProfile");
		view.addObject("usrInfo", usrInfo);
		return view;
	}
	
	/***
	 * 查询首页展示的图标
	 * @param session 
	 * @return 首页菜单图标
	 */
	@RequestMapping(value="/queryHomeMenuInfo.do")
	public @ResponseBody List<MenuVo> queryHomeMenuInfo(HttpSession session){
		UsrInfo usrInfo = getUser(session);
		List<MenuVo> lists = ILoginService.queryMenuListHome(usrInfo);
		return lists;
	}
}
