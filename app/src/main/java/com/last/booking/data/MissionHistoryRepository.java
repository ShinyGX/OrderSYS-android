package com.last.booking.data;

import com.last.booking.data.datasource.MissionDataSource;
import com.last.booking.data.model.MissionStatusInfo;
import com.last.booking.network.ErrorCode;

import java.util.List;

public class MissionHistoryRepository {

    private static MissionHistoryRepository mInstance;
    private MissionDataSource dataSource;
    private MissionHistoryRepository(MissionDataSource dataSource)
    {
        this.dataSource =dataSource;
    }

    private final static Object lock = new Object();
    public static MissionHistoryRepository getInstance(MissionDataSource dataSource)
    {
        if(mInstance == null)
        {
            synchronized (lock)
            {
                if(mInstance == null)
                    mInstance = new MissionHistoryRepository(dataSource);
            }
        }

        return mInstance;
    }

    public void getMissionInfo(int userId, final RepositoryCallback<List<MissionStatusInfo>> callback)
    {
        dataSource.getUserMission(userId, new ResultCallback<List<MissionStatusInfo>>() {
            @Override
            public void result(Integer code, String msg, List<MissionStatusInfo> data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }

    public void cancel(int id, final RepositoryCallback<String> callback)
    {
        dataSource.cancel(id, new ResultCallback<String>() {
            @Override
            public void result(Integer code, String msg, String data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }
}
