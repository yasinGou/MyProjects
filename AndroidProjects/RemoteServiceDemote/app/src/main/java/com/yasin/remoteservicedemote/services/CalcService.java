package com.yasin.remoteservicedemote.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CalcService extends Service {
    public CalcService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    public class Calculator extends ICalcInterface.Stub{
        public int add(int a,int b){
            return a+b;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Calculator();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
