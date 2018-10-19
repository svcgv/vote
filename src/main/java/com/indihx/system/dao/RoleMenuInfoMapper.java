package com.indihx.system.dao;
import java.util.List;
import com.indihx.system.entity.RoleMenuInfo;

/** 
 * 项目名称：wx_mng_maven 
 * 类名称：RoleMenuInfoMapper 
 * 类描述： 
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月10日 下午4:49:59 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
public interface RoleMenuInfoMapper {
	
     int insertSelective(RoleMenuInfo record);
	  
	 int updateByPrimaryKeySelective(RoleMenuInfo record);
	 
	 List<RoleMenuInfo>  qryRoleMenuInfoInfoAll(RoleMenuInfo record);
	  
	 RoleMenuInfo selectByPrimaryKey(long menuId);
	 
	 int deleteByPrimaryKey(long roleId);
	
}
