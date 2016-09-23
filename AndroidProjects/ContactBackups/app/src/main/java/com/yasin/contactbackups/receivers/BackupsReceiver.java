package com.yasin.contactbackups.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.yasin.contactbackups.services.BackupsIntentService;

public class BackupsReceiver extends BroadcastReceiver {
    public BackupsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            int type = info.getType();
            switch (type) {
                case ConnectivityManager.TYPE_WIFI:
                    Log.d("Receiver", "onReceive:开启了ＷＩＦＩ");


                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    Log.d("Receiver", "onReceive: 移动网络");
                    Intent service = new Intent(context, BackupsIntentService.class);
                    service.putExtra("info","backupsContacts");
                    context.startService(service);
                    break;
            }
        }else {
            Log.d("Receiver" , "onReceive: w无网络");
        }

    }
}
