/**
 * 
 */
package com.indihx.activiti.service.impl;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indihx.activiti.service.IActivitiUserBiz;
import com.indihx.comm.exception.BusinessException;
import com.indihx.comm.exception.ExceptionUtil;
import com.indihx.system.entity.RoleInfo;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.service.impl.RoleInfoServiceImpl;
import com.indihx.util.Assert;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: AcitivitiUserBizImpl.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年4月23日上午11:55:37</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>AcitivitiUserBizImpl.java</p>
 * <p>用于同步系统的用户至Activiti的用户，目的是实现Activiti对任务分配到：指定人、指定组</p>
 */
@Service
public class AcitivitiUserBizImpl implements IActivitiUserBiz {

	@Autowired
	IdentityService identityService;
	@Autowired
	private RoleInfoServiceImpl roleInfo;
	
	/* (同步系统采集用户至activiti的用户)
	 * @see com.indihx.activiti.service.IActivitiUserBiz#snyActivitiUser(com.indihx.system.entity.UsrInfo, java.util.List, boolean)
	 */
	@Override
	public int snyActivitiUser(UsrInfo user, List<Long> roleIds,
			boolean synToActiviti) {
		try{
			Assert.notNull(user, "用户信息不能为空！");
			Long usrId = user.getUsrId();
			if (synToActiviti) {
				List<org.activiti.engine.identity.User> activitiUsers = identityService.createUserQuery().userId(usrId.toString()).list();
				if (activitiUsers.size() == 1) {
					//更新用户信息
					org.activiti.engine.identity.User activitiUser = activitiUsers.get(0);
					activitiUser.setFirstName(user.getUsrName());
					activitiUser.setLastName("");
					activitiUser.setPassword(user.getPassWord());
					activitiUser.setEmail(user.getEmail());
					identityService.saveUser(activitiUser);
					
					// 删除用户的membership
					List<Group> activitiGroups = identityService.createGroupQuery().groupMember(usrId.toString()).list();
					for (Group group : activitiGroups) {
						identityService.deleteMembership(usrId.toString(), group.getId());
						identityService.deleteGroup(group.getId());
					}

					// 添加membership
					for (Long roleId : roleIds) {
						RoleInfo role = roleInfo.qryRoleInfoById(roleId.toString());
						org.activiti.engine.identity.Group newGroup =identityService.newGroup(roleId.toString());
						newGroup.setName(role.getRoleName());
						newGroup.setId(roleId.toString());
						newGroup.setType(roleId.toString());
						identityService.saveGroup(newGroup);
						identityService.createMembership(usrId.toString(), roleId.toString());
					}
				} else {
					org.activiti.engine.identity.User newUser = identityService.newUser(usrId.toString());
					newUser.setFirstName(user.getUsrName());
					newUser.setLastName("");
					newUser.setPassword(user.getPassWord());
					newUser.setEmail(user.getEmail());
					identityService.saveUser(newUser);

					// 添加membership
					for (Long roleId : roleIds) {
						RoleInfo role = roleInfo.qryRoleInfoById(roleId.toString());
						org.activiti.engine.identity.Group newGroup =identityService.newGroup(roleId.toString());
						newGroup.setName(role.getRoleName());
						newGroup.setId(roleId.toString());
						newGroup.setType(roleId.toString());
						identityService.saveGroup(newGroup);
						identityService.createMembership(usrId.toString(), roleId.toString());
					}
				}

			}
			return 0;
		}catch(Exception e){
			throw new BusinessException("同步用户信息至Activiti失败："+ExceptionUtil.getErrorMsg(e));
		}
	}
}
