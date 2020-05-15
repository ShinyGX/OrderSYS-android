package com.last.booking.data;

import com.last.booking.data.datasource.SmsCodeDataSource;
import com.last.booking.data.datasource.UserDataSource;
import com.last.booking.data.model.SmsCode;
import com.last.booking.data.model.UserInfo;
import com.last.booking.network.ErrorCode;

public class RegisterRepository {
    private static volatile RegisterRepository mInstance;
    private SmsCodeDataSource smsCodeDataSource;
    private UserDataSource userDataSource;
    private RegisterRepository(SmsCodeDataSource smsCodeDataSource,UserDataSource userDataSource)
    {
        this.smsCodeDataSource = smsCodeDataSource;
        this.userDataSource = userDataSource;
    }

    private static final Object lock = new Object();
    public static RegisterRepository getInstance(SmsCodeDataSource dataSource,UserDataSource userDataSource)
    {
        if(mInstance == null)
        {
            synchronized (lock)
            {
                if(mInstance == null)
                    mInstance = new RegisterRepository(dataSource,userDataSource);
            }
        }

        return mInstance;
    }

    public void login(String phone, String pwd, String name, final RepositoryCallback<UserInfo> callback)
    {
        userDataSource.register(phone, pwd, name, new ResultCallback<UserInfo>() {
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
