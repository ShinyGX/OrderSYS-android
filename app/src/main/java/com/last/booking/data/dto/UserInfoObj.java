package com.last.booking.data.dto;

import com.last.booking.data.model.UserInfo;

import org.jetbrains.annotations.NotNull;

public class UserInfoObj {

    private String msg;
    private Integer code;
    private UserInfo data;


    public UserInfoObj() {
    }

    public UserInfoObj(String msg, Integer code, UserInfo data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    @NotNull
    @Override
    public String toString() {
        return "UserInfoObj{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
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

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }
}
