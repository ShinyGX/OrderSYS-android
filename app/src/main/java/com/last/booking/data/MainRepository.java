package com.last.booking.data;

import com.last.booking.data.datasource.FileDataSource;
import com.last.booking.data.datasource.UserDataSource;
import com.last.booking.data.model.UserInfo;
import com.last.booking.network.ErrorCode;

import java.io.File;

public class MainRepository {

    private static volatile MainRepository mInstance;
    private FileDataSource fileDataSource;
    private UserDataSource userDataSource;
    private MainRepository(FileDataSource fileDataSource,UserDataSource userDataSource)
    {
        this.fileDataSource = fileDataSource;
        this.userDataSource = userDataSource;
    }

    private final static Object lock = new Object();
    public static MainRepository getInstance(FileDataSource fileDataSource,UserDataSource userDataSource)
    {
        if(mInstance == null)
        {
            synchronized (lock)
            {
                if(mInstance == null)
                    mInstance = new MainRepository(fileDataSource,userDataSource);
            }
        }

        return mInstance;
    }


    public void userInfo(int id, final RepositoryCallback<UserInfo> callback)
    {
        userDataSource.userInfo(id, new ResultCallback<UserInfo>() {
            @Override
            public void result(Integer code, String msg, UserInfo data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }

    public void uploadIcon(int id, File img, final RepositoryCallback<String> callback)
    {
        fileDataSource.upload(id, img, new ResultCallback<String>() {
            @Override
            public void result(Integer code, String msg, String data) {
                if(code == 200)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }

}
