package com.last.booking;

import com.last.booking.data.Userdata;
import com.last.booking.uitl.CheckUtil;

import java.util.List;

public class SmsManager {

    private static SmsManager mInstance;
    private SmsManager(){}

    private static final Object lock = new Object();
    public static SmsManager getInstance()
    {
        if(mInstance == null)
        {
            synchronized (lock)
            {
                if(mInstance == null)
                    mInstance = new SmsManager();
            }
        }

        return mInstance;
    }


    public void sendMessage(String text)
    {
        if(Userdata.getInstance().getUserInfo() == null)
            return;

        if(Userdata.getInstance().getUserInfo().getPhone() != null &&
                !Userdata.getInstance().getUserInfo().getPhone().isEmpty() &&
                 CheckUtil.isPhone(Userdata.getInstance().getUserInfo().getPhone())) {



            android.telephony.SmsManager manager = android.telephony.SmsManager.getDefault();
            List<String> msg = manager.divideMessage(text);
            for(String str : msg)
            {
                manager.sendTextMessage(Userdata.getInstance().getUserInfo().getPhone(),
                        null,str,null,null);
            }

        }
    }



}
