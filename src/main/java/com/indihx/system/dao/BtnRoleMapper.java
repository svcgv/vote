package com.indihx.system.dao;

import java.util.List;

import com.indihx.system.entity.BtnRole;
/** 
 * 类描述： 
 * 创建人：严蒙蒙
 * 单位：上海弘智信息科技有限公司
 * 创建时间：2017年7月26日 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
public interface BtnRoleMapper {
	/***
	 * 删除角色按钮信息
	 * @param roleId
	 * @return int>0 success
	 */
    int deleteAll(String roleId);
    /***
	 * 新增角色按钮信息
	 * @param record 按钮角色信息实体
	 * @return int>0 success
	 */
    int insertSelective(BtnRole record);
    /***
	 * 根据主键查询角色按钮信息
	 * @param btnId roleId 联合主键
	 * @return BtnRole 角色按钮实体
	 */
    BtnRole selectByPrimaryKey(String btnId,String roleId);
   
    /***
   	 * 查询所有角色按钮信息
   	 * @param record 按钮实体
   	 * @return int>0 success
   	 */
    List<BtnRole>  qryBtnRoleAll(String roleId);
    

}