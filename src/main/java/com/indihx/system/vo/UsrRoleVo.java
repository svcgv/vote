package com.indihx.system.vo;

/***
 * 
 * <p>
 * 描 述: 用户角色Vo信息
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年4月20日
 * </p>
 * 
 * @author 谢追辉
 */
public class UsrRoleVo extends BaseVo {

	private String roleId; // 角色ID

	private String roleName; // 角色名称

	private String userId; // 用户ID

	private String isFage; // 当前角色用户是否选中

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsFage() {
		return isFage;
	}

	public void setIsFage(String isFage) {
		this.isFage = isFage;
	}

}
