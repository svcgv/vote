package com.indihx.inspector.vo;

import com.indihx.system.vo.BaseVo;

public class KhzbVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	/*记分指标流水号*/
	private long check_seq;
	/*上一级流水号*/
	private String super_check_seq;
	/*指标类别*/
	private String zblb;
	/*指标编码*/
	private String zbbm;
	/*指标名称*/
	private String zbmc;
	/*加分减分标志*/
	private String jfbz;
	/*指标层级*/
	private String zbcj;
	/*考核评分依据*/
	private String jfyj;
	/*条*/
	private String tseq;
	/*款*/
	private String kseq;
	/*参考分值*/
	private String ckfz;
	
	
	public long getCheck_seq() {
		return check_seq;
	}
	public void setCheck_seq(long check_seq) {
		this.check_seq = check_seq;
	}
	public String getZblb() {
		return zblb;
	}
	public void setZblb(String zblb) {
		this.zblb = zblb;
	}
	public String getZbbm() {
		return zbbm;
	}
	public void setZbbm(String zbbm) {
		this.zbbm = zbbm;
	}
	public String getZbmc() {
		return zbmc;
	}
	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}
	public String getJfbz() {
		return jfbz;
	}
	public void setJfbz(String jfbz) {
		this.jfbz = jfbz;
	}
	public String getZbcj() {
		return zbcj;
	}
	public void setZbcj(String zbcj) {
		this.zbcj = zbcj;
	}
	public String getJfyj() {
		return jfyj;
	}
	public void setJfyj(String jfyj) {
		this.jfyj = jfyj;
	}
	public String getTseq() {
		return tseq;
	}
	public void setTseq(String tseq) {
		this.tseq = tseq;
	}
	public String getKseq() {
		return kseq;
	}
	public void setKseq(String kseq) {
		this.kseq = kseq;
	}
	public String getCkfz() {
		return ckfz;
	}
	public void setCkfz(String ckfz) {
		this.ckfz = ckfz;
	}
	public String getSuper_check_seq() {
		return super_check_seq;
	}
	public void setSuper_check_seq(String super_check_seq) {
		this.super_check_seq = super_check_seq;
	}
}
