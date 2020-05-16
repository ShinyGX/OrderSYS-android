package com.last.booking.network;

import android.util.Log;

import com.google.gson.Gson;
import com.last.booking.data.Result;

import org.jetbrains.annotations.NotNull;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

    public void uploadFile(final File file, final String urlstr, final NetworkCallback<Boolean> callback)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean result = false;
                final String boundary = "letv";
                final String prefix = "--",lineEnd = "\r\n";
                final String content_type = "multipart/form-data";
                try {
                    URL url = new URL(urlstr);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setReadTimeout(3000);
                    httpURLConnection.setConnectTimeout(3000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Charset","utf-8");
                    httpURLConnection.setRequestProperty("connection","keep-alive");
                    httpURLConnection.setRequestProperty("Content-Type",
                            content_type + ";boundary=" + boundary);

                    if(file != null)
                    {
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());

                        String sb = prefix +
                                boundary +
                                lineEnd +
                                "Content-Disposition: form-data; name=\"img\";" +
                                "filename=\"" + file.getName() + "\"" + lineEnd +
                                "Content-Type: application/octet-stream; charset=utf-8" +
                                lineEnd +
                                lineEnd;

                        dataOutputStream.write(sb.getBytes());

                        InputStream inputStream = new FileInputStream(file);
                        byte[] bytes = new byte[1024 * 1024];
                        int len  =0;

                        while ((len = inputStream.read(bytes)) != -1)
                        {
                            dataOutputStream.write(bytes,0,len);
                        }

                        inputStream.close();
                        dataOutputStream.write(lineEnd.getBytes());

                        byte[] endData = (prefix + boundary + prefix + lineEnd).getBytes();
                        dataOutputStream.write(endData);

                        dataOutputStream.flush();

                        int res = httpURLConnection.getResponseCode();
                        if(res == 200)
                            result = true;

                        callback.onResponse(result,null);

                    }

                    callback.onResponse(result,null);
                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();




    }

    public <T> void get(String url, final Class<T> cls, final NetworkCallback<T> callback)
    {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        Log.d("GET METHOD",url);
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
                    Log.d("GET METHOD",str);
                    callback.onResponse(true, new Result.Success<>((T) gson.fromJson(str, cls)));
                }
            }
        });

    }

    public <T> void post(String url, RequestBody body, final Class<T> cls, final NetworkCallback<T> callback)
    {
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();

        Log.d("POST METHOD",url);
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
                    Log.d("POST METHOD",str);
                    callback.onResponse(true,new Result.Success<>((T)gson.fromJson(str,cls)));

                }
            }
        });

    }



}
