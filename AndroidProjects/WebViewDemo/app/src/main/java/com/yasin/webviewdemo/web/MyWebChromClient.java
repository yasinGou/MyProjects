package com.yasin.webviewdemo.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Project: com.yasin.webviewdemo.web
 * Created by Yasin
 * Date: 2016-09-24.
 */

//给WebViews设置上，能够监听Webview加载进度，标题显示，JS对话框
public class MyWebChromClient extends WebChromeClient{

    private BrowserSupport support;

    public MyWebChromClient(BrowserSupport support) {
        this.support = support;
    }

    //加载进度变化 0-100
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (support != null) {
            support.onProgressChanged(view,newProgress);
        }

    }


    //收到标题，显示标题
    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (support != null) {
            support.onReceivedTitle(view,title);
        }
    }
}
