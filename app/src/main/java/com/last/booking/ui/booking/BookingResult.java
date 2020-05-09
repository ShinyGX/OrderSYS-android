package com.last.booking.ui.booking;

import android.support.annotation.Nullable;

public class BookingResult {

    @Nullable
    private CityInfoView bookingCityView;
    @Nullable
    private AreaInfoView areaInfoView;
    @Nullable
    private String msg;

    public BookingResult(@Nullable AreaInfoView areaInfoView) {
        this.areaInfoView = areaInfoView;
    }

    public BookingResult(@Nullable CityInfoView bookingCityView) {
        this.bookingCityView = bookingCityView;
    }

    public BookingResult(@Nullable String msg) {
        this.msg = msg;
    }

    @Nullable
    public CityInfoView getCityView() {
        return bookingCityView;
    }

    @Nullable
    public AreaInfoView getAreaInfoView() {
        return areaInfoView;
    }

    @Nullable
    public String getMsg() {
        return msg;
    }
}
