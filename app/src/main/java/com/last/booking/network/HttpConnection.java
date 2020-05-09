package com.last.booking.network;

import android.os.Debug;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.last.booking.data.Result;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpConnection {

    private static volatile  HttpConnection instance;

    private Gson gson;
    private OkHttpClient client;

    private HttpConnection()
    {
        client = new OkHttpClient();
        gson = new Gson();

    }

    private static final Object lock = new Object();
    public static HttpConnection getInstance()
    {
        if(instance == null)
        {
            synchronized (lock)
            {
                if(instance == null)
                    instance = new HttpConnection();
            }
        }
        return instance;
    }

    public <T> void get(final String url, final Class<T> cls, final NetworkCallback<T> callback)
    {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();


        //OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());
                callback.onResponse(false,new Result.Error(new Exception("Get Method Error",e)));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    String str = response.body().string();
                    Log.d("GET METHOD",url + ":\n" + str);
                    callback.onResponse(true, new Result.Success<>((T) gson.fromJson(str, cls)));
                }
            }
        });

    }

    public <T> void post(final String url, RequestBody body, final Class<T> cls, final NetworkCallback<T> callback)
    {
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();

       // OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onResponse(false, new Result.Error(new Exception("Post Method Error",e)));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    String str = response.body().string();
                    Log.d("POST METHOD",url + ":\n" + str);
                    callback.onResponse(true,new Result.Success<>((T)gson.fromJson(str,cls)));

                }
            }
        });

    }



}
