package com.indihx.system.vo;

public class ParamsInfoVo extends BaseVo{
	 private String paramsNo;

	 private String paramsName;

	 private String paramsVal;
	 
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
		this.paramsNo = paramsNo;
	}

	public String getParamsName() {
		return paramsName;
	}

	public void setParamsName(String paramsName) {
		this.paramsName = paramsName;
	}

	public String getParamsVal() {
		return paramsVal;
	}

	public void setParamsVal(String paramsVal) {
		this.paramsVal = paramsVal;
	}

	public String getTmSmp() {
		return tmSmp;
	}

	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
	}

	private String tmSmp;

	private String ewTypeHtml;

	public String getEwTypeHtml() {
		return ewTypeHtml;
	}

	public void setEwTypeHtml(String ewTypeHtml) {
		this.ewTypeHtml = ewTypeHtml;
	}

}
