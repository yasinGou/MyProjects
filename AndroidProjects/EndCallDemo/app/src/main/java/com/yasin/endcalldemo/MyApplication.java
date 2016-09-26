package com.yasin.endcalldemo;

import android.app.Application;

/**
 * Project: com.yasin.endcalldemo
 * Created by Yasin
 * Date: 2016-09-25.
 */
public class MyApplication extends Application {
    public String telphoneNum;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public String getTelphoneNum() {
        return telphoneNum;
    }

    public void setTelphoneNum(String telphoneNum) {
        this.telphoneNum = telphoneNum;
    }
}
