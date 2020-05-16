package com.last.booking.data.datasource;

import com.last.booking.data.Result;
import com.last.booking.data.ResultCallback;
import com.last.booking.network.API;
import com.last.booking.network.HttpConnection;
import com.last.booking.network.NetworkCallback;

import java.io.File;

public class FileDataSource {

    public void upload(int id, File file, final ResultCallback<String> callback)
    {
        HttpConnection.getInstance()
                .uploadFile(file, API.File.upload + "id=" + id, new NetworkCallback<Boolean>() {
                    @Override
                    public void onResponse(boolean success, Result<Boolean> result) {
                        if(success)
                            callback.result(200,"","success");
                        else
                            callback.result(0,"upload filed","");
                    }
                });
    }
}
