package com.last.booking.ui.register;

import org.jetbrains.annotations.Nullable;

public class RegisterFormState {

    @Nullable
    private String registerError;
    private boolean isDataValid;

    public RegisterFormState(@Nullable String registerError) {
        this.registerError = registerError;
    }

    public RegisterFormState(boolean isDataValid) {
        this.isDataValid = isDataValid;
    }

    @Nullable
    public String getRegisterError() {
        return registerError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
