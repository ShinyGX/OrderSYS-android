package com.last.booking.data.datasource;

import com.last.booking.data.Result;
import com.last.booking.data.ResultCallback;
import com.last.booking.data.dto.BusinessInfoObj;
import com.last.booking.data.dto.BusinessInfoListObj;
import com.last.booking.data.model.BusinessInfo;
import com.last.booking.network.API;
import com.last.booking.network.HttpConnection;
import com.last.booking.network.NetworkCallback;

import java.util.List;

import okhttp3.FormBody;

public class BusinessDataSource {

    public void getBusinessInfo(int id, final ResultCallback<BusinessInfo> callback)
    {
        HttpConnection.getInstance().get(
                API.Business.business + "id=" + id,
                BusinessInfoObj.class,
                new NetworkCallback<BusinessInfoObj>() {
                    @Override
                    public void onResponse(boolean success, Result<BusinessInfoObj> result) {
                        if(success)
                        {
                            System.out.println(result.toString());
                            BusinessInfoObj info = ((Result.Success<BusinessInfoObj>)result).getData();
                            callback.result(info.getCode(),info.getMsg(),info.getData());
                        }
                        else
                            System.out.println(((Result.Error)result).getError().getMessage());
                    }
                }
        );
    }

    public void getAllBusinessInfo(final ResultCallback<List<BusinessInfo>> callback)
    {
        HttpConnection.getInstance().post(API.Business.getAllBusiness,
                new FormBody.Builder().build(),
                BusinessInfoListObj.class,
                new NetworkCallback<BusinessInfoListObj>() {
                    @Override
                    public void onResponse(boolean success, Result<BusinessInfoListObj> result) {
                        if(success)
                        {
                            System.out.println(result.toString());
                            BusinessInfoListObj info = ((Result.Success<BusinessInfoListObj>)result).getData();
                            callback.result(info.getCode(),info.getMsg(),info.getData());
                        }
                        else
                            System.out.println(((Result.Error)result).getError().getMessage());
                    }
                });
    }
}
