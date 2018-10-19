package com.indihx.datamng.vo;

import java.math.BigDecimal;

import com.indihx.system.vo.BaseVo;

public class FgjDataInfoVo extends BaseVo{
	 private String crNo;//地块编号

	    private Long batchNo;//批次号

	    private String xzRegion;//行政区域

	    private String fqRegion;//分区区域

	    private String proNature;//项目性质

	    private String planUse;//规划用途

	    private String address;//土地坐落

	    private String devUnit;//开发企业

	    private String realName;//项目名称

	    private String gaNo;//公安编号

	    private String ysxkNo;//预售许可编号

	    private BigDecimal ysxkzzArea;//预售许可住宅面积

	    private BigDecimal ysxksyArea;//预售许可商业面积

	    private BigDecimal ysxkbgArea;//预售许可办公面积

	    private BigDecimal xkhjArea;//许可合计面积

	    private Long ysxkzzCnt;//预售许可住宅套数

	    private Long ysxusyCnt;//预售许可商业套数

	    private Long ysxkbgCnt;//预售许可办公套数

	    private Long yshjCnt;//预售合计套数

	    private BigDecimal xszzArea;//销售住宅面积

	    private BigDecimal xssyArea;//销售商业面积

	    private BigDecimal xsbgArea;//销售办公面积

	    private BigDecimal xshjArea;//销售合计面积

	    private Long xszzCnt;//销售住宅套数

	    private Long xssyCnt;//销售商业套数

	    private Long xsbgCnt;//销售办公套数

	    private Long xshjCnt;//销售合计套数

	    private BigDecimal xszzAmt;//销售住宅价格

	    private BigDecimal xssyAmt;//销售商业价格

	    private BigDecimal xsbgAmt;//销售办公价格

	    private BigDecimal xshjAmt;//销售合计价格

	    private BigDecimal xszzAveamt;//销售住宅均价

	    private BigDecimal xssyAveamt;//销售商业均价

	    private BigDecimal xsbgAveamt;//销售办公均价

	    private BigDecimal xshjAveamt;//销售合计均价

	    private BigDecimal zzclArea;//住宅存量面积

	    private BigDecimal syclArea;//商业存量面积

	    private BigDecimal bgclArea;//办公存量面积

	    private BigDecimal hjclArea;//合计存量面积

	    private BigDecimal yjxszzArea;//月均销售住宅面积

	    private BigDecimal yjxssyArea;//月均销售商业面积

	    private BigDecimal yjxsbgArea;//月均销售办公面积

	    private BigDecimal yjxshjArea;//月均销售合计面积

	    private String qhCycle;//去化周期

	    private String tmSmp;//导入时间
	    
	    private String partName;//分区区域名称
	    private String adminName;//行政区域名称
	    private String crNoList; //集合

    public String getCrNoList() {
			return crNoList;
		}

		public void setCrNoList(String crNoList) {
			this.crNoList = crNoList;
		}

	public String getPartName() {
			return partName;
		}

		public void setPartName(String partName) {
			this.partName = partName;
		}

		public String getAdminName() {
			return adminName;
		}

		public void setAdminName(String adminName) {
			this.adminName = adminName;
		}

	public String getCrNo() {
        return crNo;
    }

    public void setCrNo(String crNo) {
        this.crNo = crNo == null ? null : crNo.trim();
    }

    public Long getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Long batchNo) {
        this.batchNo = batchNo;
    }

    public String getXzRegion() {
        return xzRegion;
    }

    public void setXzRegion(String xzRegion) {
        this.xzRegion = xzRegion == null ? null : xzRegion.trim();
    }

    public String getFqRegion() {
        return fqRegion;
    }

    public void setFqRegion(String fqRegion) {
        this.fqRegion = fqRegion == null ? null : fqRegion.trim();
    }

    public String getProNature() {
        return proNature;
    }

    public void setProNature(String proNature) {
        this.proNature = proNature == null ? null : proNature.trim();
    }

    public String getPlanUse() {
        return planUse;
    }

    public void setPlanUse(String planUse) {
        this.planUse = planUse == null ? null : planUse.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDevUnit() {
        return devUnit;
    }

    public void setDevUnit(String devUnit) {
        this.devUnit = devUnit == null ? null : devUnit.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getGaNo() {
        return gaNo;
    }

    public void setGaNo(String gaNo) {
        this.gaNo = gaNo == null ? null : gaNo.trim();
    }

    public String getYsxkNo() {
        return ysxkNo;
    }

    public void setYsxkNo(String ysxkNo) {
        this.ysxkNo = ysxkNo == null ? null : ysxkNo.trim();
    }

    public BigDecimal getYsxkzzArea() {
        return ysxkzzArea;
    }

    public void setYsxkzzArea(BigDecimal ysxkzzArea) {
        this.ysxkzzArea = ysxkzzArea;
    }

    public BigDecimal getYsxksyArea() {
        return ysxksyArea;
    }

    public void setYsxksyArea(BigDecimal ysxksyArea) {
        this.ysxksyArea = ysxksyArea;
    }

    public BigDecimal getYsxkbgArea() {
        return ysxkbgArea;
    }

    public void setYsxkbgArea(BigDecimal ysxkbgArea) {
        this.ysxkbgArea = ysxkbgArea;
    }

    public BigDecimal getXkhjArea() {
        return xkhjArea;
    }

    public void setXkhjArea(BigDecimal xkhjArea) {
        this.xkhjArea = xkhjArea;
    }

    public Long getYsxkzzCnt() {
        return ysxkzzCnt;
    }

    public void setYsxkzzCnt(Long ysxkzzCnt) {
        this.ysxkzzCnt = ysxkzzCnt;
    }

    public Long getYsxusyCnt() {
        return ysxusyCnt;
    }

    public void setYsxusyCnt(Long ysxusyCnt) {
        this.ysxusyCnt = ysxusyCnt;
    }

    public Long getYsxkbgCnt() {
        return ysxkbgCnt;
    }

    public void setYsxkbgCnt(Long ysxkbgCnt) {
        this.ysxkbgCnt = ysxkbgCnt;
    }

    public Long getYshjCnt() {
        return yshjCnt;
    }

    public void setYshjCnt(Long yshjCnt) {
        this.yshjCnt = yshjCnt;
    }

    public BigDecimal getXszzArea() {
        return xszzArea;
    }

    public void setXszzArea(BigDecimal xszzArea) {
        this.xszzArea = xszzArea;
    }

    public BigDecimal getXssyArea() {
        return xssyArea;
    }

    public void setXssyArea(BigDecimal xssyArea) {
        this.xssyArea = xssyArea;
    }

    public BigDecimal getXsbgArea() {
        return xsbgArea;
    }

    public void setXsbgArea(BigDecimal xsbgArea) {
        this.xsbgArea = xsbgArea;
    }

    public BigDecimal getXshjArea() {
        return xshjArea;
    }

    public void setXshjArea(BigDecimal xshjArea) {
        this.xshjArea = xshjArea;
    }

    public Long getXszzCnt() {
        return xszzCnt;
    }

    public void setXszzCnt(Long xszzCnt) {
        this.xszzCnt = xszzCnt;
    }

    public Long getXssyCnt() {
        return xssyCnt;
    }

    public void setXssyCnt(Long xssyCnt) {
        this.xssyCnt = xssyCnt;
    }

    public Long getXsbgCnt() {
        return xsbgCnt;
    }

    public void setXsbgCnt(Long xsbgCnt) {
        this.xsbgCnt = xsbgCnt;
    }

    public Long getXshjCnt() {
        return xshjCnt;
    }

    public void setXshjCnt(Long xshjCnt) {
        this.xshjCnt = xshjCnt;
    }

    public BigDecimal getXszzAmt() {
        return xszzAmt;
    }

    public void setXszzAmt(BigDecimal xszzAmt) {
        this.xszzAmt = xszzAmt;
    }

    public BigDecimal getXssyAmt() {
        return xssyAmt;
    }

    public void setXssyAmt(BigDecimal xssyAmt) {
        this.xssyAmt = xssyAmt;
    }

    public BigDecimal getXsbgAmt() {
        return xsbgAmt;
    }

    public void setXsbgAmt(BigDecimal xsbgAmt) {
        this.xsbgAmt = xsbgAmt;
    }

    public BigDecimal getXshjAmt() {
        return xshjAmt;
    }

    public void setXshjAmt(BigDecimal xshjAmt) {
        this.xshjAmt = xshjAmt;
    }

    public BigDecimal getXszzAveamt() {
        return xszzAveamt;
    }

    public void setXszzAveamt(BigDecimal xszzAveamt) {
        this.xszzAveamt = xszzAveamt;
    }

    public BigDecimal getXssyAveamt() {
        return xssyAveamt;
    }

    public void setXssyAveamt(BigDecimal xssyAveamt) {
        this.xssyAveamt = xssyAveamt;
    }

    public BigDecimal getXsbgAveamt() {
        return xsbgAveamt;
    }

    public void setXsbgAveamt(BigDecimal xsbgAveamt) {
        this.xsbgAveamt = xsbgAveamt;
    }

    public BigDecimal getXshjAveamt() {
        return xshjAveamt;
    }

    public void setXshjAveamt(BigDecimal xshjAveamt) {
        this.xshjAveamt = xshjAveamt;
    }

    public BigDecimal getZzclArea() {
        return zzclArea;
    }

    public void setZzclArea(BigDecimal zzclArea) {
        this.zzclArea = zzclArea;
    }

    public BigDecimal getSyclArea() {
        return syclArea;
    }

    public void setSyclArea(BigDecimal syclArea) {
        this.syclArea = syclArea;
    }

    public BigDecimal getBgclArea() {
        return bgclArea;
    }

    public void setBgclArea(BigDecimal bgclArea) {
        this.bgclArea = bgclArea;
    }

    public BigDecimal getHjclArea() {
        return hjclArea;
    }

    public void setHjclArea(BigDecimal hjclArea) {
        this.hjclArea = hjclArea;
    }

    public BigDecimal getYjxszzArea() {
        return yjxszzArea;
    }

    public void setYjxszzArea(BigDecimal yjxszzArea) {
        this.yjxszzArea = yjxszzArea;
    }

    public BigDecimal getYjxssyArea() {
        return yjxssyArea;
    }

    public void setYjxssyArea(BigDecimal yjxssyArea) {
        this.yjxssyArea = yjxssyArea;
    }

    public BigDecimal getYjxsbgArea() {
        return yjxsbgArea;
    }

    public void setYjxsbgArea(BigDecimal yjxsbgArea) {
        this.yjxsbgArea = yjxsbgArea;
    }

    public BigDecimal getYjxshjArea() {
        return yjxshjArea;
    }

    public void setYjxshjArea(BigDecimal yjxshjArea) {
        this.yjxshjArea = yjxshjArea;
    }

    public String getQhCycle() {
        return qhCycle;
    }

    public void setQhCycle(String qhCycle) {
        this.qhCycle = qhCycle == null ? null : qhCycle.trim();
    }

    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }
}