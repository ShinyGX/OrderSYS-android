package com.last.booking.data.dto;

import com.last.booking.data.model.BusinessInfo;

public class BusinessInfoObj {
    private String msg;
    private Integer code;
    private BusinessInfo data;


    public BusinessInfoObj() {
    }

    public BusinessInfoObj(String msg, Integer code, BusinessInfo data) {
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

    public BusinessInfo getData() {
        return data;
    }

    public void setData(BusinessInfo data) {
        this.data = data;
    }
}
