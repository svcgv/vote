package com.indihx.datamng.vo;

import com.indihx.system.vo.BaseVo;

public class StreetInfoVo extends BaseVo{
	private Long jdid;//街道ID
    private String hpbid;// 区县ID
    private String hpbmc;//区名称
    private String jdbm; //街道编号
    private String jdmc;//街道名称
    private String cjrq;//创建日期
    private String bz;//备注
	/**
	 * @return the jdid
	 */
	public Long getJdid() {
		return jdid;
	}
	/**
	 * @param jdid the jdid to set
	 */
	public void setJdid(Long jdid) {
		this.jdid = jdid;
	}
	/**
	 * @return the hpbId
	 */
	public String getHpbid() {
		return hpbid;
	}
	/**
	 * @param hpbId the hpbId to set
	 */
	public void setHpbId(String hpbid) {
		this.hpbid = hpbid;
	}
	/**
	 * @return the hpbmc
	 */
	public String getHpbmc() {
		return hpbmc;
	}
	/**
	 * @param hpbmc the hpbmc to set
	 */
	public void setHpbmc(String hpbmc) {
		this.hpbmc = hpbmc;
	}
	/**
	 * @return the jdbm
	 */
	public String getJdbm() {
		return jdbm;
	}
	/**
	 * @param jdbm the jdbm to set
	 */
	public void setJdbm(String jdbm) {
		this.jdbm = jdbm;
	}
	/**
	 * @return the jdmc
	 */
	public String getJdmc() {
		return jdmc;
	}
	/**
	 * @param jdmc the jdmc to set
	 */
	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}
	/**
	 * @return the cjrq
	 */
	public String getCjrq() {
		return cjrq;
	}
	/**
	 * @param cjrq the cjrq to set
	 */
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
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