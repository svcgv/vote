package com.indihx.system.entity;

import java.io.Serializable;

public class ParamsInfo implements Serializable{
    private String paramsNo;

    private String paramsName;

    private String paramsVal;

    private String tmSmp;
    
    private String paramsType; //参数类别
    private String remark; //备注
    

	public String getParamsType() {
		return paramsType;
	}

	public void setParamsType(String paramsType) {
		this.paramsType = paramsType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParamsNo() {
        return paramsNo;
    }

    public void setParamsNo(String paramsNo) {
        this.paramsNo = paramsNo == null ? null : paramsNo.trim();
    }

    public String getParamsName() {
        return paramsName;
    }

    public void setParamsName(String paramsName) {
        this.paramsName = paramsName == null ? null : paramsName.trim();
    }

    public String getParamsVal() {
        return paramsVal;
    }

    public void setParamsVal(String paramsVal) {
        this.paramsVal = paramsVal == null ? null : paramsVal.trim();
    }

    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp == null ? null : tmSmp.trim();
    }

    private String ewTypeHtml;


	public String getEwTypeHtml() {
		return ewTypeHtml;
	}

	public void setEwTypeHtml(String ewTypeHtml) {
		this.ewTypeHtml = ewTypeHtml;
	}
}