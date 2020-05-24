package com.last.booking.ui.orderFinalCheck;

import com.last.booking.data.model.MissionAddResult;

import org.jetbrains.annotations.Nullable;

public class MissionResult {

    @Nullable
    private MissionAddResult data;

    @Nullable
    private String error;

    public MissionResult(@Nullable MissionAddResult data) {
        this.data = data;
    }

    public MissionResult(@Nullable String error) {
        this.error = error;
    }

    @Nullable
    public MissionAddResult getData() {
        return data;
    }

    @Nullable
    public String getError() {
        return error;
    }
}
