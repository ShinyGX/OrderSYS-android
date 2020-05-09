package com.last.booking.uitl;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ActivityUtil {

    public static void startActivity(@NonNull Context packageContext, @NonNull Class<?> cls)
    {
        Intent intent = new Intent();
        intent.setClass(packageContext,cls);
        packageContext.startActivity(intent);
    }
}
