package com.yasin.bootreceiver.receivers;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.yasin.bootreceiver.R;

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Receive", "onReceive: reboot");
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        Notification notification=builder.setContentTitle("启动了").setContentText("检测手机启动").setSmallIcon(R.mipmap.ic_launcher).build();
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(998,notification);
        Intent intent1 = new Intent("android.intent.action.CALL");
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
