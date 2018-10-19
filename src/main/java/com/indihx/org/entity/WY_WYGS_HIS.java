package com.indihx.org.entity;

import java.io.Serializable;

/**
 * 物业公司历史表信息
 */
public class WY_WYGS_HIS implements Serializable {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String task_id;//工作流任务ID
	private String app_id;//流程ID
	private String wygsid;//物业公司ID
	private String sjzt;//数据状态
	private String localflag;//是否本地注册
	private String hpbid;//注册区
	private String wygsmc;//物业公司名称
	private String wygsdz;//物业公司地址
	private String certflag;//是否三证合一
	private String shxydm;//社会信用代码
	private String yyzzbh;//营业执照注册号
	private String zczzyxq;//注册证照有效期
	private String gslx;//公司类型
	private String zczb;//注册资本
	private String clrq;//成立日期
	private String wyglsj;//从物业管理活动时间
	private String bgdz;//办公地址
	private String zcdz;//注册地址
	private String zzdj;//资质等级
	private String zzbh;//资质编号	
	private String qydh;//企业电话
	private String qycz;//企业传真
	private String qyyb;//企业邮编
	private String frdb;//法人代表
	private String frsj;//法人手机
	private String qylxr;//企业联系人
	private String lxrdh;//联系人电话
 	private String qyyx ;//企业邮箱
 	private String qygk;//企业概况
 	private String zyfw;//经营范围
 	private String bz;//备注
 	private String oldwygsid;//源系统数据ID   
   
 	
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	
	public String getSjzt() {
		return sjzt;
	}
	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
	}
	public String getWygsid() {
		return wygsid;
	}
	public void setWygsid(String wygsid) {
		this.wygsid = wygsid;
	}
	public String getHpbid() {
		return hpbid;
	}
	public void setHpbid(String hpbid) {
		this.hpbid = hpbid;
	}
	public String getLocalflag() {
		return localflag;
	}
	public void setLocalflag(String localflag) {
		this.localflag = localflag;
	}
	public String getWygsmc() {
		return wygsmc;
	}
	public void setWygsmc(String wygsmc) {
		this.wygsmc = wygsmc;
	}
	public String getWygsdz() {
		return wygsdz;
	}
	public void setWygsdz(String wygsdz) {
		this.wygsdz = wygsdz;
	}
	public String getCertflag() {
		return certflag;
	}
	public void setCertflag(String certflag) {
		this.certflag = certflag;
	}
	public String getShxydm() {
		return shxydm;
	}
	public void setShxydm(String shxydm) {
		this.shxydm = shxydm;
	}
	public String getYyzzbh() {
		return yyzzbh;
	}
	public void setYyzzbh(String yyzzbh) {
		this.yyzzbh = yyzzbh;
	}
	public String getZczzyxq() {
		return zczzyxq;
	}
	public void setZczzyxq(String zczzyxq) {
		this.zczzyxq = zczzyxq;
	}
	public String getGslx() {
		return gslx;
	}
	public void setGslx(String gslx) {
		this.gslx = gslx;
	}
	public String getZczb() {
		return zczb;
	}
	public void setZczb(String zczb) {
		this.zczb = zczb;
	}
	public String getClrq() {
		return clrq;
	}
	public void setClrq(String clrq) {
		this.clrq = clrq;
	}
	public String getWyglsj() {
		return wyglsj;
	}
	public void setWyglsj(String wyglsj) {
		this.wyglsj = wyglsj;
	}
	public String getBgdz() {
		return bgdz;
	}
	public void setBgdz(String bgdz) {
		this.bgdz = bgdz;
	}
	public String getZcdz() {
		return zcdz;
	}
	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}
	public String getZzdj() {
		return zzdj;
	}
	public void setZzdj(String zzdj) {
		this.zzdj = zzdj;
	}
	public String getZzbh() {
		return zzbh;
	}
	public void setZzbh(String zzbh) {
		this.zzbh = zzbh;
	}
	public String getQydh() {
		return qydh;
	}
	public void setQydh(String qydh) {
		this.qydh = qydh;
	}
	public String getQycz() {
		return qycz;
	}
	public void setQycz(String qycz) {
		this.qycz = qycz;
	}
	public String getQyyb() {
		return qyyb;
	}
	public void setQyyb(String qyyb) {
		this.qyyb = qyyb;
	}
	public String getFrdb() {
		return frdb;
	}
	public void setFrdb(String frdb) {
		this.frdb = frdb;
	}
	public String getFrsj() {
		return frsj;
	}
	public void setFrsj(String frsj) {
		this.frsj = frsj;
	}
	public String getQyyx() {
		return qyyx;
	}
	public void setQyyx(String qyyx) {
		this.qyyx = qyyx;
	}
	public String getQylxr() {
		return qylxr;
	}
	public void setQylxr(String qylxr) {
		this.qylxr = qylxr;
	}
	public String getLxrdh() {
		return lxrdh;
	}
	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}
	public String getQygk() {
		return qygk;
	}
	public void setQygk(String qygk) {
		this.qygk = qygk;
	}
	public String getZyfw() {
		return zyfw;
	}
	public void setZyfw(String zyfw) {
		this.zyfw = zyfw;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getOldwygsid() {
		return oldwygsid;
	}
	public void setOldwygsid(String oldwygsid) {
		this.oldwygsid = oldwygsid;
	}
	   
	   
	   
	   
	   
}
