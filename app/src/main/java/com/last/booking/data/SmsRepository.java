package com.last.booking.data;

import com.last.booking.data.datasource.SmsCodeDataSource;
import com.last.booking.data.model.SmsCode;
import com.last.booking.network.ErrorCode;

public class SmsRepository {
    private static volatile SmsRepository mInstance;
    private SmsCodeDataSource smsCodeDataSource;
    private SmsRepository(SmsCodeDataSource smsCodeDataSource)
    {
        this.smsCodeDataSource = smsCodeDataSource;
    }

    private static final Object lock = new Object();
    public static SmsRepository getInstance(SmsCodeDataSource dataSource)
    {
        if(mInstance == null)
        {
            synchronized (lock)
            {
                if(mInstance == null)
                    mInstance = new SmsRepository(dataSource);
            }
        }

        return mInstance;
    }

    public void getCode(String phone, final RepositoryCallback<SmsCode> callback)
    {
        smsCodeDataSource.getCode(phone, new ResultCallback<SmsCode>() {
            @Override
            public void result(Integer code, String msg, SmsCode data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }


}
