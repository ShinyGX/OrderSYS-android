package com.last.booking.data.dto;

import com.last.booking.data.model.BusinessInfo;

import java.util.List;

public class BusinessInfoListObj {
    private String msg;
    private Integer code;
    private List<BusinessInfo> data;

    public BusinessInfoListObj() {
    }

    public BusinessInfoListObj(String msg, Integer code, List<BusinessInfo> data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<BusinessInfo> getData() {
        return data;
    }

    public void setData(List<BusinessInfo> data) {
        this.data = data;
    }
}
