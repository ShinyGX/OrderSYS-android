package com.last.booking.ui.register;

import com.last.booking.data.model.UserInfo;

import org.jetbrains.annotations.Nullable;

public class RegisterResult {

    @Nullable
    private String error;

    @Nullable
    private UserInfo userInfo;

    public RegisterResult(@Nullable String error) {
        this.error = error;
    }

    public RegisterResult(@Nullable UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Nullable
    public String getError() {
        return error;
    }

    @Nullable
    public UserInfo getUserInfo() {
        return userInfo;
    }
}
