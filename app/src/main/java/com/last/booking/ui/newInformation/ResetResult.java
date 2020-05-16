package com.last.booking.ui.newInformation;

import com.last.booking.data.model.UserInfo;

import org.jetbrains.annotations.Nullable;

public class ResetResult {

    @Nullable
    private UserInfo userdata;

    @Nullable
    private String errorMsg;

    public ResetResult(@Nullable UserInfo userdata) {
        this.userdata = userdata;
    }

    public ResetResult(@Nullable String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Nullable
    public UserInfo getUserdata() {
        return userdata;
    }

    @Nullable
    public String getErrorMsg() {
        return errorMsg;
    }
}
