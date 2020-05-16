package com.last.booking.data;

import com.last.booking.data.datasource.UserDataSource;
import com.last.booking.data.model.UserInfo;
import com.last.booking.network.ErrorCode;

import java.util.HashMap;

public class NewInformationRepository {

    private static volatile NewInformationRepository mInstance;
    private UserDataSource userDataSource;
    private NewInformationRepository(UserDataSource userDataSource)
    {
        this.userDataSource = userDataSource;
    }

    private static final Object lock = new Object();
    public static NewInformationRepository getInstance(UserDataSource userDataSource)
    {
        if(mInstance == null)
        {
            synchronized (lock)
            {
                if(mInstance ==null)
                {
                    mInstance = new NewInformationRepository(userDataSource);
                }
            }
        }

        return mInstance;
    }

    public void reset(int id,String pwd,final RepositoryCallback<UserInfo> callback)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("pwd",pwd);
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

}
