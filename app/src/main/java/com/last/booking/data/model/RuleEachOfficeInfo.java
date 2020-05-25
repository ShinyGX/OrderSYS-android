package com.last.booking.data.model;

import java.util.List;

public class RuleEachOfficeInfo {

    private Integer officeId;
    private String officeName;
    private List<RuleInfo> officeRule;

    public RuleEachOfficeInfo() {
    }

    public RuleEachOfficeInfo(Integer officeId, String officeName, List<RuleInfo> officeRule) {
        this.officeId = officeId;
        this.officeName = officeName;
        this.officeRule = officeRule;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public List<RuleInfo> getOfficeRule() {
        return officeRule;
    }

    public void setOfficeRule(List<RuleInfo> officeRule) {
        this.officeRule = officeRule;
    }
}
