package com.last.booking.ui.register;

import org.jetbrains.annotations.Nullable;

public class UsernameFormState {
    @Nullable
    private String usernameError;

    private boolean isDataValid;

    public UsernameFormState(@Nullable String usernameError) {
        this.usernameError = usernameError;
        isDataValid = false;
    }

    public UsernameFormState(boolean isDataValid) {
        this.isDataValid = isDataValid;
        usernameError = null;
    }

    @Nullable
    public String getUsernameError() {
        return usernameError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
