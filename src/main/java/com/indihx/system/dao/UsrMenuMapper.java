package com.indihx.system.dao;

import java.util.List;

import com.indihx.system.entity.UsrMenu;
import com.indihx.system.entity.UsrMenuKey;
/***
 * 
 * <p>描 述: 用户菜单管理DAO</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年4月24日</p>
 * @author 谢追辉
 */
public interface UsrMenuMapper {
	/***
	 * 删除用户菜单
	 * @param key 根据实体的值来判断
	 * @return  
	 */
    int deleteByPrimaryKey(UsrMenuKey key);
    
    /***
     * 新增用户菜单信息
     * @param record  实体
     * @return
     */
    int insertSelective(UsrMenu record);
    
    /***
     * 查询用户菜单，根据用户ID或者菜单ID
     * @param key
     * @return
     */
    List<UsrMenu> selectByPrimaryKey(UsrMenuKey key);


}