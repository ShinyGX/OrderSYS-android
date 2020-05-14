package com.last.booking.ui.register;

import org.jetbrains.annotations.Nullable;

public class RepeatPwdFormState {
    @Nullable
    private String repeatPwdError;
    private boolean isDataValid;

    public RepeatPwdFormState(@Nullable String repeatPwdError) {
        this.repeatPwdError = repeatPwdError;
        isDataValid = false;
    }

    public RepeatPwdFormState(boolean isDataValid) {
        this.isDataValid = isDataValid;
        repeatPwdError = null;
    }

    @Nullable
    public String getRepeatPwdError() {
        return repeatPwdError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
