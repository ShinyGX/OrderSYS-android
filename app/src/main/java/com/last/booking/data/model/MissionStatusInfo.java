package com.last.booking.data.model;

import java.util.Date;

public class MissionStatusInfo {

    private int id;
    private String businessName;
    private String businessType;
    private String officeName;
    private String officeAddress;
    private Date missionTime;
    private int isDone;

    public MissionStatusInfo() {
    }

    public MissionStatusInfo(int id, String businessName, String businessType, String officeName, String officeAddress, Date missionTime, int isDone) {
        this.id = id;
        this.businessName = businessName;
        this.businessType = businessType;
        this.officeName = officeName;
        this.officeAddress = officeAddress;
        this.missionTime = missionTime;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public Date getMissionTime() {
        return missionTime;
    }

    public void setMissionTime(Date missionTime) {
        this.missionTime = missionTime;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }
}
