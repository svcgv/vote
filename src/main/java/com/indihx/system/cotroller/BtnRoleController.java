package com.indihx.system.cotroller;

import java.util.HashMap;
import java.util.Map;

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

import com.indihx.system.service.impl.BtnInfoServiceImpl;
import com.indihx.system.service.impl.BtnRoleServiceImpl;
import com.indihx.system.vo.BtnInfoVo;
import com.indihx.system.vo.BtnRoleVo;

/***
 * 
 * <p>描 述: 按钮角色信息管理controller </p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年7月26日</p>
 * @author 严蒙蒙
 */
@Controller
@RequestMapping("/btnRole")  
public class BtnRoleController {
	
	//日志
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(BtnRoleController.class); 
	
	@Autowired
	private BtnInfoServiceImpl btnInfoService;
	@Autowired
	private BtnRoleServiceImpl btnRoleService;
	
	/***
	 * 查询按钮信息列表
	 * @return 
	 */
	@RequestMapping(value="/qryBtnInfo.do",method=RequestMethod.GET)  
	public ModelAndView qryCodeData(@RequestParam("roleId") String roleId){
		BtnInfoVo vo = new BtnInfoVo();
		vo.setRoleId(roleId);
		Map<String, Object> map= btnInfoService.qryRoleBtnInfoList(vo);
		ModelAndView view = new ModelAndView();
		view.addObject("listInfo", map.get("listInfo"));
		view.addObject("pageInfo",map.get("pageInfo"));
		view.addObject("btnArr",map.get("btnArr"));
		view.setViewName("/role/setBtnRole");
		return view;
	}
	
	/*分页、筛选条件的查询*/
	@RequestMapping(value="/ajaxQryBtnInfo.do",method=RequestMethod.POST)  
	@ResponseBody
	public  Map<String, Object> ajaxQryBtnInfo(@RequestBody BtnInfoVo dataVo){
		Map<String, Object> map = btnInfoService.qryRoleBtnInfoList(dataVo);
		return map;
	}
	

	/***
	 * 角色按钮信息保存
	 * @param dataVo
	 * @return 返回成功或失败页
	 */
	@RequestMapping(value="/addSave.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addCodeData(@RequestBody BtnRoleVo dataVo){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = btnRoleService.addBtnRole(dataVo);
		map.put("status", isSuccess);
		return map;
	}
	
	
}
