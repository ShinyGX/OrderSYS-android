package com.last.booking.data.model;

public class OfficeDetail {
    private Integer officeId;
    private String desc;
    private String address;

    public OfficeDetail() {
    }

    public OfficeDetail(Integer officeId, String desc, String address) {
        this.officeId = officeId;
        this.desc = desc;
        this.address = address;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
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
