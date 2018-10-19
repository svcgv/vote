package com.indihx.system.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.indihx.system.entity.UsrRole;
import com.indihx.system.entity.UsrRoleKey;
/***
 * 
 * <p>描 述: 用户角色管理DAO</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年4月20日</p>
 * @author 谢追辉
 */
@MapperScan
public interface UsrRoleMapper {
	
	/**
	 * 根据用户ID删除角色
	 * @param key  用户ID
	 * @return  成功or失败
	 */
    int deleteByPrimaryKey(UsrRoleKey key);

    /***
     * 新增用户角色信息
     * @param record bean
     * @return 成功or失败
     */
    int insertSelective(UsrRole record);
    
    /***
     * 查询用户角色信息
     * @param key 
     * @return  返回
     */
    List<UsrRole> selectByPrimaryKey(UsrRoleKey key);

}