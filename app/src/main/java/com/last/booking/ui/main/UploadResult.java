package com.last.booking.ui.main;

import org.jetbrains.annotations.Nullable;

public class UploadResult {

    @Nullable
    private String error;

    private boolean valid;

    public UploadResult(@Nullable String error) {
        this.error = error;
        valid = false;
    }

    public UploadResult(boolean valid) {
        this.valid = valid;
        error = null;
    }

    @Nullable
    public String getError() {
        return error;
    }

    public boolean isValid() {
        return valid;
    }
}
