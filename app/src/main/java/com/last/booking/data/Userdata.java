package com.last.booking.data;

import com.last.booking.data.model.UserInfo;

public class Userdata {
    private static volatile Userdata mInstance;

    private UserInfo userInfo;
    private String pwd;

    private boolean isWeiboLogin = false;

    private Userdata(){}

    private final static Object lock = new Object();
    public static Userdata getInstance()
    {
        if(mInstance == null)
        {
            synchronized (lock)
            {
                if(mInstance == null)
                    mInstance = new Userdata();
            }
        }
        return mInstance;
    }

    public boolean isWeiboLogin() {
        return isWeiboLogin;
    }

    public void setWeiboLogin(boolean weiboLogin) {
        isWeiboLogin = weiboLogin;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
