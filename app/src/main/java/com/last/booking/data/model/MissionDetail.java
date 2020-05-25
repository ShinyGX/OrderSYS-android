package com.last.booking.data.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MissionDetail {
    private int businessId;
    private String name;
    private String desc;
    private Map<Date, Integer> time;
    private List<Date> orderList;

    public MissionDetail() {
    }


    public MissionDetail(int businessId, String name, String desc, Map<Date, Integer> time, List<Date> orderList) {
        this.businessId = businessId;
        this.name = name;
        this.desc = desc;
        this.time = time;
        this.orderList = orderList;
    }

    public List<Date> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Date> orderList) {
        this.orderList = orderList;
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

    public Map<Date, Integer> getTime() {
        return time;
    }

    public void setTime(Map<Date, Integer> time) {
        this.time = time;
    }
}
