package com.last.booking.data.dto;

import com.last.booking.data.model.SmsCode;

public class SmsCodeInfo {
    private String msg;
    private Integer code;
    private SmsCode data;

    public SmsCodeInfo() {
    }

    public SmsCodeInfo(String msg, Integer code, SmsCode data) {
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

    public SmsCode getData() {
        return data;
    }

    public void setData(SmsCode data) {
        this.data = data;
    }
}
