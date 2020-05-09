package com.last.booking.data.model;

import java.util.Date;
import java.util.List;

public class MissionDetail {
    private int businessId;
    private String name;
    private String desc;
    private List<Date> time;

    public MissionDetail() {
    }

    public MissionDetail(int businessId, String name, String desc, List<Date> time) {
        this.businessId = businessId;
        this.name = name;
        this.desc = desc;
        this.time = time;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Date> getTime() {
        return time;
    }

    public void setTime(List<Date> time) {
        this.time = time;
    }
}
