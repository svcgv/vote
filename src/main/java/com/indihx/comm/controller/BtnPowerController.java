package com.indihx.comm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.indihx.AbstractBaseController;
import com.indihx.system.entity.BtnRole;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.BtnRoleServiceImpl;
/***
 * 
 * <p>描 述:按钮权限管理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年7月25日</p>
 * @author 谢追辉
 */
@Controller
@RequestMapping("/btnPower")
public class BtnPowerController extends AbstractBaseController {
	
	@Resource
	private BtnRoleServiceImpl btnRoleService;
	
	/***
	 * 按钮权限查询
	 * @param session session
	 * @param request 
	 * @return 按钮权限集合
	 */
	@RequestMapping(value="/qryBtnByUser.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> qryBtnByUser(HttpSession session,HttpServletRequest request){
		UsrInfo usrInfo = getUser(session);
		String roleId = usrInfo.getRoleId();
		StringBuffer btnList = new StringBuffer();
		if (StringUtils.isNotEmpty(roleId)) {
			String[] roleIdList = roleId.split(",");
			for (int j = 0; j < roleIdList.length; j++) {
				List<BtnRole> btnRoleList = btnRoleService.qryBtnRoleAll(roleIdList[j]);
				for (int i = 0; i < btnRoleList.size(); i++) {
					btnList.append(btnRoleList.get(i).getBtnId()+"|");
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("btnList", btnList.toString());
		return map;
	}
	
}
