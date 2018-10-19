package com.indihx.system.service;

import java.util.List;
import java.util.Map;

import com.indihx.system.entity.OrgInfo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.vo.OrgInfoVo;
import com.indihx.system.vo.UserInfoVo;

/***
 * 
 * <p>描 述: 用户信息管理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年3月3日</p>
 * @author 谢追辉
 */
public interface IUsrInfoService {
	
	/**
	 * 用户信息分页查询
	 * 
	 * @param pagesInfo
	 * @return
	 */
	public  Map<String, Object> qryUsrInfoList(UserInfoVo infoVo);
	 
	 /***
		 * 用户信息新增
		 * 
		 * @param usrInfo
		 *            用户实体
		 * @return true增加成功 否则失败
		 */
	
	boolean addUsrInfo(UserInfoVo infoVo);
	 
	 /***
		 * 查询用户信息genjId
		 * 
		 * @param infoVo
		 * @return
		 */
	public UsrInfo qryUsrInfoById(UserInfoVo infoVo);
	 
	 /***
		 * 修改
		 * 
		 * @param infoVo
		 * @return
		 */
	boolean updUserInfoById(UserInfoVo infoVo);
	 
	 /***
		 * 删除
		 * 
		 * @param infoVo
		 * @return
		 */
	 public boolean deleteUsrInfo(UserInfoVo infoVo);
	 
	 /***
	  * 根据角色Id 查询用户信息
	  * @param roleId
	  * @return
	  */
	 public List<UsrInfo> qryUsrByRoleId(String roleId);
	 
	 /***
	  * 保存用户角色关系
	  * @param roleList 角色ID集合
	  * @param userId  用户ID
	  * @return 成功or失败
	  */
	 boolean saveUserRole(String[] roleList,String userId);
	 
	 /***
	  * 查询用户菜单列表信息
	  * @param usrId  用户ID
	  * @return
	  */
	 Map<String, Object> qryMenuJson(String usrId);
	 
	 /***
	  * 用户菜单新增
	  * @param menuList  菜单集合
	  * @param strUserId  用户信息
	  * @return  成功or失败
	  */
	 boolean saveUserMenu(String[] menuList, String strUserId);
	 
	 /***
	  * 根据机构类型查询机构集合信息
	  * @param orgType 机构类型
	  * @return 机构集合
	  */
	 List<Map<String, Object>> qryOrgListByType(String orgType,String orgName);

	boolean addSetUserSave(UserInfoVo infoVo, String pk_id, String pk_table);

	Map<String, Object> qryOrgListByRoleId(String[] org_type, OrgInfoVo infoVo);

	boolean openStaUsrInfo(UserInfoVo infovo);

	boolean closeStaUsrInfo(UserInfoVo infovo);

	OrgInfo qryUsrOrgNameInfoById(String usrId);

	boolean setUserInfo(UserInfoVo infoVo,Object pwd);
	
	public String getUserSession(Long usrId);

	public void modifyAcitveTime(Long usrId);
}
