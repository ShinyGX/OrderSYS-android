package com.last.booking.data;

public interface ResultCallback <T>{
    void result(Integer code, String msg, T data);
}
