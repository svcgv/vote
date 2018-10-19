package com.indihx.org.entity;

import java.io.Serializable;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: WY_WYGS_STAFF.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月9日 上午11:02:16</p>
 * <p>@author ChuJinze</p>
 * <p>@version 1.0</p>
 * <p>WY_WYGS_STAFF.java</p>
 * <p></p>
 */
public class WY_WYGS_STAFF implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private String gsryid;	//人员ID
	private Long wygsid;	//物业公司ID
	private String rylx;	//人员类型 /*USER_TYPE* 01：项目经理  */
	private String status;	//数据状态 /*INFO_STATUS* 00:正常、01:暂存、02:修改中、03:删除中、04:已注销  */
	private String zjlx;	//证件类型 /*CERTTYPE* 1：身份证  */
	private String zjhm;	//证件号码
	private String ryxm;	//人员姓名
	private String ryxb;	//人员性别 /*SEXTYPE* 1：男、2：女  */
	private String whcd;	//文化程度 /*EDUCATION* 1:初中、2:高中、3:大专、4:本科、5:硕士研究生、6:博士研究生  */
	private String drzw;	//职务
	private String lxsj;	//手机
	private String lxdh;	//电话
	private String lxyx;	//邮箱
	private String csrq;	//出生日期
	private String yzbm;	//邮政编码
	private String htksrq;	//劳务合同开始日期
	private String htjzrq;	//劳务合同截止日期
	private String wgnx;	//从事物管年限
	private String lxdz;	//联系地址
	private String bz;		//备注
	/**
	 * @return the gsryid
	 */
	public String getGsryid() {
		return gsryid;
	}
	/**
	 * @param gsryid the gsryid to set
	 */
	public void setGsryid(String gsryid) {
		this.gsryid = gsryid;
	}
	/**
	 * @return the wygsid
	 */
	public Long getWygsid() {
		return wygsid;
	}
	/**
	 * @param wygsid the wygsid to set
	 */
	public void setWygsid(Long wygsid) {
		this.wygsid = wygsid;
	}
	/**
	 * @return the rylx
	 */
	public String getRylx() {
		return rylx;
	}
	/**
	 * @param rylx the rylx to set
	 */
	public void setRylx(String rylx) {
		this.rylx = rylx;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the zjlx
	 */
	public String getZjlx() {
		return zjlx;
	}
	/**
	 * @param zjlx the zjlx to set
	 */
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	/**
	 * @return the zjhm
	 */
	public String getZjhm() {
		return zjhm;
	}
	/**
	 * @param zjhm the zjhm to set
	 */
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	/**
	 * @return the ryxm
	 */
	public String getRyxm() {
		return ryxm;
	}
	/**
	 * @param ryxm the ryxm to set
	 */
	public void setRyxm(String ryxm) {
		this.ryxm = ryxm;
	}
	/**
	 * @return the ryxb
	 */
	public String getRyxb() {
		return ryxb;
	}
	/**
	 * @param ryxb the ryxb to set
	 */
	public void setRyxb(String ryxb) {
		this.ryxb = ryxb;
	}
	/**
	 * @return the whcd
	 */
	public String getWhcd() {
		return whcd;
	}
	/**
	 * @param whcd the whcd to set
	 */
	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}
	/**
	 * @return the drzw
	 */
	public String getDrzw() {
		return drzw;
	}
	/**
	 * @param drzw the drzw to set
	 */
	public void setDrzw(String drzw) {
		this.drzw = drzw;
	}
	/**
	 * @return the lxsj
	 */
	public String getLxsj() {
		return lxsj;
	}
	/**
	 * @param lxsj the lxsj to set
	 */
	public void setLxsj(String lxsj) {
		this.lxsj = lxsj;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdh the lxdh to set
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the lxyx
	 */
	public String getLxyx() {
		return lxyx;
	}
	/**
	 * @param lxyx the lxyx to set
	 */
	public void setLxyx(String lxyx) {
		this.lxyx = lxyx;
	}
	/**
	 * @return the csrq
	 */
	public String getCsrq() {
		return csrq;
	}
	/**
	 * @param csrq the csrq to set
	 */
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	/**
	 * @return the yzbm
	 */
	public String getYzbm() {
		return yzbm;
	}
	/**
	 * @param yzbm the yzbm to set
	 */
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	/**
	 * @return the htksrq
	 */
	public String getHtksrq() {
		return htksrq;
	}
	/**
	 * @param htksrq the htksrq to set
	 */
	public void setHtksrq(String htksrq) {
		this.htksrq = htksrq;
	}
	/**
	 * @return the htjzrq
	 */
	public String getHtjzrq() {
		return htjzrq;
	}
	/**
	 * @param htjzrq the htjzrq to set
	 */
	public void setHtjzrq(String htjzrq) {
		this.htjzrq = htjzrq;
	}
	/**
	 * @return the wgnx
	 */
	public String getWgnx() {
		return wgnx;
	}
	/**
	 * @param wgnx the wgnx to set
	 */
	public void setWgnx(String wgnx) {
		this.wgnx = wgnx;
	}
	/**
	 * @return the lxdz
	 */
	public String getLxdz() {
		return lxdz;
	}
	/**
	 * @param lxdz the lxdz to set
	 */
	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
	
	
	

}
