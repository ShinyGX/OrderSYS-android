package com.last.booking.ui.register;

import org.jetbrains.annotations.Nullable;

public class PhoneFormState {
    @Nullable
    private String phoneError;

    private boolean isDataValid;

    public PhoneFormState(@Nullable String phoneError) {
        this.phoneError = phoneError;
        isDataValid = false;
    }

    public PhoneFormState(boolean isDataValid) {
        phoneError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public String getPhoneError() {
        return phoneError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
