package com.yasin.day25demo;

import android.app.Application;
import android.util.Log;

/**
 * Project: com.yasin.day25demo
 * Created by Administrator
 * Date: 2016-08-30.
 */
public class BaseApplication extends Application {
    private static final String TAG =BaseApplication.class.getSimpleName() ;
    private String text;
    private int age;
    @Override
    public void onCreate() {
        //存储全局变量
        //在这个应用完全退出之前，只执行一次。初始化应用
        //同一个应用中所有activity共用一个application
        super.onCreate();
        text="hello world";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
