package com.indihx.system.service;
import java.util.List;
import java.util.Map;

import com.indihx.system.entity.RoleInfo;
import com.indihx.system.vo.RoleInfoVo;
import com.indihx.system.vo.RoleMenuInfoVo;
/** 
 * 项目名称：wx_mng_maven 
 * 类名称：IRoleInfoService 
 * 类描述： 
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年4月9日 下午1:17:12 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
public interface IRoleInfoService {
	
public Map<String,Object> qryRoleInfoList(RoleInfoVo infovo);

public boolean addRoleInfo(RoleInfoVo infoVo); 

public RoleInfo qryRoleInfoById(String roleId);

public boolean  updRoleInfoById(RoleInfoVo infoVo);

public boolean deleteRoleInfo(RoleInfoVo infoVo);

public List<RoleInfo> qryRoleByRoleId(String roleId);

Map<String, Object> qryMenuJson(String roleId);

public boolean saveRoleInfo(RoleMenuInfoVo infoVo);

Map<String, Object> qryRoleUserEditInfoList(RoleInfoVo infovo); 
}
