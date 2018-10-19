package com.indihx.activiti.dao;

import java.util.List;

import com.indihx.activiti.entity.ActRoleNode;
import com.indihx.system.entity.UsrInfo;

public interface ActRoleNodeMapper {
	
	/***
	 *  清理
	 * @param record
	 * @return
	 */
    int deleteByPrimaryKey(ActRoleNode record);

    int insert(ActRoleNode record);

    int insertSelective(ActRoleNode record);
    
    /***
     * 根据当前条件，查询当前节点 信息
     * @param record 实体
     * @return
     */
    List<ActRoleNode> selectByPrimaryKey(ActRoleNode record);

    int updateByPrimaryKeySelective(ActRoleNode record);

    int updateByPrimaryKey(ActRoleNode record);
    
    /***
     * 根据当前节点查询用户信息
     * @param nodeId 节点ID
     * @param appType 流程类型
     * @param orgNo 机构ID
     * @return 用户列表
     */
    List<UsrInfo> queryUserByNode(String nodeId,String appType,String orgNo);
}