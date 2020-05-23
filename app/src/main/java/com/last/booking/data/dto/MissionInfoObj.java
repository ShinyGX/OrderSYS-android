package com.last.booking.data.dto;

public class MissionInfoObj {
    private String msg;
    private Integer code;
    private Integer data;


    public MissionInfoObj() {
    }

    public MissionInfoObj(String msg, Integer code, Integer data) {
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

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
