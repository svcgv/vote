package com.indihx.system.entity;

import java.io.Serializable;

/***
 * 
 * <p>描 述: 物业服务企业信息表</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年6月15日</p>
 * @author 谢追辉
 */
public class PropMngCompy implements Serializable{
    private Long cpyId;  //公司编号

    private String cpyName;  //公司名称

    private String creatDate;  //创建日期

    private String creatTime;  //创建时间

    private String cpyCntr;  //联系人

    private Long cntrMobile;  //联系人手机

    private String cpyAddr;  //公司地址

    private String cpyPcd;  //邮编

    private String cpyTelno;  //公司电话

    private String qualiGrade;  //资质等级

    private String certNum;  //证书编号

    private String cpyState;  //是否有效
    
    private Long admId;  //所属区县

    public Long getAdmId() {
		return admId;
	}

	public void setAdmId(Long admId) {
		this.admId = admId;
	}

	public Long getCpyId() {
        return cpyId;
    }

    public void setCpyId(Long cpyId) {
        this.cpyId = cpyId;
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

    public Long getCntrMobile() {
        return cntrMobile;
    }

    public void setCntrMobile(Long cntrMobile) {
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