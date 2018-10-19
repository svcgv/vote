package com.indihx.system.vo;

public class PropMngCompyVo extends BaseVo{
    private String cpyId;

    private String cpydId;
    
    private String cpyName;

    private String creatDate;

    private String creatTime;

    private String cpyCntr;

    private String cntrMobile;

    private String cpyAddr;

    private String cpyPcd;

    private String cpyTelno;

    private String qualiGrade;

    private String certNum;

    private String cpyState;

    public String getCpyId() {
        return cpyId;
    }

    public void setCpyId(String cpyId) {
        this.cpyId = cpyId;
    }

    public String getCpydId() {
		return cpydId;
	}

	public void setCpydId(String cpydId) {
		this.cpydId = cpydId;
	}

	public String getCpyName() {
        return cpyName;
    }

    public void setCpyName(String cpyName) {
        this.cpyName = cpyName == null ? null : cpyName.trim();
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate == null ? null : creatDate.trim();
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime == null ? null : creatTime.trim();
    }

    public String getCpyCntr() {
        return cpyCntr;
    }

    public void setCpyCntr(String cpyCntr) {
        this.cpyCntr = cpyCntr == null ? null : cpyCntr.trim();
    }

    public String getCntrMobile() {
        return cntrMobile;
    }

    public void setCntrMobile(String cntrMobile) {
        this.cntrMobile = cntrMobile;
    }

    public String getCpyAddr() {
        return cpyAddr;
    }

    public void setCpyAddr(String cpyAddr) {
        this.cpyAddr = cpyAddr == null ? null : cpyAddr.trim();
    }

    public String getCpyPcd() {
        return cpyPcd;
    }

    public void setCpyPcd(String cpyPcd) {
        this.cpyPcd = cpyPcd == null ? null : cpyPcd.trim();
    }

    public String getCpyTelno() {
        return cpyTelno;
    }

    public void setCpyTelno(String cpyTelno) {
        this.cpyTelno = cpyTelno == null ? null : cpyTelno.trim();
    }

    public String getQualiGrade() {
        return qualiGrade;
    }

    public void setQualiGrade(String qualiGrade) {
        this.qualiGrade = qualiGrade == null ? null : qualiGrade.trim();
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum == null ? null : certNum.trim();
    }

    public String getCpyState() {
        return cpyState;
    }

    public void setCpyState(String cpyState) {
        this.cpyState = cpyState == null ? null : cpyState.trim();
    }
}