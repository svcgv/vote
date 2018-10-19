package com.indihx.org.entity;

import java.io.Serializable;

/**
 * 组织机构信息表
 *
 */
public class ORG_INFO implements Serializable{
 
	private static final long serialVersionUID = 1L;
	public String parent_org_no;//上级单位ID
	public String parent_org_name;//上级单位名称
	public String org_no;//单位ID
	public String org_name;//单位名称
	public String org_type;//单位类别
	public String addres;//办公地址
	public String tel_no;//联系电话
	public String oper_usr;//创建人
	public String tm_smp;//创建日期
	public String org_status; //单位状态 /** 0：正常、1：禁用、2：注销  */
	public String link_man;//联系人
	public String email;//EMAIL地址
	public String post_code;//邮政编码
	public String remark;//备注
	
	public String getParent_org_no() {
		return parent_org_no;
	}
	public void setParent_org_no(String parent_org_no) {
		this.parent_org_no = parent_org_no;
	}
	public String getParent_org_name() {
		return parent_org_name;
	}
	public void setParent_org_name(String parent_org_name) {
		this.parent_org_name = parent_org_name;
	}
	public String getOrg_no() {
		return org_no;
	}
	public void setOrg_no(String org_no) {
		this.org_no = org_no;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getOrg_type() {
		return org_type;
	}
	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	public String getTel_no() {
		return tel_no;
	}
	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}
	public String getOper_usr() {
		return oper_usr;
	}
	public void setOper_usr(String oper_usr) {
		this.oper_usr = oper_usr;
	}
	public String getTm_smp() {
		return tm_smp;
	}
	public void setTm_smp(String tm_smp) {
		this.tm_smp = tm_smp;
	}
	public String getOrg_status() {
		return org_status;
	}
	public void setOrg_status(String org_status) {
		this.org_status = org_status;
	}
	public String getLink_man() {
		return link_man;
	}
	public void setLink_man(String link_man) {
		this.link_man = link_man;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
	
}
