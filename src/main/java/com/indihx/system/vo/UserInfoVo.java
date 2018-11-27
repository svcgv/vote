package com.indihx.system.vo;

/***
 * 
 * <p>描 述: VO字段尽量定义成String，特别是在ajax请求的时候</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年2月20日</p>
 * @author 谢追辉
 */
public class UserInfoVo extends BaseVo {
	private String usrName;  //用户姓名
	private String mblNo;   //手机号码
	private String usrId;  //用户ID
	private String userId;  //删除存储用户ID
	private String loginName;  //登录名
	private String oldLoginName;  //登录名
	private String passWord;  //密码
	private String usrNo;   //用户编号
	private String email;    //邮箱
	private String sex;    //性别
	private String birthDate;   //出生日期
	private String orgNo;    //所属机构
	private String orgType; //机构类型
	private String orgName;//机构名称
	private String deptId;//部门名称
	private String deptType;//部门类型
	private String passwordhash; //密码PASSWORDHASH
	private String passwd; //密码
	private String unlockPasswd; //解锁密码
	private String sessionId; //会话号
	private String last_active_time;//最后活动时间
	private String licenceCode;//证书编号
	private String certId;//身份证号
	private String strRemark; //备注
	private String usrSta;//用户状态
	private String roleName;
	private String operUsr;
	private String roleId;
	


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOperUsr() {
		return operUsr;
	}

	public void setOperUsr(String operUsr) {
		this.operUsr = operUsr;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsrSta() {
		return usrSta;
	}

	public void setUsrSta(String usrSta) {
		this.usrSta = usrSta;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getPasswordhash() {
		return passwordhash;
	}

	public void setPasswordhash(String passwordhash) {
		this.passwordhash = passwordhash;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUnlockPasswd() {
		return unlockPasswd;
	}

	public void setUnlockPasswd(String unlockPasswd) {
		this.unlockPasswd = unlockPasswd;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLast_active_time() {
		return last_active_time;
	}

	public void setLast_active_time(String last_active_time) {
		this.last_active_time = last_active_time;
	}
	
	public String getLicenceCode() {
		return licenceCode;
	}

	public void setLicenceCode(String licenceCode) {
		this.licenceCode = licenceCode;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public String getStrRemark() {
		return strRemark;
	}

	public void setStrRemark(String strRemark) {
		this.strRemark = strRemark;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	
	public String getUsrNo() {
		return usrNo;
	}
	public void setUsrNo(String usrNo) {
		this.usrNo = usrNo;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId ;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getMblNo() {
		return mblNo;
	}
	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getOldLoginName() {
		return oldLoginName;
	}

	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}
	
	
}
