package com.last.booking.ui.orderFinalCheck;

import org.jetbrains.annotations.Nullable;

public class MissionResult {

    @Nullable
    private String data;

    @Nullable
    private Integer error;

    public MissionResult(@Nullable String data) {
        this.data = data;
    }

    public MissionResult(@Nullable Integer error) {
        this.error = error;
    }

    @Nullable
    public String getData() {
        return data;
    }

    @Nullable
    public Integer getError() {
        return error;
    }
}
