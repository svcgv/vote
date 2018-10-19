package com.indihx.datamng.vo;

import java.math.BigDecimal;

/***
 * 
 * <p>
 * 描 述: 国土局数据VO类
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年5月9日
 * </p>
 * 
 * @author 谢追辉
 */
public class GTJDataInfoVo extends FileInfoVo {
	
	private Long batchNo; // 批次(时间+四位随机数)

	private String crNo; // 行政区域
	
	private String crNoList; //集合

	private String xzRegion; // 分区区域

	private String fqRegion; // 项目性质

	private String proNature; // 规划用途

	private String planUse;// 地块编号

	private String address;// 土地坐落

	private String devUnit;// 用地单位

	private BigDecimal gdArea;// 供地面积

	private BigDecimal ghzzArea;// 规划住宅面积

	private BigDecimal ghsyArea;// 规划商业面积

	private BigDecimal ghbgArea;// 规划办公面积

	private BigDecimal ghhjArea;// 规划合计面积

	private BigDecimal rjRate;// 容积率

	private String gdpzDate;// 供地批准时间

	private BigDecimal crArea;// 出让总额

	private BigDecimal floorPrice;// 楼板价

	private String yddgDate;// 约定动工时间

	private String ydjgDate;// 约定竣工时间

	private String realName;// 楼盘名称

	private BigDecimal zczzArea;// 自持住宅面积

	private BigDecimal zcsyArea;// 自持商业面积

	private BigDecimal zcbgArea;// 自持办公面积

	private BigDecimal zchjArea;// 自持合计面积

	private String updDate; // 更新时间
	
	public String getCrNoList() {
		return crNoList;
	}

	public void setCrNoList(String crNoList) {
		this.crNoList = crNoList;
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

	public BigDecimal getGdArea() {
		return gdArea;
	}

	public void setGdArea(BigDecimal gdArea) {
		this.gdArea = gdArea;
	}

	public BigDecimal getGhzzArea() {
		return ghzzArea;
	}

	public void setGhzzArea(BigDecimal ghzzArea) {
		this.ghzzArea = ghzzArea;
	}

	public BigDecimal getGhsyArea() {
		return ghsyArea;
	}

	public void setGhsyArea(BigDecimal ghsyArea) {
		this.ghsyArea = ghsyArea;
	}

	public BigDecimal getGhbgArea() {
		return ghbgArea;
	}

	public void setGhbgArea(BigDecimal ghbgArea) {
		this.ghbgArea = ghbgArea;
	}

	public BigDecimal getGhhjArea() {
		return ghhjArea;
	}

	public void setGhhjArea(BigDecimal ghhjArea) {
		this.ghhjArea = ghhjArea;
	}

	public BigDecimal getRjRate() {
		return rjRate;
	}

	public void setRjRate(BigDecimal rjRate) {
		this.rjRate = rjRate;
	}

	public String getGdpzDate() {
		return gdpzDate;
	}

	public void setGdpzDate(String gdpzDate) {
		this.gdpzDate = gdpzDate == null ? null : gdpzDate.trim();
	}

	public BigDecimal getCrArea() {
		return crArea;
	}

	public void setCrArea(BigDecimal crArea) {
		this.crArea = crArea;
	}

	public BigDecimal getFloorPrice() {
		return floorPrice;
	}

	public void setFloorPrice(BigDecimal floorPrice) {
		this.floorPrice = floorPrice;
	}

	public String getYddgDate() {
		return yddgDate;
	}

	public void setYddgDate(String yddgDate) {
		this.yddgDate = yddgDate == null ? null : yddgDate.trim();
	}

	public String getYdjgDate() {
		return ydjgDate;
	}

	public void setYdjgDate(String ydjgDate) {
		this.ydjgDate = ydjgDate == null ? null : ydjgDate.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public BigDecimal getZczzArea() {
		return zczzArea;
	}

	public void setZczzArea(BigDecimal zczzArea) {
		this.zczzArea = zczzArea;
	}

	public BigDecimal getZcsyArea() {
		return zcsyArea;
	}

	public void setZcsyArea(BigDecimal zcsyArea) {
		this.zcsyArea = zcsyArea;
	}

	public BigDecimal getZcbgArea() {
		return zcbgArea;
	}

	public void setZcbgArea(BigDecimal zcbgArea) {
		this.zcbgArea = zcbgArea;
	}

	public BigDecimal getZchjArea() {
		return zchjArea;
	}

	public void setZchjArea(BigDecimal zchjArea) {
		this.zchjArea = zchjArea;
	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate == null ? null : updDate.trim();
	}
}
