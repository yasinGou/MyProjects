package com.yasin.webviewdemo.web;

import android.graphics.Bitmap;
import android.webkit.WebView;

/**
 * Project: com.yasin.webviewdemo.web
 * Created by Yasin
 * Date: 2016-09-24.
 */

//用于在WebChromeClient中接收UI 状态变化
public interface BrowserSupport {
    void onReceivedTitle(WebView webView,String title);
    void onProgressChanged(WebView webView,int progress);
    void onPageStarted(WebView view, String url, Bitmap favicon);
    void onPageFinished(WebView view, String url);
    void loadAppUrl(WebView view, String url);
}
