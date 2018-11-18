package com.indihx.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indihx.AbstractBaseService;
import com.indihx.comm.InitSysConstants;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.datamng.dao.CommitteeMapper;
import com.indihx.datamng.dao.HpbMapper;
import com.indihx.datamng.dao.StreetMapper;
import com.indihx.system.dao.MenuInfoMapper;
import com.indihx.system.dao.UsrInfoMapper;
import com.indihx.system.dao.UsrRoleMapper;
import com.indihx.system.entity.MenuInfo;
import com.indihx.system.entity.MenuQryEntity;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.ILoginService;
import com.indihx.system.vo.MenuVo;
import com.indihx.system.vo.UserInfoVo;
import com.indihx.util.Assert;
import com.indihx.util.EncryptionUtil;
import com.indihx.util.EntityVoConverter;


/***
 * 
 * <p>描 述: 验证用户登录信息</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年2月24日</p>
 * @author 谢追辉
 */
@Service
public class LoginServiceImpl  extends AbstractBaseService implements ILoginService {
	private static Logger log = LoggerFactory.getLogger(LoginServiceImpl.class); 
	
	@Autowired
	private UsrInfoMapper mapper;
	
	@Autowired
	private MenuInfoMapper menuMapper;
	
	@Resource
	private UsrRoleMapper usrRoleMappeer;  //用户角色
	
	@Resource
	private HpbMapper hpbMappeer;  //区域信息
	
	@Resource
	private StreetMapper streetMappeer; //街道信息
	
	@Resource
	private CommitteeMapper sqMappeer;  //社区信息
	
	@Override
	public Map<String, Object> loginCheck(UserInfoVo infoVo,HttpSession session){
		Map<String, Object> map = new HashMap<String,Object>();
		try{
			String userId = EncryptionUtil.decryptByPrivateKey(infoVo.getUsrId());
			String password = EncryptionUtil.decryptByPrivateKey(infoVo.getPassWord());
			UsrInfo usrInfo = new UsrInfo();
			usrInfo.setUsrId(userId);
			usrInfo.setPassWord(password);
			//检查登录用户信息
			List<UsrInfo> list = null;
			try {
				list = mapper.qryUserInfoAll(usrInfo);
			} catch (Exception e) {
				log.info(e.getMessage());
				throw new BusinessException("Socket read timed out ！");
			}
			//登录结果验证
			if(list.size() > 1){
				map.put("stuatus", "1"); //登录用信息异常
				map.put("errInfo", "登录用户信息异常！");
				log.info("验证失败，存在多条用户信息！");
			}else if(list.size() < 1){
				map.put("stuatus", "2"); //用户信息不存在，账号或密码错误
				map.put("errInfo", "账号或密码输入错误！");
				log.info("验证失败，用户信息不存在！");
			}else {
				map.put("stuatus", "0");
				setSession(list.get(0), session);
			}
			return map;
		}catch(Exception e){
			map.put("stuatus", "9");//系统异常
			map.put("errInfo","登录失败："+e.getMessage());
			return map;
		}
	}
	
	/***
	 * 把用户信息存到session
	 * @param usrInfo
	 */
	private void setSession(UsrInfo usrInfo,HttpSession session){
		String id = session.getId();
		log.info("用户："+usrInfo.getUsrId()+"登入系统,登录session标志："+id);
		//如果是区局登录,将区域ID设置到session
//		if(usrInfo.getOrgType().equals(InitSysConstants.ORGTYPE_QUJU)){
//			long hpbBaseId =hpbMappeer.getOrgRefHpbId(Long.valueOf(usrInfo.getOrgNo()));
//			usrInfo.setHpbBaseId(ObjectUtil.toString(hpbBaseId));
//		}
//		//如果是街道登录,将街道ID设置到session
//		if(usrInfo.getOrgType().equals(InitSysConstants.ORGTYPE_JIEDAO)){
//			long streetBaseId =streetMappeer.getOrgRefJdId(Long.valueOf(usrInfo.getOrgNo()));
//			usrInfo.setStreetBaseId(ObjectUtil.toString(streetBaseId));
//		}
		//如果是社区登录,将社区ID设置到session
//		if(usrInfo.getOrgType().equals(InitSysConstants.ORGTYPE_JUWEIHUI)){
//			long sqBaseId =sqMappeer.getOrgRefSqId(Long.valueOf(usrInfo.getOrgNo()));
//			usrInfo.setCommitteeBaseId(ObjectUtil.toString(sqBaseId));
//		}
		usrInfo.setSessionId(id);
		mapper.updateLoginUser(usrInfo);
		//设置session有效时间8个小时
		session.setMaxInactiveInterval(280000);//280000=8小时
		session.setAttribute(InitSysConstants.USER_SESSION, usrInfo);
	}

	@Override
	public List<MenuVo> qryMenuList(HttpSession session) {
		UsrInfo usrInfo = (UsrInfo) session.getAttribute(InitSysConstants.USER_SESSION);
		MenuQryEntity qryEntity = new MenuQryEntity();
		//首先查询一级菜单
		qryEntity.setMenuLevel("1");
		qryEntity.setUsrId(usrInfo.getUsrId().toString());
		List<MenuInfo> list = menuMapper.qryMenuInfoByRoleUser(qryEntity);
		List<MenuVo> _listResult = new ArrayList<MenuVo>();
		//循环一级菜单，查询二级菜单
		for (MenuInfo menuInfo : list) {
			qryEntity.setMenuLevel("2");
			qryEntity.setParentId(menuInfo.getMenuId().toString());
			MenuVo menuVo = new MenuVo();  //一级菜单Vo
			EntityVoConverter.Convert(menuInfo, menuVo); //把一级菜单列表转换成Vo
			List<MenuInfo> list_ = menuMapper.qryMenuInfoByRoleUser(qryEntity); //二级菜单查出entity集合
			List<MenuVo> _list_2= new ArrayList<MenuVo>(); //二级菜单转换后的vo集合
			for (MenuInfo menuInfo_ : list_) {
				MenuVo menuVo_2 = new MenuVo();  //二级级菜单Vo
				EntityVoConverter.Convert(menuInfo_, menuVo_2);
				_list_2.add(menuVo_2);  //把二级菜单放入一级菜单的Vo中
			}
			menuVo.setMenuVo(_list_2);  //把二级菜单加到一级菜单实体类中
			_listResult.add(menuVo);
		}
		return _listResult;
	}

	/**
	 * 系统退出逻辑
	 * @param session
	 */
	public void clearSession(UsrInfo usrInfo,HttpSession session) {
		try{
			Assert.notNull(usrInfo, "用户登录已失效!");
			usrInfo.setSessionId("");
			int i =mapper.updateLoginUser(usrInfo);
			if(i!=1){
				throw new BusinessException("退出系统失败...");
			}
		}catch(Exception ex){
			throw new BusinessException("退出系统失败，用户登录已失效。");
		}
	}

	/**
	 * 组装Json格式的菜单
	 * @param session
	 * @return
	 */
	public String qryMenuJson(String usrId,HttpSession session) {
		try{
			Assert.hasText(usrId, "用户ID不能为空.");
			String cxt = session.getServletContext().getContextPath();
			//首先查询一级菜单
			MenuQryEntity qryEntity = new MenuQryEntity();
			qryEntity.setMenuLevel("1");
			qryEntity.setUsrId(usrId);
			List<MenuInfo> list = menuMapper.qryMenuInfoByRoleUser(qryEntity);
			
			//循环组装
			List<Map<String,Object>> _listResult = new ArrayList<Map<String,Object>>();
			//封装其他菜单
			Map<String,Object> menu1 = null;
			for (MenuInfo menuInfo : list) {
				//组装一级菜单
				menu1 = new HashMap<String,Object>();
				menu1.put("id",menuInfo.getMenuId().toString());
				menu1.put("icon",menuInfo.getCssIcon());
				menu1.put("text",menuInfo.getMenuName());
				menu1.put("url","");
				//组装二级菜单
				qryEntity.setMenuLevel("2");
				qryEntity.setParentId(menuInfo.getMenuId().toString());
				MenuVo menuVo = new MenuVo();  //一级菜单Vo
				EntityVoConverter.Convert(menuInfo, menuVo); //把一级菜单列表转换成Vo
				List<MenuInfo> list_ = menuMapper.qryMenuInfoByRoleUser(qryEntity); //二级菜单查出entity集合
				List<Map<String,Object>> menus = new ArrayList<Map<String,Object>>();
				Map<String,Object> menu2 = null;
				for (MenuInfo menuInfo_ : list_) {
					menu2 = new HashMap<String,Object>();
					menu2.put("id", menuInfo_.getMenuId().toString());
					menu2.put("icon", menuInfo_.getCssIcon());
					menu2.put("text", menuInfo_.getMenuName());
					menu2.put("url", cxt+menuInfo_.getMenuUrl());
					menu2.put("close", true);
					menus.add(menu2);
				}
				menu1.put("menus", menus);
				_listResult.add(menu1);
			}
			//System.out.println(JSONArray.fromObject(_listResult).toString());
			return JSONArray.fromObject(_listResult).toString();
		}catch(Exception ex){
			throw new BusinessException("退出系统失败，用户登录已失效。");
		}
	}
	
	@Override
	public List<MenuVo> queryMenuListHome(UsrInfo usrInfo) {
		MenuQryEntity qryEntity = new MenuQryEntity();
		// 查询首页需要展示的一级菜单信息
		qryEntity.setMenuLevel("1"); // 首页展示的菜单
		qryEntity.setIsHome("Y");
		qryEntity.setUsrId(usrInfo.getUsrId().toString());
		qryEntity.setParentId(null);
		List<MenuInfo> homes = menuMapper.qryMenuInfoByRoleUser(qryEntity);
		List<MenuVo> listHome = new ArrayList<MenuVo>(); // 首页菜单展示集合
		for (int i = 0; i < homes.size(); i++) {
			MenuVo menuVo = new MenuVo(); // 一级菜单Vo
			EntityVoConverter.Convert(homes.get(i), menuVo);
			qryEntity.setMenuLevel("9");
			qryEntity.setParentId(menuVo.getMenuId());
			List<MenuInfo> listTwo = menuMapper.qryMenuInfoByRoleUser(qryEntity); // 二级菜单查出entity集合
			List<MenuVo> listTwoVo = new ArrayList<MenuVo>(); // 二级菜单转换后的vo集合
			for (MenuInfo menuInfo_ : listTwo) {
				MenuVo menuVo_2 = new MenuVo(); // 二级级菜单Vo
				EntityVoConverter.Convert(menuInfo_, menuVo_2);
				listTwoVo.add(menuVo_2); // 把二级菜单放入一级菜单的Vo中
			}
			menuVo.setMenuVo(listTwoVo); // 把二级菜单加到一级菜单实体类中
			listHome.add(menuVo);
		}
		return listHome;
	}
}
