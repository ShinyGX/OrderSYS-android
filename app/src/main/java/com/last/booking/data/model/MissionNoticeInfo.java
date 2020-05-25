package com.last.booking.data.model;

import java.util.Date;

public class MissionNoticeInfo {

    private Integer missionId;
    private String userPhone;
    private String userName;
    private String officeName;
    private String officeAddress;
    private String businessName;
    private Date orderTime;
    private Integer missionRegisterId;

    public MissionNoticeInfo() {
    }

    public MissionNoticeInfo(Integer missionId, String userPhone, String userName, String officeName, String officeAddress, String businessName, Date orderTime, Integer missionRegisterId) {
        this.missionId = missionId;
        this.userPhone = userPhone;
        this.userName = userName;
        this.officeName = officeName;
        this.officeAddress = officeAddress;
        this.businessName = businessName;
        this.orderTime = orderTime;
        this.missionRegisterId = missionRegisterId;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getMissionRegisterId() {
        return missionRegisterId;
    }

    public void setMissionRegisterId(Integer missionRegisterId) {
        this.missionRegisterId = missionRegisterId;
    }
}
