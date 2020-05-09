package com.last.booking.data;

import com.last.booking.data.datasource.LoginDataSource;
import com.last.booking.data.model.UserInfo;
import com.last.booking.network.ErrorCode;

import java.util.HashMap;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private UserInfo user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final Object lock = new Object();
    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null)
                    instance = new LoginRepository(dataSource);
            }

        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
    }

    private void setLoggedInUser(UserInfo user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public void loginByWeibo(String weibo, final RepositoryCallback<UserInfo> callback)
    {
        dataSource.loginByWeibo(weibo, new ResultCallback<UserInfo>() {
            @Override
            public void result(Integer code, String msg, UserInfo data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }

    public void reset(int id, HashMap<String,String> map, final RepositoryCallback<UserInfo> callback)
    {
        dataSource.reset(id, map, new ResultCallback<UserInfo>() {
            @Override
            public void result(Integer code, String msg, UserInfo data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }

    public void login(String username, String password, final RepositoryCallback<UserInfo> callback) {
        // handle login
        dataSource.login(username, password, new ResultCallback<UserInfo>() {
            @Override
            public void result(Integer code, String msg, UserInfo data) {
                if(code == ErrorCode.SUCCESS)
                    callback.success(data);
                else
                    callback.failed(msg);
            }
        });
    }
}
