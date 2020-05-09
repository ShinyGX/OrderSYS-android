package com.last.booking.data.model;

public class OfficeDetail {
    private String desc;
    private String address;

    public OfficeDetail() {
    }

    public OfficeDetail(String desc, String address) {
        this.desc = desc;
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
