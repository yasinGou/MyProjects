package com.yasin.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yasin.webviewdemo.web.JsSupport;
import com.yasin.webviewdemo.web.MyWebViewClient;

public class JsDemoActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_demo);
        webview = (WebView) findViewById(R.id.js_webview);

        webview.setWebViewClient(new MyWebViewClient(null));
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        //对象的类必须使用注解@JavascriptInterface
        webview.addJavascriptInterface(new JsSupport(this),"myandroid");

         //设置访问assert 目录的能力
        settings.setAllowFileAccess(true);

        webview.loadUrl("file:///android_assert/index.html");




    }
}
