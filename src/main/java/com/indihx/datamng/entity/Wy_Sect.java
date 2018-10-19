package com.indihx.datamng.entity;

import java.io.Serializable;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: Wy_Sect.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月5日上午11:21:48</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>Wy_Sect.java</p>
 * <p>注意：所有实体类必须序列化</p>
 */
public class Wy_Sect implements Serializable{

	private static final long serialVersionUID = 1L;

	//基本信息
	String xmid;//项目ID
	String xmbm;//项目编码
	String sjzt;//数据状态
	String cjsj;//创建时间
	String xmmc;//项目名称
	String xmdz;//项目地址
	String hpbid;//所属区ID
	String hpbmc;//所属区名称
	String jdid;//所属街道ID
	String jdmc;//所属街道名称
	String sqid;//所属社区ID
	String sqmc;//所属社区名称
	String szdz;//四至-东至
	String sznz;//四至-南至
	String szxz;//四至-西至
	String szbz;//四至-北至
	String wyxz;//物业性质
	String wylx;//物业类型
	String xmms;//项目描述
	String oldxmid;//原数据ID
	
	String jzmj;//建筑面积
	String zzjzmj;//住宅建筑面积
	String syjzmj;//商业建筑面积
	String qtjzmj;//其他建筑面积
	String zdmj;//占地面积
	String zds;//总栋数
	String zhs;//总户数
	String jzmd;//建筑密度
	String rjl;//容积率
	String lhl;//绿化率
	String lhmj;//绿化面积
	String jzlhl;//集中绿化率
	String jzlhmj;//集中绿化面积
	String dscws;//地上车位数
	String dxcws;//地下车位数
	String kgsj;//开工时间
	String terms;//分几期建设
	String jhjfsj;//计划交付日期
	String bqjzmj;//本期招标建筑面积
	String jfsysj;//交付使用时间
	String wyglyfmj;//物业管理用房面积
	String ywhyfmj;//业委会用房面积
	
	String mwfmj;//门卫房面积
	String dhjmj;//电话间面积
	String jksmj;//监控室面积
	String fjdcckmj;//非机动车车库面积
	String bncmj;//避难层面积
	String sbcmj;//设备层面积
	String gybfqtms;//共有部分描述
	
	//管理单位信息
	String wygsid;//在管物业公司ID
	String wygsmc;//在管物业公司名称
	String glcid;//在管管理处ID
	String glcmc;//在管管理处名称
	String gsryid;//项目经理ID
	String xmjlxm;//项目经理姓名
	String ywhflag;//是否成立业委会
	String ywhid;//业委会ID
	String ywhmc;//业委会名称
	/**
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmid the xmid to set
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the xmbm
	 */
	public String getXmbm() {
		return xmbm;
	}
	/**
	 * @param xmbm the xmbm to set
	 */
	public void setXmbm(String xmbm) {
		this.xmbm = xmbm;
	}
	/**
	 * @return the sjzt
	 */
	public String getSjzt() {
		return sjzt;
	}
	/**
	 * @param sjzt the sjzt to set
	 */
	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
	}
	/**
	 * @return the cjsj
	 */
	public String getCjsj() {
		return cjsj;
	}
	/**
	 * @param cjsj the cjsj to set
	 */
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc the xmmc to set
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the xmdz
	 */
	public String getXmdz() {
		return xmdz;
	}
	/**
	 * @param xmdz the xmdz to set
	 */
	public void setXmdz(String xmdz) {
		this.xmdz = xmdz;
	}
	/**
	 * @return the hpbid
	 */
	public String getHpbid() {
		return hpbid;
	}
	/**
	 * @param hpbid the hpbid to set
	 */
	public void setHpbid(String hpbid) {
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
	 * @return the jdid
	 */
	public String getJdid() {
		return jdid;
	}
	/**
	 * @param jdid the jdid to set
	 */
	public void setJdid(String jdid) {
		this.jdid = jdid;
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
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the sqmc
	 */
	public String getSqmc() {
		return sqmc;
	}
	/**
	 * @param sqmc the sqmc to set
	 */
	public void setSqmc(String sqmc) {
		this.sqmc = sqmc;
	}
	/**
	 * @return the szdz
	 */
	public String getSzdz() {
		return szdz;
	}
	/**
	 * @param szdz the szdz to set
	 */
	public void setSzdz(String szdz) {
		this.szdz = szdz;
	}
	/**
	 * @return the sznz
	 */
	public String getSznz() {
		return sznz;
	}
	/**
	 * @param sznz the sznz to set
	 */
	public void setSznz(String sznz) {
		this.sznz = sznz;
	}
	/**
	 * @return the szxz
	 */
	public String getSzxz() {
		return szxz;
	}
	/**
	 * @param szxz the szxz to set
	 */
	public void setSzxz(String szxz) {
		this.szxz = szxz;
	}
	/**
	 * @return the szbz
	 */
	public String getSzbz() {
		return szbz;
	}
	/**
	 * @param szbz the szbz to set
	 */
	public void setSzbz(String szbz) {
		this.szbz = szbz;
	}
	/**
	 * @return the wyxz
	 */
	public String getWyxz() {
		return wyxz;
	}
	/**
	 * @param wyxz the wyxz to set
	 */
	public void setWyxz(String wyxz) {
		this.wyxz = wyxz;
	}
	/**
	 * @return the wylx
	 */
	public String getWylx() {
		return wylx;
	}
	/**
	 * @param wylx the wylx to set
	 */
	public void setWylx(String wylx) {
		this.wylx = wylx;
	}
	/**
	 * @return the xmms
	 */
	public String getXmms() {
		return xmms;
	}
	/**
	 * @param xmms the xmms to set
	 */
	public void setXmms(String xmms) {
		this.xmms = xmms;
	}
	/**
	 * @return the oldxmid
	 */
	public String getOldxmid() {
		return oldxmid;
	}
	/**
	 * @param oldxmid the oldxmid to set
	 */
	public void setOldxmid(String oldxmid) {
		this.oldxmid = oldxmid;
	}
	/**
	 * @return the jzmj
	 */
	public String getJzmj() {
		return jzmj;
	}
	/**
	 * @param jzmj the jzmj to set
	 */
	public void setJzmj(String jzmj) {
		this.jzmj = jzmj;
	}
	/**
	 * @return the zzjzmj
	 */
	public String getZzjzmj() {
		return zzjzmj;
	}
	/**
	 * @param zzjzmj the zzjzmj to set
	 */
	public void setZzjzmj(String zzjzmj) {
		this.zzjzmj = zzjzmj;
	}
	/**
	 * @return the syjzmj
	 */
	public String getSyjzmj() {
		return syjzmj;
	}
	/**
	 * @param syjzmj the syjzmj to set
	 */
	public void setSyjzmj(String syjzmj) {
		this.syjzmj = syjzmj;
	}
	/**
	 * @return the qtjzmj
	 */
	public String getQtjzmj() {
		return qtjzmj;
	}
	/**
	 * @param qtjzmj the qtjzmj to set
	 */
	public void setQtjzmj(String qtjzmj) {
		this.qtjzmj = qtjzmj;
	}
	/**
	 * @return the zdmj
	 */
	public String getZdmj() {
		return zdmj;
	}
	/**
	 * @param zdmj the zdmj to set
	 */
	public void setZdmj(String zdmj) {
		this.zdmj = zdmj;
	}
	/**
	 * @return the zds
	 */
	public String getZds() {
		return zds;
	}
	/**
	 * @param zds the zds to set
	 */
	public void setZds(String zds) {
		this.zds = zds;
	}
	/**
	 * @return the zhs
	 */
	public String getZhs() {
		return zhs;
	}
	/**
	 * @param zhs the zhs to set
	 */
	public void setZhs(String zhs) {
		this.zhs = zhs;
	}
	/**
	 * @return the jzmd
	 */
	public String getJzmd() {
		return jzmd;
	}
	/**
	 * @param jzmd the jzmd to set
	 */
	public void setJzmd(String jzmd) {
		this.jzmd = jzmd;
	}
	/**
	 * @return the rjl
	 */
	public String getRjl() {
		return rjl;
	}
	/**
	 * @param rjl the rjl to set
	 */
	public void setRjl(String rjl) {
		this.rjl = rjl;
	}
	/**
	 * @return the lhl
	 */
	public String getLhl() {
		return lhl;
	}
	/**
	 * @param lhl the lhl to set
	 */
	public void setLhl(String lhl) {
		this.lhl = lhl;
	}
	/**
	 * @return the lhmj
	 */
	public String getLhmj() {
		return lhmj;
	}
	/**
	 * @param lhmj the lhmj to set
	 */
	public void setLhmj(String lhmj) {
		this.lhmj = lhmj;
	}
	/**
	 * @return the jzlhl
	 */
	public String getJzlhl() {
		return jzlhl;
	}
	/**
	 * @param jzlhl the jzlhl to set
	 */
	public void setJzlhl(String jzlhl) {
		this.jzlhl = jzlhl;
	}
	/**
	 * @return the jzlhmj
	 */
	public String getJzlhmj() {
		return jzlhmj;
	}
	/**
	 * @param jzlhmj the jzlhmj to set
	 */
	public void setJzlhmj(String jzlhmj) {
		this.jzlhmj = jzlhmj;
	}
	/**
	 * @return the dscws
	 */
	public String getDscws() {
		return dscws;
	}
	/**
	 * @param dscws the dscws to set
	 */
	public void setDscws(String dscws) {
		this.dscws = dscws;
	}
	/**
	 * @return the dxcws
	 */
	public String getDxcws() {
		return dxcws;
	}
	/**
	 * @param dxcws the dxcws to set
	 */
	public void setDxcws(String dxcws) {
		this.dxcws = dxcws;
	}
	/**
	 * @return the kgsj
	 */
	public String getKgsj() {
		return kgsj;
	}
	/**
	 * @param kgsj the kgsj to set
	 */
	public void setKgsj(String kgsj) {
		this.kgsj = kgsj;
	}
	/**
	 * @return the terms
	 */
	public String getTerms() {
		return terms;
	}
	/**
	 * @param terms the terms to set
	 */
	public void setTerms(String terms) {
		this.terms = terms;
	}
	/**
	 * @return the jhjfsj
	 */
	public String getJhjfsj() {
		return jhjfsj;
	}
	/**
	 * @param jhjfsj the jhjfsj to set
	 */
	public void setJhjfsj(String jhjfsj) {
		this.jhjfsj = jhjfsj;
	}
	/**
	 * @return the bqjzmj
	 */
	public String getBqjzmj() {
		return bqjzmj;
	}
	/**
	 * @param bqjzmj the bqjzmj to set
	 */
	public void setBqjzmj(String bqjzmj) {
		this.bqjzmj = bqjzmj;
	}
	/**
	 * @return the jfsysj
	 */
	public String getJfsysj() {
		return jfsysj;
	}
	/**
	 * @param jfsysj the jfsysj to set
	 */
	public void setJfsysj(String jfsysj) {
		this.jfsysj = jfsysj;
	}
	/**
	 * @return the wyglyfmj
	 */
	public String getWyglyfmj() {
		return wyglyfmj;
	}
	/**
	 * @param wyglyfmj the wyglyfmj to set
	 */
	public void setWyglyfmj(String wyglyfmj) {
		this.wyglyfmj = wyglyfmj;
	}
	/**
	 * @return the ywhyfmj
	 */
	public String getYwhyfmj() {
		return ywhyfmj;
	}
	/**
	 * @param ywhyfmj the ywhyfmj to set
	 */
	public void setYwhyfmj(String ywhyfmj) {
		this.ywhyfmj = ywhyfmj;
	}
	/**
	 * @return the mwfmj
	 */
	public String getMwfmj() {
		return mwfmj;
	}
	/**
	 * @param mwfmj the mwfmj to set
	 */
	public void setMwfmj(String mwfmj) {
		this.mwfmj = mwfmj;
	}
	/**
	 * @return the dhjmj
	 */
	public String getDhjmj() {
		return dhjmj;
	}
	/**
	 * @param dhjmj the dhjmj to set
	 */
	public void setDhjmj(String dhjmj) {
		this.dhjmj = dhjmj;
	}
	/**
	 * @return the jksmj
	 */
	public String getJksmj() {
		return jksmj;
	}
	/**
	 * @param jksmj the jksmj to set
	 */
	public void setJksmj(String jksmj) {
		this.jksmj = jksmj;
	}
	/**
	 * @return the fjdcckmj
	 */
	public String getFjdcckmj() {
		return fjdcckmj;
	}
	/**
	 * @param fjdcckmj the fjdcckmj to set
	 */
	public void setFjdcckmj(String fjdcckmj) {
		this.fjdcckmj = fjdcckmj;
	}
	/**
	 * @return the bncmj
	 */
	public String getBncmj() {
		return bncmj;
	}
	/**
	 * @param bncmj the bncmj to set
	 */
	public void setBncmj(String bncmj) {
		this.bncmj = bncmj;
	}
	/**
	 * @return the sbcmj
	 */
	public String getSbcmj() {
		return sbcmj;
	}
	/**
	 * @param sbcmj the sbcmj to set
	 */
	public void setSbcmj(String sbcmj) {
		this.sbcmj = sbcmj;
	}
	/**
	 * @return the gybfqtms
	 */
	public String getGybfqtms() {
		return gybfqtms;
	}
	/**
	 * @param gybfqtms the gybfqtms to set
	 */
	public void setGybfqtms(String gybfqtms) {
		this.gybfqtms = gybfqtms;
	}
	/**
	 * @return the wygsid
	 */
	public String getWygsid() {
		return wygsid;
	}
	/**
	 * @param wygsid the wygsid to set
	 */
	public void setWygsid(String wygsid) {
		this.wygsid = wygsid;
	}
	/**
	 * @return the wygsmc
	 */
	public String getWygsmc() {
		return wygsmc;
	}
	/**
	 * @param wygsmc the wygsmc to set
	 */
	public void setWygsmc(String wygsmc) {
		this.wygsmc = wygsmc;
	}
	/**
	 * @return the glcid
	 */
	public String getGlcid() {
		return glcid;
	}
	/**
	 * @param glcid the glcid to set
	 */
	public void setGlcid(String glcid) {
		this.glcid = glcid;
	}
	/**
	 * @return the glcmc
	 */
	public String getGlcmc() {
		return glcmc;
	}
	/**
	 * @param glcmc the glcmc to set
	 */
	public void setGlcmc(String glcmc) {
		this.glcmc = glcmc;
	}
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
	 * @return the xmjlxm
	 */
	public String getXmjlxm() {
		return xmjlxm;
	}
	/**
	 * @param xmjlxm the xmjlxm to set
	 */
	public void setXmjlxm(String xmjlxm) {
		this.xmjlxm = xmjlxm;
	}
	/**
	 * @return the ywhflag
	 */
	public String getYwhflag() {
		return ywhflag;
	}
	/**
	 * @param ywhflag the ywhflag to set
	 */
	public void setYwhflag(String ywhflag) {
		this.ywhflag = ywhflag;
	}
	/**
	 * @return the ywhid
	 */
	public String getYwhid() {
		return ywhid;
	}
	/**
	 * @param ywhid the ywhid to set
	 */
	public void setYwhid(String ywhid) {
		this.ywhid = ywhid;
	}
	/**
	 * @return the ywhmc
	 */
	public String getYwhmc() {
		return ywhmc;
	}
	/**
	 * @param ywhmc the ywhmc to set
	 */
	public void setYwhmc(String ywhmc) {
		this.ywhmc = ywhmc;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
