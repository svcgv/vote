package com.indihx.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.indihx.system.entity.OrgInfo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.vo.RoleInfoVo;
import com.indihx.system.vo.UserInfoVo;
@MapperScan
public interface UsrInfoMapper {
    int deleteByPrimaryKey(Long usrId);

    int insert(UsrInfo record);

    int insertSelective(UsrInfo record);
    //查询该登录名的用户，验证登录名是否已存在
    UsrInfo qryUsrInfoByLoginName(Map<String, Object> map); 

    UsrInfo selectByPrimaryKey(Long usrId);

    int updateByPrimaryKeySelective(UsrInfo record);

    int updateByPrimaryKey(UsrInfo record);
    
    List<UsrInfo>  qryUserInfoAll(UsrInfo usrInfo);
    
    List<UsrInfo> qryUsrInfoByRoleId(String roleId);

	UsrInfo editUsrInfoById(Long usrId);

	List<UsrInfo> openAqzeroUserListInfo(String id, String pk_table, @Param("user")UsrInfo info);

	List<RoleInfoVo> qryRoleInfoAll(RoleInfoVo roleInfoVo);

	int insertUserRole(String string, String usrId, String time);

	int insertAqBaseRel(String relId, String usrId, String pk_id,String pk_table);

	int updateUserRole(String userIdList, String string, String datetime);

	int closeStaOrgInfo(Long long1);

	int openStaOrgInfo(Long long1);

	OrgInfo selectOrgNameByPrimaryKey(Long long1);
	
	int updateLoginUser(UsrInfo record);
	
	int modifyActiveTime(UsrInfo record);
	
	void clearUserSession(UsrInfo record);

	/**
	 * @param infoVo
	 * @return
	 */
	UsrInfo checkUserInfo(UserInfoVo infoVo);

	/**
	 * @param usrId
	 * @return
	 */
	String getUserSession(String usrId);
}