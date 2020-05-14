package com.last.booking.ui.register;

import org.jetbrains.annotations.Nullable;

public class PasswordFormState {
    @Nullable
    private String pwdError;

    private boolean isDataValid;

    public PasswordFormState(@Nullable String pwdError) {
        this.pwdError = pwdError;
        isDataValid = false;
    }

    public PasswordFormState(boolean isDataValid) {
        pwdError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public String getPwdError() {
        return pwdError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
