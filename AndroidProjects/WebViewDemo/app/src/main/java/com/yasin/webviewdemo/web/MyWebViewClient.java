package com.yasin.webviewdemo.web;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Project: com.yasin.webviewdemo.web
 * Created by Yasin
 * Date: 2016-09-24.
 */
public class MyWebViewClient extends WebViewClient {
    private BrowserSupport support;

    public MyWebViewClient(BrowserSupport support) {
        this.support = support;
    }



    //实现网址的拦截，特定功能的调用
    //返回false时，webView 会自己加载网址
    //如果这个方法返回true，那么webView不会进行网址的加载
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        boolean ret =false;
        if (url.startsWith("tel://")) {
            //TODO:使用隐式意图 ACTIONVIEW  ACTION_CALL
                //调用回调接口
            if (support != null) {
                support.loadAppUrl(view ,url);
            }

        }
        return ret;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (support != null) {
            support.onPageStarted(view, url, favicon);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (support != null) {
            support.onPageFinished(view, url);
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }
}
