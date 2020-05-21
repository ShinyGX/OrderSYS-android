package com.last.booking.ui.missionHistory;

import com.last.booking.data.model.MissionStatusInfo;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MissionInfoResult {

    @Nullable
    private List<MissionStatusInfo> info;

    @Nullable
    private String error;

    public MissionInfoResult(@Nullable String error) {
        this.error = error;
    }

    public MissionInfoResult(@Nullable List<MissionStatusInfo> info) {
        this.info = info;
    }

    @Nullable
    public List<MissionStatusInfo> getInfo() {
        return info;
    }

    @Nullable
    public String getError() {
        return error;
    }
}
