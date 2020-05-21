package com.last.booking.data.datasource;

import android.util.Log;

import com.last.booking.data.Result;
import com.last.booking.data.ResultCallback;
import com.last.booking.data.dto.BaseInfoObj;
import com.last.booking.data.dto.BookInfoObj;
import com.last.booking.data.dto.MissionStatusObj;
import com.last.booking.data.model.BookInfo;
import com.last.booking.data.model.MissionStatusInfo;
import com.last.booking.network.API;
import com.last.booking.network.HttpConnection;
import com.last.booking.network.NetworkCallback;

import java.util.Date;
import java.util.List;

import okhttp3.FormBody;

public class MissionDataSource {

    public void getUsefulInfo(int id, final ResultCallback<List<BookInfo>> callback)
    {
        HttpConnection.getInstance()
                .post(API.Mission.getUsefulInfo + "id=" + id,
                        new FormBody.Builder().build(),
                        BookInfoObj.class,
                        new NetworkCallback<BookInfoObj>() {
                            @Override
                            public void onResponse(boolean success, Result<BookInfoObj> result) {
                                if(success)
                                {
                                    BookInfoObj data = ((Result.Success<BookInfoObj>)result).getData();
                                    callback.result(data.getCode(),data.getMsg(),data.getData());
                                }
                                else
                                    showErrorMessage(result);
                            }
                        });
    }

    public void add(int userId, int officeId, int businessId, Date time, final ResultCallback<String> callback)
    {
        HttpConnection.getInstance()
                .post(API.Mission.add + "userId=" + userId + "&officeId=" + officeId + "&businessId=" + businessId + "&time=" + time.getTime(),
                        new FormBody.Builder().build(), BaseInfoObj.class,
                        new NetworkCallback<BaseInfoObj>() {
                            @Override
                            public void onResponse(boolean success, Result<BaseInfoObj> result) {
                                if(success)
                                {
                                    BaseInfoObj data = ((Result.Success<BaseInfoObj>)result).getData();
                                    callback.result(data.getCode(),data.getMsg(),data.getData());
                                }
                                else
                                    showErrorMessage(result);
                            }
                        });
    }


    public void getUserMission(int userId, final ResultCallback<List<MissionStatusInfo>> callback)
    {
        HttpConnection.getInstance()
                .get(API.Mission.getUserMission + "id=" + userId,
                        MissionStatusObj.class,
                        new NetworkCallback<MissionStatusObj>() {
                            @Override
                            public void onResponse(boolean success, Result<MissionStatusObj> result) {
                                if(success)
                                {
                                    MissionStatusObj data = ((Result.Success<MissionStatusObj>)result).getData();
                                    callback.result(data.getCode(),data.getMsg(),data.getData());
                                }
                                else
                                    showErrorMessage(result);
                            }
                        });
    }

    public void cancel(int missionId, final ResultCallback<String> callback)
    {
        HttpConnection.getInstance()
                .get(API.Mission.cancel + "id=" + missionId,
                        BaseInfoObj.class,
                        new NetworkCallback<BaseInfoObj>() {
                            @Override
                            public void onResponse(boolean success, Result<BaseInfoObj> result) {
                                if(success)
                                {
                                    BaseInfoObj data = ((Result.Success<BaseInfoObj>)result).getData();
                                    callback.result(data.getCode(),data.getMsg(),data.getData());
                                }
                                else
                                    showErrorMessage(result);
                            }
                        });
    }

    private <T> void showErrorMessage(Result<T> error)
    {
        Log.d("MISSION",((Result.Error) error).getError().getMessage());
    }
}
