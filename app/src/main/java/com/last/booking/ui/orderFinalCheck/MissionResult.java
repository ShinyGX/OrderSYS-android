package com.last.booking.ui.orderFinalCheck;

import org.jetbrains.annotations.Nullable;

public class MissionResult {

    @Nullable
    private Integer data;

    @Nullable
    private String error;

    public MissionResult(@Nullable Integer data) {
        this.data = data;
    }

    public MissionResult(@Nullable String error) {
        this.error = error;
    }

    @Nullable
    public Integer getData() {
        return data;
    }

    @Nullable
    public String getError() {
        return error;
    }
}
