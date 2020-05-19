package com.last.booking.ui.businessDetail;

import com.last.booking.data.model.BusinessInfo;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BusinessInfoResult {

    @Nullable
    private List<BusinessInfo> infoList;
    @Nullable
    private String error;


    public BusinessInfoResult(@Nullable List<BusinessInfo> infoList) {
        this.infoList = infoList;
    }

    public BusinessInfoResult(@Nullable String error) {
        this.error = error;
    }

    @Nullable
    public List<BusinessInfo> getInfoList() {
        return infoList;
    }

    @Nullable
    public String getError() {
        return error;
    }
}
