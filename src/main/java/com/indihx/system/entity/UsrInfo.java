package com.indihx.system.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
/***
 * 
 * <p>描 述: 用户信息管理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年6月16日</p
 * @author 谢追辉
 */
public class UsrInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long usrId;  //用户ID
    private String usrNo;//用户代码
    private String usrName;//用户姓名
    private String passWord;//登录密码
    private String sex;//性别
    private String birthDate;//出生年月
    private Long mblNo;//手机号码
    private String email;//电子邮件
    private Long operUsr;//创建人
    private String usrSta;//用户状态
    private String licenceCode;//证书编号
	private String certId;//身份证号
	private String tmSmp;//创建日期
	private String strRemark; //备注
	
	private String passwordhash; //密码PASSWORDHASH
	private String sessionId; //会话号
	private String last_active_time;//最后活动时间
	private Long orgNo;//机构类型
    private String orgType; //机构类型
    private String orgName;//机构名称
    private String hpbBaseId;//区ID
	private String streetBaseId;//街道ID
    private String committeeBaseId;//社区ID
	private String roleName;//角色名称
	private String roleId;//角色ID

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPasswordhash() {
		return passwordhash;
	}

	public void setPasswordhash(String passwordhash) {
		this.passwordhash = passwordhash;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Long getUsrId() {
        return usrId;
    }
	
	public String getUsrIdStr(){
		 return String.valueOf(usrId);
	}

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }
    
    public void setUsrId(String usrId) {
        this.usrId = StringUtils.isEmpty(usrId) == false ? new Long(usrId) : null;
    }

    public String getUsrNo() {
        return usrNo;
    }

    public void setUsrNo(String usrNo) {
        this.usrNo = usrNo == null ? null : usrNo.trim();
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName == null ? null : usrName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate == null ? null : birthDate.trim();
    }

    public Long getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(Long orgNo) {
        this.orgNo = orgNo;
    }
    
    public void setOrgNo(String orgNo) {
        this.orgNo = StringUtils.isEmpty(orgNo) == false ? new Long(orgNo) : null;
    }

    public Long getMblNo() {
        return mblNo;
    }

    public void setMblNo(Long mblNo) {
        this.mblNo = mblNo;
    }
    
    public void setMblNo(String mblNo) {
        this.mblNo = StringUtils.isEmpty(mblNo) == false ? new Long(mblNo) : null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getOperUsr() {
        return operUsr;
    }

    public void setOperUsr(Long operUsr) {
        this.operUsr = operUsr;
    }
    
    public void setOperUsr(String operUsr) {
        this.operUsr = StringUtils.isEmpty(operUsr) == false ? new Long(operUsr) : null;
    }

    public String getUsrSta() {
        return usrSta;
    }

    public void setUsrSta(String usrSta) {
        this.usrSta = usrSta == null ? null : usrSta.trim();
    }

    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }
    
    /**
	 * @return the hpbBaseId
	 */
	public String getHpbBaseId() {
		return hpbBaseId;
	}

	/**
	 * @param hpbBaseId the hpbBaseId to set
	 */
	public void setHpbBaseId(String hpbBaseId) {
		this.hpbBaseId = hpbBaseId;
	}
	/**
	 * @return the streetBaseId
	 */
	public String getStreetBaseId() {
		return streetBaseId;
	}

	/**
	 * @param streetBaseId the streetBaseId to set
	 */
	public void setStreetBaseId(String streetBaseId) {
		this.streetBaseId = streetBaseId;
	}

	/**
	 * @return the committeeBaseId
	 */
	public String getCommitteeBaseId() {
		return committeeBaseId;
	}

	/**
	 * @param committeeBaseId the committeeBaseId to set
	 */
	public void setCommitteeBaseId(String committeeBaseId) {
		this.committeeBaseId = committeeBaseId;
	}
}