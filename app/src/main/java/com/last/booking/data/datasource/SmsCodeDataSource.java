package com.last.booking.data.datasource;

import com.last.booking.data.Result;
import com.last.booking.data.ResultCallback;
import com.last.booking.data.dto.SmsCodeInfo;
import com.last.booking.data.model.SmsCode;
import com.last.booking.network.API;
import com.last.booking.network.HttpConnection;
import com.last.booking.network.NetworkCallback;

public class SmsCodeDataSource {

    public void getCode(String phone, final ResultCallback<SmsCode> callback)
    {
        HttpConnection.getInstance()
                .get(API.Sms.code + "phone=" + phone,
                        SmsCodeInfo.class,
                        new NetworkCallback<SmsCodeInfo>() {
                            @Override
                            public void onResponse(boolean success, Result<SmsCodeInfo> result) {
                                if(success)
                                    callback.result(((Result.Success<SmsCodeInfo>)result).getData().getCode(),
                                            ((Result.Success<SmsCodeInfo>)result).getData().getMsg(),
                                            ((Result.Success<SmsCodeInfo>)result).getData().getData());
                            }
                        });
    }
}
