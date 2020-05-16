package com.last.booking.ui.main;

import com.last.booking.data.model.UserInfo;

import org.jetbrains.annotations.Nullable;

public class UserInfoResult {
    @Nullable
    private UserInfo userInfo;

    @Nullable
    private String error;

    public UserInfoResult(@Nullable UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfoResult(@Nullable String error) {
        this.error = error;
    }

    @Nullable
    public UserInfo getUserInfo() {
        return userInfo;
    }

    @Nullable
    public String getError() {
        return error;
    }
}
