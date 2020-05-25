package com.last.booking.data.dto;

import com.last.booking.data.model.MissionNoticeInfo;

import java.util.List;

public class MissionNoticeObj {

    private String msg;
    private Integer code;
    private List<MissionNoticeInfo> data;

    public MissionNoticeObj() {
    }

    public MissionNoticeObj(String msg, Integer code, List<MissionNoticeInfo> data) {
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

    public List<MissionNoticeInfo> getData() {
        return data;
    }

    public void setData(List<MissionNoticeInfo> data) {
        this.data = data;
    }
}
