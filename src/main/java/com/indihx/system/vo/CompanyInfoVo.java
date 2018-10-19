package com.indihx.system.vo;

public class CompanyInfoVo extends BaseVo{
	private String compId;
	
	private String compdId;

    private String compOrgNo;

    private String compName;

    private String compAddre;

    private String contacts;

    private String contactsMblNo;

    private String legalPerson;

    private String legalMblNo;


    private String tmSmp;//时间
    
    
    public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	
	public String getCompdId() {
		return compdId;
	}
	public void setCompdId(String compdId) {
		this.compdId = compdId;
	}
	public String getCompOrgNo() {
		return compOrgNo;
	}
	public void setCompOrgNo(String compOrgNo) {
		this.compOrgNo = compOrgNo;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompAddre() {
		return compAddre;
	}
	public void setCompAddre(String compAddre) {
		this.compAddre = compAddre;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getContactsMblNo() {
		return contactsMblNo;
	}
	public void setContactsMblNo(String contactsMblNo) {
		this.contactsMblNo = contactsMblNo;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getLegalMblNo() {
		return legalMblNo;
	}
	public void setLegalMblNo(String legalMblNo) {
		this.legalMblNo = legalMblNo;
	}
	/**get时间*/
    public String getTmSmp() {
        return tmSmp;
    }
    /**set时间*/
    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp;
    }
}