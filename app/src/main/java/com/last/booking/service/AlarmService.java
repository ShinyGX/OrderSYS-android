package com.last.booking.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.last.booking.uitl.NotificationUtil;
import com.last.booking.uitl.NotifyObject;

import java.io.IOException;

public class AlarmService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String str = intent.getStringExtra("KEY_NOTIFY");
        if(str == null || str.trim().length() == 0)
            return  super.onStartCommand(intent, flags, startId);
        try {
            NotifyObject obj = NotifyObject.from(str);
            NotificationUtil.notifyByAlarmByReceiver(this,obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
