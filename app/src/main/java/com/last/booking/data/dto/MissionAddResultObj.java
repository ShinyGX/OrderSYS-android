package com.last.booking.data.dto;

import com.last.booking.data.model.MissionAddResult;

public class MissionAddResultObj {

    private String msg;
    private Integer code;
    private MissionAddResult data;

    public MissionAddResultObj() {
    }

    public MissionAddResultObj(String msg, Integer code, MissionAddResult missionAddResult) {
        this.msg = msg;
        this.code = code;
        this.data = missionAddResult;
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

    public MissionAddResult getData() {
        return data;
    }

    public void setData(MissionAddResult data) {
        this.data = data;
    }
}
