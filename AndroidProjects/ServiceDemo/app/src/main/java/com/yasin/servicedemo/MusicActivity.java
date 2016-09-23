package com.yasin.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.yasin.servicedemo.services.MusicBindService;

import java.io.InterruptedIOException;

public class MusicActivity extends AppCompatActivity implements ServiceConnection,Runnable {

    private MusicBindService.MusicController controller;
    private ProgressBar progressBar;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        progressBar = (ProgressBar) findViewById(R.id.music_progress);
        //绑定服务，使用上下文
        Intent intent = new Intent(this, MusicBindService.class);
        //后台音乐还可以播放
        startService(intent);


        //参数一：代表要绑定哪一个Service
        //参数二：回调接口，可以接收到Service连接成功和断开

        //解除绑定后，服务停止
        bindService(intent,this,BIND_AUTO_CREATE);


    }

    public void btnPlayMusic(View view) {
        if (controller != null) {
            controller.play();
        }
    }

    public void btnPauseMusic(View view) {
        if (controller != null) {
            controller.pause();
        }
    }


    //服务绑定 与解除绑定的回调

    /**
     * 当服务于当前绑定对象，绑定成功，服务的onBind 方法，调用并返回后调用此方法
     * @param name
     * @param service  Service onBind 绑定返回的值
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        controller = ((MusicBindService.MusicController) service);

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    @Override
    public void run() {
        running=true;
        try{
            while (running){
                if (controller != null) {
                    long duration = controller.getMusicDuration();
                    long position = controller.getposition();

                    progressBar.setMax(((int) duration));
                    //内部有一个Handler
                    progressBar.setProgress(((int) position));
                    Thread.sleep(300);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        running=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(this);
    }

    public void btnStopService(View view) {

    //如果有服务绑定  stopService后服务不会停止

        //服务的销毁    stopService（）并且没有绑定

    }
}
