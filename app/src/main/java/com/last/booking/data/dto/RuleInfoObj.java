package com.last.booking.data.dto;

import com.last.booking.data.model.RuleEachOfficeInfo;

import java.util.List;

public class RuleInfoObj {

    private String msg;
    private Integer code;
    private List<RuleEachOfficeInfo> data;


    public RuleInfoObj() {
    }

    public RuleInfoObj(String msg, Integer code, List<RuleEachOfficeInfo> data) {
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

    public List<RuleEachOfficeInfo> getData() {
        return data;
    }

    public void setData(List<RuleEachOfficeInfo> data) {
        this.data = data;
    }
}
