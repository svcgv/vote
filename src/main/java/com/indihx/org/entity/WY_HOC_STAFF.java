package com.indihx.org.entity;

import java.io.Serializable;

/**
 * 业委会人员信息表
 *
 */
public class WY_HOC_STAFF implements Serializable{
 
   private static final long serialVersionUID = 1L;
   public String ywhryid ;//人员ID
   public String ywhid ;//业委会ID
   public String rylx ;//人员类型
   public String status ;//数据状态
   public String ryxm ;//姓名
   public String ryxb ;//性别
   public String csrq ;//出生日期
   public String zjlx ;//证件类型
   public String zjhm ;//证件号码
   public String whcd ;//文化程度
   public String ryyx ;//邮箱Email
   public String lxdh ;//联系电话
   public String rzrq ;//上任日期
   public String bz ;//备注
	   
	public String getYwhryid() {
		return ywhryid;
	}
	public void setYwhryid(String ywhryid) {
		this.ywhryid = ywhryid;
	}
	
	public String getYwhid() {
		return ywhid;
	}
	
	public void setYwhid(String ywhid) {
		this.ywhid = ywhid;
	}
	public String getRylx() {
		return rylx;
	}
	public void setRylx(String rylx) {
		this.rylx = rylx;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRyxm() {
		return ryxm;
	}
	public void setRyxm(String ryxm) {
		this.ryxm = ryxm;
	}
	public String getRyxb() {
		return ryxb;
	}
	public void setRyxb(String ryxb) {
		this.ryxb = ryxb;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getZjlx() {
		return zjlx;
	}
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getWhcd() {
		return whcd;
	}
	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}
	public String getRyyx() {
		return ryyx;
	}
	public void setRyyx(String ryyx) {
		this.ryyx = ryyx;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getRzrq() {
		return rzrq;
	}
	public void setRzrq(String rzrq) {
		this.rzrq = rzrq;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	} 
	   
}
