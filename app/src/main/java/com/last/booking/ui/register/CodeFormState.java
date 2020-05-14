package com.last.booking.ui.register;

import org.jetbrains.annotations.Nullable;

public class CodeFormState {

    @Nullable
    private String codeError;

    private boolean isDataValid;

    public CodeFormState(@Nullable String codeError) {
        this.codeError = codeError;
        isDataValid = false;
    }

    public CodeFormState(boolean isDataValid) {
        codeError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public String getCodeError() {
        return codeError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }
}
