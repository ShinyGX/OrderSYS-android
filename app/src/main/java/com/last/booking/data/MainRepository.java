package com.last.booking.data;

import com.last.booking.data.datasource.FileDataSource;
import com.last.booking.data.datasource.MissionDataSource;
import com.last.booking.data.datasource.UserDataSource;
import com.last.booking.data.model.MissionNoticeInfo;
import com.last.booking.data.model.UserInfo;
import com.last.booking.network.ErrorCode;

import java.io.File;
import java.util.List;

public class MainRepository {

    private static volatile MainRepository mInstance;
    private FileDataSource fileDataSource;
    private UserDataSource userDataSource;
    private MissionDataSource missionDataSource;
    private MainRepository(FileDataSource fileDataSource,UserDataSource userDataSource,MissionDataSource missionDataSource)
    {
        this.fileDataSource = fileDataSource;
        this.userDataSource = userDataSource;
        this.missionDataSource = missionDataSource;
    }

    private final static Object lock = new Object();
    public static MainRepository getInstance(FileDataSource fileDataSource,UserDataSource userDataSource,MissionDataSource missionDataSource)
    {
        if(mInstance == null)
        {
            synchronized (lock)
            {
                if(mInstance == null)
                    mInstance = new MainRepository(fileDataSource,userDataSource,missionDataSource);
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

    public void getNotice(int userId, final RepositoryCallback<List<MissionNoticeInfo>> callback)
    {
        missionDataSource.getNotice(userId, new ResultCallback<List<MissionNoticeInfo>>() {
            @Override
            public void result(Integer code, String msg, List<MissionNoticeInfo> data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }
}
