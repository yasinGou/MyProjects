package com.yasin.broadcastreceivedemo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Normal0Receiver extends BroadcastReceiver {
    public Normal0Receiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Recevie", "onReceive0: ");
        //发送的有序广播，在这儿可以终止广播
//            abortBroadcast();
        //设置处理的结果
//        Bundle extras = new Bundle();
//        extras.putString("name","admin");
//        setResult(998,"hello",extras);

    }
}
