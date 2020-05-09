package com.last.booking.network;

import com.last.booking.data.Result;

public interface NetworkCallback<T> {
    void onResponse(boolean success, Result<T> result);
}
