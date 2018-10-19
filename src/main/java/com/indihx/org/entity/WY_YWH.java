package com.indihx.org.entity;

import java.io.Serializable;
/**
 * 业主委员会信息表
 */
public class WY_YWH implements Serializable{
	private static final long serialVersionUID = 1L;
	public String ywhid;//业委会信息ID
	public String ywhmc;//业主大会名称
	public String ywhdz;//业主大会地址
	public String hpbid;//所属区ID 
	public String ywhdm;//业委会代码
	public String lxr;//业委会联系人
	public String lxrdh;//业委会联系电话
	public String term;//第几届
	public String rq;//任期
	public String clrq;//成立日期
   	public String bjksrq;//本届任期开始日期
   	public String bjjzrq;//本届任期结束日期
   	public String barq;//业主大会备案日期
   	public String sfkz;//是否刻有公章
   	public String bz;//备注
   
	public String getYwhid() {
		return ywhid;
	}
	public void setYwhid(String ywhid) {
		this.ywhid = ywhid;
	}
	public String getYwhmc() {
		return ywhmc;
	}
	public void setYwhmc(String ywhmc) {
		this.ywhmc = ywhmc;
	}
	public String getYwhdz() {
		return ywhdz;
	}
	public void setYwhdz(String ywhdz) {
		this.ywhdz = ywhdz;
	}
	public String getHpbid() {
		return hpbid;
	}
	public void setHpbid(String hpbid) {
		this.hpbid = hpbid;
	}
	public String getYwhdm() {
		return ywhdm;
	}
	public void setYwhdm(String ywhdm) {
		this.ywhdm = ywhdm;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getLxrdh() {
		return lxrdh;
	}
	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getClrq() {
		return clrq;
	}
	public void setClrq(String clrq) {
		this.clrq = clrq;
	}
	public String getBjksrq() {
		return bjksrq;
	}
	public void setBjksrq(String bjksrq) {
		this.bjksrq = bjksrq;
	}
	public String getBjjzrq() {
		return bjjzrq;
	}
	public void setBjjzrq(String bjjzrq) {
		this.bjjzrq = bjjzrq;
	}
	public String getBarq() {
		return barq;
	}
	public void setBarq(String barq) {
		this.barq = barq;
	}
	public String getSfkz() {
		return sfkz;
	}
	public void setSfkz(String sfkz) {
		this.sfkz = sfkz;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	} 

}
