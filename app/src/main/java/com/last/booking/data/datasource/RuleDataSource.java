package com.last.booking.data.datasource;

import android.util.Log;

import com.last.booking.data.Result;
import com.last.booking.data.ResultCallback;
import com.last.booking.data.dto.RuleInfoObj;
import com.last.booking.data.model.RuleEachOfficeInfo;
import com.last.booking.network.API;
import com.last.booking.network.HttpConnection;
import com.last.booking.network.NetworkCallback;

import java.util.List;

import okhttp3.FormBody;

public class RuleDataSource {


    public void getAll(final ResultCallback<List<RuleEachOfficeInfo>> callback)
    {
        HttpConnection.getInstance()
                .post(API.Rule.getAllEachOffice,
                        new FormBody.Builder().build(),
                        RuleInfoObj.class,
                        new NetworkCallback<RuleInfoObj>() {
                            @Override
                            public void onResponse(boolean success, Result<RuleInfoObj> result) {
                                if(success)
                                {
                                    RuleInfoObj data = ((Result.Success<RuleInfoObj>)result).getData();
                                    callback.result(data.getCode(),data.getMsg(),data.getData());
                                }
                                else
                                    showErrorMessage(result);
                            }
                        });
    }

    private <T> void showErrorMessage(Result<T> error)
    {
        Log.d("Role",((Result.Error) error).getError().getMessage());
    }

}
