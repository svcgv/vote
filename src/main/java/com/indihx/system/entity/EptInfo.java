package com.indihx.system.entity;

import java.io.Serializable;

public class EptInfo implements Serializable{
    private Long eptId;

    private String creatDate;

    private String eptName;

    private String eptType;

    private String eptPhone;

    private String certCode;

    private String eptAddr;

    private String unitName;

    private String validFlag;

    private String urgFlag;

    private String serDist;

    private String urgDist;

    private String mngDesc;

    public Long getEptId() {
        return eptId;
    }

    public void setEptId(Long eptId) {
        this.eptId = eptId;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate == null ? null : creatDate.trim();
    }

    public String getEptName() {
        return eptName;
    }

    public void setEptName(String eptName) {
        this.eptName = eptName == null ? null : eptName.trim();
    }

    public String getEptType() {
        return eptType;
    }

    public void setEptType(String eptType) {
        this.eptType = eptType == null ? null : eptType.trim();
    }

    public String getEptPhone() {
        return eptPhone;
    }

    public void setEptPhone(String eptPhone) {
        this.eptPhone = eptPhone == null ? null : eptPhone.trim();
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode == null ? null : certCode.trim();
    }

    public String getEptAddr() {
        return eptAddr;
    }

    public void setEptAddr(String eptAddr) {
        this.eptAddr = eptAddr == null ? null : eptAddr.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag == null ? null : validFlag.trim();
    }

    public String getUrgFlag() {
        return urgFlag;
    }

    public void setUrgFlag(String urgFlag) {
        this.urgFlag = urgFlag == null ? null : urgFlag.trim();
    }

    public String getSerDist() {
        return serDist;
    }

    public void setSerDist(String serDist) {
        this.serDist = serDist == null ? null : serDist.trim();
    }

    public String getUrgDist() {
        return urgDist;
    }

    public void setUrgDist(String urgDist) {
        this.urgDist = urgDist == null ? null : urgDist.trim();
    }

    public String getMngDesc() {
        return mngDesc;
    }

    public void setMngDesc(String mngDesc) {
        this.mngDesc = mngDesc == null ? null : mngDesc.trim();
    }
}