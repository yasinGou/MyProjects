package com.yasin.webviewdemo.web;

/**
 * Project: com.yasin.webviewdemo.web
 * Created by Yasin
 * Date: 2016-09-24.
 */

//用于Js 调用Java 的功能

import android.content.Context;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JsSupport {

    private Context context;

    public JsSupport(Context context) {
        this.context = context;
    }

    //有注解，代表JS可以直接调用
    @JavascriptInterface
    public void showToast(String str){
        Toast.makeText(context,str, Toast.LENGTH_SHORT).show();
    }
    //Js调用Java 获取数据
    @JavascriptInterface
    public String getDeviceMode(){

        return Build.DEVICE;
    }
}
