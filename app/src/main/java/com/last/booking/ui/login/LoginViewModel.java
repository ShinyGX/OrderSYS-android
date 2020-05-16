package com.last.booking.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.last.booking.data.LoginRepository;
import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.Userdata;
import com.last.booking.data.WeiboRepository;
import com.last.booking.data.model.UserInfo;
import com.last.booking.R;
import com.last.booking.data.model.WeiboShow;

import java.util.HashMap;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private MutableLiveData<WeiboLoginResult> weiboLoginResult = new MutableLiveData<>();

    private LoginRepository loginRepository;
    private WeiboRepository weiboRepository;

    private int userId;

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

    public void login(String username, final String password) {
        // can be launched in a separate asynchronous job
        loginRepository.login(username, password, new RepositoryCallback<UserInfo>() {
            @Override
            public void success(UserInfo data) {
                loginResult.postValue(new LoginResult(new LoggedInUserView(data.getUserName(),data.getUserId())));
                Userdata.getInstance().setUserInfo(data);
                Userdata.getInstance().setPwd(password);
                userId = data.getUserId();
            }
            @Override
            public void failed(String msg) {
                loginResult.postValue(new LoginResult(msg));
            }
        });
    }

    public void loginByWeibo(String weiboId)
    {
        loginRepository.loginByWeibo(weiboId, new RepositoryCallback<UserInfo>() {
            @Override
            public void success(UserInfo data) {
                weiboLoginResult.postValue(new WeiboLoginResult(data));
                userId = data.getUserId();
                Userdata.getInstance().setUserInfo(data);
            }

            @Override
            public void failed(String msg) {
                weiboLoginResult.postValue(new WeiboLoginResult(msg));
            }
        });
    }

    public void getWeiboShow(Context context)
    {
        weiboRepository.getShow(context, new RepositoryCallback<WeiboShow>() {
            @Override
            public void success(WeiboShow data) {
                weiboLoginResult.postValue(new WeiboLoginResult(data));
            }

            @Override
            public void failed(String msg) {
                weiboLoginResult.postValue(new WeiboLoginResult(msg));
            }
        });
    }

    public void reset(HashMap<String,String> map)
    {
        loginRepository.reset(userId, map, new RepositoryCallback<UserInfo>() {
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

    public int getUserId() {
        return userId;
    }

    public MutableLiveData<WeiboLoginResult> getWeiboLoginResult() {
        return weiboLoginResult;
    }
}
