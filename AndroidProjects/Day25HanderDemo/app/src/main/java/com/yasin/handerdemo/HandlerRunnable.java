package com.yasin.handerdemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Project: com.yasin.handerdemo
 * Created by Administrator
 * Date: 2016-08-30.
 */
public class HandlerRunnable implements Runnable {
    private static final String TAG =HandlerRunnable.class.getSimpleName() ;
    private int n;
    private  Handler handler;

    @Override
    public void run() {
        Looper.prepare();
           handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        n=msg.arg1;
                        Log.d(TAG, "handleMessage: "+n+Thread.currentThread().getName());
                        break;
                }
                return true;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i;
                for (i = 0; i <10 ; i++) {
                    Message.obtain(handler,0,i,0).sendToTarget();
                }


            }
        }).start();
        Looper.loop();

    }

}
