package com.last.booking.ui.newInformation;

import org.jetbrains.annotations.Nullable;

public class NewPwdResult {

    @Nullable
    private String error;

    private boolean isDataValid;

    public NewPwdResult(@Nullable String error) {
        this.error = error;
        isDataValid = false;
    }

    public NewPwdResult(boolean isDataValid) {
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
