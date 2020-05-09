package com.last.booking.data;

import android.content.Context;

import com.last.booking.data.model.WeiboShow;
import com.last.booking.network.HttpConnection;
import com.last.booking.network.NetworkCallback;
import com.last.booking.network.Weibo;

public class WeiboDataSource {


    public void getShow(Context context, final ResultCallback<WeiboShow> callback)
    {
        HttpConnection.getInstance().get(
                Weibo.API.SHOW + "access_token=" + AccessTokenKeeper.readAccessToken(context).getToken() + "&uid="
                        + AccessTokenKeeper.readAccessToken(context).getUid(),
                WeiboShow.class,
                new NetworkCallback<WeiboShow>() {
                    @Override
                    public void onResponse(boolean success, Result<WeiboShow> result) {
                        if(success)
                            callback.result(0,"",((Result.Success<WeiboShow>)result).getData());
                    }
                }
        );
    }
}
