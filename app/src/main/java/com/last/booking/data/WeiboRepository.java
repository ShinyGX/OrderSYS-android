package com.last.booking.data;

import android.content.Context;

import com.last.booking.data.model.WeiboShow;

public class WeiboRepository {
    private static volatile WeiboRepository instance;
    private static WeiboDataSource weiboDataSource;
    private WeiboRepository(WeiboDataSource dataSource)
    {
        weiboDataSource = dataSource;
    }

    private static final Object lock = new Object();
    public static WeiboRepository getInstance(WeiboDataSource weiboDataSource)
    {
        if(instance == null)
        {
            synchronized (lock)
            {
                if(instance == null)
                    instance = new WeiboRepository(weiboDataSource);
            }
        }

        return instance;
    }

    public void getShow(Context context, final RepositoryCallback<WeiboShow> callback)
    {
        weiboDataSource.getShow(context, new ResultCallback<WeiboShow>() {
            @Override
            public void result(Integer code, String msg, WeiboShow data) {
                callback.success(data);
            }
        });
    }
}
