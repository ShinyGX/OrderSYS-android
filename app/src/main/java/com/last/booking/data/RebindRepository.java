package com.last.booking.data;

import com.last.booking.data.datasource.SmsCodeDataSource;
import com.last.booking.data.datasource.UserDataSource;
import com.last.booking.data.model.SmsCode;
import com.last.booking.data.model.UserInfo;
import com.last.booking.network.ErrorCode;

import java.util.HashMap;

public class RebindRepository {

    private static volatile RebindRepository mInstance;
    private SmsCodeDataSource smsCodeDataSource;
    private UserDataSource userDataSource;

    private RebindRepository(SmsCodeDataSource smsCodeDataSource,UserDataSource userDataSource)
    {
        this.smsCodeDataSource = smsCodeDataSource;
        this.userDataSource = userDataSource;
    }


    private final static Object lock = new Object();
    public static RebindRepository getInstance(SmsCodeDataSource smsCodeDataSource,UserDataSource userDataSource)
    {
        if(mInstance == null)
        {
            synchronized (lock)
            {
                if(mInstance == null)
                    mInstance = new RebindRepository(smsCodeDataSource,userDataSource);
            }
        }

        return mInstance;
    }

    public void reset(int id, String phone, final RepositoryCallback<UserInfo> callback)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("phone",phone);
        userDataSource.reset(id, map, new ResultCallback<UserInfo>() {
            @Override
            public void result(Integer code, String msg, UserInfo data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
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
