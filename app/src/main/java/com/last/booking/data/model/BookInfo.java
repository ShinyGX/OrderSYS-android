package com.last.booking.data.model;

import java.util.Date;
import java.util.Map;

public class BookInfo {

    private Integer businessId;
    private String businessName;
    private String businessDesc;
    private Map<Date,Integer> usefulTime;

    public BookInfo() {
    }

    public BookInfo(Integer businessId, String businessName, String businessDesc, Map<Date, Integer> usefulTime) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessDesc = businessDesc;
        this.usefulTime = usefulTime;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public Map<Date, Integer> getUsefulTime() {
        return usefulTime;
    }

    public void setUsefulTime(Map<Date, Integer> usefulTime) {
        this.usefulTime = usefulTime;
    }
}
