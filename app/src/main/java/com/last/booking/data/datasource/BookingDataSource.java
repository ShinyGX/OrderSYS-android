package com.last.booking.data.datasource;

import android.util.Log;

import com.last.booking.data.Result;
import com.last.booking.data.ResultCallback;
import com.last.booking.data.dto.CityInfoObj;
import com.last.booking.data.dto.OfficeInfoObj;
import com.last.booking.data.model.OfficeInfo;
import com.last.booking.data.model.CityInfo;
import com.last.booking.network.API;
import com.last.booking.network.HttpConnection;
import com.last.booking.network.NetworkCallback;

import java.util.List;

import okhttp3.FormBody;

public class BookingDataSource {



    public void getCityInfo(final ResultCallback<List<CityInfo>> callback) {
        HttpConnection.getInstance()
                .post(API.Office.getCity, new FormBody.Builder().build(),
                        CityInfoObj.class,
                        new NetworkCallback<CityInfoObj>() {
                            @Override
                            public void onResponse(boolean success, Result<CityInfoObj> result) {
                                if(success)
                                    callback.result(((Result.Success<CityInfoObj>)result).getData().getCode(),
                                            ((Result.Success<CityInfoObj>)result).getData().getMsg(),
                                            ((Result.Success<CityInfoObj>)result).getData().getData());
                                else
                                    showErrorMessage(result);
                            }
                        });
    }

    public void getCityOfficesInfo(int id, final ResultCallback<List<OfficeInfo>> callback)
    {
        HttpConnection.getInstance()
                .post(API.Office.getCityOffices + "cityId=" + id,
                        new FormBody.Builder().build(),
                        OfficeInfoObj.class,
                        new NetworkCallback<OfficeInfoObj>() {
                            @Override
                            public void onResponse(boolean success, Result<OfficeInfoObj> result) {
                                if(success)
                                {
                                    OfficeInfoObj data = ((Result.Success<OfficeInfoObj>)result).getData();
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
