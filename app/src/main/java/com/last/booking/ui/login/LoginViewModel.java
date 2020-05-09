package com.last.booking.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.last.booking.data.AccessTokenKeeper;
import com.last.booking.data.LoginRepository;
import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.WeiboDataSource;
import com.last.booking.data.WeiboRepository;
import com.last.booking.data.model.UserInfo;
import com.last.booking.R;
import com.last.booking.data.model.WeiboShow;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    private WeiboRepository weiboRepository;

    public LoginViewModel(LoginRepository loginRepository, WeiboRepository weiboRepository) {
        this.loginRepository = loginRepository;
        this.weiboRepository = weiboRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        loginRepository.login(username, password, new RepositoryCallback<UserInfo>() {
            @Override
            public void success(UserInfo data) {
                loginResult.postValue(new LoginResult(new LoggedInUserView(data.getUserName(),data.getUserId())));
            }
            @Override
            public void failed(String msg) {
                loginResult.postValue(new LoginResult(msg));
            }
        });
    }

    public void getWeiboName(Context context)
    {
        weiboRepository.getShow(context, new RepositoryCallback<WeiboShow>() {
            @Override
            public void success(WeiboShow data) {
                loginResult.postValue(new LoginResult(new LoggedInUserView(data.getScreen_name(),1)));
            }

            @Override
            public void failed(String msg) {

            }
        });
    }


    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        return username != null && !username.isEmpty();
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
