package com.last.booking;

import android.app.Application;

import com.cengalabs.flatui.FlatUI;
import com.facebook.drawee.backends.pipeline.Fresco;

public class BookApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.SKY);
    }
}
