package com.indihx.system.dao;
import java.util.List;
import com.indihx.system.entity.RoleInfo;
/** 
 * 项目名称：wx_mng_maven 
 * 类名称：RoleInfoMapper 
 * 类描述： 
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月9日 下午12:59:39 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
public interface RoleInfoMapper {
	  int deleteByPrimaryKey(long role_id);
	  
	  int insertSelective(RoleInfo record);
	  
	  int updateByPrimaryKeySelective(RoleInfo record);
	  
	  RoleInfo selectByPrimaryKey(long role_id);
	  
	  List<RoleInfo>  qryRoleInfoInfoAll(RoleInfo record);

	List<RoleInfo> qryRoleUserEditInfoList(RoleInfo info);
	  
}
