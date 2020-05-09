package com.last.booking.data;

public interface RepositoryCallback<T> {

    void success(T data);
    void failed(String msg);

}
