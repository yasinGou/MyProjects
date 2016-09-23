package com.yasin.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.EditText;

import com.yasin.servicedemo.services.MusicService;

public class MainActivity extends AppCompatActivity {

    private EditText textUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Context.startService(xx)启动服务
        textUrl = (EditText) findViewById(R.id.download_url);

        Intent intent = new Intent(this, MusicService.class);

        //系统会检测，是否服务已经创建。如果已经创建了，则不会再创建服务了。只会调用onStartcommand()方法
        //每次启动服务，都会调用onStartCommand()方法
        startService(intent);
    }

    public void btnPlay(View view) {
        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra("action","play");
        startService(intent);
    }

    public void btnPause(View view) {
        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra("action","pause");
        startService(intent);
    }

    public void btnStopService(View view) {
        Intent intent = new Intent(this, MusicService.class);

        //停止服务    服务自动销毁  无论开始多少次服务
        stopService(intent);
    }

    public void btnDownload(View view) {
        String url = textUrl.getText().toString();
        Intent intent = new Intent(this, DownloadListener.class);
        intent.putExtra("url",url);
        startService(intent);

    }
}
