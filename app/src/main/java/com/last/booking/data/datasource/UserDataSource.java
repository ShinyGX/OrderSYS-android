package com.last.booking.data.datasource;

import com.last.booking.data.Result;
import com.last.booking.data.ResultCallback;
import com.last.booking.data.dto.UserInfoObj;
import com.last.booking.data.model.UserInfo;
import com.last.booking.network.API;
import com.last.booking.network.HttpConnection;
import com.last.booking.network.NetworkCallback;

import java.util.HashMap;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class UserDataSource {

    public void login(String username, String password, final ResultCallback<UserInfo> callback) {
        HttpConnection.getInstance().
                get(API.User.login + "phone=" + username + "&pwd=" + password, UserInfoObj.class,
                        new NetworkCallback<UserInfoObj>() {
                    @Override
                    public void onResponse(boolean success, Result<UserInfoObj> result) {
                        if(success)
                        {
                            System.out.println(result.toString());
                            UserInfoObj info = ((Result.Success<UserInfoObj>)result).getData();
                            callback.result(info.getCode(),info.getMsg(),info.getData());
                        }
                        else
                            System.out.println(((Result.Error)result).getError().getMessage());
                    }
                });
    }

    public void loginByWeibo(String weibo, final ResultCallback<UserInfo> callback)
    {
        HttpConnection.getInstance().get(
                API.User.loginByWeibo + "weibo=" + weibo,
                UserInfoObj.class,
                new NetworkCallback<UserInfoObj>() {
                    @Override
                    public void onResponse(boolean success, Result<UserInfoObj> result) {
                        if(success)
                        {
                            System.out.println(result.toString());
                            UserInfoObj info = ((Result.Success<UserInfoObj>)result).getData();
                            callback.result(info.getCode(),info.getMsg(),info.getData());
                        }
                        else
                            System.out.println(((Result.Error)result).getError().getMessage());
                    }
                }
        );
    }

    public void reset(int id, HashMap<String,String> param, final ResultCallback<UserInfo> callback)
    {
        StringBuilder url  = new StringBuilder();
        for(String key : param.keySet())
        {
            url.append(key).append("=").append(param.get(key)).append("&");
        }
        String str_url = url.toString();
        if(!str_url.isEmpty())
            str_url = str_url.substring(0,str_url.lastIndexOf('&'));

        String totalUrl = API.User.reset + "id=" + id;
        if(!str_url.isEmpty())
            totalUrl += "&" + str_url;
        HttpConnection.getInstance().get(totalUrl,
                UserInfoObj.class,
                new NetworkCallback<UserInfoObj>() {
                    @Override
                    public void onResponse(boolean success, Result<UserInfoObj> result) {
                        if(success)
                        {
                            System.out.println(result.toString());
                            UserInfoObj info = ((Result.Success<UserInfoObj>)result).getData();
                            callback.result(info.getCode(),info.getMsg(),info.getData());
                        }
                        else
                            System.out.println(((Result.Error)result).getError().getMessage());
                    }
                });
    }

    public void register(String phone, String pwd, String name, final ResultCallback<UserInfo> callback)
    {
        HttpConnection.getInstance()
                .get(API.User.register + "phone=" + phone + "&pwd=" + pwd + "&name=" + name,
                        UserInfoObj.class,
                        new NetworkCallback<UserInfoObj>() {
                            @Override
                            public void onResponse(boolean success, Result<UserInfoObj> result) {
                                if(success)
                                {
                                    UserInfoObj info = ((Result.Success<UserInfoObj>)result).getData();
                                    callback.result(info.getCode(),info.getMsg(),info.getData());
                                }
                                else
                                    System.out.println(((Result.Error)result).getError().getMessage());
                            }
                        });
    }


}
