package com.last.booking.data.dto;

import com.last.booking.data.model.BookInfo;

import java.util.List;

public class BookInfoObj {
    private String msg;
    private Integer code;
    private List<BookInfo> data;


    public BookInfoObj() {
    }

    public BookInfoObj(String msg, Integer code, List<BookInfo> data) {
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

    public List<BookInfo> getData() {
        return data;
    }

    public void setData(List<BookInfo> data) {
        this.data = data;
    }
}
