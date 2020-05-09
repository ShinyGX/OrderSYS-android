package com.last.booking.data.dto;

import com.last.booking.data.model.CityInfo;

import java.util.List;

public class CityInfoObj {

    private String msg;
    private Integer code;
    private List<CityInfo> data;

    public CityInfoObj() {
    }

    public CityInfoObj(String msg, Integer code, List<CityInfo> data) {
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

    public List<CityInfo> getData() {
        return data;
    }

    public void setData(List<CityInfo> data) {
        this.data = data;
    }
}
