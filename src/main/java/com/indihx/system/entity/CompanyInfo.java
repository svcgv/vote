package com.indihx.system.entity;

import java.io.Serializable;

public class CompanyInfo implements Serializable{
    private Long compId;

    private String compOrgNo;

    private String compName;

    private String compAddre;

    private String contacts;

    private Long contactsMblNo;

    private String legalPerson;

    private Long legalMblNo;

    private String tmSmp;

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public String getCompOrgNo() {
        return compOrgNo;
    }

    public void setCompOrgNo(String compOrgNo) {
        this.compOrgNo = compOrgNo == null ? null : compOrgNo.trim();
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName == null ? null : compName.trim();
    }

    public String getCompAddre() {
        return compAddre;
    }

    public void setCompAddre(String compAddre) {
        this.compAddre = compAddre == null ? null : compAddre.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public Long getContactsMblNo() {
        return contactsMblNo;
    }

    public void setContactsMblNo(Long contactsMblNo) {
        this.contactsMblNo = contactsMblNo;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public Long getLegalMblNo() {
        return legalMblNo;
    }

    public void setLegalMblNo(Long legalMblNo) {
        this.legalMblNo = legalMblNo;
    }

    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }
}