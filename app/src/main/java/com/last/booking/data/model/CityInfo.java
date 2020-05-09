package com.last.booking.data.model;

import org.jetbrains.annotations.NotNull;

public class CityInfo {

    private Integer city_id;
    private String city_desc;

    public CityInfo() {
    }

    public CityInfo(Integer city_id, String city_desc) {
        this.city_id = city_id;
        this.city_desc = city_desc;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getCity_desc() {
        return city_desc;
    }

    public void setCity_desc(String city_desc) {
        this.city_desc = city_desc;
    }

    @NotNull
    @Override
    public String toString() {
        return "CityInfo{" +
                "city_id=" + city_id +
                ", city_desc='" + city_desc + '\'' +
                '}';
    }
}
