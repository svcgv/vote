package com.indihx.system.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.indihx.AbstractBaseService;
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.util.DateUtil;
import com.indihx.system.dao.MenuInfoMapper;
import com.indihx.system.dao.RoleInfoMapper;
import com.indihx.system.dao.RoleMenuInfoMapper;
import com.indihx.system.entity.MenuInfo;
import com.indihx.system.entity.MenuQryEntity;
import com.indihx.system.entity.RoleInfo;
import com.indihx.system.entity.RoleMenuInfo;
import com.indihx.system.service.IRoleInfoService;
import com.indihx.system.vo.RoleInfoVo;
import com.indihx.system.vo.RoleMenuInfoVo;

/** 
 * 项目名称：wx_mng_maven 
 * 类名称：RoleInfoServiceImpl 
 * 类描述：角色管理
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月9日 下午2:24:16 
 * @Copyright: 2017 All rights reserved
 * @version 1.0
 */
@Service
public class RoleInfoServiceImpl  extends AbstractBaseService implements IRoleInfoService{
	private static Logger logger = LoggerFactory.getLogger(RoleInfoServiceImpl.class);
    @Autowired
    private RoleInfoMapper roleInfo;
    @Autowired
	private MenuInfoMapper menuMapper;
    @Autowired
	private RoleMenuInfoMapper rolemenuMapper;
	@Autowired
	private CommUtilMapper mapper;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	   /**
     * 主页面
     * @return
     */
	@Override
	public Map<String, Object> qryRoleInfoList(RoleInfoVo infovo) {
		RoleInfo info=new RoleInfo();
		info.setRoleName(infovo.getRoleName());
		String TmSmp=infovo.getTmSmp();
		if(!StringUtil.isEmpty(TmSmp)){
			TmSmp=TmSmp.replaceAll("-", "");
		}
		info.setTmSmp(TmSmp);
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(infovo.getPages(), infovo.getRows(), true);
		List<RoleInfo> listInfo=roleInfo.qryRoleInfoInfoAll(info);
		PageInfo pageInfo = new PageInfo(listInfo);
		map.put("listInfo", listInfo);
		map.put("pageInfo", pageInfo);
		return map;
	}
	  /**
     * 保存
     * @param InfoVo
     * @return
     */
	@Override
	public boolean addRoleInfo(RoleInfoVo infoVo) {
		String roleId=mapper.getUserId();
		RoleInfo info=new RoleInfo();
		info.setRoleId(new Long(roleId));
		info.setRoleName(infoVo.getRoleName());
		info.setRoleRmk(infoVo.getRoleRmk());
		info.setTmSmp(DateUtil.getSysDate());
		int num=roleInfo.insertSelective(info);
		if(num>0){
			return true;
		}
		return false;
	}
	/**
	 * 编辑页面
	 * @param InfoVo
	 * @return
	 */
	@Override
	public RoleInfo qryRoleInfoById(String roleId) {
		
		return roleInfo.selectByPrimaryKey(new Long(roleId));
	}
	/**
	 * 编辑保存
	 * @param InfoVo
	 * @return
	 */
	@Override
	public boolean updRoleInfoById(RoleInfoVo infoVo) {
		try {
			String roleId=infoVo.getRoleId().replaceAll(",", "").trim();
			RoleInfo info=new RoleInfo();
			info.setRoleId(new Long(roleId));
			info.setRoleName(infoVo.getRoleName());
			info.setRoleRmk(infoVo.getRoleRmk());
			info.setTmSmp(DateUtil.getSysDate());
			int num=roleInfo.updateByPrimaryKeySelective(info);
			if(num<0){
				return false;
			}
		} catch (Exception e) {
			logger.info("系统异常！！");
			//使用trycatch时，必须手动加上事务回滚，spring默认只会回滚runtimeException异常才回回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			throw new BusinessException("保存角色信息失败!");
		}
		return true;

	}
	/**
	 * 删除
	 * @param InfoVo
	 * @return
	 */
	@Override
	public boolean deleteRoleInfo(RoleInfoVo infoVo) {
		String roleId=infoVo.getRoleId();
		String arr[]=roleId.split(",");
		for(int i=0;i<arr.length;i++){
			if(!StringUtil.isEmpty(arr[i])){
			roleInfo.deleteByPrimaryKey(new Long(arr[i]));
			}
		}
		return true;
	}

	@Override
	public List<RoleInfo> qryRoleByRoleId(String roleId) {
		
		return null;
	}
	/***
	 * 查询首页菜单信息
	 * @return 菜单集合
	 */

	public Map<String, Object> qryMenuJson(String roleId){
		RoleMenuInfo info=new RoleMenuInfo();
	    info.setRoleId(roleId);
		List<RoleMenuInfo> usrList = rolemenuMapper.qryRoleMenuInfoInfoAll(info);
		List<Long> listUsrMenuId = new ArrayList<Long>();
		for (RoleMenuInfo usrMenu :   usrList) {
			listUsrMenuId.add(usrMenu.getMenuId());
		}
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		MenuQryEntity qryEntity = new MenuQryEntity();
		//首先查询一级菜单
		qryEntity.setMenuLevel("1");
		List<MenuInfo> list = menuMapper.qryMenuInfoByUsr(qryEntity);
		List<Map<String, Object>>  listMeau= new ArrayList<Map<String, Object>>();
		for (MenuInfo menuInfo : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", menuInfo.getMenuName());
			map.put("menuId", menuInfo.getMenuId());
			if(listUsrMenuId.contains(menuInfo.getMenuId())){
				Map<String, Object> status = new HashMap<String, Object>();
				status.put("selected", "true");
				map.put("state", status);
			}
			//查询一级菜单下的二级菜单
			qryEntity.setMenuLevel("2");
			qryEntity.setParentId(menuInfo.getMenuId().toString());
			List<MenuInfo> list_ = menuMapper.qryMenuInfoByUsr(qryEntity); //二级菜单查出entity集合
			if(list_.size() > 0 ){
				List<Map<String, Object>> _list_2= new ArrayList<Map<String, Object>>(); //二级菜单集合
				for (int i = 0; i < list_.size(); i++) {
					Map<String, Object> map2= new HashMap<String, Object>();
					MenuInfo childMenu = list_.get(i);
					map2.put("text", childMenu.getMenuName());
					map2.put("menuId", childMenu.getMenuId());
					if(listUsrMenuId.contains(childMenu.getMenuId())){  //当当前菜单存在用户集合中，则默认选中
						Map<String, Object> status = new HashMap<String, Object>();
						status.put("selected", "true");
						map2.put("state", status);
					}
					_list_2.add(map2);
				}
				map.put("nodes", _list_2);
			}
			listMeau.add(map);
		}
		resMap.put("mean", listMeau);
		return resMap;
	}	
	/**
	 * 保存角色菜单
	 * @param InfoVo
	 * @return
	 */
	@Override
	public boolean saveRoleInfo(RoleMenuInfoVo infoVo) {
		RoleMenuInfo info=new RoleMenuInfo();
		String roleId=infoVo.getRoleId().replaceAll(",", "").trim();
		info.setRoleId(new Long(roleId));
		info.setTmSmp(DateUtil.getSysDate());
		String menuId=infoVo.getMenuId();
		String[] arr = menuId.split("\\|");
		
		 int num1=rolemenuMapper.deleteByPrimaryKey(new Long(roleId));
		  if(num1<0){
				return false;
		  }
		for(int i=0;i<arr.length;i++){
			if(!StringUtil.isEmpty(arr[i])){
				 info.setMenuId(new Long(arr[i]));
				 int num=	rolemenuMapper.insertSelective(info);
				 if(num<0){
					return false;
				
			  }
			}
		 }
		return true;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> qryRoleUserEditInfoList(RoleInfoVo infovo) {
		RoleInfo info=new RoleInfo();
		info.setRoleName(infovo.getRoleName());
		info.setRoleId(infovo.getRoleId());
		String TmSmp=infovo.getTmSmp();
		if(!StringUtil.isEmpty(TmSmp)){
			TmSmp=TmSmp.replaceAll("-", "");
		}
		info.setTmSmp(TmSmp);
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(infovo.getPages(), infovo.getRows(), true);
		List<RoleInfo> listInfo=roleInfo.qryRoleUserEditInfoList(info);
		PageInfo pageInfo = new PageInfo(listInfo);
		map.put("listInfo", listInfo);
		map.put("pageInfo", pageInfo);
		return map;
	}
}
