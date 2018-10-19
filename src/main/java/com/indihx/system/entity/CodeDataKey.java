package com.indihx.system.entity;

import java.io.Serializable;

public class CodeDataKey implements Serializable{
	//字典类型编码
    private String codeNo;
    //字典代码
    private String codeKey;

   

    public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey == null ? null : codeKey.trim();
    }
}