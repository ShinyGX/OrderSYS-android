package com.last.booking.data.model;

import org.jetbrains.annotations.NotNull;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class UserInfo {

    private Integer userId;

    private String userName;
    private String userIcon;

    public UserInfo() {
    }

    public UserInfo(Integer userId, String userName, String userIcon) {
        this.userId = userId;
        this.userName = userName;
        this.userIcon = userIcon;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    @NotNull
    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                '}';
    }
}
