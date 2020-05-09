package com.last.booking.data;

import com.last.booking.data.dto.UserInfoObj;
import com.last.booking.data.model.UserInfo;
import com.last.booking.network.API;
import com.last.booking.network.HttpConnection;
import com.last.booking.network.NetworkCallback;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

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


}
