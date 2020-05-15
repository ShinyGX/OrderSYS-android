package com.last.booking.data;

import com.last.booking.data.model.UserInfo;

public class Userdata {
    private static volatile Userdata mInstance;

    private UserInfo userInfo;

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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
