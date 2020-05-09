package com.last.booking.data.model;

import org.jetbrains.annotations.NotNull;

public class OfficeInfo {

    private Integer officeId;
    private String officeDesc;
    private String officeAddressDesc;
    private Integer areaId;
    private String areaDesc;
    private Integer cityId;
    private String cityDecs;

    public OfficeInfo() {
    }

    public OfficeInfo(Integer officeId, String officeDesc, String officeAddressDesc, Integer areaId, String areaDesc, Integer cityId, String cityDecs) {
        this.officeId = officeId;
        this.officeDesc = officeDesc;
        this.officeAddressDesc = officeAddressDesc;
        this.areaId = areaId;
        this.areaDesc = areaDesc;
        this.cityId = cityId;
        this.cityDecs = cityDecs;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeDesc() {
        return officeDesc;
    }

    public void setOfficeDesc(String officeDesc) {
        this.officeDesc = officeDesc;
    }

    public String getOfficeAddressDesc() {
        return officeAddressDesc;
    }

    public void setOfficeAddressDesc(String officeAddressDesc) {
        this.officeAddressDesc = officeAddressDesc;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityDecs() {
        return cityDecs;
    }

    public void setCityDecs(String cityDecs) {
        this.cityDecs = cityDecs;
    }

    @NotNull
    @Override
    public String toString() {
        return "OfficeInfo{" +
                "officeId=" + officeId +
                ", officeDesc='" + officeDesc + '\'' +
                ", officeAddressDesc='" + officeAddressDesc + '\'' +
                ", areaId=" + areaId +
                ", areaDesc='" + areaDesc + '\'' +
                ", cityId=" + cityId +
                ", cityDecs='" + cityDecs + '\'' +
                '}';
    }
}
