package com.last.booking.data.model;

public class BusinessInfo {
    private Integer business_id;
    private String business_desc;
    private String business_detail;
    private Integer business_type_id;


    public BusinessInfo() {
    }

    public BusinessInfo(Integer business_id, String business_desc, String business_detail, Integer business_type_id) {
        this.business_id = business_id;
        this.business_desc = business_desc;
        this.business_detail = business_detail;
        this.business_type_id = business_type_id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public String getBusiness_desc() {
        return business_desc;
    }

    public void setBusiness_desc(String business_desc) {
        this.business_desc = business_desc;
    }

    public String getBusiness_detail() {
        return business_detail;
    }

    public void setBusiness_detail(String business_detail) {
        this.business_detail = business_detail;
    }

    public Integer getBusiness_type_id() {
        return business_type_id;
    }

    public void setBusiness_type_id(Integer business_type_id) {
        this.business_type_id = business_type_id;
    }
}
