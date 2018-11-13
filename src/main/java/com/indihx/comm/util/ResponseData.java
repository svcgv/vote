/**
 * 文件名：ResponseData
 * 版权： Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述： 无
 */

package com.indihx.comm.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;

import java.util.List;

public class ResponseData {

    // 返回状态编码
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    // 返回信息
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    //数据
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<?> page;

    // 成功标识
    private boolean success = true;

    //总数
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long count;

    public ResponseData() {
    }

    public ResponseData(boolean success) {
        setSuccess(success);
        setCode("0");
        setMsg("");
    }

    public ResponseData(List<?> list) {
        this(true);
        setPage(list);
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<?> getPage() {
        return page;
    }

    public Long getCount() {
        return count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setPage(List<?> page) {
        this.page = page;
        if (page instanceof Page) {
            setCount(((Page<?>) page).getTotal());
        } else {
            setCount((long) page.size());
        }
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}