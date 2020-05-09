package com.last.booking.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private Integer userId;
    //... other data fields that may be accessible to the UI


    public LoggedInUserView(String displayName, Integer userId) {
        this.displayName = displayName;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    String getDisplayName() {
        return displayName;
    }
}
