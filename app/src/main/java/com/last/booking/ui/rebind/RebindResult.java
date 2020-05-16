package com.last.booking.ui.rebind;

import com.last.booking.data.model.UserInfo;

import org.jetbrains.annotations.Nullable;

public class RebindResult {


    @Nullable
    private UserInfo userInfo;

    @Nullable
    private String error;

    private boolean valid;

    public RebindResult(@Nullable UserInfo userInfo) {
        this.userInfo = userInfo;
        valid = true;
        error = null;
    }

    public RebindResult(@Nullable String error) {
        this.error = error;
        valid = false;
        userInfo = null;
    }

    public RebindResult(boolean valid) {
        this.valid = valid;
        userInfo = null;
        error = null;
    }

    @Nullable
    public UserInfo getUserInfo() {
        return userInfo;
    }

    @Nullable
    public String getError() {
        return error;
    }

    public boolean isValid() {
        return valid;
    }
}
