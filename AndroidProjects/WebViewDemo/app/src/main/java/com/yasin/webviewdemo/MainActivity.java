package com.yasin.webviewdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yasin.webviewdemo.web.BrowserSupport;
import com.yasin.webviewdemo.web.MyWebChromClient;
import com.yasin.webviewdemo.web.MyWebViewClient;

public class MainActivity extends AppCompatActivity implements BrowserSupport {

    private WebView webview;
    private ProgressBar webProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView) findViewById(R.id.web_view);
        webProgress = (ProgressBar) findViewById(R.id.web_progressbar);
        //TODO:解决点击超链接到系统自带浏览器的问题
        //设置WebViewClient对象可以解决跳转自带浏览器的问题
        //如果不需要拦截网址，可以使用系统的对象即可
        webview.setWebViewClient(new MyWebViewClient(this));
        //TODO:实现网页加载进度显示，标题显示
        webview.setWebChromeClient(new MyWebChromClient(this));

        //TODO:部分网址需要浏览器支持，打开JavaScript
        WebSettings settings = webview.getSettings();
        //开启JS支持
        settings.setJavaScriptEnabled(true);
        //在网页中显示放大缩小（通常不使用）
        //手指滑动缩放
        settings.supportZoom();

        if (Build.VERSION.SDK_INT < 11) {
            settings.setBuiltInZoomControls(true);
        } else {
            settings.setDisplayZoomControls(true);
        }

        //允许访问 assets目录中的内容
        settings.setAllowFileAccess(true);

        //设置webview排版算法，实现单列显示，不允许左右滑动
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //加载网址，必须加上网络访问权限
        //  webview.loadUrl("http://mail.163.com");
        //加载android assets文件夹中网页
        webview.loadUrl("file:///android_asset/index.html");
        //进入到App1 目录中的index.html
      //  webview.loadUrl("file:///android_asset/app1/index.html");

        //从JSON获取的网页通常不包含<body> 需要手动添加css，实现单列显示
        String message="";
        //加载源码数据
        //参数1:HTML源码
        //参数2:数据类型，如果参数一是html，那么 text/html;charset=utf-8 一定要写charset
        //参数3：如果参数1是Base64编码的数据，那么这个地方写“base64”,否则为null

        String html="<html><head><style>img {width:100%}</style></head><body>"+message+"";

        webview.loadData(message,"text/css",null);

        //如果HTML中包含了相对路径的图片超链接，使用loadData将无法显示
      //  webview.loadDataWithBaseURL();


        //网址拦截 需要webViewClient 来实现

    }

    // 实现点击后退，后退网页
    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    //实现网页后退后还可以前进
    public void btnGoForward(View view) {
        if (webview.canGoForward()) {
            webview.goForward();
        }
    }

    public void btnStopLoading(View view) {
        //停止当前的页面加载
        webview.stopLoading();
    }

    public void btnReLoad(View view) {
        //重新加载当前网页
        webview.reload();
    }

    @Override
    public void onReceivedTitle(WebView webView, String title) {
        setTitle(title);
    }

    @Override
    public void onProgressChanged(WebView webView, int progress) {

        webProgress.setProgress(progress);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        webProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        webProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void loadAppUrl(WebView view, String url) {
        if (url.startsWith("tel://")) {
           // String phone = url.substring(6);
            //如果使用ACTIONVIEW
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }

}
