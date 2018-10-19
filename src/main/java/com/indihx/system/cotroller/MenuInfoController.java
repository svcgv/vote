package com.indihx.system.cotroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.system.entity.MenuInfo;
import com.indihx.system.service.impl.MenuInfoServiceImpl;
import com.indihx.system.vo.MenuVo;
@Controller
@RequestMapping("/menu")
public class MenuInfoController extends AbstractBaseController{
	//日志
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MenuInfoController.class); 
	@Autowired
    private MenuInfoServiceImpl menuInfo;
	
	//首页菜单列表显示
	@RequestMapping(value="/qryViewInfo",method=RequestMethod.GET)
	public ModelAndView qryViewInfo(){
		Map<String, Object> map=menuInfo.qryMenuInfo(new MenuVo());
		ModelAndView view=new ModelAndView();
		view.addObject("listInfo",map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		view.addObject("codeData",menuInfo.qryLevelRage());
		view.setViewName("/menu/qryMenuList");
		return view;
	}
	
	//ajax条件筛选查询
	@RequestMapping(value="/ajaxQryMenuInfo",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> ajaxQryMenuInfo(@RequestBody MenuVo menuvo){
		Map<String, Object> map = menuInfo.qryMenuInfo(menuvo);
		return map;
	}
	
	
	/**
	 * 打开菜单信息新增页面
	 * @return
	 */
	@RequestMapping(value="/openMenuInfo",method=RequestMethod.GET)
	public ModelAndView openMenuInfo(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/menu/addMenuInfo");
		return view;
	}
	/*@RequestMapping(value="/openMenuInfo",method=RequestMethod.POST)
	public String openAddMenuInfo(){
		log.info("打开字典信息新增页面！！");
		return "/menu/addMenuInfo";
	}*/
	
	
	/***
	 * 菜单信息保存
	 * @param menuvo
	 * @return 返回成功或失败页
	 */
	@RequestMapping(value="/addSave",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addMenuInfo(@RequestBody MenuVo menuvo,HttpSession session){
		menuvo.setOperUser(getUser(session).getUsrId().toString());
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = menuInfo.addMenuInfo(menuvo);
		map.put("status", isSuccess);
		return map;
	}
	
	
	//查询父菜单信息
	@RequestMapping(value="/qryParentId",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> qryParentId(@RequestBody MenuVo menuvo){
		Map<String, String> map = new HashMap<String, String>();
		map.put("options", menuInfo.qryParentId(menuvo));
		return map;
	}
	
	//查询菜单级别option信息
	@RequestMapping(value="/qryMenuRage",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> qryMenuRage(@RequestBody MenuVo menuvo){
		Map<String, String> map = new HashMap<String, String>();
		map.put("options", menuInfo.qryMenuRage(menuvo));
		return map;
	}
	
	/***
	 * 根据菜单ID查询信息
	 * @param dataVo
	 * @return
	 */
	@RequestMapping(value="/qryMenuInfoById",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> qryMenuInfoById(@RequestBody MenuVo menuvo){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuInfo", menuInfo.qryMenuInfoById(menuvo));
		return map;
	}
	
	
	/**
	 * 打开修改页面，根据联合主键查询当前数据字典信息
	 * @param codeNo ,codeKeyBy 联合主键
	 * @return
	 */
	@RequestMapping(value="/openUpdateMenu",method=RequestMethod.GET)
	public ModelAndView openUpdateMenu(@RequestParam("menuId") String menuId){
		 MenuVo menuvo=new MenuVo();
		 menuvo.setMenuId(menuId);
		 MenuInfo menu = menuInfo.qryMenuInfoById(menuvo);
		ModelAndView view = new ModelAndView();
		view.setViewName("/menu/editMenuInfo");
		menuvo.setMenuLevel(menu.getMenuLevel());
		menuvo.setParentIdBy(menu.getParentId().toString());
		menu.setLevelHtml(menuInfo.qryMenuRage(menuvo));
		menu.setParentHtml( menuInfo.qryParentId(menuvo));
		view.addObject("menu", menu);
		return view;
	}
	/***
	 * 修改菜单信息的保存
	 * @param dataVo
	 * @return
	 */
	@RequestMapping(value="/editSave",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> editSave(@RequestBody MenuVo menuvo,HttpSession session){
		menuvo.setOperUser(getUser(session).getUsrId().toString());
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSucess = menuInfo.updMenuInfo(menuvo);
		map.put("success", isSucess);
		return map;
	}
	
	/***
	 * 删除菜单信息
	 * @param dataVo
	 * @return
	 */
	@RequestMapping(value="/delMenuInfo",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> delCodeData(@RequestBody MenuVo menuvo){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSucess = menuInfo.deleMenuInfo(menuvo);
		map.put("success", isSucess);
		return map;
	}
	
	
    
}
