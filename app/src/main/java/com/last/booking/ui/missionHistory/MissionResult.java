package com.last.booking.ui.missionHistory;

import org.jetbrains.annotations.Nullable;

public class MissionResult {

    @Nullable
    private String error;

    private boolean success;

    public MissionResult(@Nullable String error) {
        this.error = error;
        success = false;
    }

    public MissionResult(boolean success) {
        this.success = success;
    }

    @Nullable
    public String getError() {
        return error;
    }

    public boolean isSuccess() {
        return success;
    }
}
