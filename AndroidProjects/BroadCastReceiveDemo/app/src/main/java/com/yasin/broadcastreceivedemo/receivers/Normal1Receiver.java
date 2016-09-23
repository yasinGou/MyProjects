package com.yasin.broadcastreceivedemo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Normal1Receiver extends BroadcastReceiver {
    public Normal1Receiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Recevie", "onReceive1: ");

        //intent 参数传递的是原始的广播信息
        int code = getResultCode();
        String data = getResultData();
        //获取传递的参数
        Bundle extras = getResultExtras(true);

    }
}
