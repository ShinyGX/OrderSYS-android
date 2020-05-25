package com.last.booking.ui.rule;

import com.last.booking.data.model.RuleEachOfficeInfo;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EachOfficeRuleResult {

    @Nullable
    private List<RuleEachOfficeInfo> infoList;

    @Nullable
    private String error;

    public EachOfficeRuleResult(@Nullable List<RuleEachOfficeInfo> infoList) {
        this.infoList = infoList;
    }

    public EachOfficeRuleResult(@Nullable String error) {
        this.error = error;
    }

    @Nullable
    public List<RuleEachOfficeInfo> getInfoList() {
        return infoList;
    }

    @Nullable
    public String getError() {
        return error;
    }
}
