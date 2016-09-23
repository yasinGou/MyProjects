package com.yasin.servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

public class DownLoadService extends Service implements Runnable{
    public DownLoadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    private List<String> urls;
    private boolean running;
    private  Thread thread;

    @Override
    public void onCreate() {
        super.onCreate();
        urls=new ArrayList<>();
        thread=new Thread(this);
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String url = intent.getStringExtra("url");
            if (url != null) {
                urls.add(url);
            }

        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void run() {
        running=true;
        try {
            while (running){
                if (urls != null) {
                    if (!urls.isEmpty()) {
                        //一直移除第0个，先进先出
                        String url = urls.remove(0);
                    //TODO：实现下载

                    }
                }


                Thread.sleep(300);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        urls.clear();
        urls=null;
    }
}
