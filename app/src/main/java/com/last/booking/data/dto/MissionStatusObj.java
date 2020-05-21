package com.last.booking.data.dto;

import com.last.booking.data.model.MissionStatusInfo;

import java.util.List;

public class MissionStatusObj {
    private String msg;
    private Integer code;
    private List<MissionStatusInfo> data;

    public MissionStatusObj() {
    }

    public MissionStatusObj(String msg, Integer code, List<MissionStatusInfo> data) {
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

    public List<MissionStatusInfo> getData() {
        return data;
    }

    public void setData(List<MissionStatusInfo> data) {
        this.data = data;
    }
}
