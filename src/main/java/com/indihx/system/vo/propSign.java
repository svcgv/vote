package com.indihx.system.vo;

public class propSign {
    private Long signId;//投标报名ID

    private Long cpyId;//公司编号

    private Long biddId;//招标主键

    private String cpyName;//物业公司名称

    private String cpyCntr;//联系人

    private Long cntrTel;//联系人手机

    private String entState;//入围状态

    private String entDate;//入围日期

    private String entTime;//入围时间

    private String sigState;//是否签到

    private String sigDate;//签到日期

    private String sigTime;//签到时间

    private String bidDocState;//投标书是否合格

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public Long getCpyId() {
        return cpyId;
    }

    public void setCpyId(Long cpyId) {
        this.cpyId = cpyId;
    }

    public Long getBiddId() {
        return biddId;
    }

    public void setBiddId(Long biddId) {
        this.biddId = biddId;
    }

    public String getCpyName() {
        return cpyName;
    }

    public void setCpyName(String cpyName) {
        this.cpyName = cpyName == null ? null : cpyName.trim();
    }

    public String getCpyCntr() {
        return cpyCntr;
    }

    public void setCpyCntr(String cpyCntr) {
        this.cpyCntr = cpyCntr == null ? null : cpyCntr.trim();
    }

    public Long getCntrTel() {
        return cntrTel;
    }

    public void setCntrTel(Long cntrTel) {
        this.cntrTel = cntrTel;
    }

    public String getEntState() {
        return entState;
    }

    public void setEntState(String entState) {
        this.entState = entState == null ? null : entState.trim();
    }

    public String getEntDate() {
        return entDate;
    }

    public void setEntDate(String entDate) {
        this.entDate = entDate == null ? null : entDate.trim();
    }

    public String getEntTime() {
        return entTime;
    }

    public void setEntTime(String entTime) {
        this.entTime = entTime == null ? null : entTime.trim();
    }

    public String getSigState() {
        return sigState;
    }

    public void setSigState(String sigState) {
        this.sigState = sigState == null ? null : sigState.trim();
    }

    public String getSigDate() {
        return sigDate;
    }

    public void setSigDate(String sigDate) {
        this.sigDate = sigDate == null ? null : sigDate.trim();
    }

    public String getSigTime() {
        return sigTime;
    }

    public void setSigTime(String sigTime) {
        this.sigTime = sigTime == null ? null : sigTime.trim();
    }

    public String getBidDocState() {
        return bidDocState;
    }

    public void setBidDocState(String bidDocState) {
        this.bidDocState = bidDocState == null ? null : bidDocState.trim();
    }
}