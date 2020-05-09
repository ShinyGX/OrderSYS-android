package com.last.booking.ui.login;

import com.last.booking.data.model.UserInfo;
import com.last.booking.data.model.WeiboShow;

import org.jetbrains.annotations.Nullable;

public class WeiboLoginResult {

    @Nullable
    private UserInfo userInfo;
    @Nullable
    private String errMsg;

    @Nullable
    private WeiboShow weiboShow;

    public WeiboLoginResult(@Nullable WeiboShow weiboShow) {
        this.weiboShow = weiboShow;
    }

    public WeiboLoginResult(@Nullable UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public WeiboLoginResult(@Nullable String errMsg) {
        this.errMsg = errMsg;
    }

    @Nullable
    public WeiboShow getWeiboShow() {
        return weiboShow;
    }

    @Nullable
    public UserInfo getUserInfo() {
        return userInfo;
    }

    @Nullable
    public String getErrMsg() {
        return errMsg;
    }
}
