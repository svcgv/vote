package com.indihx.system.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/** 
 * 项目名称：ntew 
 * 类名称：AgentOrgInfo 
 * 类描述： 
 * 创建人：张顺顺
 * 作者单位：上海弘智信息科技有限公司
 * 创建时间：2017年5月15日 下午3:42:57 
 * @Copyright: 2017 All rights reserved.
 * @version 1.0
 */
public class AgentOrgInfo implements Serializable{
	private Long agentId;
	private String agentName;
	private String agentCode;
	private String agentAddre;
	private String agentDist;
	private String agentMail;
	private String fixedTel;
	private String lawName;
	private String lawTel;
	private String contactName;
	private String contactTel;
	private String agentDesc;
	private String createName;
	private String tmSmp;

	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = StringUtils.isEmpty(agentId) == false ? new Long(agentId) : null;;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentAddre() {
		return agentAddre;
	}
	public void setAgentAddre(String agentAddre) {
		this.agentAddre = agentAddre;
	}
	public String getAgentDist() {
		return agentDist;
	}
	public void setAgentDist(String agentDist) {
		this.agentDist = agentDist;
	}
	public String getAgentMail() {
		return agentMail;
	}
	public void setAgentMail(String agentMail) {
		this.agentMail = agentMail;
	}
	public String getFixedTel() {
		return fixedTel;
	}
	public void setFixedTel(String fixedTel) {
		this.fixedTel = fixedTel;
	}
	public String getLawName() {
		return lawName;
	}
	public void setLawName(String lawName) {
		this.lawName = lawName;
	}
	public String getLawTel() {
		return lawTel;
	}
	public void setLawTel(String lawTel) {
		this.lawTel = lawTel;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getAgentDesc() {
		return agentDesc;
	}
	public void setAgentDesc(String agentDesc) {
		this.agentDesc = agentDesc;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getTmSmp() {
		return tmSmp;
	}
	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
	}
	

}
