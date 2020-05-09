package com.last.booking.ui.orderFinalCheck;

import com.last.booking.data.model.BusinessInfo;

import org.jetbrains.annotations.Nullable;

public class BusinessInfoResult {

    @Nullable
    private BusinessInfo businessInfo;

    @Nullable
    private String errorMsg;

    public BusinessInfoResult(@Nullable BusinessInfo businessInfo) {
        this.businessInfo = businessInfo;
    }

    public BusinessInfoResult(@Nullable String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Nullable
    public BusinessInfo getBusinessInfo() {
        return businessInfo;
    }

    @Nullable
    public String getErrorMsg() {
        return errorMsg;
    }
}
