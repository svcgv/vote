package com.indihx.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.indihx.system.entity.UsrInfo;
import com.indihx.system.vo.MenuVo;
import com.indihx.system.vo.UserInfoVo;

/***
 * 
 * <p>描 述: 类描述</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年2月24日</p>
 * @author 谢追辉
 */
public interface ILoginService {
	
	/**
	 * 验证用户信息，并保存到session
	 * @param infoVo
	 * @return 
	 */
	public Map<String, Object> loginCheck(UserInfoVo infoVo,HttpSession session);
	
	/***
	 * 查询所有的菜单
	 * @return
	 */
	public List<MenuVo> qryMenuList(HttpSession session);
	
	/**
	 * 查询用户的菜单
	 * @param session
	 * @return
	 */
	public String qryMenuJson(String usrId,HttpSession session);
	
	public List<MenuVo> queryMenuListHome(UsrInfo usrInfo);
	
}
