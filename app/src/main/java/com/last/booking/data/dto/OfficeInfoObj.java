package com.last.booking.data.dto;

import com.last.booking.data.model.OfficeInfo;

import java.util.List;

public class OfficeInfoObj {

    private String msg;
    private Integer code;
    private List<OfficeInfo> data;

    public OfficeInfoObj() {
    }

    public OfficeInfoObj(String msg, Integer code, List<OfficeInfo> data) {
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

    public List<OfficeInfo> getData() {
        return data;
    }

    public void setData(List<OfficeInfo> data) {
        this.data = data;
    }
}
