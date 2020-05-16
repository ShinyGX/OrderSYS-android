package com.last.booking.ui.newInformation;

import org.jetbrains.annotations.Nullable;

public class OldPwdResult {

    @Nullable
    private String error;

    private boolean isDataValid;

    public OldPwdResult(@Nullable String error) {
        this.error = error;
        isDataValid = false;
    }

    public OldPwdResult(boolean isDataValid) {
        this.isDataValid = isDataValid;
        error = null;
    }

    @Nullable
    public String getError() {
        return error;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
