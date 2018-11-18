package com.indihx.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.AbstractBaseService;
import com.indihx.activiti.service.impl.AcitivitiUserBizImpl;
import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.comm.util.BasicParameterInfo;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.datamng.entity.Wy_Hpb;
import com.indihx.system.dao.MenuInfoMapper;
import com.indihx.system.dao.OrgInfoMapper;
import com.indihx.system.dao.UsrInfoMapper;
import com.indihx.system.dao.UsrMenuMapper;
import com.indihx.system.dao.UsrRoleMapper;
import com.indihx.system.entity.MenuInfo;
import com.indihx.system.entity.MenuQryEntity;
import com.indihx.system.entity.OrgInfo;
import com.indihx.system.entity.RoleInfo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.entity.UsrMenu;
import com.indihx.system.entity.UsrRole;
import com.indihx.system.entity.UsrRoleKey;
import com.indihx.system.service.IUsrInfoService;
import com.indihx.system.vo.OrgInfoVo;
import com.indihx.system.vo.RoleInfoVo;
import com.indihx.system.vo.UserInfoVo;
import com.indihx.system.vo.UsrRoleVo;
import com.indihx.util.Assert;
import com.indihx.util.ConstantStatic;
import com.indihx.util.EntityVoConverter;

/**
 * 
 * <p>
 * 描 述: 用户信息管理
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年2月16日
 * </p>
 * 
 * @author 谢追辉
 */
@Service
public class UsrInfoServiceImpl  extends AbstractBaseService implements IUsrInfoService{
	private static Logger logger = LoggerFactory.getLogger(UsrInfoServiceImpl.class);

	@Resource
	private UsrInfoMapper infoMapper;  //用户管理
	@Resource
	private CommUtilMapper mapper;  //公共
	@Resource
	private UsrRoleMapper usrRoleMappeer;  //用户角色
	@Resource
	private RoleInfoServiceImpl roleInfoService;  //角色
	
	@Resource
	private OrgInfoServiceImpl orgInfoService; //机构
	
	@Resource
	private MenuInfoMapper menuMapper; //菜单
	@Resource
	private UsrMenuMapper usrmenuMapper; //用户菜单
	@Resource
	private OrgInfoMapper orgMapper; //开发企业
	@Autowired
	private AcitivitiUserBizImpl activiti;
	
	@Override
	public Map<String, Object> qryUsrInfoList(UserInfoVo infoVo) {
		UsrInfo usrInfo = new UsrInfo();
		usrInfo.setUsrName(infoVo.getUsrName());
		usrInfo.setUsrId(ConstantStatic.strToLong(infoVo.getUserId()));
		usrInfo.setOrgType(infoVo.getOrgType());
		Map<String, Object> map = new HashMap<String, Object>();
		// 第几页、每页显示条数，是否汇总
		PageHelper.startPage(infoVo.getPages(), infoVo.getRows(), true);
		List<UsrInfo> listInfo = infoMapper.qryUserInfoAll(usrInfo);
		// PageHelper.clearPage();
		List<UserInfoVo> listVo = new ArrayList<UserInfoVo>();
		for (UsrInfo ruleInfo2 : listInfo) {
			UserInfoVo infoVo1 = new UserInfoVo();
			EntityVoConverter.Convert(ruleInfo2, infoVo1);
			infoVo1.setOrgType(BasicParameterInfo.getCodeVal("ORG_TYPE", ruleInfo2.getOrgType()));
			infoVo1.setUsrSta(BasicParameterInfo.getCodeVal("USER_STATUS", ruleInfo2.getUsrSta()));
			listVo.add(infoVo1);
		}
		PageInfo<UsrInfo> pageInfo = new PageInfo<UsrInfo>(listInfo);
		map.put("listInfo", listVo);
		/*map.put("listInfo", listInfo);*/
		map.put("pageInfo", pageInfo);
		return map;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean addUsrInfo(UserInfoVo infoVo) {
		
		final OrgInfoVo info = orgInfoService.qryOrginfoList(infoVo.getOrgNo());
		
		UsrInfo usrInfo = new UsrInfo();
		EntityVoConverter.Convert(infoVo, usrInfo);
		String usrId = mapper.getUserId();
		usrInfo.setUsrSta("0"); //有效
		usrInfo.setTmSmp(DateUtil.getSysDate());
		usrInfo.setPassWord("111111");
		usrInfo.setUsrId(new Long(usrId));
		usrInfo.setUsrName(usrInfo.getUsrName());
		usrInfo.setMblNo(usrInfo.getMblNo());
		usrInfo.setEmail(usrInfo.getEmail());
		//usrInfo.setOrgType(usrInfo.getOrgType());
		usrInfo.setOrgType(info.getOrgType());
		usrInfo.setOrgNo(usrInfo.getOrgNo());
		usrInfo.setOperUsr(infoVo.getOperUsr());
		usrInfo.setSex(usrInfo.getSex());
		usrInfo.setBirthDate(usrInfo.getBirthDate());
		usrInfo.setLicenceCode(usrInfo.getLicenceCode());
		usrInfo.setCertId(usrInfo.getCertId());
		usrInfo.setStrRemark(usrInfo.getStrRemark());
		int num = infoMapper.insertSelective(usrInfo);
		
		if (num > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public UsrInfo qryUsrInfoById(UserInfoVo infoVo) {
		String userId = infoVo.getUsrId().replaceAll(",", "").trim();
		return infoMapper.selectByPrimaryKey(new Long(userId));
	}
	@Override
	public OrgInfo qryUsrOrgNameInfoById(String usrId) {
		return infoMapper.selectOrgNameByPrimaryKey(new Long(usrId));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean updUserInfoById(UserInfoVo infoVo) {
		try {
			
			final OrgInfoVo info = orgInfoService.qryOrginfoList(infoVo.getOrgNo());
			Assert.hasText(infoVo.getUsrId(), "用户Id不能为空!");
			UsrInfo usrInfo = new UsrInfo();
			EntityVoConverter.Convert(infoVo, usrInfo);
			String userIdList = infoVo.getUsrId();
			usrInfo.setUsrId(userIdList);
			usrInfo.setTmSmp(DateUtil.getSysDate());
			usrInfo.setPassWord(ObjectUtil.isEmpty(infoVo.getPassWord())?"111111":infoVo.getPassWord());
			usrInfo.setUsrName(usrInfo.getUsrName());
			usrInfo.setOrgType(info.getOrgType());
			usrInfo.setEmail(usrInfo.getEmail());
			usrInfo.setMblNo(usrInfo.getMblNo());
			usrInfo.setOrgNo(usrInfo.getOrgNo());
			usrInfo.setSex(usrInfo.getSex());
			usrInfo.setBirthDate(usrInfo.getBirthDate());
			usrInfo.setLicenceCode(usrInfo.getLicenceCode());
			usrInfo.setCertId(usrInfo.getCertId());
			usrInfo.setStrRemark(usrInfo.getStrRemark());
			int cnt = infoMapper.updateByPrimaryKeySelective(usrInfo);
			
			UsrRoleKey userRole = new UsrRoleKey();
			userRole.setUsrId(Long.valueOf(userIdList));
			List<UsrRole> listRoles=usrRoleMappeer.selectByPrimaryKey(userRole);
			List<Long> roleList =  null;
			for (int i = 0; i < listRoles.size(); i++) {
				roleList =  new ArrayList<Long>();
				roleList.add(listRoles.get(i).getRoleId());
			}
			
			//同步用户信息
			activiti.snyActivitiUser(usrInfo, roleList, false);
			if (cnt > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.info("系统异常！！");
			//使用trycatch时，必须手动加上事务回滚，spring默认只会回滚runtimeException异常才回回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();      
			throw new BusinessException("更新用户信息失败!");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteUsrInfo(UserInfoVo infoVo) {
		String userIdList = infoVo.getUsrId();
		int num = infoMapper.deleteByPrimaryKey(new Long(userIdList));
		if(num<1){
			return false;
		}
		return true;
	}

	@Override
	public List<UsrInfo> qryUsrByRoleId(String roleId) {
		return infoMapper.qryUsrInfoByRoleId(roleId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean saveUserRole(String[] roleList, String strUserId) {
		long userId = new Long(strUserId);
		try {
			//先删除用户的所有角色
			UsrRole userRole = new UsrRole();
			userRole.setUsrId(userId);
			usrRoleMappeer.deleteByPrimaryKey(userRole);
			
			List<Long> list = new ArrayList<Long>();
			
			//为用户添加所有选取的角色
			for (int i = 0; i < roleList.length; i++) {
				long roleId = new Long(roleList[i]);
				userRole.setRoleId(roleId);
				userRole.setTmSmp(DateUtil.getSysDate()+DateUtil.getSysTime());
				usrRoleMappeer.insertSelective(userRole);
				
				list.add(roleId);
			}
			UsrInfo user = infoMapper.selectByPrimaryKey(Long.valueOf(strUserId));
			//同步用户信息
			activiti.snyActivitiUser(user, list, false);
		} catch (Exception e) {
			logger.info("系统异常！！");
			//使用trycatch时，必须手动加上事务回滚，spring默认只会回滚runtimeException异常才回回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			throw new BusinessException("设置用户角色信息失败!");
		}
		return true;
	}
	
	/***
	 * 查询当前用户的角色列表
	 * @param roles 
	 * @param usrInfo  用户实体
	 * @return  返回map
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> qryUserRole(String usrId, String roles){
		List<UsrRoleVo> listVo = new ArrayList<UsrRoleVo>();
		//查询所有角色信息
		RoleInfoVo infoVo = new RoleInfoVo();
		infoVo.setRows("99999");
		if("AQ_STREET".equals(roles)){
			infoVo.setRoleId("210");
		}
		if("AQ_REGION".equals(roles)){
			infoVo.setRoleId("200");
		}
		if("AQ_DIRECTORY_ORG".equals(roles)){
			infoVo.setRoleId("401");
		}
		Map<String, Object> roleMap = roleInfoService.qryRoleUserEditInfoList(infoVo);
		//查询所有集合
		UsrRole role = new UsrRole();
		Map<String, Object> map = new HashMap<String, Object>();
		role.setUsrId(new Long(usrId));
		List<UsrRole> list = usrRoleMappeer.selectByPrimaryKey(role);  //查询用户拥有的角色
		List<RoleInfo> roleInfos = (List<RoleInfo>) roleMap.get("listInfo");  //得到所有角色
		for (RoleInfo roleInfo : roleInfos) {
			UsrRoleVo usrRoleVo = new UsrRoleVo();
			usrRoleVo.setRoleName(roleInfo.getRoleName());
			usrRoleVo.setRoleId(roleInfo.getRoleId().toString());
			for (UsrRole usrRole : list) {
				//判断当前用户的角色
				if(roleInfo.getRoleId().equals(usrRole.getRoleId())){
					usrRoleVo.setIsSelect("Y");
					usrRoleVo.setUserId(usrId);
				}
			}
			listVo.add(usrRoleVo);
		}
		map.put("listInfo", listVo);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> qryUserRole(String usrId, String roles,String roleName) {
		List<UsrRoleVo> listVo = new ArrayList<UsrRoleVo>();
		//查询所有角色信息
		RoleInfoVo infoVo = new RoleInfoVo();
		infoVo.setRows("99999");
		if("AQ_STREET".equals(roles)){
			infoVo.setRoleId("210");
		}
		if("AQ_REGION".equals(roles)){
			infoVo.setRoleId("200");
		}
		if("AQ_DIRECTORY_ORG".equals(roles)){
			infoVo.setRoleId("401");
		}
		infoVo.setRoleName(roleName);
		Map<String, Object> roleMap = roleInfoService.qryRoleUserEditInfoList(infoVo);
		//查询所有集合
		UsrRole role = new UsrRole();
		role.setUsrId(new Long(usrId));
		Map<String, Object> map = new HashMap<String, Object>();
		List<UsrRole> list = usrRoleMappeer.selectByPrimaryKey(role);  //查询用户拥有的角色
		List<RoleInfo> roleInfos = (List<RoleInfo>) roleMap.get("listInfo");  //得到所有角色
		for (RoleInfo roleInfo : roleInfos) {
			UsrRoleVo usrRoleVo = new UsrRoleVo();
			usrRoleVo.setRoleName(roleInfo.getRoleName());
			usrRoleVo.setRoleId(roleInfo.getRoleId().toString());
			for (UsrRole usrRole : list) {
				//判断当前用户的角色
				if(roleInfo.getRoleId().equals(usrRole.getRoleId())){
					usrRoleVo.setIsSelect("Y");
					usrRoleVo.setUserId(usrId);
				}
			}
			listVo.add(usrRoleVo);
		}
		map.put("listInfo", listVo);
		return map;
	}
	
	@Override
	public Map<String, Object> qryMenuJson(String usrId){
		//查询当前用户已有的菜单信息
		UsrMenu mUsrMenu = new UsrMenu();
		mUsrMenu.setUsrId(new Long(usrId));
		List<UsrMenu> usrList = usrmenuMapper.selectByPrimaryKey(mUsrMenu);
		List<Long> listUsrMenuId = new ArrayList<Long>();
		for (UsrMenu usrMenu : usrList) {
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
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean saveUserMenu(String[] menuList, String strUserId) {
		long userId = new Long(strUserId);
		try {
			//先删除用户的所有角色
			UsrMenu usrMenu = new UsrMenu();
			usrMenu.setUsrId(userId);
			usrmenuMapper.deleteByPrimaryKey(usrMenu);
			//为用户添加所有选取的角色
			for (int i = 0; i < menuList.length; i++) {
				long menuId = new Long(menuList[i]);
				usrMenu.setMenuId(menuId);
				usrMenu.setTmSmp(DateUtil.getSysDate()+DateUtil.getSysTime());
				usrmenuMapper.insertSelective(usrMenu);
			}
		} catch (Exception e) {
			logger.info("系统异常！！");
			e.printStackTrace();
			//使用trycatch时，必须手动加上事务回滚，spring默认只会回滚runtimeException异常才回回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			return false;
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> qryOrgListByType(String orgType,String orgName) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if ("01".equals(orgType)) { //市局
			List<Wy_Hpb> reionList = BasicParameterInfo.getRegionMap("00");
			for (Wy_Hpb adminRegion : reionList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("orgId", adminRegion.getHpbid());
				map.put("orgName", adminRegion.getHpbmc());
				list.add(map);
			}
		}else if ("02".equals(orgType)) {  //区县
			List<Wy_Hpb> reionList = BasicParameterInfo.getRegionMap("01");
			for (Wy_Hpb adminRegion : reionList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("orgId", adminRegion.getHpbid());
				map.put("orgName", adminRegion.getHpbmc());
				list.add(map);
			}
		}else if ("03".equals(orgType)) { //开发商
			
			
		}else if ("04".equals(orgType)) { //物业服务企业
		
		}else{
			
		}
		return list;
	}

	public Map<String, Object> openAqzeroUserListInfo(String id,String pk_table, UserInfoVo infoVo) {
		UsrInfo info = new UsrInfo();
		EntityVoConverter.Convert(infoVo, info);
		Map<String, Object> map = new HashMap<String, Object>();
		// 第几页、每页显示条数，是否汇总
		PageHelper.startPage(infoVo.getPages(), infoVo.getRows());
		List<UsrInfo> listInfo = infoMapper.openAqzeroUserListInfo(id,pk_table,info);
		PageInfo<UsrInfo> pageInfo = new PageInfo<UsrInfo>(listInfo);
		List<UserInfoVo> listVo = new ArrayList<UserInfoVo>();
		for (UsrInfo usrInfo : listInfo) {
			UserInfoVo infoVo1 = new UserInfoVo();
			EntityVoConverter.Convert(usrInfo, infoVo1);
			infoVo1.setUsrSta(BasicParameterInfo.getCodeVal("USER_STATUS", usrInfo.getUsrSta()));
			listVo.add(infoVo1);
		}
		map.put("listInfo", listVo);
		map.put("pageInfo", pageInfo);
		return map;
	}
	@Override
	public Map<String, Object> qryOrgListByRoleId(String[] org_type, OrgInfoVo infoVo) {
		OrgInfo info =new OrgInfo();
		EntityVoConverter.Convert(infoVo, info);
		Map<String, Object> map = new HashMap<String, Object>();
		// 第几页、每页显示条数，是否汇总
		PageHelper.startPage(infoVo.getPages(), infoVo.getRows(), true);
		List<OrgInfo> listInfo = orgMapper.qryOrgInfoAll(org_type,info);
		PageInfo<OrgInfo> pageInfo = new PageInfo<OrgInfo>(listInfo);
		map.put("listInfo", listInfo);
		map.put("pageInfo", pageInfo);
		return map;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean addSetUserSave(UserInfoVo infoVo, String pk_id, String pk_table) {
		
		final OrgInfoVo info = orgInfoService.qryOrginfoList(infoVo.getOrgNo());
		
		UsrInfo usrInfo = new UsrInfo();
		EntityVoConverter.Convert(infoVo, usrInfo);
		String usrId = mapper.getUserId();
		usrInfo.setUsrSta("0"); //有效
		usrInfo.setTmSmp(DateUtil.getSysDate());
		usrInfo.setPassWord("111111");
		usrInfo.setUsrId(new Long(usrId));
		usrInfo.setUsrName(usrInfo.getUsrName());
		usrInfo.setMblNo(usrInfo.getMblNo());
		usrInfo.setEmail(usrInfo.getEmail());
		usrInfo.setOrgType(info.getOrgType());
		usrInfo.setOrgNo(infoVo.getOrgNo());
		usrInfo.setOperUsr(infoVo.getOperUsr());
		usrInfo.setSex(usrInfo.getSex());
		usrInfo.setBirthDate(usrInfo.getBirthDate());
		usrInfo.setLicenceCode(usrInfo.getLicenceCode());
		usrInfo.setCertId(usrInfo.getCertId());
		usrInfo.setStrRemark(usrInfo.getStrRemark());
		int num = infoMapper.insertSelective(usrInfo);
		String relId = mapper.getOrgNo();
		int num2 = infoMapper.insertAqBaseRel(relId,usrId,pk_id,pk_table);
		if ((num > 0) && (num2>0)) {
			return true;
		}
		return false;
	}
	/**
		   * 启用操作
		   * @param infovo
		   * @return
		   */
		@Override
		public boolean openStaUsrInfo(UserInfoVo infovo) {
			  String usrId = infovo.getUsrId();
				int num = infoMapper.openStaOrgInfo(new Long(usrId));
				if(num<1){
					return false;
				}
			return true;
		}
		 /**
		   * 关闭操作
		   * @param infovo
		   * @return
		   */
		@Override
		public boolean closeStaUsrInfo(UserInfoVo infovo) {
			String usrId = infovo.getUsrId();
				int num = infoMapper.closeStaOrgInfo(new Long(usrId));
				if(num<1){
					return false;
				}
			return true;
		}
		
		/**
		 * 设置用户信息
		 * @param infoVo
		 * @return
		 */
		@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
		public boolean setUserInfo(UserInfoVo infoVo,Object pwd) throws BusinessException{
			try {
				Assert.hasText(infoVo.getUsrId(), "用户Id不能为空!");
				Assert.hasText(infoVo.getPassWord(), "用户原密码不能为空!");
				Assert.notNull(pwd, "用户新密码不能为空!");
				UsrInfo validUser = infoMapper.checkUserInfo(infoVo);
				if(ObjectUtil.isEmpty(validUser)||ObjectUtil.isEmpty(validUser.getUsrId())){
					throw new BusinessException("用户输入原密码不正确!");
				}
				UsrInfo usrInfo = new UsrInfo();
				EntityVoConverter.Convert(infoVo, usrInfo);
				String userIdList = infoVo.getUsrId();
				usrInfo.setUsrId(userIdList);
				usrInfo.setTmSmp(DateUtil.getSysDate());
				usrInfo.setPassWord(pwd.toString());
				usrInfo.setUsrName(usrInfo.getUsrName());
				usrInfo.setEmail(usrInfo.getEmail());
				usrInfo.setMblNo(usrInfo.getMblNo());
				usrInfo.setOrgNo(usrInfo.getOrgNo());
				usrInfo.setSex(usrInfo.getSex());
				usrInfo.setBirthDate(usrInfo.getBirthDate());
				usrInfo.setLicenceCode(usrInfo.getLicenceCode());
				usrInfo.setCertId(usrInfo.getCertId());
				usrInfo.setStrRemark(usrInfo.getStrRemark());
				int cnt = infoMapper.updateByPrimaryKeySelective(usrInfo);
				if (cnt > 0) {
					return true;
				}
				return false;
			} catch (Exception e) {
				throw new BusinessException("设置用户资料失败："+ExceptionUtil.getErrorMsg(e));
			}
		}	
		
		/* (获取用户session)
		 * @see com.indihx.system.service.IUsrInfoService#getUserSession(java.lang.String)
		 */
		@Override
		public String getUserSession(Long usrId) {
			try {
				Assert.notNull(usrId, "用户登录session已失效.");
				String session =infoMapper.getUserSession(usrId.toString());
				return session;
			} catch (Exception e) {
				throw new BusinessException("清理用户session失败："+ExceptionUtil.getErrorMsg(e));
			}
		}
		
		/* (更新用户最后活动时间)
		 * @see com.indihx.system.service.IUsrInfoService#modifyAcitveTime(java.lang.Long)
		 */
		@Override
		@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
		public void modifyAcitveTime(Long usrId) {
			try {
				Assert.notNull(usrId, "用户登录session已失效.");
				UsrInfo record = new UsrInfo();
				record.setUsrId(usrId);
				record.setLast_active_time(DateUtil.getCurrentTimeMill());
				infoMapper.modifyActiveTime(record);
			} catch (Exception e) {
				throw new BusinessException("清理用户session失败："+ExceptionUtil.getErrorMsg(e));
			}
		}	
}
