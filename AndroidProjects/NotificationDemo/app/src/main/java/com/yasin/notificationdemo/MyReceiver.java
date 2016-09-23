package com.yasin.notificationdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //必须可以  Export  PendingIntent 由Android系统发出
        Log.d("Receive", "onReceive: Play");
    }
}
